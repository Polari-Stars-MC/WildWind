package org.polaris2023.wild_wind.common.entity.potion;

import it.unimi.dsi.fastutil.doubles.DoubleDoubleImmutablePair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;

import javax.annotation.Nullable;
import java.util.List;

public abstract class AbstractThrowableBottle extends ThrowableItemProjectile implements ItemSupplier {
	public AbstractThrowableBottle(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected double getDefaultGravity() {
		return 0.05D;
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		super.onHitBlock(result);
		if (!this.level().isClientSide) {
			Direction direction = result.getDirection();
			BlockPos blockpos = result.getBlockPos();
			BlockPos relative = blockpos.relative(direction);

			this.dowseFire(relative);
			this.dowseFire(relative.relative(direction.getOpposite()));

			for (Direction direction1 : Direction.Plane.HORIZONTAL) {
				this.dowseFire(relative.relative(direction1));
			}
		}
	}

	@Override
	protected void onHit(HitResult result) {
		super.onHit(result);
		if (!this.level().isClientSide) {
			this.applyWater();
			if (this.isLingering()) {
				this.makeAreaOfEffectCloud();
			} else {
				this.applySplash(result.getType() == HitResult.Type.ENTITY ? ((EntityHitResult)result).getEntity() : null);
			}
			this.level().levelEvent(LevelEvent.PARTICLES_INSTANT_POTION_SPLASH, this.blockPosition(), this.effectColor());
			this.discard();
		}
	}

	private void applyWater() {
		AABB aabb = this.getBoundingBox().inflate(4.0, 2.0, 4.0);

		for (LivingEntity livingentity : this.level().getEntitiesOfClass(LivingEntity.class, aabb, ThrownPotion.WATER_SENSITIVE_OR_ON_FIRE)) {
			double d0 = this.distanceToSqr(livingentity);
			if (d0 < 16.0) {
				if (livingentity.isSensitiveToWater()) {
					livingentity.hurt(this.damageSources().indirectMagic(this, this.getOwner()), 1.0F);
				}

				if (livingentity.isOnFire() && livingentity.isAlive()) {
					livingentity.extinguishFire();
				}
			}
		}

		for (Axolotl axolotl : this.level().getEntitiesOfClass(Axolotl.class, aabb)) {
			axolotl.rehydrate();
		}
	}

	private void applySplash(@Nullable Entity entity) {
		AABB aabb = this.getBoundingBox().inflate(4.0, 2.0, 4.0);
		List<LivingEntity> list = this.level().getEntitiesOfClass(LivingEntity.class, aabb);
		if (!list.isEmpty()) {
			Entity source = this.getEffectSource();

			for (LivingEntity livingEntity : list) {
				if (livingEntity.isAffectedByPotions()) {
					double distance = this.distanceToSqr(livingEntity);
					if (distance < 16.0) {
						double ratio;
						if (livingEntity == entity) {
							ratio = 1.0;
						} else {
							ratio = 1.0 - Math.sqrt(distance) / 4.0;
						}

						this.doApplyEffect(source, livingEntity, ratio);
					}
				}
			}
		}
	}

	private void makeAreaOfEffectCloud() {
		AbstractAreaEffectCloud areaEffectCloud = this.getAreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
		if (this.getOwner() instanceof LivingEntity livingEntity) {
			areaEffectCloud.setOwner(livingEntity);
		}

		areaEffectCloud.setRadius(3.0F);
		areaEffectCloud.setRadiusOnUse(-0.5F);
		areaEffectCloud.setWaitTime(10);
		areaEffectCloud.setRadiusPerTick(-areaEffectCloud.getRadius() / (float)areaEffectCloud.getDuration());
		this.level().addFreshEntity(areaEffectCloud);
	}

	private void dowseFire(BlockPos pos) {
		BlockState blockstate = this.level().getBlockState(pos);
		if (blockstate.is(BlockTags.FIRE)) {
			this.level().destroyBlock(pos, false, this);
		} else if (AbstractCandleBlock.isLit(blockstate)) {
			AbstractCandleBlock.extinguish(null, blockstate, this.level(), pos);
		} else if (CampfireBlock.isLitCampfire(blockstate)) {
			this.level().levelEvent(null, LevelEvent.SOUND_EXTINGUISH_FIRE, pos, 0);
			CampfireBlock.dowse(this.getOwner(), this.level(), pos, blockstate);
			this.level().setBlockAndUpdate(pos, blockstate.setValue(CampfireBlock.LIT, Boolean.FALSE));
		}
	}

	@Override
	public DoubleDoubleImmutablePair calculateHorizontalHurtKnockbackDirection(LivingEntity entity, DamageSource damageSource) {
		double xDiff = entity.position().x - this.position().x;
		double zDiff = entity.position().z - this.position().z;
		return DoubleDoubleImmutablePair.of(xDiff, zDiff);
	}

	public void setPlayerAndPosition(Player player) {
		this.setOwner(player);
		this.setPos(new Vec3(player.getX(), player.getEyeY() - 0.1D, player.getZ()));
	}

	protected abstract boolean isLingering();
	protected abstract int effectColor();
	protected abstract AbstractAreaEffectCloud getAreaEffectCloud(Level level, double x, double y, double z);
	protected abstract void doApplyEffect(Entity source, LivingEntity victim, double ratio);
}
