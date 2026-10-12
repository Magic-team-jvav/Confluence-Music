package org.confluence.music.client;

import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import org.confluence.music.common.init.CMMusics.CachedLocationMusic;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

/// 音乐种类 → 具体曲目的查询。
///
/// 数据来自数据包（`assets/<modid>/music_selection/*.json`），由 [MusicSelectionLoader] 载入：
/// 每种“音乐类型”（配置项 [CMClientConfigs#musicType]）各自维护一张
/// “音乐种类 → 曲目列表”的表。
public final class MusicRepository {
    private MusicRepository() {}

    /// 按配置的音乐类型从该种类的曲目里**随机**挑一首。
    ///
    /// 每次“要开一首”时抽（处境变化时抽一次，一首放完再开时再抽一次），
    /// 由 [MusicPlayer] 记着抽到的那一首——所以“想放的是不是正在放的这一首”这个判断
    /// 在整首歌里都是稳定的，不会每刻重抽而抖动。
    public static @Nullable CachedLocationMusic pick(RandomSource random, @Nullable MusicSelection selection) {
        if (selection == null) return null;
        @Nullable List<CachedLocationMusic> tracks = getTracks(CMClientConfigs.musicType, selection);
        if (tracks == null || tracks.isEmpty()) return null;
        return Util.getRandom(tracks, random);
    }

    /// {@return 某种音乐类型下、某个音乐种类的全部曲目，缺配置时回退到 {@link MusicHandler#CONFLUENCE}，再缺则返回 null}
    public static @Nullable List<CachedLocationMusic> getTracks(ResourceLocation type, MusicSelection selection) {
        Map<ResourceLocation, Map<MusicSelection, List<CachedLocationMusic>>> all = MusicSelectionLoader.getInstance().getRegisteredMusicSelections();
        @Nullable List<CachedLocationMusic> tracks = lookup(all, type, selection);
        if (tracks == null && !MusicHandler.CONFLUENCE.equals(type)) {
            // 比如“原版来世”类型下没有配置城镇音乐，就退回用汇流来世的曲目
            tracks = lookup(all, MusicHandler.CONFLUENCE, selection);
        }
        return tracks;
    }

    private static @Nullable List<CachedLocationMusic> lookup(
            Map<ResourceLocation, Map<MusicSelection, List<CachedLocationMusic>>> all,
            ResourceLocation type,
            MusicSelection selection) {
        @Nullable Map<MusicSelection, List<CachedLocationMusic>> bySelection = all.get(type);
        return bySelection == null ? null : bySelection.get(selection);
    }
}
