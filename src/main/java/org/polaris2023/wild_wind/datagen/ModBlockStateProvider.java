package org.polaris2023.wild_wind.datagen;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.polaris2023.wild_wind.WildWindMod;
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
            Helpers.location("block/brittle_ice_3")
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

        // Logs
        simpleBlock(ModBlocks.PALM_CROWN.get(), models().cubeAll("palm_crown", Helpers.location("block/palm_crown")));
        simpleBlock(ModBlocks.PALM_LEAVES.get(), models().cubeAll("palm_leaves", Helpers.location("block/palm_leaves")).renderType("cutout_mipped"));
        simpleBlock(ModBlocks.BAOBAB_LEAVES.get(), models().cubeAll("baobab_leaves", Helpers.location("block/baobab_leaves")).renderType("cutout_mipped"));
        simpleBlock(ModBlocks.PALM_SAPLING.get(), models().cross("palm_sapling", Helpers.location("block/palm_sapling")).renderType("cutout"));
        simpleBlock(ModBlocks.BAOBAB_SAPLING.get(), models().cross("baobab_sapling", Helpers.location("block/baobab_sapling")).renderType("cutout"));

        // Wool
        simpleBlock(ModBlocks.WOOL.get(), models().cubeAll("wool", Helpers.location("block/wool")));

        // Carpet
        simpleBlock(ModBlocks.CARPET.get(), models().carpet("carpet", Helpers.location("block/wool")));

        // Concrete
        simpleBlock(ModBlocks.CONCRETE.get(), models().cubeAll("concrete", Helpers.location("block/concrete")));

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
                    .addModels(new ConfiguredModel(models().cubeAll("glazed_terracotta", Helpers.location("block/glazed_terracotta")), 0, yRotation, false));
        }
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

    private void logModel(VariantBlockStateBuilder woodStates, String name) {
        ResourceLocation side = Helpers.location("block/" + name + "_log");
        ResourceLocation top = Helpers.location("block/" + name + "_log_top");
        woodStates.addModels(
                woodStates.partialState().with(BlockStateProperties.AXIS, Direction.Axis.Y),
                new ConfiguredModel(models().cubeColumn(name + "_log", side, top))
        );
        BlockModelBuilder azaleaLogHorizontal = models().cubeColumnHorizontal(name + "_log_horizontal", side, top);
        woodStates.addModels(
                woodStates.partialState().with(BlockStateProperties.AXIS, Direction.Axis.X),
                new ConfiguredModel(azaleaLogHorizontal, 90, 90, false)
        );
        woodStates.addModels(
                woodStates.partialState().with(BlockStateProperties.AXIS, Direction.Axis.Z),
                new ConfiguredModel(azaleaLogHorizontal, 90, 0, false)
        );
    }

    private void woodModel(VariantBlockStateBuilder woodStates, String name) {
        ResourceLocation side = Helpers.location("block/" + name + "_log");
        woodStates.addModels(
                woodStates.partialState().with(BlockStateProperties.AXIS, Direction.Axis.Y),
                new ConfiguredModel(models().cubeColumn(name + "_wood", side, side))
        );
        BlockModelBuilder azaleaLogHorizontal = models().cubeColumnHorizontal(name + "_wood_horizontal", side, side);
        woodStates.addModels(
                woodStates.partialState().with(BlockStateProperties.AXIS, Direction.Axis.X),
                new ConfiguredModel(azaleaLogHorizontal, 90, 90, false)
        );
        woodStates.addModels(
                woodStates.partialState().with(BlockStateProperties.AXIS, Direction.Axis.Z),
                new ConfiguredModel(azaleaLogHorizontal, 90, 0, false)
        );
    }
}
