package org.polaris2023.wild_wind.server.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.SnowAndFreezeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.common.Tags;
import org.polaris2023.wild_wind.common.init.ModBlocks;

public class AshFeature extends SnowAndFreezeFeature {
    public AshFeature(Codec<NoneFeatureConfiguration> p_66836_) {
        super(p_66836_);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();
        BlockPos.MutableBlockPos placePos = new BlockPos.MutableBlockPos();
        for (int x = -4; x <= 4; x++) {
            for (int y = -2; y <= 2; y++) {
                for (int z = -4; z <= 4; z++) {
                    if (x * x + z * z <= 4 * 4 && (level.getBlockState(pos.offset(x, y, z)).is(Blocks.BASALT) || level.getBlockState(pos.offset(x, y, z)).is(Blocks.BLACKSTONE)) && level.getBlockState(pos.offset(x, y + 1, z)).isAir() && random.nextBoolean()) {
                        placePos.setWithOffset(pos, x, y + 1, z);
                        BlockState pileState = ModBlocks.ASH.get().defaultBlockState();
                        if (pileState.hasProperty(BlockStateProperties.LAYERS)) {
                            pileState = pileState.setValue(BlockStateProperties.LAYERS, random.nextInt(1, 5));
                        }
                        level.setBlock(placePos, pileState, 3);
                    }
                }
            }
        }
        return true;
    }
}
