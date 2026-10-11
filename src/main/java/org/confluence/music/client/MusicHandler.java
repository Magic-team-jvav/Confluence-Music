package org.confluence.music.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;
import net.neoforged.neoforge.client.event.SelectMusicEvent;
import org.confluence.music.ConfluenceMusic;
import org.confluence.music.common.network.StructureFoundPacketS2C;
import org.jetbrains.annotations.Nullable;

/// 音乐系统的客户端入口。
///
/// 这里只做接线：NeoForge 每客户端刻在 `MusicManager.tick()` 里抛出一次 [SelectMusicEvent]，
/// 我们借它刷新 [MusicEnvironment] 并交给 [MusicPlayer] 推进播放状态。
///
///   - 看世界：[MusicEnvironment]
///   - 选音乐种类：[MusicResolver]
///   - 种类换曲目：[MusicRepository]
///   - 换曲过程：[MusicPlayer]
///
@EventBusSubscriber(modid = ConfluenceMusic.MODID, value = Dist.CLIENT)
public final class MusicHandler {
    public static final ResourceLocation CONFLUENCE = ConfluenceMusic.asResource("confluence");

    private static final MusicEnvironment ENVIRONMENT = new MusicEnvironment();
    private static final MusicPlayer PLAYER = new MusicPlayer();

    private MusicHandler() {}

    @SubscribeEvent
    public static void selectMusic(SelectMusicEvent event) {
        Minecraft minecraft = Minecraft.getInstance();
        @Nullable LocalPlayer player = minecraft.player;
        if (player == null) {
            clear(minecraft);
            return;
        }
        ENVIRONMENT.update(player, minecraft);
        PLAYER.tick(ENVIRONMENT, event, minecraft);
    }

    /// 地牢分层音乐的变更，由 {@link StructureFoundPacketS2C} 触发。
    public static void handleStructure(byte data) {
        ENVIRONMENT.setStructureFound(data);
    }

    /// 本刻末尾给刚换上的曲目补一次“压到 0”，免得它以满音量响出第一帧。
    ///
    /// 这个事件在 `Minecraft.tick()` 里排在 `MusicManager.tick()` 之后，
    /// 所以此刻原版已经为刚交出去的曲目建好了声道，见 [MusicPlayer#applyPendingFadeIn]。
    @SubscribeEvent
    public static void clientTick$Post(ClientTickEvent.Post event) {
        PLAYER.applyPendingFadeIn(Minecraft.getInstance());
    }

    /// 离开世界时清空一切状态。
    public static void clear(Minecraft minecraft) {
        ENVIRONMENT.reset();
        PLAYER.reset(minecraft);
    }

    public static void registerClientReloadListeners(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(MusicSelectionLoader.getInstance());
    }

    public static void modConfig$Loading(ModConfigEvent.Loading event) {
        if (event.getConfig().getType() == ModConfig.Type.CLIENT && ConfluenceMusic.MODID.equals(event.getConfig().getModId())) {
            CMClientConfigs.onLoad();
        }
    }

    public static void modConfig$Reloading(ModConfigEvent.Reloading event) {
        if (event.getConfig().getType() == ModConfig.Type.CLIENT && ConfluenceMusic.MODID.equals(event.getConfig().getModId())) {
            CMClientConfigs.onLoad();
        }
    }
}
