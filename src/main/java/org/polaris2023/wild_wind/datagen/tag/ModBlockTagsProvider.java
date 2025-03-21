package org.polaris2023.wild_wind.datagen.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.dyed.DyedBlockMap;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.init.tags.ModBlockTags;
import org.polaris2023.wild_wind.datagen.ModBlockFamilies;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, WildWindMod.MOD_ID, existingFileHelper);
    }

    public static final TagKey<Block> CONCRETE_POWDERS = create("concrete_powders");


    protected IntrinsicTagAppender<Block> tag(Supplier<TagKey<Block>> tag) {
        return super.tag(tag.get());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        var firefly_roost = tag(ModBlockTags.FIREFLY_ROOST_BLOCK.get());
        firefly_roost.add(Blocks.TALL_GRASS, Blocks.SHORT_GRASS,
                Blocks.FERN, Blocks.LARGE_FERN,
                Blocks.POTTED_FERN, Blocks.MANGROVE_PROPAGULE,
                Blocks.POTTED_MANGROVE_PROPAGULE);
        firefly_roost.addTag(BlockTags.FLOWERS);
        tag(BlockTags.SNOW_LAYER_CANNOT_SURVIVE_ON).add(ModBlocks.BRITTLE_ICE.get());

        //Mineable
        tag(BlockTags.SWORD_EFFICIENT).add(ModBlocks.GLISTERING_MELON.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.BRITTLE_ICE.get(), ModBlocks.SALT_ORE.get(), ModBlocks.DEEPSLATE_SALT_ORE.get(),
                ModBlocks.CONCRETE.get(), ModBlocks.GLAZED_TERRACOTTA.get());
        tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.PALM_CROWN.get(), ModBlocks.GLISTERING_MELON.get());
        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(ModBlocks.ASH_BLOCK.get(), ModBlocks.ASH.get(),
                ModBlocks.SILT.get(), ModBlocks.QUICKSAND.get(), ModBlocks.RED_QUICKSAND.get(),
                ModBlocks.CONCRETE_POWDER.get());

        tag(BlockTags.CAMEL_SAND_STEP_SOUND_BLOCKS).add(ModBlocks.CONCRETE_POWDER.get());

        tag(ModBlockTags.AZALEA_LOGS).add(ModBlocks.AZALEA_LOG.get(), ModBlocks.AZALEA_WOOD.get(), ModBlocks.STRIPPED_AZALEA_LOG.get(), ModBlocks.STRIPPED_AZALEA_WOOD.get());
        tag(ModBlockTags.PALM_LOGS).add(ModBlocks.PALM_LOG.get(), ModBlocks.PALM_WOOD.get(), ModBlocks.STRIPPED_PALM_LOG.get(), ModBlocks.STRIPPED_PALM_WOOD.get());
        tag(ModBlockTags.BAOBAB_LOGS).add(ModBlocks.BAOBAB_LOG.get(), ModBlocks.BAOBAB_WOOD.get(), ModBlocks.STRIPPED_BAOBAB_LOG.get(), ModBlocks.STRIPPED_BAOBAB_WOOD.get());
        tag(BlockTags.LOGS_THAT_BURN).addTag(ModBlockTags.AZALEA_LOGS.get()).addTag(ModBlockTags.PALM_LOGS.get()).addTag(ModBlockTags.BAOBAB_LOGS.get());
        tag(BlockTags.PLANKS).add(ModBlocks.AZALEA_PLANKS.get(), ModBlocks.PALM_PLANKS.get(), ModBlocks.BAOBAB_PLANKS.get());
        tag(BlockTags.LEAVES).add(ModBlocks.PALM_LEAVES.get(), ModBlocks.BAOBAB_LEAVES.get());
        tag(BlockTags.SAPLINGS).add(ModBlocks.PALM_SAPLING.get(), ModBlocks.BAOBAB_SAPLING.get());
        tag(ModBlockTags.ICE_SKIP).add(ModBlocks.BRITTLE_ICE.get());
        tag(BlockTags.WALLS).add(ModBlocks.STONE_WALL.get(), ModBlocks.POLISHED_STONE_WALL.get());

        tag(BlockTags.WOOL).add(ModBlocks.WOOL.get());
        tag(BlockTags.WOOL_CARPETS).add(ModBlocks.CARPET.get());

        tag(BlockTags.CONVERTABLE_TO_MUD).add(ModBlocks.SILT.get());
        tag(BlockTags.MOSS_REPLACEABLE).add(ModBlocks.SILT.get());

        tag(BlockTags.ENDERMAN_HOLDABLE).add(ModBlocks.GLISTERING_MELON.get());
        tag(BlockTags.AZALEA_GROWS_ON).add(ModBlocks.SILT.get());
        tag(BlockTags.AZALEA_ROOT_REPLACEABLE).add(ModBlocks.SILT.get());

        tag(BlockTags.REPLACEABLE).add(ModBlocks.ASH.get());

        tag(Tags.Blocks.GLAZED_TERRACOTTAS).add(ModBlocks.GLAZED_TERRACOTTA.get());
        tag(Tags.Blocks.CONCRETES).add(ModBlocks.CONCRETE.get());
        tag(CONCRETE_POWDERS).add(ModBlocks.CONCRETE_POWDER.get());
        tag(BlockTags.BANNERS).add(ModBlocks.BANNER.get(), ModBlocks.WALL_BANNER.get());
        DyedBlockMap.getDyedBlock("CONCRETE_POWDER").forEach((color, block) -> tag(CONCRETE_POWDERS).add(block));

        ModBlockFamilies.AZALEA_PLANKS.generateBlockTags(this::tag);
        ModBlockFamilies.PALM_PLANKS.generateBlockTags(this::tag);
        ModBlockFamilies.BAOBAB_PLANKS.generateBlockTags(this::tag);
    }

    public static TagKey<Block> create(String tagName) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", tagName));
    }

    @SafeVarargs
    public final <T extends Block> void add(TagKey<Block> tag, T... blocks) {
        tag(tag).add(blocks);
    }
}
