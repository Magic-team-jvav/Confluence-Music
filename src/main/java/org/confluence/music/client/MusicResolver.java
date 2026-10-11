package org.confluence.music.client;

import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import org.confluence.mod.util.OverworldUtils;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/// 从 [MusicEnvironment] 解析出当前应当播放的“音乐种类”。
///
/// 规则按优先级从高到低排列，第一条给出结果的规则胜出：
/// <ol>
///   - Boss 音乐
///   - 地牢分层音乐（结构）
///   - 创造模式音乐
///   - 维度：主世界继续细分，下界用下界音乐
/// </ol>
///
/// 主世界内部再细分，判定顺序与原实现的嵌套 if 基本一致：
/// 太空 → 迷你群系（陨石/墓地/微光，会盖掉所在群系的音乐）
/// → 生物群系（蘑菇/雪原/腐化/猩红/神圣/沙漠/海洋/丛林，其中雪原下雨会被暴风雪覆盖、沙漠沙尘暴会覆盖沙漠音乐）
/// → 事件与天气（血月、日食、史莱姆雨、入侵、月相事件、雷暴、降雨、清晨降雨、城镇）→ 地表昼夜／地下。
///
/// **尚未接入的种类**（[MusicSelection] 里有、但没人会把它们选出来）：
/// `TEMPLE`、`MARTIAN_MADNESS`、`LUNAR_EVENTS`、`OLD_ONES_ARMY`、
/// `TORCH_GOD`、`FROST_LEGION`，以及标题／旅程系列的 `TITLE`、`DAY_REMIX`、
/// `JOURNEYS_BEGINNING`、`JOURNEYS_END`；这些要么缺少客户端可读的事件状态，要么缺少音乐文件。
///
/// Boss 中还有 `QUEEN_SLIME`、`THE_TWINS`、`THE_DESTROYER`、`PLANTERA`、
/// `GOLEM`、`DUKE_FISHRON`、`EMPRESS_OF_LIGHT`、`LUNATIC_CULTIST`、
/// `MOON_LORD` 没接，原因同样是缺音乐文件，见 [MusicEnvironment] 里的 Boss 对照表。
public final class MusicResolver {
    private static final List<Rule> RULES = List.of(
            MusicResolver::boss,
            MusicResolver::dungeon,
            MusicResolver::creativeMode,
            MusicResolver::dimension
    );

    private MusicResolver() {}

    /// {@return 当前应当播放的音乐种类，没有任何规则命中时返回 null}
    public static @Nullable MusicSelection resolve(MusicEnvironment env) {
        for (Rule rule : RULES) {
            @Nullable MusicSelection selection = rule.select(env);
            if (selection != null) return selection;
        }
        return null;
    }

    /// 一条选择规则：环境不满足条件时返回 null，让后面的规则继续判断。
    @FunctionalInterface
    private interface Rule {
        @Nullable MusicSelection select(MusicEnvironment env);
    }

    private static @Nullable MusicSelection boss(MusicEnvironment env) {
        return env.bossMusic();
    }

    private static @Nullable MusicSelection dungeon(MusicEnvironment env) {
        return env.dungeonMusic();
    }

    /// 创造模式音乐覆盖其他场景，但下界例外（与原实现一致）。
    private static @Nullable MusicSelection creativeMode(MusicEnvironment env) {
        if (!CMClientConfigs.keepCreativeModeMusic || env.inUnderworld() || !env.creative()) return null;
        return MusicSelection.CREATIVE_MODE;
    }

    private static @Nullable MusicSelection dimension(MusicEnvironment env) {
        if (env.inUnderworld()) return MusicSelection.UNDERWORLD;
        if (!env.inOverworld()) return null; // 未适配的维度：交给原版音乐（是否禁用在 MusicPlayer 里决定）
        return overworld(env);
    }

