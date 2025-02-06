package org.polaris2023.wild_wind.datagen.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.Nullable;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.init.tags.ModBlockTags;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, WildWindMod.MOD_ID, existingFileHelper);
    }


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
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.BRITTLE_ICE.get(), ModBlocks.SALT_ORE.get());

    }


    @SafeVarargs
    public final <T extends Block> void add(TagKey<Block> tag, T... blocks) {
        tag(tag).add(blocks);
    }
}
