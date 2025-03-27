package org.polaris2023.wild_wind.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

/**
 * @author : baka4n
 * {@code @Date : 2025/03/26 21:14:45}
 * //模组炼药锅实现染色
 */
public class ModCauldronBlock extends Block {
    public static final IntegerProperty COLOR_R = IntegerProperty.create("color_r", 0, 255);
    public static final IntegerProperty COLOR_G = IntegerProperty.create("color_g", 0, 255);
    public static final IntegerProperty COLOR_B = IntegerProperty.create("color_b", 0, 255);

    public ModCauldronBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState()
                .setValue(COLOR_R, 0)
                .setValue(COLOR_G, 0)
                .setValue(COLOR_B, 0)
        );
    }
}
