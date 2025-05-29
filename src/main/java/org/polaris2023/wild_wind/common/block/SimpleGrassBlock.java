package org.polaris2023.wild_wind.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.IShearable;

import java.util.function.Function;

public class SimpleGrassBlock extends BushBlock implements IShearable {

    public static final MapCodec<SimpleGrassBlock> CODEC = simpleCodec(SimpleGrassBlock::new);
    private Function<BlockState, Boolean> placeFunction = (state) -> true;
    protected static final VoxelShape SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 13.0, 14.0);

    @Override
    public MapCodec<SimpleGrassBlock> codec() {
        return CODEC;
    }

    public SimpleGrassBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public SimpleGrassBlock(Properties properties, Function<BlockState, Boolean> function) {
        this(properties);
        this.placeFunction = function;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return placeFunction.apply(state);
    }
}
