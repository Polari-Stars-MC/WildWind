package org.polaris2023.wild_wind.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

public class SoulJackLanternBlock extends Block {
    public SoulJackLanternBlock() {
        super(BlockBehaviour.Properties.of().sound(SoundType.GRAVEL).strength(1f, 10f).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true));
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 15;
    }

}
