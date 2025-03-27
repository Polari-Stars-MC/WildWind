package org.polaris2023.wild_wind.mixin;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.material.MapColor;
import org.polaris2023.wild_wind.common.dyed.ModDyeColors;
import org.polaris2023.wild_wind.common.init.ModDyeColor;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/**
* @author : baka4n
* {@code @Date : 2025/03/06 21:00:21}
*/
@Mixin(DyeColor.class)
public class DyeColorMixin {
    @Shadow
    @Mutable
    @Final
    private static DyeColor[] $VALUES;

    @Invoker("<init>")
    private static DyeColor newColor(
            String enumName, int ordinal,int id, String name, int textureDefuseColor, MapColor mapColor, int fireworkColor, int textColor
    ) {
        throw new AssertionError();
    }


//    static {
//        int size = ModDyeColor.values().length;
//        int length = $VALUES.length;
//        $VALUES = Arrays.copyOf($VALUES, length + size);
//        for (ModDyeColor value : ModDyeColor.values()) {
//            value.dyeColor = newColor(value.name(), length, value.color, value.name, value.color, value.mapColor, value.color, value.color);
//            value.id = length;
//            $VALUES[length] = value.dyeColor;
//            length++;
//        }
//    }


//    @Inject(method = "<clinit>", at = @At(value = "FIELD", target = "Lnet/minecraft/world/item/DyeColor;$VALUES:[Lnet/minecraft/world/item/DyeColor;", shift = At.Shift.AFTER))
//    private static void DD$addCustomColor(CallbackInfo ci) {
//        ArrayList<DyeColor> dyeColors = new ArrayList<>(Arrays.asList($VALUES));
//        DyeColor last = dyeColors.getLast();
//        int i = 1;
//
//        for (ModDyeColor color : ModDyeColor.values()) {
//            DyeColor newColor = newColor(color.name.toUpperCase(Locale.ROOT), last.ordinal() + i, color.id, color.name, color.color, color.mapColor, color.color, color.color);
//            color.dyeColor = newColor;
//            dyeColors.add(newColor);
//            i++;
//        }
//        $VALUES = dyeColors.toArray(new DyeColor[0]);
//    }


}