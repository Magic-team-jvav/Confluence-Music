package org.confluence.music.client;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class ClientConfigs {
    public static boolean playerOurMusic = false;

    private static ModConfigSpec.BooleanValue PLAY_OUR_MUSIC;

    public static void onLoad() {
        playerOurMusic = PLAY_OUR_MUSIC.get();
    }

    public static void register(ModContainer container) {
        ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
        PLAY_OUR_MUSIC = BUILDER.define("playerOurMusic", false);
        container.registerConfig(ModConfig.Type.CLIENT, BUILDER.build());
    }
}
