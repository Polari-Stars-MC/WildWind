package org.polaris2023.wild_wind.common;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.player.PlayerContainerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.entity.Firefly;
import org.polaris2023.wild_wind.common.init.ModFoods;
import org.polaris2023.wild_wind.util.RegistryUtil;

import java.util.List;

@EventBusSubscriber(modid = WildWindMod.MOD_ID)
public class WildWindGameEventHandler {
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
