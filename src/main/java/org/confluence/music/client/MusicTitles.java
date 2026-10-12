package org.confluence.music.client;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/// 曲名对照表：音效名（音效 location 的 path，比如 `confluence_music:alt_overworld_night` 里的
/// `alt_overworld_night`）→ 展示用曲名。
///
/// 内容逐条搬自整理表 `汇流音乐曲名.txt`（“说明 音效名” + “曲名 - 作者”两行一组）。
/// 表里没有的音效（基本都是缺音频文件、放不出来的那些）就退回显示音效名本身。
public final class MusicTitles {
    private static final Map<String, String> TITLES = Util.make(new Object2ObjectOpenHashMap<>(), map -> {
        map.put("alt_overworld_night", "Awakening Moonlight (月照无眠) - SquidSine");
        map.put("alt_overworld_night_2", "Hazy Hymn (宵寤芒芒) - SquidSine");
        map.put("alt_underground", "A Rock March (岩层远征) - SquidSine");
        map.put("blizzard", "Frozen Land (永冻之域) - SquidSine");
        map.put("boss_1", "Old disasters in new world (异界旧屿) - SquidSine");
        map.put("brain_of_cthulhu_phase_1", "The Brain Behind the Curtain (幕后首脑) - SquidSine, Als2336");
        map.put("brain_of_cthulhu_phase_2", "Destored Center (失真中枢) - SquidSine, Als2336");
        map.put("creative_mode", "Creare Facereve (开天辟地) - SquidSine");
        map.put("desert", "Smoggy Sand (大漠孤烟) - SquidSine");
        map.put("dungeon_floor_1", "Corrosive Souls, Creaking Skulls (魂怨颅鸣) - SquidSine");
        map.put("dungeon_floor_2", "Comprehensive Confluence (八方之汇) - SquidSine");
        map.put("eater_of_worlds", "Swallowing Down The World (吞世腐虬) - SquidSine");
        map.put("eerie", "Redmoon, Rumbling Star (月红星落) - SquidSine");
        map.put("jungle_day", "Sound Surround Silvae (林海音涧) - SquidSine");
        map.put("jungle_night", "Echos Through Deep Forest (密林诡言) - SquidSine");
        map.put("morning_rain", "Moisturize Mistily (润物无声) - SquidSine");
        map.put("mushrooms", "Glowing Drugs (荧光致幻剂) - SquidSine");
        map.put("ocean_day", "Sail's Greeting (帆叩汪洋) - SquidSine");
        map.put("overworld_day", "Return Journey (再临的旅途) - SquidSine");
        map.put("overworld_night", "Silent Night, Silent Knight (寂夜孤士) - SquidSine");
        map.put("sandstorm", "Blow a Billion (扬沙万里) - SquidSine");
        map.put("snow", "Tundra Tour (雪上空行) - SquidSine");
        map.put("space_day", "Reaching Starryboard (手可摘星) - SquidSine");
        map.put("space_night", "Constellating Cradle (天穹摇篮) - SquidSine");
        map.put("the_corruption", "Celestial Plague (天行疫疠) - SquidSine");
        map.put("the_crimson", "Bloodstained Landscape (血染沃土) - SquidSine");
        map.put("the_end", "Ender Eremus (终眇归墟) - SquidSine");
        map.put("the_hallow_day", "Glint Shard (煜爚琉璃) - SquidSine");
        map.put("town_night", "Warm Light at Night (晚间暖光) - SquidSine");
        map.put("underground", "Discover in Depth (探渊寻源) - SquidSine");
        map.put("underground_corruption", "Eroded Chasms (虫蚀腐渊) - SquidSine");
        map.put("underground_crimson", "Inner of Blood (血肉深处) - SquidSine");
        map.put("underworld", "Demoniac Core Of Hell (魔界狱髓) - SquidSine");
        map.put("wall_of_flesh", "Wall Of Flesh(肉山) - SquidSine");
        map.put("windy_day", "Time to fly kites (风筝日) - SquidSine");
    });

    private MusicTitles() {}

    /// {@return 这一首曲目的展示名，表里没有时退回音效名（path）}
    public static @Nullable String of(@Nullable ResourceLocation location) {
        if (location == null) return null;
        String path = location.getPath();
        return TITLES.getOrDefault(path, path);
    }
}
