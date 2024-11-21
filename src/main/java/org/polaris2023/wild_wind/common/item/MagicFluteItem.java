package org.polaris2023.wild_wind.common.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.InstrumentItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.polaris2023.wild_wind.common.init.tags.ModInstrumentTags;

public class MagicFluteItem extends InstrumentItem {
	public MagicFluteItem(Properties properties) {
		super(properties, ModInstrumentTags.MAGIC_FLUTE.get());
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
		if(level instanceof ServerLevel serverLevel) {
			serverLevel.getEntities(livingEntity, AABB.ofSize(livingEntity.position(), 15, 15, 15), entity -> entity.getType().is(EntityTypeTags.UNDEAD))
					.forEach(entity -> {
						if (entity instanceof Mob mob) {
							mob.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 600));
							mob.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 600));
							mob.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 600, 3));
							mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 6));
						}
					});
			stack.hurtAndBreak(1, serverLevel, livingEntity, item -> {});
		}
		return stack;
	}

	public static MagicFluteItem stackTo1(Properties properties) {
		return new MagicFluteItem(properties.stacksTo(1).durability(10).rarity(Rarity.UNCOMMON));
	}
}
