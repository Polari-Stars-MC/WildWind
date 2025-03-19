package org.polaris2023.wild_wind.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StemBlock.class)
public class MelonMixin {
    @Inject(method = "randomTick", at = @At("TAIL"))
    private void randomTickMixin(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if (level.getBlockState(pos).is(Blocks.ATTACHED_MELON_STEM)) {
            Direction direction = level.getBlockState(pos).getValue(HorizontalDirectionalBlock.FACING);
            BlockPos blockPos = pos.relative(direction);
            if(random.nextFloat() < 0.02F) {
                level.setBlockAndUpdate(blockPos, ModBlocks.GLISTERING_MELON.get().defaultBlockState());
                level.setBlockAndUpdate(pos, Blocks.ATTACHED_MELON_STEM.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, direction));
            }
        }
    }
}
