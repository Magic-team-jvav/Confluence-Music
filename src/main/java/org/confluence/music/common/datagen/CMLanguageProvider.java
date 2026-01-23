package org.confluence.music.common.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.confluence.music.ConfluenceMusic;

public class CMLanguageProvider extends LanguageProvider {
    private final boolean isEn;

    public CMLanguageProvider(PackOutput output, boolean isEn) {
        super(output, ConfluenceMusic.MODID, isEn ? "en_us" : "zh_cn");
        this.isEn = isEn;
    }

    @Override
    protected void addTranslations() {
        add("confluence_music.configuration.title", "Confluence: Otherworld Music Configs", "汇流来世音乐配置");
        add("confluence_music.configuration.checkInterval", "check Interval", "检查间隔");
        add("confluence_music.configuration.checkInterval.tooltip", "The interval for checking whether biomes, events, and structures have changed", "检查群系、事件、结构是否变动的间隔");
        add("confluence_music.configuration.musicType", "Music Type", "音乐类型");
        add("confluence_music.configuration.musicType.tooltip", "Used to determine the playlist", "用于决定歌单");
        add("confluence_music.configuration.lastSongFadeOutTime", "Last Song Fade-Out Time", "上一首淡出时间");
        add("confluence_music.configuration.lastSongFadeOutTime.tooltip", "Set it to 20, which means 1 second; set it to 0, which means stop immediately.", "设为20即1秒，设为0即立刻停止");
        add("confluence_music.configuration.nextSongDelay", "Next Song Delay", "下一首歌的间隔");
        add("confluence_music.configuration.nextSongDelay.tooltip", "The total interval between the two songs is: lastSongFadeOutTime + nextSongDelay.", "两首歌的总间隔为：上一首淡出时间 + 下一首歌的间隔");
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
