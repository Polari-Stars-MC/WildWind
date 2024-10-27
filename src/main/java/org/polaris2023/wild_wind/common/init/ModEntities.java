package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.entity.Firefly;

public class ModEntities {
	public static final DeferredRegister<EntityType<?>> ENTITIES =
			DeferredRegister.create(Registries.ENTITY_TYPE, WildWindMod.MOD_ID);

	public static final DeferredHolder<EntityType<?>, EntityType<Firefly>> FIREFLY =
			register("firefly", EntityType.Builder.of(Firefly::new, MobCategory.AMBIENT));

	@SuppressWarnings("SameParameterValue")
	private static <E extends Entity> DeferredHolder<EntityType<?>, EntityType<E>> register(String name, EntityType.Builder<E> builder) {
		return ENTITIES.register(name, resourceLocation -> builder.build(name));
	}
}
