package org.polaris2023.wild_wind.common.entity.potion;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.EffectCures;

public abstract class AbstractMilkBottle extends AbstractThrowableBottle {
	public static final int COLOR = 0xffffffff;

	public AbstractMilkBottle(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected int effectColor() {
		return COLOR;
	}

	@Override
	protected MilkAreaEffectCloud getAreaEffectCloud(Level level, double x, double y, double z) {
		return new MilkAreaEffectCloud(level, x, y, z);
	}

	@Override
	protected void doApplyEffect(Entity source, LivingEntity victim, double ratio) {
		victim.removeEffectsCuredBy(EffectCures.MILK);
	}
}
