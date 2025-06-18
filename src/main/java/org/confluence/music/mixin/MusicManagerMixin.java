package org.confluence.music.mixin;

import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.MusicManager;
import org.confluence.music.mixed.IMusicManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import javax.annotation.Nullable;

@Mixin(MusicManager.class)
public abstract class MusicManagerMixin implements IMusicManager {
    @Shadow
    @Nullable
    private SoundInstance currentMusic;
    @Unique
    private State confluence$musicBoxOccupied = State.NONE;

    @Override
    public @Nullable SoundInstance confluence$getCurrentMusic() {
        return currentMusic;
    }

    @Override
    public void confluence$setMusicBoxOccupied(State occupied) {
        this.confluence$musicBoxOccupied = occupied;
    }

    @Override
    public State confluence$getMusicBoxOccupied() {
        return confluence$musicBoxOccupied;
    }
}
