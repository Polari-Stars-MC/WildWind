package org.polaris2023.wild_wind.common.init;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static org.polaris2023.wild_wind.common.init.ModInitializer.COMPONENTS;

public class ModComponents {
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> SLIME_COLOR =
            register("slime_color", Codec.INT, ByteBufCodecs.INT);
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




    private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name, Codec<T> codec, StreamCodec<ByteBuf, T> streamCodec) {
        return COMPONENTS.registerComponentType(name,
                builder -> builder.persistent(codec).networkSynchronized(streamCodec));
    }
}
