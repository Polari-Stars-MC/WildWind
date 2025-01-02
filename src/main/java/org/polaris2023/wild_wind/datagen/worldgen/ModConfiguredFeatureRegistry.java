package org.polaris2023.wild_wind.datagen.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.polaris2023.wild_wind.common.init.ModFeatures;
import org.polaris2023.wild_wind.util.Helpers;

public class ModConfiguredFeatureRegistry {
	public static final ResourceKey<ConfiguredFeature<?, ?>> BRITTLE_ICE = ResourceKey.create(Registries.CONFIGURED_FEATURE, Helpers.location("brittle_ice"));

	public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		FeatureUtils.register(context, BRITTLE_ICE, ModFeatures.BRITTLE_ICE, NoneFeatureConfiguration.NONE);
	}
}
