package org.polaris2023.wild_wind.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.IShearable;
import net.neoforged.neoforge.common.Tags;

public class TallBeachGrassBlock extends DoublePlantBlock implements IShearable {

    public static final MapCodec<TallBeachGrassBlock> CODEC = simpleCodec(TallBeachGrassBlock::new);

    @Override
    public MapCodec<? extends TallBeachGrassBlock> codec() {
        return CODEC;
    }
    public TallBeachGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(Tags.Blocks.SANDS);
    }
}
