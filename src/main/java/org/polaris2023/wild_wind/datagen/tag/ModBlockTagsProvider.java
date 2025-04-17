package org.polaris2023.wild_wind.datagen.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.polaris2023.annotation.enums.TagType;
import org.polaris2023.annotation.handler.TagHandler;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.dyed.DyedBlockMap;
import org.polaris2023.wild_wind.common.init.tags.ModBlockTags;
import org.polaris2023.wild_wind.util.data.ModBlockFamilies;
import org.polaris2023.wild_wind.util.Helpers;

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
