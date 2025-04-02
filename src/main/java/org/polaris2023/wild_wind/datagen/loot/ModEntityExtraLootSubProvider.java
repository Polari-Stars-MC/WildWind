package org.polaris2023.wild_wind.datagen.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.polaris2023.wild_wind.common.init.ModItems;
import org.polaris2023.wild_wind.util.Helpers;

import java.util.function.BiConsumer;

public class ModEntityExtraLootSubProvider implements LootTableSubProvider {
	protected final HolderLookup.Provider registries;

	public static final ResourceKey<LootTable> DROP_FUR = ResourceKey.create(Registries.LOOT_TABLE, Helpers.location("entities/misc/drop_fur"));

	public ModEntityExtraLootSubProvider(HolderLookup.Provider registries) {
		this.registries = registries;
	}

	@Override
	public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
		output.accept(DROP_FUR, LootTable.lootTable().withPool(
				LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1.0F))
						.add(LootItem.lootTableItem(ModItems.FUR))
						.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
		));
	}
}
