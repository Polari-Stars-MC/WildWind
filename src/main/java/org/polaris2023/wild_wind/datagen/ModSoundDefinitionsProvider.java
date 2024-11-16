package org.polaris2023.wild_wind.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

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

    }
}
