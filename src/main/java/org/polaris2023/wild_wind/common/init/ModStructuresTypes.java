package org.polaris2023.wild_wind.common.init;

import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.wild_wind.common.structure.RuinedChapelStructure;
import org.polaris2023.wild_wind.common.structure.RuinedChapelStructurePiece;

import static org.polaris2023.wild_wind.common.init.ModInitializer.STRUCTURE_PIECE;
import static org.polaris2023.wild_wind.common.init.ModInitializer.STRUCTURE_TYPE;

/**
 * @author asuka
 * @since 2025/07/21
 */
public class ModStructuresTypes {
    public static final DeferredHolder<StructureType<?>, StructureType<RuinedChapelStructure>> RUINED_CHAPEL_TYPE =
        STRUCTURE_TYPE.register("ruined_chapel", () -> () -> RuinedChapelStructure.CODEC);

    public static final DeferredHolder<StructurePieceType, StructurePieceType> RUINED_CHAPEL_PIECE_TYPE =
        STRUCTURE_PIECE.register("ruined_chapel_piece", () -> (s,t)->
            new RuinedChapelStructurePiece(s.structureTemplateManager(), t)
        );
}
