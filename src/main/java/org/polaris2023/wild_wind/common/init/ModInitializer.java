package org.polaris2023.wild_wind.common.init;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import com.google.common.reflect.TypeToken;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.polaris2023.wild_wind.common.init.items.ModBaseItems;
import org.polaris2023.wild_wind.common.init.items.entity.ModMobBuckets;
import org.polaris2023.wild_wind.common.init.items.entity.ModSpawnEggs;
import org.polaris2023.wild_wind.common.init.items.foods.ModBaseFoods;
import org.polaris2023.wild_wind.util.interfaces.registry.BlockRegistry;

import static org.polaris2023.wild_wind.WildWindMod.MOD_ID;

public interface ModInitializer extends BlockRegistry {
    DeferredRegister.DataComponents COMPONENTS =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MOD_ID);
    DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(Registries.SOUND_EVENT, MOD_ID);
    DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(Registries.ENTITY_TYPE, MOD_ID);
    DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(BuiltInRegistries.FLUID, MOD_ID);

    DeferredRegister<BlockEntityType<?>> TILES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MOD_ID);
    DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, MOD_ID);
    DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, MOD_ID);
    DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(Registries.POTION, MOD_ID);

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
    DeferredRegister<StructureType<?>> STRUCTURE_TYPE =
        DeferredRegister.create(BuiltInRegistries.STRUCTURE_TYPE, MOD_ID);
    DeferredRegister<StructurePieceType> STRUCTURE_PIECE =
        DeferredRegister.create(BuiltInRegistries.STRUCTURE_PIECE, MOD_ID);

    static void init(IEventBus bus) {
        init(bus, ModComponents.class, COMPONENTS);
        init(bus, ModSounds.class, SOUNDS);
        init(bus, ModEntities.class, ENTITIES);
        init(bus, ModFluids.class, FLUIDS);
        init(bus, ModBlocks.class, ModBlocks.REGISTER, TILES);
        init(bus, ModEffects.class, EFFECTS);
        init(bus, ModPotions.class, POTIONS);
        init(bus, new Class[]{ModItems.class, ModBaseItems.class, ModBaseFoods.class, ModSpawnEggs.class, ModMobBuckets.class, ModMobBuckets.class}, ModItems.REGISTER);
        init(bus, ModParticleTypes.class, ModParticleTypes.REGISTER);
        init(bus, ModRecipes.class, RECIPES);
        init(bus, ModRecipeSerializes.class, RECIPES_SERIALIZERS);
        init(bus, ModCreativeTabs.class, TABS);
        init(bus, ModVillagers.class, POIS, VILLAGERS, PROFESSIONS);
        init(bus, ModStructuresTypes.class, STRUCTURE_TYPE, STRUCTURE_PIECE);
        init(bus, ModMenus.class, MENU_TYPES);
        init(bus, ModInstruments.class, INSTRUMENTS);
        init(bus, ModGlobalLootModifiers.class, GLMS);
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
    static <E extends Entity> DeferredHolder<EntityType<?>, EntityType<E>> register(String name, EntityType.EntityFactory<E> factory, Consumer<EntityType.Builder<E>> consumer, MobCategory category) {
        return ENTITIES.register(name, resourceLocation -> {
            EntityType.Builder<E> builder = EntityType.Builder.of(factory, category);
            consumer.accept(builder);
            return builder.build(name);
        });
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
                        ? ModBlocks.REGISTER.getEntries()
                        : token.isSubtypeOf(MobEffect.class)
                        ? EFFECTS.getEntries()
                        : token.isSubtypeOf(Potion.class)
                        ? POTIONS.getEntries()
                        : token.isSubtypeOf(Item.class)
                        ? ModItems.REGISTER.getEntries()
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


}
