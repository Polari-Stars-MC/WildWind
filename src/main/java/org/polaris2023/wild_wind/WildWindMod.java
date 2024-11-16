package org.polaris2023.wild_wind;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.polaris2023.wild_wind.common.WildWindEventHandler;
import org.polaris2023.wild_wind.util.interfaces.IConfig;

import java.util.ServiceLoader;

@Mod(WildWindMod.MOD_ID)
public class WildWindMod {

    public static final String MOD_ID = "wild_wind";
    public static final String MOD_NAME = "Wild Wind";
    public static final String MOD_VERSION = ModList.get().getModFileById(MOD_ID).versionString();
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public WildWindMod(IEventBus modEventBus, ModContainer modContainer) {
        WildWindEventHandler.modConstruction(modEventBus);
        for (var iConfig : ServiceLoader.load(IConfig.class))
            iConfig.register(modContainer);
    }
}
