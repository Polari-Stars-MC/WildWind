package org.polaris2023.wild_wind.common.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class Glare extends Monster {

    public Glare(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }
}
