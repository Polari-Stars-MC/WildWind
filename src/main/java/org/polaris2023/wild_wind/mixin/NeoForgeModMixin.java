package org.polaris2023.wild_wind.mixin;

import com.jcraft.jorbis.Block;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.polaris2023.wild_wind.mixin.accessor.BaseFlowingFluidPropertiesAccessor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.function.Supplier;

/**
 * @author : baka4n
 * {@code @Date : 2025/05/05 00:56:44}
 */
@Mixin(NeoForgeMod.class)
public class NeoForgeModMixin {
    @Shadow @Final public static DeferredHolder<Fluid, Fluid> MILK;

    @Inject(method = "registerFluids", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/registries/RegisterEvent;register(Lnet/minecraft/resources/ResourceKey;Ljava/util/function/Consumer;)V", ordinal = 2, shift = At.Shift.AFTER))
    private void registerFluids(RegisterEvent event, CallbackInfo ci) {
        if (!BuiltInRegistries.BLOCK.containsKey(MILK.getId())) {
            event.register(Registries.BLOCK, MILK.getId(), () -> new LiquidBlock((FlowingFluid) MILK.get(), BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).noCollission().replaceable().strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY)));
        }
    }

    @Inject(method = "lambda$registerFluids$76", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/fluids/BaseFlowingFluid$Source;<init>(Lnet/neoforged/neoforge/fluids/BaseFlowingFluid$Properties;)V"))
    private static void lambda$registerFluids$76(RegisterEvent.RegisterHelper<Fluid> helper, CallbackInfo ci, @Local BaseFlowingFluid.Properties properties) {
        if (((BaseFlowingFluidPropertiesAccessor) properties).getBlock() == null) {
            properties.block(() -> (LiquidBlock) BuiltInRegistries.BLOCK.get(MILK.getId()));
        }
    }
}
