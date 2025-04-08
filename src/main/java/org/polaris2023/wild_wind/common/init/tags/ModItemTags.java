package org.polaris2023.wild_wind.common.init.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.Locale;
import java.util.function.Supplier;

import static org.polaris2023.wild_wind.util.Helpers.ctags;

public enum ModItemTags implements Supplier<TagKey<Item>> {
    FIREFLY_FOOD,
    MEAT_FOOD,
    VEGETABLE_FOOD,
    FRUIT_FOOD,
    PROTEIN_FOOD,
    FISH_FOOD,
    MONSTER_FOOD,
    AZALEA_LOGS,
    PALM_LOGS,
    BAOBAB_LOGS,
    WILD_WIND_INVISIBLE,
    VEGETABLE_COMPONENT_OP5F,
    VEGETABLE_COMPONENT_1F,
    SEEDS_COMPONENT_FOOD,
    POISON_SEEDS_COMPONENT_SEED,
    EGG_COMPONENT_FOOD,
    MEAT_COMPONENT_OP5F,
    MEAT_COMPONENT_1F,
    FISH_COMPONENT_1F,
    MONSTER_COMPONENT_1F,
    FRUIT_COMPONENT_1F,
    FRUIT_COMPONENT_0P5F,
    PROTEIN_COMPONENT_1F,
    SWEET_COMPONENT_1F,
    ;
    final TagKey<Item> tag;
    ModItemTags() {
        tag = ctags(Registries.ITEM, name().toLowerCase(Locale.ROOT));

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
