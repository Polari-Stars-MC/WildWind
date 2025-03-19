package org.polaris2023.wild_wind.datagen.loot;

import com.google.common.collect.ImmutableSet;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.polaris2023.wild_wind.common.block.AshLayerBlock;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.init.ModInitializer;
import org.polaris2023.wild_wind.common.init.ModItems;
import org.polaris2023.wild_wind.common.init.items.ModBaseItems;
import org.polaris2023.wild_wind.datagen.ModBlockFamilies;

import java.util.Set;

public class ModBlockLootSubProvider extends BlockLootSubProvider {
    private static final Set<Item> EXPLOSION_RESISTANT = ImmutableSet.of();

    public ModBlockLootSubProvider(HolderLookup.Provider registries) {
        super(EXPLOSION_RESISTANT, FeatureFlags.DEFAULT_FLAGS, registries);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModInitializer.blocks().stream().filter(holder -> !holder.get().asItem().equals(Items.AIR)).map(holder -> (Block)holder.get()).toList();
    }

    @Override
    public void generate() {
        this.dropSelf(ModBlocks.GLOW_MUCUS.get());
        this.dropSelf(ModBlocks.GLAREFLOWER.get());
        this.dropSelf(ModBlocks.GLAREFLOWER_SEEDS.get());
        this.dropSelf(ModBlocks.REEDS.get());
        this.dropSelf(ModBlocks.CATTAILS.get());
        this.dropSelf(ModBlocks.COOKING_POT.get());
        this.dropWhenSilkTouch(ModBlocks.BRITTLE_ICE.get());
        this.dropWhenSilkTouch(ModBlocks.ASH_BLOCK.get());
        this.dropWhenSilkTouch(ModBlocks.QUICKSAND.get());
        this.dropWhenSilkTouch(ModBlocks.RED_QUICKSAND.get());
        this.dropWhenSilkTouch(ModBlocks.SILT.get());
        this.add(ModBlocks.ASH_BLOCK.get(), this.createFortunateDrops(ModBlocks.ASH_BLOCK.get(), ModItems.ASH_DUST.get(), 2.0F, 4.0F));
        this.add(ModBlocks.ASH.get(), (block) -> this.createLayerDrops(block, ModBlocks.ASH_BLOCK.asItem(), ModBlocks.ASH.asItem(), ModItems.ASH_DUST.get()));
        this.dropSelf(ModBlocks.WOOL.get());
        this.dropSelf(ModBlocks.CARPET.get());
        this.dropSelf(ModBlocks.CONCRETE.get());
        this.dropSelf(ModBlocks.CONCRETE_POWDER.get());
        this.dropSelf(ModBlocks.GLAZED_TERRACOTTA.get());
        this.dropSelf(ModBlocks.SALT_BLOCK.get());
        this.add(ModBlocks.SALT_ORE.get(), this.createFortunateDrops(ModBlocks.SALT_ORE.get(), ModBaseItems.SALT.get(), 2.0F, 5.0F));
        this.add(ModBlocks.DEEPSLATE_SALT_ORE.get(), this.createFortunateDrops(ModBlocks.DEEPSLATE_SALT_ORE.get(), ModBaseItems.SALT.get(), 2.0F, 5.0F));
        this.dropSelf(ModBlocks.AZALEA_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_AZALEA_LOG.get());
        this.dropSelf(ModBlocks.AZALEA_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_AZALEA_WOOD.get());
        this.dropSelf(ModBlocks.PALM_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_PALM_LOG.get());
        this.dropSelf(ModBlocks.PALM_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_PALM_WOOD.get());
        this.dropSelf(ModBlocks.BAOBAB_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_BAOBAB_LOG.get());
        this.dropSelf(ModBlocks.BAOBAB_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_BAOBAB_WOOD.get());
        this.dropWhenSilkTouch(ModBlocks.SCULK_JAW.get());
        this.dropSelf(ModBlocks.DUCKWEED.get());
        this.dropWhenSilkTouch(ModBlocks.GLISTERING_MELON.get());
        this.add(ModBlocks.GLISTERING_MELON.get(), this.createFortunateDrops(ModBlocks.GLISTERING_MELON.get(), Items.GLISTERING_MELON_SLICE, 3.0F, 7.0F, 9));
        this.dropSelf(ModBlocks.STONE_WALL.get());
        this.dropSelf(ModBlocks.POLISHED_STONE.get());
        this.dropSelf(ModBlocks.POLISHED_STONE_WALL.get());
        this.add(ModBlocks.POLISHED_STONE_SLAB.get(), this.createSlabItemTable(ModBlocks.POLISHED_STONE_SLAB.get()));
        this.dropSelf(ModBlocks.POLISHED_STONE_STAIRS.get());
        ModBlockFamilies.AZALEA_PLANKS.generateBlockLoot(this::dropSelf, slab -> this.add(slab, this.createSlabItemTable(slab)));
        ModBlockFamilies.PALM_PLANKS.generateBlockLoot(this::dropSelf, slab -> this.add(slab, this.createSlabItemTable(slab)));
        ModBlockFamilies.BAOBAB_PLANKS.generateBlockLoot(this::dropSelf, slab -> this.add(slab, this.createSlabItemTable(slab)));
        this.add(ModBlocks.AZALEA_DOOR.get(), this.createDoorTable(ModBlocks.AZALEA_DOOR.get()));
        this.add(ModBlocks.PALM_DOOR.get(), this.createDoorTable(ModBlocks.PALM_DOOR.get()));
        this.add(ModBlocks.BAOBAB_DOOR.get(), this.createDoorTable(ModBlocks.BAOBAB_DOOR.get()));
        this.dropSelf(ModBlocks.PALM_CROWN.get());
        this.add(ModBlocks.PALM_LEAVES.get(), this.createLeavesDrops(ModBlocks.PALM_LEAVES.get(), ModBlocks.PALM_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(ModBlocks.BAOBAB_LEAVES.get(), this.createLeavesDrops(ModBlocks.BAOBAB_LEAVES.get(), ModBlocks.BAOBAB_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(ModBlocks.PALM_SAPLING.get());
        this.dropSelf(ModBlocks.BAOBAB_SAPLING.get());
        this.dropSelf(ModBlocks.ANDESITE_BRICKS.get());
        this.dropSelf(ModBlocks.CRACKED_ANDESITE_BRICKS.get());
        this.dropSelf(ModBlocks.ANDESITE_BRICK_STAIRS.get());
        this.add(ModBlocks.ANDESITE_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.ANDESITE_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.ANDESITE_BRICK_WALL.get());
        this.dropSelf(ModBlocks.DIORITE_BRICKS.get());
        this.dropSelf(ModBlocks.CRACKED_DIORITE_BRICKS.get());
        this.dropSelf(ModBlocks.DIORITE_BRICK_STAIRS.get());
        this.add(ModBlocks.DIORITE_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.DIORITE_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.DIORITE_BRICK_WALL.get());
        this.dropSelf(ModBlocks.GRANITE_BRICKS.get());
        this.dropSelf(ModBlocks.CRACKED_GRANITE_BRICKS.get());
        this.dropSelf(ModBlocks.GRANITE_BRICK_STAIRS.get());
        this.add(ModBlocks.GRANITE_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.GRANITE_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.GRANITE_BRICK_WALL.get());
    }

    protected LootTable.Builder createFortunateDrops(Block block, Item item, float miniDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                block,
                this.applyExplosionDecay(
                        block,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(miniDrops, maxDrops)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

    protected LootTable.Builder createFortunateDrops(Block block, Item item, float miniDrops, float maxDrops, int limitCount) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                block,
                this.applyExplosionDecay(
                        block,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(miniDrops, maxDrops)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE), 1))
                                .apply(LimitCount.limitCount(IntRange.upperBound(limitCount)))
                )
        );
    }

    protected LootTable.Builder createLayerDrops(Block block, Item blockItem, Item itemWithSilkTouch, Item item) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(LootItemEntityPropertyCondition.entityPresent(LootContext.EntityTarget.THIS))
                        .add(AlternativesEntry.alternatives(
                                AlternativesEntry.alternatives(
                                        AshLayerBlock.LAYERS.getPossibleValues(), (i) ->
                                                i != 8 ? LootItem.lootTableItem(item)
                                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AshLayerBlock.LAYERS, i)))
                                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(i))) :
                                                        LootItem.lootTableItem(item)
                                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AshLayerBlock.LAYERS, 8)))
                                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(0)))
                                ).when(this.doesNotHaveSilkTouch()),
                                AlternativesEntry.alternatives(
                                        AshLayerBlock.LAYERS.getPossibleValues(), (i) ->
                                                i != 8 ? LootItem.lootTableItem(itemWithSilkTouch)
                                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AshLayerBlock.LAYERS, i)))
                                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(i))) :
                                                        LootItem.lootTableItem(blockItem)
                                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AshLayerBlock.LAYERS, 8)))
                                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                )
                        ))
                );
    }

    protected final LootPool.Builder layerPoolWithoutSilkTouch(Item item, int layer, float drops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(item)
                        .when(this.doesNotHaveSilkTouch())
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.ASH.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AshLayerBlock.LAYERS, layer)))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(drops)))
                );
    }

    protected final LootPool.Builder layerPoolWithSilkTouch(Item item, int layer, float drops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(item)
                        .when(this.hasSilkTouch())
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.ASH.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AshLayerBlock.LAYERS, layer)))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(drops)))
                );
    }
}
