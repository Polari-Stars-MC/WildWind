package org.polaris2023.wild_wind.common;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.entity.Firefly;
import org.polaris2023.wild_wind.common.init.ModComponents;
import org.polaris2023.wild_wind.client.ModTranslateKey;
import org.polaris2023.wild_wind.util.RegistryUtil;

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

}
