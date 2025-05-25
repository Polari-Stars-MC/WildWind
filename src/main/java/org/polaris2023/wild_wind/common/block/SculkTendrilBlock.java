package org.polaris2023.wild_wind.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.IShearable;

public class SculkTendrilBlock extends DoublePlantBlock implements IShearable {

    public static final MapCodec<SculkTendrilBlock> CODEC = simpleCodec(SculkTendrilBlock::new);

    @Override
    public MapCodec<? extends SculkTendrilBlock> codec() {
        return CODEC;
    }
    public SculkTendrilBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(Blocks.SCULK) || state.is(Blocks.SCULK_CATALYST);
    }
}
