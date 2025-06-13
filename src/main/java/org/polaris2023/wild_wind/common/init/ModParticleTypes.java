package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

import static org.polaris2023.wild_wind.WildWindMod.MOD_ID;

public class ModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTER = DeferredRegister.create(Registries.PARTICLE_TYPE, MOD_ID);
}
