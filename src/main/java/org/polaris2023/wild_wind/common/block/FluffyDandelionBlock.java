package org.polaris2023.wild_wind.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
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
import net.neoforged.neoforge.event.entity.EntityStruckByLightningEvent;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.init.ModParticles;
import org.polaris2023.wild_wind.common.particles.FluffyDandelionParticle;

import java.util.Random;

@EventBusSubscriber
public class FluffyDandelionBlock extends FlowerBlock {
    public static final double PARTICLE_GENERATE_DISTANCE = 0.6;

    public FluffyDandelionBlock() {
        super(MobEffects.SATURATION, 0.35F, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));
    }

    private static void generateParticles(ServerLevel level, BlockPos pos) {
        RandomSource random = level.getRandom();
        int particleCount = 20 + random.nextInt(15);

        for (int i = 0; i < particleCount; i++) {

            // 微小的离心方向初动量
            double angle = random.nextDouble() * Math.PI * 2;
            double cos = Math.cos(angle);
            double sin = Math.sin(angle);

            double v = 0.02 + random.nextDouble() * 0.03;
            double vx = cos * v;
            double vz = sin * v;

            double l = PARTICLE_GENERATE_DISTANCE * random.nextDouble();
            double x = pos.getX() + 0.5 + l * cos;
            double y = pos.getY() - 0.6 + random.nextDouble() * 2.4;
            double z = pos.getZ() + 0.5 + l * sin;

            // 发送自定义粒子数据包
            level.sendParticles(
                    new FluffyDandelionParticle.Data(),
                    x, y, z,
                    1, // 每次发送1个粒子
                    vx, -0.01, vz,
                    0.05 // 速度标量
            );
        }

//        level.playSound(null, pos, SoundEvents.WOOL_FALL, SoundSource.BLOCKS, 0.8F, 1.2F);
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

        if (level.isThundering() && random.nextFloat() <= 0.2F && level.canSeeSky(pos)) {
            generateParticles(level, pos, random);
        }
    }

    // 生成粒子效果（客户端）
    @OnlyIn(Dist.CLIENT)
    private void generateParticles(Level level, BlockPos pos, RandomSource random) {
        // 轻微向上动量
        double vy = 0.01 + random.nextDouble() * 0.005;

        double angle = random.nextDouble() * Math.PI * 2;
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        double v = 0.0015 + random.nextDouble() * 0.0035;
        double vx = cos * v;
        double vz = sin * v;

        double l = PARTICLE_GENERATE_DISTANCE * random.nextDouble();
        double x = pos.getX() + 0.5 + l * cos;
        double y = pos.getY() + 0.1 + random.nextDouble() * 0.8;
        double z = pos.getZ() + 0.5 + l * sin;

        // 在客户端直接创建粒子
        level.addParticle(
                new FluffyDandelionParticle.Data(),
                x, y, z,
                vx, vy, vz
        );
    }


}
