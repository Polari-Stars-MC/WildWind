package org.polaris2023.wild_wind.datagen.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.util.Helpers;

public class ModConfiguredFeatureRegistry {
	public static final ResourceKey<ConfiguredFeature<?, ?>> BRITTLE_ICE = ResourceKey.create(Registries.CONFIGURED_FEATURE, Helpers.location("brittle_ice"));

	public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		RuleTest isIce = new BlockMatchTest(Blocks.ICE);
		FeatureUtils.register(context, BRITTLE_ICE, Feature.ORE, new OreConfiguration(isIce, ModBlocks.BRITTLE_ICE.get().defaultBlockState(), 10));
	}
}
