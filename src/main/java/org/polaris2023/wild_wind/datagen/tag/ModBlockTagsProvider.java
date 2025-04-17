package org.polaris2023.wild_wind.datagen.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.polaris2023.annotation.enums.TagType;
import org.polaris2023.annotation.handler.TagHandler;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.dyed.DyedBlockMap;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.init.tags.ModBlockTags;
import org.polaris2023.wild_wind.datagen.ModBlockFamilies;
import org.polaris2023.wild_wind.datagen.ModDyedArray;
import org.polaris2023.wild_wind.util.Helpers;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, WildWindMod.MOD_ID, existingFileHelper);
    }

    protected IntrinsicTagAppender<Block> tag(Supplier<TagKey<Block>> tag) {
        return super.tag(tag.get());
    }
    @TagHandler(TagType.Block)
    @Override
    protected void addTags(HolderLookup.Provider provider) {
//        tag(ModBlockTags.AZALEA_LOGS).add(ModBlocks.AZALEA_LOG.get(), ModBlocks.AZALEA_WOOD.get(), ModBlocks.STRIPPED_AZALEA_LOG.get(), ModBlocks.STRIPPED_AZALEA_WOOD.get());
//        tag(ModBlockTags.PALM_LOGS).add(ModBlocks.PALM_LOG.get(), ModBlocks.PALM_WOOD.get(), ModBlocks.STRIPPED_PALM_LOG.get(), ModBlocks.STRIPPED_PALM_WOOD.get());
//        tag(ModBlockTags.BAOBAB_LOGS).add(ModBlocks.BAOBAB_LOG.get(), ModBlocks.BAOBAB_WOOD.get(), ModBlocks.STRIPPED_BAOBAB_LOG.get(), ModBlocks.STRIPPED_BAOBAB_WOOD.get());
//        tag(BlockTags.LEAVES).add(ModBlocks.PALM_LEAVES.get(), ModBlocks.BAOBAB_LEAVES.get());
//        tag(BlockTags.SAPLINGS).add(ModBlocks.PALM_SAPLING.get(), ModBlocks.BAOBAB_SAPLING.get());
//        tag(ModBlockTags.ICE_SKIP).add(ModBlocks.BRITTLE_ICE.get());
//        tag(ModBlockTags.CATTAILS_MAY_PLACE).add(
//                Blocks.DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.MYCELIUM, Blocks.COARSE_DIRT, Blocks.FARMLAND,
//                Blocks.MUD, Blocks.CLAY, Blocks.GRAVEL, Blocks.SAND, Blocks.RED_SAND
//        );
//        tag(ModBlockTags.REEDS_MAY_PLACE).add(
//                Blocks.DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.MYCELIUM, Blocks.COARSE_DIRT, Blocks.FARMLAND,
//                Blocks.MUD, Blocks.CLAY, Blocks.GRAVEL, Blocks.SAND, Blocks.RED_SAND
//        );
//        for (Block block : ModDyedArray.BANNER_BLOCK) {
//            tag(ModBlockTags.BANNERS).add(block);
//        }
//        for(Block wallBanner : ModDyedArray.WALL_BANNER_BLOCK) {
//            tag(ModBlockTags.WALL_BANNERS).add(wallBanner);
//        }
        DyedBlockMap.getDyedBlock("CONCRETE_POWDER").forEach((color, block) -> tag(ModBlockTags.CONCRETE_POWDERS).add(block));

        ModBlockFamilies.AZALEA.generateBlockTags(this::tag);
        ModBlockFamilies.PALM.generateBlockTags(this::tag);
        ModBlockFamilies.BAOBAB.generateBlockTags(this::tag);
    }

    public static TagKey<Block> create(String tagName) {
        return BlockTags.create(Helpers.location(tagName));
    }

    public static TagKey<Block> create(String namespace, String tagName) {

        return BlockTags.create(ResourceLocation.fromNamespaceAndPath(namespace, tagName));
    }

    @SafeVarargs
    public final <T extends Block> void add(TagKey<Block> tag, T... blocks) {
        tag(tag).add(blocks);
    }
}
