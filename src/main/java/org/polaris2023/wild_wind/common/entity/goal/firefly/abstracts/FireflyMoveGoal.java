package org.polaris2023.wild_wind.common.entity.goal.firefly.abstracts;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.world.phys.Vec3;
import org.polaris2023.wild_wind.common.entity.Firefly;

import java.util.EnumSet;

public class FireflyMoveGoal extends FireflyBaseGoal {
    protected double deltaX, deltaY, deltaZ;
    public FireflyMoveGoal(Firefly firefly) {
        super(firefly);
        setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public void tick() {
        if (deltaX == 0 || deltaY == 0 || deltaZ == 0) {
            Vec3 target = new Vec3(firefly.getX() + deltaX, firefly.getY() + deltaY, firefly.getZ() + deltaZ);
            firefly.getNavigation().moveTo(target.x, target.y, deltaZ, 1.0F);
            firefly.lookAt(EntityAnchorArgument.Anchor.EYES, target);
            System.out.println(firefly.getXRot());
            System.out.println(firefly.getYRot());

//            firefly.setDeltaMovement(deltaX, deltaY, deltaZ);
            deltaX = 0;
            deltaY = 0;
            deltaZ = 0;
        }

    }
}
