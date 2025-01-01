package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.polaris2023.wild_wind.server.worldgen.feature.BrittleIceFeature;
import org.polaris2023.wild_wind.util.Helpers;

import java.util.function.BiConsumer;

public final class ModFeatures {
	public static final ResourceLocation BRITTLE_ICE_ID = Helpers.location("brittle_ice");
	public static final Feature<NoneFeatureConfiguration> BRITTLE_ICE = registerFeature(BRITTLE_ICE_ID, new BrittleIceFeature(NoneFeatureConfiguration.CODEC));

	private static <C extends FeatureConfiguration, F extends Feature<C>> F registerFeature(ResourceLocation key, F value) {
		return Registry.register(BuiltInRegistries.FEATURE, key, value);
	}

	public static void init(BiConsumer<ResourceLocation, Feature<?>> registry) {
		registry.accept(BRITTLE_ICE_ID, BRITTLE_ICE);
	}

	private ModFeatures() {
	}
}
