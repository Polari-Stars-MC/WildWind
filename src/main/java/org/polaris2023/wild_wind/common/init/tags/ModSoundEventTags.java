package org.polaris2023.wild_wind.common.init.tags;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;

import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.polaris2023.wild_wind.util.Helpers.ctags;

/**
 * @author : baka4n
 * {@code @Date : 2025/05/05 02:26:38}
 */
public enum ModSoundEventTags implements Supplier<TagKey<SoundEvent>> {
    GLARE_AMBIENT,
    GLARE_DEATH,
    ;
    private final TagKey<SoundEvent> tag;
    ModSoundEventTags() {
        tag = ctags(Registries.SOUND_EVENT, name().toLowerCase(Locale.ROOT));
    }

    public void tagFor(Consumer<HolderSet.Named<SoundEvent>> action) {
        BuiltInRegistries.SOUND_EVENT.getTag(tag).ifPresent(action);
    }

    @Override
    public TagKey<SoundEvent> get() {
        return tag;
    }
}
