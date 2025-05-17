package org.polaris2023.wild_wind.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.polaris2023.wild_wind.common.init.ModBlocks;

public class SculkVinesBlock extends GrowingPlantHeadBlock {
	public static final MapCodec<SculkVinesBlock> CODEC = simpleCodec(SculkVinesBlock::new);
	protected static final VoxelShape SHAPE = Block.box(4.0, 9.0, 4.0, 12.0, 16.0, 12.0);

	@Override
	public MapCodec<SculkVinesBlock> codec() {
		return CODEC;
	}

	public SculkVinesBlock(BlockBehaviour.Properties props) {
		super(props, Direction.DOWN, SHAPE, false, 0.1);
	}

	@Override
	protected int getBlocksToGrowWhenBonemealed(RandomSource randomSource) {
		return 1;
	}

	@Override
	protected Block getBodyBlock() {
		return ModBlocks.SCULK_ARTERY_PLANT.get();
	}

	@Override
	protected boolean canGrowInto(BlockState blockState) {
		return blockState.isAir();
	}
}
