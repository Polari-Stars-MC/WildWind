package org.polaris2023.wild_wind.common.entity.goal.piranha;

import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import org.polaris2023.wild_wind.common.entity.Piranha;

/**
 * @author : baka4n
 * {@code @Date : 2025/02/20 21:44:01}
 */
public class PiranhaAttackGoal extends MeleeAttackGoal {
    public PiranhaAttackGoal(Piranha piranha, double speedModifier, boolean followingTargetEvenIfNotSeen) {
        super(piranha, speedModifier, followingTargetEvenIfNotSeen);
    }
}
