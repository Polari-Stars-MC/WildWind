package org.polaris2023.wild_wind.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.polaris2023.wild_wind.common.init.ModBlocks;

public class ModBannerBlockEntity extends BannerBlockEntity {

    public int color;

    public ModBannerBlockEntity(BlockPos pos, BlockState blockState, int color) {
        super(pos, blockState);
        this.color = color;
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModBlocks.BANNER_BE.get();
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(ModBlocks.BANNER_ITEM.get());
    }

}