package org.polaris2023.wild_wind.common.event.game;

import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.entity.Firefly;
import org.polaris2023.wild_wind.common.init.ModEnchantments;
import org.polaris2023.wild_wind.common.init.items.ModBaseItems;
import org.polaris2023.wild_wind.util.EnchantmentHelper;
import org.polaris2023.wild_wind.util.TeleportUtil;

/**
 * @author : baka4n
 * {@code @Date : 2025/05/04 23:59:48}
 */
@EventBusSubscriber(modid = WildWindMod.MOD_ID)
public class LivingEvents {

    @SubscribeEvent
    public static void hurtEvent(LivingDamageEvent.Post event) {
        if (event.getEntity() instanceof Firefly firefly) {
            firefly.clearTicker();
        }
    }

    @SubscribeEvent
    public static void onUseItem(LivingEntityUseItemEvent.Finish event) {
        ItemStack itemStack = event.getItem();
        Item item = itemStack.getItem();
        LivingEntity livingEntity = event.getEntity();
        //common code


        // end common code
        if (!(livingEntity.level() instanceof ServerLevel serverLevel)) return;
        // server code
        // player use
        if(event.getEntity() instanceof Player player) {
            ItemStack resultStack = event.getResultStack();
            if (resultStack.has(DataComponents.DAMAGE) && EnchantmentHelper.hasEnchantment(serverLevel, resultStack, ModEnchantments.RUSTY.get())) {
                ItemEnchantments enchantments = resultStack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
                if (!enchantments.isEmpty()) {
                    for (var entry : enchantments.entrySet()) {
                        ResourceKey<Enchantment> key = entry.getKey().getKey();
                        if (key != null && key.isFor(ModEnchantments.RUSTY.get().registryKey())) {
                            int level = entry.getIntValue();// 0 1 2
                            resultStack.setDamageValue(resultStack.getDamageValue() + level + 1);
                        }
                    }
                }
            }
        }
        // end player use

        if(item.equals(Items.POPPED_CHORUS_FRUIT)) {
            if(TeleportUtil.tryTeleportToSurface(livingEntity, serverLevel, livingEntity.getOnPos()) || TeleportUtil.randomTeleportAround(livingEntity, serverLevel)) {
                serverLevel.gameEvent(GameEvent.TELEPORT, livingEntity.position(), GameEvent.Context.of(livingEntity));
                SoundSource soundsource;
                SoundEvent soundevent;
                if (livingEntity instanceof Fox) {
                    soundevent = SoundEvents.FOX_TELEPORT;
                    soundsource = SoundSource.NEUTRAL;
                } else {
                    soundevent = SoundEvents.CHORUS_FRUIT_TELEPORT;
                    soundsource = SoundSource.PLAYERS;
                }

                serverLevel.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), soundevent, soundsource);
                livingEntity.resetFallDistance();
            }

            if (livingEntity instanceof Player player) {
                player.resetCurrentImpulseContext();
                player.getCooldowns().addCooldown(item, 20);
            }
        } else if(item.equals(Items.GLISTERING_MELON_SLICE)) {
            livingEntity.heal(1.0F);
        } else if(itemStack.is(Tags.Items.FOODS_COOKED_FISH) || itemStack.is(Tags.Items.FOODS_RAW_FISH)) {
            if (livingEntity instanceof Player player) {
                player.addItem(new ItemStack(ModBaseItems.FISH_BONE));
            }
        }
    }
}
