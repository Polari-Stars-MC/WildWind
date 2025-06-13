package org.polaris2023.wild_wind.common.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.polaris2023.wild_wind.common.entity.potion.AbstractThrowableBottle;

import java.util.function.Supplier;

public class ThrowableBottleItem<T extends AbstractThrowableBottle> extends Item {
	private final Supplier<EntityType<T>> bottleEntityFactory;
	private final boolean lingering;

	public ThrowableBottleItem(Supplier<EntityType<T>> bottleEntityFactory, boolean lingering, Item.Properties properties) {
		super(properties);
		this.bottleEntityFactory = bottleEntityFactory;
		this.lingering = lingering;
	}

	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemStack = player.getItemInHand(hand);
		level.playSound(null, player.getX(), player.getY(), player.getZ(), this.lingering ? SoundEvents.LINGERING_POTION_THROW : SoundEvents.SPLASH_POTION_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
		if (!level.isClientSide) {
			AbstractThrowableBottle bottle = this.bottleEntityFactory.get().create(level);
			if(bottle != null) {
				bottle.setPlayerAndPosition(player);
				bottle.setItem(itemStack);
				bottle.shootFromRotation(player, player.getXRot(), player.getYRot(), -20.0F, 0.5F, 1.0F);
				level.addFreshEntity(bottle);
			}
		}

		player.awardStat(Stats.ITEM_USED.get(this));
		itemStack.consume(1, player);
		return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
	}
}
