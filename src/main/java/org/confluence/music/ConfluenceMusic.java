package org.confluence.music;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.confluence.music.client.CMClientConfigs;
import org.confluence.music.client.MusicHandler;
import org.confluence.music.common.init.CMBlocks;
import org.confluence.music.common.init.CMItems;
import org.confluence.music.common.init.CMJukeboxSongs;
import org.confluence.music.common.init.CMSoundEvents;
import org.confluence.music.common.item.MusicBoxItem;
import org.confluence.music.common.network.ReplaceMusicBoxItemPacketC2S;
import org.confluence.terra_curio.common.init.TCTabs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(ConfluenceMusic.MODID)
public class ConfluenceMusic {
    public static final String MODID = "confluence_music";
    public static final Logger LOGGER = LoggerFactory.getLogger("ConfluenceMusic");

    public ConfluenceMusic(IEventBus eventBus, ModContainer container) {
        if (FMLEnvironment.dist.isClient()) {
            CMClientConfigs.register(container);
            container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
            eventBus.addListener(MusicHandler::registerClientReloadListeners);
            eventBus.addListener(MusicHandler::clientSetup);
            eventBus.addListener(MusicHandler::modConfig$reloading);
        }
        CMBlocks.BLOCKS.register(eventBus);
        CMItems.ITEMS.register(eventBus);
        CMJukeboxSongs.SONGS.register(eventBus);
        CMSoundEvents.EVENTS.register(eventBus);
        eventBus.addListener(ConfluenceMusic::registerPayloadHandlers);
        eventBus.addListener(ConfluenceMusic::loadComplete);
        eventBus.addListener(ConfluenceMusic::buildCreativeModeTabContents);
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

    private static void buildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == TCTabs.ACCESSORIES.get()) {
            CMBlocks.BLOCKS.getEntries().forEach(block -> event.accept(block.get()));
        }
    }
}
