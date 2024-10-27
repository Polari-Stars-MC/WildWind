package org.polaris2023.wild_wind.util;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import org.polaris2023.wild_wind.WildWindMod;

public class Helpers {

    public static ResourceLocation location(String path) {
        return ResourceLocation.fromNamespaceAndPath(WildWindMod.MOD_ID, path);
    }

    public static <T> TagKey<T> tags(ResourceKey<Registry<T>> resourceKey, String name) {
        return TagKey.create(resourceKey, location(name));
    }
}
