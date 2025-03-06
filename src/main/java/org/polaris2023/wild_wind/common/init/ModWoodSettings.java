package org.polaris2023.wild_wind.common.init;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.Locale;

import static org.polaris2023.wild_wind.WildWindMod.MOD_ID;

/**
 * @author : baka4n
 * {@code @Date : 2025/03/06 18:39:27}
 */
public enum ModWoodSettings {
    AZALEA,
    PALM,
    BAOBAB,
    ;
    public final BlockSetType setType;
    public final WoodType woodType;
    ModWoodSettings() {
        String typeName = MOD_ID + "_" + name().toLowerCase(Locale.ROOT);
        setType = BlockSetType.register(new BlockSetType(typeName));
        woodType = WoodType.register(new WoodType(typeName, setType));
    }
}
