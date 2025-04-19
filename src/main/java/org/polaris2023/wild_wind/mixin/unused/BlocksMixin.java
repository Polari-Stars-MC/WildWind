package org.polaris2023.wild_wind.mixin.unused;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.polaris2023.annotation.enums.TagType;
import org.polaris2023.annotation.handler.MixinDefine;
import org.polaris2023.annotation.other.Removed;
import org.polaris2023.annotation.tag.CTag;
import org.polaris2023.annotation.tag.WildWindTag;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
* @author : baka4n
* {@code @Date : 2025/04/16 21:45:30}
*/
@SuppressWarnings("ALL")
@Mixin(Blocks.class)
@MixinDefine("net.minecraft.world.level.block.Blocks")
@Removed
public class BlocksMixin {
    @CTag(names = "firefly_roost_block", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block TALL_GRASS;
    @CTag(names = "firefly_roost_block", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block SHORT_GRASS;
    @CTag(names = "firefly_roost_block", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block FERN;
    @CTag(names = "firefly_roost_block", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block LARGE_FERN;
    @CTag(names = "firefly_roost_block", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block POTTED_FERN;
    @CTag(names = "firefly_roost_block", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block MANGROVE_PROPAGULE;
    @CTag(names = "firefly_roost_block", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block POTTED_MANGROVE_PROPAGULE;

    @CTag(names = {"cattails_may_place", "reeds_may_place"}, type = TagType.Block, mixin = true)
    @Shadow @Final public static Block DIRT;
    @CTag(names = {"cattails_may_place", "reeds_may_place"}, type = TagType.Block, mixin = true)
    @Shadow @Final public static Block PODZOL;
    @CTag(names = {"cattails_may_place", "reeds_may_place"}, type = TagType.Block, mixin = true)
    @Shadow @Final public static Block GRASS_BLOCK;
    @CTag(names = {"cattails_may_place", "reeds_may_place"}, type = TagType.Block, mixin = true)
    @Shadow @Final public static Block MYCELIUM;
    @CTag(names = {"cattails_may_place", "reeds_may_place"}, type = TagType.Block, mixin = true)
    @Shadow @Final public static Block COARSE_DIRT;
    @CTag(names = {"cattails_may_place", "reeds_may_place"}, type = TagType.Block, mixin = true)
    @Shadow @Final public static Block FARMLAND;
    @CTag(names = {"cattails_may_place", "reeds_may_place"}, type = TagType.Block, mixin = true)
    @Shadow @Final public static Block MUD;
    @CTag(names = {"cattails_may_place", "reeds_may_place"}, type = TagType.Block, mixin = true)
    @Shadow @Final public static Block CLAY;
    @CTag(names = {"cattails_may_place", "reeds_may_place"}, type = TagType.Block, mixin = true)
    @Shadow @Final public static Block GRAVEL;
    @CTag(names = {"cattails_may_place", "reeds_may_place"}, type = TagType.Block, mixin = true)
    @Shadow @Final public static Block SAND;
    @CTag(names = {"cattails_may_place", "reeds_may_place"}, type = TagType.Block, mixin = true)
    @Shadow @Final public static Block RED_SAND;
    @WildWindTag(names = "banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block BLACK_BANNER;
    @WildWindTag(names = "banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block BLUE_BANNER;
    @WildWindTag(names = "banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block BROWN_BANNER;
    @WildWindTag(names = "banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block GRAY_BANNER;
    @WildWindTag(names = "banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block GREEN_BANNER;
    @WildWindTag(names = "banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block CYAN_BANNER;
    @WildWindTag(names = "banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block LIGHT_BLUE_BANNER;
    @WildWindTag(names = "banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block LIGHT_GRAY_BANNER;
    @WildWindTag(names = "banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block LIME_BANNER;
    @WildWindTag(names = "banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block MAGENTA_BANNER;
    @WildWindTag(names = "banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block ORANGE_BANNER;
    @WildWindTag(names = "banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block PINK_BANNER;
    @WildWindTag(names = "banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block PURPLE_BANNER;
    @WildWindTag(names = "banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block RED_BANNER;
    @WildWindTag(names = "banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block WHITE_BANNER;
    @WildWindTag(names = "banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block YELLOW_BANNER;
    @WildWindTag(names = "wall_banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block BLACK_WALL_BANNER;
    @WildWindTag(names = "wall_banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block BLUE_WALL_BANNER;
    @WildWindTag(names = "wall_banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block BROWN_WALL_BANNER;
    @WildWindTag(names = "wall_banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block GRAY_WALL_BANNER;
    @WildWindTag(names = "wall_banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block GREEN_WALL_BANNER;
    @WildWindTag(names = "wall_banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block CYAN_WALL_BANNER;
    @WildWindTag(names = "wall_banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block LIGHT_BLUE_WALL_BANNER;
    @WildWindTag(names = "wall_banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block LIGHT_GRAY_WALL_BANNER;
    @WildWindTag(names = "wall_banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block LIME_WALL_BANNER;
    @WildWindTag(names = "wall_banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block MAGENTA_WALL_BANNER;
    @WildWindTag(names = "wall_banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block ORANGE_WALL_BANNER;
    @WildWindTag(names = "wall_banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block PINK_WALL_BANNER;
    @WildWindTag(names = "wall_banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block PURPLE_WALL_BANNER;
    @WildWindTag(names = "wall_banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block RED_WALL_BANNER;
    @WildWindTag(names = "wall_banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block WHITE_WALL_BANNER;
    @WildWindTag(names = "wall_banners", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block YELLOW_WALL_BANNER;
    @CTag(names = "concrete_powder", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block BLACK_CONCRETE_POWDER;
    @CTag(names = "concrete_powder", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block BLUE_CONCRETE_POWDER;
    @CTag(names = "concrete_powder", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block BROWN_CONCRETE_POWDER;
    @CTag(names = "concrete_powder", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block CYAN_CONCRETE_POWDER;
    @CTag(names = "concrete_powder", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block GRAY_CONCRETE_POWDER;
    @CTag(names = "concrete_powder", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block GREEN_CONCRETE_POWDER;
    @CTag(names = "concrete_powder", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block LIGHT_BLUE_CONCRETE_POWDER;
    @CTag(names = "concrete_powder", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block LIGHT_GRAY_CONCRETE_POWDER;
    @CTag(names = "concrete_powder", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block LIME_CONCRETE_POWDER;
    @CTag(names = "concrete_powder", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block MAGENTA_CONCRETE_POWDER;
    @CTag(names = "concrete_powder", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block ORANGE_CONCRETE_POWDER;
    @CTag(names = "concrete_powder", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block PINK_CONCRETE_POWDER;
    @CTag(names = "concrete_powder", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block PURPLE_CONCRETE_POWDER;
    @CTag(names = "concrete_powder", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block RED_CONCRETE_POWDER;
    @CTag(names = "concrete_powder", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block WHITE_CONCRETE_POWDER;
    @CTag(names = "concrete_powder", type = TagType.Block, mixin = true)
    @Shadow @Final public static Block YELLOW_CONCRETE_POWDER;
    
    static {

    }

}
