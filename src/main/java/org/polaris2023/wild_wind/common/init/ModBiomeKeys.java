package org.polaris2023.wild_wind.common.init;

import com.google.common.collect.Maps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import javax.annotation.Nullable;
import java.util.Map;

public class ModBiomeKeys {
    public record BiomeKey(ResourceKey<Biome> key, boolean generate, float suppress) {}

    public static final Map<ResourceKey<Biome>, BiomeKey> ALL_BIOME_KEYS = Maps.newHashMap();

    private static void putKey(BiomeKey biomeKey) {
        ALL_BIOME_KEYS.put(biomeKey.key(), biomeKey);
    }

    @Nullable
    public static BiomeKey getBiomeKey(ResourceKey<Biome> key) {
        return ALL_BIOME_KEYS.get(key);
    }

    public static float getSuppress(ResourceKey<Biome> key) {
        return getSuppress(key, 0.0F);
    }

    public static float getSuppress(ResourceKey<Biome> key, float defaultValue) {
        BiomeKey biomeKey = getBiomeKey(key);
        return biomeKey == null ? defaultValue : biomeKey.suppress();
    }
}