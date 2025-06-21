package org.confluence.music.client;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

@OnlyIn(Dist.CLIENT)
public class CMClientConfigs {
    public static boolean playerOurMusic = false;
    public static ResourceLocation musicType = MusicHandler.CONFLUENCE;
    public static int nextSongDelay = 2400;

    private static ModConfigSpec.BooleanValue PLAY_OUR_MUSIC;
    private static ModConfigSpec.ConfigValue<String> MUSIC_TYPE;
    private static ModConfigSpec.IntValue NEXT_SONG_DELAY;

    public static void onLoad() {
        playerOurMusic = PLAY_OUR_MUSIC.get();
        musicType = ResourceLocation.parse(MUSIC_TYPE.get());
        nextSongDelay = NEXT_SONG_DELAY.get();
    }

    public static void register(ModContainer container) {
        ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
        PLAY_OUR_MUSIC = BUILDER.define("playerOurMusic", false);
        MUSIC_TYPE = BUILDER.define("musicType", MusicHandler.CONFLUENCE.toString());
        NEXT_SONG_DELAY = BUILDER.defineInRange("nextSongDelay", 2400, 10, 18000);
        container.registerConfig(ModConfig.Type.CLIENT, BUILDER.build());
    }
}
