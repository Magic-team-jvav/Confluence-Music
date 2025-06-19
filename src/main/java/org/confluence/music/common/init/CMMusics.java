package org.confluence.music.common.init;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;

public final class CMMusics {
    public static final CachedLocationMusic OTHERWORLD = register(CMSoundEvents.OTHERWORLD);

    public static final CachedLocationMusic OVERWORLD_DAY = register(CMSoundEvents.OVERWORLD_DAY);

    public static final CachedLocationMusic ALT_OVERWORLD_DAY = register(CMSoundEvents.ALT_OVERWORLD_DAY);
    public static final CachedLocationMusic NIGHT = register(CMSoundEvents.NIGHT);
    public static final CachedLocationMusic RAIN = register(CMSoundEvents.RAIN);
    public static final CachedLocationMusic SNOW = register(CMSoundEvents.SNOW);
    public static final CachedLocationMusic ICE = register(CMSoundEvents.ICE);
    public static final CachedLocationMusic DESERT = register(CMSoundEvents.DESERT);
    public static final CachedLocationMusic OCEAN_DAY = register(CMSoundEvents.OCEAN_DAY);
    public static final CachedLocationMusic OCEAN_NIGHT = register(CMSoundEvents.OCEAN_NIGHT);
    public static final CachedLocationMusic SPACE_DAY = register(CMSoundEvents.SPACE_DAY);
    public static final CachedLocationMusic SPACE_NIGHT = register(CMSoundEvents.SPACE_NIGHT);
    public static final CachedLocationMusic UNDERGROUND = register(CMSoundEvents.UNDERGROUND);
    public static final CachedLocationMusic ALT_UNDERGROUND = register(CMSoundEvents.ALT_UNDERGROUND);
    public static final CachedLocationMusic MUSHROOMS = register(CMSoundEvents.MUSHROOMS);
    public static final CachedLocationMusic JUNGLE = register(CMSoundEvents.JUNGLE);
    public static final CachedLocationMusic CORRUPTION = register(CMSoundEvents.CORRUPTION);
    public static final CachedLocationMusic UNDERGROUND_CORRUPTION = register(CMSoundEvents.UNDERGROUND_CORRUPTION);
    public static final CachedLocationMusic CRIMSON = register(CMSoundEvents.CRIMSON);
    public static final CachedLocationMusic UNDERGROUND_CRIMSON = register(CMSoundEvents.UNDERGROUND_CRIMSON);
    public static final CachedLocationMusic THE_HALLOW = register(CMSoundEvents.THE_HALLOW);
    public static final CachedLocationMusic UNDERGROUND_HALLOW = register(CMSoundEvents.UNDERGROUND_HALLOW);
    public static final CachedLocationMusic HELL = register(CMSoundEvents.HELL);
    public static final CachedLocationMusic DUNGEON = register(CMSoundEvents.DUNGEON);
    public static final CachedLocationMusic TEMPLE = register(CMSoundEvents.TEMPLE);
    public static final CachedLocationMusic AETHER = register(CMSoundEvents.AETHER);

    public static final CachedLocationMusic JOURNEYS_END = register(CMSoundEvents.JOURNEYS_END);

    public static final CachedLocationMusic JOURNEYS_BEGINNING = register(CMSoundEvents.JOURNEYS_BEGINNING);
    public static final CachedLocationMusic TITLE = register(CMSoundEvents.TITLE);
    public static final CachedLocationMusic ALT_TITLE = register(CMSoundEvents.ALT_TITLE);
    public static final CachedLocationMusic DAY_REMIX = register(CMSoundEvents.DAY_REMIX);

