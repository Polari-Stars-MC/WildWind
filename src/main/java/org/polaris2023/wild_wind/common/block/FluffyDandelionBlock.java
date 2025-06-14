package org.polaris2023.wild_wind.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.VanillaGameEvent;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.particles.FluffyDandelionParticle;

@EventBusSubscriber
public class FluffyDandelionBlock extends FlowerBlock {
    public static final double PARTICLE_GENERATE_DISTANCE = 0.8;

    public FluffyDandelionBlock() {
        super(MobEffects.SATURATION, 0.35F, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));
    }

    @SubscribeEvent
    public static void onDandelionClicked(UseItemOnBlockEvent event) {
        if (!event.getLevel().isClientSide() && event.getLevel().getBlockState(event.getPos()).is(Blocks.DANDELION) && event.getItemStack().is(Items.BONE_MEAL)) {
            event.getLevel().setBlock(event.getPos(), ModBlocks.FLUFFY_DANDELION.get().defaultBlockState(), UPDATE_ALL);
            event.getItemStack().shrink(1);
            event.cancelWithResult(ItemInteractionResult.SUCCESS);
        }
    }

    @SubscribeEvent
    public static void onLightningStrike(VanillaGameEvent event) {
        if (!event.getLevel().isClientSide() && event.getVanillaEvent().equals(GameEvent.LIGHTNING_STRIKE)) {
            int x = (int) event.getEventPosition().x;
            int y = (int) event.getEventPosition().y;
            int z = (int) event.getEventPosition().z;
            BlockPos.betweenClosedStream(-2, -1, -2, 2, 0, 2)
                    .map(pos -> pos.offset(x, y, z))
                    .filter(pos -> event.getLevel().getBlockState(pos).is(Blocks.DANDELION))
                    .forEach(pos -> event.getLevel().setBlock(pos, ModBlocks.FLUFFY_DANDELION.get().defaultBlockState(), UPDATE_ALL));
        }
    }


    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);

        if ((level.isRaining() || level.isThundering()) && random.nextFloat() <= 0.4F && level.canSeeSky(pos)) {
            for (int i = -2; i < random.nextInt(5); i++) {
                generateParticles(level, pos, random);
            }
        }
    }

    // 生成粒子效果（客户端）
    @OnlyIn(Dist.CLIENT)
    private void generateParticles(Level level, BlockPos pos, RandomSource random) {
        double vy = random.nextDouble() * -0.15 + 0.1;

        double angle = random.nextDouble() * Math.PI * 2;
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        double v = 0.015 + random.nextDouble() * 0.06;
        double vx = cos * v;
        double vz = sin * v;

        double l = PARTICLE_GENERATE_DISTANCE * random.nextDouble();
        double x = pos.getX() + 0.5 + l * cos;
        double y = pos.getY() + 0.4 + random.nextDouble() * 0.4;
        double z = pos.getZ() + 0.5 + l * sin;

        // 在客户端直接创建粒子
        level.addParticle(
                new FluffyDandelionParticle.Data(),
                x, y, z,
                vx, vy, vz
        );
    }


}
