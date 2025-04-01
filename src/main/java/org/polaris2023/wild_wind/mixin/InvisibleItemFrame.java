package org.polaris2023.wild_wind.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.polaris2023.wild_wind.util.interfaces.ICustomItemFrame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemFrame.class)
public abstract class InvisibleItemFrame extends HangingEntity implements ICustomItemFrame {
    @Shadow public abstract ItemStack getItem();

    @Shadow public abstract void setItem(ItemStack stack);

    @Unique
    private boolean isInvisible;

    protected InvisibleItemFrame(EntityType<? extends HangingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "setItem(Lnet/minecraft/world/item/ItemStack;Z)V", at = @At("TAIL"))
    private void setHeldItem(ItemStack value, boolean update, CallbackInfo ci) {
        if (this.isInvisible) {
            this.setInvisible(true);
        }
    }

    @Inject(method = "removeFramedMap", at = @At("TAIL"))
    private void removeFromFrameMixin(ItemStack stack, CallbackInfo ci) {
        if (this.isInvisible) {
            this.setInvisible(false);
        }
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void addAdditionalSaveDataInject(CompoundTag nbt, CallbackInfo ci) {
        nbt.putBoolean("isInvisible", this.isInvisible);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void readAdditionalSaveDataInject(CompoundTag nbt, CallbackInfo ci) {
        if (nbt.contains("isInvisible")) {
            this.isInvisible = nbt.getBoolean("isInvisible");
        }
    }

    @Override
    public boolean wild_wind$getIsInvisible() {
        return isInvisible;
    }

    @Override
    public void wild_wind$setIsInvisible(boolean isInvisible) {
        this.isInvisible = isInvisible;
        if(!this.getItem().isEmpty()) {
            setItem(this.getItem());
        }
    }
}