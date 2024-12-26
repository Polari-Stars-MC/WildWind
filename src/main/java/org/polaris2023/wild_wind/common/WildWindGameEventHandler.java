package org.polaris2023.wild_wind.common;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.entity.Firefly;
import org.polaris2023.wild_wind.common.init.ModComponents;
import org.polaris2023.wild_wind.client.ModTranslateKey;
import org.polaris2023.wild_wind.common.init.ModEntityDataAccess;
import org.polaris2023.wild_wind.common.init.ModItems;
import org.polaris2023.wild_wind.common.init.ModSounds;
import org.polaris2023.wild_wind.util.RegistryUtil;
import org.polaris2023.wild_wind.util.TeleportUtil;

import java.util.List;
import java.util.function.Supplier;

@EventBusSubscriber(modid = WildWindMod.MOD_ID)
public class WildWindGameEventHandler {

    @SubscribeEvent
    public static void tooltipAdd(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        List<Component> toolTip = event.getToolTip();
        if (stack.has(DataComponents.FOOD)) {
            FoodProperties foodProperties = stack.get(DataComponents.FOOD);
            assert foodProperties != null;
            toolTip.addLast(Component.empty().append(ModTranslateKey.NUTRITION.translatable()).append(String.valueOf(foodProperties.nutrition())));
            toolTip.addLast(Component.empty().append(ModTranslateKey.SATURATION.translatable()).append(String.valueOf(foodProperties.saturation())));
            List<FoodProperties.PossibleEffect> effects = foodProperties.effects();
            if (!effects.isEmpty()) {
                toolTip.addLast(ModTranslateKey.EFFECT.translatable());
                for (FoodProperties.PossibleEffect effect : effects) {
                    MobEffectInstance effected = effect.effect();
                    toolTip.addLast(Component
                            .empty()
                            .append(effect.probability() * 100 + "% ")
                            .append(Component.translatable(effected.getDescriptionId()))
                            .append((effected.getAmplifier() + 1) + " ")
                            .append(String.valueOf(effected.getDuration()))
                            .append("tick")
                    );
                }
            }

        }
        componentAdd(stack, toolTip, ModTranslateKey.MEAT_VALUE, ModComponents.MEAT_VALUE, 0F);
        componentAdd(stack, toolTip, ModTranslateKey.VEGETABLE_VALUE, ModComponents.VEGETABLE_VALUE, 0F);
        componentAdd(stack, toolTip, ModTranslateKey.FRUIT_VALUE, ModComponents.FRUIT_VALUE, 0F);
        componentAdd(stack, toolTip, ModTranslateKey.PROTEIN_VALUE, ModComponents.PROTEIN_VALUE, 0F);
        componentAdd(stack, toolTip, ModTranslateKey.FISH_VALUE, ModComponents.FISH_VALUE, 0F);
        componentAdd(stack, toolTip, ModTranslateKey.MONSTER_VALUE, ModComponents.MONSTER_VALUE, 0F);
        componentAdd(stack, toolTip, ModTranslateKey.SWEET_VALUE, ModComponents.SWEET_VALUE, 0F);

    }

    @SuppressWarnings("SameParameterValue")
    private static <T> void componentAdd(ItemStack stack, List<Component> toolTip, ModTranslateKey key, Supplier<DataComponentType<T>> data, T defaultT) {
        if (stack.has(data)) {
            toolTip.addLast(Component
                    .empty()
                    .append(key.translatable())
                    .append(String.valueOf(stack.getOrDefault(data, defaultT))));
        }
    }


    @SubscribeEvent
    public static void hurtEvent(LivingDamageEvent.Post event) {
        if (event.getEntity() instanceof Firefly firefly) {
            firefly.clearTicker();
        }
    }

    private static final ResourceLocation VANILLA_FISHERMAN = ResourceLocation.withDefaultNamespace("fisherman");
    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event) {
        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
        ResourceLocation currentVillagerProfession = RegistryUtil.getDefaultRegisterName(event.getType());
        //TODO: add villager trades
        if(VANILLA_FISHERMAN.equals(currentVillagerProfession)) {

        }
    }

    @SubscribeEvent
    private static void mobTick(EntityTickEvent.Pre event) {

        switch (event.getEntity()) {
            case Goat goat -> {
                int i = goat.getEntityData().get(ModEntityDataAccess.MILKING_INTERVALS_BY_GOAT);
                if (i > 0) {
                    goat.getEntityData().set(ModEntityDataAccess.MILKING_INTERVALS_BY_GOAT, i - 1);
                }
            }
            case Cow cow -> {
                int i = cow.getEntityData().get(ModEntityDataAccess.MILKING_INTERVALS_BY_COW);
                if (i > 0) {
                    WildWindMod.LOGGER.info(i);
                    cow.getEntityData().set(ModEntityDataAccess.MILKING_INTERVALS_BY_COW, i - 1);
                }
            }
            case ItemEntity item -> {
                if (item.getItem().is(ModItems.LIVING_TUBER)) {
                    RandomSource random = item.getRandom();
                    Level level = item.level();
                    int j = random.nextInt(20, 200);
                    if (level.getGameTime() % j == 0) {
                        int i = random.nextInt(1, 13);
                        ModSounds sounds = ModSounds.AMBIENT_S.getOrDefault(i, ModSounds.GLARE_AMBIENT_1);
                        level.playLocalSound(item.getX(), item.getY(), item.getZ(), sounds.get(), SoundSource.HOSTILE, 1F, 1F, true);
                    }
                }
            }
            default -> {}
        }
    }

    @SubscribeEvent
    public static void onFinishUsingItem(LivingEntityUseItemEvent.Finish event) {
        Item item = event.getItem().getItem();
        LivingEntity livingEntity = event.getEntity();
        if(item.equals(Items.POPPED_CHORUS_FRUIT)) {
            if(livingEntity.level() instanceof ServerLevel serverLevel) {
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
            }
        } else if(item.equals(Items.GLISTERING_MELON_SLICE)) {
            if(!livingEntity.level().isClientSide) {
                livingEntity.heal(1.0F);
            }
        }
    }
}
