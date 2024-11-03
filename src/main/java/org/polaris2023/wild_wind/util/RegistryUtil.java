package org.polaris2023.wild_wind.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.StructureType;

public final class RegistryUtil {
	public static ResourceLocation getRegistryName(Block block) {
		return BuiltInRegistries.BLOCK.getKey(block);
	}
	public static ResourceLocation getRegistryName(Item item) {
		return BuiltInRegistries.ITEM.getKey(item);
	}
	public static ResourceLocation getRegistryName(EntityType<?> entityType) {
		return BuiltInRegistries.ENTITY_TYPE.getKey(entityType);
	}
	public static ResourceLocation getRegistryName(VillagerProfession profession) {
		return BuiltInRegistries.VILLAGER_PROFESSION.getKey(profession);
	}
	public static ResourceLocation getRegistryName(StructureType<?> structureType) {
		return BuiltInRegistries.STRUCTURE_TYPE.getKey(structureType);
	}

	private RegistryUtil() {
	}
}