    private static MusicSelection overworld(MusicEnvironment env) {
        if (env.space()) return env.day() ? MusicSelection.SPACE_DAY : MusicSelection.SPACE_NIGHT;
        // 迷你群系（陨石、墓地、微光/以太）盖掉所在群系的音乐
        @Nullable MusicSelection miniBiome = env.miniBiomeMusic();
        if (miniBiome != null) return miniBiome;
        @Nullable MusicSelection biome = biomeMusic(env);
        if (biome != null) return biome;
        return eventAndWeatherMusic(env);
    }

    /// 生物群系专属音乐：有专属音乐的群系会盖掉天气与昼夜。
    private static @Nullable MusicSelection biomeMusic(MusicEnvironment env) {
        @Nullable Holder<Biome> biome = env.biome();
        if (biome == null) return null;
        if (OverworldUtils.isMushroom(biome)) return MusicSelection.MUSHROOMS;
        if (OverworldUtils.isSnowy(biome)) {
            // 雪原下雨即暴风雪，否则地表雪原 / 地下冰原
            return env.raining() ? MusicSelection.BLIZZARD : (env.surface() ? MusicSelection.SNOW : MusicSelection.ICE);
        }
        if (OverworldUtils.isCorruption(biome)) {
            return env.surface() ? MusicSelection.THE_CORRUPTION : MusicSelection.UNDERGROUND_CORRUPTION;
        }
        if (OverworldUtils.isCrimson(biome)) {
            return env.surface() ? MusicSelection.THE_CRIMSON : MusicSelection.UNDERGROUND_CRIMSON;
        }
        if (OverworldUtils.isHallow(biome)) {
            if (!env.surface()) return MusicSelection.UNDERGROUND_HALLOW;
            return env.day() ? MusicSelection.THE_HALLOW_DAY : MusicSelection.THE_HALLOW_NIGHT;
        }
        if (OverworldUtils.isDesert(biome)) {
            // 沙尘暴盖掉沙漠音乐（与雪原的暴风雪同理）
            if (env.sandstorm()) return MusicSelection.SANDSTORM;
            return env.surface() ? MusicSelection.DESERT : MusicSelection.UNDERGROUND_DESERT;
        }
        if (OverworldUtils.isOcean(biome)) {
            return env.day() ? MusicSelection.OCEAN_DAY : MusicSelection.OCEAN_NIGHT;
        }
        if (OverworldUtils.isJungle(biome)) {
            if (!env.surface()) return MusicSelection.UNDERGROUND_JUNGLE;
            return env.day() ? MusicSelection.JUNGLE_DAY : MusicSelection.JUNGLE_NIGHT;
        }
        return null; // 没有专属音乐的群系（森林、平原……）
    }

    /// 没有生物群系专属音乐时，按事件 → 天气 → 城镇 → 地表昼夜／地下的顺序决定。
    ///
    /// 事件与天气目前排在生物群系之后（与原实现一致）：想让事件音乐盖掉丛林、海洋之类的群系音乐，
    /// 把这一段整体挪到 [#overworld] 里 `biomeMusic` 之前即可。
    private static MusicSelection eventAndWeatherMusic(MusicEnvironment env) {
        @Nullable MusicSelection event = env.eventMusic();
        if (event != null) return event;
        if (env.thundering()) return MusicSelection.STORM;
        if (env.raining()) {
            if (env.morningRain()) return MusicSelection.MORNING_RAIN;
            return env.day() ? MusicSelection.RAIN_DAY : MusicSelection.RAIN_NIGHT;
        }
        if (env.town()) return env.day() ? MusicSelection.TOWN_DAY : MusicSelection.TOWN_NIGHT;
        if (!env.surface()) return MusicSelection.UNDERGROUND;
        if (!env.day()) return MusicSelection.OVERWORLD_NIGHT;
        return env.windy() ? MusicSelection.WINDY_DAY : MusicSelection.OVERWORLD_DAY;
    }
}
