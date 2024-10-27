package org.polaris2023.wild_wind.common.entity.goal;

import net.minecraft.world.phys.Vec3;
import org.polaris2023.wild_wind.common.entity.Firefly;

public class FireflyFlyGoal extends FireflyBaseGoal {

    public FireflyFlyGoal(Firefly Firefly) {
        super(Firefly);
    }

    @Override
    public void tick() {
        super.tick();
    }

    private void checkMovement() {
        Vec3 vec3 = firefly.getDeltaMovement();
        if (Math.abs(vec3.x) > 0.1 || Math.abs(vec3.z) > 0.1) {
            double d = Math.abs(firefly.xo - firefly.getX());
            if (d < 0.1)
                firefly.getNavigation().stop();
        }
    }

    @Override
    public boolean canUse() {
        if (firefly.isFlying()) {
            return true;
        }
        return super.canUse();
    }
}
