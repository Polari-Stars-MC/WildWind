package org.polaris2023.wild_wind.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.polaris2023.wild_wind.common.init.ModItems;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    public final List<RecipeBuilder> list = new ArrayList<>();

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        add(smelting(ModItems.RAW_TROUT, RecipeCategory.FOOD, ModItems.COOKED_TROUT, 0.35F));
        add(smelting(Ingredient.of(Items.EGG, Items.TURTLE_EGG), RecipeCategory.FOOD, ModItems.COOKED_EGG, 0.35F));
        add(smelting(ModItems.LIVING_TUBER, RecipeCategory.FOOD, ModItems.COOKED_LIVING_TUBER, 0.35F));
        add(smelting(ModItems.DOUGH, RecipeCategory.FOOD, Items.BREAD, 0.35F));// input category result exp
        add(smelting(Items.CARROT, RecipeCategory.FOOD, ModItems.BAKED_CARROT, 0.35F));
        add(smelting(Items.BEETROOT, RecipeCategory.FOOD, ModItems.BAKED_BEETROOT, 0.35F));
        add(ShapelessRecipeBuilder
                .shapeless(RecipeCategory.FOOD, ModItems.FISH_CHOWDER)
                .unlockedBy("recipe_fish_chowder", has(ModItems.RAW_TROUT))
                .requires(Ingredient.of(Items.COD, Items.SALMON, ModItems.RAW_TROUT))
                .requires(Items.KELP)
                .requires(Ingredient.of(Items.BROWN_MUSHROOM, Items.RED_MUSHROOM))
                .requires(Items.BOWL));
        add(ShapelessRecipeBuilder
                .shapeless(RecipeCategory.FOOD, ModItems.CHEESE)
                .unlockedBy("recipe_cheese", has(Items.SUGAR))
                .requires(Items.BROWN_MUSHROOM)
                .requires(Items.SUGAR)
                .requires(Items.MILK_BUCKET));
        add(ShapelessRecipeBuilder
                .shapeless(RecipeCategory.FOOD, ModItems.RUSSIAN_SOUP)
                .unlockedBy("recipe_russian_soup", has(ModItems.CHEESE))
                .requires(Items.BEETROOT)
                .requires(Items.POTATO)
                .requires(Items.BEEF)
                .requires(ModItems.CHEESE)
                .requires(Items.BOWL));
        add(ShapelessRecipeBuilder
                .shapeless(RecipeCategory.FOOD, ModItems.CHEESE_PUMPKIN_SOUP)
                .unlockedBy("recipe_cheese_pumpkin_soup", has(ModItems.CHEESE))
                .requires(Items.PUMPKIN)
                .requires(Items.BOWL)
                .requires(ModItems.CHEESE));
        add(ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, ModItems.FLOUR)
                .unlockedBy("recipe_flour", has(Items.WHEAT))
                .requires(Items.WHEAT));
        add(ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, ModItems.DOUGH)
                .unlockedBy("recipe_dough", has(ModItems.FLOUR))
                .requires(ModItems.FLOUR, 3)
                .requires(Items.WATER_BUCKET));
        add(ShapelessRecipeBuilder
                .shapeless(RecipeCategory.FOOD, ModItems.CANDY)
                .unlockedBy("recipe_candy", has(Items.SUGAR))
                .requires(Items.HONEY_BOTTLE)
                .requires(Items.SUGAR)
                .requires(Ingredient
                        .of(
                                new ItemStack(Items.GLOW_BERRIES),
                                new ItemStack(Items.APPLE),
                                new ItemStack(Items.SWEET_BERRIES, 2))));
        add(ShapelessRecipeBuilder
                .shapeless(RecipeCategory.FOOD, ModItems.BERRY_CAKE)
                .unlockedBy("recipe_berry_cake", has(Items.SWEET_BERRIES))
                .requires(Items.SWEET_BERRIES, 2)
                .requires(Items.GLOW_BERRIES)
                .requires(Items.SUGAR)
                .requires(Items.EGG));
        add(ShapelessRecipeBuilder
                .shapeless(RecipeCategory.FOOD, ModItems.APPLE_CAKE)
                .unlockedBy("recipe_apple_cake", has(Items.APPLE))
                .requires(Items.APPLE)
                .requires(Items.SUGAR)
                .requires(Items.EGG));
        list.forEach(b -> b.save(recipeOutput));
    }

    public static SimpleCookingRecipeBuilder smelting(
            Ingredient input, RecipeCategory category, ItemLike result, float exp, int cookingTime
    ) {
        ItemStack[] items = input.getItems();
        Item item = items[0].getItem();
        return SimpleCookingRecipeBuilder.smelting(input, category, result, exp, cookingTime)
                .unlockedBy(BuiltInRegistries.ITEM.getKey(item).toString(), has(item));
    }

    public static SimpleCookingRecipeBuilder smelting(
            ItemLike input, RecipeCategory category, ItemLike result, float exp, int cookingTime
    ) {
        return SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), category, result, exp, cookingTime)
                .unlockedBy(BuiltInRegistries.ITEM.getKey(input.asItem()).toString(), has(input));
    }
    public static SimpleCookingRecipeBuilder smelting(
            Ingredient input, RecipeCategory category, ItemLike result, float exp
    ) {
        return  smelting(input, category, result, exp, 200);
    }

    public static SimpleCookingRecipeBuilder smelting(
            ItemLike input, RecipeCategory category, ItemLike result, float exp
    ) {
        return smelting(input, category, result, exp,200);
    }

    public void add(RecipeBuilder builder) {
        list.add(builder);
    }
}
