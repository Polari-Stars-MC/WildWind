package org.polaris2023.wild_wind.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.polaris2023.wild_wind.util.interfaces.ISquid;

import java.util.List;

public class GlowPowderItem extends Item {
    private static final MutableComponent TOOLTIP = Component.translatable("item.wild_wind.glow_powder.desc").withStyle(ChatFormatting.GRAY);

    public GlowPowderItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        Level level = pPlayer.level();
        if (pPlayer.level().isClientSide) {
            return InteractionResult.FAIL;
        }
        boolean res = pInteractionTarget.addEffect(new MobEffectInstance(MobEffects.GLOWING, 300, 0, false, false));
        if (res) {
            pStack.consume(1, pPlayer);
            if(pInteractionTarget instanceof Squid) {
                ISquid living = (ISquid) pInteractionTarget;
                level.playSound(null, pInteractionTarget.getOnPos(), SoundEvents.GLOW_SQUID_SQUIRT, SoundSource.PLAYERS);
                living.wild_wind$setGlowingConverting(true);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(TOOLTIP);
    }
}
