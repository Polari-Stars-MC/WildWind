package org.polaris_bear.wild_wind.datagen.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.polaris_bear.wild_wind.WildWindMod;
import org.polaris_bear.wild_wind.common.init.tags.ModBlockTags;

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
        IntrinsicTagAppender<Block> tag = tag(ModBlockTags.FIREFLY_ROOST_BLOCK.get());
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
