package org.confluence.music.common.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.confluence.music.ConfluenceMusic;

public final class CMTags {
    public static class Items {
        public static final TagKey<Item> MUSIC_BOX = TagKey.create(Registries.ITEM, ConfluenceMusic.asResource("music_box"));
    }

    public static class Blocks {
        public static final TagKey<Block> MUSIC_BOX = TagKey.create(Registries.BLOCK, ConfluenceMusic.asResource("music_box"));
    }
}
