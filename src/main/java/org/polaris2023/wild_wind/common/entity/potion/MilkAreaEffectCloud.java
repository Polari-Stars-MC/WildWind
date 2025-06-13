package org.polaris2023.wild_wind.common.entity.potion;

import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.EffectCures;
import org.polaris2023.wild_wind.common.init.ModEntities;

public class MilkAreaEffectCloud extends AbstractAreaEffectCloud {
	public MilkAreaEffectCloud(EntityType<? extends MilkAreaEffectCloud> entityType, Level level) {
		super(entityType, level);
	}

	public MilkAreaEffectCloud(Level level, double x, double y, double z) {
		this(ModEntities.MILK_AREA_EFFECT_CLOUD.get(), level);
		this.setPos(x, y, z);
	}

	@Override
	protected void applyEffectOn(LivingEntity livingEntity) {
		livingEntity.removeEffectsCuredBy(EffectCures.MILK);
	}

	private static final ColorParticleOption PARTICLE_OPTION = ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, AbstractMilkBottle.COLOR);
	@Override
	protected ColorParticleOption getParticleType() {
		return PARTICLE_OPTION;
	}
}
