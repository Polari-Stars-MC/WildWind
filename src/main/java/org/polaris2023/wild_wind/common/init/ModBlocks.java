package org.polaris2023.wild_wind.common.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wild_wind.WildWindMod;

import static org.polaris2023.wild_wind.common.init.ModItems.ITEMS;

public class ModBlocks {
    static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(WildWindMod.MOD_ID);
    public static final DeferredBlock<Block> FIREFLY_JAR = register("firefly_jar");

    public static final DeferredBlock<Block> GLAREFLOWER = register("glareflower");

    public static final DeferredItem<BlockItem> GLAREFLOWER_ITEM = register("glareflower", GLAREFLOWER);

    public static final DeferredBlock<Block> GLAREFLOWER_SEEDS = register("glareflower_seeds");

    public static final DeferredItem<BlockItem> GLAREFLOWER_SEEDS_ITEM = register("glareflower_seeds", GLAREFLOWER_SEEDS);

    private static DeferredItem<BlockItem> register(String name, DeferredBlock<Block> block) {
        return ITEMS.registerSimpleBlockItem(name, block);
    }

    private static DeferredBlock<Block> register(String name) {
        return BLOCKS.registerSimpleBlock(name, BlockBehaviour.Properties.of());
    }

    private static DeferredBlock<Block> register(String name, BlockBehaviour.Properties properties) {
        return BLOCKS.registerSimpleBlock(name, properties);
    }
}
