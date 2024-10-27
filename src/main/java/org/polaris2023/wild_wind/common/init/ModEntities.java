package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.entity.Firefly;

import java.util.Collection;

public class ModEntities {

    private static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(Registries.ENTITY_TYPE, WildWindMod.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<Firefly>> FIREFLY =
            register("firefly", EntityType.Builder.of(Firefly::new, MobCategory.AMBIENT));

    private static <E extends Entity> DeferredHolder<EntityType<?>, EntityType<E>> register(String name, EntityType.Builder<E> builder) {
        return ENTITIES.register(name, resourceLocation -> builder.build(name));
    }

    public static Collection<DeferredHolder<EntityType<?>, ? extends EntityType<?>>> entry() {
        return ENTITIES.getEntries();
    }

    public static void register(IEventBus bus) {
        ENTITIES.register(bus);
    }



}
