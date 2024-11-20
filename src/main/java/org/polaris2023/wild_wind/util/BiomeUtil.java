package org.polaris2023.wild_wind.util;

import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.LevelEvent;
import org.polaris2023.wild_wind.common.init.ModBiomeKeys;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

import static org.polaris2023.wild_wind.WildWindMod.MOD_ID;

@SuppressWarnings({"unused", "resource"})
@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME, modid = MOD_ID)
public final class BiomeUtil {
    private static final List<Level> worldList = Lists.newArrayList();


    @SafeVarargs
    public static ResourceKey<Biome> biomeOrFallback(Registry<Biome> biomeRegistry, ModBiomeKeys.BiomeKey key, ResourceKey<Biome>... biomes) {
        if (isKeyRegistered(biomeRegistry, key)) {
            return key.key();
        }
        for(ResourceKey<Biome> biome: biomes)  {
            if(biome != null) {
                return biome;
            }
        }
        throw new RuntimeException("Failed to find fallback for biome!");
    }

    public static boolean isKeyRegistered(Registry<Biome> registry, @Nullable ModBiomeKeys.BiomeKey key) {
        return key != null && key.generate() && registry.get(key.key()) != null;
    }

    @OnlyIn(Dist.CLIENT)
    private static Registry<Biome> getClientBiomeRegistry() {
        Minecraft minecraft = Minecraft.getInstance();
        Level world = minecraft.level;
        if (world == null) {
            throw new RuntimeException("Cannot acquire biome registry when the world is null.");
        } else {
            return world.registryAccess().registryOrThrow(Registries.BIOME);
        }
    }

    @OnlyIn(Dist.CLIENT)
    private static ResourceKey<Biome> getClientKey(Biome biome) {
        return getClientBiomeRegistry().getResourceKey(biome).orElseThrow(
                () -> new RuntimeException("Failed to get client registry key for biome!")
        );
    }

    @OnlyIn(Dist.CLIENT)
    private static Biome getClientBiome(ResourceKey<Biome> key) {
        Biome biome = getClientBiomeRegistry().get(key);
        if (biome == null) {
            throw new RuntimeException("Failed to get client biome for registry key " + key.location() + "!");
        }

        return biome;
    }

    private static Biome getBiomeFromWorlds(ResourceKey<Biome> key) {
        Iterator<Level> var1 = worldList.iterator();

        Biome biome;
        do {
            if (!var1.hasNext()) {
                throw new RuntimeException("Failed to get biome for registry key " + key.location() + " !");
            }

            Level world = var1.next();
            biome = world.registryAccess().registryOrThrow(Registries.BIOME).get(key);
        } while(biome == null);

        return biome;
    }

    @SubscribeEvent
    public static void onWorldLoad(LevelEvent.Load event) {
        worldList.add((Level)event.getLevel());
    }

    @SubscribeEvent
    public static void onWorldUnload(LevelEvent.Unload event) {
        worldList.remove((Level)event.getLevel());
    }
}