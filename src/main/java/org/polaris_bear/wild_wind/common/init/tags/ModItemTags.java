package org.polaris_bear.wild_wind.common.init.tags;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.polaris_bear.wild_wind.util.Helpers;

public class ModItemTags {
    public static final TagKey<Item> FIREFLY_FOOD = register("flrefly_food");
    private static TagKey<Item> register(String name) {
        return ItemTags.create(Helpers.location(name));
    }

}
