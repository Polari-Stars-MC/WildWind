package org.polaris2023.wild_wind.common.entity.goal;

import net.minecraft.world.entity.ai.goal.Goal;
import org.polaris2023.wild_wind.common.entity.Firefly;

public class FireflyBaseGoal extends Goal {

    public final Firefly firefly;

    public FireflyBaseGoal(Firefly Firefly) {
        this.firefly = Firefly;
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
