package org.polaris2023.wild_wind.common.init;

import com.google.common.reflect.TypeToken;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.*;
import org.polaris2023.wild_wind.common.init.items.ModBaseItems;
import org.polaris2023.wild_wind.common.init.items.entity.ModMobBuckets;
import org.polaris2023.wild_wind.common.init.items.entity.ModSpawnEggs;
import org.polaris2023.wild_wind.common.init.items.foods.ModBaseFoods;
import org.polaris2023.wild_wind.datagen.ModGLM;
import org.polaris2023.wild_wind.util.interfaces.registry.BlockRegistry;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.polaris2023.wild_wind.WildWindMod.MOD_ID;

public interface ModInitializer {
    DeferredRegister.DataComponents COMPONENTS =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MOD_ID);
    DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(Registries.SOUND_EVENT, MOD_ID);
    DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(Registries.ENTITY_TYPE, MOD_ID);
    DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(BuiltInRegistries.FLUID, MOD_ID);
    DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(MOD_ID);
    DeferredRegister<BlockEntityType<?>> TILES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MOD_ID);
    DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, MOD_ID);
    DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, MOD_ID);
    DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(Registries.POTION, MOD_ID);
    DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(MOD_ID);
    DeferredRegister<RecipeType<?>> RECIPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, MOD_ID);
    DeferredRegister<RecipeSerializer<?>> RECIPES_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, MOD_ID);
    DeferredRegister<PoiType> POIS =
            DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, MOD_ID);
    DeferredRegister<VillagerType> VILLAGERS =
            DeferredRegister.create(BuiltInRegistries.VILLAGER_TYPE, MOD_ID);
    DeferredRegister<VillagerProfession> PROFESSIONS =
            DeferredRegister.create(BuiltInRegistries.VILLAGER_PROFESSION, MOD_ID);
    DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(BuiltInRegistries.MENU, MOD_ID);
    DeferredRegister<Instrument> INSTRUMENTS =
            DeferredRegister.create(BuiltInRegistries.INSTRUMENT, MOD_ID);
    DeferredRegister<MapCodec<? extends IGlobalLootModifier>> GLMS =
            DeferredRegister.create(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MOD_ID);

    static void init(IEventBus bus) {
        init(bus, ModComponents.class, COMPONENTS);
        init(bus, ModSounds.class, SOUNDS);
        init(bus, ModEntities.class, ENTITIES);
        init(bus, ModFluids.class, FLUIDS);
        init(bus, ModBlocks.class, BLOCKS, TILES);
        init(bus, ModEffects.class, EFFECTS);
        init(bus, ModPotions.class, POTIONS);
        init(bus, new Class[]{ModItems.class, ModBaseItems.class, ModBaseFoods.class, ModSpawnEggs.class, ModMobBuckets.class, ModMobBuckets.class}, ITEMS);
        init(bus, ModRecipes.class, RECIPES);
        init(bus, ModRecipeSerializes.class, RECIPES_SERIALIZERS);
        init(bus, ModCreativeTabs.class, TABS);
        init(bus, ModVillagers.class, POIS, VILLAGERS, PROFESSIONS);
        init(bus, ModMenus.class, MENU_TYPES);
        init(bus, ModInstruments.class, INSTRUMENTS);
        init(bus, ModGLM.class, GLMS);
    }

    static void init(IEventBus bus, Class<?> clazz, DeferredRegister<?>... registers) {
        try {
            Class.forName(clazz.getName());
        } catch (ClassNotFoundException ignored) {}
        for (DeferredRegister<?> register : registers) {
            register.register(bus);
        }
    }

    static void init(IEventBus bus, Class<?>[] classes, DeferredRegister<?>... registers) {
        try {
            for (Class<?> clazz : classes) {
                Class.forName(clazz.getName());
            }
        } catch (ClassNotFoundException ignored) {}
        for (DeferredRegister<?> register : registers) {
            register.register(bus);
        }
    }

    static <E extends Entity> DeferredHolder<EntityType<?>, EntityType<E>> register(String name, EntityType.EntityFactory<E> factory, MobCategory category) {
        return ENTITIES.register(name, resourceLocation -> EntityType.Builder.of(factory, category).build(name));
    }

    static Collection<DeferredHolder<Item, ? extends Item>> items() {
        return ITEMS.getEntries();
    }

    static Collection<DeferredHolder<Block, ? extends Block>> blocks() {
        return BLOCKS.getEntries();
    }

    static Collection<DeferredHolder<EntityType<?>, ? extends EntityType<?>>> entities() {
        return ENTITIES.getEntries();
    }

    static Collection<DeferredHolder<SoundEvent, ? extends SoundEvent>> sounds() {
        return SOUNDS.getEntries();
    }

    @SuppressWarnings("unchecked")
    static <T> Collection<DeferredHolder<T, ? extends T>> entry(TypeToken<T> token) {
        return (Collection<DeferredHolder<T, ? extends T>>)
                (token.isSubtypeOf(SoundEvent.class) ?
                        SOUNDS.getEntries()
                        : token.isSubtypeOf(EntityType.class)
                        ? ENTITIES.getEntries()
                        : token.isSubtypeOf(Fluid.class)
                        ? FLUIDS.getEntries()
                        : token.isSubtypeOf(Block.class)
                        ? BLOCKS.getEntries()
                        : token.isSubtypeOf(MobEffect.class)
                        ? EFFECTS.getEntries()
                        : token.isSubtypeOf(Potion.class)
                        ? POTIONS.getEntries()
                        : token.isSubtypeOf(Item.class)
                        ? ITEMS.getEntries()
                        : token.isSubtypeOf(CreativeModeTab.class)
                        ? TABS.getEntries()
                        : token.isSubtypeOf(PoiType.class)
                        ? POIS.getEntries()
                        : token.isSubtypeOf(VillagerType.class)
                        ? VILLAGERS.getEntries()
                        : token.isSubtypeOf(VillagerProfession.class)
                        ? PROFESSIONS.getEntries()
                        : List.of());
    }

    static DeferredBlock<Block> register(String name) {
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

    static <T extends Block> DeferredItem<BlockItem> register(String name, DeferredBlock<T> block, Supplier<FoodProperties> supplier) {
        return ITEMS.registerItem(name, properties -> new BlockItem(block.get(), properties.food(supplier.get())));
    }

    static <SST extends StandingSignBlock, WST extends WallSignBlock> DeferredItem<SignItem> registerSign(String name,
                                                                                                          DeferredBlock<SST> standing,
                                                                                                          DeferredBlock<WST> wall) {
        return ITEMS.registerItem(name, properties -> new SignItem(properties.stacksTo(16), standing.get(), wall.get()));
    }

    static <SST extends CeilingHangingSignBlock, WST extends WallHangingSignBlock> DeferredItem<HangingSignItem> registerHangingSign(String name,
                                                                                                                                     DeferredBlock<SST> standing,
                                                                                                                                     DeferredBlock<WST> wall) {
        return ITEMS.registerItem(name, properties -> new HangingSignItem(standing.get(), wall.get(), properties.stacksTo(16)));
    }

    static DeferredItem<Item> simpleItem(String name) {

        return ITEMS.registerSimpleItem(name);
    }

    static DeferredItem<Item> item(String name, Function<Item.Properties, Item> toItemFunction) {
        return ITEMS.registerItem(name, toItemFunction);
    }

    static DeferredItem<Item> simpleItem(String name, Consumer<Item.Properties> consumer) {
        return item(name, properties -> {
            consumer.accept(properties);
            return new Item(properties);
        });
    }

    static DeferredItem<Item> simpleItem(String name, Supplier<FoodProperties> food) {
        return simpleItem(name, properties -> properties.food(food.get()));
    }

    static DeferredItem<Item> simpleItem(String name, Consumer<Item.Properties> consumer, Supplier<FoodProperties> food) {
        return simpleItem(name, properties -> consumer.accept(properties.food(food.get())));
    }

    static <T extends Item> DeferredItem<T> register(String name, Function<Item.Properties, T> item) {
        return ITEMS.registerItem(name, item);
    }

    static <T extends Item> DeferredItem<T> register(String name, Supplier<T> item) {
        return ITEMS.register(name, item);
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
