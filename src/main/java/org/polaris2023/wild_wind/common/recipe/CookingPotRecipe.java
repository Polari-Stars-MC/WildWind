package org.polaris2023.wild_wind.common.recipe;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.PairCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.RecipeMatcher;
import net.neoforged.neoforge.fluids.FluidStack;
import org.polaris2023.wild_wind.common.init.ModRecipeSerializes;
import org.polaris2023.wild_wind.common.init.ModRecipes;

public class CookingPotRecipe implements Recipe<CraftingInput> {
    final String group;
    final ItemStack result;
    final FluidStack stack;
    final CraftingBookCategory category;
    final NonNullList<Ingredient> ingredients;
    final Pair<Float, Float> meat, vegetable, fruit, protein, fish, monster;


    public CookingPotRecipe(String group,
                            CraftingBookCategory category,
                            ItemStack result,
                            FluidStack stack,
                            NonNullList<Ingredient> ingredients,
                            Pair<Float, Float> meat,
                            Pair<Float, Float> vegetable,
                            Pair<Float, Float> fruit,
                            Pair<Float, Float> protein,
                            Pair<Float, Float> fish,
                            Pair<Float, Float> monster) {
        this.group = group;
        this.category = category;
        this.result = result;
        this.stack = stack;
        this.ingredients = ingredients;

        this.meat = meat;
        this.vegetable = vegetable;
        this.fruit = fruit;
        this.protein = protein;
        this.fish = fish;
        this.monster = monster;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        if (input.ingredientCount() != this.ingredients.size()) {
            return false;
        } else {
            var nonEmptyItems = new java.util.ArrayList<ItemStack>(input.ingredientCount());
            for (var item : input.items())
                if (!item.isEmpty())
                    nonEmptyItems.add(item);
            return RecipeMatcher.findMatches(nonEmptyItems, this.ingredients) != null;
        }
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        return getResultItem(registries).copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return  width * height >= this.ingredients.size();
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializes.COOKING_POT.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.COOKING_POT.get();
    }

    public static class Serializer implements RecipeSerializer<CookingPotRecipe> {

        public static final PairCodec<Float, Float> FLOAT_PAIR_CODEC = new PairCodec<>(Codec.FLOAT, Codec.FLOAT);

        private static final MapCodec<CookingPotRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                                Codec.STRING.optionalFieldOf("group", "").forGetter(cookingPotRecipe -> cookingPotRecipe.group),
                                CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(cookingPotRecipe -> cookingPotRecipe.category),
                                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(cookingPotRecipe -> cookingPotRecipe.result),
                                FluidStack.CODEC.fieldOf("fluid").forGetter(cookingPotRecipe -> cookingPotRecipe.stack),
                                Ingredient.CODEC_NONEMPTY
                                        .listOf()
                                        .fieldOf("ingredients")
                                        .flatXmap(
                                                ingredientList -> {
                                                    Ingredient[] aingredient = ingredientList.toArray(Ingredient[]::new); // Neo skip the empty check and immediately create the array.
                                                    if (aingredient.length == 0) {
                                                        return DataResult.error(() -> "No ingredients for shapeless recipe");
                                                    } else {
                                                        return aingredient.length > 3 * 3
                                                                ? DataResult.error(() -> "Too many ingredients for shapeless recipe. The maximum is: %s".formatted(3 * 3))
                                                                : DataResult.success(NonNullList.of(Ingredient.EMPTY, aingredient));
                                                    }
                                                },
                                                DataResult::success
                                        )
                                        .forGetter(cookingPotRecipe -> cookingPotRecipe.ingredients),
                                FLOAT_PAIR_CODEC.fieldOf("meat").forGetter(cookingPotRecipe -> cookingPotRecipe.meat),
                                FLOAT_PAIR_CODEC.fieldOf("vegetable").forGetter(cookingPotRecipe -> cookingPotRecipe.vegetable),
                                FLOAT_PAIR_CODEC.fieldOf("fruit").forGetter(cookingPotRecipe -> cookingPotRecipe.fruit),
                                FLOAT_PAIR_CODEC.fieldOf("protein").forGetter(cookingPotRecipe -> cookingPotRecipe.protein),
                                FLOAT_PAIR_CODEC.fieldOf("fish").forGetter(cookingPotRecipe -> cookingPotRecipe.fish),
                                FLOAT_PAIR_CODEC.fieldOf("monster").forGetter(cookingPotRecipe -> cookingPotRecipe.monster)
                        )
                        .apply(instance, CookingPotRecipe::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, CookingPotRecipe> STREAM_CODEC = StreamCodec.of(
                CookingPotRecipe.Serializer::toNetwork, CookingPotRecipe.Serializer::fromNetwork
        );

        static {}


        public static final StreamCodec<RegistryFriendlyByteBuf, Pair<Float, Float>> FLOAT_PAIR_STREAM_CODEC = StreamCodec.of(
                ByteBufCodecs.fromCodec(FLOAT_PAIR_CODEC)::encode, ByteBufCodecs.fromCodec(FLOAT_PAIR_CODEC)::decode
        );

        private static CookingPotRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            String s = buffer.readUtf();
            CraftingBookCategory craftingbookcategory = buffer.readEnum(CraftingBookCategory.class);
            int i = buffer.readVarInt();
            NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);
            nonnulllist.replaceAll(__ -> Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
            ItemStack itemstack = ItemStack.STREAM_CODEC.decode(buffer);
            FluidStack stack1 = FluidStack.STREAM_CODEC.decode(buffer);

            Pair<Float, Float> meat = FLOAT_PAIR_STREAM_CODEC.decode(buffer);
            Pair<Float, Float> vegetable = FLOAT_PAIR_STREAM_CODEC.decode(buffer);
            Pair<Float, Float> fruit = FLOAT_PAIR_STREAM_CODEC.decode(buffer);
            Pair<Float, Float> protein = FLOAT_PAIR_STREAM_CODEC.decode(buffer);
            Pair<Float, Float> fish = FLOAT_PAIR_STREAM_CODEC.decode(buffer);
            Pair<Float, Float> monster = FLOAT_PAIR_STREAM_CODEC.decode(buffer);
            return new CookingPotRecipe(
                    s, craftingbookcategory,
                    itemstack, stack1,
                    nonnulllist,
                    meat,
                    vegetable,
                    fruit,
                    protein,
                    fish,
                    monster);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, CookingPotRecipe recipe) {
            buffer.writeUtf(recipe.group);
            buffer.writeEnum(recipe.category);
            buffer.writeVarInt(recipe.ingredients.size());

            for (Ingredient ingredient : recipe.ingredients) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
            }

            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
        }


        @Override
        public MapCodec<CookingPotRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CookingPotRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
