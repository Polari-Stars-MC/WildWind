package org.polaris2023.wild_wind.datagen.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModSounds;
import org.polaris2023.wild_wind.common.init.tags.ModSoundEventTags;

import java.util.concurrent.CompletableFuture;

/**
 * @author : baka4n
 * {@code @Date : 2025/05/05 02:28:51}
 */
public class ModSoundEventTagsProvider extends TagsProvider<SoundEvent> {


    public ModSoundEventTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, Registries.SOUND_EVENT, lookupProvider, WildWindMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModSoundEventTags.GLARE_DEATH.get())
                .add(
                        ModSounds.GLARE_DEATH_1.holder.getKey(),
                        ModSounds.GLARE_DEATH_2.holder.getKey(),
                        ModSounds.GLARE_DEATH_3.holder.getKey()
                );
        tag(ModSoundEventTags.GLARE_AMBIENT.get())
                .add(
                        ModSounds.GLARE_AMBIENT_1.holder.getKey(),
                        ModSounds.GLARE_AMBIENT_2.holder.getKey(),
                        ModSounds.GLARE_AMBIENT_3.holder.getKey(),
                        ModSounds.GLARE_AMBIENT_4.holder.getKey(),
                        ModSounds.GLARE_AMBIENT_5.holder.getKey(),
                        ModSounds.GLARE_AMBIENT_6.holder.getKey(),
                        ModSounds.GLARE_AMBIENT_7.holder.getKey(),
                        ModSounds.GLARE_AMBIENT_8.holder.getKey(),
                        ModSounds.GLARE_AMBIENT_9.holder.getKey(),
                        ModSounds.GLARE_AMBIENT_10.holder.getKey(),
                        ModSounds.GLARE_AMBIENT_11.holder.getKey(),
                        ModSounds.GLARE_AMBIENT_12.holder.getKey(),
                        ModSounds.GLARE_AMBIENT_13.holder.getKey()
                );
    }
}
