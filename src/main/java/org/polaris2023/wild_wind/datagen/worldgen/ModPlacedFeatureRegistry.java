package org.polaris2023.wild_wind.datagen.worldgen;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import org.polaris2023.wild_wind.util.Helpers;

import java.util.List;

public class ModPlacedFeatureRegistry {
	public static final ResourceKey<PlacedFeature> BRITTLE_ICE = create("brittle_ice");
	public static final ResourceKey<PlacedFeature> DISK_BRITTLE_ICE = create("disk_brittle_ice");

	//Ore
	public static final ResourceKey<PlacedFeature> ORE_SALT = create("ore_salt");
	public static final ResourceKey<PlacedFeature> ORE_SALT_BURIED = create("ore_salt_buried");

	//Patches
	public static final ResourceKey<PlacedFeature> PATCH_CATTAILS = create("patch_cattails");
	public static final ResourceKey<PlacedFeature> PATCH_REEDS = create("patch_reeds");

	//Waterlogged
	public static final ResourceKey<PlacedFeature> WATERLOGGED_CATTAILS = create("waterlogged_cattails");
	public static final ResourceKey<PlacedFeature> WATERLOGGED_REEDS = create("waterlogged_reeds");
	public static final ResourceKey<PlacedFeature> WATERLOGGED_AQUATIC = create("waterlogged_aquatic");
	public static final ResourceKey<PlacedFeature> WATERLOGGED_TALL_AQUATIC = create("waterlogged_tall_aquatic");
	public static final ResourceKey<PlacedFeature> PATCH_BEACH = create("patch_beach");
	public static final ResourceKey<PlacedFeature> PATCH_TALL_BEACH = create("patch_tall_beach");
	public static final ResourceKey<PlacedFeature> PATCH_THORN = create("patch_thorn");
	public static final ResourceKey<PlacedFeature> PATCH_LARGE_THORN = create("patch_large_thorn");

	//Quicksand
	public static final ResourceKey<PlacedFeature> QUICKSAND = create("quicksand");
	public static final ResourceKey<PlacedFeature> RED_QUICKSAND = create("red_quicksand");
	public static final ResourceKey<PlacedFeature> SILT = create("silt");
	public static final ResourceKey<PlacedFeature> SILT_DISK = create("silt_disk");

