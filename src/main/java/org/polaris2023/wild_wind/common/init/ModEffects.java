package org.polaris2023.wild_wind.common.init;

import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Supplier;

import static org.polaris2023.wild_wind.common.init.ModInitializer.EFFECTS;

public final class ModEffects {

    public static <T extends MobEffect> DeferredHolder<MobEffect, T> register(String name, Supplier<T> effect){
        return EFFECTS.register(name, effect);
    }
}
