package org.polaris2023.wild_wind.datagen;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.init.ModItems;
import org.polaris2023.wild_wind.common.init.items.ModBaseItems;
import org.polaris2023.wild_wind.common.init.items.foods.ModBaseFoods;
import org.polaris2023.wild_wind.datagen.custom.recipe.CookingPotRecipeBuilder;
import org.polaris2023.wild_wind.util.Helpers;
import org.polaris2023.wild_wind.util.data.ModBlockFamilies;

import static org.polaris2023.wild_wind.datagen.ModDyedArray.CARPET_BLOCK;
import static org.polaris2023.wild_wind.datagen.ModDyedArray.CONCRETE_BLOCK;
import static org.polaris2023.wild_wind.datagen.ModDyedArray.CONCRETE_POWDER_BLOCK;
import static org.polaris2023.wild_wind.datagen.ModDyedArray.DYE;
import static org.polaris2023.wild_wind.datagen.ModDyedArray.GLAZED_TERRACOTTA_BLOCK;
import static org.polaris2023.wild_wind.datagen.ModDyedArray.WOOL_BLOCK;
import static org.polaris2023.wild_wind.util.data.RecipeUtil.shaped;
import static org.polaris2023.wild_wind.util.data.RecipeUtil.unlockedBy;


public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    public final Map<ResourceLocation, RecipeBuilder> list = new HashMap<>();


    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        addDyedRecipe(recipeOutput);
        addWoodRecipes(recipeOutput);
        addStonecuttingRecipes();
        addSmeltingRecipes();
        addShapedRecipe();
        addShapelessRecipe();
        addCookingPotRecipes();
        list.forEach((s, b) -> b.save(recipeOutput, s));
    }

    protected void addCookingPotRecipes() {

    }

    public void addWoodRecipes(RecipeOutput recipeOutput) {
        ModBlockFamilies.PALM.generateRecipes(recipeOutput);
        ModBlockFamilies.BAOBAB.generateRecipes(recipeOutput);
        ModBlockFamilies.AZALEA.generateRecipes(recipeOutput);
    }

    public void simpleCookingPot(RecipeCategory category, ItemLike result, FluidStack stack, Consumer<CookingPotRecipeBuilder> consumer) {
        CookingPotRecipeBuilder cooking = CookingPotRecipeBuilder
                .cooking(category, result);
        consumer.accept(cooking);
        add(
                "cooking_pot/",
                cooking
                        .stack(stack));
    }

    public void addDyedRecipe(RecipeOutput recipeOutput) {
        for (int i = 0; i < DYE.length; i++) {
            int finalI = i;
            add(shapeless(RecipeCategory.BUILDING_BLOCKS, GLAZED_TERRACOTTA_BLOCK[i], 1, glazed_terracotta -> {
                unlockedBy(glazed_terracotta, ModBlocks.GLAZED_TERRACOTTA, DYE[finalI]);
                glazed_terracotta
                        .requires(ModBlocks.GLAZED_TERRACOTTA)
                        .requires(DYE[finalI]);
            }), WildWindMod.MOD_ID + "/");
            add(shapeless(RecipeCategory.MISC, CARPET_BLOCK[i], 1, carpet -> {
                unlockedBy(carpet, ModBlocks.CARPET, DYE[finalI]);
                carpet
                        .requires(DYE[finalI])
                        .requires(ModBlocks.CARPET);
            }));
            Block[] ingredientArray = new Block[WOOL_BLOCK.length - 1];
            System.arraycopy(WOOL_BLOCK, 0, ingredientArray, 0, i);
            System.arraycopy(WOOL_BLOCK, i + 1, ingredientArray, i, WOOL_BLOCK.length - i - 1);
            String name = WOOL_BLOCK[finalI].builtInRegistryHolder().getRegisteredName();
            shapeless(RecipeCategory.MISC, WOOL_BLOCK[i], 1, wool -> {
                unlockedBy(wool, ModBlocks.WOOL, DYE[finalI]);
                wool
                        .requires(DYE[finalI])
                        .requires(Ingredient.of(ingredientArray))
                        .save(recipeOutput, ResourceLocation.withDefaultNamespace("dye_" + name.substring(name.indexOf(':') + 1)));
            });
            add(shapeless(RecipeCategory.MISC, CONCRETE_BLOCK[i], 1, concrete -> {
                unlockedBy(concrete, ModBlocks.CONCRETE, DYE[finalI]);
                concrete
                        .requires(DYE[finalI])
                        .requires(ModBlocks.CONCRETE);
            }));
            add(shapeless(RecipeCategory.MISC, CONCRETE_POWDER_BLOCK[i], 1, concrete_powder -> {
                unlockedBy(concrete_powder, ModBlocks.CONCRETE_POWDER, DYE[finalI]);
                concrete_powder
                        .requires(DYE[finalI])
                        .requires(ModBlocks.CONCRETE_POWDER);
            }));
        }
    }

    protected void addStonecuttingRecipes() {
        add(stonecutting(Ingredient.of(Items.STONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.STONE_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(Blocks.STONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_STONE, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.POLISHED_STONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_STONE_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.POLISHED_STONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_STONE_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.POLISHED_STONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_STONE_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(Blocks.POLISHED_GRANITE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_GRANITE_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(Blocks.POLISHED_DIORITE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_DIORITE_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(Blocks.POLISHED_ANDESITE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_ANDESITE_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.ANDESITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.ANDESITE_BRICK_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.ANDESITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.ANDESITE_BRICK_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.ANDESITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.ANDESITE_BRICK_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.ANDESITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_ANDESITE_BRICKS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.DIORITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.DIORITE_BRICK_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.DIORITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.DIORITE_BRICK_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.DIORITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.DIORITE_BRICK_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.DIORITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_DIORITE_BRICKS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.GRANITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.GRANITE_BRICK_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.GRANITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.GRANITE_BRICK_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.GRANITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.GRANITE_BRICK_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.GRANITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_GRANITE_BRICKS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.BLUE_ICE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_ICE_BRICK_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.BLUE_ICE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_ICE_BRICK_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.BLUE_ICE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_ICE_BRICK_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.BLUE_ICE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_BLUE_ICE_BRICKS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(Blocks.BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_BRICKS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(Blocks.DEEPSLATE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_DEEPSLATE_BRICKS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(Blocks.DEEPSLATE_TILES), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_DEEPSLATE_TILES, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(Blocks.MUD_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_MUD_BRICKS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.SANDSTONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_SANDSTONE_BRICKS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.RED_SANDSTONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_RED_SANDSTONE_BRICKS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_SANDSTONE_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_SANDSTONE_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_SANDSTONE_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(Blocks.SMOOTH_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_SANDSTONE_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_RED_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_RED_SANDSTONE_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_RED_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_RED_SANDSTONE_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_RED_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_RED_SANDSTONE_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(Blocks.SMOOTH_RED_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_RED_SANDSTONE_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.COBBLED_BLACKSTONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.COBBLED_BLACKSTONE_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.COBBLED_BLACKSTONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.COBBLED_BLACKSTONE_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.COBBLED_BLACKSTONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.COBBLED_BLACKSTONE_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_COBBLED_BLACKSTONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_COBBLED_BLACKSTONE_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_COBBLED_BLACKSTONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_COBBLED_BLACKSTONE_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_COBBLED_BLACKSTONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_COBBLED_BLACKSTONE_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(Blocks.POLISHED_BLACKSTONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_POLISHED_BLACKSTONE_BRICKS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_POLISHED_BLACKSTONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_POLISHED_BLACKSTONE_BRICK_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_POLISHED_BLACKSTONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_POLISHED_BLACKSTONE_BRICK_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_POLISHED_BLACKSTONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_POLISHED_BLACKSTONE_BRICK_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_GRANITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_GRANITE_BRICK_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_GRANITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_GRANITE_BRICK_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_GRANITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_GRANITE_BRICK_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_DIORITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_DIORITE_BRICK_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_DIORITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_DIORITE_BRICK_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_DIORITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_DIORITE_BRICK_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_ANDESITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_ANDESITE_BRICK_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_ANDESITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_ANDESITE_BRICK_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_ANDESITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_ANDESITE_BRICK_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_COBBLED_DEEPSLATE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_COBBLED_DEEPSLATE_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_COBBLED_DEEPSLATE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_COBBLED_DEEPSLATE_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_COBBLED_DEEPSLATE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_COBBLED_DEEPSLATE_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_DEEPSLATE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_DEEPSLATE_BRICK_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_DEEPSLATE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_DEEPSLATE_BRICK_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_DEEPSLATE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_DEEPSLATE_BRICK_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_DEEPSLATE_TILES), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_DEEPSLATE_TILE_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_DEEPSLATE_TILES), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_DEEPSLATE_TILE_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_DEEPSLATE_TILES), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_DEEPSLATE_TILE_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_TUFF_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_TUFF_BRICK_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_TUFF_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_TUFF_BRICK_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_TUFF_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_TUFF_BRICK_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_BRICK_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_BRICK_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_BRICK_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_MUD_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_MUD_BRICK_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_MUD_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_MUD_BRICK_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_MUD_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_MUD_BRICK_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.SANDSTONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.SANDSTONE_BRICK_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.SANDSTONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.SANDSTONE_BRICK_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.SANDSTONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.SANDSTONE_BRICK_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.RED_SANDSTONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_SANDSTONE_BRICK_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.RED_SANDSTONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_SANDSTONE_BRICK_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.RED_SANDSTONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_SANDSTONE_BRICK_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_SANDSTONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_SANDSTONE_BRICK_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_SANDSTONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_SANDSTONE_BRICK_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_SANDSTONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_SANDSTONE_BRICK_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_PRISMARINE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_PRISMARINE_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_PRISMARINE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_PRISMARINE_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_PRISMARINE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_PRISMARINE_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_RED_SANDSTONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_RED_SANDSTONE_BRICK_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_RED_SANDSTONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_RED_SANDSTONE_BRICK_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_RED_SANDSTONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_RED_SANDSTONE_BRICK_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(Blocks.PRISMARINE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PRISMARINE, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.POLISHED_PRISMARINE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PRISMARINE_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.POLISHED_PRISMARINE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PRISMARINE_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.POLISHED_PRISMARINE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PRISMARINE_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(Blocks.PRISMARINE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_PRISMARINE_BRICKS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.SNOW_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.SNOW_BRICK_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.SNOW_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.SNOW_BRICK_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.SNOW_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.SNOW_BRICK_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.SNOW_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_SNOW_BRICKS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(Blocks.CALCITE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_CALCITE, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.POLISHED_CALCITE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_CALCITE_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.POLISHED_CALCITE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_CALCITE_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.POLISHED_CALCITE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_CALCITE_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.CALCITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CALCITE_BRICK_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.CALCITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CALCITE_BRICK_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.CALCITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CALCITE_BRICK_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.CALCITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_CALCITE_BRICKS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(Blocks.DRIPSTONE_BLOCK), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_DRIPSTONE_BLOCK, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.POLISHED_DRIPSTONE_BLOCK), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_DRIPSTONE_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.POLISHED_DRIPSTONE_BLOCK), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_DRIPSTONE_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.POLISHED_DRIPSTONE_BLOCK), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_DRIPSTONE_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.DRIPSTONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.DRIPSTONE_BRICK_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.DRIPSTONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.DRIPSTONE_BRICK_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.DRIPSTONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.DRIPSTONE_BRICK_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.DRIPSTONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_DRIPSTONE_BRICKS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_CALCITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_CALCITE_BRICK_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_CALCITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_CALCITE_BRICK_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_CALCITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_CALCITE_BRICK_SLAB, 2), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_DRIPSTONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_DRIPSTONE_BRICK_WALL, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_DRIPSTONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_DRIPSTONE_BRICK_STAIRS, 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_DRIPSTONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_DRIPSTONE_BRICK_SLAB, 2), "stonecutting/");

        // 末地石系列切石机配方
        add(stonecutting(Ingredient.of(Blocks.END_STONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.END_STONE_STAIRS.get(), 1), "stonecutting/");
        add(stonecutting(Ingredient.of(Blocks.END_STONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.END_STONE_SLAB.get(), 2), "stonecutting/");
        add(stonecutting(Ingredient.of(Blocks.END_STONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.END_STONE_WALL.get(), 1), "stonecutting/");

        add(stonecutting(Ingredient.of(Blocks.END_STONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_END_STONE.get(), 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.POLISHED_END_STONE.get()), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_END_STONE_STAIRS.get(), 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.POLISHED_END_STONE.get()), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_END_STONE_SLAB.get(), 2), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.POLISHED_END_STONE.get()), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_END_STONE_WALL.get(), 1), "stonecutting/");

        add(stonecutting(Ingredient.of(Blocks.END_STONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_END_STONE_BRICKS.get(), 1), "stonecutting/");
        add(stonecutting(Ingredient.of(Blocks.END_STONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_END_STONE_BRICKS.get(), 1), "stonecutting/");
        add(stonecutting(Ingredient.of(Blocks.END_STONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_END_STONE_BRICKS.get(), 1), "stonecutting/");

        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_END_STONE_BRICKS.get()), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_END_STONE_BRICK_STAIRS.get(), 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_END_STONE_BRICKS.get()), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_END_STONE_BRICK_SLAB.get(), 2), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_END_STONE_BRICKS.get()), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_END_STONE_BRICK_WALL.get(), 1), "stonecutting/");

        // 石英系列切石机配方
        add(stonecutting(Ingredient.of(Blocks.QUARTZ_BLOCK), RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUARTZ_BLOCK_WALL.get(), 1), "stonecutting/");
        add(stonecutting(Ingredient.of(Blocks.SMOOTH_QUARTZ), RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_QUARTZ_WALL.get(), 1), "stonecutting/");

        add(stonecutting(Ingredient.of(Blocks.QUARTZ_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_QUARTZ_BRICKS.get(), 1), "stonecutting/");
        add(stonecutting(Ingredient.of(Blocks.QUARTZ_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_QUARTZ_BRICKS.get(), 1), "stonecutting/");
        add(stonecutting(Ingredient.of(Blocks.QUARTZ_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_QUARTZ_BRICKS.get(), 1), "stonecutting/");
        add(stonecutting(Ingredient.of(Blocks.QUARTZ_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUARTZ_BRICK_STAIRS.get(), 1), "stonecutting/");
        add(stonecutting(Ingredient.of(Blocks.QUARTZ_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUARTZ_BRICK_SLAB.get(), 2), "stonecutting/");
        add(stonecutting(Ingredient.of(Blocks.QUARTZ_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUARTZ_BRICK_WALL.get(), 1), "stonecutting/");

        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_QUARTZ_BRICKS.get()), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_QUARTZ_BRICK_STAIRS.get(), 1), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_QUARTZ_BRICKS.get()), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_QUARTZ_BRICK_SLAB.get(), 2), "stonecutting/");
        add(stonecutting(Ingredient.of(ModBlocks.MOSSY_QUARTZ_BRICKS.get()), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_QUARTZ_BRICK_WALL.get(), 1), "stonecutting/");

        // 紫珀墙切石机配方
        add(stonecutting(Ingredient.of(Blocks.PURPUR_BLOCK), RecipeCategory.BUILDING_BLOCKS, ModBlocks.PURPUR_WALL.get(), 1), "stonecutting/");
    }

    protected void addSmeltingRecipes() {
        smeltingSmokingAndCampfire(ModBaseFoods.TROUT.get(), RecipeCategory.FOOD, ModBaseFoods.COOKED_TROUT.get(), 0.35F);
        smeltingSmokingAndCampfire(ModItems.LIVING_TUBER, RecipeCategory.FOOD, ModBaseFoods.BAKED_LIVING_TUBER.get(), 0.35F);
        smeltingSmokingAndCampfire(ModBaseFoods.DOUGH.get(), RecipeCategory.FOOD, Items.BREAD, 0.35F);// input category result exp
        smeltingSmokingAndCampfire(Items.CARROT, RecipeCategory.FOOD, ModBaseFoods.BAKED_CARROT.get(), 0.35F);
        smeltingSmokingAndCampfire(Items.BEETROOT, RecipeCategory.FOOD, ModBaseFoods.BAKED_BEETROOT.get(), 0.35F);
        smeltingSmokingAndCampfire(Ingredient.of(Items.EGG, Items.TURTLE_EGG), RecipeCategory.FOOD, ModBaseFoods.COOKED_EGG.get(), 0.35F);
        smeltingSmokingAndCampfire(Ingredient.of(Items.WHEAT_SEEDS, Items.PUMPKIN_SEEDS, Items.MELON_SEEDS, Items.BEETROOT_SEEDS,
                Items.TORCHFLOWER_SEEDS, Items.PITCHER_POD, ModBlocks.GLAREFLOWER_SEEDS), RecipeCategory.FOOD, ModBaseFoods.BAKED_SEEDS.get(), 0.35F);
        smeltingSmokingAndCampfire(Ingredient.of(Items.SWEET_BERRIES, Items.GLOW_BERRIES), RecipeCategory.FOOD, ModBaseFoods.BAKED_BERRIES.get(), 0.35F);
        smeltingSmokingAndCampfire(ModBaseFoods.PUMPKIN_SLICE, RecipeCategory.FOOD, ModBaseFoods.BAKED_PUMPKIN_SLICE.get(), 0.35F);
        smeltingSmokingAndCampfire(Items.MELON_SLICE, RecipeCategory.FOOD, ModBaseFoods.BAKED_MELON_SLICE.get(), 0.35F);
        smeltingSmokingAndCampfire(Items.APPLE, RecipeCategory.FOOD, ModBaseFoods.BAKED_APPLE.get(), 0.35F);
        smeltingSmokingAndCampfire(Ingredient.of(Blocks.BROWN_MUSHROOM, Blocks.RED_MUSHROOM, Blocks.CRIMSON_FUNGUS, Blocks.WARPED_FUNGUS),
                RecipeCategory.FOOD, ModBaseFoods.BAKED_MUSHROOM, 0.35F);
        smeltingSmokingAndCampfire(ModBaseFoods.FROG_LEG, RecipeCategory.FOOD, ModBaseFoods.COOKED_FROG_LEG, 0.35F);
        smeltingSmokingAndCampfire(ModBaseFoods.PIRANHA, RecipeCategory.FOOD, ModBaseFoods.COOKED_PIRANHA, 0.35F);
        smeltingSmokingAndCampfire(ModBaseFoods.BAT_WING, RecipeCategory.FOOD, ModBaseFoods.COOKED_BAT_WING, 0.35F);
        smeltingSmokingAndCampfire(ModBaseFoods.CALAMARI, RecipeCategory.FOOD, ModBaseFoods.COOKED_CALAMARI, 0.35F);
        smeltingSmokingAndCampfire(ModBaseFoods.GLOWING_CALAMARI, RecipeCategory.FOOD, ModBaseFoods.COOKED_CALAMARI, 0.35F, "_from_glowing_calamari");
        add(smelting(Items.BONE, RecipeCategory.MISC, ModBaseItems.CHARRED_BONE, 0.35F));

        add(smelting(ModBlocks.PALM_CROWN, RecipeCategory.MISC, Items.CHARCOAL, 0.35F), WildWindMod.MOD_ID + "/");

        add(smelting(Items.RAW_IRON_BLOCK, RecipeCategory.MISC, Items.IRON_BLOCK, 4.9f, 1000), WildWindMod.MOD_ID + "/", "_smelt");
        add(smelting(Items.RAW_GOLD_BLOCK, RecipeCategory.MISC, Items.GOLD_BLOCK, 4.9f, 1000), WildWindMod.MOD_ID + "/", "_smelt");
        add(smelting(Items.RAW_COPPER_BLOCK, RecipeCategory.MISC, Items.COPPER_BLOCK, 4.9f, 1000), WildWindMod.MOD_ID + "/", "_smelt");
        add(blasting(Items.RAW_IRON_BLOCK, RecipeCategory.MISC, Items.IRON_BLOCK, 4.9f, 1000), WildWindMod.MOD_ID + "/", "_blast");
        add(blasting(Items.RAW_GOLD_BLOCK, RecipeCategory.MISC, Items.GOLD_BLOCK, 4.9f, 1000), WildWindMod.MOD_ID + "/", "_blast");
        add(blasting(Items.RAW_COPPER_BLOCK, RecipeCategory.MISC, Items.COPPER_BLOCK, 4.9f, 1000), WildWindMod.MOD_ID + "/", "_blast");

        // add(smelting(ModBlocks.PALM_LEAVES, RecipeCategory.MISC, Items.LEAF_LITTER, 0.35F));
        // add(smelting(ModBlocks.BAOBAB_LEAVES, RecipeCategory.MISC, Items.LEAF_LITTER, 0.35F));

        add(smelting(ModBlocks.ANDESITE_BRICKS, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_ANDESITE_BRICKS, 0.1f, 200), "smelting/");
        add(smelting(ModBlocks.DIORITE_BRICKS, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_DIORITE_BRICKS, 0.1f, 200), "smelting/");
        add(smelting(ModBlocks.GRANITE_BRICKS, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_GRANITE_BRICKS, 0.1f, 200), "smelting/");
        add(smelting(Blocks.TUFF_BRICKS, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_TUFF_BRICKS, 0.1f, 200), "smelting/");
        add(smelting(Blocks.BRICKS, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_BRICKS, 0.1f, 200), "smelting/");
        add(smelting(Blocks.MUD_BRICKS, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_MUD_BRICKS, 0.1f, 200), "smelting/");
        add(smelting(ModBlocks.SANDSTONE_BRICKS, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_SANDSTONE_BRICKS, 0.1f, 200), "smelting/");
        add(smelting(ModBlocks.RED_SANDSTONE_BRICKS, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_RED_SANDSTONE_BRICKS, 0.1f, 200), "smelting/");
        add(smelting(Blocks.PRISMARINE_BRICKS, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_PRISMARINE_BRICKS, 0.1f, 200), "smelting/");
        add(smelting(ModBlocks.CALCITE_BRICKS, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_CALCITE_BRICKS, 0.1f, 200), "smelting/");
        add(smelting(ModBlocks.DRIPSTONE_BRICKS, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_DRIPSTONE_BRICKS, 0.1f, 200), "smelting/");
        add(smelting(Blocks.END_STONE_BRICKS, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_END_STONE_BRICKS.get(), 0.1f, 200), "smelting/");
        add(smelting(Blocks.QUARTZ_BRICKS, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_QUARTZ_BRICKS.get(), 0.1f, 200), "smelting/");
        add(smelting(ModBlocks.DARK_PRISMARINE_BRICKS.get(), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_DARK_PRISMARINE_BRICKS.get(), 0.1f, 200), "smelting/");
        add(smelting(ModBlocks.BASALT_BRICKS.get(), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_BASALT_BRICKS.get(), 0.1f, 200), "smelting/");
        add(smelting(Blocks.RED_NETHER_BRICKS, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_RED_NETHER_BRICKS.get(), 0.1f, 200), "smelting/");
        add(smelting(ModBlocks.POLISHED_NETHERRACK.get(), RecipeCategory.BUILDING_BLOCKS, Items.NETHER_BRICK, 0.1f, 200), "smelting/","from_polished_netherrack");

        SimpleCookingRecipeBuilder smelting = smelting(Items.TERRACOTTA, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLAZED_TERRACOTTA.get(), 0.35F);
        add(smelting);

        smeltingAndBlasting(Ingredient.of(ModBlocks.SALT_ORE.get(), ModBlocks.DEEPSLATE_SALT_ORE.get()), RecipeCategory.MISC, ModBaseItems.SALT.get(), 0.7F);

        smeltingSmokingAndCampfire(ModBaseFoods.VENISON, RecipeCategory.FOOD, ModBaseFoods.COOKED_VENISON, 0.35F);
    }


    protected void addShapedRecipe() {
        add(shaped(RecipeCategory.BUILDING_BLOCKS, Items.ICE, 1, builder -> {
            unlockedBy(builder, Items.ICE);
            builder
                    .pattern("III")
                    .pattern("III")
                    .pattern("III")
                    .group("ice")
                    .define('I', ModBlocks.BRITTLE_ICE);
        }), Helpers.location("ice_from_brittle_ice"));
        add(shaped(RecipeCategory.MISC, ModItems.MAGIC_FLUTE, 1, builder -> {
            unlockedBy(builder, Items.BONE);
            unlockedBy(builder, ModItems.LIVING_TUBER);
            builder
                    .pattern("BRB")
                    .group("magic_flute")
                    .define('B', Items.BONE)
                    .define('R', ModItems.LIVING_TUBER);
        }));
        add(shaped(RecipeCategory.MISC, ModBlocks.COOKING_POT.get(), 1,
                builder -> {
                    unlockedBy(builder, Items.IRON_INGOT);
                    unlockedBy(builder, ItemTags.LOGS);
                    unlockedBy(builder, ItemTags.COALS);
                    builder
                            .pattern("I I")
                            .pattern("III")
                            .pattern("PCP")
                            .group("cooking_pot")
                            .define('I', Items.IRON_INGOT)
                            .define('P', ItemTags.LOGS)
                            .define('C', ItemTags.COALS);
                }));

        add(shaped(RecipeCategory.MISC, ModBlocks.WOOL.get(), 1,
                builder -> {
                    unlockedBy(builder, Items.STRING);
                    builder
                            .pattern("SS ")
                            .pattern("SS ")
                            .pattern("   ")
                            .group("wool")
                            .define('S', Items.STRING);
                }));
        add(shaped(RecipeCategory.MISC, ModBlocks.CARPET.get(), 3,
                builder -> {
                    unlockedBy(builder, ModBlocks.WOOL.get());
                    builder
                            .pattern("SS ")
                            .pattern("   ")
                            .pattern("   ")
                            .group("carpet")
                            .define('S', ModBlocks.WOOL.get());
                }));

        add(shaped(RecipeCategory.MISC, ModBlocks.GLOW_MUCUS.get(), 1,
                builder -> {
                    unlockedBy(builder, ModItems.GLOW_POWDER);
                    unlockedBy(builder, ModItems.ASH_DUST);
                    unlockedBy(builder, Items.SLIME_BALL);
                    builder
                            .pattern("IWI")
                            .pattern("WSW")
                            .pattern("IWI")
                            .group("glow_mucus")
                            .define('I', ModItems.ASH_DUST)
                            .define('W', ModItems.GLOW_POWDER)
                            .define('S', Items.SLIME_BALL);
                }));

        add(shaped(RecipeCategory.MISC, ModBlocks.ASH_BLOCK.get(), 1,
                builder -> {
                    unlockedBy(builder, ModItems.ASH_DUST);
                    builder
                            .pattern("AA")
                            .pattern("AA")
                            .group("ash_block")
                            .define('A', ModItems.ASH_DUST);
                }));

        //石头相关
        addPolished(ModBlocks.POLISHED_STONE, () -> Blocks.COBBLESTONE);
        addMultiRecipeBrick(Blocks.STONE_BRICKS, ModBlocks.POLISHED_STONE.get());
        addBlockDerivatives(ModBlocks.POLISHED_STONE,
            ModBlocks.POLISHED_STONE_STAIRS,
            ModBlocks.POLISHED_STONE_SLAB,
            ModBlocks.POLISHED_STONE_WALL);
        addWall(ModBlocks.STONE_WALL, () -> Blocks.STONE);

        //深板岩相关
        addMultiRecipeBrick(Blocks.DEEPSLATE_BRICKS, Blocks.DEEPSLATE);
        addChiseled(ModBlocks.CHISELED_DEEPSLATE_BRICKS, () -> Blocks.DEEPSLATE_BRICK_SLAB);
        addChiseled(ModBlocks.CHISELED_DEEPSLATE_TILES, () -> Blocks.DEEPSLATE_TILE_SLAB);
        addBlockDerivatives(ModBlocks.MOSSY_COBBLED_DEEPSLATE,
            ModBlocks.MOSSY_COBBLED_DEEPSLATE_STAIRS,
            ModBlocks.MOSSY_COBBLED_DEEPSLATE_SLAB,
            ModBlocks.MOSSY_COBBLED_DEEPSLATE_WALL);

        addBlockDerivatives(ModBlocks.MOSSY_DEEPSLATE_BRICKS,
            ModBlocks.MOSSY_DEEPSLATE_BRICK_STAIRS,
            ModBlocks.MOSSY_DEEPSLATE_BRICK_SLAB,
            ModBlocks.MOSSY_DEEPSLATE_BRICK_WALL);

        addBlockDerivatives(ModBlocks.MOSSY_DEEPSLATE_TILES,
            ModBlocks.MOSSY_DEEPSLATE_TILE_STAIRS,
            ModBlocks.MOSSY_DEEPSLATE_TILE_SLAB,
            ModBlocks.MOSSY_DEEPSLATE_TILE_WALL);

        //泥砖相关
        addChiseled(ModBlocks.CHISELED_MUD_BRICKS, () -> Blocks.MUD_BRICK_SLAB);
        addBlockDerivatives(ModBlocks.MOSSY_MUD_BRICKS,
            ModBlocks.MOSSY_MUD_BRICK_STAIRS,
            ModBlocks.MOSSY_MUD_BRICK_SLAB,
            ModBlocks.MOSSY_MUD_BRICK_WALL);

        //安山岩系列
        addPolished(ModBlocks.ANDESITE_BRICKS, () -> Blocks.POLISHED_ANDESITE);
        addWall(ModBlocks.POLISHED_ANDESITE_WALL, () -> Blocks.POLISHED_ANDESITE);
        addBlockDerivatives(ModBlocks.ANDESITE_BRICKS,
            ModBlocks.ANDESITE_BRICK_STAIRS,
            ModBlocks.ANDESITE_BRICK_SLAB,
            ModBlocks.ANDESITE_BRICK_WALL);
        addChiseled(ModBlocks.CHISELED_ANDESITE_BRICKS, ModBlocks.ANDESITE_BRICK_SLAB::get);
        addBlockDerivatives(ModBlocks.MOSSY_ANDESITE_BRICKS,
            ModBlocks.MOSSY_ANDESITE_BRICK_STAIRS,
            ModBlocks.MOSSY_ANDESITE_BRICK_SLAB,
            ModBlocks.MOSSY_ANDESITE_BRICK_WALL);

        //闪长岩系列
        addPolished(ModBlocks.DIORITE_BRICKS, () -> Blocks.POLISHED_DIORITE);
        addBlockDerivatives(ModBlocks.DIORITE_BRICKS,
            ModBlocks.DIORITE_BRICK_STAIRS,
            ModBlocks.DIORITE_BRICK_SLAB,
            ModBlocks.DIORITE_BRICK_WALL);
        addChiseled(ModBlocks.CHISELED_DIORITE_BRICKS, ModBlocks.DIORITE_BRICK_SLAB::get);
        addWall(ModBlocks.POLISHED_DIORITE_WALL, () -> Blocks.POLISHED_DIORITE);
        addBlockDerivatives(ModBlocks.MOSSY_DIORITE_BRICKS,
            ModBlocks.MOSSY_DIORITE_BRICK_STAIRS,
            ModBlocks.MOSSY_DIORITE_BRICK_SLAB,
            ModBlocks.MOSSY_DIORITE_BRICK_WALL);

        //花岗岩系列
        addPolished(ModBlocks.GRANITE_BRICKS, () -> Blocks.POLISHED_GRANITE);
        addBlockDerivatives(ModBlocks.GRANITE_BRICKS,
            ModBlocks.GRANITE_BRICK_STAIRS,
            ModBlocks.GRANITE_BRICK_SLAB,
            ModBlocks.GRANITE_BRICK_WALL);
        addChiseled(ModBlocks.CHISELED_GRANITE_BRICKS, ModBlocks.GRANITE_BRICK_SLAB::get);
        addWall(ModBlocks.POLISHED_GRANITE_WALL, () -> Blocks.POLISHED_GRANITE);
        addBlockDerivatives(ModBlocks.MOSSY_GRANITE_BRICKS,
            ModBlocks.MOSSY_GRANITE_BRICK_STAIRS,
            ModBlocks.MOSSY_GRANITE_BRICK_SLAB,
            ModBlocks.MOSSY_GRANITE_BRICK_WALL);

        //砂岩相关
        addMultiRecipeBrick(ModBlocks.SANDSTONE_BRICKS.get(), Blocks.CUT_SANDSTONE);
        addWall(ModBlocks.SMOOTH_SANDSTONE_WALL, () -> Blocks.SMOOTH_SANDSTONE);
        addWall(ModBlocks.SMOOTH_RED_SANDSTONE_WALL, () -> Blocks.SMOOTH_RED_SANDSTONE);
        addBlockDerivatives(ModBlocks.SANDSTONE_BRICKS,
            ModBlocks.SANDSTONE_BRICK_STAIRS,
            ModBlocks.SANDSTONE_BRICK_SLAB,
            ModBlocks.SANDSTONE_BRICK_WALL);

        addBlockDerivatives(ModBlocks.MOSSY_SANDSTONE,
            ModBlocks.MOSSY_SANDSTONE_STAIRS,
            ModBlocks.MOSSY_SANDSTONE_SLAB,
            ModBlocks.MOSSY_SANDSTONE_WALL);

        addPolished(ModBlocks.RED_SANDSTONE_BRICKS, () -> Blocks.CUT_RED_SANDSTONE);
        addBlockDerivatives(ModBlocks.MOSSY_RED_SANDSTONE,
            ModBlocks.MOSSY_RED_SANDSTONE_STAIRS,
            ModBlocks.MOSSY_RED_SANDSTONE_SLAB,
            ModBlocks.MOSSY_RED_SANDSTONE_WALL);
        addChiseled(ModBlocks.CHISELED_SANDSTONE_BRICKS, ModBlocks.SANDSTONE_BRICK_SLAB::get);
        addBlockDerivatives(ModBlocks.MOSSY_SANDSTONE_BRICKS,
            ModBlocks.MOSSY_SANDSTONE_BRICK_STAIRS,
            ModBlocks.MOSSY_SANDSTONE_BRICK_SLAB,
            ModBlocks.MOSSY_SANDSTONE_BRICK_WALL);

        //黑石相关
        addMultiRecipeBrick(Blocks.POLISHED_BLACKSTONE , ModBlocks.COBBLED_BLACKSTONE.get());
        addBlockDerivatives(ModBlocks.COBBLED_BLACKSTONE,
            ModBlocks.COBBLED_BLACKSTONE_STAIRS,
            ModBlocks.COBBLED_BLACKSTONE_SLAB,
            ModBlocks.COBBLED_BLACKSTONE_WALL);
        addBlockDerivatives(ModBlocks.MOSSY_COBBLED_BLACKSTONE,
            ModBlocks.MOSSY_COBBLED_BLACKSTONE_STAIRS,
            ModBlocks.MOSSY_COBBLED_BLACKSTONE_SLAB,
            ModBlocks.MOSSY_COBBLED_BLACKSTONE_WALL);
        addChiseled(ModBlocks.CHISELED_POLISHED_BLACKSTONE_BRICKS, () -> Blocks.POLISHED_BLACKSTONE_SLAB);

        addBlockDerivatives(ModBlocks.MOSSY_POLISHED_BLACKSTONE_BRICKS,
            ModBlocks.MOSSY_POLISHED_BLACKSTONE_BRICK_STAIRS,
            ModBlocks.MOSSY_POLISHED_BLACKSTONE_BRICK_SLAB,
            ModBlocks.MOSSY_POLISHED_BLACKSTONE_BRICK_WALL);


        //凝灰岩相关
        addBlockDerivatives(ModBlocks.MOSSY_TUFF_BRICKS,
            ModBlocks.MOSSY_TUFF_BRICK_STAIRS,
            ModBlocks.MOSSY_TUFF_BRICK_SLAB,
            ModBlocks.MOSSY_TUFF_BRICK_WALL);

        //红砖相关
        addBlockDerivatives(ModBlocks.MOSSY_BRICKS,
            ModBlocks.MOSSY_BRICK_STAIRS,
            ModBlocks.MOSSY_BRICK_SLAB,
            ModBlocks.MOSSY_BRICK_WALL);
        addChiseled(ModBlocks.CHISELED_BRICKS, () -> Blocks.BRICK_SLAB);

        //红砂岩相关
        addBlockDerivatives(ModBlocks.RED_SANDSTONE_BRICKS,
                ModBlocks.RED_SANDSTONE_BRICK_STAIRS,
                ModBlocks.RED_SANDSTONE_BRICK_SLAB,
                ModBlocks.RED_SANDSTONE_BRICK_WALL);
        addChiseled(ModBlocks.CHISELED_RED_SANDSTONE_BRICKS, ModBlocks.RED_SANDSTONE_BRICK_SLAB::get);
        addBlockDerivatives(ModBlocks.MOSSY_RED_SANDSTONE_BRICKS,
                ModBlocks.MOSSY_RED_SANDSTONE_BRICK_STAIRS,
                ModBlocks.MOSSY_RED_SANDSTONE_BRICK_SLAB,
                ModBlocks.MOSSY_RED_SANDSTONE_BRICK_WALL);

        // === 雪砖系列 === (4)
        addPolished(ModBlocks.SNOW_BRICKS, ModBlocks.PACKED_SNOW_BLOCK);

        addBlockDerivatives(ModBlocks.SNOW_BRICKS,
                ModBlocks.SNOW_BRICK_STAIRS,
                ModBlocks.SNOW_BRICK_SLAB,
                ModBlocks.SNOW_BRICK_WALL);

        addChiseled(ModBlocks.CHISELED_SNOW_BRICKS,
                ModBlocks.SNOW_BRICK_SLAB::get);

        // === 方解石系列 === (11)
        addPolished(ModBlocks.POLISHED_CALCITE, () -> Blocks.CALCITE);
        addPolished(ModBlocks.CALCITE_BRICKS, ModBlocks.POLISHED_CALCITE);

        addBlockDerivatives(ModBlocks.POLISHED_CALCITE,
                ModBlocks.POLISHED_CALCITE_STAIRS,
                ModBlocks.POLISHED_CALCITE_SLAB,
                ModBlocks.POLISHED_CALCITE_WALL);

        addBlockDerivatives(ModBlocks.CALCITE_BRICKS,
                ModBlocks.CALCITE_BRICK_STAIRS,
                ModBlocks.CALCITE_BRICK_SLAB,
                ModBlocks.CALCITE_BRICK_WALL);

        addChiseled(ModBlocks.CHISELED_CALCITE_BRICKS,
                ModBlocks.CALCITE_BRICK_SLAB::get);

        addBlockDerivatives(ModBlocks.MOSSY_CALCITE_BRICKS,
                ModBlocks.MOSSY_CALCITE_BRICK_STAIRS,
                ModBlocks.MOSSY_CALCITE_BRICK_SLAB,
                ModBlocks.MOSSY_CALCITE_BRICK_WALL);

        // === 滴水石系列 === (11)
        addPolished(ModBlocks.POLISHED_DRIPSTONE_BLOCK, () -> Blocks.DRIPSTONE_BLOCK);
        addPolished(ModBlocks.DRIPSTONE_BRICKS, ModBlocks.POLISHED_DRIPSTONE_BLOCK);
        addBlockDerivatives(ModBlocks.POLISHED_DRIPSTONE_BLOCK,
                ModBlocks.POLISHED_DRIPSTONE_STAIRS,
                ModBlocks.POLISHED_DRIPSTONE_SLAB,
                ModBlocks.POLISHED_DRIPSTONE_WALL);

        addBlockDerivatives(ModBlocks.DRIPSTONE_BRICKS,
                ModBlocks.DRIPSTONE_BRICK_STAIRS,
                ModBlocks.DRIPSTONE_BRICK_SLAB,
                ModBlocks.DRIPSTONE_BRICK_WALL);

        addChiseled(ModBlocks.CHISELED_DRIPSTONE_BRICKS,
                ModBlocks.DRIPSTONE_BRICK_SLAB::get);

        addBlockDerivatives(ModBlocks.MOSSY_DRIPSTONE_BRICKS,
                ModBlocks.MOSSY_DRIPSTONE_BRICK_STAIRS,
                ModBlocks.MOSSY_DRIPSTONE_BRICK_SLAB,
                ModBlocks.MOSSY_DRIPSTONE_BRICK_WALL);

        // === 海晶石系列 === (30)
        addBlockDerivatives(ModBlocks.MOSSY_PRISMARINE,
                ModBlocks.MOSSY_PRISMARINE_STAIRS,
                ModBlocks.MOSSY_PRISMARINE_SLAB,
                ModBlocks.MOSSY_PRISMARINE_WALL);

        addPolished(ModBlocks.POLISHED_PRISMARINE, () -> Blocks.PRISMARINE);
        addMultiRecipeBrick(Blocks.PRISMARINE_BRICKS, ModBlocks.POLISHED_PRISMARINE.get());

        addBlockDerivatives(ModBlocks.POLISHED_PRISMARINE,
                ModBlocks.POLISHED_PRISMARINE_STAIRS,
                ModBlocks.POLISHED_PRISMARINE_SLAB,
                ModBlocks.POLISHED_PRISMARINE_WALL);

        addChiseled(ModBlocks.CHISELED_PRISMARINE_BRICKS,
                () -> Blocks.PRISMARINE_BRICK_SLAB);

        addBlockDerivatives(ModBlocks.MOSSY_PRISMARINE_BRICKS,
                ModBlocks.MOSSY_PRISMARINE_BRICK_STAIRS,
                ModBlocks.MOSSY_PRISMARINE_BRICK_SLAB,
                ModBlocks.MOSSY_PRISMARINE_BRICK_WALL);

        add(shaped(RecipeCategory.DECORATIONS, ModBlocks.OCEAN_LANTERN.get(), 1,
                builder -> {
                    unlockedBy(builder, Blocks.DARK_PRISMARINE, Items.GLOWSTONE_DUST);
                    builder.pattern(" D ").pattern("DLD").pattern(" D ")
                            .define('D', Blocks.DARK_PRISMARINE).define('L', Items.GLOWSTONE_DUST);
                }));

        addPolished(ModBlocks.POLISHED_DARK_PRISMARINE, () -> Blocks.DARK_PRISMARINE);

        addBlockDerivatives(ModBlocks.POLISHED_DARK_PRISMARINE,
                ModBlocks.POLISHED_DARK_PRISMARINE_STAIRS,
                ModBlocks.POLISHED_DARK_PRISMARINE_SLAB,
                ModBlocks.POLISHED_DARK_PRISMARINE_WALL);

        addBlockDerivatives(ModBlocks.MOSSY_DARK_PRISMARINE,
                ModBlocks.MOSSY_DARK_PRISMARINE_STAIRS,
                ModBlocks.MOSSY_DARK_PRISMARINE_SLAB,
                ModBlocks.MOSSY_DARK_PRISMARINE_WALL);

//        addPolished(ModBlocks.DARK_PRISMARINE_BRICKS, () -> Blocks.DARK_PRISMARINE);

        addBlockDerivatives(ModBlocks.DARK_PRISMARINE_BRICKS,
                ModBlocks.DARK_PRISMARINE_BRICK_STAIRS,
                ModBlocks.DARK_PRISMARINE_BRICK_SLAB,
                ModBlocks.DARK_PRISMARINE_BRICK_WALL);

        addChiseled(ModBlocks.CHISELED_DARK_PRISMARINE_BRICKS,
                ModBlocks.DARK_PRISMARINE_BRICK_SLAB::get);

        addBlockDerivatives(ModBlocks.MOSSY_DARK_PRISMARINE_BRICKS,
                ModBlocks.MOSSY_DARK_PRISMARINE_BRICK_STAIRS,
                ModBlocks.MOSSY_DARK_PRISMARINE_BRICK_SLAB,
                ModBlocks.MOSSY_DARK_PRISMARINE_BRICK_WALL);

        // === 下界岩系列 === (12)
        addPolished(ModBlocks.POLISHED_NETHERRACK, () -> Blocks.NETHERRACK);
        addBlockDerivatives(ModBlocks.POLISHED_NETHERRACK,
                ModBlocks.POLISHED_NETHERRACK_STAIRS,
                ModBlocks.POLISHED_NETHERRACK_SLAB,
                ModBlocks.POLISHED_NETHERRACK_WALL);

        addBlockDerivatives(ModBlocks.MOSSY_NETHER_BRICKS,
                ModBlocks.MOSSY_NETHER_BRICK_STAIRS,
                ModBlocks.MOSSY_NETHER_BRICK_SLAB,
                ModBlocks.MOSSY_NETHER_BRICK_WALL);

        add(shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_NETHER_BRICK_FENCE.get(), 6,
                builder -> {
                    unlockedBy(builder, Items.NETHER_BRICK, Blocks.RED_NETHER_BRICKS);
                    builder.pattern("SBS").pattern("SBS")
                            .define('S', Blocks.RED_NETHER_BRICKS).define('B', Items.NETHER_BRICK);
                }));

        addBlockDerivatives(ModBlocks.MOSSY_RED_NETHER_BRICKS,
                ModBlocks.MOSSY_RED_NETHER_BRICK_STAIRS,
                ModBlocks.MOSSY_RED_NETHER_BRICK_SLAB,
                ModBlocks.MOSSY_RED_NETHER_BRICK_WALL);

        addChiseled(ModBlocks.CHISELED_RED_NETHER_BRICKS,
                () -> Blocks.RED_NETHER_BRICK_SLAB);

        // === 玄武岩系列 === (17)
        addPolished(ModBlocks.BASALT_BRICKS, () -> Blocks.POLISHED_BASALT);

        addBlockDerivatives(() -> Blocks.BASALT,
                ModBlocks.BASALT_STAIRS,
                ModBlocks.BASALT_SLAB,
                ModBlocks.BASALT_WALL);

        addBlockDerivatives(() -> Blocks.SMOOTH_BASALT,
                ModBlocks.SMOOTH_BASALT_STAIRS,
                ModBlocks.SMOOTH_BASALT_SLAB,
                ModBlocks.SMOOTH_BASALT_WALL);

        addBlockDerivatives(() -> Blocks.POLISHED_BASALT,
                ModBlocks.POLISHED_BASALT_STAIRS,
                ModBlocks.POLISHED_BASALT_SLAB,
                ModBlocks.POLISHED_BASALT_WALL);

        addBlockDerivatives(ModBlocks.BASALT_BRICKS,
                ModBlocks.BASALT_BRICK_STAIRS,
                ModBlocks.BASALT_BRICK_SLAB,
                ModBlocks.BASALT_BRICK_WALL);

        addChiseled(ModBlocks.CHISELED_BASALT_BRICKS,
                ModBlocks.BASALT_BRICK_SLAB::get);

        addBlockDerivatives(ModBlocks.MOSSY_BASALT_BRICKS,
                ModBlocks.MOSSY_BASALT_BRICK_STAIRS,
                ModBlocks.MOSSY_BASALT_BRICK_SLAB,
                ModBlocks.MOSSY_BASALT_BRICK_WALL);

        // === 末地石系列 === (12)
        addBlockDerivatives(() -> Blocks.END_STONE,
                ModBlocks.END_STONE_STAIRS,
                ModBlocks.END_STONE_SLAB,
                ModBlocks.END_STONE_WALL);

        addPolished(ModBlocks.POLISHED_END_STONE, () -> Blocks.END_STONE);
        addPolished(Blocks.END_STONE_BRICKS, ModBlocks.POLISHED_END_STONE);

        addBlockDerivatives(ModBlocks.POLISHED_END_STONE,
                ModBlocks.POLISHED_END_STONE_STAIRS,
                ModBlocks.POLISHED_END_STONE_SLAB,
                ModBlocks.POLISHED_END_STONE_WALL);

        addChiseled(ModBlocks.CHISELED_END_STONE_BRICKS, () -> Blocks.END_STONE_BRICK_SLAB);

        addBlockDerivatives(ModBlocks.MOSSY_END_STONE_BRICKS,
                ModBlocks.MOSSY_END_STONE_BRICK_STAIRS,
                ModBlocks.MOSSY_END_STONE_BRICK_SLAB,
                ModBlocks.MOSSY_END_STONE_BRICK_WALL);

        addWall(ModBlocks.PURPUR_WALL, () -> Blocks.PURPUR_BLOCK);

        // === 石英系列 === (9)
        addWall(ModBlocks.QUARTZ_BLOCK_WALL, () -> Blocks.QUARTZ_BLOCK);
        addWall(ModBlocks.SMOOTH_QUARTZ_WALL, () -> Blocks.SMOOTH_QUARTZ);

        addBlockDerivatives(() -> Blocks.QUARTZ_BRICKS,
                ModBlocks.QUARTZ_BRICK_STAIRS,
                ModBlocks.QUARTZ_BRICK_SLAB,
                ModBlocks.QUARTZ_BRICK_WALL);

       addChiseled(ModBlocks.CHISELED_QUARTZ_BRICKS, ModBlocks.QUARTZ_BRICK_SLAB::get);

        addBlockDerivatives(ModBlocks.MOSSY_QUARTZ_BRICKS,
                ModBlocks.MOSSY_QUARTZ_BRICK_STAIRS,
                ModBlocks.MOSSY_QUARTZ_BRICK_SLAB,
                ModBlocks.MOSSY_QUARTZ_BRICK_WALL);

        // === 蓝冰系列 === (5)
        addPolished(ModBlocks.POLISHED_BLUE_ICE, () -> Blocks.BLUE_ICE);
        addMultiRecipeBrick(ModBlocks.BLUE_ICE_BRICKS.get(), ModBlocks.POLISHED_BLUE_ICE.get());

        addBlockDerivatives(ModBlocks.POLISHED_BLUE_ICE,
                ModBlocks.POLISHED_BLUE_ICE_STAIRS,
                ModBlocks.POLISHED_BLUE_ICE_SLAB,
                ModBlocks.POLISHED_BLUE_ICE_WALL);

        addBlockDerivatives(ModBlocks.BLUE_ICE_BRICKS,
            ModBlocks.BLUE_ICE_BRICK_STAIRS,
            ModBlocks.BLUE_ICE_BRICK_SLAB,
            ModBlocks.BLUE_ICE_BRICK_WALL);
        addChiseled(ModBlocks.CHISELED_BLUE_ICE_BRICKS, ModBlocks.BLUE_ICE_BRICK_SLAB::get);


        // === 夯实雪系列 === (5)
        addPolished(ModBlocks.PACKED_SNOW_BLOCK, () -> Blocks.SNOW_BLOCK);
        addBlockDerivatives(ModBlocks.PACKED_SNOW_BLOCK,
                ModBlocks.PACKED_SNOW_STAIRS,
                ModBlocks.PACKED_SNOW_SLAB,
                ModBlocks.PACKED_SNOW_WALL);
    }

    protected void addShapelessRecipe() {
        add(shapeless(RecipeCategory.FOOD, ModBaseFoods.FISH_CHOWDER.get(), 1, fish_chowder -> {
            unlockedBy(fish_chowder, ModBaseFoods.TROUT.get(), Items.COD, Items.SALMON);
            unlockedBy(fish_chowder, Items.BROWN_MUSHROOM, Items.RED_MUSHROOM);
            unlockedBy(fish_chowder, Items.KELP);
            unlockedBy(fish_chowder, Items.BOWL);
            fish_chowder
                    .requires(Ingredient.of(Items.COD, Items.SALMON, ModBaseFoods.TROUT.get()))
                    .requires(Items.KELP)
                    .requires(Ingredient.of(Items.BROWN_MUSHROOM, Items.RED_MUSHROOM))
                    .requires(Items.BOWL);
        }));
        add(shapeless(RecipeCategory.FOOD, ModItems.CHEESE, 1, cheese -> {
            unlockedBy(cheese, Items.BROWN_MUSHROOM);
            unlockedBy(cheese, Items.SUGAR);
            unlockedBy(cheese, Items.MILK_BUCKET);
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
        add(shapeless(RecipeCategory.FOOD, ModBaseItems.CHEESE_PUMPKIN_SOUP.get(), 1, cheese_pumpkin_soup -> {
            unlockedBy(cheese_pumpkin_soup, ModItems.CHEESE);
            unlockedBy(cheese_pumpkin_soup, Items.PUMPKIN);
            unlockedBy(cheese_pumpkin_soup, Items.BOWL);
            cheese_pumpkin_soup
                    .requires(Items.PUMPKIN)
                    .requires(Items.BOWL)
                    .requires(ModItems.CHEESE);
        }));
        add(shapeless(RecipeCategory.MISC, ModBaseFoods.FLOUR, 1, flour -> {
            unlockedBy(flour, Items.WHEAT);
            flour
                    .requires(Items.WHEAT);
        }));
        add(shapeless(RecipeCategory.MISC, ModBaseFoods.DOUGH.get(), 1, dough -> {
            unlockedBy(dough, ModBaseFoods.FLOUR);
            unlockedBy(dough, Items.WATER_BUCKET);
            dough
                    .requires(ModBaseFoods.FLOUR, 3)
                    .requires(Items.WATER_BUCKET);
        }));
        add(shapeless(RecipeCategory.FOOD, ModBaseItems.TOFFEE.get(), 1, toffee -> {
            unlockedBy(toffee, Items.SUGAR);
            unlockedBy(toffee, Items.HONEY_BOTTLE);
            unlockedBy(toffee, Items.GLOW_BERRIES, Items.APPLE, Items.SWEET_BERRIES);
            toffee
                    .requires(Items.HONEY_BOTTLE)
                    .requires(Items.SUGAR)
                    .requires(Ingredient
                            .of(
                                    new ItemStack(Items.GLOW_BERRIES),
                                    new ItemStack(Items.APPLE),
                                    new ItemStack(Items.SWEET_BERRIES, 2)));
        }));
        add(shapeless(RecipeCategory.FOOD, ModBaseFoods.BERRY_PIE.get(), 1, berry_cake -> {
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
        add(shapeless(RecipeCategory.FOOD, ModBaseFoods.APPLE_PIE.get(), 1, apple_pie -> {
            unlockedBy(apple_pie, Items.APPLE);
            unlockedBy(apple_pie, Items.SUGAR);
            unlockedBy(apple_pie, Items.EGG);
            apple_pie
                    .requires(Items.APPLE)
                    .requires(Items.SUGAR)
                    .requires(Items.EGG);
        }));
        add(shapeless(RecipeCategory.MISC, Items.STRING, 4, wool -> {
            unlockedBy(wool, ItemTags.WOOL);
            wool
                    .requires(ItemTags.WOOL);
        }));
        add(shapeless(RecipeCategory.MISC, ModBlocks.SALT_BLOCK.asItem(), 1, salt_block -> {
            unlockedBy(salt_block, ModBaseItems.SALT);
            salt_block.requires(ModBaseItems.SALT, 9);
        }));
        add(shapeless(RecipeCategory.MISC, ModBaseItems.SALT, 9, salt -> {
            unlockedBy(salt, ModBlocks.SALT_BLOCK);
            salt.requires(ModBlocks.SALT_BLOCK);
        }));

        add(shapeless(RecipeCategory.MISC, Blocks.PUMPKIN, 1, pumpkin -> {
            unlockedBy(pumpkin, ModBaseFoods.PUMPKIN_SLICE);
            pumpkin.requires(ModBaseFoods.PUMPKIN_SLICE, 9);
        }));
        add(shapeless(RecipeCategory.MISC, ModBlocks.GLISTERING_MELON, 1, glistering_melon -> {
            unlockedBy(glistering_melon, Items.GLISTERING_MELON_SLICE);
            glistering_melon
                    .requires(Items.GLISTERING_MELON_SLICE, 9);
        }));
        add(shapeless(RecipeCategory.MISC, Items.PUMPKIN_SEEDS, 1, pumpkin_seeds -> {
            unlockedBy(pumpkin_seeds, ModBaseFoods.PUMPKIN_SLICE);
            pumpkin_seeds.requires(ModBaseFoods.PUMPKIN_SLICE);
        }));

        add(shapeless(RecipeCategory.MISC, Items.GLOWSTONE_DUST, 1, glowstone_dust -> {
            unlockedBy(glowstone_dust, ModItems.GLOW_POWDER);
            unlockedBy(glowstone_dust, ModItems.ASH_DUST);
            glowstone_dust
                    .requires(ModItems.GLOW_POWDER)
                    .requires(ModItems.ASH_DUST);
        }));

        add(shapeless(RecipeCategory.MISC, Items.GLOW_INK_SAC, 1, glow_ink_sac -> {
            unlockedBy(glow_ink_sac, Items.INK_SAC);
            unlockedBy(glow_ink_sac, ModItems.GLOW_POWDER);
            glow_ink_sac
                    .requires(ModItems.GLOW_POWDER)
                    .requires(Items.INK_SAC);

        }));

        add(shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CONCRETE_POWDER, 8, concrete_powder -> {
            unlockedBy(concrete_powder, Blocks.SAND);
            unlockedBy(concrete_powder, Blocks.GRAVEL);
            concrete_powder
                    .requires(Blocks.SAND, 4)
                    .requires(Blocks.GRAVEL, 4);
        }));

        add(shapeless(RecipeCategory.MISC, Items.BONE_MEAL, 1, bone_meal -> {
            unlockedBy(bone_meal, ModBaseItems.FISH_BONE);
            bone_meal.requires(ModBaseItems.FISH_BONE);
        }), "", "_from_fish_bone");

        //苔系列方块
        addMossBlock(ModBlocks.MOSSY_COBBLED_DEEPSLATE, ()-> Blocks.COBBLED_DEEPSLATE);
        addMossBlock(ModBlocks.MOSSY_DEEPSLATE_BRICKS, ()-> Blocks.DEEPSLATE_BRICKS);
        addMossBlock(ModBlocks.MOSSY_DEEPSLATE_TILES, ()-> Blocks.DEEPSLATE_TILES);
        addMossBlock(ModBlocks.MOSSY_COBBLED_BLACKSTONE, ModBlocks.COBBLED_BLACKSTONE);
        addMossBlock(ModBlocks.MOSSY_POLISHED_BLACKSTONE_BRICKS,()-> Blocks.POLISHED_BLACKSTONE_BRICKS);
        addMossBlock(ModBlocks.MOSSY_ANDESITE_BRICKS, ModBlocks.ANDESITE_BRICKS);
        addMossBlock(ModBlocks.MOSSY_DIORITE_BRICKS, ModBlocks.DIORITE_BRICKS);
        addMossBlock(ModBlocks.MOSSY_GRANITE_BRICKS, ModBlocks.GRANITE_BRICKS);
        addMossBlock(ModBlocks.MOSSY_CALCITE_BRICKS, ModBlocks.CALCITE_BRICKS);
        addMossBlock(ModBlocks.MOSSY_DRIPSTONE_BRICKS, ModBlocks.DRIPSTONE_BRICKS);
        addMossBlock(ModBlocks.MOSSY_BASALT_BRICKS, ModBlocks.BASALT_BRICKS);
        addMossBlock(ModBlocks.MOSSY_TUFF_BRICKS, ()-> Blocks.TUFF_BRICKS);
        addMossBlock(ModBlocks.MOSSY_MUD_BRICKS, ()-> Blocks.MUD_BRICKS);
        addMossBlock(ModBlocks.MOSSY_SANDSTONE, ()-> Blocks.SANDSTONE);
        addMossBlock(ModBlocks.MOSSY_SANDSTONE_BRICKS, ModBlocks.SANDSTONE_BRICKS);
        addMossBlock(ModBlocks.MOSSY_BRICKS, ()-> Blocks.BRICKS);
        addMossBlock(ModBlocks.MOSSY_RED_SANDSTONE, ()-> Blocks.RED_SANDSTONE);
        addMossBlock(ModBlocks.MOSSY_RED_SANDSTONE_BRICKS, ModBlocks.RED_SANDSTONE_BRICKS);
        addMossBlock(ModBlocks.MOSSY_QUARTZ_BRICKS, ()-> Blocks.QUARTZ_BRICKS);
        addMossBlock(ModBlocks.MOSSY_NETHER_BRICKS, ()-> Blocks.NETHER_BRICKS);
        addMossBlock(ModBlocks.MOSSY_END_STONE_BRICKS, ()-> Blocks.END_STONE_BRICKS);
        addMossBlock(ModBlocks.MOSSY_PRISMARINE, ()-> Blocks.PRISMARINE);
        addMossBlock(ModBlocks.MOSSY_PRISMARINE_BRICKS, ()-> Blocks.PRISMARINE_BRICKS);
        addMossBlock(ModBlocks.MOSSY_DARK_PRISMARINE, ()-> Blocks.DARK_PRISMARINE);
        addMossBlock(ModBlocks.MOSSY_DARK_PRISMARINE_BRICKS, ModBlocks.DARK_PRISMARINE_BRICKS);
        addMossBlock(ModBlocks.MOSSY_RED_NETHER_BRICKS, () -> Blocks.RED_NETHER_BRICKS);
        // addMossBlock(ModBlocks.MOSSY_RED_NETHER_BRICKS, () -> Blocks.RED_NETHER_BRICKS);
    }


    public static ShapelessRecipeBuilder shapeless(RecipeCategory category, ItemStack result, Consumer<ShapelessRecipeBuilder> consumer) {
        ShapelessRecipeBuilder shapeless = ShapelessRecipeBuilder.shapeless(category, result);
        consumer.accept(shapeless);
        return shapeless;
    }

    public static ShapelessRecipeBuilder shapeless(RecipeCategory category, ItemLike result, int count, Consumer<ShapelessRecipeBuilder> consumer) {
        ShapelessRecipeBuilder shapeless = ShapelessRecipeBuilder.shapeless(category, result, count);
        consumer.accept(shapeless);
        return shapeless;
    }

    public static SingleItemRecipeBuilder stonecutting(
            Ingredient input, RecipeCategory category, ItemLike result, int count
    ) {
        ItemStack[] items = input.getItems();
        Item item = items[0].getItem();
        return SingleItemRecipeBuilder.stonecutting(input, category, result, count)
                .unlockedBy(BuiltInRegistries.ITEM.getKey(item).toString(), has(item));
    }

    public static SimpleCookingRecipeBuilder smelting(
            Ingredient input, RecipeCategory category, ItemLike result, float exp, int cookingTime
    ) {
        ItemStack[] items = input.getItems();
        Item item = items[0].getItem();
        return SimpleCookingRecipeBuilder.smelting(input, category, result, exp, cookingTime)
                .unlockedBy(BuiltInRegistries.ITEM.getKey(item).toString(), has(item));
    }

    public static SimpleCookingRecipeBuilder blasting(
            Ingredient input, RecipeCategory category, ItemLike result, float exp, int cookingTime
    ) {
        ItemStack[] items = input.getItems();
        Item item = items[0].getItem();
        return SimpleCookingRecipeBuilder.blasting(input, category, result, exp, cookingTime)
                .unlockedBy(BuiltInRegistries.ITEM.getKey(item).toString(), has(item));
    }

    public static SimpleCookingRecipeBuilder smoking(
            Ingredient input, RecipeCategory category, ItemLike result, float exp, int cookingTime
    ) {
        ItemStack[] items = input.getItems();
        Item item = items[0].getItem();
        return SimpleCookingRecipeBuilder.smoking(input, category, result, exp, cookingTime)
                .unlockedBy(BuiltInRegistries.ITEM.getKey(item).toString(), has(item));
    }

    public static SimpleCookingRecipeBuilder campfire(
            Ingredient input, RecipeCategory category, ItemLike result, float exp, int cookingTime
    ) {
        ItemStack[] items = input.getItems();
        Item item = items[0].getItem();
        return SimpleCookingRecipeBuilder.campfireCooking(input, category, result, exp, cookingTime)
                .unlockedBy(BuiltInRegistries.ITEM.getKey(item).toString(), has(item));
    }

    public static SimpleCookingRecipeBuilder campfire(
            Ingredient input, RecipeCategory category, ItemLike result, float exp
    ) {
        return campfire(input, category, result, exp, 200);
    }

    public static SimpleCookingRecipeBuilder campfire(
            ItemLike input, RecipeCategory category, ItemLike result, float exp, int cookingTime
    ) {
        return SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(input), category, result, exp, cookingTime)
                .unlockedBy(BuiltInRegistries.ITEM.getKey(input.asItem()).toString(), has(input));
    }

    public static SimpleCookingRecipeBuilder campfire(
            ItemLike input, RecipeCategory category, ItemLike result, float exp
    ) {
        return campfire(input, category, result, exp, 200);
    }

    public static SimpleCookingRecipeBuilder smelting(
            ItemLike input, RecipeCategory category, ItemLike result, float exp, int cookingTime
    ) {
        return SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), category, result, exp, cookingTime)
                .unlockedBy(BuiltInRegistries.ITEM.getKey(input.asItem()).toString(), has(input));
    }

    public static SimpleCookingRecipeBuilder blasting(
            ItemLike input, RecipeCategory category, ItemLike result, float exp, int cookingTime) {
        return SimpleCookingRecipeBuilder.blasting(Ingredient.of(input), category, result, exp, cookingTime)
                .unlockedBy(BuiltInRegistries.ITEM.getKey(input.asItem()).toString(), has(input));
    }

    public static SimpleCookingRecipeBuilder smoking(
            ItemLike input, RecipeCategory category, ItemLike result, float exp, int cookingTime) {
        return SimpleCookingRecipeBuilder.smoking(Ingredient.of(input), category, result, exp, cookingTime)
                .unlockedBy(BuiltInRegistries.ITEM.getKey(input.asItem()).toString(), has(input));
    }

    public void smeltingAndBlasting(Ingredient input, RecipeCategory category, ItemLike result, float exp) {
        add(smelting(input, category, result, exp));
        add(blasting(input, category, result, exp), "blasting/");
    }

    public void smeltingAndSmoking(Ingredient input, RecipeCategory category, ItemLike result, float exp) {
        add(smelting(input, category, result, exp));
        add(smoking(input, category, result, exp), "smoking/");
    }

    public void smeltingSmokingAndCampfire(Ingredient input, RecipeCategory category, ItemLike result, float exp) {
        add(smelting(input, category, result, exp));
        add(smoking(input, category, result, exp), "smoking/");
        add(campfire(input, category, result, exp), "campfire/");
    }

    public static SimpleCookingRecipeBuilder smelting(
            Ingredient input, RecipeCategory category, ItemLike result, float exp
    ) {
        return smelting(input, category, result, exp, 200);
    }

    public static SimpleCookingRecipeBuilder smoking(
            Ingredient input, RecipeCategory category, ItemLike result, float exp
    ) {
        return smoking(input, category, result, exp, 200);
    }

    public static SimpleCookingRecipeBuilder blasting(
            Ingredient input, RecipeCategory category, ItemLike result, float exp
    ) {
        return blasting(input, category, result, exp, 200);
    }

    public void smeltingAndSmoking(
            ItemLike input, RecipeCategory category, ItemLike result, float exp
    ) {
        add(smelting(input, category, result, exp));
        add(smoking(input, category, result, exp), "smoking/");
    }

    public void smeltingAndBlasting(
            ItemLike input, RecipeCategory category, ItemLike result, float exp
    ) {
        add(smelting(input, category, result, exp));
        add(blasting(input, category, result, exp), "blasting/");
    }

    public void smeltingSmokingAndCampfire(ItemLike input, RecipeCategory category, ItemLike result, float exp) {
        add(smelting(input, category, result, exp));
        add(smoking(input, category, result, exp), "smoking/");
        add(campfire(input, category, result, exp), "campfire/");
    }

    public void smeltingSmokingAndCampfire(ItemLike input, RecipeCategory category, ItemLike result, float exp, String suffix) {
        add(smelting(input, category, result, exp), "", suffix);
        add(smoking(input, category, result, exp), "smoking/", suffix);
        add(campfire(input, category, result, exp), "campfire/", suffix);
    }

    public static SimpleCookingRecipeBuilder smelting(
            ItemLike input, RecipeCategory category, ItemLike result, float exp
    ) {
        return smelting(input, category, result, exp, 200);
    }

    public static SimpleCookingRecipeBuilder smoking(
            ItemLike input, RecipeCategory category, ItemLike result, float exp
    ) {
        return smoking(input, category, result, exp, 200);
    }

    public static SimpleCookingRecipeBuilder blasting(
            ItemLike input, RecipeCategory category, ItemLike result, float exp
    ) {
        return blasting(input, category, result, exp, 200);
    }

    public void add(RecipeBuilder builder) {
        list.put(BuiltInRegistries.ITEM.getKey(builder.getResult()), builder);
    }

    public void add(RecipeBuilder builder, ResourceLocation name) {
        list.put(name, builder);
    }

    public void add(RecipeBuilder builder, String prePath) {
        list.put(BuiltInRegistries.ITEM.getKey(builder.getResult()).withPrefix(prePath), builder);
    }

    public void add(RecipeBuilder builder, String prePath, String sufPath) {
        list.put(BuiltInRegistries.ITEM.getKey(builder.getResult()).withPrefix(prePath).withSuffix(sufPath), builder);
    }

    public void add(String prePath, RecipeBuilder builder) {
        list.put(BuiltInRegistries.ITEM.getKey(builder.getResult()).withPrefix(prePath), builder);
    }


    //---[有序合成配方辅助]---


    private void addBlockDerivatives(Supplier<Block> baseBlock,
                                     DeferredBlock<StairBlock> stairs,
                                     DeferredBlock<SlabBlock> slab,
                                     DeferredBlock<WallBlock> wall) {
        addStairs(stairs, baseBlock);
        addSlab(slab, baseBlock);
        addWall(wall, baseBlock);
    }

    private void addPolished(DeferredBlock<Block> polishedBlock, Supplier<Block> baseBlock) {
        add(shaped(RecipeCategory.BUILDING_BLOCKS, polishedBlock.get(), 4,
                builder -> {
                    unlockedBy(builder, baseBlock.get());
                    builder.pattern("SS").pattern("SS").define('S', baseBlock.get());
                }));
    }

    private void addPolished(Block polishedBlock, Supplier<Block> baseBlock) {
        add(shaped(RecipeCategory.BUILDING_BLOCKS, polishedBlock, 4,
            builder -> {
                unlockedBy(builder, baseBlock.get());
                builder.pattern("SS").pattern("SS").define('S', baseBlock.get());
            }));
    }

    private void addChiseled(DeferredBlock<Block> chiseledBlock, Supplier<ItemLike> slabItem) {
        add(shaped(RecipeCategory.BUILDING_BLOCKS, chiseledBlock.get(), 1,
                builder -> {
                    unlockedBy(builder, slabItem.get());
                    builder.pattern("S").pattern("S").define('S', slabItem.get());
                }));
    }

    private void addWall(DeferredBlock<WallBlock> wallBlock, Supplier<Block> baseBlock) {
        add(shaped(RecipeCategory.BUILDING_BLOCKS, wallBlock.get(), 6,
                builder -> {
                    unlockedBy(builder, baseBlock.get());
                    builder.pattern("SSS").pattern("SSS").define('S', baseBlock.get());
                }));
    }


    private void addStairs(DeferredBlock<StairBlock> stairs, Supplier<Block> baseBlock) {
        add(shaped(RecipeCategory.BUILDING_BLOCKS, stairs.get(), 4,
                builder -> {
                    unlockedBy(builder, baseBlock.get());
                    builder.pattern("S  ").pattern("SS ").pattern("SSS")
                            .define('S', baseBlock.get());
                }));
    }

    private void addSlab(DeferredBlock<SlabBlock> slab, Supplier<Block> baseBlock) {
        add(shaped(RecipeCategory.BUILDING_BLOCKS, slab.get(), 6,
                builder -> {
                    unlockedBy(builder, baseBlock.get());
                    builder.pattern("SSS")
                            .define('S', baseBlock.get());
                }));
    }

    private void addMultiRecipeBrick(Block brickBlock, Block... baseBlocks) {
        for (Block baseBlock : baseBlocks) {
            add(shaped(RecipeCategory.BUILDING_BLOCKS, brickBlock, 4,
                builder -> {
                    unlockedBy(builder, baseBlock);
                    builder.pattern("SS").pattern("SS").define('S', baseBlock);
                }),"", "_from_" + BuiltInRegistries.BLOCK.getKey(baseBlock).getPath());
        }
    }

    private void addMossBlock(DeferredBlock<Block> mossBlock, Supplier<Block> baseBlockSupplier){
        final Block baseBlock = baseBlockSupplier.get();
        add(shapeless(RecipeCategory.BUILDING_BLOCKS, mossBlock.get(), 1,
            builder -> {
                unlockedBy(builder, baseBlock);
                unlockedBy(builder, Blocks.MOSS_BLOCK);
                builder
                    .requires(baseBlock)
                    .requires(Blocks.MOSS_BLOCK);
            }), "","_from_moss");
        add(shapeless(RecipeCategory.BUILDING_BLOCKS, mossBlock.get(), 1,
            builder -> {
                unlockedBy(builder, baseBlock);
                unlockedBy(builder, Blocks.VINE);
                builder
                    .requires(baseBlock)
                    .requires(Blocks.VINE);
            }), "","_from_vine");
    }
}