package org.polaris2023.wild_wind.server.worldgen.feature;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.util.RandomUtil;

import java.util.List;

public class BrittleIceFeature extends Feature<NoneFeatureConfiguration> {
	public static final List<BlockStatePredicate> IS_ICE = Lists.newArrayList(
			BlockStatePredicate.forBlock(Blocks.ICE), BlockStatePredicate.forBlock(Blocks.PACKED_ICE)
	);
	private static final BlockState BRITTLE_ICE = ModBlocks.BRITTLE_ICE.get().defaultBlockState();

	public BrittleIceFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	private static final int BOUND = 20;
	private static final double THRESHOLD = 1.0D / 32.0D;
	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel level = context.level();
		BlockPos blockPos = context.origin();
		if(level.getBlockState(blockPos.above()).isAir() && IS_ICE.stream().anyMatch(predicate -> predicate.test(level.getBlockState(blockPos)))) {
			RandomUtil.GaussianMixture2D model = new RandomUtil.GaussianMixture2D(context.random().nextLong(), BOUND, 5, 3.0D, 9.0D);
			for(int i = 0; i < BOUND; ++i) {
				for(int j = 0; j < BOUND; ++j) {
					BlockPos current = blockPos.offset(i, 0, j);
					if(!level.getBlockState(current.above()).isAir()) {
						continue;
					}
					double val = model.get(i, j);
					while(val >= THRESHOLD) {
						final BlockPos finalCurrent = current;
						if(IS_ICE.stream().anyMatch(predicate -> predicate.test(level.getBlockState(finalCurrent)))) {
							level.setBlock(current, BRITTLE_ICE, Block.UPDATE_CLIENTS);
						}
						val -= THRESHOLD;
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
}
