package org.polaris2023.wild_wind.client.entity.abstracts;

import com.google.common.base.Function;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import org.polaris2023.wild_wind.util.Helpers;

public class ModMobRenderer<T extends Mob, M extends EntityModel<T>> extends MobRenderer<T, M> {

    private final String name;

    public ModMobRenderer(String name, EntityRendererProvider.Context context, Function<ModelPart, M> model, ModelLayerLocation location, float shadowRadius) {
        super(context, model.apply(context.bakeLayer(location)), shadowRadius);
        this.name = name;
    }


    @Override
    public ResourceLocation getTextureLocation(T t) {
        return Helpers.location("textures/entity/" + name + ".png");
    }
}
