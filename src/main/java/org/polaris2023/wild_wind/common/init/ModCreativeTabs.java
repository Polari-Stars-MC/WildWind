package org.polaris2023.wild_wind.common.init;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.wild_wind.WildWindMod;

import java.util.Locale;
import java.util.function.Supplier;

import static org.polaris2023.wild_wind.common.init.ModInitializer.TABS;


@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = WildWindMod.MOD_ID)
public enum ModCreativeTabs implements Supplier<CreativeModeTab> {
    @I18n(en_us = "Wild Wind Tags", zh_cn = "原野之风", zh_tw = "原野之風")
    WILD_WIND(ModItems.GLOW_POWDER::toStack,
            () -> (__, output) -> {
                for (DeferredHolder<Item, ? extends Item> item : ModInitializer.entry(Item.class)) {
                    output.accept(item.get());
                }
            });
    private final DeferredHolder<CreativeModeTab, CreativeModeTab> tabs;
    ModCreativeTabs(Supplier<ItemStack> icon,
                    Supplier<CreativeModeTab.DisplayItemsGenerator> parameters) {
        tabs = TABS.register(name().toLowerCase(Locale.ROOT), () -> CreativeModeTab
                .builder()
                .icon(icon)
                .title(Component.translatable("tabs.%s.%s.title"
                        .formatted(
                                WildWindMod.MOD_ID,
                                name().toLowerCase(Locale.ROOT))))
                .displayItems(parameters.get())
                .build());
    }


    @SubscribeEvent
    public static void buildGroup(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
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

    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public CreativeModeTab get() {
        return tabs.get();
    }
}
