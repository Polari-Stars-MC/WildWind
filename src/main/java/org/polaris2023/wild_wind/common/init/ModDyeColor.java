package org.polaris2023.wild_wind.common.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.Nullable;
import org.polaris2023.wild_wind.WildWindMod;

import java.awt.*;
import java.util.Locale;

/**
 * @author : baka4n
 * {@code @Date : 2025/03/06 20:51:24}
 */
public enum ModDyeColor  {
    NATURAL(13419950, MapColor.TERRACOTTA_WHITE)
    ;
    @Nullable
    public final DyeColor dyeColor;
    public final int color;
    public final int textColor;
    public final MapColor mapColor;
    public final String name;

    ModDyeColor(String name, int color, MapColor mapColor) {
        this.name = name;
        this.mapColor = mapColor;
        this.color = color;
        this.textColor = color;
        dyeColor = null;
    }

    ModDyeColor(int color, MapColor mapColor) {
        this.name = WildWindMod.MOD_ID + "_" + name().toLowerCase(Locale.ROOT);
        this.mapColor = mapColor;
        this.color = color;
        this.textColor = color;
        dyeColor = null;

    }

    ModDyeColor() {
        dyeColor = DyeColor.valueOf(name());
        this.name = dyeColor.getName();
        this.color = dyeColor.getTextureDiffuseColor();
        this.mapColor = dyeColor.getMapColor();
        this.textColor = dyeColor.getTextColor();
    }
}
