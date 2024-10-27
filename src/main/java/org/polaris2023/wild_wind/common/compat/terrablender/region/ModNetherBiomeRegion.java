package org.polaris2023.wild_wind.common.compat.terrablender.region;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

import static org.polaris2023.wild_wind.WildWindMod.MOD_ID;

public class ModNetherBiomeRegion extends Region {
	public static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(MOD_ID, "nether_biome_provider");

	public ModNetherBiomeRegion(int weight) {
		super(LOCATION, RegionType.NETHER, weight);
	}

	@Override
	public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
		this.addBiome(mapper, Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), 0.0F, Biomes.NETHER_WASTES);
		this.addBiome(mapper, Climate.Parameter.point(0.0F), Climate.Parameter.point(-0.5F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), 0.0F, Biomes.SOUL_SAND_VALLEY);
		this.addBiome(mapper, Climate.Parameter.point(0.4F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), 0.0F, Biomes.CRIMSON_FOREST);
		this.addBiome(mapper, Climate.Parameter.point(0.0F), Climate.Parameter.point(0.5F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), 0.375F, Biomes.WARPED_FOREST);
		this.addBiome(mapper, Climate.Parameter.point(-0.5F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), 0.175F, Biomes.BASALT_DELTAS);
	}
}
