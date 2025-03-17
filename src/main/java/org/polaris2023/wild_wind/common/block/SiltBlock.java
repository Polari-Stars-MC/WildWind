package org.polaris2023.wild_wind.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.fml.common.Mod;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.init.ModDamageType;

import javax.annotation.Nullable;

public class SiltBlock extends PowderSnowBlock  {
    public SiltBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.getBlockState(pos.below()).isFaceSturdy(level, pos, Direction.UP)
        && level.getBlockState(pos.below(2)).is(Blocks.POINTED_DRIPSTONE)
        && random.nextFloat() < 0.17578125F) {
            level.setBlockAndUpdate(pos, Blocks.MUD.defaultBlockState());
        }
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!(entity instanceof LivingEntity) || entity.getInBlockState().is(this)) {
            entity.makeStuckInBlock(state, new Vec3((double)0.9F, (double)0.75F, (double)0.9F));
            if (level.isClientSide) {
                RandomSource randomsource = level.getRandom();
                boolean flag = entity.xOld != entity.getX() || entity.zOld != entity.getZ();
                if (flag && randomsource.nextBoolean()) {
                    level.addParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST, ModBlocks.SILT.get().defaultBlockState()), entity.getX(), (double)(pos.getY() + 1), entity.getZ(), (double)(Mth.randomBetween(randomsource, -1.0F, 1.0F) * 0.083333336F), (double)0.05F, (double)(Mth.randomBetween(randomsource, -1.0F, 1.0F) * 0.083333336F));
                }
            } else {
                if (!entity.isSpectator()) {
                    BlockState blockState = level.getBlockState(new BlockPos(entity.getBlockX(), (int) (entity.getEyeY() - 0.11111111F), entity.getBlockZ()));
                    if (entity instanceof LivingEntity && (blockState.is(ModBlocks.QUICKSAND) || blockState.is(ModBlocks.RED_QUICKSAND))) {
                        entity.hurt(ModDamageType.causeQuicksandDamage((LivingEntity) entity), 1.0F);
                    }
                }
            }
        }
        if (!level.isClientSide) {
            if (entity.isOnFire() && (level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING) || entity instanceof Player) && entity.mayInteract(level, pos)) {
                entity.clearFire();
            }

            entity.setSharedFlagOnFire(false);
        }
    }

    @Override
    public ItemStack pickupBlock(@Nullable Player player, LevelAccessor level, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }
}
