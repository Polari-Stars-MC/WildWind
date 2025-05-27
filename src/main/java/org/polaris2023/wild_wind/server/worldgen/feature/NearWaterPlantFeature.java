package org.polaris2023.wild_wind.server.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;

public class NearWaterPlantFeature extends Feature<SimpleBlockConfiguration> {

    public NearWaterPlantFeature(Codec<SimpleBlockConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<SimpleBlockConfiguration> context) {
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        BlockPos origin = context.origin();
        int y = level.getHeight(Heightmap.Types.OCEAN_FLOOR, origin.getX(), origin.getZ());
        BlockPos current = new BlockPos(origin.getX(), y, origin.getZ());
        BlockState toPlace = context.config().toPlace().getState(random, current);

        // Check if there's a water source block within 4 blocks
        if (!level.isEmptyBlock(current) || !isNearWater(level, current, 4)) {
            return false;
        }

        // Try to place the block if conditions are met
        if (toPlace.canSurvive(level, current)) {
            BlockPos above = current.above();
            if (toPlace.hasProperty(TallFlowerBlock.HALF)) {
                BlockState upper = toPlace.setValue(TallFlowerBlock.HALF, DoubleBlockHalf.UPPER);
                if (level.getBlockState(above).isEmpty()) {
                    level.setBlock(current, toPlace, Block.UPDATE_CLIENTS);
                    level.setBlock(above, upper, Block.UPDATE_CLIENTS);
                }
            } else {
                level.setBlock(current, toPlace, Block.UPDATE_CLIENTS);
            }
            return true;
        }

        return false;
    }

    private boolean isNearWater(WorldGenLevel level, BlockPos pos, int radius) {
        for (BlockPos checkPos : BlockPos.betweenClosed(
                pos.offset(-radius, -radius, -radius),
                pos.offset(radius, radius, radius)
        )) {
            if (level.getFluidState(checkPos).is(FluidTags.WATER)
                    && level.getFluidState(checkPos).isSource()) {
                return true;
            }
        }
        return false;
    }
}
