package org.polaris2023.wild_wind.common.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;
import org.polaris2023.wild_wind.common.init.ModComponents;
import org.polaris2023.wild_wind.common.init.ModRecipeSerializers;
import org.polaris2023.wild_wind.common.init.ModRecipeTypes;
import org.polaris2023.wild_wind.util.MinMaxValue;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static org.polaris2023.wild_wind.util.MinMaxValue.Codecs.FLOAT_CODEC;
import static org.polaris2023.wild_wind.util.MinMaxValue.StreamCodecs.FLOAT_STREAM_CODEC;

public class CookingPotRecipe extends SingleItemRecipe {
    final FluidStack stack;
    final MinMaxValue<Float> meat, vegetable, fruit, protein, fish, monster, sweet;
    final List<ResourceLocation> locations;


    public CookingPotRecipe(String group,
                            ItemStack result,
                            FluidStack stack,
                            MinMaxValue<Float> meat,
                            MinMaxValue<Float> vegetable,
                            MinMaxValue<Float> fruit,
                            MinMaxValue<Float> protein,
                            MinMaxValue<Float> fish,
                            MinMaxValue<Float> monster,
                            MinMaxValue<Float> sweet,
                            List<ResourceLocation> locations
    ) {
        super(ModRecipeTypes.COOKING_POT.get(), ModRecipeSerializers.COOKING_POT.get(), group, Ingredient.of(Items.STICK), result);
        this.stack = stack;
        this.meat = meat;
        this.vegetable = vegetable;
        this.fruit = fruit;
        this.protein = protein;
        this.fish = fish;
        this.monster = monster;
        this.sweet = sweet;
        this.locations = locations;
    }

    @Override
    public boolean matches(SingleRecipeInput input, Level level) {
        //disable item by recipe
        ItemStack item = input.item();
        List<ResourceLocation> orDefault = item.getOrDefault(ModComponents.LOCATIONS, new ArrayList<>());
        for (ResourceLocation location : locations) {
            if (orDefault.stream().filter(l -> l.equals(location)).findFirst().isEmpty()) {
                return false;
            }
        }
        return check(item, ModComponents.MEAT_VALUE, meat)
                && check(item, ModComponents.VEGETABLE_VALUE, vegetable)
                && check(item, ModComponents.FRUIT_VALUE, fruit)
                && check(item, ModComponents.PROTEIN_VALUE, protein)
                && check(item, ModComponents.FISH_VALUE, fish)
                && check(item, ModComponents.MONSTER_VALUE, monster)
                && check(item, ModComponents.SWEET_VALUE, sweet);
    }

    public static boolean check(ItemStack stack, Supplier<DataComponentType<Float>> supplier, MinMaxValue<Float> value) {
        return check(stack.getOrDefault(supplier.get(), 0F), value);
    }

    public static boolean check(float input, MinMaxValue<Float> value) {
        return (!value.hasMax() && input == value.min()) || (Math.min(Math.max(input, value.min()), value.max()) == input);
    }

    public static class Serializer implements RecipeSerializer<CookingPotRecipe> {
        public static final Codec<List<ResourceLocation>> LOCATIONS_CODEC = ResourceLocation.CODEC.listOf();
        public static final  StreamCodec<ByteBuf, List<ResourceLocation>> LOCATIONS_STREAM_CODEC = ResourceLocation.STREAM_CODEC.apply(ByteBufCodecs.list());

        private static final MapCodec<CookingPotRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> {

                    return instance.group(
                                    Codec.STRING.optionalFieldOf("group", "").forGetter(cookingPotRecipe -> cookingPotRecipe.group),
                                    ItemStack.STRICT_CODEC.fieldOf("result").forGetter(cookingPotRecipe -> cookingPotRecipe.result),
                                    FluidStack.CODEC.fieldOf("fluid").forGetter(cookingPotRecipe -> cookingPotRecipe.stack),
                                    FLOAT_CODEC.fieldOf("meat").forGetter(cookingPotRecipe -> cookingPotRecipe.meat),
                                    FLOAT_CODEC.fieldOf("vegetable").forGetter(cookingPotRecipe -> cookingPotRecipe.vegetable),
                                    FLOAT_CODEC.fieldOf("fruit").forGetter(cookingPotRecipe -> cookingPotRecipe.fruit),
                                    FLOAT_CODEC.fieldOf("protein").forGetter(cookingPotRecipe -> cookingPotRecipe.protein),
                                    FLOAT_CODEC.fieldOf("fish").forGetter(cookingPotRecipe -> cookingPotRecipe.fish),
                                    FLOAT_CODEC.fieldOf("monster").forGetter(cookingPotRecipe -> cookingPotRecipe.monster),
                                    FLOAT_CODEC.fieldOf("sweet").forGetter(cookingPotRecipe -> cookingPotRecipe.sweet),
                                    LOCATIONS_CODEC.fieldOf("locations").forGetter(cookingPotRecipe -> cookingPotRecipe.locations)
                            )
                            .apply(instance, CookingPotRecipe::new);
                }
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, CookingPotRecipe> STREAM_CODEC = StreamCodec.of(
                CookingPotRecipe.Serializer::toNetwork, CookingPotRecipe.Serializer::fromNetwork
        );


        private static CookingPotRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            String s = buffer.readUtf();
            ItemStack itemstack = ItemStack.STREAM_CODEC.decode(buffer);
            FluidStack stack1 = FluidStack.STREAM_CODEC.decode(buffer);

            MinMaxValue<Float> meat = FLOAT_STREAM_CODEC.decode(buffer);
            MinMaxValue<Float> vegetable = FLOAT_STREAM_CODEC.decode(buffer);
            MinMaxValue<Float> fruit = FLOAT_STREAM_CODEC.decode(buffer);
            MinMaxValue<Float> protein = FLOAT_STREAM_CODEC.decode(buffer);
            MinMaxValue<Float> fish = FLOAT_STREAM_CODEC.decode(buffer);
            MinMaxValue<Float> monster = FLOAT_STREAM_CODEC.decode(buffer);
            MinMaxValue<Float> sweet = FLOAT_STREAM_CODEC.decode(buffer);
            List<ResourceLocation> locations = LOCATIONS_STREAM_CODEC.decode(buffer);
            return new CookingPotRecipe(
                    s,
                    itemstack, stack1,
                    meat,
                    vegetable,
                    fruit,
                    protein,
                    fish,
                    monster,
                    sweet,
                    locations);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, CookingPotRecipe recipe) {
            buffer.writeUtf(recipe.group);
            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
            FluidStack.STREAM_CODEC.encode(buffer, recipe.stack);
            FLOAT_STREAM_CODEC.encode(buffer, recipe.meat);
            FLOAT_STREAM_CODEC.encode(buffer, recipe.vegetable);
            FLOAT_STREAM_CODEC.encode(buffer, recipe.fruit);
            FLOAT_STREAM_CODEC.encode(buffer, recipe.protein);
            FLOAT_STREAM_CODEC.encode(buffer, recipe.fish);
            FLOAT_STREAM_CODEC.encode(buffer, recipe.monster);
            FLOAT_STREAM_CODEC.encode(buffer, recipe.sweet);
            LOCATIONS_STREAM_CODEC.encode(buffer, recipe.locations);
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
