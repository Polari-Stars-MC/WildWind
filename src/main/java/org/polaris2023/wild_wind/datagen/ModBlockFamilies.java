package org.polaris2023.wild_wind.datagen;

import net.minecraft.world.level.block.*;
import org.polaris2023.wild_wind.common.init.ModBlocks;

public class ModBlockFamilies {
	public static final ModBlockFamily AZALEA_PLANKS = family(
			ModBlocks.AZALEA_PLANKS.get(), ModBlocks.AZALEA_BUTTON.get(), ModBlocks.AZALEA_FENCE.get(), ModBlocks.AZALEA_FENCE_GATE.get(), ModBlocks.AZALEA_PRESSURE_PLATE.get(),
			ModBlocks.AZALEA_SIGN.get(), ModBlocks.AZALEA_WALL_SIGN.get(), ModBlocks.AZALEA_HANGING_SIGN.get(), ModBlocks.AZALEA_WALL_HANGING_SIGN.get(),
			ModBlocks.AZALEA_SLAB.get(), ModBlocks.AZALEA_STAIRS.get(), ModBlocks.AZALEA_DOOR.get(), ModBlocks.AZALEA_TRAPDOOR.get(), "wooden","has_planks");
	public static final ModBlockFamily PALM_PLANKS = family(
			ModBlocks.PALM_PLANKS.get(), ModBlocks.PALM_BUTTON.get(), ModBlocks.PALM_FENCE.get(), ModBlocks.PALM_FENCE_GATE.get(), ModBlocks.PALM_PRESSURE_PLATE.get(),
			ModBlocks.PALM_SIGN.get(), ModBlocks.PALM_WALL_SIGN.get(), ModBlocks.PALM_HANGING_SIGN.get(), ModBlocks.PALM_WALL_HANGING_SIGN.get(),
			ModBlocks.PALM_SLAB.get(), ModBlocks.PALM_STAIRS.get(), ModBlocks.PALM_DOOR.get(), ModBlocks.PALM_TRAPDOOR.get(), "wooden","has_planks");
	public static final ModBlockFamily BAOBAB_PLANKS = family(
			ModBlocks.BAOBAB_PLANKS.get(), ModBlocks.BAOBAB_BUTTON.get(), ModBlocks.BAOBAB_FENCE.get(), ModBlocks.BAOBAB_FENCE_GATE.get(), ModBlocks.BAOBAB_PRESSURE_PLATE.get(),
			ModBlocks.BAOBAB_SIGN.get(), ModBlocks.BAOBAB_WALL_SIGN.get(), ModBlocks.BAOBAB_HANGING_SIGN.get(), ModBlocks.BAOBAB_WALL_HANGING_SIGN.get(),
			ModBlocks.BAOBAB_SLAB.get(), ModBlocks.BAOBAB_STAIRS.get(), ModBlocks.BAOBAB_DOOR.get(), ModBlocks.BAOBAB_TRAPDOOR.get(), "wooden","has_planks");

	public static ModBlockFamily family(Block baseBlock, ButtonBlock button, FenceBlock fence, FenceGateBlock fenceGate, PressurePlateBlock pressurePlate,
										StandingSignBlock standingSign, WallSignBlock wallSign, CeilingHangingSignBlock ceilingHangingSign, WallHangingSignBlock wallHangingSign,
										SlabBlock slab, StairBlock stair, DoorBlock door, TrapDoorBlock trapdoor, String recipeGroupPrefix, String recipeUnlockedBy) {
		return new ModBlockFamily(baseBlock, button, fence, fenceGate, pressurePlate, standingSign, wallSign, ceilingHangingSign, wallHangingSign, slab, stair, door, trapdoor, recipeGroupPrefix, recipeUnlockedBy);
	}
}
