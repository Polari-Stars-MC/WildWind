package org.polaris2023.wild_wind;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.polaris2023.wild_wind.common.WildWindEventHandler;
import org.polaris2023.wild_wind.common.init.ModEntities;
import org.polaris2023.wild_wind.common.init.ModInit;
import org.polaris2023.wild_wind.common.init.ModItems;
import org.polaris2023.wild_wind.util.Helpers;

import java.lang.reflect.Method;
import java.util.List;

@Mod(WildWindMod.MOD_ID)
public class WildWindMod {

    public static final String MOD_ID = "wild_wind";
    public static final String MOD_NAME = "Wild Wind";
    public static final String MOD_VERSION = ModList.get().getModFileById(MOD_ID).versionString();
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public WildWindMod(IEventBus modEventBus, ModContainer modContainer) {
        WildWindEventHandler.modConstruction(modEventBus);
        List<Class<WildWindConfig>> classes = List.of(WildWindConfig.class);
        for (Class<WildWindConfig> aClass : classes) {
            try {
                Class<?> aClass1 = Class.forName(aClass.getName() + "Impl");
                Method method = aClass1.getMethod("register", ModContainer.class);
                method.setAccessible(true);
                method.invoke(null, modContainer);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
