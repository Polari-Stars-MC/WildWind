package org.polaris2023.wild_wind.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.AbstractBannerBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.apache.commons.lang3.Validate;
import org.polaris2023.wild_wind.common.block.ModAbstractBannerBlock;
import org.polaris2023.wild_wind.common.block.ModBannerBlock;
import org.polaris2023.wild_wind.common.block.ModWallBannerBlock;
import org.polaris2023.wild_wind.common.block.entity.ModBannerBlockEntity;

import java.util.List;

public class ModBannerItem extends BannerItem {
    public static final int DEFAULT_COLOR = 12030298;

    public ModBannerItem(Block block, Block wallBlock, Item.Properties properties) {
        super(block, wallBlock, properties);
        // Validate.isInstanceOf(ModAbstractBannerBlock.class, block);
        // Validate.isInstanceOf(ModAbstractBannerBlock.class, wallBlock);
    }

    public static void appendHoverTextFromBannerBlockEntityTag(ItemStack stack, List<Component> tooltipComponents) {
        BannerPatternLayers bannerpatternlayers = stack.get(DataComponents.BANNER_PATTERNS);
        if (bannerpatternlayers != null) {
            for(int i = 0; i < Math.min(bannerpatternlayers.layers().size(), 6); ++i) {
                BannerPatternLayers.Layer bannerpatternlayers$layer = bannerpatternlayers.layers().get(i);
                tooltipComponents.add(bannerpatternlayers$layer.description().withStyle(ChatFormatting.GRAY));
            }
        }

    }
/*
    public int getIntColor() {
        return ((ModAbstractBannerBlock)this.getBlock()).getIntColor();
    }

 */

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        appendHoverTextFromBannerBlockEntityTag(stack, tooltipComponents);
    }

    @Override
    public InteractionResult place(BlockPlaceContext context) {
        InteractionResult result = super.place(context);
        BlockEntity blockEntity = context.getLevel().getBlockEntity(context.getClickedPos());
        if(result.indicateItemUse()) {
            if (blockEntity instanceof ModBannerBlockEntity modBannerBlockEntity) {
                modBannerBlockEntity.color = DEFAULT_COLOR;
            }
        }
        return result;
    }
}
