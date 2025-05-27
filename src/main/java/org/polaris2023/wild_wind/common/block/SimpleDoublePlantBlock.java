package org.polaris2023.wild_wind.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.neoforged.neoforge.common.IShearable;

import java.util.function.Function;

public class SimpleDoublePlantBlock extends DoublePlantBlock implements IShearable {

    public static final MapCodec<SimpleDoublePlantBlock> CODEC = simpleCodec(SimpleDoublePlantBlock::new);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private Function<BlockState, Boolean> placeFunction = (state) -> true;

    @Override
    public MapCodec<? extends SimpleDoublePlantBlock> codec() {
        return CODEC;
    }
    public SimpleDoublePlantBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false));
    }

    public SimpleDoublePlantBlock(Properties properties, Function<BlockState, Boolean> function) {
        this(properties);
        this.placeFunction = function;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return placeFunction.apply(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HALF, WATERLOGGED);
    }
}
