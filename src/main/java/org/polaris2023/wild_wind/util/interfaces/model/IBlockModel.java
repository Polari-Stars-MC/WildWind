package org.polaris2023.wild_wind.util.interfaces.model;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import org.polaris2023.wild_wind.datagen.WildWindClientProvider;
import org.polaris2023.wild_wind.util.Helpers;

import java.util.function.Supplier;

/**
 * @author : baka4n
 * {@code @Date : 2025/03/29 21:09:51}
 */
public interface IBlockModel {
    WildWindClientProvider self();

    default <T extends Block> BlockModelBuilder carpet(Supplier<T> block, boolean item, String renderType, String carpet) {
        T b = block.get();
        ResourceLocation key = key(b);
        BlockModelBuilder cm = carpetModel(key.getPath(), renderType, carpet.isEmpty() ? blockTexture(b) : ResourceLocation.parse(carpet));
        if (item) {
            self().stateProvider.simpleBlockWithItem(b, cm);
        } else {
            self().stateProvider.simpleBlock(b, cm);
        }
        return cm;
    }

    default <T extends Block> BlockModelBuilder cross(Supplier<T> block, boolean item, String renderType, String cross) {
        T b = block.get();
        ResourceLocation key = key(b);
        BlockModelBuilder cm = crossModel(key.getPath(), renderType, cross.isEmpty() ? blockTexture(b) : ResourceLocation.parse(cross));

        if (item) {
            self().stateProvider.simpleBlockWithItem(b, cm);
        } else {
            self().stateProvider.simpleBlock(b, cm);
        }
        return cm;
    }

    default  <T extends Block> BlockModelBuilder cubeAll(Supplier<T> block, boolean item, String renderType, String all) {
        T b = block.get();
        BlockModelBuilder model = cubeAllModel(self().key(b).getPath(), renderType, all.isEmpty() ? blockTexture(b) : ResourceLocation.parse(all));
        if (item) {
            self().stateProvider.simpleBlockWithItem(b, model);
        } else {
            self().stateProvider.simpleBlock(b, model);
        }
        return model;
    }

    default <T extends Block> BlockModelBuilder carpetModel(String path, String renderType, ResourceLocation carpet) {
        BlockModelBuilder cm = self().blockModelProvider.carpet(path, carpet);
        if (!renderType.isEmpty()) cm.renderType(renderType);
        return cm;
    }

    default <T extends Block> BlockModelBuilder crossModel(String path, String renderType, ResourceLocation cross) {
        BlockModelBuilder cm = self().blockModelProvider.cross(path, cross);
        if (!renderType.isEmpty()) cm.renderType(renderType);
        return cm;
    }

    default <T extends Block> BlockModelBuilder cubeAllModel(String path, String renderType, String all) {
        return cubeAllModel(path, renderType, all.isEmpty() ? Helpers.location(path) : ResourceLocation.parse(all));
    }

    default <T extends Block> BlockModelBuilder cubeAllModel(Supplier<T> block, String renderType, String all, int index) {
        Block b = block.get();
        ResourceLocation key = key(b);
        return cubeAllModel(key.getPath() + "_" + index, renderType, all.isEmpty() ? blockTexture(b).withSuffix("_" + index).toString() : all + "_" + index);
    }

    default <T extends Block> BlockModelBuilder cubeAllModel(String path, String renderType, ResourceLocation all) {
        BlockModelBuilder bm = self().blockModelProvider.cubeAll(path, all);
        if (!renderType.isEmpty()) bm.renderType(renderType);
        return bm;
    }

    default  <T extends Block> BlockModelBuilder cubeAllModel(Supplier<T> block, String renderType, String all) {
        T b = block.get();
        ResourceLocation key = self().key(b);
        return cubeAllModel(key.getPath(), renderType, all.isEmpty() ? blockTexture(b) : ResourceLocation.parse(all));
    }

    default <T extends Block> ResourceLocation key(T block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    default <T extends Block> ResourceLocation blockTexture(T block) {
        ResourceLocation name = key(block);
        return ResourceLocation.fromNamespaceAndPath(name.getNamespace(), "block/" + name.getPath());
    }
}
