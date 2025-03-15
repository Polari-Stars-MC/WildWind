package org.polaris2023.wild_wind.common.event;

import net.minecraft.client.particle.AshParticle;
import net.minecraft.client.particle.FallingDustParticle;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.HitResult;
import org.polaris2023.wild_wind.common.init.ModItems;
import org.polaris2023.wild_wind.common.inter.ICustomItemFrame;

public class InvisibleItemFrameEvent {
    public static InteractionResult run(Level world, Player player, InteractionHand hand, Entity entity, HitResult hitResult) {
        if ((entity.getType() == EntityType.ITEM_FRAME || entity.getType() == EntityType.GLOW_ITEM_FRAME)
                && hand == InteractionHand.MAIN_HAND
                && player.getItemInHand(hand).getItem() == ModItems.ASH_DUST.get()
                && player.isShiftKeyDown()
        ) {
            if (!world.isClientSide) {
                ICustomItemFrame frame = (ICustomItemFrame) entity;

                if (frame.wild_wind$getIsInvisible())
                    return InteractionResult.PASS;

                frame.wild_wind$setIsInvisible(true);

                ItemFrame itemFrame = (ItemFrame) entity;
                itemFrame.playSound(SoundEvents.BREEZE_LAND, 1.0f, 1.0f);

                ((ServerLevel) world).sendParticles(new BlockParticleOption(ParticleTypes.FALLING_DUST, Blocks.GRAVEL.defaultBlockState()),
                        itemFrame.getX(), itemFrame.getY(), itemFrame.getZ(),
                        10, 0.5, 0.5, 0.5, 0.1);

                player.getItemInHand(hand).consume(1, player);
                return InteractionResult.SUCCESS;
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

}
