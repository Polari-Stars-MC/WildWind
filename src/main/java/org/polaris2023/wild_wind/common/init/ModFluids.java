package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.fluids.MilkFluid;

public class ModFluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, WildWindMod.MOD_ID);
    public static final DeferredHolder<Fluid, MilkFluid> MILK = FLUIDS.register("milk", MilkFluid.Source::new);
    public static final DeferredHolder<Fluid, MilkFluid> FLOWING_MILK = FLUIDS.register("flowing_milk", MilkFluid.Flowing::new);

}
