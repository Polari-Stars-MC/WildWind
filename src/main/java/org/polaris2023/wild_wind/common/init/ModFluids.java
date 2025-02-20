package org.polaris2023.wild_wind.common.init;

import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.wild_wind.common.fluids.MilkFluid;

import static org.polaris2023.wild_wind.common.init.ModInitializer.BLOCKS;
import static org.polaris2023.wild_wind.common.init.ModInitializer.FLUIDS;

public class ModFluids {
    public static final DeferredHolder<Fluid, MilkFluid> MILK = FLUIDS.register("milk", MilkFluid.Source::new);
    public static final DeferredHolder<Fluid, MilkFluid> FLOWING_MILK = FLUIDS.register("flowing_milk", MilkFluid.Flowing::new);
    public static final DeferredBlock<LiquidBlock> MILK_BLOCK = BLOCKS.register("milk", () -> new LiquidBlock(MILK.get(), Properties.of().mapColor(MapColor.SNOW).noCollission().replaceable().strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY)));
}
