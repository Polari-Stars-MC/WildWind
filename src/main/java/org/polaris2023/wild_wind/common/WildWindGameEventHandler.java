package org.polaris2023.wild_wind.common;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.entity.Firefly;

@EventBusSubscriber(modid = WildWindMod.MOD_ID)
public class WildWindGameEventHandler {
    @SubscribeEvent
    public static void hurtEvent(LivingDamageEvent.Post event) {
        if (event.getEntity() instanceof Firefly firefly) {
            firefly.clearTicker();
        }
    }
}
