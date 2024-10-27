package org.polaris2023.wild_wind.common.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import org.polaris2023.wild_wind.util.Helpers;

public class ModBlockTags {
	public static final TagKey<Block> FIREFLY_ROOST_BLOCK = register("firefly_roost_block");

	@SuppressWarnings("SameParameterValue")
	private static TagKey<Block> register(String name) {
		return BlockTags.create(Helpers.location(name));
	}
}
