package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.recipe.CookingPotRecipe;

public class ModRecipeTypes {

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, WildWindMod.MOD_ID);

    public static final DeferredHolder<RecipeType<?>, RecipeType<CookingPotRecipe>> COOKING_POT = register("cooking_pot");

    private static <T extends Recipe<?>> DeferredHolder<RecipeType<?>, RecipeType<T>> register(String name) {
        return RECIPE_TYPES.register(name, () -> RecipeType.register(name));
    }

}