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
        add(CMItems.ALPHA.get(), "Alpha", "Alpha");
        add(CMBlocks.MUSIC_BOX.get(), "Music Box", "八音盒");
        add(CMBlocks.MUSIC_BOX_OVERWORLD_DAY.get(), "Music Box (Overworld Day)", "八音盒（地表世界）");
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
