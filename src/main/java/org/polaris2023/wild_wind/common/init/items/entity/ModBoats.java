package org.polaris2023.wild_wind.common.init.items.entity;

import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.annotation.modelgen.item.BasicItem;
import org.polaris2023.wild_wind.common.init.ModEnumExtensions;

import static org.polaris2023.wild_wind.util.interfaces.registry.ItemRegistry.*;

/**
 * @author : baka4n
 * {@code @Date : 2025/02/20 20:11:15}
 */
public class ModBoats {
    @BasicItem
    @I18n(en_us = "Azalea Boat", zh_cn = "杜鹃木船", zh_tw = "杜鵑木船")
    public static final DeferredItem<BoatItem> AZALEA_BOAT =
            register("azalea_boat", () -> new BoatItem(false, ModEnumExtensions.WILD_WIND_AZALEA.getValue(), new Item.Properties().stacksTo(1)));
    @BasicItem
    @I18n(en_us = "Azalea Chest Boat", zh_cn = "杜鹃木运输船", zh_tw = "杜鵑木運輸船")
    public static final DeferredItem<BoatItem> AZALEA_CHEST_BOAT =
            register("azalea_chest_boat", () -> new BoatItem(true, ModEnumExtensions.WILD_WIND_AZALEA.getValue(), new Item.Properties().stacksTo(1)));
    @BasicItem
    @I18n(en_us = "Palm Boat", zh_cn = "棕榈木船", zh_tw = "棕櫚木船")
    public static final DeferredItem<BoatItem> PALM_BOAT =
            register("palm_boat", () -> new BoatItem(false, ModEnumExtensions.WILD_WIND_PALM.getValue(), new Item.Properties().stacksTo(1)));
    @BasicItem
    @I18n(en_us = "Palm Chest Boat", zh_cn = "棕榈木运输船", zh_tw = "棕櫚木運輸船")
    public static final DeferredItem<BoatItem> PALM_CHEST_BOAT =
            register("palm_chest_boat", () -> new BoatItem(true, ModEnumExtensions.WILD_WIND_PALM.getValue(), new Item.Properties().stacksTo(1)));
    @BasicItem
    @I18n(en_us = "Baobab Boat", zh_cn = "猴面包木船", zh_tw = "猴麵包木船")
    public static final DeferredItem<BoatItem> BAOBAB_BOAT =
            register("baobab_boat", () -> new BoatItem(false, ModEnumExtensions.WILD_WIND_BAOBAB.getValue(), new Item.Properties().stacksTo(1)));
    @BasicItem
    @I18n(en_us = "Baobab Chest Boat", zh_cn = "猴面包木运输船", zh_tw = "猴麵包木運輸船")
    public static final DeferredItem<BoatItem> BAOBAB_CHEST_BOAT =
            register("baobab_chest_boat", () -> new BoatItem(true, ModEnumExtensions.WILD_WIND_BAOBAB.getValue(), new Item.Properties().stacksTo(1)));



}
