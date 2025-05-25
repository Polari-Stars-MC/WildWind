package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.polaris2023.wild_wind.WildWindMod;

public class ModDamageType {
    public static final ResourceKey<DamageType> QUICKSAND_DAMAGE = create("quicksand_damage");
    public static final ResourceKey<DamageType> SILT_DAMAGE = create("silt_damage");
    public static final ResourceKey<DamageType> ICE_SPIKES_DAMAGE = create("ice_spikes_damage");
    public static final ResourceKey<DamageType> ICICLE_DAMAGE = create("icicle_damage");

    public static ResourceKey<DamageType> create(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(WildWindMod.MOD_ID, name));
    }

    public static DamageSource causeQuicksandDamage(LivingEntity living) {
        return new DamageSource(living.level().registryAccess().holderOrThrow(QUICKSAND_DAMAGE), living);
    }

    public static DamageSource causeSiltDamage(LivingEntity living) {
        return new DamageSource(living.level().registryAccess().holderOrThrow(SILT_DAMAGE), living);
    }

    public static DamageSource causeIceSpikesDamage(Level level) {
        return level.damageSources().source(ICE_SPIKES_DAMAGE);
    }

    public static DamageSource fallingIcicle(Entity entity) {
        return entity.level().damageSources().source(ICICLE_DAMAGE, entity);
    }

    public static void bootstrap(BootstrapContext<DamageType> context) {
        context.register(QUICKSAND_DAMAGE, new DamageType("wild_wind.quicksand_damage", 0.0F));
        context.register(SILT_DAMAGE, new DamageType("wild_wind.silt_damage", 0.0F));
        context.register(ICE_SPIKES_DAMAGE, new DamageType("wild_wind.ice_spikes_damage", 0.0F));
        context.register(ICICLE_DAMAGE, new DamageType("wild_wind.icicle_damage", 0.0F));
    }
}
