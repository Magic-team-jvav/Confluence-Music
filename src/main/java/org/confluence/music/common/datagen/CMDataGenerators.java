package org.confluence.music.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.confluence.music.ConfluenceMusic;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = ConfluenceMusic.MODID, bus = EventBusSubscriber.Bus.MOD)
public class CMDataGenerators {
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

        boolean server = event.includeServer();
        CMBlockTagsProvider blockTagsProvider = new CMBlockTagsProvider(output, lookup, helper);
        generator.addProvider(server, blockTagsProvider);
        generator.addProvider(server, new CMItemTagsProvider(output, lookup, blockTagsProvider.contentsGetter(), helper));
        generator.addProvider(server, new CMDataMapProvider(output, lookup));
    }
}
