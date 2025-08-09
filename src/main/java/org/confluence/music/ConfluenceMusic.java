package org.confluence.music;

import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;
import org.confluence.lib.util.LibUtils;
import org.confluence.mod.common.init.ModTabs;
import org.confluence.mod.common.worldgen.structure.DungeonStructure;
import org.confluence.mod.mixed.IStructureStart;
import org.confluence.music.client.CMClientConfigs;
import org.confluence.music.client.MusicHandler;
import org.confluence.music.common.CMCommonConfigs;
import org.confluence.music.common.init.CMBlocks;
import org.confluence.music.common.init.CMItems;
import org.confluence.music.common.init.CMMusics;
import org.confluence.music.common.init.CMSoundEvents;
import org.confluence.music.common.item.MusicBoxItem;
import org.confluence.music.common.network.ReplaceMusicBoxItemPacketC2S;
import org.confluence.music.common.network.StructureFoundPacketS2C;
import org.confluence.terra_curio.common.init.TCTabs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(ConfluenceMusic.MODID)
public class ConfluenceMusic {
    public static final String MODID = "confluence_music";
    public static final Logger LOGGER = LoggerFactory.getLogger("ConfluenceMusic");

    public static final ResourceKey<Registry<CMMusics.CachedLocationMusic>> CACHED_LOCATION_MUSIC_KEY = ResourceKey.createRegistryKey(asResource("cached_location_music"));
    public static final Registry<CMMusics.CachedLocationMusic> CACHED_LOCATION_MUSIC = new RegistryBuilder<>(CACHED_LOCATION_MUSIC_KEY).sync(true).create();

    public ConfluenceMusic(IEventBus eventBus, ModContainer container) {
        CMCommonConfigs.register(container);
        if (FMLEnvironment.dist.isClient()) {
            CMClientConfigs.register(container);
            container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
            eventBus.addListener(MusicHandler::registerClientReloadListeners);
            eventBus.addListener(MusicHandler::modConfig$Loading);
            eventBus.addListener(MusicHandler::modConfig$Reloading);
        }
        CMBlocks.BLOCKS.register(eventBus);
        CMBlocks.BLOCK_ENTITIES.register(eventBus);
        CMItems.ITEMS.register(eventBus);
        CMSoundEvents.EVENTS.register(eventBus);
        CMMusics.MUSICS.register(eventBus);
        eventBus.addListener(ConfluenceMusic::registerPayloadHandlers);
        eventBus.addListener(ConfluenceMusic::loadComplete);
        eventBus.addListener(ConfluenceMusic::buildCreativeModeTabContents);
        eventBus.addListener(ConfluenceMusic::modConfig$Loading);
        eventBus.addListener(ConfluenceMusic::modConfig$Reloading);
        eventBus.addListener(ConfluenceMusic::newRegistry);
    }

    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    private static void registerPayloadHandlers(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToServer(ReplaceMusicBoxItemPacketC2S.TYPE, ReplaceMusicBoxItemPacketC2S.STREAM_CODEC, ReplaceMusicBoxItemPacketC2S::handle);
        registrar.playToClient(StructureFoundPacketS2C.TYPE, StructureFoundPacketS2C.STREAM_CODEC, StructureFoundPacketS2C::handle);
    }

    private static void loadComplete(FMLLoadCompleteEvent event) {
        event.enqueueWork(MusicBoxItem::initialize);
    }

    private static void buildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == TCTabs.ACCESSORIES.get()) {
            CMBlocks.BLOCKS.getEntries().forEach(block -> event.accept(block.get()));
        } else if (event.getTab() == ModTabs.MISC.get()) {
            CMItems.ITEMS.getEntries().forEach(item -> {
                if (!(item.get() instanceof BlockItem)) {
                    event.accept(item.get());
                }
            });
        }
    }

    private static void modConfig$Loading(ModConfigEvent.Loading event) {
        if (event.getConfig().getType() == ModConfig.Type.COMMON && MODID.equals(event.getConfig().getModId())) {
            CMCommonConfigs.onLoad();
        }
    }

    private static void modConfig$Reloading(ModConfigEvent.Reloading event) {
        if (event.getConfig().getType() == ModConfig.Type.COMMON && MODID.equals(event.getConfig().getModId())) {
            CMCommonConfigs.onLoad();
        }
    }

    private static void newRegistry(NewRegistryEvent event) {
        event.register(CACHED_LOCATION_MUSIC);
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.GAME)
    public static final class Events {
        @SubscribeEvent
        public static void playerTick(PlayerTickEvent.Post event) {
            if (event.getEntity() instanceof ServerPlayer player && player.level().getGameTime() % CMCommonConfigs.checkInterval == 0) {
                CompoundTag data = LibUtils.getOrCreatePersistedData(player);
                byte B = data.getByte("confluence_music:structure_found");
                boolean notFound = !DungeonStructure.iterateDungeon(player.serverLevel(), player.chunkPosition(), structureStart -> {
                    IStructureStart start = IStructureStart.of(structureStart);
                    BoundingBox boundingBox = start.confluence$cachedBoundingBox();
                    if (boundingBox.isInside(player.blockPosition())) {
                        byte b = StructureFoundPacketS2C.NOT_FOUND;
                        if (player.getY() <= boundingBox.minY() + DungeonStructure.getUpperBoundsFloor2()) {
                            b = StructureFoundPacketS2C.DUNGEON_FLOOR_2;
                        } else if (player.getY() <= boundingBox.minY() + DungeonStructure.getUpperBoundsFloor1()) {
                            b = StructureFoundPacketS2C.DUNGEON_FLOOR_1;
                        }
                        if (b != B) {
                            PacketDistributor.sendToPlayer(player, new StructureFoundPacketS2C(b));
                            data.putByte("confluence_music:structure_found", b);
                        }
                        return true;
                    }
                    return false;
                });
                if (notFound && B != StructureFoundPacketS2C.NOT_FOUND) {
                    PacketDistributor.sendToPlayer(player, new StructureFoundPacketS2C(StructureFoundPacketS2C.NOT_FOUND));
                    data.putByte("confluence_music:structure_found", StructureFoundPacketS2C.NOT_FOUND);
                }
            }
        }

        @SubscribeEvent
        public static void playerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
            LibUtils.getOrCreatePersistedData(event.getEntity()).putByte("confluence_music:structure_found", StructureFoundPacketS2C.NOT_FOUND);
        }
    }
}
