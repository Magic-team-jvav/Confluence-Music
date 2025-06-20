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
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_OTHERWORLD = register(CMMusics.OTHERWORLD);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_OVERWORLD_DAY = register(CMMusics.OVERWORLD_DAY);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_ALT_OVERWORLD_DAY = register(CMMusics.ALT_OVERWORLD_DAY);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_NIGHT = register(CMMusics.NIGHT);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_RAIN = register(CMMusics.RAIN);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_SNOW = register(CMMusics.SNOW);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_ICE = register(CMMusics.ICE);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_DESERT = register(CMMusics.DESERT);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_UNDERGROUND_DESERT = register(CMMusics.UNDERGROUND_DESERT);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_OCEAN_DAY = register(CMMusics.OCEAN_DAY);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_OCEAN_NIGHT = register(CMMusics.OCEAN_NIGHT);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_SPACE_DAY = register(CMMusics.SPACE_DAY);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_SPACE_NIGHT = register(CMMusics.SPACE_NIGHT);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_UNDERGROUND = register(CMMusics.UNDERGROUND);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_ALT_UNDERGROUND = register(CMMusics.ALT_UNDERGROUND);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_MUSHROOMS = register(CMMusics.MUSHROOMS);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_JUNGLE = register(CMMusics.JUNGLE);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_UNDERGROUND_JUNGLE = register(CMMusics.UNDERGROUND_JUNGLE);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_CORRUPTION = register(CMMusics.CORRUPTION);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_UNDERGROUND_CORRUPTION = register(CMMusics.UNDERGROUND_CORRUPTION);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_CRIMSON = register(CMMusics.CRIMSON);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_UNDERGROUND_CRIMSON = register(CMMusics.UNDERGROUND_CRIMSON);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_THE_HALLOW = register(CMMusics.THE_HALLOW);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_UNDERGROUND_HALLOW = register(CMMusics.UNDERGROUND_HALLOW);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_HELL = register("music_box_hell", CMMusics.UNDERWORLD);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_DUNGEON = register(CMMusics.DUNGEON);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_TEMPLE = register(CMMusics.TEMPLE);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_AETHER = register(CMMusics.AETHER);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_JOURNEYS_BEGINNING = register(CMMusics.JOURNEYS_BEGINNING);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_JOURNEYS_END = register(CMMusics.JOURNEYS_END);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_TITLE = register(CMMusics.TITLE);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_ALT_TITLE = register(CMMusics.ALT_TITLE);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_DAY_REMIX = register(CMMusics.DAY_REMIX);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_BOSS_1 = register(CMMusics.BOSS_1);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_BOSS_2 = register(CMMusics.BOSS_2);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_BOSS_3 = register(CMMusics.BOSS_3);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_BOSS_4 = register(CMMusics.BOSS_4);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_BOSS_5 = register(CMMusics.BOSS_5);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_DEERCLOPS = register(CMMusics.DEERCLOPS);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_QUEEN_SLIME = register(CMMusics.QUEEN_SLIME);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_PLANTERA = register(CMMusics.PLANTERA);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_EMPRESS_OF_LIGHT = register(CMMusics.EMPRESS_OF_LIGHT);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_DUKE_FISHRON = register(CMMusics.DUKE_FISHRON);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_LUNAR_BOSS = register(CMMusics.LUNAR_BOSS);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_EERIE = register(CMMusics.EERIE);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_ECLIPSE = register(CMMusics.ECLIPSE);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_GOBLIN_INVASION = register(CMMusics.GOBLIN_INVASION);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_PIRATE_INVASION = register(CMMusics.PIRATE_INVASION);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_MARTIAN_MADNESS = register(CMMusics.MARTIAN_MADNESS);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_PUMPKIN_MOON = register(CMMusics.PUMPKIN_MOON);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_FROST_MOON = register(CMMusics.FROST_MOON);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_THE_TOWERS = register(CMMusics.THE_TOWERS);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_SANDSTORM = register(CMMusics.SANDSTORM);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_OLD_ONES_ARMY = register(CMMusics.OLD_ONES_ARMY);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_SLIME_RAIN = register(CMMusics.SLIME_RAIN);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_TOWN_DAY = register(CMMusics.TOWN_DAY);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_TOWN_NIGHT = register(CMMusics.TOWN_NIGHT);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_WINDY_DAY = register(CMMusics.WINDY_DAY);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_STORM = register(CMMusics.STORM);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_GRAVEYARD = register(CMMusics.GRAVEYARD);
    public static final DeferredBlock<MusicBoxBlock> MUSIC_BOX_MORNING_RAIN = register(CMMusics.MORNING_RAIN);
    public static final DeferredBlock<MusicBoxBlock> OTHERWORLDLY_MUSIC_BOX_RAIN = register(CMMusics.OTHERWORLDLY_RAIN);
    public static final DeferredBlock<MusicBoxBlock> OTHERWORLDLY_MUSIC_BOX_OVERWORLD_DAY = register(CMMusics.OTHERWORLDLY_OVERWORLD_DAY);
    public static final DeferredBlock<MusicBoxBlock> OTHERWORLDLY_MUSIC_BOX_NIGHT = register(CMMusics.OTHERWORLDLY_NIGHT);
    public static final DeferredBlock<MusicBoxBlock> OTHERWORLDLY_MUSIC_BOX_UNDERGROUND = register(CMMusics.OTHERWORLDLY_UNDERGROUND);
    public static final DeferredBlock<MusicBoxBlock> OTHERWORLDLY_MUSIC_BOX_DESERT = register(CMMusics.OTHERWORLDLY_DESERT);
    public static final DeferredBlock<MusicBoxBlock> OTHERWORLDLY_MUSIC_BOX_OCEAN = register(CMMusics.OTHERWORLDLY_OCEAN);
    public static final DeferredBlock<MusicBoxBlock> OTHERWORLDLY_MUSIC_BOX_MUSHROOMS = register(CMMusics.OTHERWORLDLY_MUSHROOMS);
    public static final DeferredBlock<MusicBoxBlock> OTHERWORLDLY_MUSIC_BOX_JUNGLE = register(CMMusics.OTHERWORLDLY_JUNGLE);
    public static final DeferredBlock<MusicBoxBlock> OTHERWORLDLY_MUSIC_BOX_DUNGEON = register(CMMusics.OTHERWORLDLY_DUNGEON);
    public static final DeferredBlock<MusicBoxBlock> OTHERWORLDLY_MUSIC_BOX_SPACE = register(CMMusics.OTHERWORLDLY_SPACE);
    public static final DeferredBlock<MusicBoxBlock> OTHERWORLDLY_MUSIC_BOX_UNDERWORLD = register(CMMusics.OTHERWORLDLY_UNDERWORLD);
    public static final DeferredBlock<MusicBoxBlock> OTHERWORLDLY_MUSIC_BOX_SNOW = register(CMMusics.OTHERWORLDLY_SNOW);
    public static final DeferredBlock<MusicBoxBlock> OTHERWORLDLY_MUSIC_BOX_ICE = register(CMMusics.OTHERWORLDLY_ICE);
    public static final DeferredBlock<MusicBoxBlock> OTHERWORLDLY_MUSIC_BOX_CORRUPTION = register(CMMusics.OTHERWORLDLY_CORRUPTION);
    public static final DeferredBlock<MusicBoxBlock> OTHERWORLDLY_MUSIC_BOX_UNDERGROUND_CORRUPTION = register(CMMusics.OTHERWORLDLY_UNDERGROUND_CORRUPTION);
    public static final DeferredBlock<MusicBoxBlock> OTHERWORLDLY_MUSIC_BOX_CRIMSON = register(CMMusics.OTHERWORLDLY_CRIMSON);
    public static final DeferredBlock<MusicBoxBlock> OTHERWORLDLY_MUSIC_BOX_UNDERGROUND_CRIMSON = register(CMMusics.OTHERWORLDLY_UNDERGROUND_CRIMSON);
    public static final DeferredBlock<MusicBoxBlock> OTHERWORLDLY_MUSIC_BOX_HALLOW = register(CMMusics.OTHERWORLDLY_HALLOW);
    public static final DeferredBlock<MusicBoxBlock> OTHERWORLDLY_MUSIC_BOX_UNDERGROUND_HALLOW = register(CMMusics.OTHERWORLDLY_UNDERGROUND_HALLOW);
    public static final DeferredBlock<MusicBoxBlock> OTHERWORLDLY_MUSIC_BOX_EERIE = register(CMMusics.OTHERWORLDLY_EERIE);
    public static final DeferredBlock<MusicBoxBlock> OTHERWORLDLY_MUSIC_BOX_BOSS_1 = register(CMMusics.OTHERWORLDLY_BOSS_1);
    public static final DeferredBlock<MusicBoxBlock> OTHERWORLDLY_MUSIC_BOX_BOSS_2 = register(CMMusics.OTHERWORLDLY_BOSS_2);
    public static final DeferredBlock<MusicBoxBlock> OTHERWORLDLY_MUSIC_BOX_INVASION = register(CMMusics.OTHERWORLDLY_INVASION);
    public static final DeferredBlock<MusicBoxBlock> OTHERWORLDLY_MUSIC_BOX_THE_TOWERS = register(CMMusics.OTHERWORLDLY_THE_TOWERS);
    public static final DeferredBlock<MusicBoxBlock> OTHERWORLDLY_MUSIC_BOX_LUNAR_BOSS = register(CMMusics.OTHERWORLDLY_LUNAR_BOSS);
    public static final DeferredBlock<MusicBoxBlock> OTHERWORLDLY_MUSIC_BOX_PLANTERA = register(CMMusics.OTHERWORLDLY_PLANTERA);
    public static final DeferredBlock<MusicBoxBlock> OTHERWORLDLY_MUSIC_BOX_WALL_OF_FLESH = register(CMMusics.OTHERWORLDLY_WALL_OF_FLESH);

    public static final Supplier<BlockEntityType<MusicBoxBlock.Entity>> MUSIC_BOX_ENTITY = BLOCK_ENTITIES.register("music_box_entity", () -> {
        Block[] validBlocks = MUSIC_BOXES.stream().map(Supplier::get).toArray(Block[]::new);
        MUSIC_BOXES = null;
        return BlockEntityType.Builder.of(MusicBoxBlock.Entity::new, validBlocks).build(DSL.remainderType());
    });

    private static DeferredBlock<MusicBoxBlock> register(@Nullable Music music) {
        String id;
        String path = music.getEvent().getKey().location().getPath();
        if (path.startsWith("otherworldly_")) {
            id = "otherworldly_music_box_" + path.substring(13);
        } else {
            id = "music_box_" + path;
        }
        return register(id, music);
    }

    private static DeferredBlock<MusicBoxBlock> register(String id, @Nullable Music music) {
        DeferredBlock<MusicBoxBlock> object = BLOCKS.register(id, () -> new MusicBoxBlock(music));
        CMItems.ITEMS.register(id, () -> new MusicBoxItem(object.get()));
        MUSIC_BOXES.add(object);
        return object;
    }
}
