package org.polaris2023.wild_wind.common.init;

import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Supplier;

import static org.polaris2023.wild_wind.common.init.ModInitializer.SOUNDS;

public final class ModSounds {

    private static DeferredHolder<SoundEvent,SoundEvent> register(String name, Supplier<SoundEvent> supplier){
        return SOUNDS.register(name, supplier);
    }
}
