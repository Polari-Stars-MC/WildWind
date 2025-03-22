package org.polaris2023.wild_wind.mixin;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.material.MapColor;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;

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

    /*
    static {
        int size = ModDyeColor.values().length;
        int length = $VALUES.length;
        $VALUES = Arrays.copyOf($VALUES, length + size);
        for (ModDyeColor value : ModDyeColor.values()) {
            value.dyeColor = newColor(value.name(), length, value.color, value.name, value.color, value.mapColor, value.color, value.color);
            value.id = length;
            $VALUES[length] = value.dyeColor;
            length++;
        }
    }
    */
/*
    @Inject(method = "<clinit>", at = @At(value = "FIELD", target = "Lnet/minecraft/world/item/DyeColor;$VALUES:[Lnet/minecraft/world/item/DyeColor;", shift = At.Shift.AFTER))
    private static void DD$addCustomColor(CallbackInfo ci) {
        ArrayList<DyeColor> dyeColors = new ArrayList<>(Arrays.asList($VALUES));
        DyeColor last = dyeColors.getLast();
        int i = 1;
        for (ModDyes color : ModDyes.values()) {
            dyeColors.add(newColor(color.name(), last.ordinal() + i, color.getId(), color.getName(), color.getIntColor(), color.getMapColor(), color.getFireworkColor(), color.getTextColor()));
            i++;
        }
        $VALUES = dyeColors.toArray(new DyeColor[0]);
    }

 */
}