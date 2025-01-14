package org.polaris2023.wild_wind.util;

import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;

public class EnchantmentHelper {

    public static boolean hasEnchantment(ServerLevel level, ItemStack stack, ResourceKey<Enchantment> resourceKey) {
        ItemEnchantments itemEnchantments = stack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
        for (var entry : itemEnchantments.entrySet()) {
            ResourceKey<Enchantment> key = entry.getKey().getKey();
            if (key != null && key.isFor(resourceKey.registryKey())) {

                return true;
            }
        }
        return false;
    }
}
