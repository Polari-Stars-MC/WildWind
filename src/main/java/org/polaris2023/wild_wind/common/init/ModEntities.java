package org.polaris2023.wild_wind.common.init;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.wild_wind.common.entity.Firefly;
import org.polaris2023.wild_wind.common.entity.Glare;
import org.polaris2023.wild_wind.common.entity.Piranha;
import org.polaris2023.wild_wind.common.entity.Trout;
import org.polaris2023.wild_wind.common.entity.potion.*;

import static org.polaris2023.wild_wind.common.init.ModInitializer.register;

public class ModEntities {

    @I18n(en_us = "Firefly", zh_cn = "萤火虫", zh_tw = "螢火蟲")
    public static final DeferredHolder<EntityType<?>, EntityType<Firefly>> FIREFLY =
            register("firefly", Firefly::new, MobCategory.AMBIENT);
    @I18n(en_us = "Glare", zh_cn = "怒目怪", zh_tw = "怒目靈")
    public static final DeferredHolder<EntityType<?>, EntityType<Glare>> GLARE =
            register("glare", Glare::new, MobCategory.MONSTER);
    @I18n(en_us = "Glare", zh_cn = "鳟鱼", zh_tw = "鱒魚")
    public static final DeferredHolder<EntityType<?>, EntityType<Trout>> TROUT =
            register("trout", Trout::new, MobCategory.WATER_AMBIENT);
    @I18n(en_us = "Piranha", zh_cn = "食人鲳", zh_tw = "食人魚")
    public static final DeferredHolder<EntityType<?>, EntityType<Piranha>> PIRANHA =
            register("piranha", Piranha::new, MobCategory.WATER_AMBIENT);

    @I18n(en_us = "Splash Milk Bottle", zh_cn = "喷溅型牛奶瓶", zh_tw = "飛濺牛奶瓶")
    public static final DeferredHolder<EntityType<?>, EntityType<SplashMilkBottle>> SPLASH_MILK_BOTTLE =
            register("splash_milk_bottle", SplashMilkBottle::new, ModEntities::potion, MobCategory.MISC);
    @I18n(en_us = "Lingering Milk Bottle", zh_cn = "滞留型牛奶瓶", zh_tw = "滯留牛奶瓶")
    public static final DeferredHolder<EntityType<?>, EntityType<LingeringMilkBottle>> LINGERING_MILK_BOTTLE =
            register("lingering_milk_bottle", LingeringMilkBottle::new, ModEntities::potion, MobCategory.MISC);
    @I18n(en_us = "Splash Honey Bottle", zh_cn = "喷溅型蜂蜜瓶", zh_tw = "飛濺蜂蜜瓶")
    public static final DeferredHolder<EntityType<?>, EntityType<SplashHoneyBottle>> SPLASH_HONEY_BOTTLE =
            register("splash_honey_bottle", SplashHoneyBottle::new, ModEntities::potion, MobCategory.MISC);
    @I18n(en_us = "Lingering Honey Bottle", zh_cn = "滞留型蜂蜜瓶", zh_tw = "滯留蜂蜜瓶")
    public static final DeferredHolder<EntityType<?>, EntityType<LingeringHoneyBottle>> LINGERING_HONEY_BOTTLE =
            register("lingering_honey_bottle", LingeringHoneyBottle::new, ModEntities::potion, MobCategory.MISC);
    @I18n(en_us = "Milk Area Effect Cloud", zh_cn = "牛奶区域效果云", zh_tw = "牛奶藥水效果雲")
    public static final DeferredHolder<EntityType<?>, EntityType<MilkAreaEffectCloud>> MILK_AREA_EFFECT_CLOUD =
            register("milk_area_effect_cloud", MilkAreaEffectCloud::new,
                    builder -> builder.fireImmune()
                            .sized(6.0F, 0.5F)
                            .clientTrackingRange(10)
                            .updateInterval(Integer.MAX_VALUE), MobCategory.MISC);
    @I18n(en_us = "Honey Area Effect Cloud", zh_cn = "蜂蜜区域效果云", zh_tw = "蜂蜜藥水效果雲")
    public static final DeferredHolder<EntityType<?>, EntityType<HoneyAreaEffectCloud>> HONEY_AREA_EFFECT_CLOUD =
            register("honey_area_effect_cloud", HoneyAreaEffectCloud::new,
                    builder -> builder.fireImmune()
                            .sized(6.0F, 0.5F)
                            .clientTrackingRange(10)
                            .updateInterval(Integer.MAX_VALUE), MobCategory.MISC);

    private static void potion(EntityType.Builder<? extends AbstractThrowableBottle> builder) {
        builder.sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10);
    }
}