    public static final CachedLocationMusic BOSS_1 = register(CMSoundEvents.BOSS_1);
    public static final CachedLocationMusic BOSS_2 = register(CMSoundEvents.BOSS_2);
    public static final CachedLocationMusic BOSS_3 = register(CMSoundEvents.BOSS_3);
    public static final CachedLocationMusic BOSS_4 = register(CMSoundEvents.BOSS_4);
    public static final CachedLocationMusic BOSS_5 = register(CMSoundEvents.BOSS_5);
    public static final CachedLocationMusic DEERCLOPS = register(CMSoundEvents.DEERCLOPS);
    public static final CachedLocationMusic QUEEN_SLIME = register(CMSoundEvents.QUEEN_SLIME);
    public static final CachedLocationMusic PLANTERA = register(CMSoundEvents.PLANTERA);
    public static final CachedLocationMusic EMPRESS_OF_LIGHT = register(CMSoundEvents.EMPRESS_OF_LIGHT);
    public static final CachedLocationMusic DUKE_FISHRON = register(CMSoundEvents.DUKE_FISHRON);
    public static final CachedLocationMusic LUNAR_BOSS = register(CMSoundEvents.LUNAR_BOSS);

    public static final CachedLocationMusic EERIE = register(CMSoundEvents.EERIE);
    public static final CachedLocationMusic ECLIPSE = register(CMSoundEvents.ECLIPSE);
    public static final CachedLocationMusic GOBLIN_INVASION = register(CMSoundEvents.GOBLIN_INVASION);
    public static final CachedLocationMusic PIRATE_INVASION = register(CMSoundEvents.PIRATE_INVASION);
    public static final CachedLocationMusic MARTIAN_MADNESS = register(CMSoundEvents.MARTIAN_MADNESS);
    public static final CachedLocationMusic PUMPKIN_MOON = register(CMSoundEvents.PUMPKIN_MOON);
    public static final CachedLocationMusic FROST_MOON = register(CMSoundEvents.FROST_MOON);
    public static final CachedLocationMusic THE_TOWERS = register(CMSoundEvents.THE_TOWERS);
    public static final CachedLocationMusic SANDSTORM = register(CMSoundEvents.SANDSTORM);
    public static final CachedLocationMusic OLD_ONES_ARMY = register(CMSoundEvents.OLD_ONES_ARMY);
    public static final CachedLocationMusic SLIME_RAIN = register(CMSoundEvents.SLIME_RAIN);
    public static final CachedLocationMusic TOWN_DAY = register(CMSoundEvents.TOWN_DAY);
    public static final CachedLocationMusic TOWN_NIGHT = register(CMSoundEvents.TOWN_NIGHT);
    public static final CachedLocationMusic WINDY_DAY = register(CMSoundEvents.WINDY_DAY);
    public static final CachedLocationMusic STORM = register(CMSoundEvents.STORM);
    public static final CachedLocationMusic GRAVEYARD = register(CMSoundEvents.GRAVEYARD);
    public static final CachedLocationMusic UNDERGROUND_JUNGLE = register(CMSoundEvents.UNDERGROUND_JUNGLE);
    public static final CachedLocationMusic MORNING_RAIN = register(CMSoundEvents.MORNING_RAIN);
    public static final CachedLocationMusic UNDERGROUND_DESERT = register(CMSoundEvents.UNDERGROUND_DESERT);

