package org.polaris2023.wild_wind.datagen.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.neoforged.neoforge.common.Tags.Biomes;
import org.polaris2023.wild_wind.common.structure.RuinedChapelStructure;
import org.polaris2023.wild_wind.util.Helpers;

/**
 * @author asuka
 * @since 2025/07/21
 */
public class ModStructureModifierRegistry {
    public static final ResourceKey<Structure> RUINED_CHAPEL = createStructure("ruined_chapel");
    public static final ResourceKey<StructureSet> RUINED_CHAPEL_SET = createStructureSet("ruined_chapels");

    public static void bootstrapStructure(BootstrapContext<Structure> context) {
        HolderGetter<Biome> holdergetter = context.lookup(Registries.BIOME);

        context.register(
            RUINED_CHAPEL,
            new RuinedChapelStructure(
                new Structure.StructureSettings.Builder(holdergetter.getOrThrow(Biomes.IS_SWAMP)).build()
            )
        );
    }

    public static void bootstrapStructureSet(BootstrapContext<StructureSet> context) {
        HolderGetter<Structure> holdergetter = context.lookup(Registries.STRUCTURE);

        context.register(
            RUINED_CHAPEL_SET,
            new StructureSet(holdergetter.getOrThrow(RUINED_CHAPEL), new RandomSpreadStructurePlacement(32, 8,
                RandomSpreadType.LINEAR, 812731892))
        );
    }

    private static ResourceKey<Structure> createStructure(String name) {
        return ResourceKey.create(Registries.STRUCTURE, Helpers.location(name));
    }

    private static ResourceKey<StructureSet> createStructureSet(String name) {
        return ResourceKey.create(Registries.STRUCTURE_SET, Helpers.location(name));
    }
}
