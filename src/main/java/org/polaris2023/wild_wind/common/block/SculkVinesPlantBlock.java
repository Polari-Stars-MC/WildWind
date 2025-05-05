package org.polaris2023.wild_wind.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.polaris2023.wild_wind.common.init.ModBlocks;

public class SculkVinesPlantBlock extends GrowingPlantBodyBlock {
	public static final MapCodec<SculkVinesPlantBlock> CODEC = simpleCodec(SculkVinesPlantBlock::new);
	public static final VoxelShape SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

	@Override
	public MapCodec<SculkVinesPlantBlock> codec() {
		return CODEC;
	}

	public SculkVinesPlantBlock(BlockBehaviour.Properties props) {
		super(props, Direction.DOWN, SHAPE, false);
	}

	@Override
	protected GrowingPlantHeadBlock getHeadBlock() {
		return ModBlocks.SCULK_VINES.get();
	}
}
