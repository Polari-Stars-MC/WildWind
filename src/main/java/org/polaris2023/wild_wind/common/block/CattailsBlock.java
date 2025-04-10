package org.polaris2023.wild_wind.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.IShearable;
import org.polaris2023.wild_wind.common.init.tags.ModBlockTags;

public class CattailsBlock extends TallFlowerBlock implements IShearable {
    public CattailsBlock(Properties props) {
        super(props);
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        Vec3 vec3 = new Vec3(0.5F, 0.25F, 0.5F);

        entity.makeStuckInBlock(state, vec3);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(ModBlockTags.CATTAILS_MAY_PLACE.get());
    }
}

