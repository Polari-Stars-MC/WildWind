package org.polaris2023.wild_wind.mixin;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.polaris2023.wild_wind.common.init.tags.ModBlockTags;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HalfTransparentBlock.class)
@Debug(export = true)
public class HalfTransparentBlockMixin {
	@Inject(method = "skipRendering", at = @At(value = "HEAD"), cancellable = true)
	private void wild_wind$injectSkipRendering(BlockState state, BlockState adjacentBlockState, Direction side, CallbackInfoReturnable<Boolean> cir) {
		if (adjacentBlockState.is(ModBlockTags.ICE_SKIP.get())) {
			cir.setReturnValue(true);
		}
	}
}
