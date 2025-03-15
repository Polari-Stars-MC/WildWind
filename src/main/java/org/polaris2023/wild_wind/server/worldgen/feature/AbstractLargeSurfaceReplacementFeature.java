package org.polaris2023.wild_wind.server.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.polaris2023.wild_wind.util.RandomUtil;

public abstract class AbstractLargeSurfaceReplacementFeature extends Feature<NoneFeatureConfiguration> {
	public AbstractLargeSurfaceReplacementFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel level = context.level();
		BlockPos blockPos = context.origin();
		RandomSource randomSource = context.random();
		int bound = this.bound();
		double threshold = this.threshold();
		if(!this.isAir(level.getBlockState(blockPos.above())) || !this.isAvailableStateToReplace(level.getBlockState(blockPos))) {
			blockPos = this.yShiftBlockPos(blockPos, level);
		}
		if(this.isAir(level.getBlockState(blockPos.above())) && this.isAvailableStateToReplace(level.getBlockState(blockPos))) {
			RandomUtil.GaussianMixture2D model = this.getModel(randomSource.nextLong(), bound);
			for(int i = 0; i < bound; ++i) {
				for(int j = 0; j < bound; ++j) {
					BlockPos current = blockPos.offset(i, 0, j);
					if(!this.isAir(level.getBlockState(current.above()))) {
						continue;
					}
					double val = model.get(i, j) + this.offset(i, j, randomSource);
					while(val >= threshold) {
						BlockState blockState = level.getBlockState(current);

						if(this.isAvailableStateToReplace(blockState)) {
							level.setBlock(current, this.blockState(blockState, current, randomSource), Block.UPDATE_CLIENTS);
						} else {
							current = this.yShiftBlockPos(current, level);
							BlockState newBlockState = level.getBlockState(current);
							if(this.isAvailableStateToReplace(newBlockState)) {
								level.setBlock(current, this.blockState(newBlockState, current, randomSource), Block.UPDATE_CLIENTS);
							}
						}
						val -= threshold;
						current = current.below();
						if(current.getY() < 48) {
							break;
						}
					}
				}
			}
			return true;
		}
		return false;
	}

	protected abstract int bound();
	protected abstract RandomUtil.GaussianMixture2D getModel(long seed, int bound);
	protected abstract double threshold();
	protected abstract BlockState blockState(BlockState origin, BlockPos blockPos, RandomSource randomSource);
	protected abstract boolean isAvailableStateToReplace(BlockState blockState);

	protected int maxYShift() {
		return 5;
	}

	protected double offset(int i, int j, RandomSource randomSource) {
		return 0.0D;
	}

	protected boolean isAir(BlockState blockState) {
		return blockState.isAir();
	}

	private BlockPos yShiftBlockPos(BlockPos current, WorldGenLevel level) {
		BlockState blockState = level.getBlockState(current);
		int fails = this.maxYShift();
		if(this.isAir(blockState)) {
			while (this.isAir(blockState) && --fails >= 0) {
				current = current.below();
				blockState = level.getBlockState(current);
			}
		} else {
			do {
				current = current.above();
				blockState = level.getBlockState(current);
			} while (!this.isAir(blockState) && --fails >= 0);
			current = current.below();
		}
		return current;
	}
}
