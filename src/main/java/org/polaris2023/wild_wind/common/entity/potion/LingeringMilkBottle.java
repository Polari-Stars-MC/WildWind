package org.polaris2023.wild_wind.common.entity.potion;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.polaris2023.wild_wind.common.init.ModItems;

public class LingeringMilkBottle extends AbstractMilkBottle {
	public LingeringMilkBottle(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected boolean isLingering() {
		return true;
	}

	@Override
	protected Item getDefaultItem() {
		return ModItems.LINGERING_MILK_BOTTLE.get();
	}
}
