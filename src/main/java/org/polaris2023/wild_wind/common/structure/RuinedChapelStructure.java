package org.polaris2023.wild_wind.common.structure;

import java.util.Optional;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import org.polaris2023.wild_wind.common.init.ModStructuresTypes;

/**
 * @author asuka
 * @since 2025/07/21
 */
public class RuinedChapelStructure extends Structure {
    public static final MapCodec<RuinedChapelStructure> CODEC = simpleCodec(RuinedChapelStructure::new);

    public RuinedChapelStructure(StructureSettings settings) {
        super(settings);
    }

    @Override
    public Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        ChunkPos chunkPos = context.chunkPos();
        int centerX = chunkPos.getMiddleBlockX();
        int centerZ = chunkPos.getMiddleBlockZ();

        // 多层地形分析
        TerrainAnalysis terrain = analyzeTerrainAdvanced(context, centerX, centerZ);

        // 如果地形不适合，直接返回
        if (!terrain.isSuitable()) {
            return Optional.empty();
        }

        // 计算最佳嵌入高度
        int embeddedHeight = calculateEmbeddedHeight(terrain);
        BlockPos structurePos = new BlockPos(centerX, embeddedHeight, centerZ);

        return onTopOfChunkCenter(context, Heightmap.Types.MOTION_BLOCKING,
            builder -> builder.addPiece(new RuinedChapelStructurePiece(
                context.structureTemplateManager(),
                structurePos
            )));
    }

    @Override
    public StructureType<?> type() {
        return ModStructuresTypes.RUINED_CHAPEL_TYPE.get();
    }

    private TerrainAnalysis analyzeTerrainAdvanced(GenerationContext context, int centerX, int centerZ) {
        int[][] heightMap = new int[5][5];
        int minHeight = Integer.MAX_VALUE;
        int maxHeight = Integer.MIN_VALUE;
        int totalHeight = 0;

        for (int x = 0; x < 5; x++) {
            for (int z = 0; z < 5; z++) {
                int sampleX = centerX + (x - 2) * 4; // -8, -4, 0, 4, 8
                int sampleZ = centerZ + (z - 2) * 4;

                int surfaceHeight = context.chunkGenerator().getBaseHeight(
                    sampleX, sampleZ,
                    Heightmap.Types.MOTION_BLOCKING,
                    context.heightAccessor(),
                    context.randomState());

                int solidHeight = context.chunkGenerator().getBaseHeight(
                    sampleX, sampleZ,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    context.heightAccessor(),
                    context.randomState());

                // 选择较低的高度作为真实地面
                int realGroundHeight = Math.min(surfaceHeight, solidHeight);

                heightMap[x][z] = realGroundHeight;
                minHeight = Math.min(minHeight, realGroundHeight);
                maxHeight = Math.max(maxHeight, realGroundHeight);
                totalHeight += realGroundHeight;
            }
        }

        return new TerrainAnalysis(heightMap, minHeight, maxHeight, totalHeight / 25);
    }

    private int calculateEmbeddedHeight(TerrainAnalysis terrain) {
        int averageHeight = terrain.averageHeight;
        int minHeight = terrain.minHeight;

        // 选择一个略低于平均高度的位置
        int embeddedHeight = (int) (minHeight * 0.3 + averageHeight * 0.7);

        return Math.max(minHeight - 3, embeddedHeight);
    }

    private static class TerrainAnalysis {
        final int[][] heightMap;
        final int minHeight;
        final int maxHeight;
        final int averageHeight;
        final int heightVariation;

        TerrainAnalysis(int[][] heightMap, int minHeight, int maxHeight, int averageHeight) {
            this.heightMap = heightMap;
            this.minHeight = minHeight;
            this.maxHeight = maxHeight;
            this.averageHeight = averageHeight;
            this.heightVariation = maxHeight - minHeight;
        }

        boolean isSuitable() {
            return heightVariation <= 10;
        }
    }
}
