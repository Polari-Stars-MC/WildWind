package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.particles.FluffyDandelionParticle;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> REGISTER = DeferredRegister.create(Registries.PARTICLE_TYPE, WildWindMod.MOD_ID);

    public static final DeferredHolder<ParticleType<?>,ParticleType<FluffyDandelionParticle.Data>> FLUFFY_DANDELION =
            REGISTER.register("fluffy_dandelion", FluffyDandelionParticle.Data::new);
}
