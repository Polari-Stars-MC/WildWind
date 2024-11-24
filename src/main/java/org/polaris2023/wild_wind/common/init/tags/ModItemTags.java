package org.polaris2023.wild_wind.common.init.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.Locale;
import java.util.function.Supplier;

import static org.polaris2023.wild_wind.util.Helpers.tags;

public enum ModItemTags implements Supplier<TagKey<Item>> {
    FIREFLY_FOOD,
    MEAT_FOOD,
    VEGETABLE_FOOD,
    FRUIT_FOOD,
    PROTEIN_FOOD,
    FISH_FOOD,
    MONSTER_FOOD,
    ;
    final TagKey<Item> tag;
    ModItemTags() {
        tag = tags(Registries.ITEM, name().toLowerCase(Locale.ROOT));
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
