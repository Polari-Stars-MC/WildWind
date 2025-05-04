package org.polaris2023.wild_wind.common.event.game;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.GlowSquid;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModAttachmentTypes;
import org.polaris2023.wild_wind.common.init.ModItems;
import org.polaris2023.wild_wind.common.init.ModSounds;

/**
 * @author : baka4n
 * {@code @Date : 2025/05/05 00:02:47}
 */
@EventBusSubscriber(modid = WildWindMod.MOD_ID)
public class EntityEvents {
    @SubscribeEvent
    private static void onEntityTick(EntityTickEvent.Pre event) {
        Entity entity = event.getEntity();
        if (entity instanceof Goat || entity instanceof Cow) {
            AttachmentType<Integer> type = ModAttachmentTypes.MILKING_INTERVALS.get();
            entity.setData(type, Math.max(0, entity.getData(type) - 1));
        } else if (entity instanceof Squid squid) {
            AttachmentType<Integer> type1 = ModAttachmentTypes.SQUID_CONVERSION_TIME.get();
            AttachmentType<Boolean> type2 = ModAttachmentTypes.SHOULD_SQUID_CONVERT.get();
            squid.setData(type1, Math.max(0, squid.getData(type1) - 1));
            Level level = squid.level();
            if (squid.getData(type1) <= 0 && squid.getData(type2)) {
                GlowSquid glowSquid = squid.convertTo(EntityType.GLOW_SQUID, Boolean.FALSE);
                if (glowSquid != null) {
                    EventHooks.onLivingConvert(squid, glowSquid);
                }

                if (!squid.isSilent()) {
                    level.playSound(null, squid.blockPosition(), SoundEvents.GLOW_INK_SAC_USE, SoundSource.NEUTRAL);
                }
            }

            if (squid.getData(type1) > 0 && !squid.getData(type2) && level.isClientSide) {
                level.addParticle(ParticleTypes.GLOW, squid.getRandomX(0.6), squid.getRandomY(), squid.getRandomZ(0.6), 0.0F, 0.0F, 0.0F);
            }

        } else if (entity instanceof ItemEntity item) {
            if (item.getItem().is(ModItems.LIVING_TUBER)) {
                RandomSource random = item.getRandom();
                Level level = item.level();
                int j = random.nextInt(20, 200);
                if (level.getGameTime() % j == 0) {
                    int i = random.nextInt(1, 13);
                    ModSounds sounds = ModSounds.AMBIENT_S.getOrDefault(i, ModSounds.GLARE_AMBIENT_1);
                    level.playLocalSound(item.getX(), item.getY(), item.getZ(), sounds.get(), SoundSource.HOSTILE, 1F, 1F, true);
                }
            }
        } else if (entity instanceof ItemFrame frame) {
            AttachmentType<Boolean> isInvisible = ModAttachmentTypes.IS_INVISIBLE.get();
            AttachmentType<Boolean> vanillaInvisible = ModAttachmentTypes.VANILLA_INVISIBLE_SAVE.get();
            if (frame.hasData(isInvisible) && frame.getData(isInvisible)) {
                if (frame.getItem().isEmpty()) {
                    boolean data = frame.hasData(vanillaInvisible) ? frame.getData(vanillaInvisible) : false;
                    frame.setData(vanillaInvisible, data);
                    frame.setInvisible(false);
                } else {
                    frame.setData(vanillaInvisible, frame.isInvisible());
                    frame.setInvisible(true);
                }
            }
        }
    }
}
