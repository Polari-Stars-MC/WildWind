package org.polaris2023.wild_wind.util;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.Tags;

import java.util.EnumSet;
import java.util.Set;

public final class TeleportUtil {
	public static boolean tryTeleportToSurface(LivingEntity livingEntity, ServerLevel level, BlockPos pos) {
		int y = getTopBlock(level, pos);
		if(y < 8) {
			return false;
		}
		Vec3 position = pos.getCenter();
		return performTeleport(livingEntity, level, position.x(), y, position.z(), EnumSet.noneOf(RelativeMovement.class), livingEntity.getYRot(), livingEntity.getXRot());
	}

	public static boolean randomTeleportAround(LivingEntity livingEntity, ServerLevel level) {
		for (int i = 0; i < 16; i++) {
			double x = livingEntity.getX() + (livingEntity.getRandom().nextDouble() - 0.5) * 16.0;
			double y = Mth.clamp(
					livingEntity.getY() + (double)(livingEntity.getRandom().nextInt(16) - 8),
					level.getMinBuildHeight(),
					level.getMinBuildHeight() + level.getLogicalHeight() - 1
			);
			double z = livingEntity.getZ() + (livingEntity.getRandom().nextDouble() - 0.5) * 16.0;
			if (livingEntity.isPassenger()) {
				livingEntity.stopRiding();
			}

			if (livingEntity.randomTeleport(x, y, z, true)) {
				return true;
			}
		}
		return false;
	}

	public static int getTopBlock(Level level, BlockPos pos) {
		int y = -1;

		if(level.dimension() == Level.NETHER) {
			int air = 0;
			for(int j = 127; j > 0; j--) {
				BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos(pos.getX(), 0, pos.getZ());
				if (level.getBlockState(mutable.move(0, j, 0)).getBlock().defaultBlockState().isAir()) {
					air += 1;
				} else {
					if(air >= 2 && !level.getFluidState(mutable.move(0, j, 0)).is(Tags.Fluids.LAVA)) {
						y = j;
						break;
					}
					air = 0;
				}
			}
		} else {
			for (int j = level.getMaxBuildHeight() - 1; j > level.getMinBuildHeight(); j--) {
				BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos(pos.getX(), 0, pos.getZ());
				if (!level.getBlockState(mutable.move(0, j, 0)).getBlock().defaultBlockState().isAir()) {
					y = j;
					break;
				}
			}
		}

		return y + 1;
	}

	public static boolean performTeleport(
			Entity entity, ServerLevel level,
			double x, double y, double z,
			Set<RelativeMovement> relativeList,
			float yaw, float pitch
	) {
		BlockPos blockpos = BlockPos.containing(x, y, z);
		if (!Level.isInSpawnableBounds(blockpos)) {
			return false;
		}
		float f = Mth.wrapDegrees(yaw);
		float f1 = Mth.wrapDegrees(pitch);
		if (entity.teleportTo(level, x, y, z, relativeList, f, f1)) {
			if (!(entity instanceof LivingEntity livingentity) || !livingentity.isFallFlying()) {
				entity.setDeltaMovement(entity.getDeltaMovement().multiply(1.0, 0.0, 1.0));
				entity.setOnGround(true);
			}

			if (entity instanceof PathfinderMob pathfindermob) {
				pathfindermob.getNavigation().stop();
			}
		}

		return true;
	}

	private TeleportUtil() {
	}
}
