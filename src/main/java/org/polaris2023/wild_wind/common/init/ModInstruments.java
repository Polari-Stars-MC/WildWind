package org.polaris2023.wild_wind.common.init;

import net.minecraft.world.item.Instrument;
import net.neoforged.neoforge.registries.DeferredHolder;

import static org.polaris2023.wild_wind.common.init.ModInitializer.INSTRUMENTS;

public class ModInstruments {
	public static final DeferredHolder<Instrument, Instrument> MAGIC_FLUTE =
			INSTRUMENTS.register("magic_flute", () -> new Instrument(ModSounds.MAGIC_FLUTE.getHolder(), 60, 32.0F));
}
