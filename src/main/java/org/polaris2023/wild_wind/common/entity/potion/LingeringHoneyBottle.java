package org.polaris2023.wild_wind.common.entity.potion;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.polaris2023.wild_wind.common.init.ModItems;

public class LingeringHoneyBottle extends AbstractHoneyBottle {
	public LingeringHoneyBottle(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected boolean isLingering() {
		return true;
	}

	@Override
	protected Item getDefaultItem() {
		return ModItems.LINGERING_HONEY_BOTTLE.get();
	}
}
