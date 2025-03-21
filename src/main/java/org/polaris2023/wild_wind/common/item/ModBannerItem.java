package org.polaris2023.wild_wind.common.item;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.polaris2023.wild_wind.common.block.ModBannerBlockEntity;

public class ModBannerItem extends BannerItem {
    public static final int DEFAULT_COLOR = 12030298;

    public ModBannerItem(Block bannerBlock, Block wallBannerBlock, Properties settings) {
        super(bannerBlock, wallBannerBlock, settings);
    }

    @Override
    public InteractionResult place(BlockPlaceContext context) {
        InteractionResult result = super.place(context);
        BlockEntity blockEntity = context.getLevel().getBlockEntity(context.getClickedPos());
        if(result.indicateItemUse()) {
            if (blockEntity instanceof ModBannerBlockEntity dyeableBannerBlockEntity) {
                dyeableBannerBlockEntity.color = DEFAULT_COLOR;
            }
        }
        return result;
    }

    /*
    public static void appendBannerTooltip(ItemStack stack, List<Text> tooltip) {
        CustomBannerPatternsComponent bannerPatternsComponent = stack.get(UnidyeDataComponentTypes.CUSTOM_BANNER_PATTERNS);
        if (bannerPatternsComponent != null) {
            for (int i = 0; i < Math.min(bannerPatternsComponent.layers().size(), 6); i++) {
                CustomBannerPatternsComponent.Layer layer = bannerPatternsComponent.layers().get(i);
                tooltip.add(layer.getTooltipText());
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        DyeableBannerItem.appendBannerTooltip(stack, tooltip);
    }

     */
}
