package org.polaris2023.wild_wind.common.init;

import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.wild_wind.util.Helpers;

import java.util.function.Supplier;

import static org.polaris2023.wild_wind.common.init.ModInitializer.SOUNDS;

public class ModSounds {

    public static final DeferredHolder<SoundEvent, SoundEvent> glare_ambient_1 =
            registerFixedRange("glare_ambient_1", 1);
    public static final DeferredHolder<SoundEvent, SoundEvent> glare_ambient_2 =
            registerFixedRange("glare_ambient_2", 1);
    public static final DeferredHolder<SoundEvent, SoundEvent> glare_ambient_3 =
            registerFixedRange("glare_ambient_3", 1);
    public static final DeferredHolder<SoundEvent, SoundEvent> glare_ambient_4 =
            registerFixedRange("glare_ambient_4", 1);
    public static final DeferredHolder<SoundEvent, SoundEvent> glare_ambient_5 =
            registerFixedRange("glare_ambient_5", 1);
    public static final DeferredHolder<SoundEvent, SoundEvent> glare_ambient_6 =
            registerFixedRange("glare_ambient_6", 1);
    public static final DeferredHolder<SoundEvent, SoundEvent> glare_ambient_7 =
            registerFixedRange("glare_ambient_7", 1);
    public static final DeferredHolder<SoundEvent, SoundEvent> glare_ambient_8 =
            registerFixedRange("glare_ambient_8", 1);
    public static final DeferredHolder<SoundEvent, SoundEvent> glare_ambient_9 =
            registerFixedRange("glare_ambient_9", 1);
    public static final DeferredHolder<SoundEvent, SoundEvent> glare_ambient_10 =
            registerFixedRange("glare_ambient_10", 1);
    public static final DeferredHolder<SoundEvent, SoundEvent> glare_ambient_11 =
            registerFixedRange("glare_ambient_11", 1);
    public static final DeferredHolder<SoundEvent, SoundEvent> glare_ambient_12 =
            registerFixedRange("glare_ambient_12", 1);
    public static final DeferredHolder<SoundEvent, SoundEvent> glare_ambient_13 =
            registerFixedRange("glare_ambient_13", 1);





    private static DeferredHolder<SoundEvent,SoundEvent> register(String name, Supplier<SoundEvent> supplier){
        return SOUNDS.register(name, supplier);
    }

    private static DeferredHolder<SoundEvent, SoundEvent> registerFixedRange(String name, float range) {
        return SOUNDS.register(name, () -> SoundEvent.createFixedRangeEvent(Helpers.location(name), range));
    }
}
