package org.confluence.music.common.init;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.lib.common.component.ModRarity;
import org.confluence.lib.common.item.CustomRarityItem;
import org.confluence.music.ConfluenceMusic;

public final class CMItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ConfluenceMusic.MODID);

    public static final DeferredItem<CustomRarityItem> ALPHA = ITEMS.register("alpha", () -> new CustomRarityItem(new Item.Properties().stacksTo(1).fireResistant().jukeboxPlayable(CMJukeboxSongs.ALPHA), ModRarity.EXPERT));
}
