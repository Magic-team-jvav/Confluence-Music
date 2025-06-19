package org.confluence.music.client;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.ChannelAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;
import net.neoforged.neoforge.client.event.SelectMusicEvent;
import net.neoforged.neoforge.common.Tags;
import org.confluence.mod.common.init.ModBiomes;
import org.confluence.mod.common.init.ModTags;
import org.confluence.music.ConfluenceMusic;
import org.confluence.music.common.block.MusicBoxBlock;
import org.confluence.music.common.item.MusicBoxItem;
import org.confluence.music.mixed.IMusicManager;
import org.confluence.terraentity.entity.ai.Boss;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.Map;

import static org.confluence.music.common.init.CMMusics.CachedLocationMusic;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = ConfluenceMusic.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public final class MusicHandler {
    public static final ResourceLocation CONFLUENCE = ConfluenceMusic.asResource("confluence"); // 汇流来世
    public static final ResourceLocation OTHERWORLDLY = ConfluenceMusic.asResource("otherworldly"); // 泰拉瑞亚来世
    private static final RandomSource RANDOM = RandomSource.create();
    private static CachedLocationMusic nextSong;
    private static int nextSongDelay = 2400;
    private static Holder<Biome> lastBiome;
    private static int nextBiomeCheck = 100;
    private static float volume = 1.0F;
    private static boolean hasBossMusic = false;

    public static void handle(SelectMusicEvent event, LocalPlayer player, Minecraft minecraft) {
        if (!CMClientConfigs.playerOurMusic || !((IMusicManager) minecraft.getMusicManager()).confluence$getMusicBoxOccupied().isNone()) return;
        if (nextBiomeCheck-- <= 0) {
            Holder<Biome> biome = player.level().getBiome(player.blockPosition());
            if (biome != lastBiome) {
                lastBiome = biome;
                nextSongDelay = 0;
                nextSong = null;
            }
            nextBiomeCheck = 100;
        }
        selectBossMusic(player, minecraft);
        if (nextSong == null) {
            selectMusic(player);
        }
        SoundInstance playingMusic = event.getPlayingMusic();
        if ((playingMusic == null || (nextSong != null && isSameModButDifferentSong(nextSong.getLocation(), playingMusic.getLocation()))) && nextSongDelay-- <= 0) {
            if (volume > 0.0F) {
                volume -= 0.01F;
                float v = minecraft.options.getSoundSourceVolume(SoundSource.MUSIC) * volume;
                Map<SoundInstance, ChannelAccess.ChannelHandle> instanceToChannel = minecraft.getSoundManager().soundEngine.instanceToChannel;
                for (Map.Entry<SoundInstance, ChannelAccess.ChannelHandle> entry : instanceToChannel.entrySet()) {
                    if (entry.getKey().getSource() != SoundSource.MUSIC) continue;
                    entry.getValue().execute(channel -> {
                        if (volume <= 0.0F) {
                            channel.stop();
                        } else {
                            channel.setVolume(v);
                        }
                    });
                }
            } else {
                minecraft.getMusicManager().stopPlaying();
                event.setMusic(nextSong);
                nextSongDelay = 2400;
                volume = 1.0F;
                nextSong = null;
            }
        }
    }

    private static boolean isSameModButDifferentSong(ResourceLocation next, ResourceLocation current) {
        return next.getNamespace().equals(current.getNamespace()) && !next.getPath().equals(current.getPath());
    }

    public static void clear() {
        nextSong = null;
        nextSongDelay = 1200;
        lastBiome = null;
        nextBiomeCheck = 100;
        volume = 1.0F;
        hasBossMusic = false;
    }

    private static void selectBossMusic(LocalPlayer player, Minecraft minecraft) {
        if (minecraft.gui.getBossOverlay().shouldPlayMusic()) {
            AABB area = new AABB(player.blockPosition()).inflate(minecraft.levelRenderer.getLastViewDistance());
            for (Entity boss : player.level().getEntities((Entity) null, area, entity -> entity instanceof Boss)) { // todo
//                if (boss.getType() == TEBossEntities.KING_SLIME.get()) {
//                    nextSong = KING_SLIME;
//                } else if (boss.getType() == TEBossEntities.EYE_OF_CTHULHU.get()) {
//                    nextSong = EYE_OF_CTHULHU;
//                } else if (boss.getType() == TEBossEntities.EATER_OF_WORLDS.get()) {
//                    nextSong = EATER_OF_WORLDS;
//                } else if (boss.getType() == TEBossEntities.BRAIN_OF_CTHULHU.get()) {
//                    nextSong = BRAIN_OF_CTHULHU;
//                } else if (boss.getType() == TEBossEntities.QUEEN_BEE.get()) {
//                    nextSong = QUEEN_BEE;
//                }
                if (nextSong != null) {
                    hasBossMusic = true;
                    nextSongDelay = 0;
                    break;
                }
            }
        } else if (hasBossMusic && (nextSong == null || nextSong.getLocation().getPath().endsWith("_combat"))) {
            hasBossMusic = false;
            nextSong = null;
            nextSongDelay = 0;
        }
    }

    private static void selectMusic(LocalPlayer player) {
        if (player.level().dimension() != Level.OVERWORLD) return;
        BlockPos pos = player.blockPosition();
        Level level = player.level();
        Holder<Biome> biome = lastBiome == null ? level.getBiome(pos) : lastBiome;
        long dayTime = level.getDayTime() % 24000;
        boolean isDay = dayTime < 12000;
        int y = pos.getY();
        boolean isSurface = y >= 40;
        MusicSelection selection;

        if (y > 260) {
            selection = isDay ? MusicSelection.SPACE_DAY : MusicSelection.SPACE_NIGHT;
        } else if (level.isRaining()) {
            if (dayTime >= 22500 || dayTime <= 1500) {
                selection = MusicSelection.MORNING_RAIN;
            } else {
                selection = MusicSelection.RAIN;
            }
        } else if (level.isThundering()) {
            selection = MusicSelection.STORM;
        } else if (biome.is(ModBiomes.GLOWING_MUSHROOM)) {
            selection = MusicSelection.MUSHROOMS;
        } else if (biome.is(Tags.Biomes.IS_ICY)) {
            selection = MusicSelection.ICE;
        } else if (biome.is(Tags.Biomes.IS_SNOWY)) {
            selection = MusicSelection.SNOW;
        } else if (biome.is(ModTags.Biomes.THE_CORRUPTION)) {
            selection = isSurface ? MusicSelection.CORRUPTION : MusicSelection.UNDERGROUND_CORRUPTION;
        } else if (biome.is(ModTags.Biomes.THE_CRIMSON)) {
            selection = isSurface ? MusicSelection.CRIMSON : MusicSelection.UNDERGROUND_CRIMSON;
        } else if (biome.is(ModTags.Biomes.THE_HALLOW)) {
            selection = isSurface ? MusicSelection.THE_HALLOW : MusicSelection.UNDERGROUND_HALLOW;
        } else if (biome.is(Tags.Biomes.IS_DESERT)) {
            selection = MusicSelection.DESERT;
        } else if (biome.is(Tags.Biomes.IS_OCEAN)) {
            selection = isDay ? MusicSelection.OCEAN_DAY : MusicSelection.OCEAN_NIGHT;
        } else if (biome.is(Tags.Biomes.IS_JUNGLE)) {
            selection = isSurface ? MusicSelection.JUNGLE : MusicSelection.UNDERGROUND_JUNGLE;
        } else {
            selection = isSurface ? (isDay ? MusicSelection.DAY : MusicSelection.NIGHT) : MusicSelection.UNDERGROUND;
        }
        nextSong = randomMusic(CMClientConfigs.musicType, selection);
    }

    private static @Nullable CachedLocationMusic randomMusic(ResourceLocation type, @Nullable MusicSelection selection) {
        if (selection == null) return null;
        Map<MusicSelection, List<CachedLocationMusic>> map = MusicSelectionLoader.getInstance().getRegisteredMusicSelections().get(type);
        if (map == null) return null;
        List<CachedLocationMusic> list = map.get(selection);
        if (list == null) return null;
        if (list.isEmpty()) return null;
        if (list.size() == 1) return list.getFirst();
        return Util.getRandom(list, RANDOM);
    }

    @SubscribeEvent
    public static void clientTick$Post(ClientTickEvent.Post event) {
        /**
         * @see MusicBoxItem#curioTick(SlotContext, ItemStack) 2nd
         * @see MusicBoxBlock.Entity#clientTick(Level, BlockPos, BlockState, MusicBoxBlock.Entity) 3rd
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

    @SubscribeEvent
    public static void registerClientReloadListeners(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(MusicSelectionLoader.getInstance());
    }
}
