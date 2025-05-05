package org.polaris2023.wild_wind.datagen.loot;

import com.google.common.collect.ImmutableSet;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.polaris2023.wild_wind.common.block.AshLayerBlock;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.init.ModItems;
import org.polaris2023.wild_wind.common.init.items.ModBaseItems;
import org.polaris2023.wild_wind.util.data.ModBlockFamilies;
import org.polaris2023.wild_wind.util.interfaces.registry.BlockRegistry;

import java.util.Set;
import java.util.function.Function;

public class ModBlockLootSubProvider extends BlockLootSubProvider {
    private static final Set<Item> EXPLOSION_RESISTANT = ImmutableSet.of();

    public ModBlockLootSubProvider(HolderLookup.Provider registries) {
        super(EXPLOSION_RESISTANT, FeatureFlags.DEFAULT_FLAGS, registries);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BlockRegistry.entry().stream().filter(holder -> !holder.get().asItem().equals(Items.AIR)).map(holder -> (Block)holder.get()).toList();
    }

    @Override
    public void generate() {
        Function<Block, LootTable.Builder> tallFlowerFactory = block -> this.createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER);

        this.add(ModBlocks.BED.get(), this::createBedDrops);
        this.dropSelf(ModBlocks.GLOW_MUCUS.get());
        this.dropSelf(ModBlocks.GLAREFLOWER.get());
        this.dropSelf(ModBlocks.GLAREFLOWER_SEEDS.get());
        this.add(ModBlocks.REEDS.get(), tallFlowerFactory);
        this.add(ModBlocks.CATTAILS.get(), tallFlowerFactory);
        this.dropSelf(ModBlocks.COOKING_POT.get());
        this.dropWhenSilkTouch(ModBlocks.BRITTLE_ICE.get());
        this.dropWhenSilkTouch(ModBlocks.PYROCLAST.get());
        this.dropWhenSilkTouch(ModBlocks.ASH_BLOCK.get());
        this.dropWhenSilkTouch(ModBlocks.QUICKSAND.get());
        this.dropWhenSilkTouch(ModBlocks.RED_QUICKSAND.get());
        this.dropWhenSilkTouch(ModBlocks.SILT.get());
        this.add(ModBlocks.ASH_BLOCK.get(), block -> this.createFortunateDrops(block, ModItems.ASH_DUST.get(), 2.0F, 4.0F));
        this.add(ModBlocks.ASH.get(), block -> this.createLayerDrops(block, ModBlocks.ASH_BLOCK.asItem(), ModBlocks.ASH.asItem(), ModItems.ASH_DUST.get()));

        this.dropSelf(ModBlocks.WOOL.get());
        this.dropSelf(ModBlocks.CARPET.get());
        this.dropSelf(ModBlocks.CONCRETE.get());
        this.dropSelf(ModBlocks.CONCRETE_POWDER.get());
        this.dropSelf(ModBlocks.GLAZED_TERRACOTTA.get());
        this.add(ModBlocks.BANNER.get(), this::createBannerDrops);
        this.add(ModBlocks.WALL_BANNER.get(), this::createBannerDrops);

        this.dropSelf(ModBlocks.SALT_BLOCK.get());
//        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
//        this.add(Blocks.PUMPKIN, pumpkin ->
//            createSilkTouchDispatchTable(
//                    pumpkin,
//                    applyExplosionDecay(
//                            pumpkin,
//                            LootItem.lootTableItem(ModBaseFoods.PUMPKIN_SLICE.get())
//                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 7.0F)))
//                                    .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
//                                    .apply(LimitCount.limitCount(IntRange.upperBound(9)))
//                            )
//            ));

        this.add(ModBlocks.SALT_ORE.get(), this.createFortunateDrops(ModBlocks.SALT_ORE.get(), ModBaseItems.SALT.get(), 2.0F, 5.0F));
        this.add(ModBlocks.DEEPSLATE_SALT_ORE.get(), this.createFortunateDrops(ModBlocks.DEEPSLATE_SALT_ORE.get(), ModBaseItems.SALT.get(), 2.0F, 5.0F));

