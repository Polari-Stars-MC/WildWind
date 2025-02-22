package org.polaris2023.wild_wind.common.item;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import org.polaris2023.wild_wind.common.init.ModItems;
import org.polaris2023.wild_wind.common.init.tags.ModInstrumentTags;

import java.util.Iterator;
import java.util.Optional;

public class MagicFluteItem extends Item {
	private final TagKey<Instrument> instruments;

	public MagicFluteItem(Properties properties) {
		super(properties);
		this.instruments = ModInstrumentTags.MAGIC_FLUTE.get();
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
		ItemStack itemStack = player.getItemInHand(usedHand);
		Optional<? extends Holder<Instrument>> optional = this.getInstrument(itemStack);
		if (optional.isPresent()) {
			Instrument instrument = optional.get().value();
			player.startUsingItem(usedHand);
			play(level, player, instrument);
			onFluteWorks(itemStack, player);

			player.getCooldowns().addCooldown(this, 300);
			player.awardStat(Stats.ITEM_USED.get(this));
			return InteractionResultHolder.consume(itemStack);
		}
		return InteractionResultHolder.fail(itemStack);
	}

	public static void onFluteWorks(ItemStack stack, LivingEntity livingEntity) {
		Level level = livingEntity.level();
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
			stack.hurtAndBreak(10, serverLevel, livingEntity, item -> {});
		}
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity entity) {
		Optional<Holder<Instrument>> optional = this.getInstrument(stack);
		return optional.map(instrument -> instrument.value().useDuration()).orElse(0);
	}

	private Optional<Holder<Instrument>> getInstrument(ItemStack stack) {
		Holder<Instrument> holder = stack.get(DataComponents.INSTRUMENT);
		if (holder != null) {
			return Optional.of(holder);
		}
		Iterator<Holder<Instrument>> iterator = BuiltInRegistries.INSTRUMENT.getTagOrEmpty(this.instruments).iterator();
		return iterator.hasNext() ? Optional.of(iterator.next()) : Optional.empty();
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.TOOT_HORN;
	}

	private static void play(Level level, Player player, Instrument instrument) {
		SoundEvent soundevent = instrument.soundEvent().value();
		float volume = instrument.range() / 16.0F;
		level.playSound(player, player, soundevent, SoundSource.RECORDS, volume, 1.0F);
		level.gameEvent(GameEvent.INSTRUMENT_PLAY, player.position(), GameEvent.Context.of(player));
	}

	public static MagicFluteItem stackTo1(Properties properties) {
		return new MagicFluteItem(properties.stacksTo(1).durability(100).rarity(Rarity.UNCOMMON));
	}

	@Override
	public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
		return repair.is(ModItems.LIVING_TUBER) || super.isValidRepairItem(toRepair, repair);
	}
}
