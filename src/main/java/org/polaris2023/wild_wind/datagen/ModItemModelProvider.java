package org.polaris2023.wild_wind.datagen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModBlocks;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, WildWindMod.MOD_ID, existingFileHelper);

    }

    @Override
    protected void registerModels() {
//        withExistingParent(ModItems.MAGIC_WAND_TOOL_ITEM.getId().getPath(), ResourceLocation.fromNamespaceAndPath("minecraft", "item/stick"))
//                .texture("layer0", ResourceLocation.fromNamespaceAndPath("minecraft", "item/stick"));
        wallItem(ModBlocks.STONE_WALL, DeferredBlock.create(Registries.BLOCK, ResourceLocation.withDefaultNamespace("stone")));
        wallItem(ModBlocks.POLISHED_STONE_WALL, ModBlocks.POLISHED_STONE);
        wallItem(ModBlocks.ANDESITE_BRICK_WALL, ModBlocks.ANDESITE_BRICKS);
        wallItem(ModBlocks.DIORITE_BRICK_WALL, ModBlocks.DIORITE_BRICKS);
        wallItem(ModBlocks.GRANITE_BRICK_WALL, ModBlocks.GRANITE_BRICKS);
    }

    public void wallItem(DeferredHolder<Block, ?> block, DeferredHolder<Block, Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(baseBlock.getId().getNamespace(), "block/" + baseBlock.getId().getPath()));
    }
}
