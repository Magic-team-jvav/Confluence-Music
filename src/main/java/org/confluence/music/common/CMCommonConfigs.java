package org.confluence.music.common;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public final class CMCommonConfigs {
    /// 服务端检查玩家所在地牢分层的间隔，单位：刻。
    public static int checkInterval;

    private static ModConfigSpec.IntValue CHECK_INTERVAL;

    public static void onLoad() {
        checkInterval = CHECK_INTERVAL.get();
    }

    public static void register(ModContainer container) {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        CHECK_INTERVAL = builder
                .comment("The interval, in ticks, for checking which dungeon floor the player is on.", "检查玩家所在地牢分层的间隔，单位：刻。")
                .defineInRange("checkInterval", 50, 20, 1200);
        container.registerConfig(ModConfig.Type.COMMON, builder.build());
    }
}
