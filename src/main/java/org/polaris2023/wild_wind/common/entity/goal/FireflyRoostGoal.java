package org.polaris2023.wild_wind.common.entity.goal;

import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.polaris2023.wild_wind.common.entity.Firefly;

public class FireflyRoostGoal extends FireflyFlyGoal{

    public FireflyRoostGoal(Firefly firefly) {
        super(firefly);
    }

    @Override
    public boolean canUse() {
        Level level = firefly.level();
        if (!level.isClientSide
                && level.getGameRules().getBoolean(GameRules.RULE_DAYLIGHT)
                && level.getGameRules().getBoolean(GameRules.RULE_WEATHER_CYCLE)) {

        }
        return super.canUse();
    }
}
