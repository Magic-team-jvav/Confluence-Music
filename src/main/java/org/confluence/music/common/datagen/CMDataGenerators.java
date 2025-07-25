package org.confluence.music.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.confluence.music.ConfluenceMusic;
import org.confluence.music.common.init.CMJukeboxSongs;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = ConfluenceMusic.MODID, bus = EventBusSubscriber.Bus.MOD)
public final class CMDataGenerators {
    private static final RegistrySetBuilder DATA_BUILDER = new RegistrySetBuilder()
            .add(Registries.JUKEBOX_SONG, CMJukeboxSongs::boostrap);

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper helper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookup = event.getLookupProvider();

        boolean client = event.includeClient();
        generator.addProvider(client, new CMLanguageProvider(output, true));
        generator.addProvider(client, new CMLanguageProvider(output, false));
        generator.addProvider(client, new CMItemModelProvider(output, helper));
        generator.addProvider(client, new CMMusicSelectionProvider(output, lookup));

        boolean server = event.includeServer();
        lookup = generator.addProvider(server, new DatapackBuiltinEntriesProvider(output, lookup, DATA_BUILDER, Set.of(ConfluenceMusic.MODID))).getRegistryProvider();
        CMBlockTagsProvider blockTagsProvider = generator.addProvider(server, new CMBlockTagsProvider(output, lookup, helper));
        generator.addProvider(server, new CMItemTagsProvider(output, lookup, blockTagsProvider.contentsGetter(), helper));
        generator.addProvider(server, new CMDataMapProvider(output, lookup));
    }
}
