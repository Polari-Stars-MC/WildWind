package org.polaris2023.wild_wind.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.IShearable;
import org.polaris2023.wild_wind.common.init.ModDamageType;

public class LargeThornBlock extends DoublePlantBlock implements IShearable {

    public static final MapCodec<LargeThornBlock> CODEC = simpleCodec(LargeThornBlock::new);
    private static final float HURT_SPEED_THRESHOLD = 0.003F;
    public LargeThornBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity && entity.getType() != EntityType.FOX && entity.getType() != EntityType.BEE) {
            entity.makeStuckInBlock(state, new Vec3(0.8F, 0.75, 0.8F));
            if (!level.isClientSide && (entity.xOld != entity.getX() || entity.zOld != entity.getZ())) {
                double d0 = Math.abs(entity.getX() - entity.xOld);
                double d1 = Math.abs(entity.getZ() - entity.zOld);
                if (d0 >= HURT_SPEED_THRESHOLD || d1 >= HURT_SPEED_THRESHOLD) {
                    entity.hurt(ModDamageType.thornDamage(level), 1.0F);
                    if (level.random.nextFloat() < 0.05F) {
                        level.destroyBlock(pos, false);
                    }
                }
            }
        }
    }

    @Override
    public MapCodec<? extends DoublePlantBlock> codec() {
        return CODEC;
    }
}
