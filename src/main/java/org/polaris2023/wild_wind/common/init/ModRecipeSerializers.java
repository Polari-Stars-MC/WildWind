package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.recipe.CookingPotRecipe;

public class ModRecipeSerializers {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, WildWindMod.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, CookingPotRecipe.Serializer> COOKING_POT = RECIPE_SERIALIZERS.register("cooking_pot", CookingPotRecipe.Serializer::new);

}