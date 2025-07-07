package org.polaris2023.wild_wind.datagen.loot;

import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.polaris2023.wild_wind.common.init.ModBlocks;

/**
 * @author asuka
 * @since 2025/07/07
 */
public class VanillaLootTableProvider extends BlockLootSubProvider {

    private static final Set<Item> EXPLOSION_RESISTANT = ImmutableSet.of();

    public VanillaLootTableProvider(HolderLookup.Provider registries) {
        super(EXPLOSION_RESISTANT, FeatureFlags.DEFAULT_FLAGS, registries);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return List.of(Blocks.BLACKSTONE, Blocks.GILDED_BLACKSTONE);
    }

    @Override
    protected void generate() {
        this.add(Blocks.BLACKSTONE, (b) -> this.createOreDrop(b, ModBlocks.COBBLED_BLACKSTONE.asItem()));
        this.add(Blocks.GILDED_BLACKSTONE, (b) -> this.createOreDrop(b, ModBlocks.COBBLED_BLACKSTONE.asItem()));
    }
}
