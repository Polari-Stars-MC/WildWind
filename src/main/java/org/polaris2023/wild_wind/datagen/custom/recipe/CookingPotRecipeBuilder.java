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
    @Nullable
    private String group;

    public CookingPotRecipeBuilder(RecipeCategory category, ItemLike result, int count) {
        this(category, new ItemStack(result, count));
    }

    public CookingPotRecipeBuilder(RecipeCategory category, ItemStack itemStack) {
        this.category = category;
        this.result = itemStack.getItem();
        this.count = itemStack.getCount();
        this.resultStack = itemStack;
    }

    public static CookingPotRecipeBuilder cooking(RecipeCategory category, ItemLike result) {
        return new CookingPotRecipeBuilder(category, result, 1);
    }

    public static CookingPotRecipeBuilder cooking(RecipeCategory category, ItemLike result, int count) {
        return new CookingPotRecipeBuilder(category, result, count);
    }

    public static CookingPotRecipeBuilder cooking(RecipeCategory p_252339_, ItemStack result) {
        return new CookingPotRecipeBuilder(p_252339_, result);
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
        CookingPotRecipe shapelessrecipe = new CookingPotRecipe(
                Objects.requireNonNullElse(this.group, ""),
                RecipeBuilder.determineBookCategory(this.category),
                this.resultStack,
                this.ingredients
        );
        recipeOutput.accept(id, shapelessrecipe, builder.build(id.withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }
}
