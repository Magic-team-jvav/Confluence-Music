package org.confluence.music.common.init;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;

public final class CMMusics {
    public static final CachedLocationMusic KING_SLIME = register(CMSoundEvents.KING_SLIME_COMBAT);
    public static final CachedLocationMusic EYE_OF_CTHULHU = register(CMSoundEvents.EYE_OF_CTHULHU_COMBAT);
    public static final CachedLocationMusic EATER_OF_WORLDS = register(CMSoundEvents.EATER_OF_WORLDS_COMBAT);
    public static final CachedLocationMusic BRAIN_OF_CTHULHU = register(CMSoundEvents.BRAIN_OF_CTHULHU_COMBAT);
    public static final CachedLocationMusic QUEEN_BEE = register(CMSoundEvents.QUEEN_BEE_COMBAT);

    public static final CachedLocationMusic OVERWORLD_DAY = register(CMSoundEvents.OVERWORLD_DAY);
    public static final CachedLocationMusic OVERWORLD_NIGHT = register(CMSoundEvents.OVERWORLD_NIGHT);
    public static final CachedLocationMusic UNDERGROUND = register(CMSoundEvents.UNDERGROUND);
    public static final CachedLocationMusic ALTERNATE_DAY = register(CMSoundEvents.ALTERNATE_DAY); // todo
    public static final CachedLocationMusic ALTERNATE_UNDERGROUND = register(CMSoundEvents.UNDERGROUND); // todo
    public static final CachedLocationMusic DESERT = register(CMSoundEvents.DESERT);
    public static final CachedLocationMusic ICE = register(CMSoundEvents.ICE);
    public static final CachedLocationMusic UNDERGROUND_ICE = register(CMSoundEvents.UNDERGROUND_ICE);
    public static final CachedLocationMusic CORRUPTION = register(CMSoundEvents.CORRUPTION);
    public static final CachedLocationMusic UNDERGROUND_CORRUPTION = register(CMSoundEvents.UNDERGROUND_CORRUPTION);
    public static final CachedLocationMusic CRIMSON = register(CMSoundEvents.CRIMSON);
    public static final CachedLocationMusic UNDERGROUND_CRIMSON = register(CMSoundEvents.UNDERGROUND_CRIMSON);
    public static final CachedLocationMusic HALLOW = register(CMSoundEvents.THE_HALLOW);
    public static final CachedLocationMusic UNDERGROUND_HALLOW = register(CMSoundEvents.UNDERGROUND_HALLOW);
    public static final CachedLocationMusic JUNGLE = register(CMSoundEvents.JUNGLE);
    public static final CachedLocationMusic JUNGLE_NIGHT = register(CMSoundEvents.JUNGLE_NIGHT);
    public static final CachedLocationMusic UNDERGROUND_JUNGLE = register(CMSoundEvents.UNDERGROUND_JUNGLE);
    public static final CachedLocationMusic OCEAN = register(CMSoundEvents.OCEAN);
    public static final CachedLocationMusic OCEAN_NIGHT = register(CMSoundEvents.OCEAN_NIGHT);
    public static final CachedLocationMusic RAIN = register(CMSoundEvents.RAIN);
    public static final CachedLocationMusic MORNING_RAIN = register(CMSoundEvents.MORNING_RAIN);
    public static final CachedLocationMusic STORM = register(CMSoundEvents.STORM);
    public static final CachedLocationMusic SPACE = register(CMSoundEvents.SPACE);
    public static final CachedLocationMusic MUSHROOMS = register(CMSoundEvents.MUSHROOMS);
    public static final CachedLocationMusic UNDERWORLD = register(CMSoundEvents.UNDERWORLD);

    private static CachedLocationMusic register(Holder<SoundEvent> event) {
        return new CachedLocationMusic(event, 0, 0, true);
    }

    public static class CachedLocationMusic extends Music {
        private ResourceLocation location;

        public CachedLocationMusic(Holder<SoundEvent> event, int minDelay, int maxDelay, boolean replaceCurrentMusic) {
            super(event, minDelay, maxDelay, replaceCurrentMusic);
        }

        public ResourceLocation getLocation() {
            if (location == null) {
                this.location = getEvent().value().getLocation();
            }
            return location;
        }
    }
}
