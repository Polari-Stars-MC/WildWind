package org.polaris2023.wild_wind.datagen.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import org.polaris2023.wild_wind.util.Helpers;

import java.util.List;

public class ModPlacedFeatureRegistry {
	public static final ResourceKey<PlacedFeature> BRITTLE_ICE = ResourceKey.create(Registries.PLACED_FEATURE, Helpers.location("brittle_ice"));
	public static final ResourceKey<PlacedFeature> DISK_BRITTLE_ICE = ResourceKey.create(Registries.PLACED_FEATURE, Helpers.location("disk_brittle_ice"));

	public static void bootstrap(BootstrapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> configuredFeaturesLookup = context.lookup(Registries.CONFIGURED_FEATURE);
		PlacementUtils.register(
				context, BRITTLE_ICE, configuredFeaturesLookup.getOrThrow(ModConfiguredFeatureRegistry.BRITTLE_ICE),
				orePlacement(CountPlacement.of(2), HeightRangePlacement.uniform(VerticalAnchor.absolute(60), VerticalAnchor.absolute(67)))
		);
		PlacementUtils.register(
				context, DISK_BRITTLE_ICE, configuredFeaturesLookup.getOrThrow(ModConfiguredFeatureRegistry.DISK_BRITTLE_ICE),
				orePlacement(CountPlacement.of(6), HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR_WG))
		);
	}

	private static List<PlacementModifier> orePlacement(PlacementModifier countPlacement, PlacementModifier heightRange) {
		return List.of(countPlacement, InSquarePlacement.spread(), heightRange, BiomeFilter.biome());
	}
}
