package org.confluence.music.client;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import org.confluence.music.ConfluenceMusic;
import org.confluence.music.common.init.CMMusics;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.IOException;
import java.io.Reader;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Function;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class MusicSelectionLoader implements PreparableReloadListener {
    private static MusicSelectionLoader INSTANCE;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private static final Codec<EnumMap<MusicSelection, List<CMMusics.CachedLocationMusic>>> CODEC = Codec.unboundedMap(MusicSelection.CODEC, CMMusics.CachedLocationMusic.CODEC.listOf()).xmap(EnumMap::new, Function.identity());
    private Map<ResourceLocation, Map<MusicSelection, List<CMMusics.CachedLocationMusic>>> registeredMusicSelections = ImmutableMap.of(); // <类型: <选项: 音乐>>

    @Override
    public final CompletableFuture<Void> reload(
            PreparableReloadListener.PreparationBarrier stage,
            ResourceManager resourceManager,
            ProfilerFiller preparationsProfiler,
            ProfilerFiller reloadProfiler,
            Executor backgroundExecutor,
            Executor gameExecutor
    ) {
        return CompletableFuture.supplyAsync(
                () -> prepare(resourceManager), backgroundExecutor
        ).thenCompose(stage::wait).thenAcceptAsync(this::apply, gameExecutor);
    }

    protected Map<ResourceLocation, JsonElement> prepare(ResourceManager manager) {
        Map<ResourceLocation, JsonElement> map = new HashMap<>();
        ResourceLocation resourceLocation = ConfluenceMusic.asResource("confluence_music.json");
        for (Resource resource : manager.getResourceStack(resourceLocation)) {
            try (Reader reader = resource.openAsReader()) {
                JsonObject jsonobject = GsonHelper.fromJson(GSON, reader, JsonObject.class);
                for (Map.Entry<String, JsonElement> entry : jsonobject.entrySet()) {
                    ResourceLocation loc = ResourceLocation.parse(entry.getKey());
                    map.put(loc, entry.getValue());
                }
            } catch (RuntimeException | IOException ioexception) {
                ConfluenceMusic.LOGGER.error("Couldn't read music selection {} in resource pack {}", resourceLocation, resource.sourcePackId(), ioexception);
            }
        }
        return map;
    }

    protected void apply(Map<ResourceLocation, JsonElement> resourceList) {
        ImmutableMap.Builder<ResourceLocation, Map<MusicSelection, List<CMMusics.CachedLocationMusic>>> builder = ImmutableMap.builder();
        for (Map.Entry<ResourceLocation, JsonElement> entry : resourceList.entrySet()) {
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
