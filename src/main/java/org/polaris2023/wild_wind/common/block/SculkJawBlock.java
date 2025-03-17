package org.polaris2023.wild_wind.common.block;

import net.minecraft.core.BlockPos;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class SculkJawBlock extends Block {
    public static final IntegerProperty PHASE = IntegerProperty.create("phase", 0, 4);

    private static final String[] PHASE_TEXTURES = {
            "sculk_jaw",
            "sculk_jaw_tendril_active",
            "sculk_jaw_tendril_extend",
            "sculk_jaw_tendril_inactive",
            "sculk_jaw_tendril_retract"
    };

    public SculkJawBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(PHASE, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(PHASE);
    }


    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos ) {
        if (!worldIn.isClientSide) {//暂时是废案，没生效
            int newPhase = (state.getValue(PHASE) + 1) % PHASE_TEXTURES.length;
            worldIn.setBlock(pos, state.setValue(PHASE, newPhase), Block.UPDATE_ALL);
        }
        return InteractionResult.SUCCESS;
    }
    public String getCurrentTexture(BlockState state) {
        return PHASE_TEXTURES[state.getValue(PHASE)];
    }
}
