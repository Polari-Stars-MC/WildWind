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
    @CTag(names = {"firefly_food", "fruit_food"}, type = TagType.Item, mixin = true)
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
    @CTag(names = "vegetable_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item CARROT;
    @CTag(names = "vegetable_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item GOLDEN_CARROT;
    @CTag(names = "vegetable_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item BEETROOT;
    @CTag(names = "vegetable_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item POTATO;
    @CTag(names = "vegetable_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item BAKED_POTATO;
    @CTag(names = "vegetable_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item BROWN_MUSHROOM;
    @CTag(names = "vegetable_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item RED_MUSHROOM;
    @CTag(names = "vegetable_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item CRIMSON_FUNGUS;
    @CTag(names = "vegetable_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item WARPED_FUNGUS;
    @CTag(names = "fruit_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item APPLE;
    @CTag(names = "fruit_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item GOLDEN_APPLE;
    @CTag(names = "fruit_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item ENCHANTED_GOLDEN_APPLE;
    @CTag(names = "fruit_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item CHORUS_FRUIT;
    @CTag(names = "fruit_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item POPPED_CHORUS_FRUIT;
    @CTag(names = "fruit_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item MELON_SLICE;
    @CTag(names = "fruit_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item GLISTERING_MELON_SLICE;
    @CTag(names = "fruit_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item SWEET_BERRIES;
    @CTag(names = "fruit_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item SUGAR_CANE;
    @CTag(names = "protein_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item EGG;
    @CTag(names = {"protein_food", "egg_component_food"},type = TagType.Item, mixin = true)
    @Shadow @Final public static Item TURTLE_EGG;
    @CTag(names = "protein_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item SNIFFER_EGG;
    @CTag(names = "protein_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item DRAGON_EGG;
    @CTag(names = "fish_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item COD;
    @CTag(names = "fish_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item COOKED_COD;
    @CTag(names = "fish_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item SALMON;
    @CTag(names = "fish_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item COOKED_SALMON;
    @CTag(names = "fish_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item KELP;
    @CTag(names = "fish_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item DRIED_KELP;
    @CTag(names = "monster_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item RABBIT_FOOT;
    @CTag(names = "monster_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item SPIDER_EYE;
    @CTag(names = "monster_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item FERMENTED_SPIDER_EYE;
    @CTag(names = "monster_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item TROPICAL_FISH;
    @CTag(names = "monster_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item PUFFERFISH;
    @CTag(names = "monster_food",type = TagType.Item, mixin = true)
    @Shadow @Final public static Item ROTTEN_FLESH;
    @CTag(names = "poison_seeds_component_seed", type = TagType.Item, mixin = true)
    @Shadow @Final public static Item PITCHER_POD;

    static {

    }

}
