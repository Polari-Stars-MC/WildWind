package org.polaris_bear.wild_wind.common.init.tags;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import org.polaris_bear.wild_wind.util.Helpers;

import java.util.Locale;
import java.util.function.Supplier;

public enum ModBiomeTags implements Supplier<TagKey<Biome>> {
    ;

    final TagKey<Biome> tag;
    ModBiomeTags() {
        tag = Helpers.biomeTags(name().toLowerCase(Locale.ROOT));
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
