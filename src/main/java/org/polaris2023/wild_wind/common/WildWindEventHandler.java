package org.polaris2023.wild_wind.common;

import net.minecraft.core.registries.Registries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.entity.Firefly;
import org.polaris2023.wild_wind.common.entity.Glare;
import org.polaris2023.wild_wind.common.entity.Piranha;
import org.polaris2023.wild_wind.common.entity.Trout;
import org.polaris2023.wild_wind.common.init.*;
import org.polaris2023.wild_wind.common.item.modified.ModBannerItem;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = WildWindMod.MOD_ID)
public class WildWindEventHandler {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new ModBannerItem.RenderBannerItem(), ModItems.BANNER);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.FIREFLY.get(), Firefly.createAttributes().build());
        event.put(ModEntityTypes.GLARE.get(), Glare.createAttributes().build());
        event.put(ModEntityTypes.TROUT.get(), Trout.createAttributes().build());
        event.put(ModEntityTypes.PIRANHA.get(), Piranha.createAttributes().build());
    }

    @SubscribeEvent
    public static void register(RegisterEvent event) {
        event.register(Registries.ITEM, helper -> ModVanillaCompat.setup());
    }

}