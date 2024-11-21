package org.polaris2023.wild_wind.common.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluid;

public class BasicMobBucketItem extends MobBucketItem {
    public BasicMobBucketItem(EntityType<?> type, Fluid content, SoundEvent emptySound, Properties properties) {
        super(type, content, emptySound, properties);
    }
}
