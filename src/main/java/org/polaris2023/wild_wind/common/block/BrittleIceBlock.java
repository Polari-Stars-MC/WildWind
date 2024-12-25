package org.polaris2023.wild_wind.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.pathfinder.PathType;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class BrittleIceBlock extends IceBlock {
	public static final MapCodec<BrittleIceBlock> CODEC = simpleCodec(BrittleIceBlock::new);

	public BrittleIceBlock(Properties properties) {
		super(properties);
	}

	@Override
	public MapCodec<? extends BrittleIceBlock> codec() {
		return CODEC;
	}

	@Override
	public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
		super.stepOn(level, pos, state, entity);

		if (!level.isClientSide() && !level.getBlockTicks().willTickThisTick(pos, this)) {
			level.scheduleTick(pos, this, 2);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void tick(BlockState blockState, ServerLevel level, BlockPos blockPos, RandomSource random) {
		Predicate<Entity> entityPredicate = entity -> entity.onGround() && entity.getOnPosLegacy().equals(blockPos);
		List<? extends LivingEntity> entitiesOnBlock = level.getEntities(EntityTypeTest.forClass(LivingEntity.class), entityPredicate);

		if(!entitiesOnBlock.isEmpty()) {
			level.setBlock(blockPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
			level.playSound(null, blockPos, SoundEvents.GLASS_BREAK, SoundSource.BLOCKS, 1.25F, 1.0F);
		}
	}

	@Override
	public PathType getAdjacentBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob mob, PathType originalType) {
		return PathType.DANGER_OTHER;
	}
}
