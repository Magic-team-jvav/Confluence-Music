package org.confluence.music.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@OnlyIn(Dist.CLIENT)
public class CMClientConfigs {
    /// 进入世界后先安静一会儿再放第一首。
    public static final int INITIAL_WAIT = 10;
    /// 使用的音乐类型，对应数据包里的 {@code music_selection/<类型>.json}。
    public static ResourceLocation musicType = MusicHandler.CONFLUENCE;
    /// 淡出/淡入每一步的音量，由“淡出时间”换算而来。
    public static float lastSongFadeOutStep = 0.01F;
    /// 一首曲子结束之后，到下一首开始之间的间隔（刻）。
    public static int nextSongDelay = INITIAL_WAIT;
    public static boolean keepCreativeModeMusic = true;
    public static boolean denyVanillaMusic = true;

    private static ForgeConfigSpec.ConfigValue<String> MUSIC_TYPE;
    private static ForgeConfigSpec.IntValue LAST_SONG_FADE_OUT_TIME;
    private static ForgeConfigSpec.IntValue NEXT_SONG_DELAY;
    private static ForgeConfigSpec.BooleanValue KEEP_CREATIVE_MODE_MUSIC;
    private static ForgeConfigSpec.BooleanValue DENY_VANILLA_MUSIC;

    public static void onLoad() {
        musicType = ResourceLocation.parse(MUSIC_TYPE.get());
        lastSongFadeOutStep = LAST_SONG_FADE_OUT_TIME.get() == 0 ? 1.0F : 1.0F / LAST_SONG_FADE_OUT_TIME.get();
        nextSongDelay = NEXT_SONG_DELAY.get();
        keepCreativeModeMusic = KEEP_CREATIVE_MODE_MUSIC.get();
        denyVanillaMusic = DENY_VANILLA_MUSIC.get();
    }

    public static void register(FMLJavaModLoadingContext context) {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        MUSIC_TYPE = builder
                .comment("Which playlist to use, matching music_selection/<type>.json", "使用哪套歌单，对应 music_selection/<类型>.json")
                .define("musicType", MusicHandler.CONFLUENCE.toString());
        LAST_SONG_FADE_OUT_TIME = builder
                .comment("Fade-out time of the previous song in ticks; 20 means 1 second, 0 means stop immediately.",
                        "上一首的淡出时间（刻）：20 即 1 秒，0 即立刻停止。")
                .defineInRange("lastSongFadeOutTime", 100, 0, 1200);
        NEXT_SONG_DELAY = builder
                .comment("Ticks to wait after a song ends before the next one starts.", "一首放完之后等待多少刻才开始下一首。")
                .defineInRange("nextSongDelay", INITIAL_WAIT, 10, 18000);
        KEEP_CREATIVE_MODE_MUSIC = builder
                .comment("In creative mode only creative music plays instead of ambient music.", "创造模式下只播放创造模式音乐，而不播放环境音乐。")
                .define("keepCreativeModeMusic", true);
        DENY_VANILLA_MUSIC = builder
                .comment("Never play vanilla music.", "不再播放原版音乐。")
                .define("denyVanillaMusic", true);
        context.registerConfig(ModConfig.Type.CLIENT, builder.build());
    }
}
