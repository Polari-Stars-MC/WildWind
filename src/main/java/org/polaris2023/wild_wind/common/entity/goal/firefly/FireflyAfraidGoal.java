package org.polaris2023.wild_wind.common.entity.goal.firefly;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;
import org.polaris2023.wild_wind.common.entity.Firefly;

public class FireflyAfraidGoal extends FireflyBaseGoal {
    double deltaX = 0, deltaY = 0, deltaZ = 0;
    public FireflyAfraidGoal(Firefly firefly) {
        super(firefly);
    }

    @Override
    public void tick() {
        firefly.setDeltaMovement(deltaX, deltaY, deltaZ);
        deltaX = 0;
        deltaY = 0;
        deltaZ = 0;
    }

    @Override
    public boolean canUse() {
        Level level = firefly.level();
        LivingEntity nearestEntity = level.getNearestEntity(LivingEntity.class, TargetingConditions.DEFAULT, firefly, firefly.getX(), firefly.getY(), firefly.getZ(), firefly.getBoundingBox().inflate(8.0D));
        if (nearestEntity == null) return super.canUse();

        double x = firefly.getX() - nearestEntity.getX();
        double y = firefly.getY() - nearestEntity.getY();
        double z = firefly.getZ() - nearestEntity.getZ();
        if (x == 0 || y == 0 || z == 0) return false;
        if (x > 0 && x < 8) deltaX = 0.2;
        else if (x < 0 && x > -8) deltaX = -0.2;
        if (y > 0 && y < 8) deltaY = 0.2;
        else if (y < 0 && y > -8) deltaY = -0.2;
        if (z > 0 && z < 8) deltaZ = 0.2;
        else if (z < 0 && z > -8) deltaZ = -0.2;
        return true;
    }
}
