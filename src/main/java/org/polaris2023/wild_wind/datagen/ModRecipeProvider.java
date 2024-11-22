package org.polaris2023.wild_wind.datagen;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.init.ModItems;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;


public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    public final List<RecipeBuilder> list = new ArrayList<>();


    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        addSmeltingRecipes();
        addShapedRecipe();
        addShapelessRecipe();
        list.forEach(b -> {
            b.save(recipeOutput);

        });
    }

    protected void addSmeltingRecipes() {
        add(smelting(ModItems.RAW_TROUT, RecipeCategory.FOOD, ModItems.COOKED_TROUT, 0.35F));
        add(smelting(Ingredient.of(Items.EGG, Items.TURTLE_EGG), RecipeCategory.FOOD, ModItems.COOKED_EGG, 0.35F));
        add(smelting(ModItems.LIVING_TUBER, RecipeCategory.FOOD, ModItems.COOKED_LIVING_TUBER, 0.35F));
        add(smelting(ModItems.DOUGH, RecipeCategory.FOOD, Items.BREAD, 0.35F));// input category result exp
        add(smelting(Items.CARROT, RecipeCategory.FOOD, ModItems.BAKED_CARROT, 0.35F));
        add(smelting(Items.BEETROOT, RecipeCategory.FOOD, ModItems.BAKED_BEETROOT, 0.35F));
    }

    protected static Criterion<InventoryChangeTrigger.TriggerInstance> has(ItemLike... likes) {
        return inventoryTrigger(ItemPredicate.Builder
                .item()
                .of(likes).build());
    }

    protected void addShapedRecipe() {
        add(shaped(RecipeCategory.MISC, ModItems.MAGIC_FLUTE, 1, magic_flute -> {
            unlockedBy(magic_flute, Items.BONE);
            unlockedBy(magic_flute, ModItems.LIVING_TUBER);
            magic_flute
                    .pattern("BRB")
                    .group("magic_flute")
                    .define('B', Items.BONE)
                    .define('R', ModItems.LIVING_TUBER);
        }));
        add(shaped(RecipeCategory.MISC, ModBlocks.COOKING_POT_ITEM.get(), 1,
                builder -> {
            builder
                    .pattern("I I")
                    .pattern("III")
                    .pattern("PCP")
                    .group("cooking_pot")
                    .define('I', Items.IRON_INGOT)
                    .define('P', ItemTags.LOGS)
                    .define('C', ItemTags.COALS);
        }));
    }

    protected static  <T extends RecipeBuilder> void unlockedBy(T t, ItemLike... likes) {
        StringBuilder sb = new StringBuilder("has");
        for (ItemLike like : likes) {
            ResourceLocation key = BuiltInRegistries.ITEM.getKey(like.asItem());
            sb.append("_").append(key);
        }
        t.unlockedBy(sb.toString().toLowerCase(Locale.ROOT), has(likes));
    }



    protected void addShapelessRecipe() {


        add(shapeless(RecipeCategory.FOOD, ModItems.FISH_CHOWDER, 1, fish_chowder -> {
            unlockedBy(fish_chowder, ModItems.RAW_TROUT, Items.COD, Items.SALMON);
            unlockedBy(fish_chowder, Items.BROWN_MUSHROOM, Items.RED_MUSHROOM);
            unlockedBy(fish_chowder, Items.KELP);
            unlockedBy(fish_chowder, Items.BOWL);
            fish_chowder
                    .requires(Ingredient.of(Items.COD, Items.SALMON, ModItems.RAW_TROUT))
                    .requires(Items.KELP)
                    .requires(Ingredient.of(Items.BROWN_MUSHROOM, Items.RED_MUSHROOM))
                    .requires(Items.BOWL);
        }));
        add(shapeless(RecipeCategory.FOOD, ModItems.CHEESE, 1, cheese -> {
            unlockedBy(cheese,Items.BROWN_MUSHROOM);
            unlockedBy(cheese,Items.SUGAR);
            unlockedBy(cheese,Items.MILK_BUCKET);
            cheese
                    .requires(Items.BROWN_MUSHROOM)
                    .requires(Items.SUGAR)
                    .requires(Items.MILK_BUCKET);
        }));
        add(shapeless(RecipeCategory.FOOD, ModItems.RUSSIAN_SOUP, 1, russian_soup -> {
            unlockedBy(russian_soup, ModItems.CHEESE);
            unlockedBy(russian_soup, Items.BEETROOT);
            unlockedBy(russian_soup, Items.POTATO);
            unlockedBy(russian_soup, Items.BEEF);
            unlockedBy(russian_soup, Items.BOWL);
            russian_soup
                    .requires(Items.BEETROOT)
                    .requires(Items.POTATO)
                    .requires(Items.BEEF)
                    .requires(ModItems.CHEESE)
                    .requires(Items.BOWL);
        }));
        add(shapeless(RecipeCategory.FOOD, ModItems.CHEESE_PUMPKIN_SOUP, 1, cheese_pumpkin_soup -> {
            unlockedBy(cheese_pumpkin_soup, ModItems.CHEESE);
            unlockedBy(cheese_pumpkin_soup, Items.PUMPKIN);
            unlockedBy(cheese_pumpkin_soup, Items.BOWL);
            cheese_pumpkin_soup
                    .requires(Items.PUMPKIN)
                    .requires(Items.BOWL)
                    .requires(ModItems.CHEESE);
        }));
        add(shapeless(RecipeCategory.MISC, ModItems.FLOUR, 1, flour -> {
            unlockedBy(flour, Items.WHEAT);
            flour
                    .requires(Items.WHEAT);
        }));
        add(shapeless(RecipeCategory.MISC, ModItems.DOUGH, 1, dough -> {
            unlockedBy(dough, ModItems.FLOUR);
            unlockedBy(dough, Items.WATER_BUCKET);
            dough
                    .requires(ModItems.FLOUR, 3)
                    .requires(Items.WATER_BUCKET);
        }));
        add(shapeless(RecipeCategory.FOOD, ModItems.CANDY, 1, candy -> {
            unlockedBy(candy, Items.SUGAR);
            unlockedBy(candy, Items.HONEY_BOTTLE);
            unlockedBy(candy, Items.GLOW_BERRIES, Items.APPLE, Items.SWEET_BERRIES);
            candy
                    .requires(Items.HONEY_BOTTLE)
                    .requires(Items.SUGAR)
                    .requires(Ingredient
                            .of(
                                    new ItemStack(Items.GLOW_BERRIES),
                                    new ItemStack(Items.APPLE),
                                    new ItemStack(Items.SWEET_BERRIES, 2)));
        }));
        add(shapeless(RecipeCategory.FOOD, ModItems.BERRY_CAKE, 1, berry_cake -> {
            unlockedBy(berry_cake, Items.SWEET_BERRIES);
            unlockedBy(berry_cake, Items.GLOW_BERRIES);
            unlockedBy(berry_cake, Items.SUGAR);
            unlockedBy(berry_cake, Items.EGG);
            berry_cake
                    .requires(Items.SWEET_BERRIES, 2)
                    .requires(Items.GLOW_BERRIES)
                    .requires(Items.SUGAR)
                    .requires(Items.EGG);
        }));
        add(shapeless(RecipeCategory.FOOD, ModItems.APPLE_CAKE, 1, apple_cake -> {
            unlockedBy(apple_cake, Items.APPLE);
            unlockedBy(apple_cake, Items.SUGAR);
            unlockedBy(apple_cake, Items.EGG);
            apple_cake
                    .requires(Items.APPLE)
                    .requires(Items.SUGAR)
                    .requires(Items.EGG);
        }));
    }

    public static ShapedRecipeBuilder shaped(
            RecipeCategory category, ItemLike result, int count, Consumer<ShapedRecipeBuilder> consumer
    ) {
        ShapedRecipeBuilder shaped = ShapedRecipeBuilder.shaped(category, result, count);
        consumer.accept(shaped);
        return shaped;
    }

    public static ShapelessRecipeBuilder shapeless(RecipeCategory category, ItemLike result, int count, Consumer<ShapelessRecipeBuilder> consumer) {
        ShapelessRecipeBuilder shapeless = ShapelessRecipeBuilder.shapeless(category, result, count);
        consumer.accept(shapeless);
        return shapeless;
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