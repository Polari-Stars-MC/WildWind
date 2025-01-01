package org.polaris2023.wild_wind.datagen.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.polaris2023.wild_wind.util.Helpers;

public class ModBiomeModifierRegistry {
	public static final ResourceKey<BiomeModifier> BRITTLE_ICE = ResourceKey.create(
			NeoForgeRegistries.Keys.BIOME_MODIFIERS, Helpers.location("brittle_ice")
	);

	public static void bootstrap(BootstrapContext<BiomeModifier> context) {
		HolderGetter<Biome> biomesLookup = context.lookup(Registries.BIOME);
		HolderGetter<PlacedFeature> placedFeaturesLookup = context.lookup(Registries.PLACED_FEATURE);
		context.register(BRITTLE_ICE, new BiomeModifiers.AddFeaturesBiomeModifier(
				biomesLookup.getOrThrow(Tags.Biomes.IS_COLD_OVERWORLD),
				HolderSet.direct(placedFeaturesLookup.getOrThrow(ModPlacedFeatureRegistry.BRITTLE_ICE)),
				GenerationStep.Decoration.VEGETAL_DECORATION)
		);
	}
}
