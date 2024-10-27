package org.polaris2023.wild_wind.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.client.renderer.FireflyRenderer;
import org.polaris2023.wild_wind.common.init.ModEntities;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = WildWindMod.MOD_ID, value = Dist.CLIENT)
public class WildWindClientEventHandler {
	@SubscribeEvent
	public static void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(ModEntities.FIREFLY.get(), FireflyRenderer::new);
	}
}
