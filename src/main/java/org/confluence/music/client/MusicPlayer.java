package org.confluence.music.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.ChannelAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import org.confluence.music.ConfluenceMusic;
import org.confluence.music.common.init.CMMusics;
import org.confluence.music.common.init.CMMusics.CachedLocationMusic;
import org.jetbrains.annotations.Nullable;
import org.mesdag.portlib.event.client.PortSelectMusicEvent;

import java.util.ArrayList;
import java.util.List;

/// 音乐播放状态机：决定“什么时候换曲、怎么换”。
///
/// ## 换曲：交叉淡化
/// 音乐种类（[MusicSelection]）一变，就在**同一个刻**做三件事：
/// <ol>
///   - 把正在播放的那一首放进“淡出列表”，让它继续出声，并每刻减 [CMClientConfigs#lastSongFadeOutStep]；
///   - 把 `MusicManager.currentMusic` 置空——只让原版“松手”，不是叫它停音；
///   - 把新曲目交给 [PortSelectMusicEvent]，新曲从音量 0 起每刻加同样一个步长。
/// </ol>
/// 于是旧的降到 0 的同时新的正好涨到 1，两首在整段淡化时间里**同时出声**（真正的交叉淡化，
/// 而不是“先淡出到静音、再开始下一首”）。
/// 一轮淡化要走 `lastSongFadeOutTime` 刻，把它配成 0 就退化成硬切。
///
/// 旧的这一首**淡完就放掉声道**；但在它还没淡完（音量还没到 0）的时候如果处境又变回来，
/// 会把所有权交还给它在原地**接着放**（不重置播放位置）。所以：
/// - 群系边界那种来回抖（一个相位短于淡化时长）：靠接回来，不会反复从头放；
/// - 处境稳定超过一轮淡化时长之后再回到某一首：它早就淡完放掉了，于是**从头开始放**（这是刻意的）。
///
/// 为了不在群系边界、太空层高度线这类地方反复换曲，新种类要连续保持
/// [#CHANGE_CONFIRM_TICKS] 刻才会真的换；而“接回来”同样要确认，所以一两个刻的抖动既不会换曲、
/// 也不会把正在淡出的曲目接回来。另外同一时刻只允许有一轮淡化在走，
/// 所以处境换成第三首（白天↔夜晚、进地牢……）时也能开始新一轮，不会被上一轮挡住。
///
/// “新处境要放哪一首”是由 [MusicRepository#pick] **随机抽**出来的：处境变化时抽一次、
/// 一首放完之后再开时再抽一次，抽中的那一首记在 [#picked] 里，整个播放期间都不再变。
/// 所以“想放的是不是正在放的这一首”这个判断是稳定的，不会每刻重抽而横跳。
/// 新处境配的正好还是正在放的这一首（血月与陨石都用 `eerie` 这类）时不折腾，只更新记录。
///
/// 没有新曲目可放（处境变了但没配曲目、或者离开世界）时，就是淡出之后停在静音上，
/// 要不要连原版音乐一起压住由 [CMClientConfigs#denyVanillaMusic] 决定。
///
/// ## 为什么需要它
/// 原版 `MusicManager` 在拿到一首与当前曲目不同的 [net.minecraft.sounds.Music] 时会
/// **硬切**（先 stop 掉当前声道），所以淡化必须由我们自己做；
/// 又因为它同一时刻只认一个 `currentMusic`，想让两首同时出声就得先从它手里接管旧声道
/// （这就是 `MusicManager.f_120179_` 那条访问转换器存在的原因）。
///
/// ## 怎么用到的原版机制
/// - PortLib 把 NeoForge 的 `SelectMusicEvent` 搬成了 [PortSelectMusicEvent]，
/// 由注入到 `MusicManager.tick()` 的 Mixin 每刻触发一次（暂停时除外）；
/// 我们的处理发生在原版这一拍的开头，所以本刻稍后原版就会去 `startPlaying`。
/// - 本模组的 [CachedLocationMusic] 用 `minDelay = maxDelay = 0` 构造，
/// 因此一旦通过事件交出去，`MusicManager` 会立刻开始播放，不会自己等
/// （它的 `nextSongDelay` 每刻都被 `min(…, maxDelay)` 压成 0），新曲也就来得及在同一个刻起来。
/// - 反过来说，**两首之间的间隔只能靠“不给事件设音乐”来实现**：
/// 没设音乐时 `MusicManager` 用的是原版情景音乐，其延迟是 10~20 分钟，
/// 远长于配置的间隔，因此不会插播（若玩家要求禁用原版音乐，还会额外把事件设成 null 彻底压住它）。
/// - 直接写声道音量是安全的：原版 `SoundEngine` 只在 `play()` 时设一次音量，
/// 之后只有“音乐音量滑条被拖动”会重算，普通音乐（不是 `TickableSoundInstance`）不会被每刻覆盖。
public final class MusicPlayer {
    /// 处境变了之后，要连续保持这么多刻才真的换曲。用来滤掉在边界上来回抖的那一两个刻。
    private static final int CHANGE_CONFIRM_TICKS = 10;

