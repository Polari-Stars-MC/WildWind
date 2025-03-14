package org.polaris2023.wild_wind.common.item;

import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class MagicWandToolItem extends Item {
    public MagicWandToolItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if(!context.getLevel().isClientSide) {
            return InteractionResult.FAIL;
        }
        Level level = context.getLevel();
        Player player = context.getPlayer();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();
        final String[] MESSAGES = {
                "ID: " + clickedBlock.getDescriptionId(),
                "最大堆叠: " + clickedBlock.asItem().getDefaultMaxStackSize(),
                "硬度: " + clickedBlock.defaultBlockState().getDestroySpeed(level, context.getClickedPos()),
                "爆炸抗性: " + clickedBlock.getExplosionResistance(clickedBlock.defaultBlockState(), level, context.getClickedPos(), new Explosion(level, null, 0, 0, 0, 0, false, Explosion.BlockInteraction.DESTROY)),
                "引燃几率: " + clickedBlock.defaultBlockState().getFireSpreadSpeed(level, context.getClickedPos(), context.getClickedFace()),
                "烧毁几率: " + clickedBlock.defaultBlockState().getFlammability(level, context.getClickedPos(), context.getClickedFace()),
                "熔岩可燃性: " + clickedBlock.defaultBlockState().ignitedByLava(),
                "窒息生物: " + clickedBlock.defaultBlockState().isSuffocating(level, context.getClickedPos()),
                "红石导体: " + clickedBlock.defaultBlockState().isRedstoneConductor(level, context.getClickedPos()),
                "固体方块: " + clickedBlock.defaultBlockState().isSolidRender(level, context.getClickedPos()),
                "活塞推动行为: " + clickedBlock.defaultBlockState().getPistonPushReaction(),
                "亮度: " + clickedBlock.getLightEmission(clickedBlock.defaultBlockState(), level, context.getClickedPos())
        };
        if (player != null) {
            if(player.isShiftKeyDown()) {
                sendMessage(player, MESSAGES);
            }
        }

        return InteractionResult.SUCCESS;
    }

    protected void sendMessage(Player player, String[] messages) {
        for(String message : messages) {
            player.sendSystemMessage(Component.literal(message));
        }
    }
}
