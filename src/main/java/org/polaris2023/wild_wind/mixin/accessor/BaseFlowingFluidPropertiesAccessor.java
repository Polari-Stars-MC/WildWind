package org.polaris2023.wild_wind.mixin.accessor;

import com.jcraft.jorbis.Block;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import javax.annotation.Nullable;
import java.util.function.Supplier;

/**
 * @author : baka4n
 * {@code @Date : 2025/05/05 01:23:19}
 */
@Mixin(BaseFlowingFluid.Properties.class)
public interface BaseFlowingFluidPropertiesAccessor {
    @Accessor
    @Nullable
    Supplier<Block> getBlock();
}
