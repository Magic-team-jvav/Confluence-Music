package org.confluence.music.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import org.confluence.lib.common.data.gen.AbstractRecipeProvider;
import org.confluence.music.client.MusicHandler;
import org.confluence.music.client.MusicSelection;
import org.confluence.music.client.MusicSelectionLoader;
import org.confluence.music.common.init.CMMusics;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CMMusicSelectionProvider extends AbstractRecipeProvider {
    private final PackOutput.PathProvider pathProvider;

    public CMMusicSelectionProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup);
        this.pathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "music_selection");
    }

    /**
     * <a href="https://terraria.wiki.gg/zh/wiki/%E9%9F%B3%E4%B9%90">音乐如何选择</a>
     */
    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput, HolderLookup.@NotNull Provider holderLookup) {
        recipe(MusicSelectionLoader.CODEC, pathProvider().json(MusicHandler.CONFLUENCE)).addRecipe(new Builder()
                .add(MusicSelection.DAY, CMMusics.OVERWORLD_DAY, CMMusics.ALT_OVERWORLD_DAY)
                .add(MusicSelection.EERIE, CMMusics.EERIE)
                .add(MusicSelection.NIGHT, CMMusics.NIGHT)
                .add(MusicSelection.UNDERGROUND, CMMusics.UNDERGROUND, CMMusics.ALT_UNDERGROUND)
                .add(MusicSelection.KING_SLIME, CMMusics.BOSS_1)
                .add(MusicSelection.EYE_OF_CTHULHU, CMMusics.BOSS_1)
                .add(MusicSelection.EATER_OF_WORLDS, CMMusics.BOSS_1)
                .add(MusicSelection.SKELETRON, CMMusics.BOSS_1)
                .add(MusicSelection.WALL_OF_FLESH, CMMusics.BOSS_2)
                .add(MusicSelection.BRAIN_OF_CTHULHU, CMMusics.BOSS_3)
                .add(MusicSelection.QUEEN_BEE, CMMusics.BOSS_5)
                .add(MusicSelection.JUNGLE_DAY, CMMusics.JUNGLE)
                .add(MusicSelection.JUNGLE_NIGHT, CMMusics.JUNGLE_NIGHT)
                .add(MusicSelection.UNDERGROUND_JUNGLE, CMMusics.UNDERGROUND_JUNGLE)
                .add(MusicSelection.SNOW, CMMusics.SNOW, CMMusics.ICE)
                .add(MusicSelection.ICE, CMMusics.ICE)
                .add(MusicSelection.UNDERGROUND_ICE, CMMusics.UNDERGROUND_ICE)
                .add(MusicSelection.CORRUPTION, CMMusics.CORRUPTION)
                .add(MusicSelection.UNDERGROUND_CORRUPTION, CMMusics.UNDERGROUND_CORRUPTION)
                .add(MusicSelection.CRIMSON, CMMusics.CRIMSON)
                .add(MusicSelection.UNDERGROUND_CRIMSON, CMMusics.UNDERGROUND_CRIMSON)
                .add(MusicSelection.THE_HALLOW, CMMusics.THE_HALLOW)
                .add(MusicSelection.UNDERGROUND_HALLOW, CMMusics.UNDERGROUND_HALLOW)
                .add(MusicSelection.SPACE_DAY, CMMusics.SPACE_DAY)
                .add(MusicSelection.SPACE_NIGHT, CMMusics.SPACE_NIGHT)
                .add(MusicSelection.RAIN, CMMusics.RAIN)
                .add(MusicSelection.DESERT, CMMusics.DESERT)
                .add(MusicSelection.OCEAN_DAY, CMMusics.OCEAN_DAY)
                .add(MusicSelection.OCEAN_NIGHT, CMMusics.OCEAN_NIGHT)
                .add(MusicSelection.DUNGEON, CMMusics.DUNGEON)
                .add(MusicSelection.TEMPLE, CMMusics.TEMPLE)
                .add(MusicSelection.AETHER, CMMusics.AETHER)
                .add(MusicSelection.ECLIPSE, CMMusics.ECLIPSE)
                .add(MusicSelection.MUSHROOMS, CMMusics.MUSHROOMS)
                .add(MusicSelection.PUMPKIN_MOON, CMMusics.PUMPKIN_MOON)
                .add(MusicSelection.FROST_MOON, CMMusics.FROST_MOON)
                .add(MusicSelection.THE_TOWERS, CMMusics.THE_TOWERS)
                .add(MusicSelection.PIRATE_INVASION, CMMusics.PIRATE_INVASION)
                .add(MusicSelection.UNDERWORLD, CMMusics.UNDERWORLD)
                .add(MusicSelection.MARTIAN_MADNESS, CMMusics.MARTIAN_MADNESS)
                .add(MusicSelection.GOBLIN_INVASION, CMMusics.GOBLIN_INVASION)
                .add(MusicSelection.SANDSTORM, CMMusics.SANDSTORM)
                .add(MusicSelection.OLD_ONES_ARMY, CMMusics.OLD_ONES_ARMY)
                .add(MusicSelection.WINDY_DAY, CMMusics.WINDY_DAY)
                .add(MusicSelection.TOWN_DAY, CMMusics.TOWN_DAY)
                .add(MusicSelection.TOWN_NIGHT, CMMusics.TOWN_NIGHT)
                .add(MusicSelection.SLIME_RAIN, CMMusics.SLIME_RAIN)
                .add(MusicSelection.JOURNEYS_BEGINNING, CMMusics.JOURNEYS_BEGINNING)
                .add(MusicSelection.JOURNEYS_END, CMMusics.JOURNEYS_END)
                .add(MusicSelection.TITLE, CMMusics.TITLE)
                .add(MusicSelection.DAY_REMIX, CMMusics.DAY_REMIX)
                .add(MusicSelection.STORM, CMMusics.STORM)
                .add(MusicSelection.GRAVEYARD, CMMusics.GRAVEYARD)
                .add(MusicSelection.MORNING_RAIN, CMMusics.MORNING_RAIN)
                .map);

        recipe(MusicSelectionLoader.CODEC, pathProvider().json(MusicHandler.OTHERWORLDLY)).addRecipe(new Builder()
                // todo
                .map);
    }

    @Override
    protected PackOutput.@NotNull PathProvider pathProvider() {
        return pathProvider;
    }

    public static class Builder {
        private final EnumMap<MusicSelection, List<CMMusics.CachedLocationMusic>> map = new EnumMap<>(MusicSelection.class);

        public Builder add(MusicSelection selection, CMMusics.CachedLocationMusic... musics) {
            map.put(selection, Arrays.stream(musics).toList());
            return this;
        }
    }
}
