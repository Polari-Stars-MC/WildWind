package org.polaris2023.wild_wind.common.init.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredItem;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.annotation.modelgen.item.BasicItem;

import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.polaris2023.wild_wind.util.ItemPropertiesUtil.STACK_TO_1;
import static org.polaris2023.wild_wind.util.ItemPropertiesUtil.STACK_TO_SNOW;
import static org.polaris2023.wild_wind.util.interfaces.registry.ItemRegistry.simpleItem;

/**
 * @author : baka4n
 * {@code @Date : 2025/02/12 20:19:42}
 */
public enum ModBaseItems implements Supplier<Item>, ItemLike {
    // @I18n(en_us = "Glow Powder", zh_cn = "萤光粉末", zh_tw = "螢光粉末")
    // GLOW_POWDER,
    // @I18n(en_us = "Ash Dust", zh_cn = "灰烬粉末", zh_tw = "灰烬粉末")
    // ASH_DUST,
    @BasicItem
    @I18n(en_us = "Apple Cake", zh_cn = "苹果派", zh_tw = "蘋果派")
    APPLE_CAKE,
    @BasicItem
    @I18n(en_us = "Candy", zh_cn = "糖果", zh_tw = "糖果")
    CANDY(STACK_TO_SNOW),
    @BasicItem
    @I18n(en_us = "Cheese Pumpkin soup", zh_cn = "奶酪南瓜汤", zh_tw = "起司南瓜湯")
    CHEESE_PUMPKIN_SOUP(STACK_TO_1),
    @BasicItem
    @I18n(en_us = "Spider Egg", zh_cn = "蜘蛛卵", zh_tw = "蜘蛛卵")
    SPIDER_EGG(STACK_TO_1),
    @BasicItem
    @I18n(en_us = "Cobweb Mucosa", zh_cn = "蛛丝壁膜", zh_tw = "蛛絲壁膜")
    COBWEB_MUCOSA,
    @BasicItem
    @I18n(en_us = "salt", zh_cn = "盐", zh_tw = "鹽")
    SALT,
    @BasicItem
    @I18n(en_us = "Fish Bone", zh_cn = "鱼骨", zh_tw = "魚骨")
    FISH_BONE,
    @BasicItem
    @I18n(en_us = "Charred Bone", zh_cn = "焦脆骨头", zh_tw = "焦脆骨頭")
    CHARRED_BONE,
    ;
    public final DeferredItem<Item> entry;
    ModBaseItems() {
        entry = simpleItem(name().toLowerCase(Locale.ROOT));
    }
    ModBaseItems(Consumer<Item.Properties> consumer) {
        entry = simpleItem(name().toLowerCase(Locale.ROOT), consumer);
    }

    @Override
    public Item get() {
        return entry.get();
    }

    @Override
    public Item asItem() {
        return entry.asItem();
    }
}
