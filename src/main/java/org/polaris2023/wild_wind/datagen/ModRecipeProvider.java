package org.polaris2023.wild_wind.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.polaris2023.wild_wind.common.init.ModItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    public final List<RecipeBuilder> list = new ArrayList<>();

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        add(smelting(ModItems.RAW_TROUT, RecipeCategory.FOOD, ModItems.COOKED_TROUT, 0.35F, 200));
//        add(SimpleCookingRecipeBuilder
//                .smelting(Ingredient.of(ModItems.RAW_TROUT), RecipeCategory.FOOD, ModItems.COOKED_TROUT, 0.35F, 200)
//                .unlockedBy("cooking_trout", has(ModItems.RAW_TROUT)));

        list.forEach(b -> b.save(recipeOutput));
    }

    public static SimpleCookingRecipeBuilder smelting(
            ItemLike input, RecipeCategory category, ItemLike result, float exp, int cookingTime
    ) {
        return SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), category, result, exp, cookingTime)
                .unlockedBy(BuiltInRegistries.ITEM.getKey(input.asItem()).toString(), has(input));
    }

    public void add(RecipeBuilder builder) {
        list.add(builder);
    }
}
