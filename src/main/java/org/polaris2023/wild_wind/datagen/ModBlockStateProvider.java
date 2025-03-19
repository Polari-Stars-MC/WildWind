package org.polaris2023.wild_wind.datagen;

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
        // Glow Mucus
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
                glowMucusModel(glowMucusStates, value, possibleValue, i -> new ConfiguredModel(models().getExistingFile(GLOW_MUCUS_LIGHTS[i]), x.get(), y.get(), false));
            }
        }

        // Brittle Ice
        VariantBlockStateBuilder brittleIceStates = getVariantBuilder(ModBlocks.BRITTLE_ICE.get());
        for(int age : BrittleIceBlock.AGE.getPossibleValues()) {
            for(boolean unstable : BrittleIceBlock.UNSTABLE.getPossibleValues()) {
                brittleIceModel(brittleIceStates, age, unstable);
            }
        }

        // Ash
        VariantBlockStateBuilder ashStates = getVariantBuilder(ModBlocks.ASH.get());
        for(int layer : AshLayerBlock.LAYERS.getPossibleValues()) {
            ashModel(ashStates, layer);
        }

        // Sapling
        simpleBlock(ModBlocks.PALM_SAPLING.get(), models().cross("palm_sapling", Helpers.location("block/palm_sapling")).renderType("cutout"));
        simpleBlock(ModBlocks.BAOBAB_SAPLING.get(), models().cross("baobab_sapling", Helpers.location("block/baobab_sapling")).renderType("cutout"));

        // Tiny Cactus
        simpleBlock(ModBlocks.TINY_CACTUS.get(), models().cross("tiny_cactus", Helpers.location("block/tiny_cactus")).renderType("cutout"));

        // Carpet
        simpleBlockWithItem(ModBlocks.CARPET.get(), models().carpet("carpet", Helpers.location("block/wool")));

        // Glazed Terracotta
        VariantBlockStateBuilder glazedTerracottaStates = getVariantBuilder(ModBlocks.GLAZED_TERRACOTTA.get());
        for (Direction facing : Direction.Plane.HORIZONTAL) {
            int yRotation = switch (facing) {
                case EAST -> 270;
                case NORTH -> 180;
				case WEST -> 90;
                default -> 0;
            };
            glazedTerracottaStates.partialState().with(BlockStateProperties.HORIZONTAL_FACING, facing)
                    .addModels(new ConfiguredModel(models().singleTexture("glazed_terracotta", ResourceLocation.withDefaultNamespace("block/template_glazed_terracotta"),
                            "pattern", Helpers.location("block/glazed_terracotta")), 0, yRotation, false));
        }

        //glistering_melon
        BlockModelBuilder glisteringMelonModel = models().cubeBottomTop(
                "glistering_melon",
                Helpers.location("block/glistering_melon_side"),
                Helpers.location("block/glistering_melon_side"),
                Helpers.location("block/glistering_melon_top")
        );
        simpleBlock(ModBlocks.GLISTERING_MELON.get(), glisteringMelonModel);
    }

    private void glowMucusModel(VariantBlockStateBuilder glowMucusStates, Direction facing, int layers, Function<Integer, ConfiguredModel> function) {
        glowMucusStates.addModels(
                glowMucusStates
                        .partialState()
                        .with(GlowMucusBlock.FACING, facing)
                        .with(GlowMucusBlock.LAYERS, layers),
                function.apply(layers - 1)
        );
    }

    private void brittleIceModel(VariantBlockStateBuilder brittleIceStates, int age, boolean unstable) {
        brittleIceStates.addModels(
                brittleIceStates.partialState().with(BrittleIceBlock.AGE, age).with(BrittleIceBlock.UNSTABLE, unstable),
                new ConfiguredModel(models().getExistingFile(BRITTLE_ICES[age]))
        );
    }

    private void ashModel(VariantBlockStateBuilder ashStates, int layer) {
        ashStates.addModels(
                ashStates.partialState().with(AshLayerBlock.LAYERS, layer),
                new ConfiguredModel(models().getExistingFile(ASH_LAYERS[layer]))
        );
    }
}
