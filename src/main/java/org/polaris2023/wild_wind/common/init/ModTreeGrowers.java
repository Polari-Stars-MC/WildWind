package org.polaris2023.wild_wind.common.init;

import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
	public static final TreeGrower PALM = new TreeGrower(
			"palm",
			0.1F,
			Optional.empty(),
			Optional.empty(),
			Optional.of(TreeFeatures.OAK),
			Optional.of(TreeFeatures.FANCY_OAK),
			Optional.of(TreeFeatures.OAK_BEES_005),
			Optional.of(TreeFeatures.FANCY_OAK_BEES_005)
	);
	public static final TreeGrower BAOBAB = new TreeGrower(
			"baobab",
			0.1F,
			Optional.empty(),
			Optional.empty(),
			Optional.of(TreeFeatures.OAK),
			Optional.of(TreeFeatures.FANCY_OAK),
			Optional.of(TreeFeatures.OAK_BEES_005),
			Optional.of(TreeFeatures.FANCY_OAK_BEES_005)
	);
}
