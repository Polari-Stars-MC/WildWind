package org.polaris2023.wild_wind.common.compat.terrablender;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource AIR = makeStateRule(Blocks.AIR);
    private static final SurfaceRules.RuleSource BASALT = makeStateRule(Blocks.BASALT);
    private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);
    private static final SurfaceRules.RuleSource BLACKSTONE = makeStateRule(Blocks.BLACKSTONE);
    private static final SurfaceRules.RuleSource CALCITE = makeStateRule(Blocks.CALCITE);
    private static final SurfaceRules.RuleSource COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
    private static final SurfaceRules.RuleSource CRIMSON_NYLIUM = makeStateRule(Blocks.CRIMSON_NYLIUM);
    private static final SurfaceRules.RuleSource CRYING_OBSIDIAN = makeStateRule(Blocks.CRYING_OBSIDIAN);
    private static final SurfaceRules.RuleSource DEEPSLATE = makeStateRule(Blocks.DEEPSLATE);
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource DRIPSTONE_BLOCK = makeStateRule(Blocks.DRIPSTONE_BLOCK);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource GRAVEL = makeStateRule(Blocks.GRAVEL);
    private static final SurfaceRules.RuleSource ICE = makeStateRule(Blocks.ICE);
    private static final SurfaceRules.RuleSource LAVA = makeStateRule(Blocks.LAVA);
    private static final SurfaceRules.RuleSource MAGMA_BLOCK = makeStateRule(Blocks.MAGMA_BLOCK);
    private static final SurfaceRules.RuleSource MOSS_BLOCK = makeStateRule(Blocks.MOSS_BLOCK);
    private static final SurfaceRules.RuleSource MUD = makeStateRule(Blocks.MUD);
    private static final SurfaceRules.RuleSource MYCELIUM = makeStateRule(Blocks.MYCELIUM);
    private static final SurfaceRules.RuleSource NETHER_WART_BLOCK = makeStateRule(Blocks.NETHER_WART_BLOCK);
    private static final SurfaceRules.RuleSource NETHERRACK = makeStateRule(Blocks.NETHERRACK);
    private static final SurfaceRules.RuleSource ORANGE_TERRACOTTA = makeStateRule(Blocks.ORANGE_TERRACOTTA);
    private static final SurfaceRules.RuleSource PACKED_ICE = makeStateRule(Blocks.PACKED_ICE);
    private static final SurfaceRules.RuleSource PODZOL = makeStateRule(Blocks.PODZOL);
    private static final SurfaceRules.RuleSource POWDER_SNOW = makeStateRule(Blocks.POWDER_SNOW);
    private static final SurfaceRules.RuleSource RED_SAND = makeStateRule(Blocks.RED_SAND);
    private static final SurfaceRules.RuleSource RED_SANDSTONE = makeStateRule(Blocks.RED_SANDSTONE);
    private static final SurfaceRules.RuleSource SAND = makeStateRule(Blocks.SAND);
    private static final SurfaceRules.RuleSource SANDSTONE = makeStateRule(Blocks.SANDSTONE);
    private static final SurfaceRules.RuleSource SNOW_BLOCK = makeStateRule(Blocks.SNOW_BLOCK);
    private static final SurfaceRules.RuleSource SOUL_SAND = makeStateRule(Blocks.SOUL_SAND);
    private static final SurfaceRules.RuleSource SOUL_SOIL = makeStateRule(Blocks.SOUL_SOIL);
    private static final SurfaceRules.RuleSource STONE = makeStateRule(Blocks.STONE);
    private static final SurfaceRules.RuleSource TERRACOTTA = makeStateRule(Blocks.TERRACOTTA);
    private static final SurfaceRules.RuleSource WARPED_NYLIUM = makeStateRule(Blocks.WARPED_NYLIUM);
    private static final SurfaceRules.RuleSource WARPED_WART_BLOCK = makeStateRule(Blocks.WARPED_WART_BLOCK);
    private static final SurfaceRules.RuleSource WATER = makeStateRule(Blocks.WATER);
    private static final SurfaceRules.RuleSource WHITE_TERRACOTTA = makeStateRule(Blocks.WHITE_TERRACOTTA);

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

        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAboveWaterLevel, GRASS_BLOCK), DIRT);
        SurfaceRules.RuleSource sandstoneLinedSand = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, SANDSTONE), SAND);
        SurfaceRules.RuleSource stoneLinedGravel = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, STONE), GRAVEL);
        SurfaceRules.RuleSource hillAndSeaAndDesertSource = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.STONY_PEAKS),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.CALCITE, -0.0125D, 0.0125D), CALCITE), STONE)
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.STONY_SHORE),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.GRAVEL, -0.05D, 0.05D), stoneLinedGravel),
                                STONE
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.WINDSWEPT_HILLS),
                        SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), STONE)
                ),
                SurfaceRules.ifTrue(isSandSurfaceBiomes, sandstoneLinedSand),
                SurfaceRules.ifTrue(isDesert, sandstoneLinedSand),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.DRIPSTONE_CAVES), STONE)
        );
        SurfaceRules.RuleSource smallPowderSnow = SurfaceRules.ifTrue(
                SurfaceRules.noiseCondition(Noises.POWDER_SNOW, 0.45D, 0.58D),
                SurfaceRules.ifTrue(isAboveWaterLevel, POWDER_SNOW)
        );
        SurfaceRules.RuleSource largePowderSnow = SurfaceRules.ifTrue(
                SurfaceRules.noiseCondition(Noises.POWDER_SNOW, 0.35D, 0.6D),
                SurfaceRules.ifTrue(isAboveWaterLevel, POWDER_SNOW)
        );
        SurfaceRules.RuleSource underSurfaceSource = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.FROZEN_PEAKS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(isSteep, PACKED_ICE),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.PACKED_ICE, -0.5D, 0.2D), PACKED_ICE),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.ICE, -0.0625D, 0.025D), ICE),
                                SurfaceRules.ifTrue(isAboveWaterLevel, SNOW_BLOCK)
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.SNOWY_SLOPES),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(isSteep, STONE),
                                smallPowderSnow,
                                SurfaceRules.ifTrue(isAboveWaterLevel, SNOW_BLOCK)
                        )
                ),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.JAGGED_PEAKS), STONE),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.GROVE), SurfaceRules.sequence(smallPowderSnow, DIRT)),
                hillAndSeaAndDesertSource,
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.WINDSWEPT_SAVANNA),
                        SurfaceRules.ifTrue(surfaceNoiseAbove(1.75D), STONE)
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.WINDSWEPT_GRAVELLY_HILLS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(surfaceNoiseAbove(2.0D),stoneLinedGravel),
                                SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), STONE),
                                SurfaceRules.ifTrue(surfaceNoiseAbove(-1.0D), DIRT),
                                stoneLinedGravel
                        )
                ),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.MANGROVE_SWAMP), MUD),
                DIRT
        );
        SurfaceRules.RuleSource surfaceSource = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.FROZEN_PEAKS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(isSteep, PACKED_ICE),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.PACKED_ICE, 0.0D, 0.2D), PACKED_ICE),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.ICE, 0.0D, 0.025D), ICE),
                                SurfaceRules.ifTrue(isAboveWaterLevel, SNOW_BLOCK)
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.SNOWY_SLOPES),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(isSteep, STONE),
                                largePowderSnow,
                                SurfaceRules.ifTrue(isAboveWaterLevel, SNOW_BLOCK)
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.JAGGED_PEAKS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(isSteep, STONE),
                                SurfaceRules.ifTrue(isAboveWaterLevel, SNOW_BLOCK)
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.GROVE),
                        SurfaceRules.sequence(
                                largePowderSnow,
                                SurfaceRules.ifTrue(isAboveWaterLevel, SNOW_BLOCK)
                        )
                ),
                hillAndSeaAndDesertSource,
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.WINDSWEPT_SAVANNA),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(surfaceNoiseAbove(1.75D), STONE),
                                SurfaceRules.ifTrue(surfaceNoiseAbove(-0.5D), COARSE_DIRT)
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.WINDSWEPT_GRAVELLY_HILLS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(surfaceNoiseAbove(2.0D), stoneLinedGravel),
                                SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), STONE),
                                SurfaceRules.ifTrue(surfaceNoiseAbove(-1.0D), grassSurface),
                                stoneLinedGravel
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.OLD_GROWTH_PINE_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(surfaceNoiseAbove(1.75D), COARSE_DIRT),
                                SurfaceRules.ifTrue(surfaceNoiseAbove(-0.95D), PODZOL)
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.ICE_SPIKES),
                        SurfaceRules.ifTrue(isAboveWaterLevel, SNOW_BLOCK)
                ),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.MANGROVE_SWAMP), MUD),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.MUSHROOM_FIELDS), MYCELIUM),
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
                                                        SurfaceRules.ifTrue(isBandNeg, COARSE_DIRT),
                                                        SurfaceRules.ifTrue(isBandZero, COARSE_DIRT),
                                                        SurfaceRules.ifTrue(isBandPos, COARSE_DIRT),
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
                                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SWAMP, 0.0D), WATER)
                                                )
                                        )
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.isBiome(Biomes.MANGROVE_SWAMP),
                                        SurfaceRules.ifTrue(
                                                above60,
                                                SurfaceRules.ifTrue(
                                                        SurfaceRules.not(above63),
                                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SWAMP, 0.0), WATER)
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
                                                SurfaceRules.ifTrue(above256, ORANGE_TERRACOTTA),
                                                SurfaceRules.ifTrue(
                                                        above74_1,
                                                        SurfaceRules.sequence(
                                                                SurfaceRules.ifTrue(isBandNeg, TERRACOTTA),
                                                                SurfaceRules.ifTrue(isBandZero, TERRACOTTA),
                                                                SurfaceRules.ifTrue(isBandPos, TERRACOTTA),
                                                                SurfaceRules.bandlands()
                                                        )
                                                ),
                                                SurfaceRules.ifTrue(
                                                        isAtOrAboveWaterLevel,
                                                        SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, RED_SANDSTONE), RED_SAND)
                                                ),
                                                SurfaceRules.ifTrue(SurfaceRules.not(isHole), ORANGE_TERRACOTTA),
                                                SurfaceRules.ifTrue(isUnderWaterLevel, WHITE_TERRACOTTA),
                                                stoneLinedGravel
                                        )
                                ),
                                SurfaceRules.ifTrue(
                                        above63_1,
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(
                                                        above63,
                                                        SurfaceRules.ifTrue(SurfaceRules.not(above74_1), ORANGE_TERRACOTTA)
                                                ),
                                                SurfaceRules.bandlands()
                                        )
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_FLOOR,
                                        SurfaceRules.ifTrue(isUnderWaterLevel, WHITE_TERRACOTTA)
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
                                                                SurfaceRules.ifTrue(isAboveWaterLevel, AIR),
                                                                SurfaceRules.ifTrue(SurfaceRules.temperature(), ICE),
                                                                WATER
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
                                                SurfaceRules.ifTrue(isHole, WATER)
                                        )
                                ),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, underSurfaceSource),
                                SurfaceRules.ifTrue(isSandSurfaceBiomes, SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, SANDSTONE)),
                                SurfaceRules.ifTrue(isDesert, SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR, SANDSTONE))
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.FROZEN_PEAKS, Biomes.JAGGED_PEAKS), STONE),
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
            builder.add(SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("bedrock_roof", VerticalAnchor.belowTop(5), VerticalAnchor.top())), BEDROCK));
        }

        if (bedrockFloor) {
            builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK));
        }

        SurfaceRules.RuleSource abovePreliminary = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), ruleSource);
        builder.add(checkAbovePreliminarySurface ? abovePreliminary : ruleSource);
        builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("deepslate", VerticalAnchor.absolute(0), VerticalAnchor.absolute(8)), DEEPSLATE));
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
                        SurfaceRules.ifTrue(below35, GRAVEL)
                ));

        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)),
                        BEDROCK
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.not(SurfaceRules.verticalGradient("bedrock_roof", VerticalAnchor.belowTop(5), VerticalAnchor.top())),
                        BEDROCK
                ),
                SurfaceRules.ifTrue(bedrockLayer, NETHERRACK),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.BASALT_DELTAS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_CEILING, BASALT),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_FLOOR,
                                        SurfaceRules.sequence(gravelSource, SurfaceRules.ifTrue(netherStoneNoised, BASALT), BLACKSTONE)
                                )
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.SOUL_SAND_VALLEY),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_CEILING,
                                        SurfaceRules.sequence(SurfaceRules.ifTrue(netherStoneNoised, SOUL_SAND), SOUL_SOIL)
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_FLOOR,
                                        SurfaceRules.sequence(gravelSource, SurfaceRules.ifTrue(netherStoneNoised, SOUL_SAND), SOUL_SOIL)
                                )
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.not(above32),
                                        SurfaceRules.ifTrue(isHole, LAVA)
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.isBiome(Biomes.WARPED_FOREST),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.not(netherrackNoised),
                                                SurfaceRules.ifTrue(
                                                        above31,
                                                        SurfaceRules.sequence(
                                                                SurfaceRules.ifTrue(wartBlockNoised, WARPED_WART_BLOCK),
                                                                WARPED_NYLIUM
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
                                                                SurfaceRules.ifTrue(wartBlockNoised, NETHER_WART_BLOCK),
                                                                CRIMSON_NYLIUM
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
                                                                SurfaceRules.ifTrue(above30, SurfaceRules.ifTrue(below35, SOUL_SAND))
                                                        ),
                                                        NETHERRACK
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
                                                                        SurfaceRules.ifTrue(above32, GRAVEL),
                                                                        SurfaceRules.ifTrue(SurfaceRules.not(isHole), GRAVEL)
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                ),
                NETHERRACK
        );
    }

    private static SurfaceRules.ConditionSource surfaceNoiseAbove(double threshold) {
        return SurfaceRules.noiseCondition(Noises.SURFACE, threshold / 8.25D, Double.MAX_VALUE);
    }
}
