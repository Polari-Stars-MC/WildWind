package org.polaris2023.wild_wind.common.init.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

import java.util.Locale;
import java.util.function.Supplier;

import static org.polaris2023.wild_wind.util.Helpers.ctags;
import static org.polaris2023.wild_wind.util.Helpers.tags;

public enum ModBiomeTags implements Supplier<TagKey<Biome>> {
    ;

    final TagKey<Biome> tag;
    ModBiomeTags() {
        tag = ctags(Registries.BIOME, name().toLowerCase(Locale.ROOT));
    }

    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public TagKey<Biome> get() {
        return tag;
    }
}
