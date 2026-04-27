package org.confluence.music.client;

import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.ChannelAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.AABB;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;
import net.neoforged.neoforge.client.event.SelectMusicEvent;
import net.neoforged.neoforge.common.Tags;
import org.confluence.lib.api.entity.Boss;
import org.confluence.lib.util.LibDateUtils;
import org.confluence.mod.client.gameevent.ClientGameEventSystem;
import org.confluence.mod.client.handler.WeatherHandler;
import org.confluence.mod.common.gameevent.BloodMoonGameEvent;
import org.confluence.mod.common.init.ModBiomes;
import org.confluence.mod.common.init.ModTags;
import org.confluence.mod.util.OverworldUtils;
import org.confluence.music.ConfluenceMusic;
import org.confluence.music.common.CMCommonConfigs;
import org.confluence.music.common.init.CMMusics;
import org.confluence.music.common.network.StructureFoundPacketS2C;
import org.confluence.terraentity.init.entity.TEBossEntities;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.confluence.music.common.init.CMMusics.CachedLocationMusic;

@EventBusSubscriber(modid = ConfluenceMusic.MODID, value = Dist.CLIENT)
public final class MusicHandler {
    public static final ResourceLocation CONFLUENCE = ConfluenceMusic.asResource("confluence"); // 汇流来世类型
    public static final ResourceLocation OTHERWORLDLY = ConfluenceMusic.asResource("otherworldly"); // 原版来世类型
    private static final RandomSource RANDOM = RandomSource.create();
    private static final Function<EntityType<?>, MusicSelection> bossMusicSelector = new Function<>() {
        private Map<EntityType<?>, MusicSelection> cache;

        @Override
        public @Nullable MusicSelection apply(EntityType<?> entityType) {
            if (cache == null) {
                this.cache = new Reference2ObjectOpenHashMap<>();
                cache.put(TEBossEntities.KING_SLIME.get(), MusicSelection.KING_SLIME);
                cache.put(TEBossEntities.EYE_OF_CTHULHU.get(), MusicSelection.EYE_OF_CTHULHU);
                cache.put(TEBossEntities.EATER_OF_WORLDS.get(), MusicSelection.EATER_OF_WORLDS);
                cache.put(TEBossEntities.BRAIN_OF_CTHULHU.get(), MusicSelection.BRAIN_OF_CTHULHU);
                cache.put(TEBossEntities.DEERCLOPS.get(), MusicSelection.DEERCLOPS);
                cache.put(TEBossEntities.QUEEN_BEE.get(), MusicSelection.QUEEN_BEE);
                cache.put(TEBossEntities.SKELETRON.get(), MusicSelection.SKELETRON);
                cache.put(TEBossEntities.WALL_OF_FLESH.get(), MusicSelection.WALL_OF_FLESH);
                cache.put(TEBossEntities.HILL_OF_FLESH.get(), MusicSelection.WALL_OF_FLESH);
//                cache.put(TEBossEntities.THE_TWINS.get(), MusicSelection.THE_TWINS); boss_2
                cache.put(TEBossEntities.SKELETRON_PRIME.get(), MusicSelection.SKELETRON_PRIME);
//                cache.put(TEBossEntities.THE_DESTROYER.get(), MusicSelection.THE_DESTROYER); boss_3
//                cache.put(TEBossEntities.PLANTERA.get(), MusicSelection.PLANTERA); plantera
            }
            return cache.get(entityType);
        }
    };
    private static final int _07$30 = LibDateUtils.getDayTime(7, 30);
    private static CachedLocationMusic nextSong;
    private static int nextSongDelay = 10;
    private static Holder<Biome> lastBiome;
    static int nextBiomeCheck = 50;
    private static float volume = 1.0F;
    private static boolean hasBossMusic = false;
    private static byte structureMusic = StructureFoundPacketS2C.NOT_FOUND;
    private static MusicSelection lastSelection;

