package org.polaris2023.wild_wind.common.entity.goal;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.MoverType;
import org.polaris2023.wild_wind.common.entity.Firefly;

public class FireflyFlyGoal extends FireflyBaseGoal {//寻路机制

    public FireflyFlyGoal(Firefly firefly) {
        super(firefly);
    }

    @Override
    public void tick() {
        super.tick();
        if (firefly.movePos().equals(firefly.blockPosition())) {
            RandomSource random = firefly.getRandom();
            firefly.setMovePos(firefly.movePos().offset(random.nextInt(-50, 50),0,random.nextInt(-50, 50)));
        }
        firefly.move(MoverType.SELF, firefly.movePos().getCenter());
    }



    @Override
    public boolean canUse() {
        if (firefly.isFlying()) {
            return true;
        }
        return super.canUse();
    }
}
