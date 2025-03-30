package org.polaris2023.wild_wind.datagen;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;

import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.*;

import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.wild_wind.common.block.AshLayerBlock;
import org.polaris2023.wild_wind.common.block.BrittleIceBlock;
import org.polaris2023.wild_wind.common.block.GlowMucusBlock;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.util.Helpers;
import org.polaris2023.wild_wind.util.interfaces.datagen.DatagenClient;
import org.polaris2023.wild_wind.util.interfaces.datagen.ILanguage;
import org.polaris2023.wild_wind.util.interfaces.datagen.model.IBlockModel;
import org.polaris2023.wild_wind.util.interfaces.datagen.model.IItemModel;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * @author : baka4n
 * {@code @Date : 2025/03/18 19:09:04}
 */
public class WildWindClientProvider implements DatagenClient, DataProvider, IBlockModel, IItemModel, ILanguage {

    public final PackOutput output;
    public final BlockStateProvider stateProvider;
    public final BlockModelProvider blockModelProvider;
    public final ItemModelProvider itemModelProvider;

    public final String modid;

    public WildWindClientProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        this.modid = modid;
        this.output = output;

        this.stateProvider = new BlockStateProvider(output, modid, exFileHelper) {

            @Override
            protected void registerStatesAndModels() {
                block();
                init();
                state();
                item();
            }
        };
        languageInit();
        itemModelProvider = stateProvider.itemModels();
        blockModelProvider = stateProvider.models();


    }

    public void state() {
        // Stone & Polished Stone

//        stateProvider.wallBlock(ModBlocks.POLISHED_STONE_WALL.get(), blockTexture(ModBlocks.POLISHED_STONE.get()));
//        stateProvider.stairsBlock(ModBlocks.POLISHED_STONE_STAIRS.get(), blockTexture(ModBlocks.POLISHED_STONE.get()));
//        stateProvider.slabBlock(ModBlocks.POLISHED_STONE_SLAB.get(), blockTexture(ModBlocks.POLISHED_STONE.get()), stateProvider.blockTexture(ModBlocks.POLISHED_STONE.get()));

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
                    .addModels(new ConfiguredModel(blockModelProvider.getBuilder("glazed_terracotta"), 0, yRotation, false));
        }

        //glistering_melon


        stateProvider.simpleBlock(ModBlocks.GLISTERING_MELON.get(), blockModelProvider.getBuilder("glistering_melon"));
//        List<DeferredHolder<Block, ?>> blocks = new ArrayList<>(ModInitializer.blocks());
//        stateProvider.models().generatedModels.forEach((location, __) -> {
//            blocks.removeIf(h -> h.getId().equals(location));
//        });
//        for (DeferredHolder<Block, ?> block : blocks) {
//            Block b = block.get();
//            if (b.asItem().equals(Items.AIR)) {
//                stateProvider.simpleBlock(b, blockModelProvider.cubeAll(key(b).getPath(), blockTexture(b)).texture("all", Helpers.BLOCK_PLACEHOLDER));
//            }
//        }

    } // 手写模型生成塞这块

    public void block() {
        //glistering_melon
        blockModelProvider.cubeBottomTop(
                "glistering_melon",
                Helpers.location("block/glistering_melon_side"),
                Helpers.location("block/glistering_melon_side"),
                Helpers.location("block/glistering_melon_top")
        );
        blockModelProvider.cubeAll("glazed_terracotta", Helpers.location("block/glazed_terracotta"));

    }

    public void item() {
        itemModelProvider.simpleBlockItem(ModBlocks.BRITTLE_ICE.get());
    }

    public void init() {
    }// 不可在此处写代码，切记,这里用于注解生成器生成代码

    public void languageInit() {
    }// 不可在此处写代码，切记,这里用于注解生成器生成代码


    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        List<CompletableFuture<?>> futures =
                new LinkedList<>(LANGUAGES.values().stream().map((LanguageProvider cache) -> cache.run(cachedOutput)).toList());
        futures.add(stateProvider.run(cachedOutput));
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    }

    @Override
    public String getName() {
        return "Client Generated Bus By " + modid;
    }

    @Override
    public WildWindClientProvider self() {
        return this;
    }
}
