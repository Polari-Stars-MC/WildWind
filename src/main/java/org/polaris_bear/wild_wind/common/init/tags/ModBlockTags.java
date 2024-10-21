package org.polaris_bear.wild_wind.common.init.tags;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import org.polaris_bear.wild_wind.util.Helpers;

import java.util.Locale;
import java.util.function.Supplier;

import static org.polaris_bear.wild_wind.util.Helpers.blockTags;

public enum ModBlockTags implements Supplier<TagKey<Block>> {
    FIREFLY_ROOST_BLOCK;
    final TagKey<Block> tag;
    ModBlockTags() {
        tag = blockTags(name().toLowerCase(Locale.ROOT));
    }

    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public TagKey<Block> get() {
        return tag;
    }
}
