package org.polaris_bear.wild_wind.common.init.tags;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

import java.util.Locale;
import java.util.function.Supplier;

import static org.polaris_bear.wild_wind.util.Helpers.entityTags;

public enum ModEntityTypeTags implements Supplier<TagKey<EntityType<?>>> {
    ;
    final TagKey<EntityType<?>> tag;
    ModEntityTypeTags() {
        tag = entityTags(name().toLowerCase(Locale.ROOT));
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
