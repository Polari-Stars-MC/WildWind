package org.polaris2023.wild_wind.common.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * basic item using by datagen or more
 * @see org.polaris2023.wild_wind.datagen.model.ModItemModelProvider
 */
public class BasicItem extends Item {
    protected BasicItem(Properties properties) {
        super(properties);
    }

    protected BasicItem(Properties properties, int maxCount) {
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

    public static BasicItem simpleFoodByMax(Properties properties,Supplier<FoodProperties> supplier) {
        return  new BasicItem(properties, supplier.get());
    }

    public static BasicItem simpleFoodByMax(Supplier<FoodProperties> supplier) {
        return  new BasicItem(new Properties(), supplier.get());
    }
    public static BasicItem simpleFoodBy1(Supplier<FoodProperties> supplier) {
        return  new BasicItem(new Properties().stacksTo(1), supplier.get());
    }
    public static BasicItem simpleFoodBy16(Supplier<FoodProperties> supplier) {
        return  new BasicItem(new Properties().stacksTo(16), supplier.get());
    }

    public static BasicItem simpleFoodBy16(Properties properties, Supplier<FoodProperties> supplier) {
        return  new BasicItem(properties.stacksTo(16), supplier.get());
    }



    protected BasicItem(Properties properties, FoodProperties foodProperties) {
        super(properties.food(foodProperties));
    }
}
