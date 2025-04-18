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

public class BrittleIceFeature extends AbstractLargeSurfaceReplacementFeature {
	public static final List<BlockStatePredicate> IS_ICE = Lists.newArrayList(
			BlockStatePredicate.forBlock(Blocks.ICE), BlockStatePredicate.forBlock(Blocks.PACKED_ICE)
	);
	private static final BlockState BRITTLE_ICE = ModBlocks.BRITTLE_ICE.get().defaultBlockState();

	public BrittleIceFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	protected int bound() {
		return 20;
	}

	@Override
	protected RandomUtil.GaussianMixture2D getModel(long seed, int bound) {
		return new RandomUtil.GaussianMixture2D(seed, bound, 5, 3.0D, 9.0D);
	}

	@Override
	protected double threshold() {
		return 1.0D / 32.0D;
	}

	@Override
	protected BlockState blockState(BlockState origin, BlockPos blockPos, RandomSource randomSource) {
		return BRITTLE_ICE;
	}

	@Override
	protected boolean isAvailableStateToReplace(BlockState blockState) {
		return IS_ICE.stream().anyMatch(predicate -> predicate.test(blockState));
	}
}
