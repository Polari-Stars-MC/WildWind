package org.polaris2023.wild_wind.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.polaris2023.wild_wind.common.block.entity.ModBannerBlockEntity;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.item.modified.ModBannerItem;

@OnlyIn(Dist.CLIENT)
public class ModBannerItemRenderer extends BlockEntityWithoutLevelRenderer {

    private final ModBannerBlockEntity banner = new ModBannerBlockEntity(BlockPos.ZERO, ModBlocks.BANNER.get().defaultBlockState(), 13419950);

    public ModBannerItemRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {}

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        if (stack.getItem() instanceof ModBannerItem bannerItem) {
            this.banner.color = bannerItem.getTextureDiffuseColor();
            this.banner.applyComponentsFromItemStack(stack);
            Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(this.banner, poseStack, buffer, packedLight, packedOverlay);
        }
    }

}