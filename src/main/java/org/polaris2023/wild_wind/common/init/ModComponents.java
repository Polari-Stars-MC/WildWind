package org.polaris2023.wild_wind.common.init;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;

import static org.polaris2023.wild_wind.common.init.ModInitializer.COMPONENTS;

public final class ModComponents {
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> SLIME_COLOR =
            COMPONENTS.registerComponentType("slime_color",
                    builder -> builder
                            .persistent(Codec.INT)
                            .networkSynchronized(ByteBufCodecs.INT));
}