    /// 一首正在淡出的旧音乐：声道实例、它属于哪一首曲目/哪个处境，以及当前施加在它上面的音量倍率。
    ///
    /// 只要它**还没淡完**（音量 > 0），处境又变回来时就可以把所有权交还给它、接着往下放；
    /// 音量降到 0 之后立刻放掉声道并从列表里移除，之后再回来就是从头放。
    ///
    /// [#track()] 为 null 表示这一首不是本模组交出去的（比如被我们淡出的原版音乐），
    /// 交还不了所有权。
    private record FadingOut(SoundInstance instance, @Nullable CachedLocationMusic track,
                             @Nullable MusicSelection selection, float volume) {}

    /// 音量倍率，1.0 表示原始音量。淡化只改这个值。
    private float volume = 1.0F;
    /// [#volume] 作用在哪一个声道实例上（正在渐入的那一首）。
    private @Nullable SoundInstance fadedInstance;
    /// 是否正在给 [#fadedInstance] 渐入（刚交出去的那一首从 0 起涨）。
    private boolean fadingIn;
    /// 最近一次交给事件的那一首曲目，用来在本刻末尾认出原版刚为它建好的声道，见 [#applyPendingFadeIn]。
    private @Nullable CachedLocationMusic committed;
    /// 正在淡出的旧声道们；一轮交叉淡化通常只有一首，短促的连续换曲下会叠上第二首。
    private final List<FadingOut> fadingOut = new ArrayList<>(2);
    /// 正在等待确认的新种类，见 [#CHANGE_CONFIRM_TICKS]。
    private @Nullable MusicSelection pendingSelection;
    /// 上面的新种类还需要保持多少刻才算数。
    private int pendingTicks;
    /// 没有音乐在播放时，距离下一首开始还差的刻数。
    private int waitTicks = CMClientConfigs.INITIAL_WAIT;
    /// 正在播放的这一首对应的音乐种类，用来识别“处境变了”。
    private @Nullable MusicSelection lastSelection;
    /// 当前处境下**随机抽中**的那一首；抽中之后就记着，直到处境变了或者这一首放完。
    private @Nullable CachedLocationMusic picked;
    /// [#picked] 属于哪个音乐种类。
    private @Nullable MusicSelection pickedSelection;

    /// 每客户端刻推进一次播放状态。
    public void tick(MusicEnvironment env, PortSelectMusicEvent event, Minecraft minecraft) {
        tickFadingOut(minecraft); // 旧声道的淡出每刻都要推进，不管本刻走的是哪个分支

        @Nullable MusicSelection selection = MusicResolver.resolve(env);
        if (selection != pickedSelection) { // 处境变了：从新处境的曲目里重新随机抽一首
            pickedSelection = selection;
            picked = selection == null ? null : MusicRepository.pick(minecraft.player.getRandom(), selection);
        }
        @Nullable CachedLocationMusic desired = picked;
        @Nullable SoundInstance playing = event.getPlayingMusic();

        if (isPlaying(minecraft, playing)) {
            if (selection != lastSelection) {
                if (sameTrack(desired, playing)) {
                    // 处境变了，但新处境放的还是这一首：只更新记录，音量淡回去
                    pendingSelection = null;
                    lastSelection = selection;
                    waitTicks = CMClientConfigs.nextSongDelay;
                    fadeIn(minecraft, playing);
                } else if (!shouldReplace(playing)) {
                    // 别的模组的音乐一律不打断：音量淡回去，也不推进 lastSelection，
                    // 这样它一放完就会由下面的“没有音乐在播放”分支立刻接手
                    pendingSelection = null;
                    fadeIn(minecraft, playing);
                } else if (confirmChange(selection)) {
                    @Nullable FadingOut back = findResumable(minecraft, selection);
                    if (back != null) {
                        // 处境又变回来了，而那一首还没淡完：接着放，不从头开始
                        resume(minecraft, event, selection, playing, back);
                    } else if (fadingOut.isEmpty()) {
                        // 处境确实换了：旧的一首接着放并开始淡出，新的这一刻就开始淡入
                        crossfade(minecraft, event, selection, desired, playing);
                    }
                    // 剩下一种情况：正有一轮淡化在走，等它走完这一拍再处理
                }
                return;
            }
            // 想听的就是正在放的：把（可能还没涨到头的）音量淡回原始值
            pendingSelection = null;
            fadeIn(minecraft, playing);
            waitTicks = CMClientConfigs.nextSongDelay;
            return;
        }

        // 当前没有音乐在播放
        pendingSelection = null;
        boolean changed = lastSelection != null && lastSelection != selection;
        lastSelection = selection;
        if (desired == null) {
            suppressVanillaMusic(event);
            waitTicks = CMClientConfigs.nextSongDelay;
            return;
        }
        if (changed || desired.getType() == CMMusics.Type.BOSS) {
            waitTicks = 0; // 处境变了（换群系、进 Boss 战……），不必再等间隔
        }
        if (waitTicks-- > 0) {
            suppressVanillaMusic(event); // 等待期间也别让原版音乐插进来
            return;
        }
        // 上一首已经放完了：同一次处境里再开一首就是重新随机抽一首
        desired = MusicRepository.pick(minecraft.player.getRandom(), selection);
        picked = desired;
        commit(event, selection, desired);
    }

