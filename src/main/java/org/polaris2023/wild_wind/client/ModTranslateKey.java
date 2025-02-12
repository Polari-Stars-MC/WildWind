package org.polaris2023.wild_wind.client;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModComponents;

import java.util.Locale;
import java.util.function.Supplier;

public enum ModTranslateKey implements Supplier<TranslatableContents> {
    @I18n(en_us = "Nutrition: ", zh_cn = "饥饿值：", zh_tw = "饑餓值：")
    NUTRITION("wild","wind","nutrition"),
    @I18n(en_us = "Saturation: ", zh_cn = "饱食度：", zh_tw = "飽食度：")
    SATURATION("wild","wind","saturation"),
    @I18n(en_us = "effect: ", zh_cn = "效果：", zh_tw = "效果：")
    EFFECT("wild", "wind", "effects"),
    @I18n(en_us = "Trigger probability", zh_cn = "概率触发", zh_tw = "概率觸發")
    TRIGGER_PROBABILITY,
    @I18n(en_us = "Duration", zh_cn = "持续时间", zh_tw = "持續時間")
    DURATION("wild","wind","duration"),
    @I18n(en_us = "Meat: ", zh_cn = "肉值：", zh_tw = "肉值：")
    MEAT_VALUE(ModComponents.MEAT_VALUE),
    @I18n(en_us = "Vegetable: ", zh_cn = "菜值：", zh_tw = "菜值：")
    VEGETABLE_VALUE(ModComponents.VEGETABLE_VALUE),
    @I18n(en_us = "Fruit: ", zh_cn = "果值：", zh_tw = "果值：")
    FRUIT_VALUE(ModComponents.FRUIT_VALUE),
    @I18n(en_us = "Protein: ", zh_cn = "蛋值：", zh_tw = "蛋值：")
    PROTEIN_VALUE(ModComponents.PROTEIN_VALUE),
    @I18n(en_us = "Fish: ", zh_cn = "鱼值：", zh_tw = "魚值：")
    FISH_VALUE(ModComponents.FISH_VALUE),
    @I18n(en_us = "Monster: ", zh_cn = "怪值：", zh_tw = "怪值：")
    MONSTER_VALUE(ModComponents.MONSTER_VALUE),
    @I18n(en_us = "Sweet: ", zh_cn = "甜值：", zh_tw = "甜值：")
    SWEET_VALUE(ModComponents.SWEET_VALUE),
    @I18n(en_us = "cooking pot", zh_cn = "烹饪锅", zh_tw = "烹飪鍋")
    COOKIN_POT("gui", WildWindMod.MOD_ID, "cooking_pot"),
    @I18n(en_us = "Cow intolerance", zh_tw = "奶牛不耐受", zh_cn = "奶牛不耐受")
    COW_INTOLERANCE("cow", "intolerance"),
    @I18n(en_us = "Goat intolerance", zh_tw = "山羊不耐受", zh_cn = "山羊不耐受")
    GOAT_INTOLERANCE("cow", "intolerance"),

    ;

    final MutableComponent translatable;
    ModTranslateKey(String key) {
        translatable = Component.translatable(key);
    }

    ModTranslateKey(String... keys) {
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < keys.length; i++) {
            String k = keys[i];
            if (i == 1) {
                key.append(k);
            } else {
                key.append(".").append(k);
            }
        }
        translatable = Component.translatable(key.toString());
    }

    <T> ModTranslateKey(DeferredHolder<DataComponentType<?>, DataComponentType<T>> holder) {
        translatable = Component.translatable("component." + holder.getId().toString().replace(":", "."));
    }

    ModTranslateKey() {
        translatable = Component.translatable(name().toLowerCase(Locale.ROOT).replace("_", ".").replace("$", "."));
    }

    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public TranslatableContents get() {
        return (TranslatableContents) translatable.getContents();
    }

    public MutableComponent translatable() {
        return translatable;
    }
}
