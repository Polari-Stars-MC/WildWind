package org.polaris2023.wild_wind.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.init.ModSounds;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public class MixinItem {
    @Inject(method = "inventoryTick", at = @At("RETURN"))
    private void playSoundByGlareFlowerItem(ItemStack stack,
                                     Level level,
                                     Entity entity,
                                     int slotId,
                                     boolean isSelected,
                                     CallbackInfo ci) {
        if (stack.is(ModBlocks.GLAREFLOWER_ITEM) && level.getGameTime() % 200 == 0) {

            int i = entity.getRandom().nextInt(1, 13);
            try {
                ModSounds sounds = ModSounds.valueOf("GLARE_AMBIENT_" + i);
                entity.playSound(sounds.get(), 0.8F, 1.2F);
            } catch (Exception ignored) {}
        }
    }
}
