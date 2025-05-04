package org.polaris2023.wild_wind.common.event.mod;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.entity.Firefly;
import org.polaris2023.wild_wind.common.entity.Glare;
import org.polaris2023.wild_wind.common.entity.Piranha;
import org.polaris2023.wild_wind.common.entity.Trout;
import org.polaris2023.wild_wind.common.init.ModEntities;

/**
 * @author : baka4n
 * {@code @Date : 2025/05/05 00:08:16}
 */
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = WildWindMod.MOD_ID)
public class EntityEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.FIREFLY.get(), Firefly.createAttributes().build());
        event.put(ModEntities.GLARE.get(), Glare.createAttributes().build());
        event.put(ModEntities.TROUT.get(), Trout.createAttributes().build());
        event.put(ModEntities.PIRANHA.get(), Piranha.createAttributes().build());
    }
}
