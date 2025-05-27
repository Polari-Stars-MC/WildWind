package org.polaris2023.wild_wind.server.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;

public class AquaticPlantFeature extends Feature<SimpleBlockConfiguration> {
	public AquaticPlantFeature(Codec<SimpleBlockConfiguration> props) {
		super(props);
	}

	@Override
	public boolean place(FeaturePlaceContext<SimpleBlockConfiguration> context) {
		boolean flag = false;
		RandomSource random = context.random();
		WorldGenLevel level = context.level();
		BlockPos origin = context.origin();
		int xOffset = random.nextInt(8) - random.nextInt(8);
		int zOffset = random.nextInt(8) - random.nextInt(8);
		int y = level.getHeight(Heightmap.Types.OCEAN_FLOOR, origin.getX() + xOffset, origin.getZ() + zOffset);
		BlockPos current = new BlockPos(origin.getX() + xOffset, y, origin.getZ() + zOffset);
		BlockState toPlace = context.config().toPlace().getState(random, current);
		if (level.getBlockState(current).is(Blocks.WATER)) {
			BlockState lower = toPlace.setValue(BlockStateProperties.WATERLOGGED, true);
			if (lower.canSurvive(level, current)) {
				BlockPos above = current.above();
				if (toPlace.hasProperty(TallFlowerBlock.HALF)) {
					BlockState upper = toPlace.setValue(TallFlowerBlock.HALF, DoubleBlockHalf.UPPER).setValue(BlockStateProperties.WATERLOGGED, false);
					if (!level.getBlockState(above).is(Blocks.WATER)) {
						level.setBlock(current, lower, Block.UPDATE_CLIENTS);
						level.setBlock(above, upper, Block.UPDATE_CLIENTS);
					}
				} else {
					if (!level.getBlockState(above).is(Blocks.WATER)) {
						level.setBlock(current, lower, Block.UPDATE_CLIENTS);
					}
				}

				flag = true;
			}
		}

		return flag;
	}
}
