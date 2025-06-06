package org.polaris2023.wild_wind.datagen.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.polaris2023.wild_wind.util.Helpers;

public class ModBiomeModifierRegistry {
	public static final ResourceKey<BiomeModifier> BRITTLE_ICE = create("brittle_ice");
	public static final ResourceKey<BiomeModifier> SALT_ORE = create("salt_ore");
	public static final ResourceKey<BiomeModifier> QUICKSAND = create("quicksand");
	public static final ResourceKey<BiomeModifier> RED_QUICKSAND = create("red_quicksand");
	public static final ResourceKey<BiomeModifier> SILT = create("silt");
	public static final ResourceKey<BiomeModifier> PATCH_CATTAILS = create("patch_cattails");
	public static final ResourceKey<BiomeModifier> PATCH_REEDS = create("patch_reeds");
	public static final ResourceKey<BiomeModifier> WATERLOGGED_CATTAILS = create("waterlogged_cattails");
	public static final ResourceKey<BiomeModifier> WATERLOGGED_REEDS = create("waterlogged_reeds");
	public static final ResourceKey<BiomeModifier> WATERLOGGED_AQUATIC = create("waterlogged_aquatic");
	public static final ResourceKey<BiomeModifier> WATERLOGGED_TALL_AQUATIC = create("waterlogged_tall_aquatic");
	public static final ResourceKey<BiomeModifier> PATCH_BEACH = create("patch_beach");
	public static final ResourceKey<BiomeModifier> PATCH_TALL_BEACH = create("patch_tall_beach");
	public static final ResourceKey<BiomeModifier> PATCH_THORN = create("patch_thorn");
	public static final ResourceKey<BiomeModifier> PATCH_LARGE_THORN = create("patch_large_thorn");
	public static final ResourceKey<BiomeModifier> ASH = create("ash");

