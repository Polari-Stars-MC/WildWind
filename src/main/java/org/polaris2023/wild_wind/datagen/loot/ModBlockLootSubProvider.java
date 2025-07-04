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
        return BlockRegistry.entry().stream().map(holder -> (Block) holder.get()).toList();
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
        this.dropWhenSilkTouch(ModBlocks.SCORCH_PYROCLAST.get());
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
        this.dropSelf(ModBlocks.POLISHED_DIORITE_WALL.get());
        this.dropSelf(ModBlocks.POLISHED_ANDESITE_WALL.get());

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
        this.dropSelf(ModBlocks.CRACKED_TUFF_BRICKS.get());
        this.dropSelf(ModBlocks.CRACKED_BRICKS.get());
        this.dropSelf(ModBlocks.CHISELED_BRICKS.get());
        this.dropSelf(ModBlocks.CHISELED_DEEPSLATE_BRICKS.get());
        this.dropSelf(ModBlocks.CHISELED_DEEPSLATE_TILES.get());
        this.dropSelf(ModBlocks.CRACKED_MUD_BRICKS.get());
        this.dropSelf(ModBlocks.CHISELED_MUD_BRICKS.get());
        this.dropSelf(ModBlocks.CRACKED_SANDSTONE_BRICKS.get());
        this.dropSelf(ModBlocks.CHISELED_SANDSTONE_BRICKS.get());
        this.dropSelf(ModBlocks.CRACKED_RED_SANDSTONE_BRICKS.get());
        this.dropSelf(ModBlocks.CHISELED_RED_SANDSTONE_BRICKS.get());

        this.dropSelf(ModBlocks.MOSSY_SANDSTONE.get());
        this.dropSelf(ModBlocks.MOSSY_SANDSTONE_WALL.get());
        this.add(ModBlocks.MOSSY_SANDSTONE_SLAB.get(), this.createSlabItemTable(ModBlocks.MOSSY_SANDSTONE_SLAB.get()));
        this.dropSelf(ModBlocks.MOSSY_SANDSTONE_STAIRS.get());
        this.dropSelf(ModBlocks.SMOOTH_SANDSTONE_WALL.get());
        this.dropSelf(ModBlocks.MOSSY_RED_SANDSTONE.get());
        this.dropSelf(ModBlocks.MOSSY_RED_SANDSTONE_WALL.get());
        this.add(ModBlocks.MOSSY_RED_SANDSTONE_SLAB.get(), this.createSlabItemTable(ModBlocks.MOSSY_RED_SANDSTONE_SLAB.get()));
        this.dropSelf(ModBlocks.MOSSY_RED_SANDSTONE_STAIRS.get());
        this.dropSelf(ModBlocks.SMOOTH_RED_SANDSTONE_WALL.get());

        this.dropSelf(ModBlocks.COBBLED_BLACKSTONE.get());
        this.dropSelf(ModBlocks.COBBLED_BLACKSTONE_WALL.get());
        this.add(ModBlocks.COBBLED_BLACKSTONE_SLAB.get(), this.createSlabItemTable(ModBlocks.COBBLED_BLACKSTONE_SLAB.get()));
        this.dropSelf(ModBlocks.COBBLED_BLACKSTONE_STAIRS.get());
        this.dropSelf(ModBlocks.MOSSY_COBBLED_BLACKSTONE.get());
        this.dropSelf(ModBlocks.MOSSY_COBBLED_BLACKSTONE_WALL.get());
        this.add(ModBlocks.MOSSY_COBBLED_BLACKSTONE_SLAB.get(), this.createSlabItemTable(ModBlocks.MOSSY_COBBLED_BLACKSTONE_SLAB.get()));
        this.dropSelf(ModBlocks.MOSSY_COBBLED_BLACKSTONE_STAIRS.get());
        this.dropSelf(ModBlocks.CHISELED_POLISHED_BLACKSTONE_BRICKS.get());
        this.dropSelf(ModBlocks.MOSSY_POLISHED_BLACKSTONE_BRICKS.get());
        this.dropSelf(ModBlocks.MOSSY_POLISHED_BLACKSTONE_BRICK_WALL.get());
        this.add(ModBlocks.MOSSY_POLISHED_BLACKSTONE_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.MOSSY_POLISHED_BLACKSTONE_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.MOSSY_POLISHED_BLACKSTONE_BRICK_STAIRS.get());

        this.dropSelf(ModBlocks.MOSSY_GRANITE_BRICKS.get());
        this.dropSelf(ModBlocks.MOSSY_GRANITE_BRICK_STAIRS.get());
        this.add(ModBlocks.MOSSY_GRANITE_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.MOSSY_GRANITE_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.MOSSY_GRANITE_BRICK_WALL.get());
        this.dropSelf(ModBlocks.MOSSY_DIORITE_BRICKS.get());
        this.dropSelf(ModBlocks.MOSSY_DIORITE_BRICK_STAIRS.get());
        this.add(ModBlocks.MOSSY_DIORITE_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.MOSSY_DIORITE_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.MOSSY_DIORITE_BRICK_WALL.get());
        this.dropSelf(ModBlocks.MOSSY_ANDESITE_BRICKS.get());
        this.dropSelf(ModBlocks.MOSSY_ANDESITE_BRICK_STAIRS.get());
        this.add(ModBlocks.MOSSY_ANDESITE_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.MOSSY_ANDESITE_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.MOSSY_ANDESITE_BRICK_WALL.get());
        this.dropSelf(ModBlocks.MOSSY_COBBLED_DEEPSLATE.get());
        this.dropSelf(ModBlocks.MOSSY_COBBLED_DEEPSLATE_STAIRS.get());
        this.add(ModBlocks.MOSSY_COBBLED_DEEPSLATE_SLAB.get(), this.createSlabItemTable(ModBlocks.MOSSY_COBBLED_DEEPSLATE_SLAB.get()));
        this.dropSelf(ModBlocks.MOSSY_COBBLED_DEEPSLATE_WALL.get());
        this.dropSelf(ModBlocks.MOSSY_DEEPSLATE_BRICKS.get());
        this.dropSelf(ModBlocks.MOSSY_DEEPSLATE_BRICK_STAIRS.get());
        this.add(ModBlocks.MOSSY_DEEPSLATE_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.MOSSY_DEEPSLATE_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.MOSSY_DEEPSLATE_BRICK_WALL.get());
        this.dropSelf(ModBlocks.MOSSY_DEEPSLATE_TILES.get());
        this.dropSelf(ModBlocks.MOSSY_DEEPSLATE_TILE_STAIRS.get());
        this.add(ModBlocks.MOSSY_DEEPSLATE_TILE_SLAB.get(), this.createSlabItemTable(ModBlocks.MOSSY_DEEPSLATE_TILE_SLAB.get()));
        this.dropSelf(ModBlocks.MOSSY_DEEPSLATE_TILE_WALL.get());
        this.dropSelf(ModBlocks.MOSSY_TUFF_BRICKS.get());
        this.dropSelf(ModBlocks.MOSSY_TUFF_BRICK_STAIRS.get());
        this.add(ModBlocks.MOSSY_TUFF_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.MOSSY_TUFF_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.MOSSY_TUFF_BRICK_WALL.get());
        this.dropSelf(ModBlocks.MOSSY_BRICKS.get());
        this.dropSelf(ModBlocks.MOSSY_BRICK_STAIRS.get());
        this.add(ModBlocks.MOSSY_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.MOSSY_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.MOSSY_BRICK_WALL.get());
        this.dropSelf(ModBlocks.MOSSY_MUD_BRICKS.get());
        this.dropSelf(ModBlocks.MOSSY_MUD_BRICK_STAIRS.get());
        this.add(ModBlocks.MOSSY_MUD_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.MOSSY_MUD_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.MOSSY_MUD_BRICK_WALL.get());
        this.dropSelf(ModBlocks.SANDSTONE_BRICKS.get());
        this.dropSelf(ModBlocks.SANDSTONE_BRICK_STAIRS.get());
        this.add(ModBlocks.SANDSTONE_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.SANDSTONE_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.SANDSTONE_BRICK_WALL.get());
        this.dropSelf(ModBlocks.MOSSY_SANDSTONE_BRICKS.get());
        this.dropSelf(ModBlocks.MOSSY_SANDSTONE_BRICK_STAIRS.get());
        this.add(ModBlocks.MOSSY_SANDSTONE_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.MOSSY_SANDSTONE_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.MOSSY_SANDSTONE_BRICK_WALL.get());
        this.dropSelf(ModBlocks.MOSSY_RED_SANDSTONE_BRICKS.get());
        this.dropSelf(ModBlocks.MOSSY_RED_SANDSTONE_BRICK_STAIRS.get());
        this.add(ModBlocks.MOSSY_RED_SANDSTONE_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.MOSSY_RED_SANDSTONE_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.MOSSY_RED_SANDSTONE_BRICK_WALL.get());
        this.dropSelf(ModBlocks.MOSSY_PRISMARINE.get());
        this.dropSelf(ModBlocks.MOSSY_PRISMARINE_STAIRS.get());
        this.add(ModBlocks.MOSSY_PRISMARINE_SLAB.get(), this.createSlabItemTable(ModBlocks.MOSSY_PRISMARINE_SLAB.get()));
        this.dropSelf(ModBlocks.MOSSY_PRISMARINE_WALL.get());
        this.dropSelf(ModBlocks.POLISHED_PRISMARINE.get());
        this.dropSelf(ModBlocks.POLISHED_PRISMARINE_STAIRS.get());
        this.add(ModBlocks.POLISHED_PRISMARINE_SLAB.get(), this.createSlabItemTable(ModBlocks.POLISHED_PRISMARINE_SLAB.get()));
        this.dropSelf(ModBlocks.POLISHED_PRISMARINE_WALL.get());
        this.dropSelf(ModBlocks.CRACKED_PRISMARINE_BRICKS.get());
        this.dropSelf(ModBlocks.CHISELED_PRISMARINE_BRICKS.get());
        this.dropSelf(ModBlocks.RED_SANDSTONE_BRICKS.get());
        this.dropSelf(ModBlocks.RED_SANDSTONE_BRICK_STAIRS.get());
        this.add(ModBlocks.RED_SANDSTONE_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.RED_SANDSTONE_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.RED_SANDSTONE_BRICK_WALL.get());
        this.dropSelf(ModBlocks.SNOW_BRICKS.get());
        this.dropSelf(ModBlocks.SNOW_BRICK_STAIRS.get());
        this.add(ModBlocks.SNOW_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.SNOW_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.SNOW_BRICK_WALL.get());
        this.dropSelf(ModBlocks.CRACKED_SNOW_BRICKS.get());
        this.dropSelf(ModBlocks.CHISELED_SNOW_BRICKS.get());
        this.dropSelf(ModBlocks.POLISHED_CALCITE.get());
        this.dropSelf(ModBlocks.POLISHED_CALCITE_STAIRS.get());
        this.add(ModBlocks.POLISHED_CALCITE_SLAB.get(), this.createSlabItemTable(ModBlocks.POLISHED_CALCITE_SLAB.get()));
        this.dropSelf(ModBlocks.POLISHED_CALCITE_WALL.get());
        this.dropSelf(ModBlocks.CALCITE_BRICKS.get());
        this.dropSelf(ModBlocks.CALCITE_BRICK_STAIRS.get());
        this.add(ModBlocks.CALCITE_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.CALCITE_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.CALCITE_BRICK_WALL.get());
        this.dropSelf(ModBlocks.CRACKED_CALCITE_BRICKS.get());
        this.dropSelf(ModBlocks.CHISELED_CALCITE_BRICKS.get());
        this.dropSelf(ModBlocks.POLISHED_DRIPSTONE_BLOCK.get());
        this.dropSelf(ModBlocks.POLISHED_DRIPSTONE_STAIRS.get());
        this.add(ModBlocks.POLISHED_DRIPSTONE_SLAB.get(), this.createSlabItemTable(ModBlocks.POLISHED_DRIPSTONE_SLAB.get()));
        this.dropSelf(ModBlocks.POLISHED_DRIPSTONE_WALL.get());
        this.dropSelf(ModBlocks.DRIPSTONE_BRICKS.get());
        this.dropSelf(ModBlocks.DRIPSTONE_BRICK_STAIRS.get());
        this.add(ModBlocks.DRIPSTONE_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.DRIPSTONE_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.DRIPSTONE_BRICK_WALL.get());
        this.dropSelf(ModBlocks.CRACKED_DRIPSTONE_BRICKS.get());
        this.dropSelf(ModBlocks.CHISELED_DRIPSTONE_BRICKS.get());
        this.dropSelf(ModBlocks.MOSSY_CALCITE_BRICKS.get());
        this.dropSelf(ModBlocks.MOSSY_CALCITE_BRICK_STAIRS.get());
        this.add(ModBlocks.MOSSY_CALCITE_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.MOSSY_CALCITE_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.MOSSY_CALCITE_BRICK_WALL.get());
        this.dropSelf(ModBlocks.MOSSY_DRIPSTONE_BRICKS.get());
        this.dropSelf(ModBlocks.MOSSY_DRIPSTONE_BRICK_STAIRS.get());
        this.add(ModBlocks.MOSSY_DRIPSTONE_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.MOSSY_DRIPSTONE_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.MOSSY_DRIPSTONE_BRICK_WALL.get());

        // 末地石系列掉落物
        this.dropSelf(ModBlocks.END_STONE_STAIRS.get());
        this.add(ModBlocks.END_STONE_SLAB.get(), this.createSlabItemTable(ModBlocks.END_STONE_SLAB.get()));
        this.dropSelf(ModBlocks.END_STONE_WALL.get());
        this.dropSelf(ModBlocks.POLISHED_END_STONE.get());
        this.dropSelf(ModBlocks.POLISHED_END_STONE_STAIRS.get());
        this.add(ModBlocks.POLISHED_END_STONE_SLAB.get(), this.createSlabItemTable(ModBlocks.POLISHED_END_STONE_SLAB.get()));
        this.dropSelf(ModBlocks.POLISHED_END_STONE_WALL.get());
        this.dropSelf(ModBlocks.CRACKED_END_STONE_BRICKS.get());
        this.dropSelf(ModBlocks.CHISELED_END_STONE_BRICKS.get());
        this.dropSelf(ModBlocks.MOSSY_END_STONE_BRICKS.get());
        this.dropSelf(ModBlocks.MOSSY_END_STONE_BRICK_STAIRS.get());
        this.add(ModBlocks.MOSSY_END_STONE_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.MOSSY_END_STONE_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.MOSSY_END_STONE_BRICK_WALL.get());

        // 石英系列掉落物
        this.dropSelf(ModBlocks.PURPUR_WALL.get());
        this.dropSelf(ModBlocks.QUARTZ_BLOCK_WALL.get());
        this.dropSelf(ModBlocks.CRACKED_QUARTZ_BRICKS.get());
        this.dropSelf(ModBlocks.QUARTZ_BRICK_STAIRS.get());
        this.add(ModBlocks.QUARTZ_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.QUARTZ_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.QUARTZ_BRICK_WALL.get());
        this.dropSelf(ModBlocks.CHISELED_QUARTZ_BRICKS.get());
        this.dropSelf(ModBlocks.MOSSY_QUARTZ_BRICKS.get());
        this.dropSelf(ModBlocks.MOSSY_QUARTZ_BRICK_STAIRS.get());
        this.add(ModBlocks.MOSSY_QUARTZ_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.MOSSY_QUARTZ_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.MOSSY_QUARTZ_BRICK_WALL.get());
        this.dropSelf(ModBlocks.SMOOTH_QUARTZ_WALL.get());

        // 海晶石系列掉落物
        this.dropSelf(ModBlocks.MOSSY_PRISMARINE_BRICKS.get());
        this.dropSelf(ModBlocks.MOSSY_PRISMARINE_BRICK_STAIRS.get());
        this.add(ModBlocks.MOSSY_PRISMARINE_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.MOSSY_PRISMARINE_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.MOSSY_PRISMARINE_BRICK_WALL.get());
        this.dropSelf(ModBlocks.OCEAN_LANTERN.get());

        // 暗海晶石系列掉落物
        this.dropSelf(ModBlocks.MOSSY_DARK_PRISMARINE.get());
        this.dropSelf(ModBlocks.MOSSY_DARK_PRISMARINE_STAIRS.get());
        this.add(ModBlocks.MOSSY_DARK_PRISMARINE_SLAB.get(), this.createSlabItemTable(ModBlocks.MOSSY_DARK_PRISMARINE_SLAB.get()));
        this.dropSelf(ModBlocks.MOSSY_DARK_PRISMARINE_WALL.get());
        this.dropSelf(ModBlocks.POLISHED_DARK_PRISMARINE.get());
        this.dropSelf(ModBlocks.POLISHED_DARK_PRISMARINE_STAIRS.get());
        this.add(ModBlocks.POLISHED_DARK_PRISMARINE_SLAB.get(), this.createSlabItemTable(ModBlocks.POLISHED_DARK_PRISMARINE_SLAB.get()));
        this.dropSelf(ModBlocks.POLISHED_DARK_PRISMARINE_WALL.get());
        this.dropSelf(ModBlocks.DARK_PRISMARINE_BRICKS.get());
        this.dropSelf(ModBlocks.DARK_PRISMARINE_BRICK_STAIRS.get());
        this.add(ModBlocks.DARK_PRISMARINE_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.DARK_PRISMARINE_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.DARK_PRISMARINE_BRICK_WALL.get());
        this.dropSelf(ModBlocks.CRACKED_DARK_PRISMARINE_BRICKS.get());
        this.dropSelf(ModBlocks.CHISELED_DARK_PRISMARINE_BRICKS.get());
        this.dropSelf(ModBlocks.MOSSY_DARK_PRISMARINE_BRICKS.get());
        this.dropSelf(ModBlocks.MOSSY_DARK_PRISMARINE_BRICK_STAIRS.get());
        this.add(ModBlocks.MOSSY_DARK_PRISMARINE_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.MOSSY_DARK_PRISMARINE_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.MOSSY_DARK_PRISMARINE_BRICK_WALL.get());

        // 下界岩系列掉落物
        this.dropSelf(ModBlocks.POLISHED_NETHERRACK.get());
        this.dropSelf(ModBlocks.POLISHED_NETHERRACK_STAIRS.get());
        this.add(ModBlocks.POLISHED_NETHERRACK_SLAB.get(), this.createSlabItemTable(ModBlocks.POLISHED_NETHERRACK_SLAB.get()));
        this.dropSelf(ModBlocks.POLISHED_NETHERRACK_WALL.get());
        this.dropSelf(ModBlocks.MOSSY_NETHER_BRICKS.get());
        this.dropSelf(ModBlocks.MOSSY_NETHER_BRICK_STAIRS.get());
        this.add(ModBlocks.MOSSY_NETHER_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.MOSSY_NETHER_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.MOSSY_NETHER_BRICK_WALL.get());
        this.dropSelf(ModBlocks.RED_NETHER_BRICK_FENCE.get());
        this.dropSelf(ModBlocks.CRACKED_RED_NETHER_BRICKS.get());
        this.dropSelf(ModBlocks.MOSSY_RED_NETHER_BRICKS.get());
        this.dropSelf(ModBlocks.MOSSY_RED_NETHER_BRICK_STAIRS.get());
        this.add(ModBlocks.MOSSY_RED_NETHER_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.MOSSY_RED_NETHER_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.MOSSY_RED_NETHER_BRICK_WALL.get());
        this.dropSelf(ModBlocks.CHISELED_RED_NETHER_BRICKS.get());

        // 玄武岩系列掉落物
        this.dropSelf(ModBlocks.BASALT_STAIRS.get());
        this.add(ModBlocks.BASALT_SLAB.get(), this.createSlabItemTable(ModBlocks.BASALT_SLAB.get()));
        this.dropSelf(ModBlocks.BASALT_WALL.get());
        this.dropSelf(ModBlocks.SMOOTH_BASALT_STAIRS.get());
        this.add(ModBlocks.SMOOTH_BASALT_SLAB.get(), this.createSlabItemTable(ModBlocks.SMOOTH_BASALT_SLAB.get()));
        this.dropSelf(ModBlocks.SMOOTH_BASALT_WALL.get());
        this.dropSelf(ModBlocks.POLISHED_BASALT_STAIRS.get());
        this.add(ModBlocks.POLISHED_BASALT_SLAB.get(), this.createSlabItemTable(ModBlocks.POLISHED_BASALT_SLAB.get()));
        this.dropSelf(ModBlocks.POLISHED_BASALT_WALL.get());
        this.dropSelf(ModBlocks.BASALT_BRICKS.get());
        this.dropSelf(ModBlocks.CRACKED_BASALT_BRICKS.get());
        this.dropSelf(ModBlocks.BASALT_BRICK_STAIRS.get());
        this.add(ModBlocks.BASALT_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.BASALT_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.BASALT_BRICK_WALL.get());
        this.dropSelf(ModBlocks.CHISELED_BASALT_BRICKS.get());
        this.dropSelf(ModBlocks.MOSSY_BASALT_BRICKS.get());
        this.dropSelf(ModBlocks.MOSSY_BASALT_BRICK_STAIRS.get());
        this.add(ModBlocks.MOSSY_BASALT_BRICK_SLAB.get(), this.createSlabItemTable(ModBlocks.MOSSY_BASALT_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.MOSSY_BASALT_BRICK_WALL.get());
        this.dropSelf(ModBlocks.POLISHED_BLUE_ICE.get());
        this.dropSelf(ModBlocks.POLISHED_BLUE_ICE_STAIRS.get());
        this.add(ModBlocks.POLISHED_BLUE_ICE_SLAB.get(), this.createSlabItemTable(ModBlocks.POLISHED_BLUE_ICE_SLAB.get()));
        this.dropSelf(ModBlocks.POLISHED_BLUE_ICE_WALL.get());
        this.dropSelf(ModBlocks.PACKED_SNOW_BLOCK.get());
        this.dropSelf(ModBlocks.PACKED_SNOW_STAIRS.get());
        this.add(ModBlocks.PACKED_SNOW_SLAB.get(), this.createSlabItemTable(ModBlocks.PACKED_SNOW_SLAB.get()));
        this.dropSelf(ModBlocks.PACKED_SNOW_WALL.get());


        this.dropSelf(ModBlocks.SOUL_JACK_O_LANTERN.get());
        this.addNetherVinesDropTable(ModBlocks.SCULK_ARTERY.get(), ModBlocks.SCULK_ARTERY_PLANT.get());
        this.dropSelf(ModBlocks.FLUFFY_DANDELION.get());
        this.dropSelf(ModBlocks.ROSE.get());
        this.dropSelf(ModBlocks.WITHER_ROSE_BUSH.get());
        this.dropWhenSilkTouch(ModBlocks.SHORT_BEACH_GRASS.get());
        this.dropWhenSilkTouch(ModBlocks.TALL_BEACH_GRASS.get());
        this.dropWhenSilkTouch(ModBlocks.TALL_DEAD_BUSH.get());
        this.dropSelf(ModBlocks.SCULK_CILIA.get());
        this.dropSelf(ModBlocks.SCULK_TENDRIL.get());
        this.dropWhenSilkTouch(ModBlocks.THORN.get());
        this.dropWhenSilkTouch(ModBlocks.LARGE_THORN.get());
        this.dropWhenSilkTouch(ModBlocks.SHORT_AQUATIC_GRASS.get());
        this.dropWhenSilkTouch(ModBlocks.TALL_AQUATIC_GRASS.get());
        this.dropSelf(ModBlocks.POINTED_ICICLE.get());
        this.dropSelf(ModBlocks.LOTUS.get());
        this.dropSelf(ModBlocks.REMAINS.get());
        this.dropSelf(ModBlocks.BRAZIER.get());
        this.dropSelf(ModBlocks.SOUL_BRAZIER.get());
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
