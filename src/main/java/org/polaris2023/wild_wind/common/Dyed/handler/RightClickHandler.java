package org.polaris2023.wild_wind.common.Dyed.handler;

import net.minecraft.client.renderer.block.model.BlockModelDefinition;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.Tags;
import org.polaris2023.wild_wind.common.Dyed.DyedBlockMap;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RightClickHandler {

    private static final Set<Block> carpetBlock= new HashSet<>() ;
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
    private static final Set<Block> concreteBlock= new HashSet<>();
    static {
        concreteBlock.add(Blocks.BLACK_CONCRETE);
        concreteBlock.add(Blocks.BLUE_CONCRETE);
        concreteBlock.add(Blocks.BROWN_CONCRETE);
        concreteBlock.add(Blocks.CYAN_CONCRETE);
        concreteBlock.add(Blocks.GRAY_CONCRETE);
        concreteBlock.add(Blocks.GREEN_CONCRETE);
        concreteBlock.add(Blocks.LIGHT_BLUE_CONCRETE);
        concreteBlock.add(Blocks.LIGHT_GRAY_CONCRETE);
        concreteBlock.add(Blocks.LIME_CONCRETE);
        concreteBlock.add(Blocks.MAGENTA_CONCRETE);
        concreteBlock.add(Blocks.ORANGE_CONCRETE);
        concreteBlock.add(Blocks.PINK_CONCRETE);
        concreteBlock.add(Blocks.PURPLE_CONCRETE);
        concreteBlock.add(Blocks.RED_CONCRETE);
        concreteBlock.add(Blocks.WHITE_CONCRETE);
        concreteBlock.add(Blocks.YELLOW_CONCRETE);
    }
    public static final Set<Block> concrete_powderBlock= new HashSet<>();
    static {
        concrete_powderBlock.add(Blocks.BLACK_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.BLUE_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.BROWN_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.CYAN_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.GRAY_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.GREEN_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.LIGHT_BLUE_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.LIGHT_GRAY_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.LIME_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.MAGENTA_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.ORANGE_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.PINK_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.PURPLE_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.RED_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.WHITE_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.YELLOW_CONCRETE_POWDER);
    }
    public static final Set<Block> bannerBlock= new HashSet<>();
    static {
        bannerBlock.add(Blocks.BLACK_BANNER);
        bannerBlock.add(Blocks.BLUE_BANNER);
        bannerBlock.add(Blocks.BROWN_BANNER);
        bannerBlock.add(Blocks.CYAN_BANNER);
        bannerBlock.add(Blocks.GRAY_BANNER);
        bannerBlock.add(Blocks.GREEN_BANNER);
        bannerBlock.add(Blocks.LIGHT_BLUE_BANNER);
        bannerBlock.add(Blocks.LIGHT_GRAY_BANNER);
        bannerBlock.add(Blocks.LIME_BANNER);
        bannerBlock.add(Blocks.MAGENTA_BANNER);
        bannerBlock.add(Blocks.ORANGE_BANNER);
        bannerBlock.add(Blocks.PINK_BANNER);
        bannerBlock.add(Blocks.PURPLE_BANNER);
        bannerBlock.add(Blocks.RED_BANNER);
        bannerBlock.add(Blocks.WHITE_BANNER);
        bannerBlock.add(Blocks.YELLOW_BANNER);
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
            case "BED":
                dyedBlock=dyedBlockMap.getDyedBlock("BED");
                break;
            case "TERRACOTTA":
                dyedBlock=dyedBlockMap.getDyedBlock("TERRACOTTA");
                break;
            case "CONCRETE":
                dyedBlock=dyedBlockMap.getDyedBlock("CONCRETE");
                break;
            case "CONCRETE_POWDER":
                dyedBlock=dyedBlockMap.getDyedBlock("CONCRETE_POWDER");
                break;
            case "GLAZED_TERRACOTTA":
                dyedBlock=dyedBlockMap.getDyedBlock("GLAZED_TERRACOTTA");
                break;
            case "GLASS":
                dyedBlock=dyedBlockMap.getDyedBlock("GLASS");
                break;
            case "GLASS_PANE":
                dyedBlock=dyedBlockMap.getDyedBlock("GLASS_PANE");
                break;
            case "SHULKER_BOX":
                dyedBlock=dyedBlockMap.getDyedBlock("SHULKER_BOX");
                break;
            case "CANDLE":
                dyedBlock=dyedBlockMap.getDyedBlock("CANDLE");
                break;
            case "BANNER":
                dyedBlock=dyedBlockMap.getDyedBlock("BANNER");
                break;

        }

        if(dyedBlock !=null & dyedBlock !=dyedBlockMap.getDyedBlock("BED")){
            DyeColor dyeColor = DyeColor.getColor(itemStack);
            Block dyedBlockInstance = dyedBlock.get(dyeColor);
            if (dyedBlockInstance != null & dyeColor != null) {
                //保存方块状态
                BlockState newBlockStateProperties = dyedBlockInstance.withPropertiesOf(blockState);
                System.out.println(newBlockStateProperties+"--------------");
                level.setBlockAndUpdate(pos,newBlockStateProperties);
                player.swing(InteractionHand.MAIN_HAND);
                if(!player.isCreative()){
                    itemStack.shrink(1);
                }
                level.playSound(null,pos, SoundEvents.DYE_USE, SoundSource.NEUTRAL);
                player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()),1);
            }
        }else {
            return;
        }

    }


    //判断方块类型
    public static String blockType(BlockState blockState) {
        if (blockState.is(BlockTags.WOOL)){
            return "WOOL";
        }
        if (carpetBlock.contains(blockState.getBlock())){
            return "CARPET";
        }
        if (blockState.getBlock() instanceof BedBlock){
            return "BED";
        }
        if (blockState.is(BlockTags.TERRACOTTA)){
            return "TERRACOTTA";
        }
        if (concreteBlock.contains(blockState.getBlock())){
            return "CONCRETE";
        }
        if (concrete_powderBlock.contains(blockState.getBlock())){
            return "CONCRETE_POWDER";
        }
        if (blockState.is(Tags.Blocks.GLAZED_TERRACOTTAS)){
            return "GLAZED_TERRACOTTA";
        }
        if (blockState.is(Tags.Blocks.GLASS_BLOCKS)){
            return "GLASS";
        }
        if (blockState.is(Tags.Blocks.GLASS_PANES)){
            return "GLASS_PANE";
        }
        if (blockState.is(BlockTags.SHULKER_BOXES)){
            return "SHULKER_BOX";
        }
        if (blockState.is(BlockTags.CANDLES)){
            return "CANDLE";
        }
        if (bannerBlock.contains(blockState.getBlock())){
            return "BANNER";
        }



        return "pass";
    }
}
