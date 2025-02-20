package org.polaris2023.wild_wind.common.init.items.entity;

import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.wild_wind.common.init.ModEnumExtensions;
import org.polaris2023.wild_wind.common.init.ModInitializer;

import static org.polaris2023.wild_wind.common.init.ModInitializer.register;

/**
 * @author : baka4n
 * {@code @Date : 2025/02/20 20:11:15}
 */
public class ModBoats {

    @I18n(en_us = "Azalea Boat", zh_cn = "杜鹃木船", zh_tw = "杜鵑木船")
    public static final DeferredItem<BoatItem> AZALEA_BOAT =
            register("azalea_boat", () -> new BoatItem(false, ModEnumExtensions.WILD_WIND_AZALEA.getValue(), new Item.Properties().stacksTo(1)));
    @I18n(en_us = "Azalea Chest Boat", zh_tw = "杜鵑木運輸船", zh_cn = "杜鹃木运输船")
    public static final DeferredItem<BoatItem> AZALEA_CHEST_BOAT =
            register("azalea_chest_boat", () -> new BoatItem(true, ModEnumExtensions.WILD_WIND_AZALEA.getValue(), new Item.Properties().stacksTo(1)));


    public static void init() {}
}
