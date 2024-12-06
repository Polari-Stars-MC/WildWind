package org.polaris2023.wild_wind.common.init;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.wild_wind.common.recipe.CookingPotRecipe;

import static org.polaris2023.wild_wind.common.init.ModInitializer.RECIPES;

public class ModRecipes {
    public static final DeferredHolder<RecipeType<?>, RecipeType<CookingPotRecipe>> COOKING_POT =
            register("cooking_pot");


    private static <T extends Recipe<?>> DeferredHolder<RecipeType<?>,RecipeType<T>> register(String name) {
        return RECIPES.register(name, () -> RecipeType.register(name));
    }
}
