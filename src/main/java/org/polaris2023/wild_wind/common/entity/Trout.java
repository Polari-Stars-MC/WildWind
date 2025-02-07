package org.polaris2023.wild_wind.common.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.polaris2023.wild_wind.common.init.ModItems;

public class Trout extends AbstractSchoolingFish {

    public final AnimationState swim = new AnimationState();
    public final AnimationState struggle = new AnimationState();
    public final AnimationState attack = new AnimationState();

    public Trout(EntityType<? extends AbstractSchoolingFish> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AmbientCreature.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 3f);
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return ModItems.TROUT_BUCKET.toStack();
    }
}
