package org.polaris2023.wild_wind.common.init;

import net.minecraft.world.level.block.state.properties.WoodType;

public final class ModWoodTypes {
	public static final WoodType AZALEA = WoodType.register(new WoodType("wild_wind_azalea", ModBlockSetTypes.AZALEA));

	private ModWoodTypes() {
	}
}
