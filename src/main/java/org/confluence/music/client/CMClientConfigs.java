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

    private static ModConfigSpec.BooleanValue PLAY_OUR_MUSIC;
    private static ModConfigSpec.ConfigValue<String> MUSIC_TYPE;

    public static void onLoad() { // todo 调用
        playerOurMusic = PLAY_OUR_MUSIC.get();
        musicType = ResourceLocation.parse(MUSIC_TYPE.get());
    }

    public static void register(ModContainer container) {
        ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
        PLAY_OUR_MUSIC = BUILDER.define("playerOurMusic", false);
        MUSIC_TYPE = BUILDER.define("musicType", MusicHandler.CONFLUENCE.toString());
        container.registerConfig(ModConfig.Type.CLIENT, BUILDER.build());
    }
}
