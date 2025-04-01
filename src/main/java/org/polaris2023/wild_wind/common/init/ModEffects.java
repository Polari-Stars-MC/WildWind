package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wild_wind.WildWindMod;

import java.util.function.Supplier;

public class ModEffects {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, WildWindMod.MOD_ID);

    public static <T extends MobEffect> DeferredHolder<MobEffect, T> register(String name, Supplier<T> effect){
        return EFFECTS.register(name, effect);
    }

}