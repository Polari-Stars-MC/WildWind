package org.polaris2023.wild_wind.common;

import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.entity.Firefly;
import org.polaris2023.wild_wind.common.entity.Glare;
import org.polaris2023.wild_wind.common.entity.Piranha;
import org.polaris2023.wild_wind.common.entity.Trout;
import org.polaris2023.wild_wind.common.init.*;
import org.polaris2023.wild_wind.common.init.tags.ModItemTags;
import org.polaris2023.wild_wind.common.item.modified.ModBannerItem;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = WildWindMod.MOD_ID)
public class WildWindEventHandler {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new ModBannerItem.RenderBannerItem(), ModBlocks.BANNER.asItem());
    }

    private static void food(DataComponentPatch.Builder builder, ModFoods food) {
        builder.set(DataComponents.FOOD, food.get());
    }
    private static <T> void component(DataComponentPatch.Builder builder, DataComponentType<T> component, T t) {

        builder.set(component, t);
    }

    @SubscribeEvent
    public static void modifyDefaultComponent(ModifyDefaultComponentsEvent event) {
        event.modify(Items.EGG, builder -> food(builder, ModFoods.EGG));
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.FIREFLY.get(), Firefly.createAttributes().build());
        event.put(ModEntities.GLARE.get(), Glare.createAttributes().build());
        event.put(ModEntities.TROUT.get(), Trout.createAttributes().build());
        event.put(ModEntities.PIRANHA.get(), Piranha.createAttributes().build());
    }

    @SubscribeEvent
    public static void register(RegisterEvent event) {
        event.register(Registries.FEATURE, helper -> ModFeatures.init(helper::register));
        event.register(Registries.ITEM, helper -> ModBlocks.registerBlockItem(helper::register));
        event.register(Registries.ITEM, helper -> ModVanillaCompat.setup());
    }

}