package org.polaris2023.wild_wind.common.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class GlowPowderItem extends Item {
    public GlowPowderItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        if (pPlayer.level().isClientSide) {
            return InteractionResult.FAIL;
        }
        boolean res = pInteractionTarget.addEffect(new MobEffectInstance(MobEffects.GLOWING, 300));
        if (res) {
            pStack.consume(1, pPlayer);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }
}
