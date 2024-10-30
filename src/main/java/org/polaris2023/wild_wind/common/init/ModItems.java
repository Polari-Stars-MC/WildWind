package org.polaris2023.wild_wind.common.init;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wild_wind.WildWindMod;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModItems {

    static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(WildWindMod.MOD_ID);

    public static final DeferredItem<Item> GLOW_MUCUS = register("glow_mucus");
    public static final DeferredItem<Item> GLOW_POWDER = register("glow_powder");

    public static final DeferredItem<Item> LIVING_TUBER = register("living_tuber");



    public static final DeferredItem<DeferredSpawnEggItem> FIREFLY_SPAWN_EGG =
            register("firefly_spawn_egg",
                    ModEntities.FIREFLY,
                            0xAA8F74,
                            0x0A233F);
    public static final DeferredItem<DeferredSpawnEggItem> GLARE_SPAWN_EGG =
            register("glare_spawn_egg",
                    ModEntities.GLARE,
                    0x10160A,
                    0x49601B);

    private static DeferredItem<Item> register(String name) {
        return ITEMS.registerSimpleItem(name);
    }

    private static DeferredItem<DeferredSpawnEggItem> register(String name,
                                                               Supplier<? extends EntityType<? extends Mob>> type,
                                                               int backgroundColor,
                                                               int highlightColor,
                                                               Consumer<Item.Properties> consumer) {
        return ITEMS.registerItem(name, properties -> {
            consumer.accept(properties);
            return new DeferredSpawnEggItem(type, backgroundColor, highlightColor, properties);
        });
    }

    private static DeferredItem<DeferredSpawnEggItem> register(String name,
                                                               Supplier<? extends EntityType<? extends Mob>> type,
                                                               int backgroundColor,
                                                               int highlightColor) {
        return register(name, type, backgroundColor, highlightColor, properties -> {});
    }

    private static DeferredItem<Item> register(String name, Item item) {
        return ITEMS.registerItem(name, properties -> item);
    }

    public static Collection<DeferredHolder<Item, ? extends Item>> entry() {
        return ITEMS.getEntries();
    }
}
