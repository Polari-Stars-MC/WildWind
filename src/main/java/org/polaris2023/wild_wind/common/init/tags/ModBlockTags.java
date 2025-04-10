package org.polaris2023.wild_wind.common.init.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.Locale;
import java.util.function.Supplier;

import static org.polaris2023.wild_wind.util.Helpers.ctags;


public enum ModBlockTags implements Supplier<TagKey<Block>> {
    FIREFLY_ROOST_BLOCK,
    AZALEA_LOGS,
    PALM_LOGS,
    BAOBAB_LOGS,
    ICE_SKIP,
    CATTAILS_MAY_PLACE,
    REEDS_MAY_PLACE
    ;
    final TagKey<Block> tag;
    ModBlockTags() {
        tag = ctags(Registries.BLOCK, name().toLowerCase(Locale.ROOT));
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
