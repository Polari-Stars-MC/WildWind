package org.polaris2023.wild_wind.common.init.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Instrument;

import java.util.Locale;
import java.util.function.Supplier;

import static org.polaris2023.wild_wind.util.Helpers.tags;

public enum ModInstrumentTags implements Supplier<TagKey<Instrument>> {
    MAGIC_FLUTE;

    final TagKey<Instrument> tag;
    ModInstrumentTags() {
        tag = tags(Registries.INSTRUMENT, name().toLowerCase(Locale.ROOT));
    }

    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public TagKey<Instrument> get() {
        return tag;
    }
}
