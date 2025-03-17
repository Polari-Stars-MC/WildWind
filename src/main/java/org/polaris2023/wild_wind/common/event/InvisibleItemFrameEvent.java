package org.polaris2023.wild_wind.common.event;

import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModItems;
import org.polaris2023.wild_wind.util.interfaces.ICustomItemFrame;

@EventBusSubscriber(modid = WildWindMod.MOD_ID)
public class InvisibleItemFrameEvent {
    @SubscribeEvent
    public static void run(PlayerInteractEvent.EntityInteract event) {
        Level world = event.getLevel();
        Player player = event.getEntity();
        InteractionHand hand = event.getHand();
        Entity entity = event.getTarget();
        if ((entity.getType() == EntityType.ITEM_FRAME || entity.getType() == EntityType.GLOW_ITEM_FRAME)
                && hand == InteractionHand.MAIN_HAND
                && player.getItemInHand(hand).getItem() == ModItems.ASH_DUST.get()
                && player.isShiftKeyDown()
        ) {
            if (!world.isClientSide) {
                ICustomItemFrame frame = (ICustomItemFrame) entity;

                if (frame.wild_wind$getIsInvisible()) {
                    event.setCanceled(true);
                    return;
                }

                frame.wild_wind$setIsInvisible(true);

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