    /// 清空全部状态，用于离开世界。
    public void reset(Minecraft minecraft) {
        resetFade();
        for (FadingOut fading : fadingOut) {
            minecraft.getSoundManager().stop(fading.instance()); // 别让旧声道留在世界里继续响
        }
        fadingOut.clear();
        pendingSelection = null;
        lastSelection = null;
        picked = null;
        pickedSelection = null;
        waitTicks = CMClientConfigs.INITIAL_WAIT;
    }

    /// 一轮交叉淡化：旧的那一首继续出声并开始降音量，**同一个刻**把新曲目交给事件立刻开始播放。
    ///
    /// 关键在于先把 `MusicManager.currentMusic` 置空：原版一旦发现事件给的曲目和正在放的不是同一首，
    /// 第一件事就是 `stop` 掉旧声道，那样就没有东西可以淡出了。置空之后原版只是不再认领这一首，
    /// 旧声道照旧出声（音量由 [#tickFadingOut] 接管），而新曲目也正因为 `currentMusic == null`
    /// 会在同一个刻被原版开始播放。
    private void crossfade(Minecraft minecraft, PortSelectMusicEvent event, @Nullable MusicSelection selection,
                           @Nullable CachedLocationMusic desired, SoundInstance outgoing) {
        float from = fadedInstance == outgoing ? volume : 1.0F; // 如果它本来就还在渐入，就从当前音量接着降
        // 只有确实是本模组交出去的那一首，才记得住它的曲目、将来能被接回来
        fadingOut.add(new FadingOut(outgoing, sameTrack(committed, outgoing) ? committed : null, lastSelection, from));
        minecraft.getMusicManager().currentMusic = null;
        commit(event, selection, desired);
    }

    /// {@return 淡出列表里属于 `selection`、还没淡完而且还在出声的那一首（因此可以接着放而不是从头开始）}
    ///
    /// 已经淡完的（音量 0）不会留在列表里——它们在 [#tickFadingOut] 里就被放掉声道了，
    /// 所以处境再回到它们时走的是 [#crossfade]，也就是**从头开始放**。
    private @Nullable FadingOut findResumable(Minecraft minecraft, @Nullable MusicSelection selection) {
        if (selection == null) return null;
        for (FadingOut fading : fadingOut) {
            if (fading.volume() > 0.0F && fading.track() != null && fading.selection() == selection
                    && isPlaying(minecraft, fading.instance())) {
                return fading;
            }
        }
        return null;
    }

    /// 处境又变回来了：把那一首**还没淡完**的旧音乐接着放，并把所有权交还给它。
    ///
    /// 它的声道还活着（音量还没降到 0），所以这里只要把“当前”换回去：
    /// 它重新回到 [#fadedInstance] 里继续往上涨、事件给出的曲目换回它、`MusicManager.currentMusic`
    /// 也指回它的声道——两处 location 相同，原版这一拍就不会去 stop 它，也不会重新 startPlaying，
    /// 于是曲目位置不重置。刚换上的那一首则进淡出列表，降下去之后被放掉。
    private void resume(Minecraft minecraft, PortSelectMusicEvent event, @Nullable MusicSelection selection,
                        SoundInstance playing, FadingOut back) {
        @Nullable CachedLocationMusic track = back.track();
        if (track == null) return; // findResumable 已保证非空，这里只是让编译器放心
        @Nullable MusicSelection previous = lastSelection; // 日志用：正在放的那一首属于哪个处境
        fadingOut.remove(back);
        fadingOut.add(new FadingOut(playing, sameTrack(committed, playing) ? committed : null, previous,
                fadedInstance == playing ? volume : 1.0F));
        minecraft.getMusicManager().currentMusic = back.instance();
        event.setMusic(track);
        this.committed = track;
        this.picked = track; // 接着放的这一首就是当前处境要的那一首
        this.pickedSelection = selection;
        fadedInstance = back.instance();
        volume = back.volume(); // 接着它当前（还没淡完）的音量继续往上淡
        fadingIn = true;
        pendingSelection = null;
        lastSelection = selection;
        waitTicks = CMClientConfigs.nextSongDelay;
        ConfluenceMusic.LOGGER.debug("[MusicPlayer] resume {} ({} -> {}) vol={}", track.getLocation(), previous, selection, volume);
    }

