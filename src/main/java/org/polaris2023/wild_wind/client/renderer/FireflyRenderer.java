package org.polaris2023.wild_wind.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import org.polaris2023.wild_wind.client.model.FireflyModel;
import org.polaris2023.wild_wind.common.entity.Firefly;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class FireflyRenderer extends GeoEntityRenderer<Firefly> {
	public FireflyRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new FireflyModel());
	}
}
