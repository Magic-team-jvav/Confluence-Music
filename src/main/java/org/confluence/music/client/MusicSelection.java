package org.confluence.music.client;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

import java.util.Locale;

// todo 补充选项
public enum MusicSelection implements StringRepresentable {
    ;

    public static final Codec<MusicSelection> CODEC = StringRepresentable.fromEnum(MusicSelection::values);

    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
