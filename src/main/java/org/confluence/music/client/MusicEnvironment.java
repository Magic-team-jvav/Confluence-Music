package org.confluence.music.client;

import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.AABB;
import org.confluence.lib.api.entity.Boss;
import org.confluence.lib.common.worldgen.biome.MiniBiome;
import org.confluence.lib.util.LibDateUtils;
import org.confluence.mod.client.gameevent.ClientGameEventSystem;
import org.confluence.mod.client.handler.WeatherHandler;
import org.confluence.mod.common.entity.boss.BrainOfCthulhu;
import org.confluence.mod.common.entity.npc.BaseNPC;
import org.confluence.mod.common.gameevent.*;
import org.confluence.mod.common.init.ModMiniBiomes;
import org.confluence.mod.common.init.entity.BossEntities;
import org.confluence.mod.util.OverworldUtils;
import org.confluence.music.common.network.StructureFoundPacketS2C;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Map;

/// 玩家周围所有与“现在该放哪首音乐”有关的信息。
///
/// 每客户端刻由 [MusicHandler] 调用 [#update] 刷新：
/// 维度、生物群系、高度、时间、天气、游戏事件等廉价信息每刻都取；
/// 需要遍历大范围实体的 Boss 与城镇 NPC 则按 [#SCAN_INTERVAL] 节流。
///
/// 本类只负责“看”，不负责“选”，选择规则见 [MusicResolver]。
public final class MusicEnvironment {
    /// 实体扫描（Boss、城镇 NPC）的间隔，单位：刻。
    private static final int SCAN_INTERVAL = 20;
    /// 清晨降雨时段的结束时刻：07:30。
    private static final int MORNING_RAIN_END = LibDateUtils.getDayTime(7, 30);
    /// 判定为“城镇”所需的附近 NPC 数量。
    private static final int TOWN_NPC_COUNT = 3;
    /// 统计城镇 NPC 的半径，单位：格。
    private static final double TOWN_SCAN_RADIUS = 80.0D;
    /// 1.20.1 的 {@code LevelRenderer#getLastViewDistance} 返回的是区块数，换算成格。
    private static final int BLOCKS_PER_CHUNK = 16;

    /// Boss 与专属音乐的对应关系，延迟构建（见 {@link #bossMusics()}）。
    private static volatile @Nullable Map<EntityType<?>, MusicSelection> bossMusics;

    private boolean inOverworld;
    private boolean inUnderworld;
    private boolean surface;
    private boolean space;
    private boolean day;
    private boolean morningRain;
    private boolean raining;
    private boolean thundering;
    private boolean windy;
    private boolean creative;
    private boolean town;
    private boolean sandstorm;
    private boolean townMiniBiome;
    private @Nullable Holder<Biome> biome;
    /// 当前所在的迷你群系 id（MagicLib 的 {@code MiniBiome}），没有则为 null。
    private @Nullable ResourceLocation miniBiome;
    /// 正在进行的、带有专属音乐的事件（血月、日食、入侵、月相事件）。
    private @Nullable MusicSelection eventMusic;
    /// 由 {@link StructureFoundPacketS2C} 告知的地牢分层音乐。
    private @Nullable MusicSelection dungeonMusic;
    /// 最近一次扫描到的、带有专属音乐的 Boss。
    private @Nullable Entity bossEntity;
    private @Nullable MusicSelection bossMusic;
    private int scanCooldown;

