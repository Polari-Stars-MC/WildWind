package org.polaris2023.wild_wind.util;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wild_wind.WildWindMod;

public class Helpers {

    public static ResourceLocation location(String path) {
        return ResourceLocation.fromNamespaceAndPath(WildWindMod.MOD_ID, path);
    }

    public static void register(IEventBus eventBus,DeferredRegister<?>... registries) {
        for (DeferredRegister<?> registry : registries) registry.register(eventBus);
    }

    public static <T> TagKey<T> tags(ResourceKey<Registry<T>> resourceKey, String name) {
        return TagKey.create(resourceKey, location(name));
    }

    public static TagKey<Item> itemTags(String name) {
        return ItemTags.create(location(name));
    }

    public static TagKey<Block> blockTags(String name) {
        return BlockTags.create(location(name));
    }

    public static TagKey<EntityType<?>> entityTags(String name) {
        return tags(Registries.ENTITY_TYPE, name);
    }

    public static TagKey<Fluid> fluidTags(String name) {
        return FluidTags.create(location(name));
    }

    public static TagKey<Enchantment> enchantmentTags(String name) {
        return tags(Registries.ENCHANTMENT, name);
    }

    public static TagKey<Biome> biomeTags(String name) {
        return tags(Registries.BIOME, name);
    }
}
