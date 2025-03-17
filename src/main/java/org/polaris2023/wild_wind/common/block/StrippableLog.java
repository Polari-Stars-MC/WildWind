package org.polaris2023.wild_wind.common.block;

import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class StrippableLog extends RotatedPillarBlock {
    DeferredBlock<? extends Block> strippedState;

    public StrippableLog(Properties properties, DeferredBlock<? extends Block> stateSupplier) {
        super(properties);
        this.strippedState = stateSupplier;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
        return itemAbility == ItemAbilities.AXE_STRIP ? strippedState.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS)) : null;
    }
}