    public static void handle(SelectMusicEvent event, LocalPlayer player, Minecraft minecraft) {
        if (nextBiomeCheck-- <= 0) {
            Holder<Biome> biome = player.level().getBiome(player.blockPosition());
            if (biome != lastBiome) {
                lastBiome = biome;
                nextSongDelay = 0;
                nextSong = null;
                lastSelection = null;
            }
            nextBiomeCheck = CMCommonConfigs.checkInterval;
        }
        selectBossMusic(player, minecraft);
        selectStructureMusic(player);
        selectBiomeMusic(player);
        SoundInstance playingMusic = event.getPlayingMusic();
        // A = 没有正在播放的音乐
        // B = 正在播放的音乐已经停止
        // C = 禁用了原版音乐 且 正在播放的音乐是原版音乐
        // D = A 或 B 或 C
        // E = 下一首曲子已经选好 且 选好的是同命名空间但不同音乐
        // F = D 或 E
        // G = 播放下首曲子的冷却时间已结束
        // OK = F 且 H
        if (((playingMusic == null // A
                || // D
                isPlayingMusicStopped(minecraft, playingMusic) // B
                || // D
                (CMClientConfigs.denyVanillaMusic && isVanillaMusic(playingMusic))) // C
                || // F
                (nextSong != null && isSameNamespaceButDifferentSong(nextSong, playingMusic))) // E
                && // OK
                nextSongDelay-- <= 0 // G
        ) {
            // nextSong 与 playingMusic 仍有null的可能性
            if (volume > 0.0F) {
                volume = Mth.clamp(Math.min(volume, playingMusic == null ? 1.0F : playingMusic.getSound().getVolume().sample(player.getRandom())) - CMClientConfigs.lastSongFadeOutStep, 0.0F, 1.0F);
                float v = minecraft.options.getSoundSourceVolume(SoundSource.MUSIC) * volume;
                ChannelAccess.ChannelHandle handle = minecraft.getSoundManager().soundEngine.instanceToChannel.get(playingMusic);
                if (handle != null) handle.execute(channel -> {
                    if (volume <= 0.0F) {
                        channel.stop();
                    } else {
                        channel.setVolume(v);
                    }
                });
            } else {
                minecraft.getMusicManager().stopPlaying();
                event.setMusic(nextSong);
                nextSongDelay = CMClientConfigs.nextSongDelay;
                volume = 1.0F;
                nextSong = null;
                lastSelection = null;
            }
        } else if (volume < 1.0F) {
            volume = Mth.clamp(Math.min(volume, playingMusic == null ? 1.0F : playingMusic.getSound().getVolume().sample(player.getRandom())) + CMClientConfigs.lastSongFadeOutStep, 0.0F, 1.0F);
            float v = minecraft.options.getSoundSourceVolume(SoundSource.MUSIC) * volume;
            ChannelAccess.ChannelHandle handle = minecraft.getSoundManager().soundEngine.instanceToChannel.get(playingMusic);
            if (handle != null) handle.execute(channel -> channel.setVolume(v));
        }
    }

    private static boolean isVanillaMusic(SoundInstance playingMusic) {
        return ResourceLocation.DEFAULT_NAMESPACE.equals(playingMusic.getLocation().getNamespace());
    }

    private static boolean isPlayingMusicStopped(Minecraft minecraft, SoundInstance playingMusic) {
        ChannelAccess.ChannelHandle handle = minecraft.getSoundManager().soundEngine.instanceToChannel.get(playingMusic);
        return handle == null || handle.isStopped();
    }

    private static boolean isSameNamespaceButDifferentSong(CachedLocationMusic next, SoundInstance current) {
        ResourceLocation nextRl = next.getLocation();
        ResourceLocation currentRl = current.getLocation();
        return nextRl.getNamespace().equals(currentRl.getNamespace()) && !nextRl.getPath().equals(currentRl.getPath());
    }

    public static void clear() {
        nextSong = null;
        lastSelection = null;
        nextSongDelay = CMClientConfigs.nextSongDelay;
        lastBiome = null;
        nextBiomeCheck = CMCommonConfigs.checkInterval;
        volume = 1.0F;
        hasBossMusic = false;
    }

