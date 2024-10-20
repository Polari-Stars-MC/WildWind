package org.polaris_bear.wild_wind.datagen.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.polaris_bear.wild_wind.WildWindMod;
import org.polaris_bear.wild_wind.common.init.tags.ModBlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, WildWindMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        IntrinsicTagAppender<Block> tag = tag(ModBlockTags.FIREFLY_ROOST_BLOCK);
        tag.add(
                Blocks.TALL_GRASS,
                Blocks.SHORT_GRASS,
                Blocks.FERN,
                Blocks.LARGE_FERN,
                Blocks.POTTED_FERN,
                Blocks.MANGROVE_PROPAGULE,
                Blocks.POTTED_MANGROVE_PROPAGULE
        );
        tag.addTag(BlockTags.FLOWERS);
    }
}
