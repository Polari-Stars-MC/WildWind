package org.polaris_bear.wild_wind.common.entity.goal;

import net.minecraft.world.entity.ai.goal.Goal;
import org.polaris_bear.wild_wind.common.entity.Firefly;


public class FireflyBaseGoal extends Goal {

    public final Firefly firefly;

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
