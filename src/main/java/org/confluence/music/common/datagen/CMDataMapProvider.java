package org.confluence.music.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import org.confluence.mod.common.component.ValueComponent;
import org.confluence.mod.common.init.ModDataMaps;
import org.confluence.music.common.init.CMTags;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class CMDataMapProvider extends DataMapProvider {
    public CMDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.@NotNull Provider provider) {
        builder(ModDataMaps.VALUE).add(CMTags.Items.MUSIC_BOX, new ValueComponent(20000), false);
    }
}
