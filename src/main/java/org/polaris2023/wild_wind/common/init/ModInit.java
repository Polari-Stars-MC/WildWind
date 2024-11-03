package org.polaris2023.wild_wind.common.init;

import net.neoforged.bus.api.IEventBus;
import org.polaris2023.wild_wind.util.Helpers;

import static org.polaris2023.wild_wind.common.init.ModBlocks.BLOCKS;
import static org.polaris2023.wild_wind.common.init.ModCreativeTabs.TABS;
import static org.polaris2023.wild_wind.common.init.ModEntities.ENTITIES;
import static org.polaris2023.wild_wind.common.init.ModItems.ITEMS;

public class ModInit {
    public static void init(IEventBus bus) {
        try {
            init(
                    ModEntities.class,
                    ModBlocks.class,
                    ModItems.class,
                    ModCreativeTabs.class
            );
        } catch (ClassNotFoundException ignored) {}
        Helpers.register(bus,
                ENTITIES, BLOCKS,
                ITEMS, TABS);
    }

    public static void init(Class<?>... clazz) throws ClassNotFoundException {
        for (Class<?> aClass : clazz) {
            Class.forName(aClass.getName());
        }
    }
}
