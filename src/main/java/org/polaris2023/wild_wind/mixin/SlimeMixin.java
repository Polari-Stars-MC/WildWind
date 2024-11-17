package org.polaris2023.wild_wind.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.polaris2023.wild_wind.common.init.ModComponents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public class SlimeMixin {
    @Inject(method = "inventoryTick", at = @At("RETURN"))
    private void tick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected, CallbackInfo ci) {
        if (stack.is(Items.SLIME_BALL) && !stack.has(ModComponents.SLIME_COLOR)) {
            stack.set(ModComponents.SLIME_COLOR, 0);
        }
    }
}
