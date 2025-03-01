package org.polaris2023.wild_wind.mixin;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.polaris2023.wild_wind.common.block.IRenderingSkippableTransparentBlock;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(IceBlock.class)
@Debug(export = true)
public class IceBlockMixin implements IRenderingSkippableTransparentBlock {
	@Override
	public boolean wild_wind$shouldSkipRender(BlockState state, BlockState adjacentBlockState, Direction side) {
		return adjacentBlockState.is(ModBlocks.BRITTLE_ICE);
	}
}
