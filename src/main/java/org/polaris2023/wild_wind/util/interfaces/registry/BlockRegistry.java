package org.polaris2023.wild_wind.util.interfaces.registry;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.Function;
import java.util.function.Supplier;

import static org.polaris2023.wild_wind.common.init.ModInitializer.BLOCKS;
import static org.polaris2023.wild_wind.common.init.ModInitializer.ITEMS;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/01 21:00:23}
 */
public interface BlockRegistry {
    static DeferredBlock<Block> register(String name) {
        return BLOCKS.registerSimpleBlock(name, BlockBehaviour.Properties.of());
    }

    static <T extends Block> DeferredBlock<T> register(String name, Function<BlockBehaviour.Properties, T> function, BlockBehaviour.Properties properties) {
        return BLOCKS.registerBlock(name, function, properties);
    }

    static DeferredBlock<Block> register(String name, BlockBehaviour.Properties properties) {
        return BLOCKS.registerSimpleBlock(name, properties);
    }


}
