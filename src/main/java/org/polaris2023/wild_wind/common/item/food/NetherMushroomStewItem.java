package org.polaris2023.wild_wind.common.item.food;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.polaris2023.wild_wind.common.init.ModFoods;
import org.polaris2023.wild_wind.common.item.BasicItem;

import java.util.function.Supplier;

public class NetherMushroomStewItem extends BasicItem {
    public NetherMushroomStewItem(Properties properties, Supplier<FoodProperties> supplier) {
        super(properties.food(supplier.get()));
    }


    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (ModFoods.NETHER_MUSHROOM_STEW.get().equals(stack.get(DataComponents.FOOD))) {
            entity.removeEffect(MobEffects.WITHER);
            entity.clearFire();
            for (Piglin piglin : level.getEntitiesOfClass(Piglin.class, new AABB(entity.getOnPos()).inflate(7))) {
                if (entity.equals(piglin.getLastHurtByMob())) {
                    entity.setLastHurtByMob(null);
                }

            }
        }
        return super.finishUsingItem(stack, level, entity);
    }
}
