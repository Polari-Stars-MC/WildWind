package org.polaris2023.wild_wind;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.polaris2023.wild_wind.common.WildWindEventHandler;
import org.polaris2023.wild_wind.common.compat.ModCompatHandler;

@Mod(WildWindMod.MOD_ID)
public class WildWindMod {
	public static final String MOD_ID = "wild_wind";
	public static final String MOD_NAME = "Wild Wind";
	public static final String MOD_VERSION = ModList.get().getModFileById(MOD_ID).versionString();
	public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

	public WildWindMod(IEventBus modEventBus, ModContainer modContainer) {
		WildWindEventHandler.modConstruction(modEventBus);
		modContainer.registerConfig(ModConfig.Type.COMMON, WildWindCommonConfig.SPEC);
	}

	@SubscribeEvent
	public void setup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			ModCompatHandler.solveCompat();

			//Optional Dependencies
			ModCompatHandler.solveTerraBlender();

			//
		});
	}
}
