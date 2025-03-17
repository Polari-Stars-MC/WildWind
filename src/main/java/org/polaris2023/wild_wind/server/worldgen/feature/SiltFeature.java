package org.polaris2023.wild_wind.server.worldgen.feature;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.util.RandomUtil;

import java.util.List;

public class SiltFeature extends AbstractLargeSurfaceReplacementFeature {
	public static final List<BlockStatePredicate> IS_MUD = Lists.newArrayList(
			BlockStatePredicate.forBlock(Blocks.MUD)
	);
	private static final BlockState SILT = ModBlocks.SILT.get().defaultBlockState();

	public SiltFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	protected int bound() {
		return 40;
	}

	@Override
	protected RandomUtil.GaussianMixture2D getModel(long seed, int bound) {
		return new RandomUtil.GaussianMixture2D(seed, bound, 6, 5.0D, 12.0D);
	}

	@Override
	protected double threshold() {
		return 1.0D / 40.0D;
	}

	@Override
	protected double offset(int i, int j, RandomSource randomSource) {
		double bound = this.bound();
		return 0.01D + 0.00075D * (20.0D - Math.sqrt(bound * (bound - (i + j) * 2.0D) + i * i * 2.0D + j * j * 2.0D)) + 0.005D * randomSource.nextDouble();
	}

	@Override
	protected boolean isAir(BlockState blockState) {
		return super.isAir(blockState) || blockState.is(Blocks.WATER);
	}

	@Override
	protected BlockState blockState(BlockState origin, BlockPos blockPos, RandomSource randomSource) {
		return SILT;
	}

	@Override
	protected boolean isAvailableStateToReplace(BlockState blockState) {
		return IS_MUD.stream().anyMatch(predicate -> predicate.test(blockState));
	}

	@Override
	protected int maxYShift() {
		return 8;
	}
}
