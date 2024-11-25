package org.polaris2023.wild_wind.datagen.custom.recipe;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;
import org.polaris2023.wild_wind.common.recipe.CookingPotRecipe;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class CookingPotRecipeBuilder implements RecipeBuilder {

    private final RecipeCategory category;
    private final Item result;
    private final int count;
    private final ItemStack resultStack; // Neo: add stack result support
    private final NonNullList<Ingredient> ingredients = NonNullList.create();
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    private final FluidStack stack;
    @Nullable
    private String group;

    public CookingPotRecipeBuilder(RecipeCategory category, ItemLike result, int count, FluidStack stack) {
        this(category, new ItemStack(result, count), stack);
    }

    public CookingPotRecipeBuilder(RecipeCategory category, ItemStack result, FluidStack stack) {
        this.category = category;
        this.result = result.getItem();
        this.count = result.getCount();
        this.resultStack = result;
        this.stack = stack;
    }

    public static CookingPotRecipeBuilder cooking(RecipeCategory category, ItemLike result, FluidStack stack) {
        return new CookingPotRecipeBuilder(category, result, 1, stack);
    }

    public static CookingPotRecipeBuilder cooking(RecipeCategory category, ItemLike result, int count, FluidStack stack) {
        return new CookingPotRecipeBuilder(category, result, count, stack);
    }

    public static CookingPotRecipeBuilder cooking(RecipeCategory category, ItemStack result, FluidStack stack) {
        return new CookingPotRecipeBuilder(category, result, stack);
    }

    @Override
    public RecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        criteria.put(name, criterion);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String groupName) {
        this.group = groupName;
        return this;
    }

    @Override
    public Item getResult() {
        return result;
    }

    @Override
    public void save(RecipeOutput recipeOutput, ResourceLocation id) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }
        Advancement.Builder builder = recipeOutput.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                .rewards(AdvancementRewards.Builder.recipe(id))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(builder::addCriterion);
        CookingPotRecipe cookingPot = new CookingPotRecipe(
                Objects.requireNonNullElse(this.group, ""),
                RecipeBuilder.determineBookCategory(this.category),
                this.resultStack,
                stack,
                this.ingredients
        );
        recipeOutput.accept(id, cookingPot, builder.build(id.withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }
}
