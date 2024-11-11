package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.util.Helpers;

import java.util.Collection;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModInitializer {
    static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(Registries.ENTITY_TYPE, WildWindMod.MOD_ID);
    static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(WildWindMod.MOD_ID);
    static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, WildWindMod.MOD_ID);
    static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(WildWindMod.MOD_ID);

    public static Collection<DeferredHolder<CreativeModeTab, ? extends CreativeModeTab>> creativeTabs() {
        return TABS.getEntries();
    }

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
            System.out.println(aClass.getName());
            Class.forName(aClass.getName());
        }
    }

    static <E extends Entity> DeferredHolder<EntityType<?>, EntityType<E>> register(String name, EntityType.EntityFactory<E> factory, MobCategory category) {
        return ENTITIES.register(name, resourceLocation -> EntityType.Builder.of(factory, category).build(name));
    }

    public static Collection<DeferredHolder<EntityType<?>, ? extends EntityType<?>>> entities() {
        return ENTITIES.getEntries();
    }

    static  DeferredBlock<Block> register(String name) {
        return BLOCKS.registerSimpleBlock(name, BlockBehaviour.Properties.of());

    }

    static <T extends Block> DeferredBlock<T> register(String name, Function<BlockBehaviour.Properties, T> function, BlockBehaviour.Properties properties) {
        return BLOCKS.registerBlock(name, function, properties);
    }

    static DeferredBlock<Block> register(String name, BlockBehaviour.Properties properties) {
        return BLOCKS.registerSimpleBlock(name, properties);
    }

    static <T extends Block> DeferredItem<BlockItem> register(String name, DeferredBlock<T> block) {
        return ITEMS.registerSimpleBlockItem(name, block);
    }

    static DeferredItem<Item> simpleItem(String name) {
        return ITEMS.registerSimpleItem(name);
    }

    static <T extends Item> DeferredItem<T> register(String name, Function<Item.Properties, T> item) {
        return ITEMS.registerItem(name, item);
    }

    static <T extends Item> DeferredItem<T> register(String name, Supplier<T> item) {
        return ITEMS.register(name, item);
    }

    public static Collection<DeferredHolder<Item, ? extends Item>> items() {
        return ITEMS.getEntries();
    }

    static DeferredItem<DeferredSpawnEggItem> register(String name,
                                                       Supplier<? extends EntityType<? extends Mob>> type,
                                                       int backgroundColor,
                                                       int highlightColor,
                                                       Consumer<Item.Properties> consumer) {
        return ModInitializer.ITEMS.registerItem(name, properties -> {
            consumer.accept(properties);
            return new DeferredSpawnEggItem(type, backgroundColor, highlightColor, properties);
        });
    }

    static DeferredItem<DeferredSpawnEggItem> register(String name,
                                                       Supplier<? extends EntityType<? extends Mob>> type,
                                                       int backgroundColor,
                                                       int highlightColor) {
        return register(name, type, backgroundColor, highlightColor, properties -> {});
    }
}
