package org.polaris2023.wild_wind.common.init;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.goat.Goat;

public class ModEntityDataAccess {
    public static final EntityDataAccessor<Integer> MILKING_INTERVALS_BY_COW =
            SynchedEntityData.defineId(Cow.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> MILKING_INTERVALS_BY_GOAT =
            SynchedEntityData.defineId(Goat.class, EntityDataSerializers.INT);

}
