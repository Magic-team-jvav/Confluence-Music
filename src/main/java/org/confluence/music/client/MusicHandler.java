package org.confluence.music.client;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.ChannelAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;
import net.neoforged.neoforge.client.event.SelectMusicEvent;
import net.neoforged.neoforge.common.Tags;
import org.confluence.lib.util.LibDateUtils;
import org.confluence.mod.common.init.ModBiomes;
import org.confluence.mod.common.init.ModTags;
import org.confluence.mod.util.OverworldUtils;
import org.confluence.music.ConfluenceMusic;
import org.confluence.music.common.CMCommonConfigs;
import org.confluence.music.common.block.MusicBoxBlock;
import org.confluence.music.common.init.CMMusics;
import org.confluence.music.common.item.MusicBoxItem;
import org.confluence.music.common.network.StructureFoundPacketS2C;
import org.confluence.music.mixed.IMusicManager;
import org.confluence.terraentity.api.entity.Boss;
import org.confluence.terraentity.init.entity.TEBossEntities;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.confluence.music.common.init.CMMusics.CachedLocationMusic;

@EventBusSubscriber(modid = ConfluenceMusic.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public final class MusicHandler {
    public static final ResourceLocation CONFLUENCE = ConfluenceMusic.asResource("confluence"); // 汇流来世
    public static final ResourceLocation OTHERWORLDLY = ConfluenceMusic.asResource("otherworldly"); // 泰拉瑞亚来世
    private static final RandomSource RANDOM = RandomSource.create();
    private static final Function<EntityType<?>, MusicSelection> bossMusicSelector = new Function<>() {
        private Map<EntityType<?>, MusicSelection> cache;

        @Override
        public @Nullable MusicSelection apply(EntityType<?> entityType) {
            if (cache == null) {
                this.cache = new IdentityHashMap<>();
                cache.put(TEBossEntities.KING_SLIME.get(), MusicSelection.KING_SLIME);
                cache.put(TEBossEntities.EYE_OF_CTHULHU.get(), MusicSelection.EYE_OF_CTHULHU);
                cache.put(TEBossEntities.EATER_OF_WORLDS.get(), MusicSelection.EATER_OF_WORLDS);
                cache.put(TEBossEntities.BRAIN_OF_CTHULHU.get(), MusicSelection.BRAIN_OF_CTHULHU);
                cache.put(TEBossEntities.QUEEN_BEE.get(), MusicSelection.QUEEN_BEE);
                cache.put(TEBossEntities.SKELETRON.get(), MusicSelection.SKELETRON);
                cache.put(TEBossEntities.WALL_OF_FLESH.get(), MusicSelection.WALL_OF_FLESH);
                cache.put(TEBossEntities.HILL_OF_FLESH.get(), MusicSelection.WALL_OF_FLESH);
            }
            return cache.get(entityType);
        }
    };
    private static final int _07$30 = LibDateUtils.getDayTime(7, 30);
    private static CachedLocationMusic nextSong;
    private static int nextSongDelay = 10;
    private static Holder<Biome> lastBiome;
    static int nextBiomeCheck = 20;
    private static float volume = 1.0F;
    private static boolean hasBossMusic = false;
    private static byte structureMusic = StructureFoundPacketS2C.NOT_FOUND;

    public static void handle(SelectMusicEvent event, LocalPlayer player, Minecraft minecraft) {
        if (!((IMusicManager) minecraft.getMusicManager()).confluence$getMusicBoxOccupied().isNone()) return;
        if (nextBiomeCheck-- <= 0) {
            Holder<Biome> biome = player.level().getBiome(player.blockPosition());
            if (biome != lastBiome) {
                lastBiome = biome;
                nextSongDelay = 0;
                nextSong = null;
            }
            nextBiomeCheck = CMCommonConfigs.checkInterval;
        }
        selectBossMusic(player, minecraft);
        selectStructureMusic();
        selectBiomeMusic(player);
        SoundInstance playingMusic = event.getPlayingMusic();
        if ((playingMusic == null || (nextSong != null && isSameModButDifferentSong(nextSong.getLocation(), playingMusic.getLocation()))) && nextSongDelay-- <= 0) {
            if (volume > 0.0F) {
                volume = Math.min(volume, playingMusic == null ? 1.0F : playingMusic.getSound().getVolume().sample(player.getRandom())) - 0.01F;
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
            }
        } else if (volume < 1.0F) {
            volume = Math.min(volume, playingMusic == null ? 1.0F : playingMusic.getSound().getVolume().sample(player.getRandom())) + 0.01F;
            float v = minecraft.options.getSoundSourceVolume(SoundSource.MUSIC) * volume;
            ChannelAccess.ChannelHandle handle = minecraft.getSoundManager().soundEngine.instanceToChannel.get(playingMusic);
            if (handle != null) handle.execute(channel -> channel.setVolume(v));
        }
    }

    private static boolean isSameModButDifferentSong(ResourceLocation next, ResourceLocation current) {
        return next.getNamespace().equals(current.getNamespace()) && !next.getPath().equals(current.getPath());
    }

    public static void clear() {
        nextSong = null;
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
                nextSong = randomMusic(CMClientConfigs.musicType, bossMusicSelector.apply(boss.getType()));
                if (nextSong != null) {
                    hasBossMusic = true;
                    nextSongDelay = 0;
                    break;
                }
            }
        } else if (hasBossMusic && (nextSong == null || nextSong.getType() == CMMusics.Type.BOSS)) {
            hasBossMusic = false;
            nextSong = null;
            nextSongDelay = 0;
        }
    }

    private static void selectStructureMusic() {
        if (nextSong == null) {
            nextSong = randomMusic(CMClientConfigs.musicType, switch (structureMusic) {
                case StructureFoundPacketS2C.DUNGEON_FLOOR_1 -> MusicSelection.DUNGEON_FLOOR_1;
                case StructureFoundPacketS2C.DUNGEON_FLOOR_2 -> MusicSelection.DUNGEON_FLOOR_2;
                default -> null;
            });
        }
    }

    private static void selectBiomeMusic(LocalPlayer player) {
        if (nextSong != null) return;
        BlockPos pos = player.blockPosition();
        Level level = player.level();
        Holder<Biome> biome = lastBiome == null ? level.getBiome(pos) : lastBiome;
        MusicSelection selection = null;
        ResourceKey<Level> dimension = player.level().dimension();
        if (dimension == OverworldUtils.dimension()) {
            int dayTime = LibDateUtils.getDayTime(level);
            boolean isDay = LibDateUtils.isDay(dayTime);
            int y = pos.getY();
            boolean isSurface = y >= 40;

            if (y > 260) {
                selection = isDay ? MusicSelection.SPACE_DAY : MusicSelection.SPACE_NIGHT;
            } else if (level.isRaining()) {
                if (LibDateUtils.isWithinDayTime(LibDateUtils._04$30, _07$30, dayTime)) {
                    selection = MusicSelection.MORNING_RAIN;
                } else {
                    selection = isDay ? MusicSelection.RAIN_DAY : MusicSelection.RAIN_NIGHT;
                }
            } else if (level.isThundering()) {
                selection = MusicSelection.STORM;
            } else if (biome.is(ModBiomes.GLOWING_MUSHROOM)) {
                selection = MusicSelection.MUSHROOMS;
            } else if (biome.is(Tags.Biomes.IS_ICY)) {
                selection = isSurface ? MusicSelection.ICE : MusicSelection.UNDERGROUND_ICE;
            } else if (biome.is(Tags.Biomes.IS_SNOWY)) {
                selection = MusicSelection.SNOW;
            } else if (biome.is(ModTags.Biomes.THE_CORRUPTION)) {
                selection = isSurface ? MusicSelection.CORRUPTION : MusicSelection.UNDERGROUND_CORRUPTION;
            } else if (biome.is(ModTags.Biomes.THE_CRIMSON)) {
                selection = isSurface ? MusicSelection.CRIMSON : MusicSelection.UNDERGROUND_CRIMSON;
            } else if (biome.is(ModTags.Biomes.THE_HALLOW)) {
                selection = isSurface ? (isDay ? MusicSelection.THE_HALLOW_DAY : MusicSelection.THE_HALLOW_NIGHT) : MusicSelection.UNDERGROUND_HALLOW;
            } else if (biome.is(Tags.Biomes.IS_DESERT)) {
                selection = isSurface ? MusicSelection.DESERT : MusicSelection.UNDERGROUND_DESERT;
            } else if (biome.is(Tags.Biomes.IS_OCEAN)) {
                selection = isDay ? MusicSelection.OCEAN_DAY : MusicSelection.OCEAN_NIGHT;
            } else if (biome.is(Tags.Biomes.IS_JUNGLE)) {
                selection = isSurface ? (isDay ? MusicSelection.JUNGLE_DAY : MusicSelection.JUNGLE_NIGHT) : MusicSelection.UNDERGROUND_JUNGLE;
            } else {
                selection = isSurface ? (isDay ? MusicSelection.DAY : MusicSelection.NIGHT) : MusicSelection.UNDERGROUND;
            }
        } else if (dimension == Level.NETHER) {
            selection = MusicSelection.UNDERWORLD;
        }
        nextSong = randomMusic(CMClientConfigs.musicType, selection);
    }

    private static @Nullable CachedLocationMusic randomMusic(ResourceLocation type, @Nullable MusicSelection selection) {
        if (selection == null) return null;
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
        return Util.getRandom(list, RANDOM);
    }

    @SubscribeEvent
    public static void clientTick$Post(ClientTickEvent.Post event) {
        /**
         * @see MusicBoxItem#curioTick(SlotContext, ItemStack) 2nd
         * @see MusicBoxBlock.BEntity#clientTick(Level, BlockPos, BlockState, MusicBoxBlock.BEntity) 3rd
         */
        IMusicManager.reset(Minecraft.getInstance().getMusicManager()); // 1st
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
            nextSongDelay = 0;
        }
    }
}
