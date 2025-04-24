package org.polaris2023.wild_wind.datagen.loot;

import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.polaris2023.wild_wind.common.init.ModItems;
import org.polaris2023.wild_wind.common.init.items.ModBaseItems;
import org.polaris2023.wild_wind.common.init.items.foods.ModBaseFoods;
import org.polaris2023.wild_wind.util.Helpers;

import java.util.List;
import java.util.function.BiConsumer;

public class ModEntityExtraLootSubProvider implements LootTableSubProvider {
	protected final HolderLookup.Provider registries;

	public static final ResourceKey<LootTable> DROP_FUR = ResourceKey.create(Registries.LOOT_TABLE, Helpers.location("entities/misc/drop_fur"));
	public static final ResourceKey<LootTable> DROP_BAT_WING = ResourceKey.create(Registries.LOOT_TABLE, Helpers.location("entities/misc/drop_bat_wing"));
	public static final ResourceKey<LootTable> DROP_CALAMARI = ResourceKey.create(Registries.LOOT_TABLE, Helpers.location("entities/misc/drop_calamari"));
	public static final ResourceKey<LootTable> DROP_GLOWING_CALAMARI = ResourceKey.create(Registries.LOOT_TABLE, Helpers.location("entities/misc/drop_glowing_calamari"));
	public static final ResourceKey<LootTable> DROP_CHARRED_BONE = ResourceKey.create(Registries.LOOT_TABLE, Helpers.location("entities/misc/drop_charred_bone"));

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
		output.accept(DROP_BAT_WING, LootTable.lootTable().withPool(
				LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1.0F))
						.add(LootItem.lootTableItem(ModBaseFoods.BAT_WING))
						.apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
						.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
		));
		output.accept(DROP_CALAMARI, LootTable.lootTable().withPool(
				LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1.0F))
						.add(LootItem.lootTableItem(ModBaseFoods.CALAMARI))
						.apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
						.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 4.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
		));
		output.accept(DROP_GLOWING_CALAMARI, LootTable.lootTable().withPool(
				LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1.0F))
						.add(LootItem.lootTableItem(ModBaseFoods.GLOWING_CALAMARI))
						.apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
						.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 4.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
		));
		output.accept(DROP_CHARRED_BONE, LootTable.lootTable().withPool(
				LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1.0F))
						.add(LootItem.lootTableItem(ModBaseItems.CHARRED_BONE))
						.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
		));
	}

	protected final AnyOfCondition.Builder shouldSmeltLoot() {
		HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
		return AnyOfCondition.anyOf(
				LootItemEntityPropertyCondition.hasProperties(
						LootContext.EntityTarget.THIS,
						EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true))
				),
				LootItemEntityPropertyCondition.hasProperties(
						LootContext.EntityTarget.DIRECT_ATTACKER,
						EntityPredicate.Builder.entity().equipment(
								EntityEquipmentPredicate.Builder.equipment().mainhand(
										ItemPredicate.Builder.item().withSubPredicate(
												ItemSubPredicates.ENCHANTMENTS, ItemEnchantmentsPredicate.enchantments(
														List.of(new EnchantmentPredicate(registrylookup.getOrThrow(EnchantmentTags.SMELTS_LOOT), MinMaxBounds.Ints.ANY))
												)
										)
								)
						)
				)
		);
	}
}
