package org.polaris2023.wild_wind.common.init.tags;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import org.polaris2023.wild_wind.util.Helpers;

import java.util.Locale;
import java.util.function.Supplier;

public enum ModFluidTags implements Supplier<TagKey<Fluid>> {
    ;
    final TagKey<Fluid> tag;
    ModFluidTags() {
        tag = Helpers.fluidTags(name().toLowerCase(Locale.ROOT));
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
