package org.polaris2023.wild_wind.common.init;

import net.minecraft.world.level.block.state.properties.WoodType;

public final class ModWoodTypes {
	public static final WoodType AZALEA = WoodType.register(new WoodType("wild_wind_azalea", ModBlockSetTypes.AZALEA));
	public static final WoodType PALM = WoodType.register(new WoodType("wild_wind_palm", ModBlockSetTypes.PALM));
	public static final WoodType BAOBAB = WoodType.register(new WoodType("wild_wind_baobab", ModBlockSetTypes.BAOBAB));

	private ModWoodTypes() {
	}
}
