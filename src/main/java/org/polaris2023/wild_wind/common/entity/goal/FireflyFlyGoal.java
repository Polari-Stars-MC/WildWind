package org.polaris2023.wild_wind.common.entity.goal;

import org.polaris2023.wild_wind.common.entity.Firefly;

public class FireflyFlyGoal extends FireflyBaseGoal {

    public FireflyFlyGoal(Firefly firefly) {
        super(firefly);
    }

    @Override
    public void tick() {
        super.tick();

    }



    @Override
    public boolean canUse() {
        if (firefly.isFlying()) {
            return true;
        }
        return super.canUse();
    }
}
