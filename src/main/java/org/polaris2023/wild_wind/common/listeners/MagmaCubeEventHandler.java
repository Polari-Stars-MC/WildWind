package org.polaris2023.wild_wind.common.listeners;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.MobSplitEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import org.apache.commons.lang3.compare.ComparableUtils;
import org.polaris2023.wild_wind.common.init.ModAttachmentTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@EventBusSubscriber
public class MagmaCubeEventHandler {

    private static final Logger log = LoggerFactory.getLogger(MagmaCubeEventHandler.class);

    private static final Vec3i RANGE = new Vec3i(3, 3, 3);

    @SubscribeEvent
    public static void onMagmaAttack(LivingDamageEvent.Pre event) {
        if(event.getSource().getEntity() instanceof MagmaCube) {
            LivingEntity entity = event.getEntity();
            entity.setRemainingFireTicks(entity.getRemainingFireTicks() + 40);
        }
    }

    @SubscribeEvent
    public static void onMagmaDied(LivingDeathEvent event) {
        if(event.getEntity() instanceof MagmaCube magmaCube) {
            Level level = magmaCube.level();
            Vec3 position = magmaCube.position();
            BlockPos vertex1 = magmaCube.blockPosition().subtract(RANGE);
            BlockPos vertex2 = magmaCube.blockPosition().offset(RANGE);
            for(BlockPos blockPos : BlockPos.betweenClosed(vertex1, vertex2)) {
                if(level.getBlockState(blockPos).is(Tags.Blocks.NETHERRACKS)) {
                    level.setBlockAndUpdate(blockPos, Blocks.MAGMA_BLOCK.defaultBlockState());
                }
            }
            if(level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.FLAME, position.x, position.y, position.z, 250, RANGE.getX(), RANGE.getY(), RANGE.getZ(), 0.5);
            }
        }
    }

    @SubscribeEvent
    public static void onTickTryMerge(EntityTickEvent.Post event) {
        if(event.getEntity() instanceof MagmaCube magmaCube) {
            Level level = magmaCube.level();
            int size = magmaCube.getSize();

            // check maximum size
            if(size >= 3) return;

            // get 4 magma cubes inside the merging boundary
            var mergingAABB = magmaCube.getBoundingBox().inflate(0.5, 0.0, 0.5);
            var magmaCubesInAABB = level.getEntitiesOfClass(MagmaCube.class, mergingAABB, e -> e.getSize() == size && isMergeableMagmaCube(e));
            if(magmaCubesInAABB.size() < 4) return;
            var chosenMagmaCubes = magmaCubesInAABB.subList(0, 4);

            // create a new magma cube and put basic data
            var newMagmaCube = EntityType.MAGMA_CUBE.create(level);
            if(newMagmaCube == null) return;
            newMagmaCube.setPos(magmaCube.position());
            newMagmaCube.setSize(switch(size) {
                case 0 -> 1;
                case 1 -> 3;
                default -> {
                    log.info("Unsupported Magma Cube size {}, fallback to 3", size);
                    yield 3;
                }
            }, true);

            // gather data from the chosen magma cubes
            Map<Holder<MobEffect>, MobEffectInstance> effectMap = new HashMap<>();
            for(MagmaCube mergingCube : chosenMagmaCubes) {
                mergingCube.getActiveEffectsMap().forEach((holder, inst) -> effectMap.merge(holder, inst, ComparableUtils::max));
                mergingCube.discard();
            }

            // apply them to the new magma cube
            effectMap.forEach((holder, inst) -> newMagmaCube.addEffect(inst));

            // spawn it
            level.addFreshEntity(newMagmaCube);
        }
    }

    @SubscribeEvent
    public static void onMagmaCubeSplit(MobSplitEvent event) {
        if(event.getParent() instanceof MagmaCube) {
            for(Mob child : event.getChildren()) {
                child.setData(ModAttachmentTypes.SPLIT_MAGMA_CUBE.get(), true);
            }
        }
    }

    public static boolean isMergeableMagmaCube(MagmaCube magmaCube) {
        return !magmaCube.hasData(ModAttachmentTypes.SPLIT_MAGMA_CUBE.get()) || !magmaCube.getData(ModAttachmentTypes.SPLIT_MAGMA_CUBE.get());
    }

}
