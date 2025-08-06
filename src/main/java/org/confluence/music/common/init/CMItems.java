package org.confluence.music.common.init;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.lib.common.component.ModRarity;
import org.confluence.lib.common.item.CustomRarityItem;
import org.confluence.music.ConfluenceMusic;

@SuppressWarnings("unused")
public final class CMItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ConfluenceMusic.MODID);

    public static final DeferredItem<CustomRarityItem> ALPHA = registerMusicDisc(CMJukeboxSongs.ALPHA, ModRarity.EXPERT);
    public static final DeferredItem<CustomRarityItem> OTHERWORLD = registerMusicDisc(CMJukeboxSongs.NEW_JOURNEY, ModRarity.RARE);


    public static final DeferredItem<CustomRarityItem> OVERWORLD_DAY = registerMusicDisc(CMJukeboxSongs.OVERWORLD_DAY, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> ALT_OVERWORLD_DAY = registerMusicDisc(CMJukeboxSongs.ALT_OVERWORLD_DAY, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OVERWORLD_NIGHT = registerMusicDisc(CMJukeboxSongs.OVERWORLD_NIGHT, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> ALT_OVERWORLD_NIGHT = registerMusicDisc(CMJukeboxSongs.ALT_OVERWORLD_NIGHT, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> JOURNEYS_BEGINNING = registerMusicDisc(CMJukeboxSongs.JOURNEYS_BEGINNING, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> JOURNEYS_END = registerMusicDisc(CMJukeboxSongs.JOURNEYS_END, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> TITLE = registerMusicDisc(CMJukeboxSongs.TITLE, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> ALT_TITLE = registerMusicDisc(CMJukeboxSongs.ALT_TITLE, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> DAY_REMIX = registerMusicDisc(CMJukeboxSongs.DAY_REMIX, ModRarity.RARE);

    public static final DeferredItem<CustomRarityItem> SNOW = registerMusicDisc(CMJukeboxSongs.SNOW, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> ICE = registerMusicDisc(CMJukeboxSongs.ICE, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> UNDERGROUND_ICE = registerMusicDisc(CMJukeboxSongs.UNDERGROUND_ICE, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> DESERT = registerMusicDisc(CMJukeboxSongs.DESERT, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> UNDERGROUND_DESERT = registerMusicDisc(CMJukeboxSongs.UNDERGROUND_DESERT, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OCEAN_DAY = registerMusicDisc(CMJukeboxSongs.OCEAN_DAY, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OCEAN_NIGHT = registerMusicDisc(CMJukeboxSongs.OCEAN_NIGHT, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> SPACE_DAY = registerMusicDisc(CMJukeboxSongs.SPACE_DAY, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> SPACE_NIGHT = registerMusicDisc(CMJukeboxSongs.SPACE_NIGHT, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> UNDERGROUND = registerMusicDisc(CMJukeboxSongs.UNDERGROUND, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> ALT_UNDERGROUND = registerMusicDisc(CMJukeboxSongs.ALT_UNDERGROUND, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> MUSHROOMS = registerMusicDisc(CMJukeboxSongs.MUSHROOMS, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> JUNGLE = registerMusicDisc(CMJukeboxSongs.JUNGLE, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> JUNGLE_NIGHT = registerMusicDisc(CMJukeboxSongs.JUNGLE_NIGHT, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> UNDERGROUND_JUNGLE = registerMusicDisc(CMJukeboxSongs.UNDERGROUND_JUNGLE, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> CORRUPTION = registerMusicDisc(CMJukeboxSongs.CORRUPTION, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> UNDERGROUND_CORRUPTION = registerMusicDisc(CMJukeboxSongs.UNDERGROUND_CORRUPTION, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> CRIMSON = registerMusicDisc(CMJukeboxSongs.CRIMSON, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> UNDERGROUND_CRIMSON = registerMusicDisc(CMJukeboxSongs.UNDERGROUND_CRIMSON, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> THE_HALLOW = registerMusicDisc(CMJukeboxSongs.THE_HALLOW, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> UNDERGROUND_HALLOW = registerMusicDisc(CMJukeboxSongs.UNDERGROUND_HALLOW, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> UNDERWORLD = registerMusicDisc(CMJukeboxSongs.UNDERWORLD, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> DUNGEON_FLOOR_1 = registerMusicDisc(CMJukeboxSongs.DUNGEON_FLOOR_1, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> DUNGEON_FLOOR_2 = registerMusicDisc(CMJukeboxSongs.DUNGEON_FLOOR_2, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> TEMPLE = registerMusicDisc(CMJukeboxSongs.TEMPLE, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> AETHER = registerMusicDisc(CMJukeboxSongs.AETHER, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> GRAVEYARD = registerMusicDisc(CMJukeboxSongs.GRAVEYARD, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> TOWN_DAY = registerMusicDisc(CMJukeboxSongs.TOWN_DAY, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> TOWN_NIGHT = registerMusicDisc(CMJukeboxSongs.TOWN_NIGHT, ModRarity.RARE);

    public static final DeferredItem<CustomRarityItem> BOSS_1 = registerMusicDisc(CMJukeboxSongs.BOSS_1, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> BOSS_2 = registerMusicDisc(CMJukeboxSongs.BOSS_2, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> BOSS_3 = registerMusicDisc(CMJukeboxSongs.BOSS_3, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> BOSS_4 = registerMusicDisc(CMJukeboxSongs.BOSS_4, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> BOSS_5 = registerMusicDisc(CMJukeboxSongs.BOSS_5, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> DEERCLOPS = registerMusicDisc(CMJukeboxSongs.DEERCLOPS, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> QUEEN_SLIME = registerMusicDisc(CMJukeboxSongs.QUEEN_SLIME, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> PLANTERA = registerMusicDisc(CMJukeboxSongs.PLANTERA, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> EMPRESS_OF_LIGHT = registerMusicDisc(CMJukeboxSongs.EMPRESS_OF_LIGHT, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> DUKE_FISHRON = registerMusicDisc(CMJukeboxSongs.DUKE_FISHRON, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> MOON_LORD = registerMusicDisc(CMJukeboxSongs.MOON_LORD, ModRarity.RARE);

    public static final DeferredItem<CustomRarityItem> EERIE = registerMusicDisc(CMJukeboxSongs.EERIE, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> ECLIPSE = registerMusicDisc(CMJukeboxSongs.ECLIPSE, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> GOBLIN_ARMY = registerMusicDisc(CMJukeboxSongs.GOBLIN_ARMY, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> PIRATE_INVASION = registerMusicDisc(CMJukeboxSongs.PIRATE_INVASION, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> MARTIAN_MADNESS = registerMusicDisc(CMJukeboxSongs.MARTIAN_MADNESS, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> PUMPKIN_MOON = registerMusicDisc(CMJukeboxSongs.PUMPKIN_MOON, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> FROST_MOON = registerMusicDisc(CMJukeboxSongs.FROST_MOON, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> THE_TOWERS = registerMusicDisc(CMJukeboxSongs.THE_TOWERS, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> SANDSTORM = registerMusicDisc(CMJukeboxSongs.SANDSTORM, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> BLIZZARD = registerMusicDisc(CMJukeboxSongs.BLIZZARD, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OLD_ONES_ARMY = registerMusicDisc(CMJukeboxSongs.OLD_ONES_ARMY, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> SLIME_RAIN = registerMusicDisc(CMJukeboxSongs.SLIME_RAIN, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> WINDY_DAY = registerMusicDisc(CMJukeboxSongs.WINDY_DAY, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> RAIN = registerMusicDisc(CMJukeboxSongs.RAIN, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> MORNING_RAIN = registerMusicDisc(CMJukeboxSongs.MORNING_RAIN, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> STORM = registerMusicDisc(CMJukeboxSongs.STORM, ModRarity.RARE);

    public static final DeferredItem<CustomRarityItem> OTHERWORLDLY_RAIN = registerMusicDisc(CMJukeboxSongs.OTHERWORLDLY_RAIN, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OTHERWORLDLY_OVERWORLD_DAY = registerMusicDisc(CMJukeboxSongs.OTHERWORLDLY_OVERWORLD_DAY, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OTHERWORLDLY_NIGHT = registerMusicDisc(CMJukeboxSongs.OTHERWORLDLY_NIGHT, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OTHERWORLDLY_UNDERGROUND = registerMusicDisc(CMJukeboxSongs.OTHERWORLDLY_UNDERGROUND, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OTHERWORLDLY_DESERT = registerMusicDisc(CMJukeboxSongs.OTHERWORLDLY_DESERT, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OTHERWORLDLY_OCEAN = registerMusicDisc(CMJukeboxSongs.OTHERWORLDLY_OCEAN, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OTHERWORLDLY_MUSHROOMS = registerMusicDisc(CMJukeboxSongs.OTHERWORLDLY_MUSHROOMS, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OTHERWORLDLY_JUNGLE = registerMusicDisc(CMJukeboxSongs.OTHERWORLDLY_JUNGLE, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OTHERWORLDLY_DUNGEON = registerMusicDisc(CMJukeboxSongs.OTHERWORLDLY_DUNGEON, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OTHERWORLDLY_SPACE = registerMusicDisc(CMJukeboxSongs.OTHERWORLDLY_SPACE, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OTHERWORLDLY_ICE = registerMusicDisc(CMJukeboxSongs.OTHERWORLDLY_ICE, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OTHERWORLDLY_UNDERWORLD = registerMusicDisc(CMJukeboxSongs.OTHERWORLDLY_UNDERWORLD, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OTHERWORLDLY_SNOW = registerMusicDisc(CMJukeboxSongs.OTHERWORLDLY_SNOW, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OTHERWORLDLY_CORRUPTION = registerMusicDisc(CMJukeboxSongs.OTHERWORLDLY_CORRUPTION, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OTHERWORLDLY_UNDERGROUND_CORRUPTION = registerMusicDisc(CMJukeboxSongs.OTHERWORLDLY_UNDERGROUND_CORRUPTION, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OTHERWORLDLY_CRIMSON = registerMusicDisc(CMJukeboxSongs.OTHERWORLDLY_CRIMSON, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OTHERWORLDLY_UNDERGROUND_CRIMSON = registerMusicDisc(CMJukeboxSongs.OTHERWORLDLY_UNDERGROUND_CRIMSON, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OTHERWORLDLY_HALLOW = registerMusicDisc(CMJukeboxSongs.OTHERWORLDLY_HALLOW, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OTHERWORLDLY_UNDERGROUND_HALLOW = registerMusicDisc(CMJukeboxSongs.OTHERWORLDLY_UNDERGROUND_HALLOW, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OTHERWORLDLY_EERIE = registerMusicDisc(CMJukeboxSongs.OTHERWORLDLY_EERIE, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OTHERWORLDLY_BOSS_1 = registerMusicDisc(CMJukeboxSongs.OTHERWORLDLY_BOSS_1, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OTHERWORLDLY_BOSS_2 = registerMusicDisc(CMJukeboxSongs.OTHERWORLDLY_BOSS_2, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OTHERWORLDLY_INVASION = registerMusicDisc(CMJukeboxSongs.OTHERWORLDLY_INVASION, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OTHERWORLDLY_THE_TOWERS = registerMusicDisc(CMJukeboxSongs.OTHERWORLDLY_THE_TOWERS, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OTHERWORLDLY_LUNAR_BOSS = registerMusicDisc(CMJukeboxSongs.OTHERWORLDLY_LUNAR_BOSS, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OTHERWORLDLY_PLANTERA = registerMusicDisc(CMJukeboxSongs.OTHERWORLDLY_PLANTERA, ModRarity.RARE);
    public static final DeferredItem<CustomRarityItem> OTHERWORLDLY_WALL_OF_FLESH = registerMusicDisc(CMJukeboxSongs.OTHERWORLDLY_WALL_OF_FLESH, ModRarity.RARE);

    private static DeferredItem<CustomRarityItem> registerMusicDisc(ResourceKey<JukeboxSong> key, ModRarity rarity) {
        return ITEMS.register(key.location().getPath(), () -> new CustomRarityItem(new Item.Properties().stacksTo(1).fireResistant().jukeboxPlayable(key), rarity));
    }
}
