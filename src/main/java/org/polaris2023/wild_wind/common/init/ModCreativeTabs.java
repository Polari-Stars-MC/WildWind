package org.polaris2023.wild_wind.common.init;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.polaris2023.wild_wind.WildWindMod;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = WildWindMod.MOD_ID)
public class ModCreativeTabs {

    @SubscribeEvent
    public static void buildGroup(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.GLOW_MUCUS);
            event.accept(ModItems.GLOW_POWDER);
            event.accept(ModItems.LIVING_TUBER);
        }
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.FIREFLY_SPAWN_EGG);
        }
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(ModItems.FIREFLY_SPAWN_EGG);
        }
    }
}
