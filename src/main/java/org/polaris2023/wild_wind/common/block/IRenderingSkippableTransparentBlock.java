package org.polaris2023.wild_wind.common.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public interface IRenderingSkippableTransparentBlock {
	boolean wild_wind$shouldSkipRender(BlockState state, BlockState adjacentBlockState, Direction side);
}
