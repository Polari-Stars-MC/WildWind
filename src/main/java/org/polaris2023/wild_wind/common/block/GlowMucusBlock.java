package org.polaris2023.wild_wind.common.block;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class GlowMucusBlock extends Block {
    public static final IntegerProperty LAYERS = IntegerProperty.create("layers", 1, 5);
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final int MAX_HEIGHT = 5;

    public GlowMucusBlock(Properties properties) {
        super(properties.strength(0, 0)
                .noOcclusion()
                .noCollission()
                .isRedstoneConductor((blockState, blockGetter, blockPos) -> true)
                );
        this.registerDefaultState(this.stateDefinition.any().setValue(LAYERS, 1).setValue(FACING, Direction.DOWN));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
        if (blockstate.is(this)) {
            int i = blockstate.getValue(LAYERS);
            return blockstate.setValue(LAYERS, Math.min(5, i + 1));
        } else {
            return super.getStateForPlacement(context);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LAYERS, FACING);
    }
}
