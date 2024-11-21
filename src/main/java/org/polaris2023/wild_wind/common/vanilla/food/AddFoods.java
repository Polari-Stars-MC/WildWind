package org.polaris2023.wild_wind.common.vanilla.food;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.polaris2023.wild_wind.common.init.ModFoods;

public class AddFoods {

    public static void init() {
        food(Items.EGG, ModFoods.EGG);
        food(Items.TURTLE_EGG, ModFoods.EGG);
        food(Items.BROWN_MUSHROOM, ModFoods.BROWN_MUSHROOM);
        food(Items.RED_MUSHROOM, ModFoods.RED_MUSHROOM);
        food(Items.CRIMSON_FUNGUS, ModFoods.CRIMSON_FUNGUS);
        food(Items.WARPED_FUNGUS, ModFoods.WARPED_FUNGUS);
    }

    public static void food(Item item, ModFoods foods) {
        DataComponentMap.Builder.SimpleMap components = (DataComponentMap.Builder.SimpleMap) item.components();
        components.map().put(DataComponents.FOOD, foods.get());
    }
}
