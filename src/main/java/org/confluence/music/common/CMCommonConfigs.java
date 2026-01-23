package org.confluence.music.common;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public final class CMCommonConfigs {
    public static int checkInterval;

    private static ModConfigSpec.IntValue CHECK_INTERVAL;

    public static void onLoad() {
        checkInterval = CHECK_INTERVAL.get();
    }

    public static void register(ModContainer container) {
        ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
        CHECK_INTERVAL = BUILDER.defineInRange("checkInterval", 20, 20, 200);
        container.registerConfig(ModConfig.Type.COMMON, BUILDER.build());
    }
}
