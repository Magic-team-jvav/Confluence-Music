package org.confluence.music.client;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

@OnlyIn(Dist.CLIENT)
public class CMClientConfigs {
    public static ResourceLocation musicType = MusicHandler.CONFLUENCE;
    public static float lastSongFadeOutStep = 0.01F;
    public static int nextSongDelay = 2400;
    public static boolean keepCreativeModeMusic = true;
    public static boolean denyVanillaMusic = true;

    private static ModConfigSpec.ConfigValue<String> MUSIC_TYPE;
    private static ModConfigSpec.IntValue LAST_SONG_FADE_OUT_TIME;
    private static ModConfigSpec.IntValue NEXT_SONG_DELAY;
    private static ModConfigSpec.BooleanValue KEEP_CREATIVE_MODE_MUSIC;
    private static ModConfigSpec.BooleanValue DENY_VANILLA_MUSIC;

    public static void onLoad() {
        musicType = ResourceLocation.parse(MUSIC_TYPE.get());
        lastSongFadeOutStep = LAST_SONG_FADE_OUT_TIME.get() == 0 ? 1.0F : 1.0F / LAST_SONG_FADE_OUT_TIME.get();
        nextSongDelay = NEXT_SONG_DELAY.get();
        keepCreativeModeMusic = KEEP_CREATIVE_MODE_MUSIC.get();
        denyVanillaMusic = DENY_VANILLA_MUSIC.get();
    }

    public static void register(ModContainer container) {
        ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
        MUSIC_TYPE = BUILDER.define("musicType", MusicHandler.CONFLUENCE.toString());
        LAST_SONG_FADE_OUT_TIME = BUILDER.defineInRange("lastSongFadeOutTime", 50, 0, 1200);
        NEXT_SONG_DELAY = BUILDER.defineInRange("nextSongDelay", 2400, 10, 18000);
        KEEP_CREATIVE_MODE_MUSIC = BUILDER.define("keepCreativeModeMusic", true);
        DENY_VANILLA_MUSIC = BUILDER.define("denyVanillaMusic", true);
        container.registerConfig(ModConfig.Type.CLIENT, BUILDER.build());
    }
}
