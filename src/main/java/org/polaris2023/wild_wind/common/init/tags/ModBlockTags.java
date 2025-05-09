package org.polaris2023.wild_wind.common.init.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import org.polaris2023.annotation.enums.TagType;
import org.polaris2023.annotation.tag.VanillaTag;

import java.util.Locale;
import java.util.function.Supplier;

import static org.polaris2023.wild_wind.util.Helpers.ctags;
import static org.polaris2023.wild_wind.util.Helpers.tags;


public enum ModBlockTags implements Supplier<TagKey<Block>> {
    FIREFLY_ROOST_BLOCK,
    @VanillaTag(names = "logs_that_burn", type = TagType.Block, tag = true)
    AZALEA_LOGS,
    @VanillaTag(names = "logs_that_burn", type = TagType.Block, tag = true)
    PALM_LOGS,
    @VanillaTag(names = "logs_that_burn", type = TagType.Block, tag = true)
    BAOBAB_LOGS,
    ICE_SKIP,
    CATTAILS_MAY_PLACE,
    REEDS_MAY_PLACE,
    CONCRETE_POWDERS,
    BANNERS(true),
    WALL_BANNERS(true)
    ;
    final TagKey<Block> tag;
    ModBlockTags() {
        this(false);
    }
    ModBlockTags(boolean isMod) {
        if (isMod) {
            tag = tags(Registries.BLOCK, name().toLowerCase(Locale.ROOT));
        } else {
            tag = ctags(Registries.BLOCK, name().toLowerCase(Locale.ROOT));
        }
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
