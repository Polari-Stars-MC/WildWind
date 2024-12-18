package org.polaris2023.wild_wind.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinition;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.wild_wind.common.init.ModInitializer;

import static org.polaris2023.wild_wind.WildWindMod.MOD_ID;

public class ModSoundDefinitionsProvider extends SoundDefinitionsProvider {
    /**
     * Creates a new instance of this data provider.
     *
     * @param output The {@linkplain PackOutput} instance provided by the data generator.
     * @param helper The existing file helper provided by the event you are initializing this provider in.
     */
    protected ModSoundDefinitionsProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, MOD_ID, helper);
    }

    /**
     * Registers the sound definitions that should be generated via one of the {@code add} methods.
     */
    @Override
    public void registerSounds() {
        for (DeferredHolder<SoundEvent, ? extends SoundEvent> sound : ModInitializer.sounds()) {
            SoundEvent soundEvent = sound.get();
            add(soundEvent, SoundDefinition.definition()
                    .with(sound(soundEvent.getLocation().toString(), SoundDefinition.SoundType.SOUND)
                            .volume(1F)
                            .pitch(1F)
                            .weight(2)
                            .attenuationDistance(8)
                            .stream()
                            .preload()
                    )
                    .subtitle("sound." + soundEvent.getLocation().toString().replace(":", "."))
                    .replace(true));
        }

    }
}
