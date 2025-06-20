package org.confluence.music.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.confluence.music.ConfluenceMusic;
import org.confluence.music.common.init.CMBlocks;
import org.confluence.music.common.init.CMTags;
import org.confluence.terra_curio.common.init.TCTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class CMItemTagsProvider extends ItemTagsProvider {
    public CMItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, ConfluenceMusic.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        copy(CMTags.Blocks.MUSIC_BOX, CMTags.Items.MUSIC_BOX);
        IntrinsicTagAppender<Item> accessory = tag(TCTags.ACCESSORY);
        CMBlocks.BLOCKS.getEntries().forEach(block -> accessory.add(block.get().asItem()));
    }
}
