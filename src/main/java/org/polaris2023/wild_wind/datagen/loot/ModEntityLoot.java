package org.polaris2023.wild_wind.datagen.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.wild_wind.common.init.ModEntities;

import java.util.stream.Stream;

public class ModEntityLoot extends EntityLootSubProvider {
	public ModEntityLoot(HolderLookup.Provider registries) {
		super(FeatureFlags.REGISTRY.allFlags(), registries);
	}

	@Override
	public void generate() {
		this.add(
				ModEntities.FIREFLY.get(),
				LootTable.lootTable()
						.withPool(
								LootPool.lootPool()
										.setRolls(ConstantValue.exactly(1.0F))
										.add(LootItem.lootTableItem(Items.COD).apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot())))
						)
						.withPool(
								LootPool.lootPool()
										.setRolls(ConstantValue.exactly(1.0F))
										.add(LootItem.lootTableItem(Items.BONE_MEAL))
										.when(LootItemRandomChanceCondition.randomChance(0.05F))
						)
		);
	}

	@Override
	protected Stream<EntityType<?>> getKnownEntityTypes() {
		return ModEntities.getModEntities().stream().map(DeferredHolder::get);
	}
}
