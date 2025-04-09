package org.polaris2023.wild_wind.common.event;

import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModAttachmentTypes;
import org.polaris2023.wild_wind.common.init.tags.ModEntityTypeTags;
import org.polaris2023.wild_wind.common.init.tags.ModItemTags;

@EventBusSubscriber(modid = WildWindMod.MOD_ID)
public class InvisibleItemFrameEvent {
    @SubscribeEvent
    public static void run(PlayerInteractEvent.EntityInteract event) {
        Level world = event.getLevel();
        Player player = event.getEntity();
        InteractionHand hand = event.getHand();
        Entity entity = event.getTarget();

        if (entity.getType().is(ModEntityTypeTags.WILD_WIND_INVISIBLE.get())
                && hand == InteractionHand.MAIN_HAND
                && player.getItemInHand(hand).is(ModItemTags.WILD_WIND_INVISIBLE.get())
                && player.isShiftKeyDown()
        ) {
            if (!world.isClientSide) {
                ItemFrame frame = (ItemFrame) entity;
                DeferredHolder<AttachmentType<?>, AttachmentType<Boolean>> isInvisible = ModAttachmentTypes.IS_INVISIBLE;
                if (!entity.hasData(isInvisible)) {
                    entity.setData(isInvisible, false);
                }
                if (entity.getData(isInvisible)) {
                    event.setCanceled(true);
                    return;
                }

                entity.setData(isInvisible, true);

                ItemFrame itemFrame = (ItemFrame) entity;
                itemFrame.setRotation(itemFrame.getRotation() - 1);
                itemFrame.playSound(SoundEvents.BREEZE_LAND, 1.0f, 1.0f);

                ((ServerLevel) world).sendParticles(new BlockParticleOption(ParticleTypes.FALLING_DUST, Blocks.GRAVEL.defaultBlockState()),
                        itemFrame.getX(), itemFrame.getY(), itemFrame.getZ(),
                        10, 0.5, 0.5, 0.5, 0.1);

                player.getItemInHand(hand).consume(1, player);
                event.setCanceled(true);
            }
            event.setCanceled(true);
        }
    }
}
