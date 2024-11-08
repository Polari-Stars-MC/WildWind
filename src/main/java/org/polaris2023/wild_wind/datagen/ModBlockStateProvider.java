package org.polaris2023.wild_wind.datagen;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.block.GlowMucusBlock;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.util.Helpers;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, WildWindMod.MOD_ID, exFileHelper);
    }

    public static final ResourceLocation[] GLOW_MUCUS_LIGHTS = new ResourceLocation[] {
            Helpers.location("block/glow_mucus_light3"),
            Helpers.location("block/glow_mucus_light6"),
            Helpers.location("block/glow_mucus_light9"),
            Helpers.location("block/glow_mucus_light12"),
            Helpers.location("block/glow_mucus_light15"),
    };

    @Override
    protected void registerStatesAndModels() {

        VariantBlockStateBuilder glowMucusStates = getVariantBuilder(ModBlocks.GLOW_MUCUS.get());



        for (Direction value : Direction.values()) {
            AtomicInteger x = new AtomicInteger();
            AtomicInteger y = new AtomicInteger();
            switch (value) {
                case UP -> x.set(180);
                case NORTH, WEST -> {
                    x.set(270);
                    if (value.equals(Direction.WEST)) {
                        y.set(270);
                    }
                }
                case SOUTH, EAST -> {
                    x.set(90);
                    if (value.equals(Direction.EAST)) {
                        y.set(270);
                    }
                }

            }
            for (Integer possibleValue : GlowMucusBlock.LAYERS.getPossibleValues()) {
                glow_mucus_model(glowMucusStates, value, possibleValue, i -> new ConfiguredModel(models().getExistingFile(GLOW_MUCUS_LIGHTS[i]), x.get(), y.get(), false));
            }

        }
    }

    private void glow_mucus_model(VariantBlockStateBuilder glowMucusStates, Direction facing, int layers, Function<Integer, ConfiguredModel> function) {
        glowMucusStates.addModels(
                glowMucusStates
                        .partialState()
                        .with(GlowMucusBlock.FACING, facing)
                        .with(GlowMucusBlock.LAYERS, layers),
                        function.apply(layers - 1)
                );

    }
}
