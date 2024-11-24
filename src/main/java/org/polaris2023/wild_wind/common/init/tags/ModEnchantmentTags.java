package org.polaris2023.wild_wind.common.init.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.Locale;
import java.util.function.Supplier;

import static org.polaris2023.wild_wind.util.Helpers.ctags;
import static org.polaris2023.wild_wind.util.Helpers.tags;

public enum ModEnchantmentTags implements Supplier<TagKey<Enchantment>> {
    ;
    final TagKey<Enchantment> tag;
    ModEnchantmentTags() {
        tag = ctags(Registries.ENCHANTMENT, name().toLowerCase(Locale.ROOT));
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
