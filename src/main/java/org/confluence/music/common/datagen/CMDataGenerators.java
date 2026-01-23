package org.confluence.music.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.confluence.music.ConfluenceMusic;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = ConfluenceMusic.MODID)
public final class CMDataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookup = event.getLookupProvider();

        boolean client = event.includeClient();
        generator.addProvider(client, new CMLanguageProvider(output, true));
        generator.addProvider(client, new CMLanguageProvider(output, false));
        generator.addProvider(client, new CMMusicSelectionProvider(output, lookup));
    }
}
