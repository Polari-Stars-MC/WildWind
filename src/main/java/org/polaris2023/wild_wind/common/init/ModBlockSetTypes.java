package org.polaris2023.wild_wind.common.init;

import net.minecraft.world.level.block.state.properties.BlockSetType;

public final class ModBlockSetTypes {
	public static final BlockSetType AZALEA = BlockSetType.register(new BlockSetType("wild_wind_azalea"));
	public static final BlockSetType PALM = BlockSetType.register(new BlockSetType("wild_wind_palm"));
	public static final BlockSetType BAOBAB = BlockSetType.register(new BlockSetType("wild_wind_baobab"));

	private ModBlockSetTypes() {
	}
}
