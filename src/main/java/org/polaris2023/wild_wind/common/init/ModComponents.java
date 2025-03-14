package org.polaris2023.wild_wind.common.init;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.wild_wind.common.recipe.CookingPotRecipe;

import java.util.List;

import static org.polaris2023.wild_wind.common.init.ModInitializer.COMPONENTS;
import static org.polaris2023.wild_wind.common.recipe.CookingPotRecipe.Serializer.LOCATIONS_CODEC;
import static org.polaris2023.wild_wind.common.recipe.CookingPotRecipe.Serializer.LOCATIONS_STREAM_CODEC;

public class ModComponents {
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> SLIME_COLOR =
            register("slime_color", Codec.INT, ByteBufCodecs.INT);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> COLOR =
            register("color", Codec.INT, ByteBufCodecs.INT);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Float>> MEAT_VALUE =
            register("meat", Codec.FLOAT, ByteBufCodecs.FLOAT);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Float>> VEGETABLE_VALUE =
            register("vegetable", Codec.FLOAT, ByteBufCodecs.FLOAT);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Float>> FRUIT_VALUE =
            register("fruit", Codec.FLOAT, ByteBufCodecs.FLOAT);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Float>> PROTEIN_VALUE =
            register("protein", Codec.FLOAT, ByteBufCodecs.FLOAT);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Float>> FISH_VALUE =
            register("fish", Codec.FLOAT, ByteBufCodecs.FLOAT);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Float>> MONSTER_VALUE =
            register("monster", Codec.FLOAT, ByteBufCodecs.FLOAT);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Float>> SWEET_VALUE =
            register("sweet", Codec.FLOAT, ByteBufCodecs.FLOAT);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<ResourceLocation>>> LOCATIONS =
            register("locations", LOCATIONS_CODEC, LOCATIONS_STREAM_CODEC);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> PRESENT_BOX =
            register("present_box", Codec.STRING, ByteBufCodecs.STRING_UTF8);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> PRESENT_RIBBON =
            register("present_ribbon", Codec.STRING, ByteBufCodecs.STRING_UTF8);




    private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name, Codec<T> codec, StreamCodec<ByteBuf, T> streamCodec) {
        return COMPONENTS.registerComponentType(name,
                builder -> builder
                        .persistent(codec)
                        .networkSynchronized(streamCodec));
    }
}
