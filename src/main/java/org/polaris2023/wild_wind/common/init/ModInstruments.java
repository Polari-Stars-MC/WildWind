package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Instrument;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static org.polaris2023.wild_wind.WildWindMod.MOD_ID;

public class ModInstruments {

	public static final DeferredRegister<Instrument> INSTRUMENTS = DeferredRegister.create(BuiltInRegistries.INSTRUMENT, MOD_ID);

	public static final DeferredHolder<Instrument, Instrument> MAGIC_FLUTE = INSTRUMENTS.register("magic_flute", () -> new Instrument(ModSounds.MAGIC_FLUTE.getDelegate(), 60, 32.0F));

}