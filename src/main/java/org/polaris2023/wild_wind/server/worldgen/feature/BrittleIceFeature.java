package org.polaris2023.wild_wind.server.worldgen.feature;

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

public class BrittleIceFeature extends Feature<NoneFeatureConfiguration> {
	private static final BlockStatePredicate IS_ICE = BlockStatePredicate.forBlock(Blocks.ICE);
	private static final BlockState BRITTLE_ICE = ModBlocks.BRITTLE_ICE.get().defaultBlockState();

	public BrittleIceFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	private static final int BOUND = 9;
	private static final double THRESHOLD = 0.04D;
	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel level = context.level();
		BlockPos blockPos = context.origin();
		if(level.getBlockState(blockPos.above()).isAir() && IS_ICE.test(level.getBlockState(blockPos))) {
			RandomUtil.GaussianMixture2D model = new RandomUtil.GaussianMixture2D(context.random().nextLong(), BOUND, 3);
			for(int i = 0; i < BOUND; ++i) {
				for(int j = 0; j < BOUND; ++j) {
					if(!level.getBlockState(blockPos.above()).isAir()) {
						continue;
					}
					BlockPos current = blockPos.offset(i, 0, j);
					double val = model.get(i, j);
					while(val >= THRESHOLD && IS_ICE.test(level.getBlockState(current))) {
						level.setBlock(current, BRITTLE_ICE, Block.UPDATE_CLIENTS);
						val -= THRESHOLD;
						current = current.below();
					}
				}
			}
			return true;
		}
		return false;
	}
}
