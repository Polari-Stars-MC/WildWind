package org.polaris2023.wild_wind.common.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.material.MapColor;

/**
 * @author : baka4n
 * {@code @Date : 2025/03/06 20:51:24}
 */
public enum ModDyeColor  {
    ;
    public DyeColor dyeColor;
    public int id;
    public final int color;
    public final MapColor mapColor;
    public final String name;

    ModDyeColor(String name, int color, MapColor mapColor) {
        this.name = name;
        this.mapColor = mapColor;
        this.color = color;
    }
}
