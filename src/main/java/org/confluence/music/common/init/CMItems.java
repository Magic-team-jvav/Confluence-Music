package org.confluence.music.common.init;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.lib.common.component.ModRarity;
import org.confluence.lib.common.item.CustomRarityItem;
import org.confluence.music.ConfluenceMusic;
import org.confluence.music.common.item.MusicBoxItem;

public final class CMItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ConfluenceMusic.MODID);

    public static final DeferredItem<CustomRarityItem> ALPHA = ITEMS.register("alpha", () -> new CustomRarityItem(new Item.Properties().stacksTo(1).fireResistant().jukeboxPlayable(CMJukeboxSongs.ALPHA), ModRarity.EXPERT));
    public static final DeferredItem<MusicBoxItem> MUSIC_BOX = ITEMS.register("music_box", () -> new MusicBoxItem(CMBlocks.MUSIC_BOX.get()));
    public static final DeferredItem<MusicBoxItem> MUSIC_BOX_OTHERWORLD_DAY = ITEMS.register("music_box_otherworld_day", () -> new MusicBoxItem(CMBlocks.MUSIC_BOX_OTHERWORLD_DAY.get()));
    // todo eerie
    public static final DeferredItem<MusicBoxItem> MUSIC_BOX_NIGHT = ITEMS.register("music_box_night", () -> new MusicBoxItem(CMBlocks.MUSIC_BOX_NIGHT.get()));
    // todo title
    public static final DeferredItem<MusicBoxItem> MUSIC_BOX_UNDERGROUND = ITEMS.register("music_box_underground", () -> new MusicBoxItem(CMBlocks.MUSIC_BOX_UNDERGROUND.get()));
    // todo boss1
    public static final DeferredItem<MusicBoxItem> MUSIC_BOX_JUNGLE = ITEMS.register("music_box_jungle", () -> new MusicBoxItem(CMBlocks.MUSIC_BOX_JUNGLE.get()));
    public static final DeferredItem<MusicBoxItem> MUSIC_BOX_CORRUPTION = ITEMS.register("music_box_corruption", () -> new MusicBoxItem(CMBlocks.MUSIC_BOX_CORRUPTION.get()));
    public static final DeferredItem<MusicBoxItem> MUSIC_BOX_UNDERGROUND_CORRUPTION = ITEMS.register("music_box_underground_corruption", () -> new MusicBoxItem(CMBlocks.MUSIC_BOX_UNDERGROUND_CORRUPTION.get()));
    public static final DeferredItem<MusicBoxItem> MUSIC_BOX_THE_HALLOW = ITEMS.register("music_box_the_hallow", () -> new MusicBoxItem(CMBlocks.MUSIC_BOX_THE_HALLOW.get()));
    // todo boss2
    public static final DeferredItem<MusicBoxItem> MUSIC_BOX_UNDERGROUND_HALLOW = ITEMS.register("music_box_underground_hallow", () -> new MusicBoxItem(CMBlocks.MUSIC_BOX_UNDERGROUND_HALLOW.get()));
    // todo boss3
}