        this.dropWhenSilkTouch(ModBlocks.SCULK_JAW.get());
        this.dropSelf(ModBlocks.DUCKWEED.get());
        this.dropWhenSilkTouch(ModBlocks.GLISTERING_MELON.get());
        this.add(ModBlocks.GLISTERING_MELON.get(), this.createFortunateDrops(ModBlocks.GLISTERING_MELON.get(), Items.GLISTERING_MELON_SLICE, 3.0F, 7.0F, 9));

        this.dropSelf(ModBlocks.STONE_WALL.get());
        this.dropSelf(ModBlocks.POLISHED_STONE.get());
        this.dropSelf(ModBlocks.POLISHED_STONE_WALL.get());
        this.add(ModBlocks.POLISHED_STONE_SLAB.get(), this.createSlabItemTable(ModBlocks.POLISHED_STONE_SLAB.get()));
        this.dropSelf(ModBlocks.POLISHED_STONE_STAIRS.get());
        this.dropSelf(ModBlocks.POLISHED_GRANITE_WALL.get());

        ModBlockFamilies.AZALEA.generateBlockLoot(this::dropSelf, slab -> this.add(slab, this.createSlabItemTable(slab)));
        ModBlockFamilies.PALM.generateBlockLoot(this::dropSelf, slab -> this.add(slab, this.createSlabItemTable(slab)));
        ModBlockFamilies.BAOBAB.generateBlockLoot(this::dropSelf, slab -> this.add(slab, this.createSlabItemTable(slab)));

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
        this.dropSelf(ModBlocks.CHISELED_ANDESITE_BRICKS.get());
        this.dropSelf(ModBlocks.DIORITE_BRICKS.get());
        this.dropSelf(ModBlocks.CRACKED_DIORITE_BRICKS.get());
        this.dropSelf(ModBlocks.DIORITE_BRICK_STAIRS.get());
        this.add(ModBlocks.DIORITE_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.DIORITE_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.DIORITE_BRICK_WALL.get());
        this.dropSelf(ModBlocks.CHISELED_DIORITE_BRICKS.get());
        this.dropSelf(ModBlocks.GRANITE_BRICKS.get());
        this.dropSelf(ModBlocks.CRACKED_GRANITE_BRICKS.get());
        this.dropSelf(ModBlocks.GRANITE_BRICK_STAIRS.get());
        this.add(ModBlocks.GRANITE_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.GRANITE_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.GRANITE_BRICK_WALL.get());
        this.dropSelf(ModBlocks.CHISELED_GRANITE_BRICKS.get());
        this.dropSelf(ModBlocks.BLUE_ICE_BRICKS.get());
        this.dropSelf(ModBlocks.CRACKED_BLUE_ICE_BRICKS.get());
        this.dropSelf(ModBlocks.BLUE_ICE_BRICK_STAIRS.get());
        this.add(ModBlocks.BLUE_ICE_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.BLUE_ICE_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.BLUE_ICE_BRICK_WALL.get());
        this.dropSelf(ModBlocks.CHISELED_BLUE_ICE_BRICKS.get());
        this.dropSelf(ModBlocks.SOUL_JACK_O_LANTERN.get());
        this.dropSelf(ModBlocks.FLUFFY_DANDELION.get());
        this.dropSelf(ModBlocks.ROSE.get());
    }

    protected LootTable.Builder createBedDrops(Block block) {
        return LootTable.lootTable()
                .withPool(
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(block.asItem())
                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                        .hasProperty(BedBlock.PART, BedPart.HEAD)
                                                )
                                        )
                                )
                                .when(ExplosionCondition.survivesExplosion())
        );
    }

    protected LootTable.Builder createBannerDrops(Block block) {
        return LootTable.lootTable()
                .withPool(
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(block.asItem())
                                        .apply(
                                                CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY)
                                                        .include(DataComponents.CUSTOM_NAME)
                                                        .include(DataComponents.ITEM_NAME)
                                                        .include(DataComponents.HIDE_ADDITIONAL_TOOLTIP)
                                                        .include(DataComponents.BANNER_PATTERNS)
                                                        .include(DataComponents.RARITY)
                                        )
                                )
                                .when(ExplosionCondition.survivesExplosion())
                );
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
                                                LootItem.lootTableItem(item)
                                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AshLayerBlock.LAYERS, i)))
                                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(i)))
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
}
