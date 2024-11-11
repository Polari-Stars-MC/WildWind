package org.polaris2023.wild_wind.common.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;

/**
 * basic item using by datagen or more
 */
public class BasicItem extends Item {
    public BasicItem(Properties properties) {
        super(properties);
    }

    public BasicItem(Properties properties, Consumer<FoodProperties.Builder> consumer) {
        super(properties.food(food(consumer)));
    }

    private static FoodProperties food(Consumer<FoodProperties.Builder> consumer) {
        FoodProperties.Builder t = new FoodProperties.Builder();
        consumer.accept(t);
        return t.build();
    }
}
