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
import net.neoforged.neoforge.network.PacketDistributor;
import org.polaris2023.wild_wind.common.init.ModAttachmentTypes;
import org.polaris2023.wild_wind.common.network.packets.SquidConvertPacket;

import java.util.List;

public class GlowPowderItem extends Item {

    private static final MutableComponent TOOLTIP = Component.translatable("item.wild_wind.glow_powder.desc").withStyle(ChatFormatting.GRAY);

    public GlowPowderItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity interactionTarget, InteractionHand usedHand) {
        Level level = player.level();
        if (player.level().isClientSide) {
            return InteractionResult.FAIL;
        }

        MobEffectInstance mobEffectInstance = new MobEffectInstance(MobEffects.GLOWING, 300, 0, false, false);
        if (interactionTarget.addEffect(mobEffectInstance)) {
            stack.consume(1, player);
            if (interactionTarget instanceof Squid squid) {
                level.playSound(null, squid.getOnPos(), SoundEvents.GLOW_SQUID_SQUIRT, SoundSource.PLAYERS);
                PacketDistributor.sendToPlayersTrackingEntityAndSelf(squid, new SquidConvertPacket(squid.getId()));
                squid.setData(ModAttachmentTypes.SQUID_CONVERSION_TIME, 300);
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