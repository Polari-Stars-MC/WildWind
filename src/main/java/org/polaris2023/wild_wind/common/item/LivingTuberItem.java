package org.polaris2023.wild_wind.common.item;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.polaris2023.wild_wind.common.listeners.LivingTuberEventHandler;
import org.polaris2023.wild_wind.util.RandomUtil;

public class LivingTuberItem extends Item {
    public LivingTuberItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        if(entity instanceof Player player && RandomUtil.nextBoolean(5)) {
            LivingTuberEventHandler.playAmbientSound(player, player.level(), player.blockPosition());
        }
    }

    // eaten
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        ItemStack itemStack = super.finishUsingItem(stack, level, livingEntity);
        if(livingEntity instanceof Player player) {
            LivingTuberEventHandler.playDeathSound(player, level, livingEntity.blockPosition());
        } else if(livingEntity instanceof Fox) {
            LivingTuberEventHandler.playDeathSound(null, level, livingEntity.blockPosition());
        }
        return itemStack;
    }

    // item entity died
    @Override
    public void onDestroyed(ItemEntity itemEntity, DamageSource damageSource) {
        super.onDestroyed(itemEntity, damageSource);
        LivingTuberEventHandler.playDeathSound(null, itemEntity.level(), itemEntity.blockPosition());
    }
}