	public static final ResourceKey<PlacedFeature> ASH = create("ash");

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
		PlacementUtils.register(
				context, ORE_SALT, configuredFeaturesLookup.getOrThrow(ModConfiguredFeatureRegistry.ORE_SALT),
				orePlacement(CountPlacement.of(2), HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(32)))
		);
		PlacementUtils.register(
				context, ORE_SALT_BURIED, configuredFeaturesLookup.getOrThrow(ModConfiguredFeatureRegistry.ORE_SALT_BURIED),
				orePlacement(CountPlacement.of(2), HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(64)))
		);
		PlacementUtils.register(
				context, QUICKSAND, configuredFeaturesLookup.getOrThrow(ModConfiguredFeatureRegistry.QUICKSAND),
				RarityFilter.onAverageOnceEvery(16),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
				BiomeFilter.biome()
		);
		PlacementUtils.register(
				context, RED_QUICKSAND, configuredFeaturesLookup.getOrThrow(ModConfiguredFeatureRegistry.RED_QUICKSAND),
				RarityFilter.onAverageOnceEvery(16),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
				BiomeFilter.biome()
		);
		PlacementUtils.register(
				context, SILT, configuredFeaturesLookup.getOrThrow(ModConfiguredFeatureRegistry.SILT),
				RarityFilter.onAverageOnceEvery(24),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_TOP_SOLID,
				BiomeFilter.biome()
		);
		PlacementUtils.register(
				context, SILT_DISK, configuredFeaturesLookup.getOrThrow(ModConfiguredFeatureRegistry.SILT_DISK),
				RarityFilter.onAverageOnceEvery(10),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_TOP_SOLID,
				BiomeFilter.biome()
		);
		PlacementUtils.register(
				context, PATCH_CATTAILS, configuredFeaturesLookup.getOrThrow(ModConfiguredFeatureRegistry.PATCH_CATTAILS),
				RarityFilter.onAverageOnceEvery(6),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP,
				BiomeFilter.biome()
		);
		PlacementUtils.register(
				context, PATCH_REEDS, configuredFeaturesLookup.getOrThrow(ModConfiguredFeatureRegistry.PATCH_REEDS),
				RarityFilter.onAverageOnceEvery(8),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP,
				BiomeFilter.biome()
		);
		PlacementUtils.register(
				context, WATERLOGGED_CATTAILS, configuredFeaturesLookup.getOrThrow(ModConfiguredFeatureRegistry.WATERLOGGED_CATTAILS),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_TOP_SOLID,
				CountPlacement.of(12),
				BiomeFilter.biome()
		);
		PlacementUtils.register(
				context, WATERLOGGED_REEDS, configuredFeaturesLookup.getOrThrow(ModConfiguredFeatureRegistry.WATERLOGGED_REEDS),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_TOP_SOLID,
				CountPlacement.of(16),
				BiomeFilter.biome()
		);
		PlacementUtils.register(
				context, WATERLOGGED_AQUATIC, configuredFeaturesLookup.getOrThrow(ModConfiguredFeatureRegistry.WATERLOGGED_AQUATIC),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_TOP_SOLID,
				CountPlacement.of(32),
				BiomeFilter.biome()
		);
		PlacementUtils.register(
				context, WATERLOGGED_TALL_AQUATIC, configuredFeaturesLookup.getOrThrow(ModConfiguredFeatureRegistry.WATERLOGGED_TALL_AQUATIC),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_TOP_SOLID,
				CountPlacement.of(16),
				BiomeFilter.biome()
		);
		PlacementUtils.register(
				context, PATCH_BEACH, configuredFeaturesLookup.getOrThrow(ModConfiguredFeatureRegistry.PATCH_BEACH),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_TOP_SOLID,
				CountPlacement.of(1),
				BiomeFilter.biome()
		);
		PlacementUtils.register(
				context, PATCH_TALL_BEACH, configuredFeaturesLookup.getOrThrow(ModConfiguredFeatureRegistry.PATCH_TALL_BEACH),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_TOP_SOLID,
				CountPlacement.of(1),
				BiomeFilter.biome()
		);
		PlacementUtils.register(
				context,PATCH_THORN , configuredFeaturesLookup.getOrThrow(ModConfiguredFeatureRegistry.PATCH_THORN),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_TOP_SOLID,
				CountPlacement.of(1),
				BiomeFilter.biome()
		);
		PlacementUtils.register(
				context,PATCH_LARGE_THORN , configuredFeaturesLookup.getOrThrow(ModConfiguredFeatureRegistry.PATCH_LARGE_THORN),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_TOP_SOLID,
				CountPlacement.of(1),
				BiomeFilter.biome()
		);
		PlacementUtils.register(
				context, ASH,
				configuredFeaturesLookup.getOrThrow(ModConfiguredFeatureRegistry.ASH),
				CountPlacement.of(60),
				InSquarePlacement.spread(),
				HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.top()),
				EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.hasSturdyFace(Direction.UP), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
				RandomOffsetPlacement.vertical(ConstantInt.of(1)),
				BiomeFilter.biome()
		);
	}

	private static List<PlacementModifier> orePlacement(PlacementModifier countPlacement, PlacementModifier heightRange) {
		return List.of(countPlacement, InSquarePlacement.spread(), heightRange, BiomeFilter.biome());
	}

	private static ResourceKey<PlacedFeature> create(String name) {
		return ResourceKey.create(Registries.PLACED_FEATURE, Helpers.location(name));
	}
}
