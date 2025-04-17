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
@SuppressWarnings("UnusedMixin")
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




}
