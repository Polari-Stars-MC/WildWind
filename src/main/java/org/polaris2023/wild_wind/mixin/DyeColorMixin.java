package org.polaris2023.wild_wind.mixin;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.material.MapColor;
import org.polaris2023.wild_wind.common.init.ModDyeColor;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Arrays;

/**
* @author : baka4n
* {@code @Date : 2025/03/06 21:00:21}
*/
@Mixin(DyeColor.class)
@Debug(export = true)
public class DyeColorMixin {
    @Shadow
    @Mutable
    @Final
    private static DyeColor[] $VALUES;

    @Invoker("<init>")
    private static DyeColor init(
            String enumName, int ordinal,int id, String name, int textureDefuseColor, MapColor mapColor, int fireworkColor, int textColor
    ) {
        throw new AssertionError();
    }

    static {
        int size = ModDyeColor.values().length;
        int length = $VALUES.length;
        $VALUES = Arrays.copyOf($VALUES, length + size);
        for (ModDyeColor value : ModDyeColor.values()) {
            value.id = length;
            $VALUES[length] = init(value.name(), length, value.color, value.name, value.color, value.mapColor, value.color, value.color);
            length++;
        }
    }

}
