package org.confluence.music.common.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.confluence.music.ConfluenceMusic;
import org.confluence.music.common.init.CMBlocks;
import org.confluence.music.common.init.CMItems;

public class CMLanguageProvider extends LanguageProvider {
    private final boolean isEn;

    public CMLanguageProvider(PackOutput output, boolean isEn) {
        super(output, ConfluenceMusic.MODID, isEn ? "en_us" : "zh_cn");
        this.isEn = isEn;
    }

    @Override
    protected void addTranslations() {
        add("jukebox_song.confluence_music.alpha", "C418 - alpha", "C418 - alpha");
        add(CMItems.ALPHA.get(), "Alpha", "Alpha");
        add(CMBlocks.MUSIC_BOX.get(), "Music Box", "八音盒");
        add(CMBlocks.MUSIC_BOX_OTHERWORLD.get(), "Music Box (Otherworld)", "八音盒（来世）");
        add(CMBlocks.MUSIC_BOX_OVERWORLD_DAY.get(), "Music Box (Overworld Day)", "八音盒（地表世界）");
        add(CMBlocks.MUSIC_BOX_ALT_OVERWORLD_DAY.get(), "Music Box (Alt Overworld Day)", "八音盒（地表世界备选曲）");
        add(CMBlocks.MUSIC_BOX_NIGHT.get(), "Music Box (Night)", "八音盒（夜间）");
        add(CMBlocks.MUSIC_BOX_RAIN.get(), "Music Box (Rain)", "八音盒（雨）");
        add(CMBlocks.MUSIC_BOX_SNOW.get(), "Music Box (Snow)", "八音盒（雪原）");
        add(CMBlocks.MUSIC_BOX_ICE.get(), "Music Box (Ice)", "八音盒（冰雪）");
        add(CMBlocks.MUSIC_BOX_DESERT.get(), "Music Box (Desert)", "八音盒（沙漠）");
        add(CMBlocks.MUSIC_BOX_UNDERGROUND_DESERT.get(), "Music Box (Underground Desert)", "八音盒（地下沙漠）");
        add(CMBlocks.MUSIC_BOX_OCEAN_DAY.get(), "Music Box (Ocean Day)", "八音盒（海洋白天）");
        add(CMBlocks.MUSIC_BOX_OCEAN_NIGHT.get(), "Music Box (Ocean Night)", "八音盒（海洋夜晚）");
        add(CMBlocks.MUSIC_BOX_SPACE_DAY.get(), "Music Box (Space Day)", "八音盒（太空白天）");
        add(CMBlocks.MUSIC_BOX_SPACE_NIGHT.get(), "Music Box (Space Night)", "八音盒（太空夜晚）");
        add(CMBlocks.MUSIC_BOX_UNDERGROUND.get(), "Music Box (Underground)", "八音盒（地下）");
        add(CMBlocks.MUSIC_BOX_ALT_UNDERGROUND.get(), "Music Box (Alt Underground)", "八音盒（地下备选曲）");
        add(CMBlocks.MUSIC_BOX_MUSHROOMS.get(), "Music Box (Mushrooms)", "八音盒（蘑菇）");
        add(CMBlocks.MUSIC_BOX_JUNGLE.get(), "Music Box (Jungle)", "八音盒（丛林）");
        add(CMBlocks.MUSIC_BOX_UNDERGROUND_JUNGLE.get(), "Music Box (Underground Jungle)", "八音盒（地下丛林）");
        add(CMBlocks.MUSIC_BOX_CORRUPTION.get(), "Music Box (Corruption)", "八音盒（腐化之地）");
        add(CMBlocks.MUSIC_BOX_UNDERGROUND_CORRUPTION.get(), "Music Box (Underground Corruption)", "八音盒（地下腐化之地）");
        add(CMBlocks.MUSIC_BOX_CRIMSON.get(), "Music Box (Crimson)", "八音盒（猩红之地）");
        add(CMBlocks.MUSIC_BOX_UNDERGROUND_CRIMSON.get(), "Music Box (Underground Crimson)", "八音盒（地下猩红之地）");
        add(CMBlocks.MUSIC_BOX_THE_HALLOW.get(), "Music Box (The Hallow)", "八音盒（神圣之地）");
        add(CMBlocks.MUSIC_BOX_UNDERGROUND_HALLOW.get(), "Music Box (Underground Hallow)", "八音盒（地下神圣之地）");
        add(CMBlocks.MUSIC_BOX_HELL.get(), "Music Box (Hell)", "八音盒（地狱）");
        add(CMBlocks.MUSIC_BOX_DUNGEON.get(), "Music Box (Dungeon)", "八音盒（地牢）");
        add(CMBlocks.MUSIC_BOX_TEMPLE.get(), "Music Box (Temple)", "八音盒（神庙）");
        add(CMBlocks.MUSIC_BOX_AETHER.get(), "Music Box (Aether)", "八音盒（以太）");
        add(CMBlocks.MUSIC_BOX_JOURNEYS_BEGINNING.get(), "Music Box (Journey's Beginning)", "八音盒（旅程开始）");
        add(CMBlocks.MUSIC_BOX_JOURNEYS_END.get(), "Music Box (Journey's End)", "八音盒（旅程结束）");
        add(CMBlocks.MUSIC_BOX_TITLE.get(), "Music Box (Title)", "八音盒（标题）");
        add(CMBlocks.MUSIC_BOX_ALT_TITLE.get(), "Music Box (Alt Title)", "八音盒（标题备选曲）");
        add(CMBlocks.MUSIC_BOX_DAY_REMIX.get(), "Music Box (Remix)", "八音盒（白天混音）");
        add(CMBlocks.MUSIC_BOX_BOSS_1.get(), "Music Box (Boss 1)", "八音盒（Boss 1）");
        add(CMBlocks.MUSIC_BOX_BOSS_2.get(), "Music Box (Boss 2)", "八音盒（Boss 2）");
        add(CMBlocks.MUSIC_BOX_BOSS_3.get(), "Music Box (Boss 3)", "八音盒（Boss 3）");
        add(CMBlocks.MUSIC_BOX_BOSS_4.get(), "Music Box (Boss 4)", "八音盒（Boss 4）");
        add(CMBlocks.MUSIC_BOX_BOSS_5.get(), "Music Box (Boss 5)", "八音盒（Boss 5）");
        add(CMBlocks.MUSIC_BOX_DEERCLOPS.get(), "Music Box (Deerclops)", "八音盒（独眼巨鹿）");
        add(CMBlocks.MUSIC_BOX_QUEEN_SLIME.get(), "Music Box (Queen Slime)", "八音盒（史莱姆皇后）");
        add(CMBlocks.MUSIC_BOX_PLANTERA.get(), "Music Box (Plantera)", "八音盒（世纪之花）");
        add(CMBlocks.MUSIC_BOX_EMPRESS_OF_LIGHT.get(), "Music Box (Empress Of Light)", "八音盒（光之女皇）");
        add(CMBlocks.MUSIC_BOX_DUKE_FISHRON.get(), "Music Box (Duke FIshron)", "八音盒（猪龙鱼公爵）");
        add(CMBlocks.MUSIC_BOX_LUNAR_BOSS.get(), "Music Box (Lunar Boss)", "八音盒（月亮Boss）");
        add(CMBlocks.MUSIC_BOX_EERIE.get(), "Music Box (Eerie)", "八音盒（恐惧）");
        add(CMBlocks.MUSIC_BOX_ECLIPSE.get(), "Music Box (Eclipse)", "八音盒（日食）");
        add(CMBlocks.MUSIC_BOX_GOBLIN_INVASION.get(), "Music Box (Goblin Invasion)", "八音盒（哥布林入侵）");
        add(CMBlocks.MUSIC_BOX_PIRATE_INVASION.get(), "Music Box (Pirate Invasion)", "八音盒（海盗入侵）");
        add(CMBlocks.MUSIC_BOX_MARTIAN_MADNESS.get(), "Music Box (Martian Madness)", "八音盒（火星暴乱）");
        add(CMBlocks.MUSIC_BOX_PUMPKIN_MOON.get(), "Music Box (Pumpkin Moon)", "八音盒（南瓜月）");
        add(CMBlocks.MUSIC_BOX_FROST_MOON.get(), "Music Box (Frost Moon)", "八音盒（霜月）");
        add(CMBlocks.MUSIC_BOX_THE_TOWERS.get(), "Music Box (The Towers)", "八音盒（天界柱）");
        add(CMBlocks.MUSIC_BOX_SANDSTORM.get(), "Music Box (Sandstorm)", "八音盒（沙尘暴）");
        add(CMBlocks.MUSIC_BOX_OLD_ONES_ARMY.get(), "Music Box (Old One's Army)", "八音盒（撒旦军队）");
        add(CMBlocks.MUSIC_BOX_SLIME_RAIN.get(), "Music Box (Slime Rain)", "八音盒（史莱姆雨）");
        add(CMBlocks.MUSIC_BOX_TOWN_DAY.get(), "Music Box (Town Day)", "八音盒（城镇白天）");
        add(CMBlocks.MUSIC_BOX_TOWN_NIGHT.get(), "Music Box (Town Night)", "八音盒（城镇夜晚）");
        add(CMBlocks.MUSIC_BOX_WINDY_DAY.get(), "Music Box (Windy Day)", "八音盒（大风天）");
        add(CMBlocks.MUSIC_BOX_STORM.get(), "Music Box (Storm)", "八音盒（暴风雨）");
        add(CMBlocks.MUSIC_BOX_GRAVEYARD.get(), "Music Box (Graveyard)", "八音盒（墓地）");
        add(CMBlocks.MUSIC_BOX_MORNING_RAIN.get(), "Music Box (Morning Rain)", "八音盒（晨雨）");
        add(CMBlocks.OTHERWORLDLY_MUSIC_BOX_RAIN.get(), "Otherworldly Music Box (Rain)", "异界八音盒（雨）");
        add(CMBlocks.OTHERWORLDLY_MUSIC_BOX_OVERWORLD_DAY.get(), "Otherworldly Music Box (Overworld Day)", "异界八音盒（地表世界）");
        add(CMBlocks.OTHERWORLDLY_MUSIC_BOX_NIGHT.get(), "Otherworldly Music Box (Night)", "异界八音盒（夜晚）");
        add(CMBlocks.OTHERWORLDLY_MUSIC_BOX_UNDERGROUND.get(), "Otherworldly Music Box (Underground)", "异界八音盒（地下）");
        add(CMBlocks.OTHERWORLDLY_MUSIC_BOX_DESERT.get(), "Otherworldly Music Box (Desert)", "异界八音盒（沙漠）");
        add(CMBlocks.OTHERWORLDLY_MUSIC_BOX_OCEAN.get(), "Otherworldly Music Box (Ocean)", "异界八音盒（海洋）");
        add(CMBlocks.OTHERWORLDLY_MUSIC_BOX_MUSHROOMS.get(), "Otherworldly Music Box (Mushrooms)", "异界八音盒（蘑菇）");
        add(CMBlocks.OTHERWORLDLY_MUSIC_BOX_JUNGLE.get(), "Otherworldly Music Box (Jungle)", "异界八音盒（丛林）");
        add(CMBlocks.OTHERWORLDLY_MUSIC_BOX_DUNGEON.get(), "Otherworldly Music Box (Dungeon)", "异界八音盒（地牢）");
        add(CMBlocks.OTHERWORLDLY_MUSIC_BOX_SPACE.get(), "Otherworldly Music Box (Space)", "异界八音盒（太空）");
        add(CMBlocks.OTHERWORLDLY_MUSIC_BOX_UNDERWORLD.get(), "Otherworldly Music Box (Underworld)", "异界八音盒（地狱）");
        add(CMBlocks.OTHERWORLDLY_MUSIC_BOX_SNOW.get(), "Otherworldly Music Box (Snow)", "异界八音盒（雪原）");
        add(CMBlocks.OTHERWORLDLY_MUSIC_BOX_ICE.get(), "Otherworldly Music Box (Ice)", "异界八音盒（冰雪）");
        add(CMBlocks.OTHERWORLDLY_MUSIC_BOX_CORRUPTION.get(), "Otherworldly Music Box (Corruption)", "异界八音盒（腐化之地）");
        add(CMBlocks.OTHERWORLDLY_MUSIC_BOX_UNDERGROUND_CORRUPTION.get(), "Otherworldly Music Box (Underground Corruption)", "异界八音盒（地下腐化之地）");
        add(CMBlocks.OTHERWORLDLY_MUSIC_BOX_CRIMSON.get(), "Otherworldly Music Box (Crimson)", "异界八音盒（猩红之地）");
        add(CMBlocks.OTHERWORLDLY_MUSIC_BOX_UNDERGROUND_CRIMSON.get(), "Otherworldly Music Box (Underground Crimson)", "异界八音盒（地下猩红之地）");
        add(CMBlocks.OTHERWORLDLY_MUSIC_BOX_HALLOW.get(), "Otherworldly Music Box (Hallow)", "异界八音盒（神圣之地）");
        add(CMBlocks.OTHERWORLDLY_MUSIC_BOX_UNDERGROUND_HALLOW.get(), "Otherworldly Music Box (Underground Hallow)", "异界八音盒（地下神圣之地）");
        add(CMBlocks.OTHERWORLDLY_MUSIC_BOX_EERIE.get(), "Otherworldly Music Box (Eerie)", "异界八音盒（恐惧）");
        add(CMBlocks.OTHERWORLDLY_MUSIC_BOX_BOSS_1.get(), "Otherworldly Music Box (Boss 1)", "异界八音盒（Boss 1）");
        add(CMBlocks.OTHERWORLDLY_MUSIC_BOX_BOSS_2.get(), "Otherworldly Music Box (Boss 2)", "异界八音盒（Boss 2）");
        add(CMBlocks.OTHERWORLDLY_MUSIC_BOX_INVASION.get(), "Otherworldly Music Box (Invasion)", "异界八音盒（入侵）");
        add(CMBlocks.OTHERWORLDLY_MUSIC_BOX_THE_TOWERS.get(), "Otherworldly Music Box (The Towers)", "异界八音盒（天界柱）");
        add(CMBlocks.OTHERWORLDLY_MUSIC_BOX_LUNAR_BOSS.get(), "Otherworldly Music Box (Lunar Boss)", "异界八音盒（月亮Boss）");
        add(CMBlocks.OTHERWORLDLY_MUSIC_BOX_PLANTERA.get(), "Otherworldly Music Box (Plantera)", "异界八音盒（世纪之花）");
        add(CMBlocks.OTHERWORLDLY_MUSIC_BOX_WALL_OF_FLESH.get(), "Otherworldly Music Box (Wall of Flesh)", "异界八音盒（血肉墙）");
    }

    public void add(String key, String en, String zh) {
        super.add(key, isEn ? en : zh);
    }

    public void add(Item key, String en, String zh) {
        super.add(key, isEn ? en : zh);
    }

    public void add(Block key, String en, String zh) {
        super.add(key, isEn ? en : zh);
    }
}
