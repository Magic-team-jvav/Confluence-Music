package org.confluence.music.common.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.confluence.music.ConfluenceMusic;

public final class CMSoundEvents {
    public static final DeferredRegister<SoundEvent> EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, ConfluenceMusic.MODID);

    // common
    public static final RegistryObject<SoundEvent> NEW_JOURNEY = register("new_journey"); // 汇流主题曲（汇流新增）
    public static final RegistryObject<SoundEvent> OVERWORLD_DAY = register("overworld_day"); // 常规白天
    public static final RegistryObject<SoundEvent> ALT_OVERWORLD_DAY = register("alt_overworld_day"); // 常规白天备选
    public static final RegistryObject<SoundEvent> OVERWORLD_NIGHT = register("overworld_night"); // 常规夜晚
    public static final RegistryObject<SoundEvent> ALT_OVERWORLD_NIGHT = register("alt_overworld_night"); // 常规夜晚备选（汇流新增）
    public static final RegistryObject<SoundEvent> ALT_OVERWORLD_NIGHT_2 = register("alt_overworld_night_2"); // 常规夜晚备选其二（汇流新增）
    public static final RegistryObject<SoundEvent> JOURNEYS_BEGINNING = register("journeys_beginning"); // 旅程开始
    public static final RegistryObject<SoundEvent> JOURNEYS_END = register("journeys_end"); // 旅程结束
    public static final RegistryObject<SoundEvent> TITLE = register("title");
    public static final RegistryObject<SoundEvent> ALT_TITLE = register("alt_title"); // 标题备选曲
    public static final RegistryObject<SoundEvent> DAY_REMIX = register("day_remix"); // 白天混音
    public static final RegistryObject<SoundEvent> CREATIVE_MODE = register("creative_mode");
    // location
    public static final RegistryObject<SoundEvent> SNOW = register("snow"); // 地表雪原
    public static final RegistryObject<SoundEvent> ICE = register("ice"); // 地下雪原
    public static final RegistryObject<SoundEvent> DESERT = register("desert");
    public static final RegistryObject<SoundEvent> UNDERGROUND_DESERT = register("underground_desert");
    public static final RegistryObject<SoundEvent> OCEAN_DAY = register("ocean_day");
    public static final RegistryObject<SoundEvent> OCEAN_NIGHT = register("ocean_night");
    public static final RegistryObject<SoundEvent> SPACE_DAY = register("space_day");
    public static final RegistryObject<SoundEvent> SPACE_NIGHT = register("space_night");
    public static final RegistryObject<SoundEvent> UNDERGROUND = register("underground");
    public static final RegistryObject<SoundEvent> ALT_UNDERGROUND = register("alt_underground");
    public static final RegistryObject<SoundEvent> MUSHROOMS = register("mushrooms");
    public static final RegistryObject<SoundEvent> JUNGLE_DAY = register("jungle_day");
    public static final RegistryObject<SoundEvent> JUNGLE_NIGHT = register("jungle_night");
    public static final RegistryObject<SoundEvent> UNDERGROUND_JUNGLE = register("underground_jungle");
    public static final RegistryObject<SoundEvent> THE_CORRUPTION = register("the_corruption");
    public static final RegistryObject<SoundEvent> UNDERGROUND_CORRUPTION = register("underground_corruption");
    public static final RegistryObject<SoundEvent> THE_CRIMSON = register("the_crimson");
    public static final RegistryObject<SoundEvent> UNDERGROUND_CRIMSON = register("underground_crimson");
    public static final RegistryObject<SoundEvent> THE_HALLOW_DAY = register("the_hallow_day");
    public static final RegistryObject<SoundEvent> UNDERGROUND_HALLOW = register("underground_hallow");
    public static final RegistryObject<SoundEvent> UNDERWORLD = register("underworld"); // 下界
    public static final RegistryObject<SoundEvent> TEMPLE = register("temple");
    public static final RegistryObject<SoundEvent> AETHER = register("aether");
    public static final RegistryObject<SoundEvent> GRAVEYARD = register("graveyard");
    public static final RegistryObject<SoundEvent> TOWN_DAY = register("town_day");
    public static final RegistryObject<SoundEvent> TOWN_NIGHT = register("town_night");
    public static final RegistryObject<SoundEvent> DUNGEON_FLOOR_1 = register("dungeon_floor_1"); // 地牢一层
    public static final RegistryObject<SoundEvent> DUNGEON_FLOOR_2 = register("dungeon_floor_2"); // 地牢二层
    public static final RegistryObject<SoundEvent> THE_END = register("the_end"); // 末地
    // boss
    public static final RegistryObject<SoundEvent> BOSS_1 = register("boss_1");
    public static final RegistryObject<SoundEvent> BOSS_2 = register("boss_2");
    public static final RegistryObject<SoundEvent> BOSS_3 = register("boss_3");
    public static final RegistryObject<SoundEvent> BOSS_4 = register("boss_4");
    public static final RegistryObject<SoundEvent> BOSS_5 = register("boss_5");
    public static final RegistryObject<SoundEvent> EATER_OF_WORLDS = register("eater_of_worlds"); // 世界吞噬怪
    public static final RegistryObject<SoundEvent> BRAIN_OF_CTHULHU_PHASE_1 = register("brain_of_cthulhu_phase_1"); // 克苏鲁之脑第一阶段
    public static final RegistryObject<SoundEvent> BRAIN_OF_CTHULHU_PHASE_2 = register("brain_of_cthulhu_phase_2"); // 克苏鲁之脑第二阶段
    public static final RegistryObject<SoundEvent> DEERCLOPS = register("deerclops");
    public static final RegistryObject<SoundEvent> WALL_OF_FLESH = register("wall_of_flesh");
    public static final RegistryObject<SoundEvent> QUEEN_SLIME = register("queen_slime");
    public static final RegistryObject<SoundEvent> PLANTERA = register("plantera");
    public static final RegistryObject<SoundEvent> EMPRESS_OF_LIGHT = register("empress_of_light");
    public static final RegistryObject<SoundEvent> DUKE_FISHRON = register("duke_fishron");
    public static final RegistryObject<SoundEvent> MOON_LORD = register("moon_lord");
    // event
    public static final RegistryObject<SoundEvent> EERIE = register("eerie"); // 血月
    public static final RegistryObject<SoundEvent> ECLIPSE = register("eclipse"); // 日食
    public static final RegistryObject<SoundEvent> GOBLIN_ARMY = register("goblin_army");
    public static final RegistryObject<SoundEvent> PIRATE_INVASION = register("pirate_invasion");
    public static final RegistryObject<SoundEvent> MARTIAN_MADNESS = register("martian_madness"); // 火星暴乱
    public static final RegistryObject<SoundEvent> PUMPKIN_MOON = register("pumpkin_moon");
    public static final RegistryObject<SoundEvent> FROST_MOON = register("frost_moon");
    public static final RegistryObject<SoundEvent> THE_TOWERS = register("the_towers"); // 天界柱
    public static final RegistryObject<SoundEvent> SANDSTORM = register("sandstorm");
    public static final RegistryObject<SoundEvent> BLIZZARD = register("blizzard"); // 暴风雪（汇流新增）
    public static final RegistryObject<SoundEvent> OLD_ONES_ARMY = register("old_ones_army");
    public static final RegistryObject<SoundEvent> SLIME_RAIN = register("slime_rain");
    public static final RegistryObject<SoundEvent> WINDY_DAY = register("windy_day");
    public static final RegistryObject<SoundEvent> RAIN = register("rain");
    public static final RegistryObject<SoundEvent> MORNING_RAIN = register("morning_rain");
    public static final RegistryObject<SoundEvent> STORM = register("storm");

    private static RegistryObject<SoundEvent> register(String name) {
        return EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ConfluenceMusic.asResource(name)));
    }
}
