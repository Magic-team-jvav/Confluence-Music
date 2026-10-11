package org.confluence.music.common;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public final class CMCommonConfigs {
    /// 服务端检查玩家所在地牢分层的间隔，单位：刻。
    public static int checkInterval;

    private static ForgeConfigSpec.IntValue CHECK_INTERVAL;

    public static void onLoad() {
        checkInterval = CHECK_INTERVAL.get();
    }

    public static void register(FMLJavaModLoadingContext context) {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        CHECK_INTERVAL = builder
                .comment("The interval, in ticks, for checking which dungeon floor the player is on.", "检查玩家所在地牢分层的间隔，单位：刻。")
                .defineInRange("checkInterval", 50, 20, 1200);
        context.registerConfig(ModConfig.Type.COMMON, builder.build());
    }
}