	public static void bootstrap(BootstrapContext<BiomeModifier> context) {
		HolderGetter<Biome> biomesLookup = context.lookup(Registries.BIOME);
		HolderGetter<PlacedFeature> placedFeaturesLookup = context.lookup(Registries.PLACED_FEATURE);
		context.register(BRITTLE_ICE, new BiomeModifiers.AddFeaturesBiomeModifier(
				biomesLookup.getOrThrow(Tags.Biomes.IS_COLD_OVERWORLD),
				HolderSet.direct(placedFeaturesLookup.getOrThrow(ModPlacedFeatureRegistry.BRITTLE_ICE), placedFeaturesLookup.getOrThrow(ModPlacedFeatureRegistry.DISK_BRITTLE_ICE)),
				GenerationStep.Decoration.TOP_LAYER_MODIFICATION
		));
		context.register(SALT_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
				biomesLookup.getOrThrow(Tags.Biomes.IS_OVERWORLD),
				HolderSet.direct(placedFeaturesLookup.getOrThrow(ModPlacedFeatureRegistry.ORE_SALT), placedFeaturesLookup.getOrThrow(ModPlacedFeatureRegistry.ORE_SALT_BURIED)),
				GenerationStep.Decoration.UNDERGROUND_ORES
		));
		context.register(QUICKSAND, new BiomeModifiers.AddFeaturesBiomeModifier(
				biomesLookup.getOrThrow(Tags.Biomes.IS_DESERT),
				HolderSet.direct(placedFeaturesLookup.getOrThrow(ModPlacedFeatureRegistry.QUICKSAND)),
				GenerationStep.Decoration.LAKES
		));
		context.register(RED_QUICKSAND, new BiomeModifiers.AddFeaturesBiomeModifier(
				biomesLookup.getOrThrow(Tags.Biomes.IS_BADLANDS),
				HolderSet.direct(placedFeaturesLookup.getOrThrow(ModPlacedFeatureRegistry.RED_QUICKSAND)),
				GenerationStep.Decoration.LAKES
		));
		context.register(SILT, new BiomeModifiers.AddFeaturesBiomeModifier(
				biomesLookup.getOrThrow(Tags.Biomes.IS_SWAMP),
				HolderSet.direct(placedFeaturesLookup.getOrThrow(ModPlacedFeatureRegistry.SILT), placedFeaturesLookup.getOrThrow(ModPlacedFeatureRegistry.SILT_DISK)),
				GenerationStep.Decoration.FLUID_SPRINGS
		));
		context.register(PATCH_CATTAILS, new BiomeModifiers.AddFeaturesBiomeModifier(
				biomesLookup.getOrThrow(Tags.Biomes.IS_SWAMP),
				HolderSet.direct(placedFeaturesLookup.getOrThrow(ModPlacedFeatureRegistry.PATCH_CATTAILS)),
				GenerationStep.Decoration.VEGETAL_DECORATION
		));
		context.register(PATCH_REEDS, new BiomeModifiers.AddFeaturesBiomeModifier(
				biomesLookup.getOrThrow(Tags.Biomes.IS_SWAMP),
				HolderSet.direct(placedFeaturesLookup.getOrThrow(ModPlacedFeatureRegistry.PATCH_REEDS)),
				GenerationStep.Decoration.VEGETAL_DECORATION
		));
		context.register(WATERLOGGED_CATTAILS, new BiomeModifiers.AddFeaturesBiomeModifier(
				biomesLookup.getOrThrow(Tags.Biomes.IS_SWAMP),
				HolderSet.direct(placedFeaturesLookup.getOrThrow(ModPlacedFeatureRegistry.WATERLOGGED_CATTAILS)),
				GenerationStep.Decoration.VEGETAL_DECORATION
		));
		context.register(WATERLOGGED_REEDS, new BiomeModifiers.AddFeaturesBiomeModifier(
				biomesLookup.getOrThrow(Tags.Biomes.IS_SWAMP),
				HolderSet.direct(placedFeaturesLookup.getOrThrow(ModPlacedFeatureRegistry.WATERLOGGED_REEDS)),
				GenerationStep.Decoration.VEGETAL_DECORATION
		));
		context.register(WATERLOGGED_AQUATIC, new BiomeModifiers.AddFeaturesBiomeModifier(
				biomesLookup.getOrThrow(Tags.Biomes.IS_RIVER),
				HolderSet.direct(placedFeaturesLookup.getOrThrow(ModPlacedFeatureRegistry.WATERLOGGED_AQUATIC)),
				GenerationStep.Decoration.VEGETAL_DECORATION
		));
		context.register(WATERLOGGED_TALL_AQUATIC, new BiomeModifiers.AddFeaturesBiomeModifier(
				biomesLookup.getOrThrow(Tags.Biomes.IS_RIVER),
				HolderSet.direct(placedFeaturesLookup.getOrThrow(ModPlacedFeatureRegistry.WATERLOGGED_TALL_AQUATIC)),
				GenerationStep.Decoration.VEGETAL_DECORATION
		));
		context.register(PATCH_BEACH, new BiomeModifiers.AddFeaturesBiomeModifier(
				biomesLookup.getOrThrow(Tags.Biomes.IS_BEACH),
				HolderSet.direct(placedFeaturesLookup.getOrThrow(ModPlacedFeatureRegistry.PATCH_BEACH)),
				GenerationStep.Decoration.VEGETAL_DECORATION
		));
		context.register(PATCH_TALL_BEACH, new BiomeModifiers.AddFeaturesBiomeModifier(
				biomesLookup.getOrThrow(Tags.Biomes.IS_BEACH),
				HolderSet.direct(placedFeaturesLookup.getOrThrow(ModPlacedFeatureRegistry.PATCH_TALL_BEACH)),
				GenerationStep.Decoration.VEGETAL_DECORATION
		));
		context.register(PATCH_THORN, new BiomeModifiers.AddFeaturesBiomeModifier(
				biomesLookup.getOrThrow(Tags.Biomes.IS_SWAMP),
				HolderSet.direct(placedFeaturesLookup.getOrThrow(ModPlacedFeatureRegistry.PATCH_THORN)),
				GenerationStep.Decoration.VEGETAL_DECORATION
		));
		context.register(PATCH_LARGE_THORN, new BiomeModifiers.AddFeaturesBiomeModifier(
				biomesLookup.getOrThrow(Tags.Biomes.IS_SWAMP),
				HolderSet.direct(placedFeaturesLookup.getOrThrow(ModPlacedFeatureRegistry.PATCH_LARGE_THORN)),
				GenerationStep.Decoration.VEGETAL_DECORATION
		));
		context.register(ASH, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomesLookup.getOrThrow(Biomes.BASALT_DELTAS)),
				HolderSet.direct(placedFeaturesLookup.getOrThrow(ModPlacedFeatureRegistry.ASH)),
				GenerationStep.Decoration.UNDERGROUND_DECORATION
		));
	}

	private static ResourceKey<BiomeModifier> create(String name) {
		return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, Helpers.location(name));
	}
}
