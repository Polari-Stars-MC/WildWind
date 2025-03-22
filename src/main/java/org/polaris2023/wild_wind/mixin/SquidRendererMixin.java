package org.polaris2023.wild_wind.mixin;

import net.minecraft.client.model.SquidModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.SquidRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Squid;
import org.polaris2023.wild_wind.util.interfaces.ISquid;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(SquidRenderer.class)
public abstract class SquidRendererMixin<T extends Squid> extends MobRenderer<T, SquidModel<T>> {
    
    public SquidRendererMixin(EntityRendererProvider.Context context, SquidModel<T> model, float shadowRadius) {
        super(context, model, shadowRadius);
    }

    protected boolean isShaking(T entity) {
        ISquid living = (ISquid) entity;
        return living.wild_wind$isShaking();
    }
}
