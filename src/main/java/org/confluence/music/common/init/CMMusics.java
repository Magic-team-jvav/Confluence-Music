package org.confluence.music.common.init;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.StringRepresentable;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.music.ConfluenceMusic;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public final class CMMusics {
    public static final DeferredRegister<CachedLocationMusic> MUSICS = DeferredRegister.create(ConfluenceMusic.CACHED_LOCATION_MUSIC_KEY, ConfluenceMusic.MODID);

    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OTHERWORLD = register(CMSoundEvents.NEW_JOURNEY, Type.COMMON);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OVERWORLD_DAY = register(CMSoundEvents.OVERWORLD_DAY, Type.COMMON);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> ALT_OVERWORLD_DAY = register(CMSoundEvents.ALT_OVERWORLD_DAY, Type.COMMON);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OVERWORLD_NIGHT = register(CMSoundEvents.OVERWORLD_NIGHT, Type.COMMON);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> ALT_OVERWORLD_NIGHT = register(CMSoundEvents.ALT_OVERWORLD_NIGHT, Type.COMMON);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> JOURNEYS_BEGINNING = register(CMSoundEvents.JOURNEYS_BEGINNING, Type.COMMON);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> JOURNEYS_END = register(CMSoundEvents.JOURNEYS_END, Type.COMMON);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> TITLE = register(CMSoundEvents.TITLE, Type.COMMON);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> ALT_TITLE = register(CMSoundEvents.ALT_TITLE, Type.COMMON);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> DAY_REMIX = register(CMSoundEvents.DAY_REMIX, Type.COMMON);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> CREATIVE_MODE = register(CMSoundEvents.CREATIVE_MODE, Type.COMMON);

    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> SNOW = register(CMSoundEvents.SNOW, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> ICE = register(CMSoundEvents.ICE, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> UNDERGROUND_ICE = register(CMSoundEvents.UNDERGROUND_ICE, Type.LOCATION); // 没有八音盒
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> DESERT = register(CMSoundEvents.DESERT, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> UNDERGROUND_DESERT = register(CMSoundEvents.UNDERGROUND_DESERT, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OCEAN_DAY = register(CMSoundEvents.OCEAN_DAY, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OCEAN_NIGHT = register(CMSoundEvents.OCEAN_NIGHT, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> SPACE_DAY = register(CMSoundEvents.SPACE_DAY, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> SPACE_NIGHT = register(CMSoundEvents.SPACE_NIGHT, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> UNDERGROUND = register(CMSoundEvents.UNDERGROUND, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> ALT_UNDERGROUND = register(CMSoundEvents.ALT_UNDERGROUND, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> MUSHROOMS = register(CMSoundEvents.MUSHROOMS, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> JUNGLE_DAY = register(CMSoundEvents.JUNGLE_DAY, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> JUNGLE_NIGHT = register(CMSoundEvents.JUNGLE_NIGHT, Type.LOCATION); // 没有八音盒
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> UNDERGROUND_JUNGLE = register(CMSoundEvents.UNDERGROUND_JUNGLE, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> THE_CORRUPTION = register(CMSoundEvents.THE_CORRUPTION, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> UNDERGROUND_CORRUPTION = register(CMSoundEvents.UNDERGROUND_CORRUPTION, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> THE_CRIMSON = register(CMSoundEvents.THE_CRIMSON, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> UNDERGROUND_CRIMSON = register(CMSoundEvents.UNDERGROUND_CRIMSON, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> THE_HALLOW_DAY = register(CMSoundEvents.THE_HALLOW_DAY, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> UNDERGROUND_HALLOW = register(CMSoundEvents.UNDERGROUND_HALLOW, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> UNDERWORLD = register(CMSoundEvents.UNDERWORLD, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> TEMPLE = register(CMSoundEvents.TEMPLE, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> AETHER = register(CMSoundEvents.AETHER, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> GRAVEYARD = register(CMSoundEvents.GRAVEYARD, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> TOWN_DAY = register(CMSoundEvents.TOWN_DAY, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> TOWN_NIGHT = register(CMSoundEvents.TOWN_NIGHT, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> DUNGEON_FLOOR_1 = register(CMSoundEvents.DUNGEON_FLOOR_1, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> DUNGEON_FLOOR_2 = register(CMSoundEvents.DUNGEON_FLOOR_2, Type.LOCATION);

    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> BOSS_1 = register(CMSoundEvents.BOSS_1, Type.BOSS);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> BOSS_2 = register(CMSoundEvents.BOSS_2, Type.BOSS);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> BOSS_3 = register(CMSoundEvents.BOSS_3, Type.BOSS);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> BOSS_4 = register(CMSoundEvents.BOSS_4, Type.BOSS);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> BOSS_5 = register(CMSoundEvents.BOSS_5, Type.BOSS);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> DEERCLOPS = register(CMSoundEvents.DEERCLOPS, Type.BOSS);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> WALL_OF_FLESH = register(CMSoundEvents.WALL_OF_FLESH, Type.BOSS);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> QUEEN_SLIME = register(CMSoundEvents.QUEEN_SLIME, Type.BOSS);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> PLANTERA = register(CMSoundEvents.PLANTERA, Type.BOSS);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> EMPRESS_OF_LIGHT = register(CMSoundEvents.EMPRESS_OF_LIGHT, Type.BOSS);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> DUKE_FISHRON = register(CMSoundEvents.DUKE_FISHRON, Type.BOSS);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> MOON_LORD = register(CMSoundEvents.MOON_LORD, Type.BOSS);

    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> EERIE = register(CMSoundEvents.EERIE, Type.EVENT);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> ECLIPSE = register(CMSoundEvents.ECLIPSE, Type.EVENT);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> GOBLIN_ARMY = register(CMSoundEvents.GOBLIN_ARMY, Type.EVENT);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> PIRATE_INVASION = register(CMSoundEvents.PIRATE_INVASION, Type.EVENT);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> MARTIAN_MADNESS = register(CMSoundEvents.MARTIAN_MADNESS, Type.EVENT);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> PUMPKIN_MOON = register(CMSoundEvents.PUMPKIN_MOON, Type.EVENT);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> FROST_MOON = register(CMSoundEvents.FROST_MOON, Type.EVENT);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> THE_TOWERS = register(CMSoundEvents.THE_TOWERS, Type.EVENT);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> SANDSTORM = register(CMSoundEvents.SANDSTORM, Type.EVENT);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OLD_ONES_ARMY = register(CMSoundEvents.OLD_ONES_ARMY, Type.EVENT);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> SLIME_RAIN = register(CMSoundEvents.SLIME_RAIN, Type.EVENT);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> WINDY_DAY = register(CMSoundEvents.WINDY_DAY, Type.EVENT);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> RAIN = register(CMSoundEvents.RAIN, Type.EVENT);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> MORNING_RAIN = register(CMSoundEvents.MORNING_RAIN, Type.EVENT);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> STORM = register(CMSoundEvents.STORM, Type.EVENT);

    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OTHERWORLDLY_RAIN = register(CMSoundEvents.OTHERWORLDLY_RAIN, Type.COMMON);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OTHERWORLDLY_OVERWORLD_DAY = register(CMSoundEvents.OTHERWORLDLY_OVERWORLD_DAY, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OTHERWORLDLY_NIGHT = register(CMSoundEvents.OTHERWORLDLY_NIGHT, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OTHERWORLDLY_UNDERGROUND = register(CMSoundEvents.OTHERWORLDLY_UNDERGROUND, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OTHERWORLDLY_DESERT = register(CMSoundEvents.OTHERWORLDLY_DESERT, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OTHERWORLDLY_OCEAN = register(CMSoundEvents.OTHERWORLDLY_OCEAN, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OTHERWORLDLY_MUSHROOMS = register(CMSoundEvents.OTHERWORLDLY_MUSHROOMS, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OTHERWORLDLY_JUNGLE = register(CMSoundEvents.OTHERWORLDLY_JUNGLE, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OTHERWORLDLY_DUNGEON = register(CMSoundEvents.OTHERWORLDLY_DUNGEON, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OTHERWORLDLY_SPACE = register(CMSoundEvents.OTHERWORLDLY_SPACE, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OTHERWORLDLY_ICE = register(CMSoundEvents.OTHERWORLDLY_ICE, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OTHERWORLDLY_UNDERWORLD = register(CMSoundEvents.OTHERWORLDLY_UNDERWORLD, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OTHERWORLDLY_SNOW = register(CMSoundEvents.OTHERWORLDLY_SNOW, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OTHERWORLDLY_CORRUPTION = register(CMSoundEvents.OTHERWORLDLY_CORRUPTION, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OTHERWORLDLY_UNDERGROUND_CORRUPTION = register(CMSoundEvents.OTHERWORLDLY_UNDERGROUND_CORRUPTION, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OTHERWORLDLY_CRIMSON = register(CMSoundEvents.OTHERWORLDLY_CRIMSON, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OTHERWORLDLY_UNDERGROUND_CRIMSON = register(CMSoundEvents.OTHERWORLDLY_UNDERGROUND_CRIMSON, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OTHERWORLDLY_HALLOW = register(CMSoundEvents.OTHERWORLDLY_HALLOW, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OTHERWORLDLY_UNDERGROUND_HALLOW = register(CMSoundEvents.OTHERWORLDLY_UNDERGROUND_HALLOW, Type.LOCATION);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OTHERWORLDLY_EERIE = register(CMSoundEvents.OTHERWORLDLY_EERIE, Type.EVENT);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OTHERWORLDLY_BOSS_1 = register(CMSoundEvents.OTHERWORLDLY_BOSS_1, Type.BOSS);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OTHERWORLDLY_BOSS_2 = register(CMSoundEvents.OTHERWORLDLY_BOSS_2, Type.BOSS);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OTHERWORLDLY_INVASION = register(CMSoundEvents.OTHERWORLDLY_INVASION, Type.EVENT);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OTHERWORLDLY_THE_TOWERS = register(CMSoundEvents.OTHERWORLDLY_THE_TOWERS, Type.EVENT);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OTHERWORLDLY_LUNAR_BOSS = register(CMSoundEvents.OTHERWORLDLY_LUNAR_BOSS, Type.BOSS);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OTHERWORLDLY_PLANTERA = register(CMSoundEvents.OTHERWORLDLY_PLANTERA, Type.BOSS);
    public static final DeferredHolder<CachedLocationMusic, CachedLocationMusic> OTHERWORLDLY_WALL_OF_FLESH = register(CMSoundEvents.OTHERWORLDLY_WALL_OF_FLESH, Type.BOSS);

    private static DeferredHolder<CachedLocationMusic, CachedLocationMusic> register(DeferredHolder<SoundEvent, SoundEvent> sound, Type type) {
        return MUSICS.register(sound.getId().getPath(), () -> new CachedLocationMusic(sound, type));
    }

    public static class CachedLocationMusic extends Music {
        public static final Codec<CachedLocationMusic> CODEC = ConfluenceMusic.CACHED_LOCATION_MUSIC.byNameCodec();
        private ResourceLocation location;
        private final Type type;

        public CachedLocationMusic(Holder<SoundEvent> event, Type type) {
            super(event, 0, 0, true);
            this.type = type;
        }

        public ResourceLocation getLocation() {
            if (location == null) {
                this.location = getEvent().value().getLocation();
            }
            return location;
        }

        public Type getType() {
            return type;
        }
    }

    public enum Type implements StringRepresentable {
        COMMON,
        LOCATION,
        BOSS,
        EVENT;

        public static final Codec<Type> CODEC = StringRepresentable.fromEnum(Type::values);

        @Override
        public @NotNull String getSerializedName() {
            return name().toLowerCase(Locale.ROOT);
        }
    }
}
