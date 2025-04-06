package org.polaris2023.wild_wind.common.init;

import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.wild_wind.common.fluids.MilkFluid;

import static org.polaris2023.wild_wind.common.init.ModInitializer.FLUIDS;

public class ModFluids {
    public static final DeferredHolder<Fluid, MilkFluid> MILK = FLUIDS.register("milk", MilkFluid.Source::new);
    public static final DeferredHolder<Fluid, MilkFluid> FLOWING_MILK = FLUIDS.register("flowing_milk", MilkFluid.Flowing::new);
}
