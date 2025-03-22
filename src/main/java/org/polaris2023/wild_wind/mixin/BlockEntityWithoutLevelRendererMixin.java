package org.polaris2023.wild_wind.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import org.polaris2023.wild_wind.common.block.ModAbstractBannerBlock;
import org.polaris2023.wild_wind.common.block.entity.ModBannerBlockEntity;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.item.ModBannerItem;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockEntityWithoutLevelRenderer.class)
public class BlockEntityWithoutLevelRendererMixin {
    @Mutable
    @Final
    @Shadow
    private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;

    public BlockEntityWithoutLevelRendererMixin(BlockEntityRenderDispatcher blockEntityRenderDispatcher, EntityModelSet entityModelSet) {
        this.blockEntityRenderDispatcher = blockEntityRenderDispatcher;
    }

    @Unique
    private final ModBannerBlockEntity renderDyeableBanner = new ModBannerBlockEntity(BlockPos.ZERO, ModBlocks.BANNER.get().defaultBlockState());

    @Inject(method = "renderByItem", at = @At("HEAD"), cancellable = true)
    public void renderByItemInject(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay, CallbackInfo ci) {
        Item item = stack.getItem();
        if (item instanceof BlockItem) {
            Block block = ((BlockItem)item).getBlock();
            if (block instanceof ModAbstractBannerBlock) {
                this.renderDyeableBanner.fromItem(stack, ((ModAbstractBannerBlock) block).getIntColor());
                this.blockEntityRenderDispatcher.renderItem(this.renderDyeableBanner, poseStack, buffer, packedLight, packedOverlay);
                ci.cancel();
            }
        }

    }
}
