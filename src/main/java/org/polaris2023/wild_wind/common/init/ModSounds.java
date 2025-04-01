package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.util.Helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModSounds {
    
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, WildWindMod.MOD_ID);
    
    @I18n(en_us = "Living Tuber: Screaming", zh_cn = "活根 : 尖叫", zh_tw = "活根 : 尖叫")
    public static final DeferredHolder<SoundEvent, SoundEvent> GLARE_AMBIENT_1 = registerVariableRange("glare_ambient_1");
    @I18n(en_us = "Living Tuber: Screaming", zh_cn = "活根 : 尖叫", zh_tw = "活根 : 尖叫")
    public static final DeferredHolder<SoundEvent, SoundEvent> GLARE_AMBIENT_2 = registerVariableRange("glare_ambient_2");
    @I18n(en_us = "Living Tuber: Screaming", zh_cn = "活根 : 尖叫", zh_tw = "活根 : 尖叫")
    public static final DeferredHolder<SoundEvent, SoundEvent> GLARE_AMBIENT_3 = registerVariableRange("glare_ambient_3");
    @I18n(en_us = "Living Tuber: Screaming", zh_cn = "活根 : 尖叫", zh_tw = "活根 : 尖叫")
    public static final DeferredHolder<SoundEvent, SoundEvent> GLARE_AMBIENT_4 = registerVariableRange("glare_ambient_4");
    @I18n(en_us = "Living Tuber: Screaming", zh_cn = "活根 : 尖叫", zh_tw = "活根 : 尖叫")
    public static final DeferredHolder<SoundEvent, SoundEvent> GLARE_AMBIENT_5 = registerVariableRange("glare_ambient_5");
    @I18n(en_us = "Living Tuber: Screaming", zh_cn = "活根 : 尖叫", zh_tw = "活根 : 尖叫")
    public static final DeferredHolder<SoundEvent, SoundEvent> GLARE_AMBIENT_6 = registerVariableRange("glare_ambient_6");
    @I18n(en_us = "Living Tuber: Screaming", zh_cn = "活根 : 尖叫", zh_tw = "活根 : 尖叫")
    public static final DeferredHolder<SoundEvent, SoundEvent> GLARE_AMBIENT_7 = registerVariableRange("glare_ambient_7");
    @I18n(en_us = "Living Tuber: Screaming", zh_cn = "活根 : 尖叫", zh_tw = "活根 : 尖叫")
    public static final DeferredHolder<SoundEvent, SoundEvent> GLARE_AMBIENT_8 = registerVariableRange("glare_ambient_8");
    @I18n(en_us = "Living Tuber: Screaming", zh_cn = "活根 : 尖叫", zh_tw = "活根 : 尖叫")
    public static final DeferredHolder<SoundEvent, SoundEvent> GLARE_AMBIENT_9 = registerVariableRange("glare_ambient_9");
    @I18n(en_us = "Living Tuber: Screaming", zh_cn = "活根 : 尖叫", zh_tw = "活根 : 尖叫")
    public static final DeferredHolder<SoundEvent, SoundEvent> GLARE_AMBIENT_10 = registerVariableRange("glare_ambient_10");
    @I18n(en_us = "Living Tuber: Screaming", zh_cn = "活根 : 尖叫", zh_tw = "活根 : 尖叫")
    public static final DeferredHolder<SoundEvent, SoundEvent> GLARE_AMBIENT_11 = registerVariableRange("glare_ambient_11");
    @I18n(en_us = "Living Tuber: Screaming", zh_cn = "活根 : 尖叫", zh_tw = "活根 : 尖叫")
    public static final DeferredHolder<SoundEvent, SoundEvent> GLARE_AMBIENT_12 = registerVariableRange("glare_ambient_12");
    @I18n(en_us = "Living Tuber: Screaming", zh_cn = "活根 : 尖叫", zh_tw = "活根 : 尖叫")
    public static final DeferredHolder<SoundEvent, SoundEvent> GLARE_AMBIENT_13 = registerVariableRange("glare_ambient_13");
    @I18n(en_us = "Living Tuber: Death", zh_cn = "活根 : 死亡", zh_tw = "活根 : 死亡")
    public static final DeferredHolder<SoundEvent, SoundEvent> GLARE_DEATH_1 = registerVariableRange("glare_death_1");
    @I18n(en_us = "Living Tuber: Death", zh_cn = "活根 : 死亡", zh_tw = "活根 : 死亡")
    public static final DeferredHolder<SoundEvent, SoundEvent> GLARE_DEATH_2 = registerVariableRange("glare_death_2");
    @I18n(en_us = "Living Tuber: Death", zh_cn = "活根 : 死亡", zh_tw = "活根 : 死亡")
    public static final DeferredHolder<SoundEvent, SoundEvent> GLARE_DEATH_3 = registerVariableRange("glare_death_3");
    @I18n(en_us = "magic flute", zh_cn = "魔笛音", zh_tw = "魔笛音")
    public static final DeferredHolder<SoundEvent, SoundEvent> MAGIC_FLUTE = registerVariableRange("magic_flute");

    public static final List<Supplier<SoundEvent>> AMBIENT_S = new ArrayList<>();
    public static final List<Supplier<SoundEvent>> DEATH_S = new ArrayList<>();

    static {
        AMBIENT_S.add(GLARE_AMBIENT_1);
        AMBIENT_S.add(GLARE_AMBIENT_2);
        AMBIENT_S.add(GLARE_AMBIENT_3);
        AMBIENT_S.add(GLARE_AMBIENT_4);
        AMBIENT_S.add(GLARE_AMBIENT_5);
        AMBIENT_S.add(GLARE_AMBIENT_6);
        AMBIENT_S.add(GLARE_AMBIENT_7);
        AMBIENT_S.add(GLARE_AMBIENT_8);
        AMBIENT_S.add(GLARE_AMBIENT_9);
        AMBIENT_S.add(GLARE_AMBIENT_10);
        AMBIENT_S.add(GLARE_AMBIENT_11);
        AMBIENT_S.add(GLARE_AMBIENT_12);
        AMBIENT_S.add(GLARE_AMBIENT_13);
        DEATH_S.add(GLARE_DEATH_1);
        DEATH_S.add(GLARE_DEATH_2);
        DEATH_S.add(GLARE_DEATH_3);
    }

    private static DeferredHolder<SoundEvent, SoundEvent> registerVariableRange(String name) {
        return SOUNDS.register(name,() -> SoundEvent.createVariableRangeEvent(Helpers.location(name)));
    }

    private static DeferredHolder<SoundEvent,SoundEvent> register(String name, Supplier<SoundEvent> supplier){
        return SOUNDS.register(name, supplier);
    }

    private static DeferredHolder<SoundEvent, SoundEvent> registerFixedRange(String name, float range) {
        return SOUNDS.register(name, () -> SoundEvent.createFixedRangeEvent(Helpers.location(name), range));
    }

}