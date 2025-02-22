package org.polaris2023.wild_wind.common.init.items.foods;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredItem;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.annotation.modelgen.item.BasicItem;
import org.polaris2023.wild_wind.common.init.ModComponents;
import org.polaris2023.wild_wind.common.init.ModFoods;
import org.polaris2023.wild_wind.common.init.ModInitializer;

import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.polaris2023.wild_wind.util.ItemPropertiesUtil.STACK_TO_1;
import static org.polaris2023.wild_wind.util.ItemPropertiesUtil.STACK_TO_SNOW;

/**
 * @author : baka4n
 * {@code @Date : 2025/02/12 20:43:50}
 */
@BasicItem
public enum ModBaseFoods implements Supplier<Item>, ItemLike {
    @I18n(en_us = "Baked Living Tuber", zh_cn = "烤活根", zh_tw = "烤活根")
    BAKED_LIVING_TUBER(
            p -> p
                    .stacksTo(16)
                    .component(ModComponents.VEGETABLE_VALUE, 1F)
                    .component(ModComponents.MEAT_VALUE, 1F)
                    .component(ModComponents.MONSTER_VALUE, 1F)),
    @I18n(en_us = "Raw Trout", zh_cn = "生鳟鱼", zh_tw = "生鱒魚")
    RAW_TROUT(p ->
            p
                    .component(ModComponents.MEAT_VALUE, 0.5F)
                    .component(ModComponents.FISH_VALUE, 1F)),
    @I18n(en_us = "Cooked Trout", zh_cn = "烤鳟鱼", zh_tw = "烤鱒魚")
    COOKED_TROUT(p ->
            p
                    .component(ModComponents.MEAT_VALUE, 0.5F)
                    .component(ModComponents.FISH_VALUE, 1F)),
    @I18n(en_us = "Raw Piranha", zh_cn = "生食人鲳", zh_tw = "生食人魚")
    RAW_PIRANHA(
            p -> p
                    .component(ModComponents.MEAT_VALUE, 0.5F)
                    .component(ModComponents.FISH_VALUE, 1F),
            ModFoods.RAW_TROUT),
    @I18n(en_us = "Cooked Piranha", zh_cn = "烤食人鲳", zh_tw = "烤食人魚")
    COOKED_PIRANHA(
            p -> p
                    .component(ModComponents.MEAT_VALUE, 0.5F)
                    .component(ModComponents.FISH_VALUE, 1F),
            ModFoods.COOKED_TROUT),
    @I18n(en_us = "Baked Beetroot", zh_cn = "烤甜菜根", zh_tw = "烤甜菜根")
    BAKED_BEETROOT(p -> p.component(ModComponents.VEGETABLE_VALUE, 1F)),
    @I18n(en_us = "Baked carrot", zh_tw = "烤胡蘿蔔", zh_cn = "烤胡萝卜")
    BAKED_CARROT(p -> p.component(ModComponents.VEGETABLE_VALUE, 1F)),
    @I18n(en_us = "Cooked Egg", zh_cn = "煎蛋", zh_tw = "煎蛋")
    COOKED_EGG(p -> p.component(ModComponents.PROTEIN_VALUE, 1F)),
    @I18n(en_us = "Dough", zh_cn = "面团", zh_tw = "麵團")
    DOUGH,
    @I18n(en_us = "Fish Chowder", zh_cn = "海鲜杂烩", zh_tw = "海鮮雜燴")
    FISH_CHOWDER(STACK_TO_1),
    @I18n(en_us = "Pumpkin Slice", zh_cn = "南瓜片", zh_tw = "南瓜片")
    PUMPKIN_SLICE(p -> p.component(ModComponents.VEGETABLE_VALUE, 0.5F)),
    @I18n(en_us = "Baked Pumpkin Slice", zh_cn = "南瓜片", zh_tw = "南瓜片")
    BAKED_PUMPKIN_SLICE(p -> p.component(ModComponents.VEGETABLE_VALUE, 0.5F)),
    @I18n(en_us = "Baked Apple", zh_cn = "烤苹果", zh_tw = "烤蘋果")
    BAKED_APPLE(p -> p.component(ModComponents.FRUIT_VALUE, 1F)),
    @I18n(en_us = "Baked Melon Slice", zh_cn = "烤西瓜片", zh_tw = "烤西瓜片")
    BAKED_MELON_SLICE(p -> p.component(ModComponents.FRUIT_VALUE, 0.5F)),
    @I18n(en_us = "Flour", zh_cn = "面粉", zh_tw = "麵粉")
    FLOUR,
    @I18n(en_us = "Baked Mushroom", zh_cn = "烤蘑菇", zh_tw = "烤蘑菇")
    BAKED_MUSHROOM(p -> p.component(ModComponents.VEGETABLE_VALUE, 0.5F)),
    @I18n(en_us = "Baked Seeds", zh_cn = "烤种子", zh_tw = "烤種子")
    BAKED_SEEDS,
    @I18n(en_us = "Baked Berries", zh_cn = "烤浆果", zh_tw = "烤莓醬")
    BAKED_BERRIES(p -> p.component(ModComponents.FRUIT_VALUE, 0.5F)),
    @I18n(en_us = "Raw Frog Leg", zh_cn = "生蛙腿", zh_tw = "生蛙腿")
    RAW_FROG_LEG(p -> p
            .component(ModComponents.MEAT_VALUE, 0.5F)
            .component(ModComponents.MONSTER_VALUE, 1F)),
    @I18n(en_us = "Cooked Frog Leg", zh_cn = "烤蛙腿", zh_tw = "烤蛙腿")
    COOKED_FROG_LEG(p -> p
                            .component(ModComponents.MEAT_VALUE, 0.5F)
                            .component(ModComponents.MONSTER_VALUE, 1F)),

    ;

    public final DeferredItem<Item> entry;

    ModBaseFoods(Consumer<Item.Properties> consumer, Supplier<FoodProperties> food) {
        entry = ModInitializer.simpleItem(name().toLowerCase(Locale.ROOT), consumer, food);
    }

    ModBaseFoods(Consumer<Item.Properties> consumer) {
        entry = ModInitializer.simpleItem(name().toLowerCase(Locale.ROOT), consumer, ModFoods.valueOf(name()));
    }

    ModBaseFoods(Supplier<FoodProperties> food) {
        entry = ModInitializer.simpleItem(name().toLowerCase(Locale.ROOT), food);
    }

    ModBaseFoods() {
        entry = ModInitializer.simpleItem(name().toLowerCase(Locale.ROOT), ModFoods.valueOf(name()));
    }

    public static void init() {}

    @Override
    public Item get() {
        return entry.get();
    }

    @Override
    public Item asItem() {
        return entry.asItem();
    }
}
