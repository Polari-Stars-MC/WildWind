package org.polaris2023.wild_wind.util.registry;

import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import org.polaris2023.wild_wind.common.init.ModFoods;
import org.polaris2023.wild_wind.common.init.ModItems;

import java.util.Locale;
import java.util.function.Supplier;

public class ModItemRegUtils {

    public static DeferredItem<Item> normal(String name) {
        return ModItems.ITEMS.register(name, () -> new Item(new Item.Properties()));
    }

    public static DeferredItem<Item> normal(String name, Supplier<Item.Properties> properties) {
        return ModItems.ITEMS.register(name, () -> new Item(properties.get()));
    }

    public static DeferredItem<Item> spawnEgg(String name, Supplier<? extends EntityType<? extends Mob>> type, int bgColor, int hlColor) {
        return ModItems.ITEMS.register(name + "_spawn_egg", () -> new DeferredSpawnEggItem(type, bgColor, hlColor, new Item.Properties()));
    }

    public static DeferredItem<Item> food(String name, Supplier<Item.Properties> properties) {
        return ModItems.ITEMS.register(name, () -> new Item(properties.get().food(ModFoods.valueOf(name.toUpperCase(Locale.ROOT)).get())));
    }

    public static DeferredItem<Item> food(String name) {
        return ModItems.ITEMS.register(name, () -> new Item(new Item.Properties().food(ModFoods.valueOf(name.toUpperCase(Locale.ROOT)).get())));
    }

    public static DeferredItem<SignItem> sign(String name, Supplier<StandingSignBlock> standing, Supplier<WallSignBlock> wall) {
        return ModItems.ITEMS.register(name, () -> new SignItem(new Item.Properties().stacksTo(16), standing.get(), wall.get()));
    }

    public static DeferredItem<HangingSignItem> hangingSign(String name, Supplier<CeilingHangingSignBlock> standing, Supplier<WallHangingSignBlock> wall) {
        return ModItems.ITEMS.register(name, () -> new HangingSignItem(standing.get(), wall.get(), new Item.Properties().stacksTo(16)));
    }

    public static DeferredItem<Item> mobBucket(String name, Supplier<? extends EntityType<? extends Mob>> type) {
        return ModItems.ITEMS.register(name + "_bucket", () -> new MobBucketItem(type.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH,
                new Item.Properties().stacksTo(1).component(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY)));
    }

    public static DeferredItem<BoatItem> boat(String name, boolean hasChest, EnumProxy<Boat.Type> type) {
        return ModItems.ITEMS.register(name, () -> new BoatItem(hasChest, type.getValue(), new Item.Properties().stacksTo(1)));
    }

}