package org.polaris2023.wild_wind.util;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wild_wind.WildWindMod;

import java.util.function.Supplier;

public interface Helpers {

    static ResourceLocation location(String path) {
        return ResourceLocation.fromNamespaceAndPath(WildWindMod.MOD_ID, path);
    }

    static void register(IEventBus eventBus,DeferredRegister<?>... registries) {
        for (DeferredRegister<?> registry : registries) registry.register(eventBus);
    }

    static <T> TagKey<T> tags(ResourceKey<Registry<T>> resourceKey, String name) {
        return TagKey.create(resourceKey, location(name));
    }

    static <T, E extends T> DeferredHolder<T, E> register(DeferredRegister<T> register, String name, Supplier<E> supplier) {
        return register.register(name, supplier);

    }

    static <T, E extends T> DeferredHolder<T, E> register(DeferredRegister<T> register, String name, E e) {
        return register.register(name, properties -> e);
    }

}
