package org.polaris2023.wild_wind.common.init.tags;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.Locale;
import java.util.function.Supplier;

import static org.polaris2023.wild_wind.util.Helpers.enchantmentTags;

public enum ModEnchantmentTags implements Supplier<TagKey<Enchantment>> {
    ;
    final TagKey<Enchantment> tag;
    ModEnchantmentTags() {
        tag = enchantmentTags(name().toLowerCase(Locale.ROOT));
    }

    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public TagKey<Enchantment> get() {
        return tag;
    }
}
