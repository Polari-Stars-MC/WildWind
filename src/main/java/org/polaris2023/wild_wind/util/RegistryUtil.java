package org.polaris2023.wild_wind.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Objects;
import java.util.function.Supplier;

public final class RegistryUtil {

    public static <T, E extends T> DeferredHolder<T, E> register(DeferredRegister<T> register, String name, Supplier<E> supplier) {
        return register.register(name, supplier);

    }

    public static void register(IEventBus eventBus, DeferredRegister<?>... registries) {
        for (DeferredRegister<?> registry : registries) registry.register(eventBus);
    }

    public static <T, E extends T> DeferredHolder<T, E> register(DeferredRegister<T> register, String name, E e) {
        return register.register(name, properties -> e);
    }

    public static <T> ResourceLocation getDefaultRegisterName(T supplier) {
        return switch (supplier) {
            case Item item ->
                    BuiltInRegistries.ITEM.getKey(item);
            case Block block ->
                    BuiltInRegistries.BLOCK.getKey(block);
            case EntityType<?> type ->
                    BuiltInRegistries.ENTITY_TYPE.getKey(type);
            case Fluid fluid ->
                    BuiltInRegistries.FLUID.getKey(fluid);
            case GameEvent event ->
                    BuiltInRegistries.GAME_EVENT.getKey(event);
            case ChunkStatus status ->
                    BuiltInRegistries.CHUNK_STATUS.getKey(status);
            case VillagerType type ->
                    BuiltInRegistries.VILLAGER_TYPE.getKey(type);
            case VillagerProfession profession ->
                    BuiltInRegistries.VILLAGER_PROFESSION.getKey(profession);
            case MemoryModuleType<?> type ->
                    BuiltInRegistries.MEMORY_MODULE_TYPE.getKey(type);
            case SensorType<?> sensorType ->
                BuiltInRegistries.SENSOR_TYPE.getKey(sensorType);
            case StructureType<?> structureType ->
                    Objects.requireNonNull(BuiltInRegistries.STRUCTURE_TYPE.getKey(structureType));
            default -> throw new IllegalStateException("Unexpected value: " + supplier);
        };
    }
}
