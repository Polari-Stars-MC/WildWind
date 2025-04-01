package org.polaris2023.wild_wind.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.polaris2023.wild_wind.common.init.ModBlockEntityTypes;
import org.polaris2023.wild_wind.common.init.ModItems;

public class ModBannerBlockEntity extends BannerBlockEntity {

    public int color;

    public ModBannerBlockEntity(BlockPos pos, BlockState blockState, int color) {
        super(pos, blockState);
        this.color = color;
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntityTypes.BANNER_BE.get();
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(ModItems.BANNER.get());
    }

}