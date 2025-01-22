package org.polaris2023.wild_wind.common.Dyed;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.HashMap;
import java.util.Map;

public class DyedBlockMap {
    private static final Map<String, Map<DyeColor, Block>> dyedBlockMap = new HashMap<>();

    static {
        Map<DyeColor,Block> woolBlock = new HashMap<>();
        woolBlock.put(DyeColor.WHITE, Blocks.WHITE_WOOL);
        woolBlock.put(DyeColor.ORANGE, Blocks.ORANGE_WOOL);
        woolBlock.put(DyeColor.MAGENTA, Blocks.MAGENTA_WOOL);
        woolBlock.put(DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_WOOL);
        woolBlock.put(DyeColor.YELLOW, Blocks.YELLOW_WOOL);
        woolBlock.put(DyeColor.LIME, Blocks.LIME_WOOL);
        woolBlock.put(DyeColor.PINK, Blocks.PINK_WOOL);
        woolBlock.put(DyeColor.GRAY, Blocks.GRAY_WOOL);
        woolBlock.put(DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_WOOL);
        woolBlock.put(DyeColor.CYAN, Blocks.CYAN_WOOL);
        woolBlock.put(DyeColor.PURPLE, Blocks.PURPLE_WOOL);
        woolBlock.put(DyeColor.BLUE, Blocks.BLUE_WOOL);
        woolBlock.put(DyeColor.BROWN, Blocks.BROWN_WOOL);
        woolBlock.put(DyeColor.GREEN, Blocks.GREEN_WOOL);
        woolBlock.put(DyeColor.RED, Blocks.RED_WOOL);
        woolBlock.put(DyeColor.BLACK, Blocks.BLACK_WOOL);
        dyedBlockMap.put("WOOL", woolBlock);
        Map<DyeColor, Block> carpetBlock= new HashMap<>();
        carpetBlock.put(DyeColor.BLACK, Blocks.BLACK_CARPET);
        carpetBlock.put(DyeColor.BLUE, Blocks.BLUE_CARPET);
        carpetBlock.put(DyeColor.BROWN, Blocks.BROWN_CARPET);
        carpetBlock.put(DyeColor.CYAN, Blocks.CYAN_CARPET);
        carpetBlock.put(DyeColor.GRAY, Blocks.GRAY_CARPET);
        carpetBlock.put(DyeColor.GREEN, Blocks.GREEN_CARPET);
        carpetBlock.put(DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_CARPET);
        carpetBlock.put(DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_CARPET);
        carpetBlock.put(DyeColor.LIME, Blocks.LIME_CARPET);
        carpetBlock.put(DyeColor.MAGENTA, Blocks.MAGENTA_CARPET);
        carpetBlock.put(DyeColor.ORANGE, Blocks.ORANGE_CARPET);
        carpetBlock.put(DyeColor.PINK, Blocks.PINK_CARPET);
        carpetBlock.put(DyeColor.PURPLE, Blocks.PURPLE_CARPET);
        carpetBlock.put(DyeColor.RED, Blocks.RED_CARPET);
        carpetBlock.put(DyeColor.WHITE, Blocks.WHITE_CARPET);
        carpetBlock.put(DyeColor.YELLOW, Blocks.YELLOW_CARPET);
        dyedBlockMap.put("CARPET", carpetBlock);
        Map<DyeColor, Block> terracottaBlock= new HashMap<>();
        terracottaBlock.put(DyeColor.BLACK, Blocks.BLACK_TERRACOTTA);
        terracottaBlock.put(DyeColor.BLUE, Blocks.BLUE_TERRACOTTA);
        terracottaBlock.put(DyeColor.BROWN, Blocks.BROWN_TERRACOTTA);
        terracottaBlock.put(DyeColor.CYAN, Blocks.CYAN_TERRACOTTA);
        terracottaBlock.put(DyeColor.GRAY, Blocks.GRAY_TERRACOTTA);
        terracottaBlock.put(DyeColor.GREEN, Blocks.GREEN_TERRACOTTA);
        terracottaBlock.put(DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_TERRACOTTA);
        terracottaBlock.put(DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_TERRACOTTA);
        terracottaBlock.put(DyeColor.LIME, Blocks.LIME_TERRACOTTA);
        terracottaBlock.put(DyeColor.MAGENTA, Blocks.MAGENTA_TERRACOTTA);
        terracottaBlock.put(DyeColor.ORANGE, Blocks.ORANGE_TERRACOTTA);
        terracottaBlock.put(DyeColor.PINK, Blocks.PINK_TERRACOTTA);
        terracottaBlock.put(DyeColor.PURPLE, Blocks.PURPLE_TERRACOTTA);
        terracottaBlock.put(DyeColor.RED, Blocks.RED_TERRACOTTA);
        terracottaBlock.put(DyeColor.WHITE, Blocks.WHITE_TERRACOTTA);
        terracottaBlock.put(DyeColor.YELLOW, Blocks.YELLOW_TERRACOTTA);
        dyedBlockMap.put("TERRACOTTA", terracottaBlock);
    }
    public Map<DyeColor,Block> getDyedBlock(String type){
        return dyedBlockMap.get(type);
    }
}
