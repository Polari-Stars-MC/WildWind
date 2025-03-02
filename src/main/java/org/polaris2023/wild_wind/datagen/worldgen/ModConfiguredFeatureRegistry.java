package org.polaris2023.wild_wind.datagen.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.init.ModFeatures;
import org.polaris2023.wild_wind.util.Helpers;

import java.util.List;

public class ModConfiguredFeatureRegistry {
	public static final ResourceKey<ConfiguredFeature<?, ?>> BRITTLE_ICE = ResourceKey.create(Registries.CONFIGURED_FEATURE, Helpers.location("brittle_ice"));
	public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_BRITTLE_ICE = ResourceKey.create(Registries.CONFIGURED_FEATURE, Helpers.location("disk_brittle_ice"));

	public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		FeatureUtils.register(context, BRITTLE_ICE, ModFeatures.BRITTLE_ICE, NoneFeatureConfiguration.NONE);
		FeatureUtils.register(context, DISK_BRITTLE_ICE, Feature.DISK, new DiskConfiguration(
				RuleBasedBlockStateProvider.simple(ModBlocks.BRITTLE_ICE.get()),
				BlockPredicate.matchesBlocks(List.of(Blocks.ICE, ModBlocks.BRITTLE_ICE.get())),
				UniformInt.of(4, 6), 1
		));
	}
}
