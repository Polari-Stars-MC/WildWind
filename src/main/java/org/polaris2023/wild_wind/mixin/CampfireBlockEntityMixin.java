package org.polaris2023.wild_wind.mixin;

import com.google.common.collect.Iterables;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.polaris2023.wild_wind.common.init.ModItems;
import org.polaris2023.wild_wind.common.listeners.LivingTuberEventHandler;
import org.polaris2023.wild_wind.util.RandomUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CampfireBlockEntity.class)
public class CampfireBlockEntityMixin {

    @Redirect(method = "cookTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/Containers;dropItemStack(Lnet/minecraft/world/level/Level;DDDLnet/minecraft/world/item/ItemStack;)V"))
    private static void ww$onItemBurnt(Level level, double x, double y, double z, ItemStack itemStack, @Local(ordinal = 0) ItemStack itemStackIn, @Local(argsOnly = true) CampfireBlockEntity blockEntity) {
        if(itemStackIn.is(ModItems.LIVING_TUBER)) {
            LivingTuberEventHandler.playDeathSound(null, level, blockEntity.getBlockPos());
        }
        Containers.dropItemStack(level, x, y, z, itemStack);
    }

    @Inject(method = "cookTick", at = @At("RETURN"))
    private static void ww$onCookTick(Level level, BlockPos pos, BlockState state, CampfireBlockEntity blockEntity, CallbackInfo ci) {
        if(RandomUtil.nextBoolean(5) && Iterables.any(blockEntity.getItems(), itemStack -> itemStack.is(ModItems.LIVING_TUBER))) {
            LivingTuberEventHandler.playAmbientSound(null, level, blockEntity.getBlockPos());
        }
    }

}
