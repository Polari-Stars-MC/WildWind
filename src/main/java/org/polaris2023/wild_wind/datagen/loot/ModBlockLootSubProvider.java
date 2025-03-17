package org.polaris2023.wild_wind.datagen.loot;

import com.google.common.collect.ImmutableSet;
import com.mojang.blaze3d.shaders.Uniform;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.NumberProviders;
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
        this.add(ModBlocks.ASH_BLOCK.get(), this.createFortunateDrops(ModBlocks.ASH_BLOCK.get(), ModItems.ASH_DUST.get(), 2.0F, 4.0F));
        this.add(ModBlocks.ASH.get(), this.createLayerDrops(ModBlocks.ASH_BLOCK.get(), ModBlocks.ASH.asItem(), ModItems.ASH_DUST.get()));
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
        this.dropSelf(ModBlocks.GLISTERING_MELON.get());
        this.dropSelf(ModBlocks.STONE_WALL.get());
        this.dropSelf(ModBlocks.POLISHED_STONE.get());
        this.dropSelf(ModBlocks.POLISHED_STONE_WALL.get());
        this.dropSelf(ModBlocks.POLISHED_STONE_SLAB.get());
        this.dropSelf(ModBlocks.POLISHED_STONE_STAIRS.get());
        ModBlockFamilies.AZALEA_PLANKS.generateBlockLoot(this::dropSelf);
        ModBlockFamilies.PALM_PLANKS.generateBlockLoot(this::dropSelf);
        ModBlockFamilies.BAOBAB_PLANKS.generateBlockLoot(this::dropSelf);
        this.add(ModBlocks.AZALEA_DOOR.get(), this.createDoorTable(ModBlocks.AZALEA_DOOR.get()));
        this.add(ModBlocks.PALM_DOOR.get(), this.createDoorTable(ModBlocks.PALM_DOOR.get()));
        this.add(ModBlocks.BAOBAB_DOOR.get(), this.createDoorTable(ModBlocks.BAOBAB_DOOR.get()));
        this.dropSelf(ModBlocks.PALM_CROWN.get());
        this.add(ModBlocks.PALM_LEAVES.get(), this.createLeavesDrops(ModBlocks.PALM_LEAVES.get(), ModBlocks.PALM_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(ModBlocks.BAOBAB_LEAVES.get(), this.createLeavesDrops(ModBlocks.BAOBAB_LEAVES.get(), ModBlocks.BAOBAB_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(ModBlocks.PALM_SAPLING.get());
        this.dropSelf(ModBlocks.BAOBAB_SAPLING.get());
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

    protected LootTable.Builder createLayerDrops(Block block, Item itemWithSilkTouch, Item item) {
        return LootTable.lootTable()
                .withPool(
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(block))
                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.ASH.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AshLayerBlock.LAYERS, 8)))
                                .when(hasSilkTouch())
                )
                .withPool(layerPoolWithoutSilkTouch(item, 1, 1.0F))
                .withPool(layerPoolWithoutSilkTouch(item, 2, 2.0F))
                .withPool(layerPoolWithoutSilkTouch(item, 3, 3.0F))
                .withPool(layerPoolWithoutSilkTouch(item, 4, 4.0F))
                .withPool(layerPoolWithoutSilkTouch(item, 5, 5.0F))
                .withPool(layerPoolWithoutSilkTouch(item, 6, 6.0F))
                .withPool(layerPoolWithoutSilkTouch(item, 7, 7.0F))
                .withPool(layerPoolWithSilkTouch(itemWithSilkTouch, 1, 1.0F))
                .withPool(layerPoolWithSilkTouch(itemWithSilkTouch, 2, 2.0F))
                .withPool(layerPoolWithSilkTouch(itemWithSilkTouch, 3, 3.0F))
                .withPool(layerPoolWithSilkTouch(itemWithSilkTouch, 4, 4.0F))
                .withPool(layerPoolWithSilkTouch(itemWithSilkTouch, 5, 5.0F))
                .withPool(layerPoolWithSilkTouch(itemWithSilkTouch, 6, 6.0F))
                .withPool(layerPoolWithSilkTouch(itemWithSilkTouch, 7, 7.0F));
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
