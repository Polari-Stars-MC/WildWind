package org.polaris2023.wild_wind.common.init;

import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import org.polaris2023.wild_wind.common.init.items.entity.ModBoats;

import java.util.function.Supplier;

/**
 * @author : baka4n
 * {@code @Date : 2025/02/20 19:47:19}
 */
public class ModEnumExtensions {
    public static final EnumProxy<Boat.Type> WILD_WIND_AZALEA = new EnumProxy<>(
            Boat.Type.class,
            ModBlocks.AZALEA_PLANKS,
            "wild_wind:azalea",
            ModBoats.AZALEA_BOAT,
            ModBoats.AZALEA_CHEST_BOAT,
            (Supplier<Item>) () -> Items.STICK,
            false
    );
    public static final EnumProxy<Boat.Type> WILD_WIND_PALM = new EnumProxy<>(
            Boat.Type.class,
            ModBlocks.PALM_PLANKS,
            "wild_wind:palm",
            ModBoats.PALM_BOAT,
            ModBoats.PALM_CHEST_BOAT,
            (Supplier<Item>) () -> Items.STICK,
            false
    );
    public static final EnumProxy<Boat.Type> WILD_WIND_BAOBAB = new EnumProxy<>(
            Boat.Type.class,
            ModBlocks.BAOBAB_PLANKS,
            "wild_wind:baobab",
            ModBoats.BAOBAB_BOAT,
            ModBoats.BAOBAB_CHEST_BOAT,
            (Supplier<Item>) () -> Items.STICK,
            false
    );
}
