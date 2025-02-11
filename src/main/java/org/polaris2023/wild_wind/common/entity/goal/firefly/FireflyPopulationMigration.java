package org.polaris2023.wild_wind.common.entity.goal.firefly;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.polaris2023.wild_wind.common.entity.Firefly;
import org.polaris2023.wild_wind.common.entity.goal.firefly.abstracts.FireflyBaseGoal;
import org.polaris2023.wild_wind.common.entity.goal.firefly.abstracts.FireflyMoveGoal;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FireflyPopulationMigration extends FireflyMoveGoal {//萤火虫种群迁徙
    @Nullable
    public List<Firefly> fireflies;

    public FireflyPopulationMigration(Firefly firefly) {
        super(firefly);

    }

    @Override
    public void tick() {
        if (fireflies == null) {
            UUID uuid = firefly.getEGM();
            Level level = firefly.level();
            fireflies = new ArrayList<>(level.getEntitiesOfClass(Firefly.class, firefly.getBoundingBox().inflate(12)));//12格内的同族萤火虫
            fireflies.removeIf(ff -> !ff.getEGM().equals(uuid));
        }
        //迁徙代码,漫无目的
    }

    @Override
    public boolean canUse() {
        return firefly.isLeader();
    }
}
