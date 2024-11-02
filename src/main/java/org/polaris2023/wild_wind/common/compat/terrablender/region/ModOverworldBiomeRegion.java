package org.polaris2023.wild_wind.common.compat.terrablender.region;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import org.polaris2023.wild_wind.common.compat.terrablender.biome.ModOverworldBiomeBuilder;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

import static org.polaris2023.wild_wind.WildWindMod.MOD_ID;

public class ModOverworldBiomeRegion extends Region {
    public static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(MOD_ID, "overworld_biome_provider");

    public ModOverworldBiomeRegion(int weight) {
        super(LOCATION, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        (new ModOverworldBiomeBuilder()).addBiomes(registry, mapper);
    }
}