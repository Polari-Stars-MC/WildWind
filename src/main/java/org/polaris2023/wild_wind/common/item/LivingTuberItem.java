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
import org.polaris2023.wild_wind.common.init.tags.ModSoundEventTags;
import org.polaris2023.wild_wind.util.RandomUtil;

public class LivingTuberItem extends Item {
    public LivingTuberItem(Properties properties) {
        super(properties);
    }


    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        RandomSource random = entity.getRandom();
        int j = random.nextInt(20, 200);
        if (level.getGameTime() % j == 0) {

            ModSoundEventTags.GLARE_AMBIENT.tagFor(named -> {
                level.playLocalSound(entity.getX(), entity.getY(), entity.getZ(), named.get(random.nextInt(named.size())).value(), SoundSource.AMBIENT, 1F, 1F, true);
            });
//            level.playLocalSound(entity.getX(), entity.getY(), entity.getZ(), ModSounds.AMBIENT_S.get(random.nextInt(ModSounds.AMBIENT_S.size())).get(), SoundSource.HOSTILE, 1F, 1F, true);
        }
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        super.onUseTick(level, livingEntity, stack, remainingUseDuration);
        RandomSource random = livingEntity.getRandom();
        if (remainingUseDuration % 20 == 0) {
            ModSoundEventTags.GLARE_AMBIENT.tagFor(named -> {
                level.playLocalSound(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), named.get(random.nextInt(named.size())).value(), SoundSource.AMBIENT, 1F, 1F, true);
            });
//            level.playLocalSound(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), ModSounds.AMBIENT_S.get(random.nextInt(ModSounds.AMBIENT_S.size())).get(), SoundSource.AMBIENT, 1F, 1F, true);
        }
    }

    /*
            eating and destroyed
             */
    @Override
    public void onDestroyed(ItemEntity itemEntity, DamageSource damageSource) {
        super.onDestroyed(itemEntity, damageSource);
        ModSoundEventTags.GLARE_AMBIENT.tagFor(named -> {
            itemEntity.playSound(named.get(itemEntity.getRandom().nextInt(named.size())).value());
        });
//        itemEntity.playSound(ModSounds.DEATH_S.get(itemEntity.getRandom().nextInt(ModSounds.DEATH_S.size())).get());
    }
}
