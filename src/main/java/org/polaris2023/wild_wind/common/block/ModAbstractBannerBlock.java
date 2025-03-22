package org.polaris2023.wild_wind.common.block;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.AbstractBannerBlock;

public abstract class ModAbstractBannerBlock extends AbstractBannerBlock {
    protected ModAbstractBannerBlock(DyeColor color, Properties properties) {
        super(color, properties);
    }
}
