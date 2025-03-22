package org.polaris2023.wild_wind.common.init;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.item.Items;

public class ModEntityDataAccess {
    public static EntityDataAccessor<Integer> MILKING_INTERVALS_BY_COW;
    public static EntityDataAccessor<Integer> MILKING_INTERVALS_BY_GOAT;
    public static EntityDataAccessor<Integer> MILKING_INTERVALS_BY_MUSHROOM_COW;

    static {
        //bootstrap
        Cow.createAttributes();
        Goat.createAttributes();
        MushroomCow.createAttributes();
    }
}
