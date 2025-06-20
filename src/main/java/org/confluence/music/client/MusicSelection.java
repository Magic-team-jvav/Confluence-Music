package org.confluence.music.client;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import net.neoforged.fml.common.asm.enumextension.ExtensionInfo;
import net.neoforged.fml.common.asm.enumextension.IExtensibleEnum;
import net.neoforged.fml.common.asm.enumextension.NetworkedEnum;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

// todo 补充选项
@NetworkedEnum(NetworkedEnum.NetworkCheck.BIDIRECTIONAL)
public enum MusicSelection implements StringRepresentable, IExtensibleEnum {
    DAY,
    NIGHT,
    RAIN,
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
    JUNGLE,
    UNDERGROUND_JUNGLE,
    CORRUPTION,
    UNDERGROUND_CORRUPTION,
    CRIMSON,
    UNDERGROUND_CRIMSON,
    THE_HALLOW,
    UNDERGROUND_HALLOW,
    HELL,
    DUNGEON,
    TEMPLE,
    AETHER,

    JOURNEYS_END,

    JOURNEYS_BEGINNING,
    TITLE,
    DAY_REMIX,

    KING_SLIME,
    EYE_OF_CTHULHU,
    EATER_OF_WORLDS,
    BRAIN_OF_CTHULHU,
    QUEEN_BEE,
    SKELETRON,
    WALL_OF_FLESH,

    EERIE,
    ECLIPSE,
    GOBLIN_INVASION,
    PIRATE_INVASION,
    MARTIAN_MADNESS,
    PUMPKIN_MOON,
    FROST_MOON,
    THE_TOWERS,
    SANDSTORM,
    OLD_ONES_ARMY,
    SLIME_RAIN,
    TOWN_DAY,
    TOWN_NIGHT,
    WINDY_DAY,
    STORM,
    GRAVEYARD,
    MORNING_RAIN,

    /* otherworldly */
    ;

    public static final Codec<MusicSelection> CODEC = StringRepresentable.fromEnum(MusicSelection::values);

    @Override
    public @NotNull String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public static ExtensionInfo getExtensionInfo() {
        return ExtensionInfo.nonExtended(MusicSelection.class);
    }
}
