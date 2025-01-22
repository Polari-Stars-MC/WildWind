package org.polaris2023.wild_wind.common.Dyed.handler;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.polaris2023.wild_wind.common.Dyed.DyedBlockMap;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static net.minecraft.commands.arguments.blocks.BlockStateArgument.getBlock;

public class RightClickHandler {
    private static final Set<Block> carpetBlock= new HashSet<Block>() ;
    static {
        carpetBlock.add(Blocks.BLACK_CARPET);
        carpetBlock.add(Blocks.BLUE_CARPET);
        carpetBlock.add(Blocks.BROWN_CARPET);
        carpetBlock.add(Blocks.CYAN_CARPET);
        carpetBlock.add(Blocks.GRAY_CARPET);
        carpetBlock.add(Blocks.GREEN_CARPET);
        carpetBlock.add(Blocks.LIGHT_BLUE_CARPET);
        carpetBlock.add(Blocks.LIGHT_GRAY_CARPET);
        carpetBlock.add(Blocks.LIME_CARPET);
        carpetBlock.add(Blocks.MAGENTA_CARPET);
        carpetBlock.add(Blocks.ORANGE_CARPET);
        carpetBlock.add(Blocks.PINK_CARPET);
        carpetBlock.add(Blocks.PURPLE_CARPET);
        carpetBlock.add(Blocks.RED_CARPET);
        carpetBlock.add(Blocks.WHITE_CARPET);
        carpetBlock.add(Blocks.YELLOW_CARPET);
    }


    public static void rightClick(Player player, Level level, ItemStack itemStack, BlockPos pos, BlockState blockState) {
        System.out.println("right click");
        DyedBlockMap dyedBlockMap = new DyedBlockMap();
        Map<DyeColor, Block> dyedBlock = null;
        switch (blockType(blockState)) {
            case "WOOL":
                dyedBlock= dyedBlockMap.getDyedBlock("WOOL");
                break;
            case "CARPET":
                dyedBlock=dyedBlockMap.getDyedBlock("CARPET");
                break;

            case "pass": return;
        }

        if(dyedBlock !=null){
            DyeColor dyeColor = DyeColor.getColor(itemStack);
            Block dyedBlockInstance = dyedBlock.get(dyeColor);
            if (dyedBlockInstance != null) {
//                Optional<BlockState> optional = Optional.ofNullable(blockState.);
                BlockState newBlockStateProperties = dyedBlockInstance.withPropertiesOf(blockState);


                System.out.println(newBlockStateProperties+"--------------");
                level.setBlockAndUpdate(pos,newBlockStateProperties);

            }
        }

    }


    //判断方块类型
    public static String blockType(BlockState blockState) {
        if (blockState.is(BlockTags.WOOL)){
            return "WOOL";
        } else if (carpetBlock.contains(blockState.getBlock())){
            return "CARPET";
        }


        return "pass";
    }
}
