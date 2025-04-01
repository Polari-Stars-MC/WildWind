package org.polaris2023.wild_wind.util.interfaces.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import org.polaris2023.wild_wind.common.init.ModInitializer;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.polaris2023.wild_wind.common.init.ModInitializer.ITEMS;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/01 21:55:10}
 */
public interface ItemRegistry {

    static DeferredItem<Item> simpleItem(String name) {
        return ITEMS.registerSimpleItem(name);
    }

    static DeferredItem<Item> item(String name, Function<Item.Properties, Item> toItemFunction) {
        return ITEMS.registerItem(name, toItemFunction);
    }

    static DeferredItem<Item> simpleItem(String name, Consumer<Item.Properties> consumer) {
        return item(name, properties -> {
            consumer.accept(properties);
            return new Item(properties);
        });
    }

    static DeferredItem<Item> simpleItem(String name, Supplier<FoodProperties> food) {
        return simpleItem(name, properties -> properties.food(food.get()));
    }

    static DeferredItem<Item> simpleItem(String name, Consumer<Item.Properties> consumer, Supplier<FoodProperties> food) {
        return simpleItem(name, properties -> consumer.accept(properties.food(food.get())));
    }

    static <T extends Item> DeferredItem<T> register(String name, Function<Item.Properties, T> item) {
        return ITEMS.registerItem(name, item);
    }

    static <T extends Item> DeferredItem<T> register(String name, Supplier<T> item) {
        return ITEMS.register(name, item);
    }

    static DeferredItem<DeferredSpawnEggItem> register(String name,
                                                       Supplier<? extends EntityType<? extends Mob>> type,
                                                       int backgroundColor,
                                                       int highlightColor,
                                                       Consumer<Item.Properties> consumer) {
        return ModInitializer.ITEMS.registerItem(name, properties -> {
            consumer.accept(properties);
            return new DeferredSpawnEggItem(type, backgroundColor, highlightColor, properties);
        });
    }

    static DeferredItem<DeferredSpawnEggItem> register(String name,
                                                       Supplier<? extends EntityType<? extends Mob>> type,
                                                       int backgroundColor,
                                                       int highlightColor) {
        return register(name, type, backgroundColor, highlightColor, properties -> {});
    }

    static <T extends Block> DeferredItem<BlockItem> register(String name, DeferredBlock<T> block) {
        return ITEMS.registerSimpleBlockItem(name, block);
    }

    static <T extends Block> DeferredItem<BlockItem> register(String name, DeferredBlock<T> block, Supplier<FoodProperties> supplier) {
        return ITEMS.registerItem(name, properties -> new BlockItem(block.get(), properties.food(supplier.get())));
    }

    static <SST extends StandingSignBlock, WST extends WallSignBlock> DeferredItem<SignItem> registerSign(String name,
                                                                                                          DeferredBlock<SST> standing,
                                                                                                          DeferredBlock<WST> wall) {
        return ITEMS.registerItem(name, properties -> new SignItem(properties.stacksTo(16), standing.get(), wall.get()));
    }

    static <SST extends CeilingHangingSignBlock, WST extends WallHangingSignBlock> DeferredItem<HangingSignItem> registerHangingSign(String name,
                                                                                                                                     DeferredBlock<SST> standing,
                                                                                                                                     DeferredBlock<WST> wall) {
        return ITEMS.registerItem(name, properties -> new HangingSignItem(standing.get(), wall.get(), properties.stacksTo(16)));
    }
}
