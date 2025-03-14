package org.polaris2023.wild_wind.server.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.LakeFeature;

@SuppressWarnings("deprecation")
public class QuickSandLakeFeature extends LakeFeature {
	public QuickSandLakeFeature(Codec<LakeFeature.Configuration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<LakeFeature.Configuration> context) {
		BlockPos below = context.origin().below();
		WorldGenLevel level = context.level();
		RandomSource random = context.random();
		if(context.config().barrier().getState(random, below).equals(level.getBlockState(below))) {
			return super.place(context);
		}
		return false;
	}
}
