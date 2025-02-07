package org.polaris2023.wild_wind.client.entity.piranha;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.polaris2023.wild_wind.common.entity.Piranha;
import org.polaris2023.wild_wind.util.Helpers;

public class PiranhaRenderer extends MobRenderer<Piranha, PiranhaModel> {
    public PiranhaRenderer(EntityRendererProvider.Context context) {
        super(context, new PiranhaModel(context.bakeLayer(PiranhaModel.LAYER_LOCATION)), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(Piranha piranha) {
        return Helpers.location("textures/entity/piranha.png");
    }
}
