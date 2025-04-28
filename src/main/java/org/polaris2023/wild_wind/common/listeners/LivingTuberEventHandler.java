package org.polaris2023.wild_wind.common.listeners;

import com.google.common.collect.Iterables;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import org.jetbrains.annotations.Nullable;
import org.polaris2023.wild_wind.common.init.ModItems;
import org.polaris2023.wild_wind.common.init.ModSounds;
import org.polaris2023.wild_wind.util.RandomUtil;

@EventBusSubscriber
public class LivingTuberEventHandler {

    private static void playSound(@Nullable Player player, Level world, BlockPos position, SoundEvent sound) {
        world.playSound(player, position, sound, SoundSource.AMBIENT, 1.0F, 1.0F);
    }

    public static void playAmbientSound(@Nullable Player player, Level world, BlockPos position) {
        SoundEvent sound = RandomUtil.randomValue(ModSounds.AMBIENT_S).get();
        playSound(player, world, position, sound);
    }

    public static void playDeathSound(@Nullable Player player, Level world, BlockPos position) {
        SoundEvent sound = RandomUtil.randomValue(ModSounds.DEATH_S).get();
        playSound(player, world, position, sound);
    }

    @SubscribeEvent
    public static void onEntityTick(EntityTickEvent.Post event) {
        switch(event.getEntity()) {
            case ItemEntity itemEntity -> {
                ItemStack itemStack = itemEntity.getItem();
                if(itemStack.is(ModItems.LIVING_TUBER) && RandomUtil.nextBoolean(5)) {
                    playAmbientSound(null, itemEntity.level(), itemEntity.blockPosition());
                }
            }
            case ItemFrame itemFrameEntity -> {
                ItemStack itemStack = itemFrameEntity.getItem();
                if(itemStack.is(ModItems.LIVING_TUBER) && RandomUtil.nextBoolean(5)) {
                    playAmbientSound(null, itemFrameEntity.level(), itemFrameEntity.blockPosition());
                }
            }
            case ArmorStand armorStandEntity -> {
                if(RandomUtil.nextBoolean(5) &&
                        Iterables.any(armorStandEntity.getHandSlots(), itemStack -> itemStack.is(ModItems.LIVING_TUBER))) {
                    playAmbientSound(null, armorStandEntity.level(), armorStandEntity.blockPosition());
                }
            }
            case Fox fox -> {
                if(RandomUtil.nextBoolean(5) && fox.getMainHandItem().is(ModItems.LIVING_TUBER)) {
                    playAmbientSound(null, fox.level(), fox.blockPosition());
                }
            }
            default -> {
            }
        }
    }

}
