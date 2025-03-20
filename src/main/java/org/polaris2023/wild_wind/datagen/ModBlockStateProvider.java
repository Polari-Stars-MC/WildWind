package org.polaris2023.wild_wind.datagen;

import com.machinezoo.noexception.throwing.ThrowingConsumer;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.block.AshLayerBlock;
import org.polaris2023.wild_wind.common.block.BrittleIceBlock;
import org.polaris2023.wild_wind.common.block.GlowMucusBlock;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.datagen.custom.WildWindClientProvider;
import org.polaris2023.wild_wind.util.Helpers;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;


@Deprecated(forRemoval = true)
public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, WildWindMod.MOD_ID, exFileHelper);
    }
    public static final ResourceLocation[] BRITTLE_ICES = new ResourceLocation[] {
            Helpers.location("block/brittle_ice_0"),
            Helpers.location("block/brittle_ice_1"),
            Helpers.location("block/brittle_ice_2"),
            Helpers.location("block/brittle_ice_3"),
    };
    public static final ResourceLocation[] ASH_LAYERS = new ResourceLocation[] {
            Helpers.location("block/ash_0"),
            Helpers.location("block/ash_1"),
            Helpers.location("block/ash_2"),
            Helpers.location("block/ash_3"),
            Helpers.location("block/ash_4"),
            Helpers.location("block/ash_5"),
            Helpers.location("block/ash_6"),
            Helpers.location("block/ash_7"),
            Helpers.location("block/ash_8"),
    };

    @Override
    protected void registerStatesAndModels() {
        ThrowingConsumer<WildWindClientProvider> state = WildWindClientProvider::state;//use this
    }
    @Deprecated(forRemoval = true)
    private void glowMucusModel(VariantBlockStateBuilder glowMucusStates, Direction facing, int layers, Function<Integer, ConfiguredModel> function) {
        glowMucusStates.addModels(
                glowMucusStates
                        .partialState()
                        .with(GlowMucusBlock.FACING, facing)
                        .with(GlowMucusBlock.LAYERS, layers),
                function.apply(layers - 1)
        );
    }
    @Deprecated(forRemoval = true)
    private void brittleIceModel(VariantBlockStateBuilder brittleIceStates, int age, boolean unstable) {
        brittleIceStates.addModels(
                brittleIceStates.partialState().with(BrittleIceBlock.AGE, age).with(BrittleIceBlock.UNSTABLE, unstable),
                new ConfiguredModel(models().getExistingFile(BRITTLE_ICES[age]))
        );
    }
    @Deprecated(forRemoval = true)
    private void ashModel(VariantBlockStateBuilder ashStates, int layer) {
        ashStates.addModels(
                ashStates.partialState().with(AshLayerBlock.LAYERS, layer),
                new ConfiguredModel(models().getExistingFile(ASH_LAYERS[layer]))
        );
    }
}
