package org.polaris2023.wild_wind.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.AbstractBannerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.polaris2023.wild_wind.common.block.entity.ModBannerBlockEntity;

public abstract class ModAbstractBannerBlock extends AbstractBannerBlock {
    private final int color;

    protected ModAbstractBannerBlock(int color, BlockBehaviour.Properties properties) {
        super(DyeColor.BLACK, properties);
        this.color = color;
    }

    protected abstract MapCodec<? extends ModAbstractBannerBlock> codec();

    public boolean isPossibleToRespawnInThis(BlockState state) {
        return true;
    }

    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ModBannerBlockEntity(pos, state);
    }

    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        BlockEntity var5 = level.getBlockEntity(pos);
        ItemStack var10000;
        if (var5 instanceof ModBannerBlockEntity bannerblockentity) {
            var10000 = bannerblockentity.getItem();
        } else {
            var10000 = super.getCloneItemStack(level, pos, state);
        }

        return var10000;
    }

    public DyeColor getColor() {
        return DyeColor.BLACK;
    }

    public int getIntColor() {
        return this.color;
    }
}
