package org.polaris2023.wild_wind.mixin;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.polaris2023.wild_wind.common.init.ModAttachmentTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity> extends EntityRenderer<T> {

    protected LivingEntityRendererMixin(EntityRendererProvider.Context context) {
        super(context);
    }

    @Inject(method = "isShaking", at = @At(value = "HEAD"), cancellable = true)
    protected void isShaking(T entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity.getData(ModAttachmentTypes.SHOULD_SQUID_CONVERT)) {
            cir.setReturnValue(true);
        }

    }

}