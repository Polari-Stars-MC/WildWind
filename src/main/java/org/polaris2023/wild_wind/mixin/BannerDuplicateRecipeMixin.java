package org.polaris2023.wild_wind.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.BannerDuplicateRecipe;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.level.Level;
import org.polaris2023.wild_wind.common.item.ModBannerItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BannerDuplicateRecipe.class)
public class BannerDuplicateRecipeMixin {
    @ModifyReturnValue(method = "matches(Lnet/minecraft/world/item/crafting/CraftingInput;Lnet/minecraft/world/level/Level;)Z", at = @At(value = "RETURN"))
    private boolean wild_wind$matches(boolean original, @Local(argsOnly = true)CraftingInput input, @Local(argsOnly = true) Level level) {
        if (original) {
            return checkForUnidyeItems(input, level);
        }
        return false;
    }

    @Unique
    private boolean checkForUnidyeItems(CraftingInput input, Level level) {
        for (int i = 0; i < input.size(); ++i) {
            ItemStack itemStack3 = input.getItem(i);
            if (itemStack3.isEmpty()) continue;
            Item item = itemStack3.getItem();
            if (item instanceof ModBannerItem) {
                return false;
            }
        }
        return true;
    }
}
