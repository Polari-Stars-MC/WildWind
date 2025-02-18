package org.polaris2023.wild_wind.common.entity.goal.firefly;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.polaris2023.wild_wind.common.entity.Firefly;
import org.polaris2023.wild_wind.common.entity.goal.firefly.abstracts.FireflyMoveGoal;
import org.polaris2023.wild_wind.common.init.tags.ModItemTags;

public class FireflyAttractGoal extends FireflyMoveGoal {//萤火虫受玩家手中的食物吸引

    public FireflyAttractGoal(Firefly firefly) {
        super(firefly);
    }

    @Override
    public void tick() {
        if (!(entity instanceof Player)) return;
        //吸引
        double x = firefly.getX() - entity.getX();
        double y = firefly.getY() - entity.getY();
        double z = firefly.getZ() - entity.getZ();

        if (x > 0) deltaX = -0.2;
        else if (x < 0) deltaX = 0.2;
        if (y > 0) deltaY = -0.2;
        else if (y < 0) deltaY = 0.2;
        if (z > 0) deltaZ = -0.2;
        else if (z < 0) deltaZ = 0.2;
        firefly.setDeltaMovement(deltaX, deltaY, deltaZ);
        deltaX = 0;
        deltaY = 0;
        deltaZ = 0;
    }

    @Override
    public boolean canUse() {
        Level level = firefly.level();
        entity = level.getNearestPlayer(firefly, 8);
        if (!(entity instanceof Player player)) return super.canUse();
        return player.getMainHandItem().is(ModItemTags.FIREFLY_FOOD.get());
    }
}
