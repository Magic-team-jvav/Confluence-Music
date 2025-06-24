package org.confluence.music.common.init;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.music.ConfluenceMusic;

public final class CMJukeboxSongs {
    public static final DeferredRegister<JukeboxSong> SONGS = DeferredRegister.create(Registries.JUKEBOX_SONG, ConfluenceMusic.MODID);

    public static final ResourceKey<JukeboxSong> ALPHA = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("alpha"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> ALPHA_SONG = register(ALPHA, SoundEvents.MUSIC_CREDITS, 603.0F, 0);


    public static final ResourceKey<JukeboxSong> NEW_JOURNEY = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("new_journey"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> NEW_JOURNEY_SONG = register(NEW_JOURNEY, CMSoundEvents.NEW_JOURNEY, 603.0F, 0);

    public static final ResourceKey<JukeboxSong> OVERWORLD_DAY = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("overworld_day"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OVERWORLD_DAY_SONG = register(OVERWORLD_DAY, CMSoundEvents.OVERWORLD_DAY, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> ALT_OVERWORLD_DAY = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("alt_overworld_day"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> ALT_OVERWORLD_DAY_SONG = register(ALT_OVERWORLD_DAY, CMSoundEvents.ALT_OVERWORLD_DAY, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OVERWORLD_NIGHT = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("overworld_night"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OVERWORLD_NIGHT_SONG = register(OVERWORLD_NIGHT, CMSoundEvents.OVERWORLD_NIGHT, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> JOURNEYS_BEGINNING = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("journeys_beginning"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> JOURNEYS_BEGINNING_SONG = register(JOURNEYS_BEGINNING, CMSoundEvents.JOURNEYS_BEGINNING, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> JOURNEYS_END = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("journeys_end"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> JOURNEYS_END_SONG = register(JOURNEYS_END, CMSoundEvents.JOURNEYS_END, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> TITLE = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("title"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> TITLE_SONG = register(TITLE, CMSoundEvents.TITLE, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> ALT_TITLE = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("alt_title"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> ALT_TITLE_SONG = register(ALT_TITLE, CMSoundEvents.ALT_TITLE, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> DAY_REMIX = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("day_remix"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> DAY_REMIX_SONG = register(DAY_REMIX, CMSoundEvents.DAY_REMIX, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> SNOW = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("snow"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> SNOW_SONG = register(SNOW, CMSoundEvents.SNOW, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> ICE = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("ice"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> ICE_SONG = register(ICE, CMSoundEvents.ICE, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> UNDERGROUND_ICE = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("underground_ice"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> UNDERGROUND_ICE_SONG = register(UNDERGROUND_ICE, CMSoundEvents.UNDERGROUND_ICE, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> DESERT = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("desert"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> DESERT_SONG = register(DESERT, CMSoundEvents.DESERT, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> UNDERGROUND_DESERT = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("underground_desert"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> UNDERGROUND_DESERT_SONG = register(UNDERGROUND_DESERT, CMSoundEvents.UNDERGROUND_DESERT, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OCEAN_DAY = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("ocean_day"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OCEAN_DAY_SONG = register(OCEAN_DAY, CMSoundEvents.OCEAN_DAY, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OCEAN_NIGHT = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("ocean_night"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OCEAN_NIGHT_SONG = register(OCEAN_NIGHT, CMSoundEvents.OCEAN_NIGHT, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> SPACE_DAY = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("space_day"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> SPACE_DAY_SONG = register(SPACE_DAY, CMSoundEvents.SPACE_DAY, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> SPACE_NIGHT = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("space_night"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> SPACE_NIGHT_SONG = register(SPACE_NIGHT, CMSoundEvents.SPACE_NIGHT, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> UNDERGROUND = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("underground"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> UNDERGROUND_SONG = register(UNDERGROUND, CMSoundEvents.UNDERGROUND, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> ALT_UNDERGROUND = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("alt_underground"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> ALT_UNDERGROUND_SONG = register(ALT_UNDERGROUND, CMSoundEvents.ALT_UNDERGROUND, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> MUSHROOMS = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("mushrooms"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> MUSHROOMS_SONG = register(MUSHROOMS, CMSoundEvents.MUSHROOMS, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> JUNGLE = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("jungle"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> JUNGLE_SONG = register(JUNGLE, CMSoundEvents.JUNGLE, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> JUNGLE_NIGHT = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("jungle_night"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> JUNGLE_NIGHT_SONG = register(JUNGLE_NIGHT, CMSoundEvents.JUNGLE_NIGHT, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> UNDERGROUND_JUNGLE = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("underground_jungle"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> UNDERGROUND_JUNGLE_SONG = register(UNDERGROUND_JUNGLE, CMSoundEvents.UNDERGROUND_JUNGLE, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> CORRUPTION = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("corruption"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> CORRUPTION_SONG = register(CORRUPTION, CMSoundEvents.CORRUPTION, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> UNDERGROUND_CORRUPTION = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("underground_corruption"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> UNDERGROUND_CORRUPTION_SONG = register(UNDERGROUND_CORRUPTION, CMSoundEvents.UNDERGROUND_CORRUPTION, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> CRIMSON = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("crimson"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> CRIMSON_SONG = register(CRIMSON, CMSoundEvents.CRIMSON, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> UNDERGROUND_CRIMSON = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("underground_crimson"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> UNDERGROUND_CRIMSON_SONG = register(UNDERGROUND_CRIMSON, CMSoundEvents.UNDERGROUND_CRIMSON, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> THE_HALLOW = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("the_hallow"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> THE_HALLOW_SONG = register(THE_HALLOW, CMSoundEvents.THE_HALLOW, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> UNDERGROUND_HALLOW = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("underground_hallow"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> UNDERGROUND_HALLOW_SONG = register(UNDERGROUND_HALLOW, CMSoundEvents.UNDERGROUND_HALLOW, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> UNDERWORLD = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("underworld"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> UNDERWORLD_SONG = register(UNDERWORLD, CMSoundEvents.UNDERWORLD, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> DUNGEON = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("dungeon"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> DUNGEON_SONG = register(DUNGEON, CMSoundEvents.DUNGEON, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> TEMPLE = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("temple"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> TEMPLE_SONG = register(TEMPLE, CMSoundEvents.TEMPLE, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> AETHER = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("aether"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> AETHER_SONG = register(AETHER, CMSoundEvents.AETHER, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> GRAVEYARD = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("graveyard"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> GRAVEYARD_SONG = register(GRAVEYARD, CMSoundEvents.GRAVEYARD, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> TOWN_DAY = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("town_day"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> TOWN_DAY_SONG = register(TOWN_DAY, CMSoundEvents.TOWN_DAY, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> TOWN_NIGHT = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("town_night"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> TOWN_NIGHT_SONG = register(TOWN_NIGHT, CMSoundEvents.TOWN_NIGHT, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> BOSS_1 = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("boss_1"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> BOSS_1_SONG = register(BOSS_1, CMSoundEvents.BOSS_1, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> BOSS_2 = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("boss_2"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> BOSS_2_SONG = register(BOSS_2, CMSoundEvents.BOSS_2, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> BOSS_3 = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("boss_3"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> BOSS_3_SONG = register(BOSS_3, CMSoundEvents.BOSS_3, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> BOSS_4 = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("boss_4"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> BOSS_4_SONG = register(BOSS_4, CMSoundEvents.BOSS_4, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> BOSS_5 = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("boss_5"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> BOSS_5_SONG = register(BOSS_5, CMSoundEvents.BOSS_5, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> DEERCLOPS = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("deerclops"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> DEERCLOPS_SONG = register(DEERCLOPS, CMSoundEvents.DEERCLOPS, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> QUEEN_SLIME = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("queen_slime"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> QUEEN_SLIME_SONG = register(QUEEN_SLIME, CMSoundEvents.QUEEN_SLIME, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> PLANTERA = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("plantera"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> PLANTERA_SONG = register(PLANTERA, CMSoundEvents.PLANTERA, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> EMPRESS_OF_LIGHT = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("empress_of_light"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> EMPRESS_OF_LIGHT_SONG = register(EMPRESS_OF_LIGHT, CMSoundEvents.EMPRESS_OF_LIGHT, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> DUKE_FISHRON = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("duke_fishron"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> DUKE_FISHRON_SONG = register(DUKE_FISHRON, CMSoundEvents.DUKE_FISHRON, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> MOON_LORD = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("moon_lord"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> MOON_LORD_SONG = register(MOON_LORD, CMSoundEvents.MOON_LORD, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> EERIE = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("eerie"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> EERIE_SONG = register(EERIE, CMSoundEvents.EERIE, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> ECLIPSE = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("eclipse"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> ECLIPSE_SONG = register(ECLIPSE, CMSoundEvents.ECLIPSE, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> GOBLIN_ARMY = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("goblin_army"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> GOBLIN_ARMY_SONG = register(GOBLIN_ARMY, CMSoundEvents.GOBLIN_ARMY, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> PIRATE_INVASION = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("pirate_invasion"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> PIRATE_INVASION_SONG = register(PIRATE_INVASION, CMSoundEvents.PIRATE_INVASION, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> MARTIAN_MADNESS = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("martian_madness"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> MARTIAN_MADNESS_SONG = register(MARTIAN_MADNESS, CMSoundEvents.MARTIAN_MADNESS, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> PUMPKIN_MOON = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("pumpkin_moon"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> PUMPKIN_MOON_SONG = register(PUMPKIN_MOON, CMSoundEvents.PUMPKIN_MOON, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> FROST_MOON = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("frost_moon"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> FROST_MOON_SONG = register(FROST_MOON, CMSoundEvents.FROST_MOON, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> THE_TOWERS = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("the_towers"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> THE_TOWERS_SONG = register(THE_TOWERS, CMSoundEvents.THE_TOWERS, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> SANDSTORM = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("sandstorm"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> SANDSTORM_SONG = register(SANDSTORM, CMSoundEvents.SANDSTORM, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OLD_ONES_ARMY = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("old_ones_army"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OLD_ONES_ARMY_SONG = register(OLD_ONES_ARMY, CMSoundEvents.OLD_ONES_ARMY, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> SLIME_RAIN = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("slime_rain"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> SLIME_RAIN_SONG = register(SLIME_RAIN, CMSoundEvents.SLIME_RAIN, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> WINDY_DAY = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("windy_day"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> WINDY_DAY_SONG = register(WINDY_DAY, CMSoundEvents.WINDY_DAY, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> RAIN = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("rain"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> RAIN_SONG = register(RAIN, CMSoundEvents.RAIN, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> MORNING_RAIN = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("morning_rain"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> MORNING_RAIN_SONG = register(MORNING_RAIN, CMSoundEvents.MORNING_RAIN, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> STORM = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("storm"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> STORM_SONG = register(STORM, CMSoundEvents.STORM, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OTHERWORLDLY_RAIN = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("otherworldly_rain"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OTHERWORLDLY_RAIN_SONG = register(OTHERWORLDLY_RAIN, CMSoundEvents.OTHERWORLDLY_RAIN, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OTHERWORLDLY_OVERWORLD_DAY = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("otherworldly_overworld_day"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OTHERWORLDLY_OVERWORLD_DAY_SONG = register(OTHERWORLDLY_OVERWORLD_DAY, CMSoundEvents.OTHERWORLDLY_OVERWORLD_DAY, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OTHERWORLDLY_NIGHT = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("otherworldly_night"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OTHERWORLDLY_NIGHT_SONG = register(OTHERWORLDLY_NIGHT, CMSoundEvents.OTHERWORLDLY_NIGHT, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OTHERWORLDLY_UNDERGROUND = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("otherworldly_underground"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OTHERWORLDLY_UNDERGROUND_SONG = register(OTHERWORLDLY_UNDERGROUND, CMSoundEvents.OTHERWORLDLY_UNDERGROUND, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OTHERWORLDLY_DESERT = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("otherworldly_desert"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OTHERWORLDLY_DESERT_SONG = register(OTHERWORLDLY_DESERT, CMSoundEvents.OTHERWORLDLY_DESERT, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OTHERWORLDLY_OCEAN = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("otherworldly_ocean"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OTHERWORLDLY_OCEAN_SONG = register(OTHERWORLDLY_OCEAN, CMSoundEvents.OTHERWORLDLY_OCEAN, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OTHERWORLDLY_MUSHROOMS = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("otherworldly_mushrooms"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OTHERWORLDLY_MUSHROOMS_SONG = register(OTHERWORLDLY_MUSHROOMS, CMSoundEvents.OTHERWORLDLY_MUSHROOMS, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OTHERWORLDLY_JUNGLE = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("otherworldly_jungle"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OTHERWORLDLY_JUNGLE_SONG = register(OTHERWORLDLY_JUNGLE, CMSoundEvents.OTHERWORLDLY_JUNGLE, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OTHERWORLDLY_DUNGEON = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("otherworldly_dungeon"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OTHERWORLDLY_DUNGEON_SONG = register(OTHERWORLDLY_DUNGEON, CMSoundEvents.OTHERWORLDLY_DUNGEON, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OTHERWORLDLY_SPACE = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("otherworldly_space"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OTHERWORLDLY_SPACE_SONG = register(OTHERWORLDLY_SPACE, CMSoundEvents.OTHERWORLDLY_SPACE, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OTHERWORLDLY_ICE = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("otherworldly_ice"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OTHERWORLDLY_ICE_SONG = register(OTHERWORLDLY_ICE, CMSoundEvents.OTHERWORLDLY_ICE, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OTHERWORLDLY_UNDERWORLD = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("otherworldly_underworld"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OTHERWORLDLY_UNDERWORLD_SONG = register(OTHERWORLDLY_UNDERWORLD, CMSoundEvents.OTHERWORLDLY_UNDERWORLD, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OTHERWORLDLY_SNOW = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("otherworldly_snow"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OTHERWORLDLY_SNOW_SONG = register(OTHERWORLDLY_SNOW, CMSoundEvents.OTHERWORLDLY_SNOW, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OTHERWORLDLY_CORRUPTION = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("otherworldly_corruption"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OTHERWORLDLY_CORRUPTION_SONG = register(OTHERWORLDLY_CORRUPTION, CMSoundEvents.OTHERWORLDLY_CORRUPTION, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OTHERWORLDLY_UNDERGROUND_CORRUPTION = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("otherworldly_underground_corruption"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OTHERWORLDLY_UNDERGROUND_CORRUPTION_SONG = register(OTHERWORLDLY_UNDERGROUND_CORRUPTION, CMSoundEvents.OTHERWORLDLY_UNDERGROUND_CORRUPTION, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OTHERWORLDLY_CRIMSON = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("otherworldly_crimson"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OTHERWORLDLY_CRIMSON_SONG = register(OTHERWORLDLY_CRIMSON, CMSoundEvents.OTHERWORLDLY_CRIMSON, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OTHERWORLDLY_UNDERGROUND_CRIMSON = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("otherworldly_underground_crimson"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OTHERWORLDLY_UNDERGROUND_CRIMSON_SONG = register(OTHERWORLDLY_UNDERGROUND_CRIMSON, CMSoundEvents.OTHERWORLDLY_UNDERGROUND_CRIMSON, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OTHERWORLDLY_HALLOW = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("otherworldly_hallow"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OTHERWORLDLY_HALLOW_SONG = register(OTHERWORLDLY_HALLOW, CMSoundEvents.OTHERWORLDLY_HALLOW, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OTHERWORLDLY_UNDERGROUND_HALLOW = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("otherworldly_underground_hallow"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OTHERWORLDLY_UNDERGROUND_HALLOW_SONG = register(OTHERWORLDLY_UNDERGROUND_HALLOW, CMSoundEvents.OTHERWORLDLY_UNDERGROUND_HALLOW, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OTHERWORLDLY_EERIE = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("otherworldly_eerie"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OTHERWORLDLY_EERIE_SONG = register(OTHERWORLDLY_EERIE, CMSoundEvents.OTHERWORLDLY_EERIE, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OTHERWORLDLY_BOSS_1 = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("otherworldly_boss_1"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OTHERWORLDLY_BOSS_1_SONG = register(OTHERWORLDLY_BOSS_1, CMSoundEvents.OTHERWORLDLY_BOSS_1, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OTHERWORLDLY_BOSS_2 = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("otherworldly_boss_2"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OTHERWORLDLY_BOSS_2_SONG = register(OTHERWORLDLY_BOSS_2, CMSoundEvents.OTHERWORLDLY_BOSS_2, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OTHERWORLDLY_INVASION = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("otherworldly_invasion"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OTHERWORLDLY_INVASION_SONG = register(OTHERWORLDLY_INVASION, CMSoundEvents.OTHERWORLDLY_INVASION, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OTHERWORLDLY_THE_TOWERS = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("otherworldly_the_towers"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OTHERWORLDLY_THE_TOWERS_SONG = register(OTHERWORLDLY_THE_TOWERS, CMSoundEvents.OTHERWORLDLY_THE_TOWERS, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OTHERWORLDLY_LUNAR_BOSS = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("otherworldly_lunar_boss"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OTHERWORLDLY_LUNAR_BOSS_SONG = register(OTHERWORLDLY_LUNAR_BOSS, CMSoundEvents.OTHERWORLDLY_LUNAR_BOSS, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OTHERWORLDLY_PLANTERA = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("otherworldly_plantera"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OTHERWORLDLY_PLANTERA_SONG = register(OTHERWORLDLY_PLANTERA, CMSoundEvents.OTHERWORLDLY_PLANTERA, 120.0F, 0);

    public static final ResourceKey<JukeboxSong> OTHERWORLDLY_WALL_OF_FLESH = ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceMusic.asResource("otherworldly_wall_of_flesh"));
    public static final DeferredHolder<JukeboxSong, JukeboxSong> OTHERWORLDLY_WALL_OF_FLESH_SONG = register(OTHERWORLDLY_WALL_OF_FLESH, CMSoundEvents.OTHERWORLDLY_WALL_OF_FLESH, 120.0F, 0);
    public static DeferredHolder<JukeboxSong, JukeboxSong> register(ResourceKey<JukeboxSong> key, Holder<SoundEvent> soundEvent, float lengthInSeconds, int comparatorOutput) {
        return SONGS.register(key.location().getPath(), () -> new JukeboxSong(soundEvent, Component.translatable(Util.makeDescriptionId("jukebox_song", key.location())), lengthInSeconds, comparatorOutput));
    }
}