    private static void selectBossMusic(LocalPlayer player, Minecraft minecraft) {
        if (minecraft.gui.getBossOverlay().shouldPlayMusic()) {
            AABB area = new AABB(player.blockPosition()).inflate(minecraft.levelRenderer.getLastViewDistance());
            for (Entity boss : player.level().getEntities((Entity) null, area, entity -> entity instanceof Boss)) {
                nextSong = randomMusic(CMClientConfigs.musicType, bossMusicSelector.apply(boss.getType()), player);
                if (nextSong != null) {
                    hasBossMusic = true;
                    nextSongDelay = 0;
                    break;
                }
            }
        } else if (hasBossMusic && (nextSong == null || nextSong.getType() == CMMusics.Type.BOSS)) {
            hasBossMusic = false;
            nextSong = null;
            lastSelection = null;
            nextSongDelay = 0;
        }
    }

    private static void selectStructureMusic(LocalPlayer player) {
        if (nextSong == null) {
            MusicSelection selection = switch (structureMusic) {
                case StructureFoundPacketS2C.DUNGEON_FLOOR_1 -> MusicSelection.DUNGEON_FLOOR_1;
                case StructureFoundPacketS2C.DUNGEON_FLOOR_2 -> MusicSelection.DUNGEON_FLOOR_2;
                default -> null;
            };
            nextSong = randomMusic(CMClientConfigs.musicType, selection, player);
        }
    }

    private static void selectBiomeMusic(LocalPlayer player) {
        if (nextSong != null) return;
        BlockPos pos = player.blockPosition();
        Level level = player.level();
        Holder<Biome> biome = lastBiome == null ? level.getBiome(pos) : lastBiome;
        MusicSelection selection = null;
        ResourceKey<Level> dimension = player.level().dimension();
        if (CMClientConfigs.keepCreativeModeMusic && dimension != OverworldUtils.underworld() && isCreativeMode()) {
            selection = MusicSelection.CREATIVE_MODE;
        } else if (dimension == OverworldUtils.dimension()) {
            int dayTime = LibDateUtils.getDayTime(level);
            boolean isDay = LibDateUtils.isDay(dayTime);
            int y = pos.getY();
            boolean isSurface = y > OverworldUtils.getSurfaceY();

            if (y > OverworldUtils.getSpaceY()) {
                selection = isDay ? MusicSelection.SPACE_DAY : MusicSelection.SPACE_NIGHT;
            } else if (biome.is(ModBiomes.GLOWING_MUSHROOM)) {
                selection = MusicSelection.MUSHROOMS;
            } else if (biome.is(Tags.Biomes.IS_SNOWY) || biome.is(Tags.Biomes.IS_ICY)) {
                selection = isSurface ? MusicSelection.SNOW : MusicSelection.ICE;
            } else if (biome.is(ModTags.Biomes.THE_CORRUPTION)) {
                selection = isSurface ? MusicSelection.THE_CORRUPTION : MusicSelection.UNDERGROUND_CORRUPTION;
            } else if (biome.is(ModTags.Biomes.THE_CRIMSON)) {
                selection = isSurface ? MusicSelection.THE_CRIMSON : MusicSelection.UNDERGROUND_CRIMSON;
            } else if (biome.is(ModTags.Biomes.THE_HALLOW)) {
                if (isSurface) {
                    selection = isDay ? MusicSelection.THE_HALLOW_DAY : MusicSelection.THE_HALLOW_NIGHT;
                } else {
                    selection = MusicSelection.UNDERGROUND_HALLOW;
                }
            } else if (biome.is(Tags.Biomes.IS_DESERT)) {
                selection = isSurface ? MusicSelection.DESERT : MusicSelection.UNDERGROUND_DESERT;
            } else if (biome.is(Tags.Biomes.IS_OCEAN)) {
                selection = isDay ? MusicSelection.OCEAN_DAY : MusicSelection.OCEAN_NIGHT;
            } else if (biome.is(Tags.Biomes.IS_JUNGLE)) {
                if (isSurface) {
                    selection = isDay ? MusicSelection.JUNGLE_DAY : MusicSelection.JUNGLE_NIGHT;
                } else {
                    selection = MusicSelection.UNDERGROUND_JUNGLE;
                }
            } else { // todo 沙尘暴，陨石
                if (ClientGameEventSystem.isEventStarted(BloodMoonGameEvent.KEY)) {
                    selection = MusicSelection.BLOOD_MOON;
                } else if (level.isThundering()) {
                    selection = MusicSelection.STORM;
                } else if (level.isRaining()) {
                    if (LibDateUtils.isWithinDayTime(LibDateUtils._04$30, _07$30, dayTime)) {
                        selection = MusicSelection.MORNING_RAIN;
                    } else {
                        selection = isDay ? MusicSelection.RAIN_DAY : MusicSelection.RAIN_NIGHT;
                    }
                } else if (player.level().getEntities(player, new AABB(player.blockPosition()).inflate(80), entity -> entity instanceof Npc).size() >= 3) {
                    selection = isDay ? MusicSelection.TOWN_DAY : MusicSelection.TOWN_NIGHT;
                } else if (isSurface) {
                    if (isDay) {
                        if (WeatherHandler.WIND_SPEED.length() > 1) {
                            selection = MusicSelection.WINDY_DAY;
                        } else {
                            selection = MusicSelection.OVERWORLD_DAY;
                        }
                    } else {
                        selection = MusicSelection.OVERWORLD_NIGHT;
                    }
                } else {
                    selection = MusicSelection.UNDERGROUND;
                }
            }
        } else if (dimension == OverworldUtils.underworld()) {
            selection = MusicSelection.UNDERWORLD;
        }
        nextSong = randomMusic(CMClientConfigs.musicType, selection, player);
    }

