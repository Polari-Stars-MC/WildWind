package org.polaris2023.wild_wind.common.init.tags;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.polaris2023.wild_wind.util.Helpers;

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
