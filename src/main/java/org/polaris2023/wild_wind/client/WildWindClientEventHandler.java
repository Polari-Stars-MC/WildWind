package org.polaris2023.wild_wind.client;

import net.minecraft.util.FastColor;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import org.polaris2023.wild_wind.common.init.ModComponents;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class WildWindClientEventHandler {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void registerSlimeColor(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) ->
                FastColor.ARGB32.opaque(stack.getOrDefault(ModComponents.SLIME_COLOR, 0)), Items.SLIME_BALL);
    }

    @SubscribeEvent
    public static void registerRender(EntityRenderersEvent.RegisterRenderers event) {

    }
}