    private static boolean isCreativeMode() {
        MultiPlayerGameMode gameMode = Minecraft.getInstance().gameMode;
        return gameMode == null || gameMode.getPlayerMode().isCreative();
    }

    private static @Nullable CachedLocationMusic randomMusic(ResourceLocation type, @Nullable MusicSelection selection, LocalPlayer player) {
        if (selection == null) return null;
        if (selection == lastSelection) return nextSong;
        lastSelection = selection;
        Map<ResourceLocation, Map<MusicSelection, List<CachedLocationMusic>>> selections = MusicSelectionLoader.getInstance().getRegisteredMusicSelections();
        Map<MusicSelection, List<CachedLocationMusic>> map = selections.get(type);
        if (map == null) {
            if (CONFLUENCE.equals(type) || (map = selections.get(CONFLUENCE)) == null) return null;
        }
        List<CachedLocationMusic> list = map.get(selection);
        if (list == null) {
            if (CONFLUENCE.equals(type) || (list = map.get(selection)) == null) return null;
        }
        if (list.isEmpty()) return null;
        if (list.size() == 1) return list.getFirst();
        RANDOM.setSeed(player.level().getGameTime() / 24000 + System.identityHashCode(selection));
        return Util.getRandom(list, RANDOM);
    }

    @SubscribeEvent
    public static void selectMusic(SelectMusicEvent event) {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;
        if (player == null) {
            clear();
        } else {
            handle(event, player, minecraft);
        }
    }

    public static void registerClientReloadListeners(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(MusicSelectionLoader.getInstance());
    }

    public static void modConfig$Loading(ModConfigEvent.Reloading event) {
        if (event.getConfig().getType() == ModConfig.Type.CLIENT && ConfluenceMusic.MODID.equals(event.getConfig().getModId())) {
            CMClientConfigs.onLoad();
        }
    }

    public static void modConfig$Reloading(ModConfigEvent.Reloading event) {
        if (event.getConfig().getType() == ModConfig.Type.CLIENT && ConfluenceMusic.MODID.equals(event.getConfig().getModId())) {
            CMClientConfigs.onLoad();
        }
    }

    public static void handleStructure(byte data) {
        if (structureMusic != data) {
            structureMusic = data;
            nextSong = null;
            lastSelection = null;
            nextSongDelay = 0;
        }
    }
}
