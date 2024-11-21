package org.polaris2023.wild_wind.common.init;

import com.mojang.datafixers.util.Function5;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import org.polaris2023.wild_wind.util.Helpers;

import java.util.function.Supplier;

public enum ModEnchantments implements Supplier<ResourceKey<Enchantment>> {
    ;
    private final ResourceKey<Enchantment> key;
    private final Function5<ResourceLocation, HolderGetter<DamageType>, HolderGetter<Enchantment>, HolderGetter<Item>, HolderGetter<Block>, Enchantment.Builder> builder;

    ModEnchantments(String name, Function5<ResourceLocation, HolderGetter<DamageType>, HolderGetter<Enchantment>, HolderGetter<Item>, HolderGetter<Block>, Enchantment.Builder> builder) {
        this.key = key(name);

        this.builder = builder;
    }

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<DamageType> hg = context.lookup(Registries.DAMAGE_TYPE);
        HolderGetter<Enchantment> hg1 = context.lookup(Registries.ENCHANTMENT);
        HolderGetter<Item> hg2 = context.lookup(Registries.ITEM);
        HolderGetter<Block> hg3 = context.lookup(Registries.BLOCK);
        for (ModEnchantments value : ModEnchantments.values()) {
            ResourceKey<Enchantment> key = value.get();
            context.register(key, value.builder.apply(key.location(), hg, hg1, hg2, hg3).build(key.location()));
        }
    }

    private static ResourceKey<Enchantment> key(String name) {
        return ResourceKey.create(Registries.ENCHANTMENT, Helpers.location(name));
    }

    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public ResourceKey<Enchantment> get() {
        return key;
    }
}
