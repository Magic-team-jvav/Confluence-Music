package org.confluence.music.client;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.confluence.music.ConfluenceMusic;
import org.confluence.music.common.init.CMMusics;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/// 载入数据包里的 `assets/<modid>/music_selection/*.json`。
///
/// 每份 JSON 对应一种“音乐类型”，内容是“音乐种类 → 曲目列表”。
public class MusicSelectionLoader extends SimpleJsonResourceReloadListener {
    public static final Codec<EnumMap<MusicSelection, List<CMMusics.CachedLocationMusic>>> CODEC =
            Codec.unboundedMap(MusicSelection.CODEC, CMMusics.CachedLocationMusic.CODEC.listOf()).xmap(EnumMap::new, Function.identity());
    private static volatile MusicSelectionLoader INSTANCE;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    /// <类型: <选项: \[音乐,\]>>，在资源重载线程写入、在客户端刻读取，故为 volatile。
    private volatile Map<ResourceLocation, Map<MusicSelection, List<CMMusics.CachedLocationMusic>>> registeredMusicSelections = ImmutableMap.of();

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
