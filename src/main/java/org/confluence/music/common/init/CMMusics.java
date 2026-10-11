package org.confluence.music.common.init;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.StringRepresentable;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public final class CMMusics {
    public static void init() {}

    public static final BiMap<ResourceLocation, CachedLocationMusic> MUSICS = HashBiMap.create();

    public static final CachedLocationMusic NEW_JOURNEY = register(CMSoundEvents.NEW_JOURNEY, Type.COMMON);
    public static final CachedLocationMusic OVERWORLD_DAY = register(CMSoundEvents.OVERWORLD_DAY, Type.COMMON);
    public static final CachedLocationMusic ALT_OVERWORLD_DAY = register(CMSoundEvents.ALT_OVERWORLD_DAY, Type.COMMON);
    public static final CachedLocationMusic OVERWORLD_NIGHT = register(CMSoundEvents.OVERWORLD_NIGHT, Type.COMMON);
    public static final CachedLocationMusic ALT_OVERWORLD_NIGHT = register(CMSoundEvents.ALT_OVERWORLD_NIGHT, Type.COMMON);
    public static final CachedLocationMusic ALT_OVERWORLD_NIGHT_2 = register(CMSoundEvents.ALT_OVERWORLD_NIGHT_2, Type.COMMON);
    public static final CachedLocationMusic JOURNEYS_BEGINNING = register(CMSoundEvents.JOURNEYS_BEGINNING, Type.COMMON);
    public static final CachedLocationMusic JOURNEYS_END = register(CMSoundEvents.JOURNEYS_END, Type.COMMON);
    public static final CachedLocationMusic TITLE = register(CMSoundEvents.TITLE, Type.COMMON);
    public static final CachedLocationMusic ALT_TITLE = register(CMSoundEvents.ALT_TITLE, Type.COMMON);
    public static final CachedLocationMusic DAY_REMIX = register(CMSoundEvents.DAY_REMIX, Type.COMMON);
    public static final CachedLocationMusic CREATIVE_MODE = register(CMSoundEvents.CREATIVE_MODE, Type.COMMON);

    public static final CachedLocationMusic SNOW = register(CMSoundEvents.SNOW, Type.LOCATION);
    public static final CachedLocationMusic ICE = register(CMSoundEvents.ICE, Type.LOCATION);
    public static final CachedLocationMusic DESERT = register(CMSoundEvents.DESERT, Type.LOCATION);
    public static final CachedLocationMusic UNDERGROUND_DESERT = register(CMSoundEvents.UNDERGROUND_DESERT, Type.LOCATION);
    public static final CachedLocationMusic OCEAN_DAY = register(CMSoundEvents.OCEAN_DAY, Type.LOCATION);
    public static final CachedLocationMusic OCEAN_NIGHT = register(CMSoundEvents.OCEAN_NIGHT, Type.LOCATION);
    public static final CachedLocationMusic SPACE_DAY = register(CMSoundEvents.SPACE_DAY, Type.LOCATION);
    public static final CachedLocationMusic SPACE_NIGHT = register(CMSoundEvents.SPACE_NIGHT, Type.LOCATION);
    public static final CachedLocationMusic UNDERGROUND = register(CMSoundEvents.UNDERGROUND, Type.LOCATION);
    public static final CachedLocationMusic ALT_UNDERGROUND = register(CMSoundEvents.ALT_UNDERGROUND, Type.LOCATION);
    public static final CachedLocationMusic MUSHROOMS = register(CMSoundEvents.MUSHROOMS, Type.LOCATION);
    public static final CachedLocationMusic JUNGLE_DAY = register(CMSoundEvents.JUNGLE_DAY, Type.LOCATION);
    public static final CachedLocationMusic JUNGLE_NIGHT = register(CMSoundEvents.JUNGLE_NIGHT, Type.LOCATION); // 没有八音盒
    public static final CachedLocationMusic UNDERGROUND_JUNGLE = register(CMSoundEvents.UNDERGROUND_JUNGLE, Type.LOCATION);
    public static final CachedLocationMusic THE_CORRUPTION = register(CMSoundEvents.THE_CORRUPTION, Type.LOCATION);
    public static final CachedLocationMusic UNDERGROUND_CORRUPTION = register(CMSoundEvents.UNDERGROUND_CORRUPTION, Type.LOCATION);
    public static final CachedLocationMusic THE_CRIMSON = register(CMSoundEvents.THE_CRIMSON, Type.LOCATION);
    public static final CachedLocationMusic UNDERGROUND_CRIMSON = register(CMSoundEvents.UNDERGROUND_CRIMSON, Type.LOCATION);
    public static final CachedLocationMusic THE_HALLOW_DAY = register(CMSoundEvents.THE_HALLOW_DAY, Type.LOCATION);
    public static final CachedLocationMusic UNDERGROUND_HALLOW = register(CMSoundEvents.UNDERGROUND_HALLOW, Type.LOCATION);
    public static final CachedLocationMusic UNDERWORLD = register(CMSoundEvents.UNDERWORLD, Type.LOCATION);
    public static final CachedLocationMusic TEMPLE = register(CMSoundEvents.TEMPLE, Type.LOCATION);
    public static final CachedLocationMusic AETHER = register(CMSoundEvents.AETHER, Type.LOCATION);
    public static final CachedLocationMusic GRAVEYARD = register(CMSoundEvents.GRAVEYARD, Type.LOCATION);
    public static final CachedLocationMusic TOWN_DAY = register(CMSoundEvents.TOWN_DAY, Type.LOCATION);
    public static final CachedLocationMusic TOWN_NIGHT = register(CMSoundEvents.TOWN_NIGHT, Type.LOCATION);
    public static final CachedLocationMusic DUNGEON_FLOOR_1 = register(CMSoundEvents.DUNGEON_FLOOR_1, Type.LOCATION);
    public static final CachedLocationMusic DUNGEON_FLOOR_2 = register(CMSoundEvents.DUNGEON_FLOOR_2, Type.LOCATION);
    public static final CachedLocationMusic THE_END = register(CMSoundEvents.THE_END, Type.LOCATION);

    public static final CachedLocationMusic BOSS_1 = register(CMSoundEvents.BOSS_1, Type.BOSS);
    public static final CachedLocationMusic BOSS_2 = register(CMSoundEvents.BOSS_2, Type.BOSS);
    public static final CachedLocationMusic BOSS_3 = register(CMSoundEvents.BOSS_3, Type.BOSS);
    public static final CachedLocationMusic BOSS_4 = register(CMSoundEvents.BOSS_4, Type.BOSS);
    public static final CachedLocationMusic BOSS_5 = register(CMSoundEvents.BOSS_5, Type.BOSS);
    public static final CachedLocationMusic EATER_OF_WORLDS = register(CMSoundEvents.EATER_OF_WORLDS, Type.BOSS);
    public static final CachedLocationMusic BRAIN_OF_CTHULHU_PHASE_1 = register(CMSoundEvents.BRAIN_OF_CTHULHU_PHASE_1, Type.BOSS);
    public static final CachedLocationMusic BRAIN_OF_CTHULHU_PHASE_2 = register(CMSoundEvents.BRAIN_OF_CTHULHU_PHASE_2, Type.BOSS);
    public static final CachedLocationMusic DEERCLOPS = register(CMSoundEvents.DEERCLOPS, Type.BOSS);
    public static final CachedLocationMusic WALL_OF_FLESH = register(CMSoundEvents.WALL_OF_FLESH, Type.BOSS);
    public static final CachedLocationMusic QUEEN_SLIME = register(CMSoundEvents.QUEEN_SLIME, Type.BOSS);
    public static final CachedLocationMusic PLANTERA = register(CMSoundEvents.PLANTERA, Type.BOSS);
    public static final CachedLocationMusic EMPRESS_OF_LIGHT = register(CMSoundEvents.EMPRESS_OF_LIGHT, Type.BOSS);
    public static final CachedLocationMusic DUKE_FISHRON = register(CMSoundEvents.DUKE_FISHRON, Type.BOSS);
    public static final CachedLocationMusic MOON_LORD = register(CMSoundEvents.MOON_LORD, Type.BOSS);

    public static final CachedLocationMusic EERIE = register(CMSoundEvents.EERIE, Type.EVENT);
    public static final CachedLocationMusic ECLIPSE = register(CMSoundEvents.ECLIPSE, Type.EVENT);
    public static final CachedLocationMusic GOBLIN_ARMY = register(CMSoundEvents.GOBLIN_ARMY, Type.EVENT);
    public static final CachedLocationMusic PIRATE_INVASION = register(CMSoundEvents.PIRATE_INVASION, Type.EVENT);
    public static final CachedLocationMusic MARTIAN_MADNESS = register(CMSoundEvents.MARTIAN_MADNESS, Type.EVENT);
    public static final CachedLocationMusic PUMPKIN_MOON = register(CMSoundEvents.PUMPKIN_MOON, Type.EVENT);
    public static final CachedLocationMusic FROST_MOON = register(CMSoundEvents.FROST_MOON, Type.EVENT);
    public static final CachedLocationMusic THE_TOWERS = register(CMSoundEvents.THE_TOWERS, Type.EVENT);
    public static final CachedLocationMusic SANDSTORM = register(CMSoundEvents.SANDSTORM, Type.EVENT);
    public static final CachedLocationMusic BLIZZARD = register(CMSoundEvents.BLIZZARD, Type.EVENT);
    public static final CachedLocationMusic OLD_ONES_ARMY = register(CMSoundEvents.OLD_ONES_ARMY, Type.EVENT);
    public static final CachedLocationMusic SLIME_RAIN = register(CMSoundEvents.SLIME_RAIN, Type.EVENT);
    public static final CachedLocationMusic WINDY_DAY = register(CMSoundEvents.WINDY_DAY, Type.EVENT);
    public static final CachedLocationMusic RAIN = register(CMSoundEvents.RAIN, Type.EVENT);
    public static final CachedLocationMusic MORNING_RAIN = register(CMSoundEvents.MORNING_RAIN, Type.EVENT);
    public static final CachedLocationMusic STORM = register(CMSoundEvents.STORM, Type.EVENT);

    private static CachedLocationMusic register(DeferredHolder<SoundEvent, SoundEvent> sound, Type type) {
        CachedLocationMusic value = new CachedLocationMusic(sound, type);
        MUSICS.put(sound.getId(), value);
        return value;
    }

    public static class CachedLocationMusic extends Music {
        public static final Codec<CachedLocationMusic> CODEC = ResourceLocation.CODEC.xmap(MUSICS::get, MUSICS.inverse()::get);
        private ResourceLocation location;
        private final Type type;

        public CachedLocationMusic(Holder<SoundEvent> event, Type type) {
            // 延迟全部交给本模组的状态机，因此 minDelay 与 maxDelay 都是 0，且允许直接替换当前曲目
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
