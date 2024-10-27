package org.polaris2023.wild_wind.common.init;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wild_wind.WildWindMod;

import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(WildWindMod.MOD_ID);

    public static final DeferredItem<Item> GLOW_GOOP = register("glow_goop");
    public static final DeferredItem<DeferredSpawnEggItem> FIREFLY_SPAWN_EGG =
            register("firefly_spawn_egg",
                    ModEntities.FIREFLY,
                            0x000000,
                            0x000000, new Item.Properties());

    private static DeferredItem<Item> register(String name) {
        return ITEMS.registerSimpleItem(name);
    }

    private static DeferredItem<DeferredSpawnEggItem> register(String name, Supplier<? extends EntityType<? extends Mob>> type, int backgroundColor, int highlightColor, Item.Properties props) {
        return ITEMS.registerItem(name, properties ->
                new DeferredSpawnEggItem(type,
                        backgroundColor,
                        highlightColor,
                        props));
    }

    private static DeferredItem<Item> register(String name, Item item) {
        return ITEMS.registerItem(name, properties -> item);
    }
}
