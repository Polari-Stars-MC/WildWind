package org.polaris2023.wild_wind.common.block.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.polaris2023.wild_wind.common.block.entity.WoolBlockEntity;
import org.polaris2023.wild_wind.common.init.ModComponents;

/**
 * @author : baka4n
 * {@code @Date : 2025/02/16 21:46:09}
 */
public class WoolBlockItem extends BlockItem {
    public WoolBlockItem(Block block, Properties properties) {
        super(block, properties.component(ModComponents.COLOR, 0));
    }

    @Override
    public InteractionResult place(BlockPlaceContext context) {
        ItemStack itemInHand = context.getItemInHand();
        int rgb = itemInHand.getOrDefault(ModComponents.COLOR, 0);
        InteractionResult place = super.place(context);
        BlockPos clickedPos = context.getClickedPos();
        Level level = context.getLevel();

        BlockEntity blockEntity = level.getBlockEntity(clickedPos);
        if (blockEntity instanceof WoolBlockEntity) {
            ((WoolBlockEntity) blockEntity).rgb = rgb;
            blockEntity.setChanged();
        }
        return place;


    }
}
