package org.polaris2023.wild_wind.common;

import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.entity.Firefly;
import org.polaris2023.wild_wind.common.entity.Glare;
import org.polaris2023.wild_wind.common.entity.Trout;
import org.polaris2023.wild_wind.common.init.ModEntities;
import org.polaris2023.wild_wind.common.init.ModFeatures;
import org.polaris2023.wild_wind.common.init.ModInitializer;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = WildWindMod.MOD_ID)
public class WildWindEventHandler {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.FIREFLY.get(), Firefly.createAttributes().build());
        event.put(ModEntities.GLARE.get(), Glare.createAttributes().build());
        event.put(ModEntities.TROUT.get(), Trout.createAttributes().build());
    }

    @SubscribeEvent
    public static void register(RegisterEvent event) {
        event.register(Registries.FEATURE, helper -> ModFeatures.init(helper::register));
    }

    public static void modConstruction(IEventBus bus) {
        ModInitializer.init(bus);
    }
}
