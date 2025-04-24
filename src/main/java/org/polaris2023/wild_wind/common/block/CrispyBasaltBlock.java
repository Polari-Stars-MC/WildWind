package org.polaris2023.wild_wind.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.vehicle.VehicleEntity;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import org.polaris2023.wild_wind.util.EnchantmentHelper;

import java.util.List;
import java.util.function.Predicate;

public class CrispyBasaltBlock extends Block {
	public static final MapCodec<CrispyBasaltBlock> CODEC = simpleCodec(CrispyBasaltBlock::new);
	public static final int MAX_AGE = 4;
	public static final IntegerProperty AGE = BlockStateProperties.AGE_4;
	public static final BooleanProperty UNSTABLE = BlockStateProperties.UNSTABLE;

	public CrispyBasaltBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(UNSTABLE, false));
	}

	@Override
	public MapCodec<? extends CrispyBasaltBlock> codec() {
		return CODEC;
	}

	protected boolean canCrash(Entity entity, ServerLevel serverLevel) {
		return (entity instanceof LivingEntity livingEntity && !hasFrostWalker(serverLevel, livingEntity)) || entity instanceof VehicleEntity || entity instanceof Projectile;
	}

	@Override
	public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
		if(level instanceof ServerLevel serverLevel && this.canCrash(entity, serverLevel) && fallDistance > 0.75F) {
			this.crash(serverLevel, pos, entity);
		}
		super.fallOn(level, state, pos, entity, fallDistance);
	}

	@Override
	public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
		super.stepOn(level, pos, state, entity);

		if (level instanceof ServerLevel serverLevel && !level.getBlockTicks().willTickThisTick(pos, this) &&
				!entity.isSteppingCarefully() && this.canCrash(entity, serverLevel)) {
			level.scheduleTick(pos, this, 10);
		}
	}

	@Override
	protected void onProjectileHit(Level level, BlockState state, BlockHitResult hit, Projectile projectile) {
		if(level instanceof ServerLevel serverLevel && this.canCrash(projectile, serverLevel)) {
			this.crash(serverLevel, hit.getBlockPos(), projectile);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void tick(BlockState blockState, ServerLevel level, BlockPos blockPos, RandomSource random) {
		int age = blockState.getValue(AGE);
		boolean unstable = blockState.getValue(UNSTABLE);
		if(unstable) {
			this.crash(level, blockPos, null);
			return;
		}
		Predicate<Entity> entityPredicate = entity -> entity.onGround() && entity.getOnPosLegacy().equals(blockPos) && !entity.isSteppingCarefully() && this.canCrash(entity, level);
		List<? extends Entity> entitiesOnBlock = level.getEntities(EntityTypeTest.forClass(Entity.class), entityPredicate);
		if(entitiesOnBlock.isEmpty()) {
			if(age > 0) {
				if(random.nextInt(120 / age / age) < 10) {
					level.setBlock(blockPos, blockState.setValue(AGE, age - 1), Block.UPDATE_ALL);
				}
				level.scheduleTick(blockPos, this, 10);
			}
		} else if(age == MAX_AGE) {
			this.crash(level, blockPos, entitiesOnBlock.get(random.nextInt(entitiesOnBlock.size())));
		} else {
			level.setBlock(blockPos, blockState.setValue(AGE, age + 1), Block.UPDATE_ALL);
			level.playSound(null, blockPos, SoundEvents.GLASS_HIT, SoundSource.BLOCKS, 0.5F, 0.9F + random.nextFloat() * 0.2F);
			level.scheduleTick(blockPos, this, 10);
		}
	}

	@SuppressWarnings("BooleanMethodIsAlwaysInverted")
	private static boolean hasFrostWalker(ServerLevel level, LivingEntity livingEntity) {	//TODO
		return EnchantmentHelper.hasEnchantment(level, livingEntity.getItemBySlot(EquipmentSlot.FEET), Enchantments.FROST_WALKER);
	}

	private void crash(ServerLevel level, BlockPos blockPos, @Nullable Entity entity) {
		level.destroyBlock(blockPos, false, entity);
		level.setBlock(blockPos, Blocks.LAVA.defaultBlockState(), Block.UPDATE_ALL);
		if(level.random.nextInt(5) < 2) {
			this.tryChainReactAt(level, blockPos.south());
		}
		if(level.random.nextInt(5) < 2) {
			this.tryChainReactAt(level, blockPos.east());
		}
		if(level.random.nextInt(5) < 2) {
			this.tryChainReactAt(level, blockPos.north());
		}
		if(level.random.nextInt(5) < 2) {
			this.tryChainReactAt(level, blockPos.west());
		}
		if(level.random.nextInt(5) < 3) {
			this.tryChainReactAt(level, blockPos.below());
		}
	}

	private void tryChainReactAt(ServerLevel level, BlockPos blockPos) {
		BlockState blockState = level.getBlockState(blockPos);
		BlockState above = level.getBlockState(blockPos.above());
		if(blockState.is(this) && !above.is(this)) {
			level.setBlock(blockPos, blockState.setValue(UNSTABLE, true), Block.UPDATE_ALL);
			if(!level.getBlockTicks().willTickThisTick(blockPos, this)) {
				level.scheduleTick(blockPos, this, 2);
			}
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(AGE, UNSTABLE);
	}

	@Override
	public PathType getAdjacentBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob mob, PathType originalType) {
		return PathType.DANGER_OTHER;
	}
}
