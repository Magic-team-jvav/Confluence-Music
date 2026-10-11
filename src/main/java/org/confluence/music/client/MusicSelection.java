package org.confluence.music.client;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

import java.util.Locale;

/// 音乐种类。数据包里的 `music_selection/*.json` 用这里的名字作为键。
public enum MusicSelection implements StringRepresentable {
    OVERWORLD_DAY,
    OVERWORLD_NIGHT,
    JOURNEYS_BEGINNING,
    JOURNEYS_END,
    TITLE,
    DAY_REMIX,
    CREATIVE_MODE,

    SNOW,
    ICE,
    DESERT,
    UNDERGROUND_DESERT,
    OCEAN_DAY,
    OCEAN_NIGHT,
    SPACE_DAY,
    SPACE_NIGHT,
    UNDERGROUND,
    MUSHROOMS,
    JUNGLE_DAY,
    JUNGLE_NIGHT,
    UNDERGROUND_JUNGLE,
    THE_CORRUPTION,
    UNDERGROUND_CORRUPTION,
    THE_CRIMSON,
    UNDERGROUND_CRIMSON,
    THE_HALLOW_DAY,
    THE_HALLOW_NIGHT,
    UNDERGROUND_HALLOW,
    UNDERWORLD,
    DUNGEON_FLOOR_1,
    DUNGEON_FLOOR_2,
    TEMPLE,
    AETHER,
    METEORITE,
    GRAVEYARD,
    TOWN_DAY,
    TOWN_NIGHT,
    THE_END,

    KING_SLIME,
    EYE_OF_CTHULHU,
    EATER_OF_WORLDS,
    BRAIN_OF_CTHULHU_PHASE_1, // 克苏鲁之脑第一阶段
    BRAIN_OF_CTHULHU_PHASE_2, // 克苏鲁之脑第二阶段
    QUEEN_BEE,
    DEERCLOPS,
    SKELETRON,
    WALL_OF_FLESH,
    QUEEN_SLIME,
    THE_TWINS,
    THE_DESTROYER,
    SKELETRON_PRIME,
    PLANTERA,
    GOLEM,
    DUKE_FISHRON,
    EMPRESS_OF_LIGHT,
    LUNATIC_CULTIST, // 拜月教徒
    MOON_LORD,

    BLOOD_MOON,
    ECLIPSE,
    GOBLIN_ARMY,
    PIRATE_INVASION,
    MARTIAN_MADNESS,
    PUMPKIN_MOON,
    FROST_MOON,
    LUNAR_EVENTS,
    SANDSTORM,
    BLIZZARD,
    OLD_ONES_ARMY, // 旧日军团
    SLIME_RAIN,
    WINDY_DAY,
    TORCH_GOD,
    FROST_LEGION, // 雪人军团
    RAIN_DAY,
    RAIN_NIGHT,
    MORNING_RAIN,
    STORM,
    ;

    public static final Codec<MusicSelection> CODEC = StringRepresentable.fromEnum(MusicSelection::values);

    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
