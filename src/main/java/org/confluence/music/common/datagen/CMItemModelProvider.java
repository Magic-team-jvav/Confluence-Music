package org.confluence.music.common.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.confluence.music.ConfluenceMusic;
import org.confluence.music.common.init.CMItems;

public class CMItemModelProvider extends ItemModelProvider {
    public CMItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ConfluenceMusic.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (DeferredHolder<Item, ? extends Item> item : CMItems.ITEMS.getEntries()) {
            String path = item.getId().getPath();
            ResourceLocation texture = ConfluenceMusic.asResource("item/" + path);
            if (existingFileHelper.exists(texture, ModelProvider.TEXTURE)) {
                withExistingParent(path, "item/generated").texture("layer0", texture);
            }
        }
    }
}
