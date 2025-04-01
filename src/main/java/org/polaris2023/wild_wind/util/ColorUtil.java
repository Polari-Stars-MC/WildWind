package org.polaris2023.wild_wind.util;

import net.minecraft.util.ColorRGBA;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.DyeColor;

import java.awt.*;

/**
* @author : baka4n
* {@code @Date : 2025/03/30 22:18:55}
*/
public class ColorUtil {
    public static int getColor(DyeColor color) {
        int diffuseColor = color.getTextureDiffuseColor();
        int red = diffuseColor >> 16 & 0xFF;
        int green = diffuseColor >> 8 & 0xFF;
        int blue = diffuseColor & 0xFF;
        return FastColor.ARGB32.color(red, green, blue) & ~ 0xff000000;
    }

    public static DyeColor opaque(int color) {

        int a = opaqueValue(color);
        for (DyeColor value : DyeColor.values()) {
            if (value.getTextureDiffuseColor() == a) {
                return value;
            }
        }
        return DyeColor.WHITE;
    }

    public static int opaqueValue(int color) {
        int opaque = FastColor.ARGB32.opaque(color);
        int red = Math.max(0, Math.min(255, FastColor.ARGB32.red(opaque)));
        int green = Math.max(0, Math.min(255, FastColor.ARGB32.green(opaque)));
        int blue = Math.max(0, Math.min(255, FastColor.ARGB32.blue(opaque)));
        return  (red << 16) | (green << 8) | blue;


    }
}
