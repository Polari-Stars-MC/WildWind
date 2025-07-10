package org.polaris2023.wild_wind.datagen.loot;

import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.polaris2023.wild_wind.common.init.ModBlocks;

/**
 * @author asuka
 * @since 2025/07/07
 */
public class VanillaLootTableProvider extends BlockLootSubProvider {

    private static final Set<Item> EXPLOSION_RESISTANT = ImmutableSet.of();

    public VanillaLootTableProvider(HolderLookup.Provider registries) {
        super(EXPLOSION_RESISTANT, FeatureFlags.DEFAULT_FLAGS, registries);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return List.of(Blocks.BLACKSTONE, Blocks.GILDED_BLACKSTONE);
    }

    @Override
    protected void generate() {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        this.add(Blocks.BLACKSTONE, (b) -> this.createOreDrop(b, ModBlocks.COBBLED_BLACKSTONE.asItem()));
        this.add(Blocks.GILDED_BLACKSTONE, LootTable.lootTable()
            .withPool(
                LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(Blocks.GILDED_BLACKSTONE).when(hasSilkTouch()))
            ).withPool(
                LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(ModBlocks.COBBLED_BLACKSTONE.asItem()).when(hasSilkTouch().invert()))
            ).withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(Items.GOLD_NUGGET)
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                    .when(
                        BonusLevelTableCondition.bonusLevelFlatChance(
                            registrylookup.getOrThrow(Enchantments.FORTUNE), 0.1F, 0.14285715F, 0.25F
                            , 1.0F)
                    )
                    .when(hasSilkTouch().invert())
                )));
    }
}
