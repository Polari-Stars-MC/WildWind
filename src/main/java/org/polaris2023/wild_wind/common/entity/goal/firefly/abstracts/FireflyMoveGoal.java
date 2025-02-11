package org.polaris2023.wild_wind.common.entity.goal.firefly.abstracts;

import org.polaris2023.wild_wind.common.entity.Firefly;

public class FireflyMoveGoal extends FireflyBaseGoal {
    protected double deltaX, deltaY, deltaZ;
    public FireflyMoveGoal(Firefly firefly) {
        super(firefly);
    }

    @Override
    public void tick() {
        if (deltaX == 0 || deltaY == 0 || deltaZ == 0) {
            firefly.setDeltaMovement(deltaX, deltaY, deltaZ);
            deltaX = 0;
            deltaY = 0;
            deltaZ = 0;
        }

    }
}
