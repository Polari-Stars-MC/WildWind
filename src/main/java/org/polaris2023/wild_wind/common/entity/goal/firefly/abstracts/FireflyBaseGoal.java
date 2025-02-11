package org.polaris2023.wild_wind.common.entity.goal.firefly.abstracts;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import org.jetbrains.annotations.Nullable;
import org.polaris2023.wild_wind.common.entity.Firefly;


public class FireflyBaseGoal extends Goal {

    public final Firefly firefly;
    @Nullable
    public LivingEntity entity;

    public FireflyBaseGoal(Firefly firefly) {
        this.firefly = firefly;
    }

    @Override
    public void tick() {
        super.tick();

    }

    @Override
    public boolean canUse() {
        return false;
    }
}
