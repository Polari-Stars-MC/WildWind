package org.polaris2023.wild_wind.client.entity.trout;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.polaris2023.wild_wind.common.entity.Trout;
import org.polaris2023.wild_wind.util.Helpers;

public class TroutRenderer extends MobRenderer<Trout, TroutModel> {
    public TroutRenderer(EntityRendererProvider.Context context) {
        super(context, new TroutModel(context.bakeLayer(TroutModel.LAYER_LOCATION)), 1);
    }

    @Override
    public ResourceLocation getTextureLocation(Trout trout) {
        return Helpers.location("textures/entity/trout.png");
    }
}
