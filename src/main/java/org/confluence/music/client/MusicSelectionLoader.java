package org.confluence.music.client;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.confluence.music.ConfluenceMusic;
import org.confluence.music.common.init.CMMusics;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class MusicSelectionLoader extends SimpleJsonResourceReloadListener {
    public static final Codec<EnumMap<MusicSelection, List<CMMusics.CachedLocationMusic>>> CODEC = Codec.unboundedMap(MusicSelection.CODEC, CMMusics.CachedLocationMusic.CODEC.listOf()).xmap(EnumMap::new, Function.identity());
    private static MusicSelectionLoader INSTANCE;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private Map<ResourceLocation, Map<MusicSelection, List<CMMusics.CachedLocationMusic>>> registeredMusicSelections = ImmutableMap.of(); // <类型: <选项: [音乐,]>>

    public MusicSelectionLoader() {
        super(GSON, "music_selection");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> map, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        ImmutableMap.Builder<ResourceLocation, Map<MusicSelection, List<CMMusics.CachedLocationMusic>>> builder = ImmutableMap.builder();
        for (Map.Entry<ResourceLocation, JsonElement> entry : map.entrySet()) {
            ResourceLocation location = entry.getKey();
            JsonElement json = entry.getValue();
            CODEC.parse(JsonOps.INSTANCE, json)
                    .resultOrPartial(errorMsg -> ConfluenceMusic.LOGGER.warn("Could not decode music selection with json id {} - error: {}", location, errorMsg))
                    .ifPresent(vec2 -> builder.put(location, vec2));
        }
        this.registeredMusicSelections = builder.build();
    }

    public Map<ResourceLocation, Map<MusicSelection, List<CMMusics.CachedLocationMusic>>> getRegisteredMusicSelections() {
        return registeredMusicSelections;
    }

    public static MusicSelectionLoader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MusicSelectionLoader();
        }
        return INSTANCE;
    }
}
