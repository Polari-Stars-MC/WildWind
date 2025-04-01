package org.polaris2023.wild_wind.common.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.polaris2023.wild_wind.common.init.ModSounds;

public class LivingTuberItem extends Item {

    public LivingTuberItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        int j = entity.getRandom().nextInt(20, 200);
        if (level.getGameTime() % j == 0) {
            playRandomAmbientSound(level, entity);
        }
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        super.onUseTick(level, livingEntity, stack, remainingUseDuration);
        if (remainingUseDuration % 20 == 0) {
            playRandomAmbientSound(level, livingEntity);
        }
    }

    @Override
    public void onDestroyed(ItemEntity itemEntity, DamageSource damageSource) {
        RandomSource random = itemEntity.getRandom();
        int i = random.nextInt(ModSounds.DEATH_S.size());
        itemEntity.playSound(ModSounds.DEATH_S.get(i).get());
        super.onDestroyed(itemEntity, damageSource);
    }

    private static void playRandomAmbientSound(Level level, Entity entity) {
        int i = entity.getRandom().nextInt(ModSounds.AMBIENT_S.size());
        SoundEvent soundEvent = ModSounds.AMBIENT_S.get(i).get();
        level.playLocalSound(entity.getX(), entity.getY(), entity.getZ(), soundEvent, SoundSource.HOSTILE, 1F, 1F, true);
    }

}