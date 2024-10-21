package org.polaris_bear.wild_wind.common.init.tags;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.polaris_bear.wild_wind.util.Helpers;

import java.util.Locale;
import java.util.function.Supplier;

public enum ModItemTags implements Supplier<TagKey<Item>> {
    FIREFLY_FOOD;
    final TagKey<Item> tag;
    ModItemTags() {
        tag = Helpers.itemTags(name().toLowerCase(Locale.ROOT));
    }

    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public TagKey<Item> get() {
        return tag;
    }
}
