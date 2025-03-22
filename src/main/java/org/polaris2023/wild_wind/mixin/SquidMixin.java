package org.polaris2023.wild_wind.mixin;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;
import org.polaris2023.wild_wind.util.interfaces.ISquid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Squid.class)
public class SquidMixin extends WaterAnimal implements ISquid {
    @Unique
    private static final EntityDataAccessor<Boolean> DATA_SQUID_CONVERSION_ID;

    @Unique
    private int wild_wind$conversionTime = 300;

    protected SquidMixin(EntityType<? extends WaterAnimal> entityType, Level level) {
        super(entityType, level);
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_SQUID_CONVERSION_ID, false);
    }

    @Unique
    public boolean wild_wind$isGlowingConverting() {
        return this.getEntityData().get(DATA_SQUID_CONVERSION_ID);
    }

    @Unique
    public void wild_wind$setGlowingConverting(boolean isGlowing) {
        this.entityData.set(DATA_SQUID_CONVERSION_ID, isGlowing);
    }

    public void tick() {
        if (!this.level().isClientSide && this.isAlive() && !this.isNoAi()) {
            if (this.wild_wind$isGlowingConverting()) {
                --this.wild_wind$conversionTime;
                if (this.wild_wind$conversionTime < 0) {
                    this.wild_wind$doGlowingConversion();
                }
            }
        }

        super.tick();
    }

    @Inject(method = "aiStep", at = @At("TAIL"))
    public void aiStep(CallbackInfo ci) {
        if(this.wild_wind$isGlowingConverting()) {
            this.level().addParticle(ParticleTypes.GLOW, this.getRandomX(0.6), this.getRandomY(), this.getRandomZ(0.6), (double)0.0F, (double)0.0F, (double)0.0F);
        }
    }

    @Unique
    protected void wild_wind$doGlowingConversion() {
        if (EventHooks.canLivingConvert(this, EntityType.GLOW_SQUID, (timer) -> this.wild_wind$conversionTime = timer)) {
            GlowSquid glowSquid = this.convertTo(EntityType.GLOW_SQUID, true);
            if (glowSquid != null) {
                EventHooks.onLivingConvert(this, glowSquid);
            }

            if (!this.isSilent()) {
                this.level().playSound((Player)null, this.blockPosition(), SoundEvents.GLOW_INK_SAC_USE, SoundSource.NEUTRAL);
            }

        }
    }

    public boolean canFreeze() {
        return false;
    }

    @Unique
    public boolean wild_wind$isShaking() {
        return this.wild_wind$isGlowingConverting();
    }

    static {
        DATA_SQUID_CONVERSION_ID = SynchedEntityData.defineId(SquidMixin.class, EntityDataSerializers.BOOLEAN);
    }
}
