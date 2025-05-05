package org.polaris2023.wild_wind.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
* @author : baka4n
* {@code @Date : 2025/05/05 20:43:25}
*/
@Mixin(NeoForgeMod.class)
public class NeoForgeModMixin {
    @Inject(method = "lambda$registerFluids$76", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/fluids/BaseFlowingFluid$Source;<init>(Lnet/neoforged/neoforge/fluids/BaseFlowingFluid$Properties;)V"))
    private static void lambda$registerFluids$76(RegisterEvent.RegisterHelper<Fluid> helper, CallbackInfo ci, @Local BaseFlowingFluid.Properties properties) {
        properties.block(ModBlocks.MILK);
    }

}
