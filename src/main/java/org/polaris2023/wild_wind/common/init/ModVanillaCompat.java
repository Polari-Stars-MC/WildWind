package org.polaris2023.wild_wind.common.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import org.polaris2023.wild_wind.datagen.ModBlockFamilies;

public class ModVanillaCompat {

    public static void setup() {
        ModBlockFamilies.AZALEA.generateFlammable();
        ModBlockFamilies.BAOBAB.generateFlammable();
        ModBlockFamilies.PALM.generateFlammable();
        registerFlammable(ModBlocks.BAOBAB_LEAVES.get(), 30, 60);
        registerFlammable(ModBlocks.PALM_LEAVES.get(), 30, 60);
        registerFlammable(ModBlocks.PALM_CROWN.get(), 5, 5);
    }

    public static void registerFlammable(Block block, int encouragement, int flammability) {
        FireBlock fireblock = (FireBlock) Blocks.FIRE;
        fireblock.setFlammable(block, encouragement, flammability);
    }

}