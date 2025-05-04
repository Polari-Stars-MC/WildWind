package org.polaris2023.wild_wind.common.event.mod;

import net.minecraft.core.registries.Registries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.init.ModFeatures;
import org.polaris2023.wild_wind.common.init.ModVanillaCompat;
import org.polaris2023.wild_wind.common.item.modified.ModBannerItem;

/**
 * @author : baka4n
 * {@code @Date : 2025/05/05 00:06:00}
 */
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = WildWindMod.MOD_ID)
public class RegisterEvents {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new ModBannerItem.RenderBannerItem(), ModBlocks.BANNER.asItem());
    }

    @SubscribeEvent
    public static void register(RegisterEvent event) {
        event.register(Registries.FEATURE, helper -> ModFeatures.init(helper::register));
        event.register(Registries.ITEM, helper -> ModBlocks.registerBlockItem(helper::register));
        event.register(Registries.ITEM, helper -> ModVanillaCompat.setup());
    }
}
