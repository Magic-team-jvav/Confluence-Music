package org.confluence.music.common.init;

import com.mojang.datafixers.DSL;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.Music;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.music.ConfluenceMusic;
import org.confluence.music.common.block.MusicBoxBlock;
import org.confluence.music.common.item.MusicBoxItem;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

// todo 补全
public final class CMBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ConfluenceMusic.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ConfluenceMusic.MODID);
    private static List<Supplier<? extends Block>> MUSIC_BOXES = new ArrayList<>();

    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX = register("music_box", null);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_OVERWORLD_DAY = register("music_box_overworld_day", CMMusics.OVERWORLD_DAY);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_ALT_OVERWORLD_DAY = register("music_box_alt_overworld_day", CMMusics.ALT_OVERWORLD_DAY);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_EERIE = register("music_box_eerie", CMMusics.EERIE);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_NIGHT = register("music_box_night", CMMusics.NIGHT);

    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_UNDERGROUND = register("music_box_underground", CMMusics.UNDERGROUND);

    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_JUNGLE = register("music_box_jungle", CMMusics.JUNGLE);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_CORRUPTION = register("music_box_corruption", CMMusics.CORRUPTION);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_UNDERGROUND_CORRUPTION = register("music_box_underground_corruption", CMMusics.UNDERGROUND_CORRUPTION);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_THE_HALLOW = register("music_box_the_hallow", CMMusics.THE_HALLOW);

    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_UNDERGROUND_HALLOW = register("music_box_underground_hallow", CMMusics.UNDERGROUND_HALLOW);

    public static final Supplier<BlockEntityType<MusicBoxBlock.Entity>> MUSIC_BOX_ENTITY = BLOCK_ENTITIES.register("music_box_entity", () -> {
        Block[] validBlocks = MUSIC_BOXES.stream().map(Supplier::get).toArray(Block[]::new);
        MUSIC_BOXES = null;
        return BlockEntityType.Builder.of(MusicBoxBlock.Entity::new, validBlocks).build(DSL.remainderType());
    });

    private static DeferredBlock<MusicBoxBlock> register(String id, @Nullable Music music) {
        DeferredBlock<MusicBoxBlock> object = BLOCKS.register(id, () -> new MusicBoxBlock(music));
        CMItems.ITEMS.register(id, () -> new MusicBoxItem(object.get()));
        MUSIC_BOXES.add(object);
        return object;
    }
}