    /// 刷新一次环境快照。每客户端刻调用一次。
    public void update(LocalPlayer player, Minecraft minecraft) {
        Level level = player.level();
        BlockPos pos = player.blockPosition();
        ResourceKey<Level> dimension = level.dimension();
        this.biome = level.getBiome(pos);
        this.inOverworld = dimension == OverworldUtils.dimension();
        this.inUnderworld = dimension == OverworldUtils.underworld();
        this.surface = pos.getY() > OverworldUtils.getSurfaceY();
        this.space = pos.getY() > OverworldUtils.getSpaceY();
        int dayTime = LibDateUtils.getDayTime(level);
        this.day = LibDateUtils.isDay(dayTime);
        this.morningRain = LibDateUtils.isWithinDayTime(LibDateUtils._04$30, MORNING_RAIN_END, dayTime);
        this.raining = level.isRaining();
        this.thundering = level.isThundering();
        this.windy = WeatherHandler.WIND_SPEED.length() > 1.0F;
        this.creative = isCreativeMode(minecraft);
        this.eventMusic = currentEventMusic();
        this.sandstorm = ClientGameEventSystem.isEventStarted(SandstormGameEvent.KEY);
        scan(player, minecraft);
    }

    /// 清空全部状态，用于离开世界。
    public void reset() {
        this.inOverworld = false;
        this.inUnderworld = false;
        this.surface = false;
        this.space = false;
        this.day = false;
        this.morningRain = false;
        this.raining = false;
        this.thundering = false;
        this.windy = false;
        this.creative = false;
        this.town = false;
        this.sandstorm = false;
        this.townMiniBiome = false;
        this.biome = null;
        this.miniBiome = null;
        this.eventMusic = null;
        this.dungeonMusic = null;
        this.bossEntity = null;
        this.bossMusic = null;
        this.scanCooldown = 0;
    }

    /// 收到 {@link StructureFoundPacketS2C} 时更新地牢分层音乐。
    public void setStructureFound(byte data) {
        this.dungeonMusic = switch (data) {
            case StructureFoundPacketS2C.DUNGEON_FLOOR_1 -> MusicSelection.DUNGEON_FLOOR_1;
            case StructureFoundPacketS2C.DUNGEON_FLOOR_2 -> MusicSelection.DUNGEON_FLOOR_2;
            default -> null;
        };
    }

    public boolean inOverworld() {
        return inOverworld;
    }

    public boolean inUnderworld() {
        return inUnderworld;
    }

    /// 是否位于地表（高于 {@link OverworldUtils#getSurfaceY()}）。
    public boolean surface() {
        return surface;
    }

    /// 是否位于太空层（高于 {@link OverworldUtils#getSpaceY()}）。
    public boolean space() {
        return space;
    }

    public boolean day() {
        return day;
    }

    /// 是否处于清晨降雨时段（04:30 ~ 07:30）。
    public boolean morningRain() {
        return morningRain;
    }

    public boolean raining() {
        return raining;
    }

    public boolean thundering() {
        return thundering;
    }

    public boolean windy() {
        return windy;
    }

    public boolean creative() {
        return creative;
    }

    /// 附近是否有至少 {@link #TOWN_NPC_COUNT} 个 NPC。
    public boolean town() {
        return town;
    }

    /// 沙漠中是否正在刮沙尘暴。
    public boolean sandstorm() {
        return sandstorm;
    }

    public @Nullable Holder<Biome> biome() {
        return biome;
    }

    public @Nullable MusicSelection eventMusic() {
        return eventMusic;
    }

    public @Nullable MusicSelection dungeonMusic() {
        return dungeonMusic;
    }

    public @Nullable MusicSelection bossMusic() {
        return bossMusic;
    }

    /// 迷你群系（MagicLib 的 `MiniBiome`，由汇流注册并进行方块计数）里的专属音乐。
    ///
    /// 陨石、墓地、微光（以太）各有专属曲目；花岗岩洞、大理石洞、蜘蛛巢、蜂巢、城镇没有。
    /// 这些迷你群系会盖掉所在群系的音乐，因此 [MusicResolver] 把它排在群系音乐之前。
    public @Nullable MusicSelection miniBiomeMusic() {
        ResourceLocation id = this.miniBiome;
        if (id == null) return null;
        if (id.equals(ModMiniBiomes.METEORITE.id())) return MusicSelection.METEORITE;
        if (id.equals(ModMiniBiomes.GRAVEYARD.id())) return MusicSelection.GRAVEYARD;
        if (id.equals(ModMiniBiomes.SHIMMER.id())) return MusicSelection.AETHER;
        return null;
    }

