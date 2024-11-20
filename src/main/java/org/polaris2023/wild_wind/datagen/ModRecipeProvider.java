package org.polaris2023.wild_wind.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
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
		this.addSmeltingRecipes();
		this.addCraftingShapedRecipes();

		this.list.forEach(b -> b.save(recipeOutput));
	}

	protected void addSmeltingRecipes() {
		this.add(smelting(ModItems.RAW_TROUT, RecipeCategory.FOOD, ModItems.COOKED_TROUT, 0.35F, 200));
	}

	protected void addCraftingShapedRecipes() {
		this.add(
				shaped(RecipeCategory.MISC, ModItems.MAGIC_FLUTE, 1)
						.define('B', Items.BONE).define('R', ModItems.LIVING_TUBER)
						.pattern("BRB")
						.group("magic_flute")
						.unlockedBy("has_bone", has(Items.BONE))
						.unlockedBy("has_living_tuber", has(ModItems.LIVING_TUBER))
		);
	}

	public static SimpleCookingRecipeBuilder smelting(
			ItemLike input, RecipeCategory category, ItemLike result, float exp, int cookingTime
	) {
		return SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), category, result, exp, cookingTime)
				.unlockedBy(BuiltInRegistries.ITEM.getKey(input.asItem()).toString(), has(input));
	}

	public static ShapedRecipeBuilder shaped(
			RecipeCategory category, ItemLike result, int count
	) {
		return ShapedRecipeBuilder.shaped(category, result, count);
	}

	public void add(RecipeBuilder builder) {
		this.list.add(builder);
	}
}
