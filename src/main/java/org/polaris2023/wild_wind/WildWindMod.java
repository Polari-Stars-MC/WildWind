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
import org.polaris2023.wild_wind.common.init.ModEntities;
import org.polaris2023.wild_wind.common.init.ModItems;

@Mod(WildWindMod.MOD_ID)
public class WildWindMod {
    public static final String MOD_ID = "wild_wind";
    public static final String MOD_NAME = "Wild Wind";
    public static final String MOD_VERSION = ModList.get().getModFileById(MOD_ID).versionString();
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public WildWindMod(IEventBus modEventBus, ModContainer modContainer) {
        ModEntities.ENTITIES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        modContainer.registerConfig(ModConfig.Type.COMMON, WildWindConfig.SPEC);
    }

    @SubscribeEvent
    public void setup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

        });
    }
}
