package org.polaris2023.wild_wind.common.init;

import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.wild_wind.util.Helpers;

import java.util.Locale;
import java.util.function.Supplier;

import static org.polaris2023.wild_wind.common.init.ModInitializer.SOUNDS;

public enum ModSounds implements Supplier<SoundEvent> {
    GLARE_AMBIENT_1(1),
    GLARE_AMBIENT_2(1),
    GLARE_AMBIENT_3(1),
    GLARE_AMBIENT_4(1),
    GLARE_AMBIENT_5(1),
    GLARE_AMBIENT_6(1),
    GLARE_AMBIENT_7(1),
    GLARE_AMBIENT_8(1),
    GLARE_AMBIENT_9(1),
    GLARE_AMBIENT_10(1),
    GLARE_AMBIENT_11(1),
    GLARE_AMBIENT_12(1),
    GLARE_AMBIENT_13(1),
    ;
    private final DeferredHolder<SoundEvent, SoundEvent> holder;

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
}
