package org.polaris2023.wild_wind.common.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;

/**
 * basic item using by datagen or more
 */
public class BasicItem extends Item {
    BasicItem(Properties properties) {
        super(properties);
    }

    BasicItem(Properties properties, int maxCount) {
        super(properties.stacksTo(maxCount));
    }

    public static BasicItem stackToMax(Properties properties) {
        return new BasicItem(properties);
    }

    public static BasicItem stackTo1(Properties properties) {
        return new BasicItem(properties, 1);
    }

    public static BasicItem stackTo16(Properties properties) {
        return new BasicItem(properties, 16);
    }

    public static BasicItem simpleFoodByMax(Consumer<FoodProperties.Builder> consumer) {
        return  new BasicItem(new Properties(), consumer);
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
