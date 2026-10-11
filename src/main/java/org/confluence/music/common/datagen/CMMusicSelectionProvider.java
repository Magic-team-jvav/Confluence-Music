package org.confluence.music.common.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import org.confluence.lib.common.data.gen.AbstractRecipeProvider;
import org.confluence.music.client.MusicHandler;
import org.confluence.music.client.MusicSelection;
import org.confluence.music.client.MusicSelectionLoader;
import org.confluence.music.common.init.CMMusics;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Consumer;

public class CMMusicSelectionProvider extends AbstractRecipeProvider {
    private final PackOutput.PathProvider pathProvider;

    public CMMusicSelectionProvider(PackOutput output) {
        super(output);
        this.pathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "music_selection");
    }

    /// [音乐如何选择](https://terraria.wiki.gg/zh/wiki/%E9%9F%B3%E4%B9%90)
    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> recipeOutput) {
        recipe(MusicSelectionLoader.CODEC, pathProvider().json(MusicHandler.CONFLUENCE)).addRecipe(new Builder()
                .add(MusicSelection.OVERWORLD_DAY, CMMusics.OVERWORLD_DAY/*, CMMusics.ALT_OVERWORLD_DAY*/)
                .add(MusicSelection.BLOOD_MOON, CMMusics.EERIE)
                .add(MusicSelection.METEORITE, CMMusics.EERIE)
                .add(MusicSelection.OVERWORLD_NIGHT, CMMusics.OVERWORLD_NIGHT, CMMusics.ALT_OVERWORLD_NIGHT, CMMusics.ALT_OVERWORLD_NIGHT_2)
                .add(MusicSelection.THE_HALLOW_NIGHT, CMMusics.OVERWORLD_NIGHT)
                .add(MusicSelection.UNDERGROUND, CMMusics.UNDERGROUND, CMMusics.ALT_UNDERGROUND)
                .add(MusicSelection.EATER_OF_WORLDS, CMMusics.EATER_OF_WORLDS)
                .add(MusicSelection.EYE_OF_CTHULHU, CMMusics.BOSS_1)
                .add(MusicSelection.KING_SLIME, CMMusics.BOSS_1)
                .add(MusicSelection.SKELETRON, CMMusics.BOSS_1)
                .add(MusicSelection.SKELETRON_PRIME, CMMusics.BOSS_1)
                .add(MusicSelection.THE_TWINS, CMMusics.BOSS_2)
                .add(MusicSelection.WALL_OF_FLESH, CMMusics.WALL_OF_FLESH)
                .add(MusicSelection.BRAIN_OF_CTHULHU_PHASE_1, CMMusics.BRAIN_OF_CTHULHU_PHASE_1)
                .add(MusicSelection.BRAIN_OF_CTHULHU_PHASE_2, CMMusics.BRAIN_OF_CTHULHU_PHASE_2)
                .add(MusicSelection.THE_DESTROYER, CMMusics.BOSS_3)
                .add(MusicSelection.TORCH_GOD, CMMusics.BOSS_3)
                .add(MusicSelection.GOLEM, CMMusics.BOSS_4)
                .add(MusicSelection.LUNATIC_CULTIST, CMMusics.BOSS_4)
                .add(MusicSelection.PLANTERA, CMMusics.PLANTERA)
                .add(MusicSelection.QUEEN_BEE, CMMusics.BOSS_5)
                .add(MusicSelection.MOON_LORD, CMMusics.MOON_LORD)
                .add(MusicSelection.QUEEN_SLIME, CMMusics.QUEEN_SLIME)
                .add(MusicSelection.EMPRESS_OF_LIGHT, CMMusics.EMPRESS_OF_LIGHT)
                .add(MusicSelection.DUKE_FISHRON, CMMusics.DUKE_FISHRON)
                .add(MusicSelection.DEERCLOPS, CMMusics.DEERCLOPS)
                .add(MusicSelection.TEMPLE, CMMusics.TEMPLE)
                .add(MusicSelection.JUNGLE_DAY, CMMusics.JUNGLE_DAY)
                .add(MusicSelection.JUNGLE_NIGHT, CMMusics.JUNGLE_NIGHT)
                .add(MusicSelection.UNDERGROUND_JUNGLE, CMMusics.UNDERGROUND_JUNGLE)
                .add(MusicSelection.SNOW, CMMusics.SNOW)
                .add(MusicSelection.ICE, CMMusics.ICE)
                .add(MusicSelection.THE_CORRUPTION, CMMusics.THE_CORRUPTION)
                .add(MusicSelection.UNDERGROUND_CORRUPTION, CMMusics.UNDERGROUND_CORRUPTION)
                .add(MusicSelection.THE_CRIMSON, CMMusics.THE_CRIMSON)
                .add(MusicSelection.UNDERGROUND_CRIMSON, CMMusics.UNDERGROUND_CRIMSON)
                .add(MusicSelection.THE_HALLOW_DAY, CMMusics.THE_HALLOW_DAY)
                .add(MusicSelection.UNDERGROUND_HALLOW, CMMusics.UNDERGROUND_HALLOW)
                .add(MusicSelection.SPACE_DAY, CMMusics.SPACE_DAY)
                .add(MusicSelection.SPACE_NIGHT, CMMusics.SPACE_NIGHT)
                .add(MusicSelection.RAIN_DAY, CMMusics.RAIN)
                .add(MusicSelection.RAIN_NIGHT, CMMusics.RAIN)
                .add(MusicSelection.DESERT, CMMusics.DESERT)
                .add(MusicSelection.UNDERGROUND_DESERT, CMMusics.UNDERGROUND_DESERT)
                .add(MusicSelection.OCEAN_DAY, CMMusics.OCEAN_DAY)
                .add(MusicSelection.OCEAN_NIGHT, CMMusics.OCEAN_NIGHT)
                .add(MusicSelection.DUNGEON_FLOOR_1, CMMusics.DUNGEON_FLOOR_1)
                .add(MusicSelection.DUNGEON_FLOOR_2, CMMusics.DUNGEON_FLOOR_2)
                .add(MusicSelection.THE_END, CMMusics.THE_END)
                .add(MusicSelection.TEMPLE, CMMusics.TEMPLE)
                .add(MusicSelection.AETHER, CMMusics.AETHER)
                .add(MusicSelection.ECLIPSE, CMMusics.ECLIPSE)
                .add(MusicSelection.MUSHROOMS, CMMusics.MUSHROOMS)
                .add(MusicSelection.PUMPKIN_MOON, CMMusics.PUMPKIN_MOON)
                .add(MusicSelection.FROST_MOON, CMMusics.FROST_MOON)
                .add(MusicSelection.LUNAR_EVENTS, CMMusics.THE_TOWERS)
                .add(MusicSelection.PIRATE_INVASION, CMMusics.PIRATE_INVASION)
                .add(MusicSelection.UNDERWORLD, CMMusics.UNDERWORLD)
                .add(MusicSelection.MARTIAN_MADNESS, CMMusics.MARTIAN_MADNESS)
                .add(MusicSelection.GOBLIN_ARMY, CMMusics.GOBLIN_ARMY)
                .add(MusicSelection.SANDSTORM, CMMusics.SANDSTORM)
                .add(MusicSelection.BLIZZARD, CMMusics.BLIZZARD)
                .add(MusicSelection.OLD_ONES_ARMY, CMMusics.OLD_ONES_ARMY)
                .add(MusicSelection.WINDY_DAY, CMMusics.WINDY_DAY)
                .add(MusicSelection.TOWN_DAY, CMMusics.TOWN_DAY)
                .add(MusicSelection.TOWN_NIGHT, CMMusics.TOWN_NIGHT)
                .add(MusicSelection.SLIME_RAIN, CMMusics.SLIME_RAIN)
                .add(MusicSelection.JOURNEYS_BEGINNING, CMMusics.JOURNEYS_BEGINNING)
                .add(MusicSelection.JOURNEYS_END, CMMusics.JOURNEYS_END)
                .add(MusicSelection.TITLE, CMMusics.TITLE)
                .add(MusicSelection.DAY_REMIX, CMMusics.DAY_REMIX)
                .add(MusicSelection.CREATIVE_MODE, CMMusics.CREATIVE_MODE)
                .add(MusicSelection.STORM, CMMusics.STORM)
                .add(MusicSelection.GRAVEYARD, CMMusics.GRAVEYARD)
                .add(MusicSelection.MORNING_RAIN, CMMusics.MORNING_RAIN)
                .map);
    }

    @Override
    protected PackOutput.@NotNull PathProvider pathProvider() {
        return pathProvider;
    }

    public static class Builder {
        private final EnumMap<MusicSelection, List<CMMusics.CachedLocationMusic>> map = new EnumMap<>(MusicSelection.class);

        public final Builder add(MusicSelection selection, CMMusics.CachedLocationMusic... musics) {
            map.put(selection, Arrays.asList(musics));
            return this;
        }
    }
}
