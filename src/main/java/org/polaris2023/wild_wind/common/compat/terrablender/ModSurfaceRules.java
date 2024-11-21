package org.polaris2023.wild_wind.common.compat.terrablender;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public enum ModSurfaceRules implements Supplier<SurfaceRules.RuleSource> {
    AIR(Blocks.AIR),
    BASALT(Blocks.BASALT),
    BEDROCK(Blocks.BEDROCK),
    BLACKSTONE(Blocks.BLACKSTONE),
    CALCITE(Blocks.CALCITE),
    COARSE_DIRT(Blocks.COARSE_DIRT),
    CRIMSON_NYLIUM(Blocks.CRIMSON_NYLIUM),
    CRYING_OBSIDIAN(Blocks.CRYING_OBSIDIAN),
    DEEPSLATE(Blocks.DEEPSLATE),
    DIRT(Blocks.DIRT),
    DRIPSTONE_BLOCK(Blocks.DRIPSTONE_BLOCK),
    GRASS_BLOCK(Blocks.GRASS_BLOCK),
    GRAVEL(Blocks.GRAVEL),
    ICE(Blocks.ICE),
    LAVA(Blocks.LAVA),
    MAGMA_BLOCK(Blocks.MAGMA_BLOCK),
    MOSS_BLOCK(Blocks.MOSS_BLOCK),
    MUD(Blocks.MUD),
    MYCELIUM(Blocks.MYCELIUM),
    NETHER_WART_BLOCK(Blocks.NETHER_WART_BLOCK),
    NETHERRACK(Blocks.NETHERRACK),
    ORANGE_TERRACOTTA(Blocks.ORANGE_TERRACOTTA),
    PACKED_ICE(Blocks.PACKED_ICE),
    PODZOL(Blocks.PODZOL),
    POWDER_SNOW(Blocks.POWDER_SNOW),
    RED_SAND(Blocks.RED_SAND),
    RED_SANDSTONE(Blocks.RED_SANDSTONE),
    SAND(Blocks.SAND),
    SANDSTONE(Blocks.SANDSTONE),
    SNOW_BLOCK(Blocks.SNOW_BLOCK),
    SOUL_SAND(Blocks.SOUL_SAND),
    SOUL_SOIL(Blocks.SOUL_SOIL),
    STONE(Blocks.STONE),
    TERRACOTTA(Blocks.TERRACOTTA),
    WARPED_NYLIUM(Blocks.WARPED_NYLIUM),
    WARPED_WART_BLOCK(Blocks.WARPED_WART_BLOCK),
    WATER(Blocks.WATER),
    WHITE_TERRACOTTA(Blocks.WHITE_TERRACOTTA),

    ;

    private final SurfaceRules.RuleSource source;

    ModSurfaceRules(Block block) {
        source = makeStateRule(block);
    }

    ModSurfaceRules(DeferredBlock<Block> block) {
        source = makeStateRule(block.get());
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }

    public static SurfaceRules.RuleSource overworld() {
        return overworldLike(true, false, true);
    }

    @SuppressWarnings("unused")
    public static SurfaceRules.RuleSource overworldLike(boolean checkAbovePreliminarySurface, boolean bedrockRoof, boolean bedrockFloor) {
        SurfaceRules.ConditionSource above97_2 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(97), 2);
        SurfaceRules.ConditionSource above256 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(256), 0);
        SurfaceRules.ConditionSource above63_1 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(63), -1);
        SurfaceRules.ConditionSource above74_1 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(74), 1);
        SurfaceRules.ConditionSource above60 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(60), 0);
        SurfaceRules.ConditionSource above62 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0);
        SurfaceRules.ConditionSource above63 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0);
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.ConditionSource isAboveWaterLevel = SurfaceRules.waterBlockCheck(0, 0);
        SurfaceRules.ConditionSource isUnderWaterLevel = SurfaceRules.waterStartCheck(-6, -1);
        SurfaceRules.ConditionSource isHole = SurfaceRules.hole();
        SurfaceRules.ConditionSource isFrozenOcean = SurfaceRules.isBiome(Biomes.FROZEN_OCEAN, Biomes.DEEP_FROZEN_OCEAN);
        SurfaceRules.ConditionSource isSteep = SurfaceRules.steep();
        SurfaceRules.ConditionSource isSandSurfaceBiomes = SurfaceRules.isBiome(Biomes.WARM_OCEAN, Biomes.BEACH, Biomes.SNOWY_BEACH);
        SurfaceRules.ConditionSource isDesert = SurfaceRules.isBiome(Biomes.DESERT);

        SurfaceRules.ConditionSource isBandNeg = SurfaceRules.noiseCondition(Noises.SURFACE, -0.909D, -0.5454D);
        SurfaceRules.ConditionSource isBandZero = SurfaceRules.noiseCondition(Noises.SURFACE, -0.1818D, 0.1818D);
        SurfaceRules.ConditionSource isBandPos = SurfaceRules.noiseCondition(Noises.SURFACE, 0.5454D, 0.909D);

        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAboveWaterLevel, GRASS_BLOCK.get()), DIRT.get());
        SurfaceRules.RuleSource sandstoneLinedSand = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, SANDSTONE.get()), SAND.get());
        SurfaceRules.RuleSource stoneLinedGravel = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, STONE.get()), GRAVEL.get());
        SurfaceRules.RuleSource hillAndSeaAndDesertSource = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.STONY_PEAKS),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.CALCITE, -0.0125D, 0.0125D), CALCITE.get()), STONE.get())
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.STONY_SHORE),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.GRAVEL, -0.05D, 0.05D), stoneLinedGravel),
                                STONE.get()
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.WINDSWEPT_HILLS),
                        SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), STONE.get())
                ),
                SurfaceRules.ifTrue(isSandSurfaceBiomes, sandstoneLinedSand),
                SurfaceRules.ifTrue(isDesert, sandstoneLinedSand),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.DRIPSTONE_CAVES), STONE.get())
        );
        SurfaceRules.RuleSource smallPowderSnow = SurfaceRules.ifTrue(
                SurfaceRules.noiseCondition(Noises.POWDER_SNOW, 0.45D, 0.58D),
                SurfaceRules.ifTrue(isAboveWaterLevel, POWDER_SNOW.get())
        );
        SurfaceRules.RuleSource largePowderSnow = SurfaceRules.ifTrue(
                SurfaceRules.noiseCondition(Noises.POWDER_SNOW, 0.35D, 0.6D),
                SurfaceRules.ifTrue(isAboveWaterLevel, POWDER_SNOW.get())
        );
        SurfaceRules.RuleSource underSurfaceSource = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.FROZEN_PEAKS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(isSteep, PACKED_ICE.get()),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.PACKED_ICE, -0.5D, 0.2D), PACKED_ICE.get()),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.ICE, -0.0625D, 0.025D), ICE.get()),
                                SurfaceRules.ifTrue(isAboveWaterLevel, SNOW_BLOCK.get())
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.SNOWY_SLOPES),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(isSteep, STONE.get()),
                                smallPowderSnow,
                                SurfaceRules.ifTrue(isAboveWaterLevel, SNOW_BLOCK.get())
                        )
                ),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.JAGGED_PEAKS), STONE.get()),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.GROVE), SurfaceRules.sequence(smallPowderSnow, DIRT.get())),
                hillAndSeaAndDesertSource,
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.WINDSWEPT_SAVANNA),
                        SurfaceRules.ifTrue(surfaceNoiseAbove(1.75D), STONE.get())
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.WINDSWEPT_GRAVELLY_HILLS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(surfaceNoiseAbove(2.0D),stoneLinedGravel),
                                SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), STONE.get()),
                                SurfaceRules.ifTrue(surfaceNoiseAbove(-1.0D), DIRT.get()),
                                stoneLinedGravel
                        )
                ),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.MANGROVE_SWAMP), MUD.get()),
                DIRT.get()
        );
        SurfaceRules.RuleSource surfaceSource = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.FROZEN_PEAKS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(isSteep, PACKED_ICE.get()),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.PACKED_ICE, 0.0D, 0.2D), PACKED_ICE.get()),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.ICE, 0.0D, 0.025D), ICE.get()),
                                SurfaceRules.ifTrue(isAboveWaterLevel, SNOW_BLOCK.get())
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.SNOWY_SLOPES),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(isSteep, STONE.get()),
                                largePowderSnow,
                                SurfaceRules.ifTrue(isAboveWaterLevel, SNOW_BLOCK.get())
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.JAGGED_PEAKS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(isSteep, STONE.get()),
                                SurfaceRules.ifTrue(isAboveWaterLevel, SNOW_BLOCK.get())
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.GROVE),
                        SurfaceRules.sequence(
                                largePowderSnow,
                                SurfaceRules.ifTrue(isAboveWaterLevel, SNOW_BLOCK.get())
                        )
                ),
                hillAndSeaAndDesertSource,
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.WINDSWEPT_SAVANNA),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(surfaceNoiseAbove(1.75D), STONE.get()),
                                SurfaceRules.ifTrue(surfaceNoiseAbove(-0.5D), COARSE_DIRT.get())
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.WINDSWEPT_GRAVELLY_HILLS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(surfaceNoiseAbove(2.0D), stoneLinedGravel),
                                SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), STONE.get()),
                                SurfaceRules.ifTrue(surfaceNoiseAbove(-1.0D), grassSurface),
                                stoneLinedGravel
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.OLD_GROWTH_PINE_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(surfaceNoiseAbove(1.75D), COARSE_DIRT.get()),
                                SurfaceRules.ifTrue(surfaceNoiseAbove(-0.95D), PODZOL.get())
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.ICE_SPIKES),
                        SurfaceRules.ifTrue(isAboveWaterLevel, SNOW_BLOCK.get())
                ),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.MANGROVE_SWAMP), MUD.get()),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.MUSHROOM_FIELDS), MYCELIUM.get()),
                grassSurface
        );

        SurfaceRules.RuleSource ruleSource = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.isBiome(Biomes.WOODED_BADLANDS),
                                        SurfaceRules.ifTrue(
                                                above97_2,
                                                SurfaceRules.sequence(
                                                        SurfaceRules.ifTrue(isBandNeg, COARSE_DIRT.get()),
                                                        SurfaceRules.ifTrue(isBandZero, COARSE_DIRT.get()),
                                                        SurfaceRules.ifTrue(isBandPos, COARSE_DIRT.get()),
                                                        grassSurface
                                                )
                                        )
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.isBiome(Biomes.SWAMP),
                                        SurfaceRules.ifTrue(
                                                above62,
                                                SurfaceRules.ifTrue(
                                                        SurfaceRules.not(above63),
                                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SWAMP, 0.0D), WATER.get())
                                                )
                                        )
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.isBiome(Biomes.MANGROVE_SWAMP),
                                        SurfaceRules.ifTrue(
                                                above60,
                                                SurfaceRules.ifTrue(
                                                        SurfaceRules.not(above63),
                                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SWAMP, 0.0), WATER.get())
                                                )
                                        )
                                )
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.BADLANDS, Biomes.ERODED_BADLANDS, Biomes.WOODED_BADLANDS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.ON_FLOOR,
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(above256, ORANGE_TERRACOTTA.get()),
                                                SurfaceRules.ifTrue(
                                                        above74_1,
                                                        SurfaceRules.sequence(
                                                                SurfaceRules.ifTrue(isBandNeg, TERRACOTTA.get()),
                                                                SurfaceRules.ifTrue(isBandZero, TERRACOTTA.get()),
                                                                SurfaceRules.ifTrue(isBandPos, TERRACOTTA.get()),
                                                                SurfaceRules.bandlands()
                                                        )
                                                ),
                                                SurfaceRules.ifTrue(
                                                        isAtOrAboveWaterLevel,
                                                        SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, RED_SANDSTONE.get()), RED_SAND.get())
                                                ),
                                                SurfaceRules.ifTrue(SurfaceRules.not(isHole), ORANGE_TERRACOTTA.get()),
                                                SurfaceRules.ifTrue(isUnderWaterLevel, WHITE_TERRACOTTA.get()),
                                                stoneLinedGravel
                                        )
                                ),
                                SurfaceRules.ifTrue(
                                        above63_1,
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(
                                                        above63,
                                                        SurfaceRules.ifTrue(SurfaceRules.not(above74_1), ORANGE_TERRACOTTA.get())
                                                ),
                                                SurfaceRules.bandlands()
                                        )
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_FLOOR,
                                        SurfaceRules.ifTrue(isUnderWaterLevel, WHITE_TERRACOTTA.get())
                                )
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.ifTrue(
                                isAtOrAboveWaterLevel,
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(
                                                isFrozenOcean,
                                                SurfaceRules.ifTrue(
                                                        isHole,
                                                        SurfaceRules.sequence(
                                                                SurfaceRules.ifTrue(isAboveWaterLevel, AIR.get()),
                                                                SurfaceRules.ifTrue(SurfaceRules.temperature(), ICE.get()),
                                                                WATER.get()
                                                        )
                                                )
                                        ),
                                        surfaceSource
                                )
                        )
                ),
                SurfaceRules.ifTrue(
                        isUnderWaterLevel,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(
                                                isFrozenOcean,
                                                SurfaceRules.ifTrue(isHole, WATER.get())
                                        )
                                ),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, underSurfaceSource),
                                SurfaceRules.ifTrue(isSandSurfaceBiomes, SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, SANDSTONE.get())),
                                SurfaceRules.ifTrue(isDesert, SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR, SANDSTONE.get()))
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.FROZEN_PEAKS, Biomes.JAGGED_PEAKS), STONE.get()),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.isBiome(Biomes.WARM_OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN),
                                        sandstoneLinedSand
                                ),
                                stoneLinedGravel
                        )
                )
        );

        ImmutableList.Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();
        if (bedrockRoof) {
            builder.add(SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("bedrock_roof", VerticalAnchor.belowTop(5), VerticalAnchor.top())), BEDROCK.get()));
        }

        if (bedrockFloor) {
            builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK.get()));
        }

        SurfaceRules.RuleSource abovePreliminary = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), ruleSource);
        builder.add(checkAbovePreliminarySurface ? abovePreliminary : ruleSource);
        builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("deepslate", VerticalAnchor.absolute(0), VerticalAnchor.absolute(8)), DEEPSLATE.get()));
        return SurfaceRules.sequence(builder.build().toArray(SurfaceRules.RuleSource[]::new));
    }

    public static SurfaceRules.RuleSource nether() {
        SurfaceRules.ConditionSource above31 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(31), 0);
        SurfaceRules.ConditionSource above32 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(32), 0);
        SurfaceRules.ConditionSource above30 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(30), 0);
        SurfaceRules.ConditionSource below35 = SurfaceRules.not(SurfaceRules.yStartCheck(VerticalAnchor.absolute(35), 0));
        SurfaceRules.ConditionSource isHole = SurfaceRules.hole();
        SurfaceRules.ConditionSource bedrockLayer = SurfaceRules.yBlockCheck(VerticalAnchor.belowTop(5), 0);
        SurfaceRules.ConditionSource soulSandNoised = SurfaceRules.noiseCondition(Noises.SOUL_SAND_LAYER, -0.012D);
        SurfaceRules.ConditionSource gravelNoised = SurfaceRules.noiseCondition(Noises.GRAVEL_LAYER, -0.012D);
        SurfaceRules.ConditionSource netherrackNoised = SurfaceRules.noiseCondition(Noises.NETHERRACK, 0.54D);
        SurfaceRules.ConditionSource wartBlockNoised = SurfaceRules.noiseCondition(Noises.NETHER_WART, 1.17D);
        SurfaceRules.ConditionSource netherStoneNoised = SurfaceRules.noiseCondition(Noises.NETHER_STATE_SELECTOR, 0.0D);
        SurfaceRules.RuleSource gravelSource = SurfaceRules.ifTrue(
                SurfaceRules.noiseCondition(Noises.PATCH, -0.012D),
                SurfaceRules.ifTrue(
                        above30,
                        SurfaceRules.ifTrue(below35, GRAVEL.get())
                ));

        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)),
                        BEDROCK.get()
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.not(SurfaceRules.verticalGradient("bedrock_roof", VerticalAnchor.belowTop(5), VerticalAnchor.top())),
                        BEDROCK.get()
                ),
                SurfaceRules.ifTrue(bedrockLayer, NETHERRACK.get()),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.BASALT_DELTAS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_CEILING, BASALT.get()),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_FLOOR,
                                        SurfaceRules.sequence(gravelSource, SurfaceRules.ifTrue(netherStoneNoised, BASALT.get()), BLACKSTONE.get())
                                )
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.SOUL_SAND_VALLEY),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_CEILING,
                                        SurfaceRules.sequence(SurfaceRules.ifTrue(netherStoneNoised, SOUL_SAND.get()), SOUL_SOIL.get())
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_FLOOR,
                                        SurfaceRules.sequence(gravelSource, SurfaceRules.ifTrue(netherStoneNoised, SOUL_SAND.get()), SOUL_SOIL.get())
                                )
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.not(above32),
                                        SurfaceRules.ifTrue(isHole, LAVA.get())
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.isBiome(Biomes.WARPED_FOREST),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.not(netherrackNoised),
                                                SurfaceRules.ifTrue(
                                                        above31,
                                                        SurfaceRules.sequence(
                                                                SurfaceRules.ifTrue(wartBlockNoised, WARPED_WART_BLOCK.get()),
                                                                WARPED_NYLIUM.get()
                                                        )
                                                )
                                        )
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.isBiome(Biomes.CRIMSON_FOREST),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.not(netherrackNoised),
                                                SurfaceRules.ifTrue(
                                                        above31,
                                                        SurfaceRules.sequence(
                                                                SurfaceRules.ifTrue(wartBlockNoised, NETHER_WART_BLOCK.get()),
                                                                CRIMSON_NYLIUM.get()
                                                        )
                                                )
                                        )
                                )
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.NETHER_WASTES),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_FLOOR,
                                        SurfaceRules.ifTrue(
                                                soulSandNoised,
                                                SurfaceRules.sequence(
                                                        SurfaceRules.ifTrue(
                                                                SurfaceRules.not(isHole),
                                                                SurfaceRules.ifTrue(above30, SurfaceRules.ifTrue(below35, SOUL_SAND.get()))
                                                        ),
                                                        NETHERRACK.get()
                                                )
                                        )
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(
                                                above31,
                                                SurfaceRules.ifTrue(
                                                        below35,
                                                        SurfaceRules.ifTrue(
                                                                gravelNoised,
                                                                SurfaceRules.sequence(
                                                                        SurfaceRules.ifTrue(above32, GRAVEL.get()),
                                                                        SurfaceRules.ifTrue(SurfaceRules.not(isHole), GRAVEL.get())
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                ),
                NETHERRACK.get()
        );
    }

    private static SurfaceRules.ConditionSource surfaceNoiseAbove(double threshold) {
        return SurfaceRules.noiseCondition(Noises.SURFACE, threshold / 8.25D, Double.MAX_VALUE);
    }

    @Override
    public SurfaceRules.RuleSource get() {
        return source;
    }
}