    private void scan(LocalPlayer player, Minecraft minecraft) {
        if (scanCooldown-- > 0) return;
        this.scanCooldown = SCAN_INTERVAL;
        scanMiniBiome(player);
        scanBoss(player, minecraft);
        scanTown(player);
    }

    /// 查询当前所在迷你群系。
    ///
    /// `MiniBiome` 的方块计数按区块 section 缓存（汇流在构造函数里调用
    /// `DynamicBiomeUtils.enable()` 打开，客户端同样生效），但查询仍跟着 [#SCAN_INTERVAL] 节流，
    /// 免得每刻都去遍历窗口内的 section。
    private void scanMiniBiome(LocalPlayer player) {
        @Nullable MiniBiome.Marker marker = MiniBiome.primaryAt(player.level(), player.blockPosition());
        this.miniBiome = marker == null ? null : marker.id();
        this.townMiniBiome = this.miniBiome != null && this.miniBiome.equals(ModMiniBiomes.TOWN.id());
    }

    /// 找出视野范围内带有专属音乐的 Boss。
    /// 找到之后就一直沿用，只在它死亡、消失或离开视野时才重新扫描，避免每刻遍历实体。
    private void scanBoss(LocalPlayer player, Minecraft minecraft) {
        if (!minecraft.gui.getBossOverlay().shouldPlayMusic()) {
            this.bossEntity = null;
            this.bossMusic = null;
            return;
        }
        if (bossEntity == null || !isValidBoss(bossEntity, player, minecraft)) {
            this.bossEntity = findBoss(player, minecraft);
        }
        this.bossMusic = bossEntity == null ? null : bossMusic(bossEntity);
    }

    private void scanTown(LocalPlayer player) {
        if (this.townMiniBiome) { // 汇流自己判定“城镇”用迷你群系，比数 NPC 更贴近它的语义
            this.town = true;
            return;
        }
        AABB area = new AABB(player.blockPosition()).inflate(TOWN_SCAN_RADIUS);
        this.town = player.level().getEntities(player, area, entity -> entity instanceof BaseNPC).size() >= TOWN_NPC_COUNT;
    }

    private static boolean isValidBoss(Entity boss, LocalPlayer player, Minecraft minecraft) {
        if (!boss.isAlive() || boss.isRemoved() || boss.level() != player.level()) return false;
        double range = bossScanRange(minecraft);
        return boss.distanceToSqr(player) <= range * range;
    }

    /// {@return 距离玩家最近、且带有专属音乐的 Boss，没有则返回 null}
    private static @Nullable Entity findBoss(LocalPlayer player, Minecraft minecraft) {
        AABB area = new AABB(player.blockPosition()).inflate(bossScanRange(minecraft));
        Entity nearest = null;
        double nearestDistanceSqr = Double.MAX_VALUE;
        for (Entity entity : player.level().getEntities(player, area, entity -> entity instanceof Boss)) {
            if (bossMusic(entity) == null) continue; // 没有对应音乐的 Boss（或其身体部件）不参与
            double distanceSqr = entity.distanceToSqr(player);
            if (distanceSqr < nearestDistanceSqr) {
                nearestDistanceSqr = distanceSqr;
                nearest = entity;
            }
        }
        return nearest;
    }

    /// @return 某个 Boss 当前应当播放的音乐种类，没有专属音乐时返回 null
    ///
    /// 克苏鲁之脑分两个阶段、两首音乐：`BRAIN_OF_CTHULHU_PHASE_1` / `BRAIN_OF_CTHULHU_PHASE_2`。
    /// 阶段位是同步数据，客户端可以直接读。
    private static @Nullable MusicSelection bossMusic(Entity boss) {
        if (boss instanceof BrainOfCthulhu brain) {
            return brain.isPhase2() ? MusicSelection.BRAIN_OF_CTHULHU_PHASE_2 : MusicSelection.BRAIN_OF_CTHULHU_PHASE_1;
        }
        return bossMusics().get(boss.getType());
    }

