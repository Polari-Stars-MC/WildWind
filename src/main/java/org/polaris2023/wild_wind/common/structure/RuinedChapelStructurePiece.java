package org.polaris2023.wild_wind.common.structure;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.polaris2023.wild_wind.common.init.ModStructuresTypes;
import org.polaris2023.wild_wind.util.Helpers;

/**
 * @author asuka
 * @since 2025/07/21
 */
public class RuinedChapelStructurePiece extends TemplateStructurePiece {
    public static final ResourceLocation STRUCTURE_ID = Helpers.location("ruined_chapel");

    public RuinedChapelStructurePiece(StructureTemplateManager manager, BlockPos pos) {
        super(ModStructuresTypes.RUINED_CHAPEL_PIECE_TYPE.get(), 0, manager, STRUCTURE_ID, STRUCTURE_ID.toString(), makeSettings(), pos);
    }

    public RuinedChapelStructurePiece(StructureTemplateManager structureTemplateManager, CompoundTag tag) {
        super(ModStructuresTypes.RUINED_CHAPEL_PIECE_TYPE.get(), tag, structureTemplateManager,
            r -> makeSettings());
    }

    private static StructurePlaceSettings makeSettings() {
        return new StructurePlaceSettings()
            .setRotation(Rotation.getRandom(RandomSource.create()))
            .setMirror(Mirror.values()[RandomSource.create().nextInt(Mirror.values().length)]);
    }

    private static StructurePlaceSettings makeSettings(Rotation rotation,Mirror mirror) {
        return new StructurePlaceSettings()
            .setRotation(rotation)
            .setMirror(mirror);
    }


    @Override
    protected void handleDataMarker(String s, BlockPos blockPos, ServerLevelAccessor serverLevelAccessor,
        RandomSource randomSource, BoundingBox boundingBox) {

    }


}
