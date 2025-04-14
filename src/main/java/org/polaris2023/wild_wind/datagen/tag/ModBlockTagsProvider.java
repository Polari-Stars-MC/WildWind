package org.polaris2023.wild_wind.datagen.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
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

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, WildWindMod.MOD_ID, existingFileHelper);
    }

    public static final TagKey<Block> CONCRETE_POWDERS = createCTag("concrete_powders");
    public static final TagKey<Block> BANNER = create("banners");
    public static final TagKey<Block> WALL_BANNER = create("wall_banners");


    protected IntrinsicTagAppender<Block> tag(Supplier<TagKey<Block>> tag) {
        return super.tag(tag.get());
    }
    @TagHandler(TagType.Block)
    @Override
    protected void addTags(HolderLookup.Provider provider) {

        var firefly_roost = tag(ModBlockTags.FIREFLY_ROOST_BLOCK.get());
        firefly_roost.add(Blocks.TALL_GRASS, Blocks.SHORT_GRASS,
                Blocks.FERN, Blocks.LARGE_FERN,
                Blocks.POTTED_FERN, Blocks.MANGROVE_PROPAGULE,
                Blocks.POTTED_MANGROVE_PROPAGULE);
        firefly_roost.addTag(BlockTags.FLOWERS);

        //Mineable
//        tag(BlockTags.SWORD_EFFICIENT).add(ModBlocks.GLISTERING_MELON.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                ModBlocks.BRITTLE_ICE.get(), ModBlocks.SALT_ORE.get(), ModBlocks.DEEPSLATE_SALT_ORE.get(),
                ModBlocks.CONCRETE.get(), ModBlocks.GLAZED_TERRACOTTA.get(),
                ModBlocks.STONE_WALL.get(),
                ModBlocks.POLISHED_STONE.get(), ModBlocks.POLISHED_STONE_SLAB.get(), ModBlocks.POLISHED_STONE_STAIRS.get(), ModBlocks.POLISHED_STONE_WALL.get(),
                ModBlocks.ANDESITE_BRICKS.get(), ModBlocks.CRACKED_ANDESITE_BRICKS.get(), ModBlocks.ANDESITE_BRICK_SLAB.get(), ModBlocks.ANDESITE_BRICK_STAIRS.get(), ModBlocks.ANDESITE_BRICK_WALL.get(),
                ModBlocks.DIORITE_BRICKS.get(), ModBlocks.CRACKED_DIORITE_BRICKS.get(), ModBlocks.DIORITE_BRICK_SLAB.get(), ModBlocks.DIORITE_BRICK_STAIRS.get(), ModBlocks.DIORITE_BRICK_WALL.get(),
                ModBlocks.GRANITE_BRICKS.get(), ModBlocks.CRACKED_GRANITE_BRICKS.get(), ModBlocks.GRANITE_BRICK_SLAB.get(), ModBlocks.GRANITE_BRICK_STAIRS.get(), ModBlocks.GRANITE_BRICK_WALL.get(),
                ModBlocks.BLUE_ICE_BRICKS.get(), ModBlocks.CRACKED_BLUE_ICE_BRICKS.get(), ModBlocks.BLUE_ICE_BRICK_SLAB.get(), ModBlocks.BLUE_ICE_BRICK_STAIRS.get(), ModBlocks.BLUE_ICE_BRICK_WALL.get()
        );
        tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.PALM_CROWN.get(), ModBlocks.GLISTERING_MELON.get());
        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(ModBlocks.ASH_BLOCK.get(), ModBlocks.ASH.get(),
                ModBlocks.SILT.get(), ModBlocks.QUICKSAND.get(), ModBlocks.RED_QUICKSAND.get(),
                ModBlocks.CONCRETE_POWDER.get());

        tag(BlockTags.CAMEL_SAND_STEP_SOUND_BLOCKS).add(ModBlocks.CONCRETE_POWDER.get());

        tag(ModBlockTags.AZALEA_LOGS).add(ModBlocks.AZALEA_LOG.get(), ModBlocks.AZALEA_WOOD.get(), ModBlocks.STRIPPED_AZALEA_LOG.get(), ModBlocks.STRIPPED_AZALEA_WOOD.get());
        tag(ModBlockTags.PALM_LOGS).add(ModBlocks.PALM_LOG.get(), ModBlocks.PALM_WOOD.get(), ModBlocks.STRIPPED_PALM_LOG.get(), ModBlocks.STRIPPED_PALM_WOOD.get());
        tag(ModBlockTags.BAOBAB_LOGS).add(ModBlocks.BAOBAB_LOG.get(), ModBlocks.BAOBAB_WOOD.get(), ModBlocks.STRIPPED_BAOBAB_LOG.get(), ModBlocks.STRIPPED_BAOBAB_WOOD.get());
        tag(BlockTags.LEAVES).add(ModBlocks.PALM_LEAVES.get(), ModBlocks.BAOBAB_LEAVES.get());
        tag(BlockTags.SAPLINGS).add(ModBlocks.PALM_SAPLING.get(), ModBlocks.BAOBAB_SAPLING.get());
        tag(ModBlockTags.ICE_SKIP).add(ModBlocks.BRITTLE_ICE.get());
        tag(ModBlockTags.CATTAILS_MAY_PLACE).add(
                Blocks.DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.MYCELIUM, Blocks.COARSE_DIRT, Blocks.FARMLAND,
                Blocks.MUD, Blocks.CLAY, Blocks.GRAVEL, Blocks.SAND, Blocks.RED_SAND
        );
        tag(ModBlockTags.REEDS_MAY_PLACE).add(
                Blocks.DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.MYCELIUM, Blocks.COARSE_DIRT, Blocks.FARMLAND,
                Blocks.MUD, Blocks.CLAY, Blocks.GRAVEL, Blocks.SAND, Blocks.RED_SAND
        );
        tag(BlockTags.STAIRS).add(
                ModBlocks.ANDESITE_BRICK_STAIRS.get(), ModBlocks.DIORITE_BRICK_STAIRS.get(), ModBlocks.GRANITE_BRICK_STAIRS.get(),
                ModBlocks.BLUE_ICE_BRICK_STAIRS.get()
        );
        tag(BlockTags.SLABS).add(
                ModBlocks.ANDESITE_BRICK_SLAB.get(), ModBlocks.DIORITE_BRICK_SLAB.get(), ModBlocks.GRANITE_BRICK_SLAB.get(),
                ModBlocks.BLUE_ICE_BRICK_SLAB.get()
        );
        tag(BlockTags.WALLS).add(
                ModBlocks.STONE_WALL.get(), ModBlocks.POLISHED_STONE_WALL.get(),
                ModBlocks.ANDESITE_BRICK_WALL.get(), ModBlocks.DIORITE_BRICK_WALL.get(), ModBlocks.GRANITE_BRICK_WALL.get(),
                ModBlocks.BLUE_ICE_BRICK_WALL.get()
        );

        tag(BlockTags.WOOL).add(ModBlocks.WOOL.get());
        tag(BlockTags.WOOL_CARPETS).add(ModBlocks.CARPET.get());

        tag(BlockTags.CONVERTABLE_TO_MUD).add(ModBlocks.SILT.get());
        tag(BlockTags.MOSS_REPLACEABLE).add(ModBlocks.SILT.get());

        tag(BlockTags.AZALEA_GROWS_ON).add(ModBlocks.SILT.get());
        tag(BlockTags.AZALEA_ROOT_REPLACEABLE).add(ModBlocks.SILT.get());

        tag(BlockTags.REPLACEABLE).add(ModBlocks.ASH.get());

        tag(Tags.Blocks.GLAZED_TERRACOTTAS).add(ModBlocks.GLAZED_TERRACOTTA.get());
//        tag(Tags.Blocks.CONCRETES).add(ModBlocks.CONCRETE.get());
        tag(CONCRETE_POWDERS).add(ModBlocks.CONCRETE_POWDER.get());
//        tag(BlockTags.BANNERS).add(ModBlocks.BANNER.get(), ModBlocks.WALL_BANNER.get());
//        for(Block banner : ModDyedArray.BANNER_BLOCK) {
//            tag(BANNER).add(banner);
//        }
        for(Block wallBanner : ModDyedArray.WALL_BANNER_BLOCK) {
            tag(WALL_BANNER).add(wallBanner);
        }
        DyedBlockMap.getDyedBlock("CONCRETE_POWDER").forEach((color, block) -> tag(CONCRETE_POWDERS).add(block));

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

    public static TagKey<Block> createCTag(String tagName) {

        return BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", tagName));
    }

    @SafeVarargs
    public final <T extends Block> void add(TagKey<Block> tag, T... blocks) {
        tag(tag).add(blocks);
    }
}
