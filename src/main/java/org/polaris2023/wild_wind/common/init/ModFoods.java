package org.polaris2023.wild_wind.common.init;

import net.minecraft.server.commands.EffectCommands;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public enum ModFoods implements Supplier<FoodProperties> {
    EGG(2, 1.3F, p -> {
        p.effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F);
    }),
    COOKED_EGG(5, 6),
    RAW_TROUT(2, 0.4F, p-> {
        p.effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F);
    } ),
    COOKED_TROUT(6, 9.6F),
    BROWN_MUSHROOM(1, 1.2F, p -> {
        p.effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 100, 0), 0.5F);
    }),
    RED_MUSHROOM(1, 1.2F, p -> {
        p.effect(() -> new MobEffectInstance(MobEffects.POISON, 160, 0), 0.5F);
    }),
    CRIMSON_FUNGUS(1, 1.2F, p-> {
        p.effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 200, 0), 0.5F);
    }),
    WARPED_FUNGUS(1, 1.5F, p-> {
        p.effect(() -> new MobEffectInstance(MobEffects.WITHER, 240, 0), 0.5F);
    }),
    BAKED_MUSHROOM(4, 5),
    NETHER_MUSHROOM_STEW(6, 7.2F),
    LIVING_TUBER(4, 0.6F, p -> {
        p
                .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 7), 1)
                .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 600, 4), 1)
                .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 600, 0), 1)
                .effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 600, 0), 1);
    }),
    COOKED_LIVING_TUBER(8, 10, p -> {
        p
                .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 7), 1)
                .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 600, 4), 1)
                .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 600, 0), 1)
                .effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 600, 0), 1);
    }),

    ;

    private final FoodProperties properties;

    ModFoods(int nutrition, float saturationModifier) {
        this(nutrition, saturationModifier, p -> {});
    }

    ModFoods(int nutrition, float saturationModifier, Consumer<FoodProperties.Builder> consumer) {
        FoodProperties.Builder p = new FoodProperties.Builder();
        p.nutrition(nutrition);
        p.saturationModifier(saturationModifier);
        consumer.accept(p);
        this.properties = p.build();
    }

    @Override
    public FoodProperties get() {
        return properties;
    }
}
