package org.polaris2023.wild_wind.common.event;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import org.polaris2023.wild_wind.common.init.ModItems;
import org.polaris2023.wild_wind.common.inter.ICustomItemFrame;

public class InvisibleItemFrameEvent {
    public static InteractionResult run(Level world, Player player, InteractionHand hand, Entity entity, HitResult hitResult) {
        if ((entity.getType() == EntityType.ITEM_FRAME || entity.getType() == EntityType.GLOW_ITEM_FRAME)
                && hand == InteractionHand.MAIN_HAND
                && player.getItemInHand(hand).getItem() == ModItems.ASH_DUST.get()
                && player.isShiftKeyDown()
        ) {
            if (!world.isClientSide) {
                ICustomItemFrame frame = (ICustomItemFrame) entity;

                if (frame.wild_wind$getIsInvisible())
                    return InteractionResult.PASS;

                frame.wild_wind$setIsInvisible(true);

                player.getItemInHand(hand).consume(1, player);
                return InteractionResult.SUCCESS;
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

}
