package org.polaris2023.wild_wind.common.entity.goal.firefly;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.polaris2023.wild_wind.common.entity.Firefly;
import org.polaris2023.wild_wind.common.entity.goal.firefly.abstracts.FireflyMoveGoal;
import org.polaris2023.wild_wind.common.init.tags.ModItemTags;

public class FireflyAfraidGoal extends FireflyMoveGoal {
    public FireflyAfraidGoal(Firefly firefly) {
        super(firefly);

    }

    @Override
    public void tick() {
        if (entity == null) return;
        double x = firefly.getX() - entity.getX();
        double y = firefly.getY() - entity.getY();
        double z = firefly.getZ() - entity.getZ();

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
        getNearestEntity();

        return entity != null && !(entity instanceof Firefly) && !entity.getMainHandItem().is(ModItemTags.FIREFLY_FOOD.get());//避开逃离同类
    }

    private  void getNearestEntity() {
        Level level = firefly.level();
        Player nearestPlayer = level.getNearestPlayer(firefly, 8);
        LivingEntity nearestEntity = level.getNearestEntity(LivingEntity.class, TargetingConditions.DEFAULT, firefly, firefly.getX(), firefly.getY(), firefly.getZ(), firefly.getBoundingBox().inflate(8.0D));
        if (nearestPlayer == null && nearestEntity == null) return;
        if (nearestEntity == null) {
            entity = nearestPlayer;
            return;
        }
        if (nearestPlayer == null) {
            entity = nearestEntity;
            return;
        }
        double v = nearestPlayer.position().distanceTo(firefly.position());
        double v1 = nearestEntity.position().distanceTo(firefly.position());
        entity = v > v1 ? nearestEntity : nearestPlayer;
    }
}