    public static final CachedLocationMusic OTHERWORLDLY_RAIN = register(CMSoundEvents.OTHERWORLDLY_RAIN);
    public static final CachedLocationMusic OTHERWORLDLY_OVERWORLD_DAY = register(CMSoundEvents.OTHERWORLDLY_OVERWORLD_DAY);
    public static final CachedLocationMusic OTHERWORLDLY_NIGHT = register(CMSoundEvents.OTHERWORLDLY_NIGHT);
    public static final CachedLocationMusic OTHERWORLDLY_UNDERGROUND = register(CMSoundEvents.OTHERWORLDLY_UNDERGROUND);
    public static final CachedLocationMusic OTHERWORLDLY_DESERT = register(CMSoundEvents.OTHERWORLDLY_DESERT);
    public static final CachedLocationMusic OTHERWORLDLY_OCEAN = register(CMSoundEvents.OTHERWORLDLY_OCEAN);
    public static final CachedLocationMusic OTHERWORLDLY_MUSHROOMS = register(CMSoundEvents.OTHERWORLDLY_MUSHROOMS);
    public static final CachedLocationMusic OTHERWORLDLY_DUNGEON = register(CMSoundEvents.OTHERWORLDLY_DUNGEON);
    public static final CachedLocationMusic OTHERWORLDLY_SPACE = register(CMSoundEvents.OTHERWORLDLY_SPACE);
    public static final CachedLocationMusic OTHERWORLDLY_UNDERWORLD = register(CMSoundEvents.OTHERWORLDLY_UNDERWORLD);
    public static final CachedLocationMusic OTHERWORLDLY_SNOW = register(CMSoundEvents.OTHERWORLDLY_SNOW);
    public static final CachedLocationMusic OTHERWORLDLY_CORRUPTION = register(CMSoundEvents.OTHERWORLDLY_CORRUPTION);
    public static final CachedLocationMusic OTHERWORLDLY_UNDERGROUND_CORRUPTION = register(CMSoundEvents.OTHERWORLDLY_UNDERGROUND_CORRUPTION);
    public static final CachedLocationMusic OTHERWORLDLY_CRIMSON = register(CMSoundEvents.OTHERWORLDLY_CRIMSON);
    public static final CachedLocationMusic OTHERWORLDLY_UNDERGROUND_CRIMSON = register(CMSoundEvents.OTHERWORLDLY_UNDERGROUND_CRIMSON);
    public static final CachedLocationMusic OTHERWORLDLY_ICE = register(CMSoundEvents.OTHERWORLDLY_ICE);
    public static final CachedLocationMusic OTHERWORLDLY_UNDERGROUND_HALLOW = register(CMSoundEvents.OTHERWORLDLY_UNDERGROUND_HALLOW);
    public static final CachedLocationMusic OTHERWORLDLY_EERIE = register(CMSoundEvents.OTHERWORLDLY_EERIE);
    public static final CachedLocationMusic OTHERWORLDLY_BOSS_2 = register(CMSoundEvents.OTHERWORLDLY_BOSS_2);
    public static final CachedLocationMusic OTHERWORLDLY_BOSS_1 = register(CMSoundEvents.OTHERWORLDLY_BOSS_1);
    public static final CachedLocationMusic OTHERWORLDLY_INVASION = register(CMSoundEvents.OTHERWORLDLY_INVASION);
    public static final CachedLocationMusic OTHERWORLDLY_THE_TOWERS = register(CMSoundEvents.OTHERWORLDLY_THE_TOWERS);
    public static final CachedLocationMusic OTHERWORLDLY_LUNAR_BOSS = register(CMSoundEvents.OTHERWORLDLY_LUNAR_BOSS);
    public static final CachedLocationMusic OTHERWORLDLY_PLANTERA = register(CMSoundEvents.OTHERWORLDLY_PLANTERA);
    public static final CachedLocationMusic OTHERWORLDLY_JUNGLE = register(CMSoundEvents.OTHERWORLDLY_JUNGLE);
    public static final CachedLocationMusic OTHERWORLDLY_WALL_OF_FLESH = register(CMSoundEvents.OTHERWORLDLY_WALL_OF_FLESH);
    public static final CachedLocationMusic OTHERWORLDLY_WALL_OF_HALLOW = register(CMSoundEvents.OTHERWORLDLY_WALL_OF_HALLOW);

    private static CachedLocationMusic register(Holder<SoundEvent> event) {
        return new CachedLocationMusic(event);
    }

    public static class CachedLocationMusic extends Music {
        public static final Codec<CachedLocationMusic> CODEC = BuiltInRegistries.SOUND_EVENT.holderByNameCodec().xmap(CachedLocationMusic::new, Music::getEvent);
        private ResourceLocation location;

        public CachedLocationMusic(Holder<SoundEvent> event) {
            super(event, 0, 0, true);
        }

        public ResourceLocation getLocation() {
            if (location == null) {
                this.location = getEvent().value().getLocation();
            }
            return location;
        }
    }
}
