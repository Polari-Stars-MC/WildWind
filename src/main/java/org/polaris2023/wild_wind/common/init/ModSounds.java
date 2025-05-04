package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.wild_wind.util.Helpers;

import java.util.*;
import java.util.function.Supplier;

import static org.polaris2023.wild_wind.common.init.ModInitializer.SOUNDS;

public enum ModSounds implements Supplier<SoundEvent> {
    @I18n(en_us = "Living Tuber: Screaming", zh_cn = "活根 : 尖叫", zh_tw = "活根 : 尖叫")
    GLARE_AMBIENT_1,
    @I18n(en_us = "Living Tuber: Screaming", zh_cn = "活根 : 尖叫", zh_tw = "活根 : 尖叫")
    GLARE_AMBIENT_2,
    @I18n(en_us = "Living Tuber: Screaming", zh_cn = "活根 : 尖叫", zh_tw = "活根 : 尖叫")
    GLARE_AMBIENT_3,
    @I18n(en_us = "Living Tuber: Screaming", zh_cn = "活根 : 尖叫", zh_tw = "活根 : 尖叫")
    GLARE_AMBIENT_4,
    @I18n(en_us = "Living Tuber: Screaming", zh_cn = "活根 : 尖叫", zh_tw = "活根 : 尖叫")
    GLARE_AMBIENT_5,
    @I18n(en_us = "Living Tuber: Screaming", zh_cn = "活根 : 尖叫", zh_tw = "活根 : 尖叫")
    GLARE_AMBIENT_6,
    @I18n(en_us = "Living Tuber: Screaming", zh_cn = "活根 : 尖叫", zh_tw = "活根 : 尖叫")
    GLARE_AMBIENT_7,
    @I18n(en_us = "Living Tuber: Screaming", zh_cn = "活根 : 尖叫", zh_tw = "活根 : 尖叫")
    GLARE_AMBIENT_8,
    @I18n(en_us = "Living Tuber: Screaming", zh_cn = "活根 : 尖叫", zh_tw = "活根 : 尖叫")
    GLARE_AMBIENT_9,
    @I18n(en_us = "Living Tuber: Screaming", zh_cn = "活根 : 尖叫", zh_tw = "活根 : 尖叫")
    GLARE_AMBIENT_10,
    @I18n(en_us = "Living Tuber: Screaming", zh_cn = "活根 : 尖叫", zh_tw = "活根 : 尖叫")
    GLARE_AMBIENT_11,
    @I18n(en_us = "Living Tuber: Screaming", zh_cn = "活根 : 尖叫", zh_tw = "活根 : 尖叫")
    GLARE_AMBIENT_12,
    @I18n(en_us = "Living Tuber: Screaming", zh_cn = "活根 : 尖叫", zh_tw = "活根 : 尖叫")
    GLARE_AMBIENT_13,
    @I18n(en_us = "Living Tuber: Death", zh_cn = "活根 : 死亡", zh_tw = "活根 : 死亡")
    GLARE_DEATH_1,
    @I18n(en_us = "Living Tuber: Death", zh_cn = "活根 : 死亡", zh_tw = "活根 : 死亡")
    GLARE_DEATH_2,
    @I18n(en_us = "Living Tuber: Death", zh_cn = "活根 : 死亡", zh_tw = "活根 : 死亡")
    GLARE_DEATH_3,
    @I18n(en_us = "magic flute", zh_cn = "魔笛音", zh_tw = "魔笛音")
    MAGIC_FLUTE
    ;

    public static final List<ModSounds> DEATH_S = new ArrayList<>();

    static {

        DEATH_S.add(GLARE_DEATH_1);
        DEATH_S.add(GLARE_DEATH_2);
        DEATH_S.add(GLARE_DEATH_3);
    }

    public final DeferredHolder<SoundEvent, SoundEvent> holder;

    ModSounds(float range) {
        this.holder = registerFixedRange(name().toLowerCase(Locale.ROOT), range);
    }

    ModSounds() {
        this.holder = registerVariableRange(name().toLowerCase(Locale.ROOT));
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

    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public SoundEvent get() {
        return holder.get();
    }

    public Holder<SoundEvent> getHolder() {
        return this.holder;
    }
}
