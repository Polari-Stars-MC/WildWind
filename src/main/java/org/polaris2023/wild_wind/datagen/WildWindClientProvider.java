package org.polaris2023.wild_wind.datagen;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.*;

import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.polaris2023.wild_wind.common.block.AshLayerBlock;
import org.polaris2023.wild_wind.common.block.BrittleIceBlock;
import org.polaris2023.wild_wind.common.block.GlowMucusBlock;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.util.Helpers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : baka4n
 * {@code @Date : 2025/03/18 19:09:04}
 */
public class WildWindClientProvider implements DataProvider {

    final PackOutput output;
    final BlockStateProvider stateProvider;
    final BlockModelProvider blockModelProvider;
    final ItemModelProvider itemModelProvider;

    final String modid;

    public void state() {

        // Tiny Cactus
        stateProvider.simpleBlock(ModBlocks.TINY_CACTUS.get(), blockModelProvider.cross("tiny_cactus", Helpers.location("block/tiny_cactus")).renderType("cutout"));

        // Carpet
        stateProvider.simpleBlockWithItem(ModBlocks.CARPET.get(), blockModelProvider.carpet("carpet", Helpers.location("block/wool")));
        // Stone & Polished Stone
        stateProvider.wallBlock(ModBlocks.STONE_WALL.get(), stateProvider.blockTexture(Blocks.STONE));
        stateProvider.simpleBlockWithItem(ModBlocks.POLISHED_STONE.get(), blockModelProvider.cubeAll("polished_stone", Helpers.location("block/polished_stone")));
        stateProvider.wallBlock(ModBlocks.POLISHED_STONE_WALL.get(), stateProvider.blockTexture(ModBlocks.POLISHED_STONE.get()));
        stateProvider.stairsBlock(ModBlocks.POLISHED_STONE_STAIRS.get(), stateProvider.blockTexture(ModBlocks.POLISHED_STONE.get()));
        stateProvider.slabBlock(ModBlocks.POLISHED_STONE_SLAB.get(), stateProvider.blockTexture(ModBlocks.POLISHED_STONE.get()), stateProvider.blockTexture(ModBlocks.POLISHED_STONE.get()));

        // Sapling
        stateProvider.simpleBlock(ModBlocks.PALM_SAPLING.get(), blockModelProvider.cross("palm_sapling", Helpers.location("block/palm_sapling")).renderType("cutout"));
        stateProvider.simpleBlock(ModBlocks.BAOBAB_SAPLING.get(), blockModelProvider.cross("baobab_sapling", Helpers.location("block/baobab_sapling")).renderType("cutout"));


        VariantBlockStateBuilder glowMucusStates = stateProvider.getVariantBuilder(ModBlocks.GLOW_MUCUS.get());
        for (Direction facing : Direction.values()) {
            AtomicInteger x = new AtomicInteger();
            AtomicInteger y = new AtomicInteger();
            switch (facing) {
                case UP -> x.set(180);
                case NORTH, WEST -> {
                    x.set(270);
                    if (facing.equals(Direction.WEST)) {
                        y.set(270);
                    }
                }
                case SOUTH, EAST -> {
                    x.set(90);
                    if (facing.equals(Direction.EAST)) {
                        y.set(270);
                    }
                }

            }
            for (Integer possibleValue : GlowMucusBlock.LAYERS.getPossibleValues()) {
                glowMucusStates.addModels(
                        glowMucusStates
                                .partialState()
                                .with(GlowMucusBlock.FACING, facing)
                                .with(GlowMucusBlock.LAYERS, possibleValue),
                        new ConfiguredModel(blockModelProvider.getExistingFile(Helpers.location("block/glow_mucus_light" + (possibleValue * 3))), x.get(), y.get(), false)
                );
            }
        }

        VariantBlockStateBuilder brittleIceStates = stateProvider.getVariantBuilder(ModBlocks.BRITTLE_ICE.get());
        for(int age : BrittleIceBlock.AGE.getPossibleValues()) {
            for(boolean unstable : BrittleIceBlock.UNSTABLE.getPossibleValues()) {
                brittleIceStates.addModels(
                        brittleIceStates.partialState().with(BrittleIceBlock.AGE, age).with(BrittleIceBlock.UNSTABLE, unstable),
                        new ConfiguredModel(blockModelProvider.getExistingFile(Helpers.location("block/brittle_ice_" + age)))
                );
            }
        }
        VariantBlockStateBuilder ashStates = stateProvider.getVariantBuilder(ModBlocks.ASH.get());
        for(int layer : AshLayerBlock.LAYERS.getPossibleValues()) {
            ashStates.addModels(
                    ashStates.partialState().with(AshLayerBlock.LAYERS, layer),
                    new ConfiguredModel(blockModelProvider.getExistingFile(Helpers.location("block/ash_" + layer)))
            );
        }

        // Glazed Terracotta
        VariantBlockStateBuilder glazedTerracottaStates = stateProvider.getVariantBuilder(ModBlocks.GLAZED_TERRACOTTA.get());
        for (Direction facing : Direction.Plane.HORIZONTAL) {
            int yRotation = switch (facing) {
                case EAST -> 270;
                case NORTH -> 180;
                case WEST -> 90;
                default -> 0;
            };
            glazedTerracottaStates.partialState().with(BlockStateProperties.HORIZONTAL_FACING, facing)
                    .addModels(new ConfiguredModel(blockModelProvider.cubeAll("glazed_terracotta", Helpers.location("block/glazed_terracotta")), 0, yRotation, false));
        }

        //glistering_melon
        BlockModelBuilder glisteringMelonModel = blockModelProvider.cubeBottomTop(
                "glistering_melon",
                Helpers.location("block/glistering_melon_side"),
                Helpers.location("block/glistering_melon_side"),
                Helpers.location("block/glistering_melon_top")
        );
        stateProvider.simpleBlock(ModBlocks.GLISTERING_MELON.get(), glisteringMelonModel);
    } // 手写模型生成塞这块

    public void item() {
        itemModelProvider.simpleBlockItem(ModBlocks.GLISTERING_MELON.get());
        itemModelProvider.simpleBlockItem(ModBlocks.GLAZED_TERRACOTTA.get());
    }

    public void init() {
    }// 不可在此处写代码，切记


    public ItemModelBuilder basicBlockLocatedItem(ResourceLocation block) {
        return itemModelProvider.getBuilder(block.toString()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", ResourceLocation.fromNamespaceAndPath(block.getNamespace(), "block/" + block.getPath()));
    }

    public ItemModelBuilder basicBlockLocatedItem(Item blockItem) {
        return basicBlockLocatedItem(BuiltInRegistries.ITEM.getKey(blockItem));
    }

    public static WildWindClientProvider getInstance(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        return new WildWindClientProvider(output, modid, exFileHelper);
    }


    public WildWindClientProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        this.modid = modid;
        this.output = output;
        this.stateProvider = new BlockStateProvider(output, modid, exFileHelper) {

            @Override
            protected void registerStatesAndModels() {
                state();//1
                item();// 2
                init();// 3
            }
        };
        itemModelProvider = stateProvider.itemModels();
        blockModelProvider = stateProvider.models();


    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        CompletableFuture<?> run = stateProvider.run(cachedOutput);
        return CompletableFuture.allOf(run);
    }

    @Override
    public String getName() {
        return "Model Generated By " + modid;
    }
}
