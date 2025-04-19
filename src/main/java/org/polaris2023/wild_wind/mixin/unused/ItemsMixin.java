package org.polaris2023.wild_wind.mixin.unused;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.polaris2023.annotation.enums.TagType;
import org.polaris2023.annotation.handler.MixinDefine;
import org.polaris2023.annotation.other.Removed;
import org.polaris2023.annotation.tag.CTag;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/20 00:16:29}
 */
@SuppressWarnings("ALL")
@Mixin(Items.class)
@MixinDefine("net.minecraft.world.item.Items")
@Removed
public class ItemsMixin {
    @CTag(names = "firefly_food", type = TagType.Item, mixin = true)
    @Shadow @Final public static Item GLOW_BERRIES;
    @CTag(names = "firefly_food", type = TagType.Item, mixin = true)
    @Shadow @Final public static Item GLOW_LICHEN;
    @CTag(names = "meat_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item BEEF;
    @CTag(names = "meat_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item COOKED_BEEF;
    @CTag(names = "meat_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item PORKCHOP;
    @CTag(names = "meat_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item COOKED_PORKCHOP;
    @CTag(names = "meat_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item MUTTON;
    @CTag(names = "meat_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item COOKED_MUTTON;
    @CTag(names = "meat_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item RABBIT;
    @CTag(names = "meat_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item COOKED_RABBIT;
    @CTag(names = "meat_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item CHICKEN;
    @CTag(names = "meat_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item COOKED_CHICKEN;

    static {

    }

}
