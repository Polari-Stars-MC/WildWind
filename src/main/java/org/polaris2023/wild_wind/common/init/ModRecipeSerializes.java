package org.polaris2023.wild_wind.common.init;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.wild_wind.common.recipe.CookingPotRecipe;

import static org.polaris2023.wild_wind.common.init.ModInitializer.RECIPES_SERIALIZERS;

public class ModRecipeSerializes {
    public static final DeferredHolder<RecipeSerializer<?>, CookingPotRecipe.Serializer> COOKING_POT =
            RECIPES_SERIALIZERS.register("cooking_pot", CookingPotRecipe.Serializer::new);
}
