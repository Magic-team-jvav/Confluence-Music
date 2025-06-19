package org.confluence.music;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforgespi.locating.IModFile;
import org.confluence.lib.util.ConfluenceResources;
import org.confluence.music.client.CMClientConfigs;
import org.confluence.music.common.init.CMBlocks;
import org.confluence.music.common.init.CMItems;
import org.confluence.music.common.init.CMJukeboxSongs;
import org.confluence.music.common.init.CMSoundEvents;
import org.confluence.music.common.item.MusicBoxItem;
import org.confluence.music.common.network.ReplaceMusicBoxItemPacketC2S;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Mod(ConfluenceMusic.MODID)
public class ConfluenceMusic {
    public static final String MODID = "confluence_music";
    public static final Logger LOGGER = LoggerFactory.getLogger("ConfluenceMusic");

    public ConfluenceMusic(IEventBus eventBus, ModContainer container) {
        if (FMLEnvironment.dist.isClient()) {
            CMClientConfigs.register(container);
            container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        }
        CMBlocks.BLOCKS.register(eventBus);
        CMItems.ITEMS.register(eventBus);
        CMJukeboxSongs.SONGS.register(eventBus);
        CMSoundEvents.EVENTS.register(eventBus);
        eventBus.addListener(ConfluenceMusic::registerPayloadHandlers);
        eventBus.addListener(ConfluenceMusic::loadComplete);
    }

    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    private static void registerPayloadHandlers(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToServer(ReplaceMusicBoxItemPacketC2S.TYPE, ReplaceMusicBoxItemPacketC2S.STREAM_CODEC, ReplaceMusicBoxItemPacketC2S::handle);
    }

    private static void loadComplete(FMLLoadCompleteEvent event) {
        event.enqueueWork(MusicBoxItem::initialize);
    }

    private static void addPackFinders(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            IModFile modFile = ModList.get().getModFileById(MODID).getFile();
            event.addRepositorySource(consumer -> {
                Pack pack = Pack.readMetaAndCreate(
                        new PackLocationInfo("confluence:otherworldly_music", Component.translatable("resourcepack.otherworldly_music"), PackSource.BUILT_IN, Optional.empty()),
                        new ConfluenceResources(modFile, "resourcepacks/otherworldly_music"),
                        PackType.CLIENT_RESOURCES,
                        new PackSelectionConfig(false, Pack.Position.TOP, false)
                );
                if (pack != null) consumer.accept(pack);
            });
        }
    }
}
