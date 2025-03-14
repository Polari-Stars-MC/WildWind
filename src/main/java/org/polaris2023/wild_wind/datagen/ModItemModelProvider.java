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
import org.polaris2023.wild_wind.common.init.ModItems;

import javax.swing.*;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, WildWindMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModBlocks.DUCKWEED_ITEM.get());
        withExistingParent(ModItems.MAGIC_WAND_TOOL_ITEM.getId().getPath(), ResourceLocation.fromNamespaceAndPath("minecraft", "item/stick"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath("minecraft", "item/stick"));
        stoneWallItem(ModBlocks.STONE_WALL);
        wallItem(ModBlocks.POLISHED_STONE_WALL, ModBlocks.POLISHED_STONE);

        withExistingParent(ModBlocks.ASH.getId().getPath(), modLoc("block/ash_1"));
    }

    public void stoneWallItem(DeferredBlock<?> block) {
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