    private static double bossScanRange(Minecraft minecraft) {
        return minecraft.levelRenderer.getLastViewDistance() * BLOCKS_PER_CHUNK;
    }

    private static boolean isCreativeMode(Minecraft minecraft) {
        MultiPlayerGameMode gameMode = minecraft.gameMode;
        return gameMode == null || gameMode.getPlayerMode().isCreative();
    }

    /// {@return 正在进行中的、带有专属音乐的事件，没有则返回 null}
    private static @Nullable MusicSelection currentEventMusic() {
        if (ClientGameEventSystem.isEventStarted(BloodMoonGameEvent.KEY)) return MusicSelection.BLOOD_MOON;
        if (ClientGameEventSystem.isEventStarted(SolarEclipseGameEvent.KEY)) return MusicSelection.ECLIPSE;
        if (ClientGameEventSystem.isEventStarted(SlimeRainGameEvent.KEY)) return MusicSelection.SLIME_RAIN;
        if (ClientGameEventSystem.isEventStarted(GoblinArmyGameEvent.KEY)) return MusicSelection.GOBLIN_ARMY;
        if (ClientGameEventSystem.isEventStarted(PirateInvasionGameEvent.KEY)) return MusicSelection.PIRATE_INVASION;
        if (ClientGameEventSystem.isEventStarted(PumpkinMoonGameEvent.KEY)) return MusicSelection.PUMPKIN_MOON;
        if (ClientGameEventSystem.isEventStarted(FrostMoonGameEvent.KEY)) return MusicSelection.FROST_MOON;
        return null;
    }

    /// Boss 与专属音乐的对应关系（克苏鲁之脑按阶段取，见 [#bossMusic(Entity)]，这里只留第一阶段的默认值）。
    ///
    /// 延迟构建：本类在数据生成等场合也会被加载，那时实体类型注册表还没就绪。
    private static Map<EntityType<?>, MusicSelection> bossMusics() {
        Map<EntityType<?>, MusicSelection> map = bossMusics;
        if (map == null) {
            // 用 put 逐个填充而不是 Map.ofEntries：实体类型的泛型参数会牵扯到其它模组的依赖
            Map<EntityType<?>, MusicSelection> built = new Reference2ObjectOpenHashMap<>();
            built.put(BossEntities.KING_SLIME.get(), MusicSelection.KING_SLIME);
            built.put(BossEntities.EYE_OF_CTHULHU.get(), MusicSelection.EYE_OF_CTHULHU);
            built.put(BossEntities.EATER_OF_WORLDS.get(), MusicSelection.EATER_OF_WORLDS);
            built.put(BossEntities.DEERCLOPS.get(), MusicSelection.DEERCLOPS);
            built.put(BossEntities.QUEEN_BEE.get(), MusicSelection.QUEEN_BEE);
            built.put(BossEntities.SKELETRON.get(), MusicSelection.SKELETRON);
            built.put(BossEntities.WALL_OF_FLESH.get(), MusicSelection.WALL_OF_FLESH);
            built.put(BossEntities.HILL_OF_FLESH.get(), MusicSelection.WALL_OF_FLESH);
            built.put(BossEntities.SKELETRON_PRIME.get(), MusicSelection.SKELETRON_PRIME);
            // 以下 Boss 的音乐文件尚未补齐，补齐后取消注释即可：
            // built.put(BossEntities.THE_TWINS.get(), MusicSelection.THE_TWINS);                 // boss_2
            // built.put(BossEntities.THE_DESTROYER.get(), MusicSelection.THE_DESTROYER);         // boss_3
            // built.put(BossEntities.PLANTERA.get(), MusicSelection.PLANTERA);                   // plantera
            // built.put(BossEntities.LUNATIC_CULTIST.get(), MusicSelection.LUNATIC_CULTIST);     // boss_4
            map = bossMusics = Collections.unmodifiableMap(built);
        }
        return map;
    }
}
