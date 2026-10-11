package org.confluence.music;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.confluence.lib.util.LibUtils;
import org.confluence.mod.common.worldgen.structure.DungeonStructure;
import org.confluence.mod.mixed.IStructureStart;
import org.confluence.music.client.CMClientConfigs;
import org.confluence.music.client.MusicHandler;
import org.confluence.music.common.CMCommonConfigs;
import org.confluence.music.common.init.CMMusics;
import org.confluence.music.common.init.CMSoundEvents;
import org.confluence.music.common.network.StructureFoundPacketS2C;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(ConfluenceMusic.MODID)
public class ConfluenceMusic {
    public static final String MODID = "confluence_music";
    public static final Logger LOGGER = LoggerFactory.getLogger("ConfluenceMusic");

    /// 玩家持久化数据里记录“当前所在地牢分层”的键。
    private static final String STRUCTURE_FOUND_KEY = "confluence_music:structure_found";

    public ConfluenceMusic(IEventBus eventBus, ModContainer container) {
        CMCommonConfigs.register(container);
        if (LibUtils.isPhysicalClient()) {
            CMClientConfigs.register(container);
            container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
            eventBus.addListener(MusicHandler::registerClientReloadListeners);
            eventBus.addListener(MusicHandler::modConfig$Loading);
            eventBus.addListener(MusicHandler::modConfig$Reloading);
        }
        CMSoundEvents.EVENTS.register(eventBus);
        CMMusics.init();
        eventBus.addListener(ConfluenceMusic::registerPayloadHandlers);
        eventBus.addListener(ConfluenceMusic::modConfig$Loading);
        eventBus.addListener(ConfluenceMusic::modConfig$Reloading);
    }

    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    private static void registerPayloadHandlers(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToClient(StructureFoundPacketS2C.TYPE, StructureFoundPacketS2C.STREAM_CODEC, StructureFoundPacketS2C::handle);
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

    @EventBusSubscriber(modid = MODID)
    public static final class Events {
        @SubscribeEvent
        public static void playerTick$Post(PlayerTickEvent.Post event) {
            if (!(event.getEntity() instanceof ServerPlayer player)) return;
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
                        PacketDistributor.sendToPlayer(player, new StructureFoundPacketS2C(found));
                        data.putByte(STRUCTURE_FOUND_KEY, found);
                    }
                    return true;
                }
                return false;
            });
            if (notFound && b != StructureFoundPacketS2C.NOT_FOUND) {
                PacketDistributor.sendToPlayer(player, new StructureFoundPacketS2C(StructureFoundPacketS2C.NOT_FOUND));
                data.putByte(STRUCTURE_FOUND_KEY, StructureFoundPacketS2C.NOT_FOUND);
            }
        }

        @SubscribeEvent
        public static void playerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
            event.getEntity().getPersistentData().putByte(STRUCTURE_FOUND_KEY, StructureFoundPacketS2C.NOT_FOUND);
        }
    }
}