    /// 推进所有旧声道的淡出；降到 0 的那一首立刻放掉声道并从列表里移除
    /// （之后处境再回到它就是从头放，见 [#findResumable]）。
    private void tickFadingOut(Minecraft minecraft) {
        if (fadingOut.isEmpty()) return;
        float step = CMClientConfigs.lastSongFadeOutStep;
        for (int i = fadingOut.size() - 1; i >= 0; i--) {
            FadingOut fading = fadingOut.get(i);
            float next = Mth.clamp(fading.volume() - step, 0.0F, 1.0F);
            setVolume(minecraft, fading.instance(), next);
            if (next <= 0.0F) {
                fadingOut.remove(i);
                minecraft.getSoundManager().stop(fading.instance());
                ConfluenceMusic.LOGGER.debug("[MusicPlayer] faded out {}", fading.instance().getLocation());
            } else {
                fadingOut.set(i, new FadingOut(fading.instance(), fading.track(), fading.selection(), next));
            }
        }
    }

    /// {@return 这个新种类是否已经连续保持了 [#CHANGE_CONFIRM_TICKS] 刻}
    ///
    /// 在群系边界、太空层高度线这类地方，处境可能只抖一两个刻；抖一下不换曲，
    /// 免得曲目被反复从头开始放。
    private boolean confirmChange(@Nullable MusicSelection selection) {
        if (pendingSelection != selection) {
            pendingSelection = selection;
            pendingTicks = CHANGE_CONFIRM_TICKS;
            return false;
        }
        return --pendingTicks <= 0;
    }

    /// 把曲目交给 {@link PortSelectMusicEvent}，让 {@code MusicManager} 立刻开始播放。
    ///
    /// 换上的新曲目（若真有）记成“从 0 开始渐入”；它的声道要到本刻稍后
    /// `MusicManager.startPlaying` 才建立，所以真正的第一步写音量在下一刻。
    private void commit(PortSelectMusicEvent event, @Nullable MusicSelection selection, @Nullable CachedLocationMusic track) {
        resetFade();
        this.committed = track;
        if (track != null) {
            event.setMusic(track);
            fadingIn = true;
            volume = 0.0F;
        } else {
            // 明确告知“不需要任何音乐”，原版情景音乐也就不会响
            suppressVanillaMusic(event);
        }
        lastSelection = selection;
        waitTicks = CMClientConfigs.nextSongDelay;
    }

    /// 交给事件之后，在本客户端刻结束前把刚换上的那一首压到音量 0。
    ///
    /// 原版是在我们处理完事件之后、同一次 `MusicManager.tick()` 里才 `startPlaying` 的，
    /// 而它建立声道时是按**原始音量**写的初值；如果等到下一刻才动手，新曲开头就会以满音量响一帧
    /// （交叉淡化时还会跟还没降下来的旧曲叠在一起，听起来像“咔”一下变响）。
    /// 所以由 [MusicHandler] 在 `ClientTickEvent.End` 调这里补一次：那一刻声道一定已经建好
    /// （`currentMusic` 只有原版的 `startPlaying` 会设置），而声道写入走的是
    /// `SoundEngineExecutor` 这个单线程 FIFO 队列，排在原版那次初值写入之后，因此一定是最后生效的。
    public void applyPendingFadeIn(Minecraft minecraft) {
        @Nullable CachedLocationMusic track = committed;
        if (!fadingIn || fadedInstance != null || track == null) return; // 没有待接管的新曲
        @Nullable SoundInstance playing = minecraft.getMusicManager().currentMusic;
        if (playing == null || !track.getLocation().equals(playing.getLocation())) return; // 还没起来或不是我们交出去的那一首
        fadedInstance = playing;
        volume = 0.0F;
        setVolume(minecraft, playing, 0.0F);
    }

