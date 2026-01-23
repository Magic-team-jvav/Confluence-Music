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

    private static ModConfigSpec.ConfigValue<String> MUSIC_TYPE;
    private static ModConfigSpec.IntValue LAST_SONG_FADE_OUT_TIME;
    private static ModConfigSpec.IntValue NEXT_SONG_DELAY;

    public static void onLoad() {
        musicType = ResourceLocation.parse(MUSIC_TYPE.get());
        lastSongFadeOutStep = LAST_SONG_FADE_OUT_TIME.get() == 0 ? 1.0F : 1.0F / LAST_SONG_FADE_OUT_TIME.get();
        nextSongDelay = NEXT_SONG_DELAY.get();
    }

    public static void register(ModContainer container) {
        ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
        MUSIC_TYPE = BUILDER.define("musicType", MusicHandler.CONFLUENCE.toString());
        LAST_SONG_FADE_OUT_TIME = BUILDER.defineInRange("lastSongFadeOutTime", 100, 0, 100);
        NEXT_SONG_DELAY = BUILDER.defineInRange("nextSongDelay", 2400, 10, 18000);
        container.registerConfig(ModConfig.Type.CLIENT, BUILDER.build());
    }
}
