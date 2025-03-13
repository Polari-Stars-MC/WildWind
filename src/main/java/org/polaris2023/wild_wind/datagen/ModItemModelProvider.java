package org.polaris2023.wild_wind.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModBlocks;

import javax.swing.*;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, WildWindMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        stoneWallItem(ModBlocks.STONE_WALL, Blocks.STONE);
        wallItem(ModBlocks.POLISHED_STONE_WALL, ModBlocks.POLISHED_STONE);
    }

    public void stoneWallItem(DeferredBlock<?> block, Block baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath("minecraft",
                        "block/stone"));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(WildWindMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }
}
