package org.polaris2023.wild_wind.mixin;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.init.ModSounds;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.lang.reflect.Field;

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
                Field field = ModSounds.class.getField("glare_ambient_" + i);
                field.setAccessible(true);
                Object o = field.get(null);
                if (o instanceof DeferredHolder<?,?> holder && holder.get() instanceof SoundEvent sound) {
                    entity.playSound(sound, 0.8F, 1.2F);
                }
            } catch (Exception ignored) {}
        }
    }
}
