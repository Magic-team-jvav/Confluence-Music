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
    public static final DeferredHolder<JukeboxSong, JukeboxSong> NEW_JOURNEY_SONG = register(ALPHA, SoundEvents.MUSIC_CREDITS, 603.0F, 0);
    public static DeferredHolder<JukeboxSong, JukeboxSong> register(ResourceKey<JukeboxSong> key, Holder<SoundEvent> soundEvent, float lengthInSeconds, int comparatorOutput) {
        return SONGS.register(key.location().getPath(), () -> new JukeboxSong(soundEvent, Component.translatable(Util.makeDescriptionId("jukebox_song", key.location())), lengthInSeconds, comparatorOutput));
    }
}
