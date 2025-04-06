package org.polaris2023.wild_wind.util.interfaces.registry;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Collection;
import java.util.function.Function;

import static org.polaris2023.wild_wind.common.init.ModBlocks.REGISTER;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/01 21:00:23}
 */
public interface BlockRegistry {
    static DeferredBlock<Block> register(String name) {
        return REGISTER.registerSimpleBlock(name, BlockBehaviour.Properties.of());
    }

    static <T extends Block> DeferredBlock<T> register(String name, Function<BlockBehaviour.Properties, T> function, BlockBehaviour.Properties properties) {
        return REGISTER.registerBlock(name, function, properties);
    }

    static DeferredBlock<Block> register(String name, BlockBehaviour.Properties properties) {
        return REGISTER.registerSimpleBlock(name, properties);
    }

    static Collection<DeferredHolder<Block, ? extends Block>> entry() {
        return REGISTER.getEntries();
    }


}
