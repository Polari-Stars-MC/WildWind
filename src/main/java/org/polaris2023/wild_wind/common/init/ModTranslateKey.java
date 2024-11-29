package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.annotation.language.I18n;

import java.util.Locale;
import java.util.function.Supplier;

public enum ModTranslateKey implements Supplier<TranslatableContents> {
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
    ;

    final MutableComponent translatable;
    ModTranslateKey(String key) {
        translatable = Component.translatable(key);
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
