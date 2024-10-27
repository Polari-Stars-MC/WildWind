package org.polaris2023.wild_wind.common.entity.goal;

import org.polaris2023.wild_wind.common.entity.Firefly;

public class FireflyGlowGoal extends FireflyBaseGoal {

    public FireflyGlowGoal(Firefly firefly) {
        super(firefly);
    }

    @Override
    public void tick() {
        if (firefly.isTicker()) {
            firefly.clearTicker();
            firefly.setLastHurtByMob(null);
        }
    }

    @Override
    public boolean canUse() {
        return firefly.getLastHurtByMob() != null;
    }
}
