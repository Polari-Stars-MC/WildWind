package org.polaris2023.wild_wind.client.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.polaris2023.wild_wind.common.entity.Firefly;
import org.polaris2023.wild_wind.util.Helpers;

public class FireflyRenderer extends MobRenderer<Firefly, FireflyModel> {
    public FireflyRenderer(EntityRendererProvider.Context context) {
        super(context, new FireflyModel(context.bakeLayer(FireflyModel.LAYER_LOCATION)), 1);
    }

    @Override
    public ResourceLocation getTextureLocation(Firefly firefly) {
        return Helpers.location("textures/entity/firefly.png");
    }
}
