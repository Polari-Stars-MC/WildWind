package org.polaris2023.wild_wind.common.entity.potion;

import com.google.common.collect.Maps;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.PushReaction;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class AbstractAreaEffectCloud extends Entity implements TraceableEntity {
	private static final EntityDataAccessor<Float> DATA_RADIUS = SynchedEntityData.defineId(AbstractAreaEffectCloud.class, EntityDataSerializers.FLOAT);
	private static final EntityDataAccessor<Boolean> DATA_WAITING = SynchedEntityData.defineId(AbstractAreaEffectCloud.class, EntityDataSerializers.BOOLEAN);
	private final Map<Entity, Integer> victims = Maps.newHashMap();
	private int duration = 600;
	private int waitTime = 20;
	private int reapplicationDelay = 20;
	private int durationOnUse;
	private float radiusOnUse;
	private float radiusPerTick;
	@Nullable
	private LivingEntity owner;
	@Nullable
	private UUID ownerUUID;

	public AbstractAreaEffectCloud(EntityType<? extends AbstractAreaEffectCloud> entityType, Level level) {
		super(entityType, level);
		this.noPhysics = true;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		builder.define(DATA_RADIUS, 3.0F);
		builder.define(DATA_WAITING, false);
	}

	public void setRadius(float radius) {
		if (!this.level().isClientSide) {
			this.getEntityData().set(DATA_RADIUS, Mth.clamp(radius, 0.0F, 32.0F));
		}
	}

	@Override
	public void refreshDimensions() {
		double d0 = this.getX();
		double d1 = this.getY();
		double d2 = this.getZ();
		super.refreshDimensions();
		this.setPos(d0, d1, d2);
	}

	public float getRadius() {
		return this.getEntityData().get(DATA_RADIUS);
	}

	protected void setWaiting(boolean waiting) {
		this.getEntityData().set(DATA_WAITING, waiting);
	}

	public boolean isWaiting() {
		return this.getEntityData().get(DATA_WAITING);
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public void tick() {
		super.tick();
		boolean waiting = this.isWaiting();
		float radius = this.getRadius();
		if (this.level().isClientSide) {
			if (waiting && this.random.nextBoolean()) {
				return;
			}

			for (int i = 0; i < 2; i++) {
				// box-muller
				float theta = this.random.nextFloat() * Mth.TWO_PI;
				float r = Mth.sqrt(this.random.nextFloat()) * (waiting ? 0.2F : radius);
				float x = Mth.cos(theta) * r;
				float z = Mth.sin(theta) * r;
				this.level().addAlwaysVisibleParticle(
						this.getParticleType(),
						this.getX() + (double)x, this.getY(), this.getZ() + (double)z,
						0.1D * this.random.nextDouble() - 0.05D,
						0.05D + 0.05D * this.random.nextDouble(),
						0.1D * this.random.nextDouble() - 0.05D
				);
			}
		} else {
			if (this.tickCount >= this.waitTime + this.duration) {
				this.discard();
				return;
			}

			boolean newStatus = this.tickCount < this.waitTime;
			if (waiting != newStatus) {
				this.setWaiting(newStatus);
			}

			if (newStatus) {
				return;
			}

			if (this.radiusPerTick != 0.0F) {
				radius += this.radiusPerTick;
				if (radius < 0.5F) {
					this.discard();
					return;
				}

				this.setRadius(radius);
			}

			if (this.tickCount % 5 == 0) {
				this.victims.entrySet().removeIf(entry -> this.tickCount >= entry.getValue());
				List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox());
				if (!entities.isEmpty()) {
					for (LivingEntity livingEntity : entities) {
						if (!this.victims.containsKey(livingEntity) && livingEntity.isAffectedByPotions()) {
							double xDiff = livingEntity.getX() - this.getX();
							double zDiff = livingEntity.getZ() - this.getZ();
							double diffSqr = xDiff * xDiff + zDiff * zDiff;
							if (diffSqr <= (double)(radius * radius)) {
								this.victims.put(livingEntity, this.tickCount + this.reapplicationDelay);

								this.applyEffectOn(livingEntity);

								if (this.radiusOnUse != 0.0F) {
									radius += this.radiusOnUse;
									if (radius < 0.5F) {
										this.discard();
										return;
									}

									this.setRadius(radius);
								}

								if (this.durationOnUse != 0) {
									this.duration = this.duration + this.durationOnUse;
									if (this.duration <= 0) {
										this.discard();
										return;
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public float getRadiusOnUse() {
		return this.radiusOnUse;
	}

	public void setRadiusOnUse(float radiusOnUse) {
		this.radiusOnUse = radiusOnUse;
	}

	public float getRadiusPerTick() {
		return this.radiusPerTick;
	}

	public void setRadiusPerTick(float radiusPerTick) {
		this.radiusPerTick = radiusPerTick;
	}

	public int getDurationOnUse() {
		return this.durationOnUse;
	}

	public void setDurationOnUse(int durationOnUse) {
		this.durationOnUse = durationOnUse;
	}

	public int getWaitTime() {
		return this.waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public void setOwner(@Nullable LivingEntity owner) {
		this.owner = owner;
		this.ownerUUID = owner == null ? null : owner.getUUID();
	}

	@Nullable
	public LivingEntity getOwner() {
		if (this.owner == null && this.ownerUUID != null && this.level() instanceof ServerLevel) {
			Entity entity = ((ServerLevel)this.level()).getEntity(this.ownerUUID);
			if (entity instanceof LivingEntity) {
				this.owner = (LivingEntity)entity;
			}
		}

		return this.owner;
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		this.tickCount = compound.getInt("Age");
		this.duration = compound.getInt("Duration");
		this.waitTime = compound.getInt("WaitTime");
		this.reapplicationDelay = compound.getInt("ReapplicationDelay");
		this.durationOnUse = compound.getInt("DurationOnUse");
		this.radiusOnUse = compound.getFloat("RadiusOnUse");
		this.radiusPerTick = compound.getFloat("RadiusPerTick");
		this.setRadius(compound.getFloat("Radius"));
		if (compound.hasUUID("Owner")) {
			this.ownerUUID = compound.getUUID("Owner");
		}
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
		compound.putInt("Age", this.tickCount);
		compound.putInt("Duration", this.duration);
		compound.putInt("WaitTime", this.waitTime);
		compound.putInt("ReapplicationDelay", this.reapplicationDelay);
		compound.putInt("DurationOnUse", this.durationOnUse);
		compound.putFloat("RadiusOnUse", this.radiusOnUse);
		compound.putFloat("RadiusPerTick", this.radiusPerTick);
		compound.putFloat("Radius", this.getRadius());
		if (this.ownerUUID != null) {
			compound.putUUID("Owner", this.ownerUUID);
		}
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
		if (DATA_RADIUS.equals(key)) {
			this.refreshDimensions();
		}

		super.onSyncedDataUpdated(key);
	}

	@Override
	public PushReaction getPistonPushReaction() {
		return PushReaction.IGNORE;
	}

	@Override
	public EntityDimensions getDimensions(Pose pose) {
		return EntityDimensions.scalable(this.getRadius() * 2.0F, 0.5F);
	}

	protected abstract void applyEffectOn(LivingEntity livingEntity);
	protected abstract ColorParticleOption getParticleType();
}
