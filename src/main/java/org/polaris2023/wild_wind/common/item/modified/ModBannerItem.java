package org.polaris2023.wild_wind.common.item.modified;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.polaris2023.wild_wind.client.renderer.ModBannerItemRenderer;
import org.polaris2023.wild_wind.common.block.entity.ModBannerBlockEntity;
import org.polaris2023.wild_wind.util.interfaces.IBannerBlock;

public class ModBannerItem extends BannerItem {

    public static final int DEFAULT_COLOR = 13419950;

    public ModBannerItem(Block block, Block wallBlock, Item.Properties properties) {
        super(block, wallBlock, properties);
    }

    public int getTextureDiffuseColor() {
        return ((IBannerBlock) this.getBlock()).getTextureDiffuseColor();
    }

    @Override
    public InteractionResult place(BlockPlaceContext context) {
        InteractionResult result = super.place(context);
        BlockEntity blockEntity = context.getLevel().getBlockEntity(context.getClickedPos());
        if (result.indicateItemUse()) {
            if (blockEntity instanceof ModBannerBlockEntity modBannerBlockEntity) {
                modBannerBlockEntity.color = DEFAULT_COLOR;
            }
        }

        return result;
    }

    public static class RenderBannerItem implements IClientItemExtensions {

        @Override
        public BlockEntityWithoutLevelRenderer getCustomRenderer() {
            return new ModBannerItemRenderer();
        }

    }

}