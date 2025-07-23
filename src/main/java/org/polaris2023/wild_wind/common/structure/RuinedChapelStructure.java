package org.polaris2023.wild_wind.common.structure;

import java.util.Optional;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
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
        return onTopOfChunkCenter(context, Types.WORLD_SURFACE_WG, (builder) -> generatePieces(builder, context));
    }

    private void generatePieces(StructurePiecesBuilder builder, GenerationContext context) {
        ChunkPos chunkPos = context.chunkPos();

        int surfaceY = context.chunkGenerator().getBaseHeight(
            chunkPos.getMiddleBlockX(),
            chunkPos.getMiddleBlockZ(),
            Heightmap.Types.WORLD_SURFACE_WG,
            context.heightAccessor(),
            context.randomState()
        );

        BlockPos spawnPos = new BlockPos(
            chunkPos.getMiddleBlockX(),
            surfaceY,
            chunkPos.getMiddleBlockZ()
        );

        builder.addPiece(new RuinedChapelStructurePiece(
            context.structureTemplateManager(),
            spawnPos
        ));
    }

    @Override
    public StructureType<?> type() {
        return ModStructuresTypes.RUINED_CHAPEL_TYPE.get();
    }


}
