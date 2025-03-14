package org.polaris2023.wild_wind.common.event_handler;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.event.InvisibleItemFrameEvent;

@EventBusSubscriber(modid = WildWindMod.MOD_ID)
public class InvisibleItemFrameEventHandler {
    @SubscribeEvent
    public static void onEntityUse(PlayerInteractEvent.EntityInteract event) {
        InteractionResult interactionResult = InvisibleItemFrameEvent.run(event.getLevel(), event.getEntity(), event.getHand(), event.getTarget(), null);

        if (interactionResult.consumesAction())
            event.setCanceled(true);
    }
}