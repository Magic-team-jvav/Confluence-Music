package org.confluence.music;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.confluence.lib.util.LibUtils;
import org.confluence.mod.common.worldgen.structure.DungeonStructure;
import org.confluence.mod.mixed.IStructureStart;
import org.confluence.music.client.CMClientConfigs;
import org.confluence.music.client.MusicHandler;
import org.confluence.music.common.CMCommonConfigs;
import org.confluence.music.common.init.CMMusics;
import org.confluence.music.common.init.CMSoundEvents;
import org.confluence.music.common.network.StructureFoundPacketS2C;
import org.mesdag.portlib.client.gui.PortConfigurationScreen;
import org.mesdag.portlib.event.PortEventHandler;
import org.mesdag.portlib.event.network.PortRegisterPayloadHandlersEvent;
import org.mesdag.portlib.network.PortPacketDistributor;
import org.mesdag.portlib.network.PortPayloadHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(ConfluenceMusic.MODID)
public class ConfluenceMusic {
    public static final String MODID = "confluence_music";
    public static final Logger LOGGER = LoggerFactory.getLogger("ConfluenceMusic");

    /// 玩家持久化数据里记录“当前所在地牢分层”的键。
    private static final String STRUCTURE_FOUND_KEY = "confluence_music:structure_found";

    public ConfluenceMusic(FMLJavaModLoadingContext context) {
        IEventBus eventBus = context.getModEventBus();
        CMCommonConfigs.register(context);
        if (LibUtils.isPhysicalClient()) {
            CMClientConfigs.register(context);
            context.registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((minecraft, parent) -> new PortConfigurationScreen(ModList.get().getModContainerById(MODID).orElseThrow(), parent)));
            PortEventHandler.addListener(MusicHandler::registerClientReloadListeners);
            eventBus.addListener(MusicHandler::modConfig$Loading);
            eventBus.addListener(MusicHandler::modConfig$Reloading);
        }
        CMSoundEvents.EVENTS.register(eventBus);
        CMMusics.init();
        PortEventHandler.addListener(ConfluenceMusic::registerPayloadHandlers);
        eventBus.addListener(ConfluenceMusic::modConfig$Loading);
        eventBus.addListener(ConfluenceMusic::modConfig$Reloading);
    }

    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    private static void registerPayloadHandlers(PortRegisterPayloadHandlersEvent event) {
        PortPayloadHandler handler = event.registrar("1");
        handler.registerInGameS2C(StructureFoundPacketS2C.class, StructureFoundPacketS2C.ID, StructureFoundPacketS2C.STREAM_CODEC);
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

    @Mod.EventBusSubscriber(modid = MODID)
    public static final class Events {
        @SubscribeEvent
        public static void playerTick(TickEvent.PlayerTickEvent event) {
            if (event.phase != TickEvent.Phase.END) return;
            if (!(event.player instanceof ServerPlayer player)) return;
            if (player.level().getGameTime() % CMCommonConfigs.checkInterval != 0) return;
            CompoundTag data = player.getPersistentData();
            byte b = data.getByte(STRUCTURE_FOUND_KEY);
            boolean notFound = !DungeonStructure.iterateDungeon(player.serverLevel(), player.chunkPosition(), structureStart -> {
                IStructureStart start = IStructureStart.of(structureStart);
                BoundingBox boundingBox = start.confluence$cachedBoundingBox();
                if (boundingBox.isInside(player.blockPosition())) {
                    byte found = StructureFoundPacketS2C.NOT_FOUND;
                    if (player.getY() <= boundingBox.minY() + DungeonStructure.getUpperBoundsFloor2()) {
                        found = StructureFoundPacketS2C.DUNGEON_FLOOR_2;
                    } else if (player.getY() <= boundingBox.minY() + DungeonStructure.getUpperBoundsFloor1()) {
                        found = StructureFoundPacketS2C.DUNGEON_FLOOR_1;
                    }
                    if (found != b) {
                        PortPacketDistributor.sendToPlayer(player, new StructureFoundPacketS2C(found));
                        data.putByte(STRUCTURE_FOUND_KEY, found);
                    }
                    return true;
                }
                return false;
            });
            if (notFound && b != StructureFoundPacketS2C.NOT_FOUND) {
                PortPacketDistributor.sendToPlayer(player, new StructureFoundPacketS2C(StructureFoundPacketS2C.NOT_FOUND));
                data.putByte(STRUCTURE_FOUND_KEY, StructureFoundPacketS2C.NOT_FOUND);
            }
        }

        @SubscribeEvent
        public static void playerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
            event.getEntity().getPersistentData().putByte(STRUCTURE_FOUND_KEY, StructureFoundPacketS2C.NOT_FOUND);
        }
    }
}
