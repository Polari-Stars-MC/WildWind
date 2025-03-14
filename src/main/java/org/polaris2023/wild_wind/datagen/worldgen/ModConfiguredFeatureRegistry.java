package org.polaris2023.wild_wind.datagen.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.init.ModFeatures;
import org.polaris2023.wild_wind.util.Helpers;

import java.util.List;

public class ModConfiguredFeatureRegistry {
	private static final RuleTest STONE_ORE_REPLACEABLES = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
	private static final RuleTest DEEPSLATE_ORE_REPLACEABLES = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

	public static final ResourceKey<ConfiguredFeature<?, ?>> BRITTLE_ICE = create("brittle_ice");
	public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_BRITTLE_ICE = create("disk_brittle_ice");

	//Ore
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SALT = create("ore_salt");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SALT_BURIED = create("ore_salt_buried");

	//Quicksand
	public static final ResourceKey<ConfiguredFeature<?, ?>> QUICKSAND = create("quicksand");
	public static final ResourceKey<ConfiguredFeature<?, ?>> RED_QUICKSAND = create("red_quicksand");

	@SuppressWarnings("deprecation")
	public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		FeatureUtils.register(context, BRITTLE_ICE, ModFeatures.BRITTLE_ICE, NoneFeatureConfiguration.NONE);
		FeatureUtils.register(context, DISK_BRITTLE_ICE, Feature.DISK, new DiskConfiguration(
				RuleBasedBlockStateProvider.simple(ModBlocks.BRITTLE_ICE.get()),
				BlockPredicate.matchesBlocks(List.of(Blocks.ICE, ModBlocks.BRITTLE_ICE.get())),
				UniformInt.of(4, 6), 1
		));
		FeatureUtils.register(context, ORE_SALT, Feature.ORE, new OreConfiguration(List.of(
				OreConfiguration.target(STONE_ORE_REPLACEABLES, ModBlocks.SALT_ORE.get().defaultBlockState()),
				OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SALT_ORE.get().defaultBlockState())
		), 7));
		FeatureUtils.register(context, ORE_SALT_BURIED, Feature.ORE, new OreConfiguration(List.of(
				OreConfiguration.target(STONE_ORE_REPLACEABLES, ModBlocks.SALT_ORE.get().defaultBlockState()),
				OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SALT_ORE.get().defaultBlockState())
		), 7, 1.0F));
		FeatureUtils.register(context, QUICKSAND, ModFeatures.QUICKSAND_LAKE, new LakeFeature.Configuration(
				BlockStateProvider.simple(ModBlocks.QUICKSAND.get()), BlockStateProvider.simple(Blocks.SAND)
		));
		FeatureUtils.register(context, RED_QUICKSAND, ModFeatures.QUICKSAND_LAKE, new LakeFeature.Configuration(
				BlockStateProvider.simple(ModBlocks.RED_QUICKSAND.get()), BlockStateProvider.simple(Blocks.RED_SAND)
		));
	}

	private static ResourceKey<ConfiguredFeature<?, ?>> create(String name) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, Helpers.location(name));
	}
}
