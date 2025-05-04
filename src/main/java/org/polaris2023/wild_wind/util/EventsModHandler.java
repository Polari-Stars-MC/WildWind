package org.polaris2023.wild_wind.util;

import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.polaris2023.wild_wind.common.init.ModFoods;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author : baka4n
 * {@code @Date : 2025/05/05 00:05:03}
 */
public class EventsModHandler {
    public static Consumer<DataComponentPatch.Builder> food(Supplier<FoodProperties> food) {
        return component(DataComponents.FOOD, food);
    }
    public static <T> Consumer<DataComponentPatch.Builder> component(DataComponentType<T> component, Supplier<T> t) {
        return component(component, t.get());
    }
    public static <T> Consumer<DataComponentPatch.Builder> component(DataComponentType<T> component, T t) {
        return (builder) -> {
            builder.set(component, t);
        };
    }


    public static <T> void component(BiConsumer<ItemLike, Consumer<DataComponentPatch.Builder>> modify , Supplier<DataComponentType<T>> type, T t, Item... items) {
        component(modify, type.get(), t, items);
    }

    public static <T> void component(BiConsumer<ItemLike, Consumer<DataComponentPatch.Builder>> modify , DataComponentType<T> type, T t, Item... items) {
        for (Item item : items) {
            modify.accept(item, component(type, t));
        }
    }
}
