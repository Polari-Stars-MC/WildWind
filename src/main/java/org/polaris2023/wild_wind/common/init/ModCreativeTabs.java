package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wild_wind.WildWindMod;

import static org.polaris2023.wild_wind.common.init.ModItems.ITEMS;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = WildWindMod.MOD_ID)
public class ModCreativeTabs {
    static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, WildWindMod.MOD_ID);
    public static final
        DeferredHolder<CreativeModeTab, CreativeModeTab> WILD_WIND = TABS.register("wild_wind", () -> CreativeModeTab
                .builder()
                .icon(ModItems.GLOW_POWDER::toStack)
                .displayItems((parameters, output) -> {
                    for (DeferredHolder<Item, ? extends Item> entry : ITEMS.getEntries()) {
                        output.accept(entry.get());
                    }
                }).build());

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
