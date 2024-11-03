package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.util.Helpers;

import static org.polaris2023.wild_wind.common.init.ModBlocks.BLOCKS;
import static org.polaris2023.wild_wind.common.init.ModEntities.ENTITIES;
import static org.polaris2023.wild_wind.common.init.ModItems.ITEMS;

public class ModInit {
    static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, WildWindMod.MOD_ID);

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
