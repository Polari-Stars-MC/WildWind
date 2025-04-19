package org.polaris2023.wild_wind.mixin.unused;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
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
 * {@code @Date : 2025/04/16 22:01:27}
 */
@SuppressWarnings("ALL")
@Mixin(BlockTags.class)
@MixinDefine(value = "net.minecraft.tags.BlockTags")
@Removed
public class BlockTagsMixin {
    @CTag(names = "firefly_roost_block", type = TagType.Block, mixin = true, tag = true)
    @Shadow @Final public static TagKey<Block> FLOWERS;

    static {

    }
}
