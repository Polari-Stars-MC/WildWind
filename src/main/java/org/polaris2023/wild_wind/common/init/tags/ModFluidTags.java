package org.polaris2023.wild_wind.common.init.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

import java.util.Locale;
import java.util.function.Supplier;

import static org.polaris2023.wild_wind.util.Helpers.ctags;
import static org.polaris2023.wild_wind.util.Helpers.tags;

public enum ModFluidTags implements Supplier<TagKey<Fluid>> {
    ;
    final TagKey<Fluid> tag;
    ModFluidTags() {
        tag = ctags(Registries.FLUID, name().toLowerCase(Locale.ROOT));
    }


    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public TagKey<Fluid> get() {
        return tag;
    }
}
