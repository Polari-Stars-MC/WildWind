package org.polaris2023.wild_wind.common.entity.goal.firefly;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.polaris2023.wild_wind.common.entity.Firefly;
import org.polaris2023.wild_wind.common.init.tags.ModItemTags;

public class FireflyAfraidGoal extends FireflyBaseGoal {
    double deltaX = 0, deltaY = 0, deltaZ = 0;
    public FireflyAfraidGoal(Firefly firefly) {
        super(firefly);
    }

    @Override
    public void tick() {
        LivingEntity nearestEntity = getNearestEntity();
        if (nearestEntity == null) return;
        double x = firefly.getX() - nearestEntity.getX();
        double y = firefly.getY() - nearestEntity.getY();
        double z = firefly.getZ() - nearestEntity.getZ();

        if (x > 0) deltaX = 0.2;
        else if (x < 0) deltaX = -0.2;
        if (y > 0) deltaY = 0.2;
        else if (y < 0) deltaY = -0.2;
        if (z > 0) deltaZ = 0.2;
        else if (z < 0) deltaZ = -0.2;
        firefly.setDeltaMovement(deltaX, deltaY, deltaZ);
        deltaX = 0;
        deltaY = 0;
        deltaZ = 0;
    }

    @Override
    public boolean canUse() {
        LivingEntity nearestEntity = getNearestEntity();
        
        return nearestEntity != null && !(nearestEntity instanceof Firefly) && !nearestEntity.getUseItem().is(ModItemTags.FIREFLY_FOOD.get());//避开逃离同类
    }

    private @Nullable LivingEntity getNearestEntity() {
        Level level = firefly.level();
        Player nearestPlayer = level.getNearestPlayer(firefly, 8);
        LivingEntity nearestEntity = level.getNearestEntity(LivingEntity.class, TargetingConditions.DEFAULT, firefly, firefly.getX(), firefly.getY(), firefly.getZ(), firefly.getBoundingBox().inflate(8.0D));
        if (nearestEntity == null) {
            nearestEntity = nearestPlayer;
        }
        return nearestEntity;
    }
}