    /// 把正在播放的这一首渐入到原始音量。
    ///
    /// 刚交出去的那一首从 0 开始（[#commit] 把 [#fadingIn] 打开）；其他情况（音量本来就在原始值）
    /// 只是确认一下，不会去改声道。
    ///
    /// @return 是否已经回到原始音量
    private boolean fadeIn(Minecraft minecraft, SoundInstance playing) {
        if (fadedInstance != playing) { // 换了一个声道实例
            fadedInstance = playing;
            volume = fadingIn ? 0.0F : 1.0F;
        }
        if (volume >= 1.0F) {
            fadingIn = false;
            return true; // 已经是原始音量，不必每刻都去改声道
        }
        volume = Mth.clamp(volume + CMClientConfigs.lastSongFadeOutStep, 0.0F, 1.0F);
        setVolume(minecraft, playing, volume);
        if (volume >= 1.0F) {
            fadingIn = false;
            announce(playing); // 这一首刚好涨满：此刻上一首也正好淡完
        }
        return volume >= 1.0F;
    }

    /// 一首曲子从 0 涨到原始音量的那一刻，弹一条“正在播放”提示。
    ///
    /// 交叉淡化时两边的步长一样，所以新曲涨满的那一刻正是旧曲淡到 0 的那一刻，
    /// 显示的也就是“现在真正在响的这一首”。自然放完、间隔之后重开的那一首同样会走这里，
    /// 因此不会出现“有的歌没提示”；不是本模组的音乐（别的模组插进来的）不提示。
    private static void announce(SoundInstance playing) {
        ResourceLocation location = playing.getLocation();
        if (ConfluenceMusic.MODID.equals(location.getNamespace())) {
            MusicToast.show(MusicTitles.of(location));
        }
    }

    private void resetFade() {
        fadingIn = false;
        fadedInstance = null;
        committed = null;
        volume = 1.0F;
    }

    /// 把声道音量设为“本来应有的音量 × factor”。
    ///
    /// 原版 `SoundEngine` 的算法是 `实例音量 × “音乐”音量设置`（再截到 0~1），
    /// 这里保持一致，所以淡化结束时不会出现音量跳变。
    private static void setVolume(Minecraft minecraft, SoundInstance playing, float factor) {
        @Nullable ChannelAccess.ChannelHandle handle = minecraft.getSoundManager().soundEngine.instanceToChannel.get(playing);
        if (handle == null) return;
        float full = Mth.clamp(playing.getVolume() * minecraft.options.getSoundSourceVolume(SoundSource.MUSIC), 0.0F, 1.0F);
        handle.execute(channel -> channel.setVolume(full * factor));
    }

    /// {@return 声道是否还在出声}
    ///
    /// `MusicManager` 自己会在音乐结束时把 `currentMusic` 置空，所以这里只需要问声道：
    /// 声道不存在（还没开始放、或已经被回收）或已停止，都算“没有音乐在放”。
    /// 原版 `SoundEngine.play` 是同步把声道放进 `instanceToChannel` 的，
    /// 因此刚换上的曲目下一刻就能被认出来。
    private static boolean isPlaying(Minecraft minecraft, @Nullable SoundInstance playing) {
        if (playing == null) return false;
        @Nullable ChannelAccess.ChannelHandle handle = minecraft.getSoundManager().soundEngine.instanceToChannel.get(playing);
        return handle != null && !handle.isStopped();
    }

    /// {@return 正在播放的这个声道是不是就是这一首曲目}
    private static boolean sameTrack(@Nullable CachedLocationMusic track, SoundInstance playing) {
        return track != null && track.getLocation().equals(playing.getLocation());
    }

    /// {@return 是否可以打断当前正在播放的这一首}
    ///
    /// 本模组自己的曲目（都注册在 [ConfluenceMusic#MODID] 命名空间下）随时可以换；
    /// 原版音乐只在玩家勾选了 [CMClientConfigs#denyVanillaMusic] 时才打断；
    /// 其他模组的音乐一律不打断——等它放完，间隔一到再轮到我们，免得和别的音乐模组打架。
    private static boolean shouldReplace(SoundInstance playing) {
        ResourceLocation location = playing.getLocation();
        if (ConfluenceMusic.MODID.equals(location.getNamespace())) return true;
        return CMClientConfigs.denyVanillaMusic && ResourceLocation.DEFAULT_NAMESPACE.equals(location.getNamespace());
    }

    /// 玩家要求禁用原版音乐时，用“没有音乐”把原版情景音乐压住。
    private static void suppressVanillaMusic(PortSelectMusicEvent event) {
        if (CMClientConfigs.denyVanillaMusic) {
            event.setMusic(null);
        }
    }
}
