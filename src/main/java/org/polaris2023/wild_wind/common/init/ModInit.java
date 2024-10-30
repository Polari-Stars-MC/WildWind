package org.polaris2023.wild_wind.common.init;

import net.neoforged.bus.api.IEventBus;

import static org.polaris2023.wild_wind.common.init.ModBlocks.BLOCKS;
import static org.polaris2023.wild_wind.common.init.ModEntities.ENTITIES;
import static org.polaris2023.wild_wind.common.init.ModItems.ITEMS;

public class ModInit {
    public static void init(IEventBus bus) {
        try {
            init(
                    ModEntities.class,
                    ModBlocks.class,
                    ModItems.class
            );
        } catch (ClassNotFoundException ignored) {}
        ENTITIES.register(bus);
        BLOCKS.register(bus);
        ITEMS.register(bus);
    }

    public static void init(Class<?>... clazz) throws ClassNotFoundException {
        for (Class<?> aClass : clazz) {
            Class.forName(aClass.getName());
        }
    }
}
