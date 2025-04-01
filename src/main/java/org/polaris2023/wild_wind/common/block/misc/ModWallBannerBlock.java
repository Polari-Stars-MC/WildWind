package org.polaris2023.wild_wind.common.block.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.WallBannerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.polaris2023.wild_wind.common.block.entity.ModBannerBlockEntity;
import org.polaris2023.wild_wind.util.interfaces.IBannerBlock;

public class ModWallBannerBlock extends WallBannerBlock implements IBannerBlock {

    private final int color;

    public ModWallBannerBlock(int color, Properties properties) {
        super(DyeColor.WHITE, properties);
        this.color = color;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ModBannerBlockEntity(pos, state, this.color);
    }

    @Override
    public int getTextureDiffuseColor() {
        return this.color;
    }

}