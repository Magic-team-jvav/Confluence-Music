package org.confluence.music.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.confluence.music.ConfluenceMusic;
import org.confluence.music.common.init.CMBlocks;
import org.confluence.music.common.init.CMTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class CMBlockTagsProvider extends BlockTagsProvider {
    public CMBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ConfluenceMusic.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        IntrinsicTagAppender<Block> musicBox = tag(CMTags.Blocks.MUSIC_BOX);
        CMBlocks.BLOCKS.getEntries().forEach(block -> musicBox.add(block.get()));
    }
}
