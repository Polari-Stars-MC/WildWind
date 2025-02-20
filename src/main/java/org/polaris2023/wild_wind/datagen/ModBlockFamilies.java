package org.polaris2023.wild_wind.datagen;

import net.minecraft.world.level.block.*;
import org.polaris2023.wild_wind.common.init.ModBlocks;

public class ModBlockFamilies {
	public static final ModBlockFamily AZALEA_PLANKS = family(
			ModBlocks.AZALEA_PLANKS.get(), ModBlocks.AZALEA_BUTTON.get(), ModBlocks.AZALEA_FENCE.get(), ModBlocks.AZALEA_FENCE_GATE.get(), ModBlocks.AZALEA_PRESSURE_PLATE.get(),
			ModBlocks.AZALEA_SIGN.get(), ModBlocks.AZALEA_WALL_SIGN.get(), ModBlocks.AZALEA_HANGING_SIGN.get(), ModBlocks.AZALEA_WALL_HANGING_SIGN.get(),
			ModBlocks.AZALEA_SLAB.get(), ModBlocks.AZALEA_STAIRS.get(), ModBlocks.AZALEA_DOOR.get(), ModBlocks.AZALEA_TRAPDOOR.get(), "wooden","has_planks");

	public static ModBlockFamily family(Block baseBlock, ButtonBlock button, FenceBlock fence, FenceGateBlock fenceGate, PressurePlateBlock pressurePlate,
										StandingSignBlock standingSign, WallSignBlock wallSign, CeilingHangingSignBlock ceilingHangingSign, WallHangingSignBlock wallHangingSign,
										SlabBlock slab, StairBlock stair, DoorBlock door, TrapDoorBlock trapdoor, String recipeGroupPrefix, String recipeUnlockedBy) {
		return new ModBlockFamily(baseBlock, button, fence, fenceGate, pressurePlate, standingSign, wallSign, ceilingHangingSign, wallHangingSign, slab, stair, door, trapdoor, recipeGroupPrefix, recipeUnlockedBy);
	}
}
