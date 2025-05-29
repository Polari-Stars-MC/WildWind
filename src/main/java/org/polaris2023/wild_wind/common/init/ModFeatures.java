package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import org.polaris2023.wild_wind.server.worldgen.feature.*;
import org.polaris2023.wild_wind.util.Helpers;

import java.util.function.BiConsumer;

public final class ModFeatures {
	public static final ResourceLocation BRITTLE_ICE_ID = Helpers.location("brittle_ice");
	public static final ResourceLocation QUICKSAND_LAKE_ID = Helpers.location("quicksand_lake");
	public static final ResourceLocation SILT_ID = Helpers.location("silt");
	public static final ResourceLocation ASH_ID = Helpers.location("ash");
	public static final ResourceLocation AQUATIC_PLANT_FEATURE_ID = Helpers.location("aquatic_plant");
	public static final Feature<NoneFeatureConfiguration> BRITTLE_ICE = new BrittleIceFeature(NoneFeatureConfiguration.CODEC);
	@SuppressWarnings("deprecation")
	public static final Feature<LakeFeature.Configuration> QUICKSAND_LAKE = new QuickSandLakeFeature(LakeFeature.Configuration.CODEC);
	public static final Feature<NoneFeatureConfiguration> SILT = new SiltFeature(NoneFeatureConfiguration.CODEC);
	public static final Feature<NoneFeatureConfiguration> ASH = new AshFeature(NoneFeatureConfiguration.CODEC);
	public static final Feature<SimpleBlockConfiguration> AQUATIC_PLANT_FEATURE = new AquaticPlantFeature(SimpleBlockConfiguration.CODEC);

	private static <C extends FeatureConfiguration, F extends Feature<C>> F registerFeature(ResourceLocation key, F value) {
		return Registry.register(BuiltInRegistries.FEATURE, key, value);
	}

	public static void init(BiConsumer<ResourceLocation, Feature<?>> registry) {
		registry.accept(BRITTLE_ICE_ID, BRITTLE_ICE);
		registry.accept(QUICKSAND_LAKE_ID, QUICKSAND_LAKE);
		registry.accept(SILT_ID, SILT);
		registry.accept(ASH_ID, ASH);
		registry.accept(AQUATIC_PLANT_FEATURE_ID, AQUATIC_PLANT_FEATURE);
	}

	private ModFeatures() {
	}
}
