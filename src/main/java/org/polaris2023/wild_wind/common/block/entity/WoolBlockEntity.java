package org.polaris2023.wild_wind.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.polaris2023.wild_wind.common.init.ModBlocks;

/**
 * @author : baka4n
 * {@code @Date : 2025/02/16 21:28:52}
 */
public class WoolBlockEntity extends BlockEntity {
    public int rgb = 0;
    public WoolBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlocks.WOOL_TILE.get(), pos, blockState);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("rgb", rgb);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        rgb = tag.getInt("rgb");
    }
}
