package org.polaris2023.wild_wind.common.init.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

import java.util.Locale;
import java.util.function.Supplier;

import static org.polaris2023.wild_wind.util.Helpers.ctags;
import static org.polaris2023.wild_wind.util.Helpers.tags;

public enum ModEntityTypeTags implements Supplier<TagKey<EntityType<?>>> {
    ;
    final TagKey<EntityType<?>> tag;
    ModEntityTypeTags() {
        tag = ctags(Registries.ENTITY_TYPE, name().toLowerCase(Locale.ROOT));
    }

    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public TagKey<EntityType<?>> get() {
        return tag;
    }
}
