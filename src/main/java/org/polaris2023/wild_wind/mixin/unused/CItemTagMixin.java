package org.polaris2023.wild_wind.mixin.unused;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.Tags;
import org.polaris2023.annotation.enums.TagType;
import org.polaris2023.annotation.handler.MixinDefine;
import org.polaris2023.annotation.other.Removed;
import org.polaris2023.annotation.tag.CTag;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/21 17:19:30}
 */
@SuppressWarnings("ALL")
@Mixin(Tags.Items.class)
@Removed
@MixinDefine("net.neoforged.neoforge.common.Tags.Items")
public class CItemTagMixin {
    @CTag(names = "poison_seeds_component_seed", type = TagType.Item, mixin = true, tag = true)
    @Shadow @Final public static TagKey<Item> SEEDS_TORCHFLOWER;
    @CTag(names = "seeds_component_food", type = TagType.Item, mixin = true, tag = true)
    @Shadow @Final public static TagKey<Item> SEEDS_PUMPKIN;
    @CTag(names = "seeds_component_food", type = TagType.Item, mixin = true, tag = true)
    @Shadow @Final public static TagKey<Item> SEEDS_MELON;
    @CTag(names = "seeds_component_food", type = TagType.Item, mixin = true, tag = true)
    @Shadow @Final public static TagKey<Item> SEEDS_BEETROOT;
    @CTag(names = "seeds_component_food", type = TagType.Item, mixin = true, tag = true)
    @Shadow @Final public static TagKey<Item> SEEDS_WHEAT;
    @CTag(names = "egg_component_food", type = TagType.Item, mixin = true, tag = true)
    @Shadow @Final public static TagKey<Item> EGGS;

    static {

    }
}
