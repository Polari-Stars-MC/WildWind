package org.polaris2023.wild_wind.common.dyed.handler;

import java.util.*;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.polaris2023.wild_wind.common.block.entity.ModBannerBlockEntity;
import org.polaris2023.wild_wind.common.dyed.DyedBlockMap;
import org.polaris2023.wild_wind.common.init.tags.ModBlockTags;

public class RightClickHandler {
    public static void rightClick(Player player, Level level, ItemStack itemStack, BlockPos pos, BlockState blockState, PlayerInteractEvent.RightClickBlock event) {
        boolean canceled = false;
        Map<Integer, Block> dyedBlock = switch (blockType(blockState)) {
            case "WOOL" -> DyedBlockMap.getDyedBlock("WOOL");
            case "CARPET" -> DyedBlockMap.getDyedBlock("CARPET");
            case "BED" -> DyedBlockMap.getDyedBlock("BED");
            case "TERRACOTTA" -> DyedBlockMap.getDyedBlock("TERRACOTTA");
            case "CONCRETE" -> DyedBlockMap.getDyedBlock("CONCRETE");
            case "CONCRETE_POWDER" -> DyedBlockMap.getDyedBlock("CONCRETE_POWDER");
            case "GLAZED_TERRACOTTA" -> DyedBlockMap.getDyedBlock("GLAZED_TERRACOTTA");
            case "GLASS" -> DyedBlockMap.getDyedBlock("GLASS");
            case "GLASS_PANE" -> DyedBlockMap.getDyedBlock("GLASS_PANE");
            case "SHULKER_BOX" -> DyedBlockMap.getDyedBlock("SHULKER_BOX");
            case "CANDLE" -> DyedBlockMap.getDyedBlock("CANDLE");
            case "BANNER" -> DyedBlockMap.getDyedBlock("BANNER");
            case "WALL_BANNER" -> DyedBlockMap.getDyedBlock("WALL_BANNER");
            default -> null;
        };
        if (dyedBlock == null) return;
        DyeColor dyeColor = DyeColor.getColor(itemStack);
        if (dyeColor == null) return;
        Block dyedBlockInstance = dyedBlock.get(dyeColor.getId());
        if (dyedBlockInstance == null) return;
        BlockState newBlockState = dyedBlockInstance.withPropertiesOf(blockState);

        if (dyedBlock == DyedBlockMap.getDyedBlock("BED")) {
            BlockState oldBlockState = level.getBlockState(pos);
            if (oldBlockState.is(dyedBlock.get(dyeColor.getId()))) return;
            if (player.isCrouching()) {
                canceled = handleDyedBed(level, pos, oldBlockState, newBlockState);
            }
        }
        else if (dyedBlock == DyedBlockMap.getDyedBlock("SHULKER_BOX")) {
            if (level.getBlockState(pos).is(dyedBlock.get(dyeColor.getId()))) return;
            if (player.isCrouching()){
                canceled = handleDyedShulkerBox(level, pos, newBlockState);
            }
        } else if (dyedBlock == DyedBlockMap.getDyedBlock("BANNER") ||
                dyedBlock == DyedBlockMap.getDyedBlock("WALL_BANNER")) {
            if (level.getBlockState(pos).is(dyedBlock.get(dyeColor.getId()))) return;
            canceled = handleDyedBanner(level, pos, newBlockState);
        } else {
            if (level.getBlockState(pos).is(dyedBlock.get(dyeColor.getId()))) return;
            canceled = true;
            level.setBlockAndUpdate(pos, newBlockState);
        }
        if (canceled) {
            itemStack.consume(1, player);
            event.setCanceled(true);
            player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()),1);
            level.playSound(null,pos, SoundEvents.DYE_USE, SoundSource.NEUTRAL);
        }
    }

    private static boolean handleDyedShulkerBox(Level level, BlockPos pos, BlockState newBlockStateProperties) {
        ShulkerBoxBlockEntity shulkerBoxEntity = (ShulkerBoxBlockEntity) level.getBlockEntity(pos);
        if (shulkerBoxEntity == null) return false;
        List<ItemStack> items = new ArrayList<>();
        for (int i = 0; i < shulkerBoxEntity.getContainerSize(); i++) {
            items.add(shulkerBoxEntity.getItem(i));
        }
        level.setBlockAndUpdate(pos, newBlockStateProperties);
        ShulkerBoxBlockEntity newShulkerBoxEntity = (ShulkerBoxBlockEntity) level.getBlockEntity(pos);
        if (newShulkerBoxEntity == null) return false;

        for (int i = 0; i < items.size(); i++) {
            newShulkerBoxEntity.setItem(i, items.get(i));
        }
        return true;
    }

    private static boolean handleDyedBed(Level level, BlockPos pos, BlockState oldBlockState, BlockState newBlockState) {
        if(oldBlockState.getValue(BedBlock.PART) == BedPart.HEAD) {
            BlockPos blockpos = pos.relative(oldBlockState.getValue(BedBlock.FACING).getOpposite());
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 1);
            level.setBlockAndUpdate(blockpos, Blocks.AIR.defaultBlockState());
            level.blockUpdated(blockpos, Blocks.AIR);
            level.setBlock(blockpos, newBlockState.setValue(BedBlock.PART, BedPart.FOOT), 3);

            level.blockUpdated(pos, Blocks.AIR);
            level.setBlock(pos, newBlockState, 3);
        } else {
            BlockPos blockpos = pos.relative(oldBlockState.getValue(BedBlock.FACING));
            level.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 1);
            level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
            level.blockUpdated(blockpos, Blocks.AIR);
            level.setBlock(blockpos, newBlockState.setValue(BedBlock.PART, BedPart.HEAD), 3);

            level.blockUpdated(pos, Blocks.AIR);
            level.setBlock(pos, newBlockState, 3);
        }
        return true;
    }

    public static boolean handleDyedBanner(Level level, BlockPos pos, BlockState newBlockState) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof BannerBlockEntity bannerBlockEntity) {
            BannerPatternLayers patternLayers = bannerBlockEntity.getPatterns();
            level.setBlockAndUpdate(pos, newBlockState);

            BannerBlockEntity newBannerBlockEntity = (BannerBlockEntity) level.getBlockEntity(pos);
            if (newBannerBlockEntity == null) return false;

            newBannerBlockEntity.patterns = patternLayers;
            return true;
        } else if (blockEntity instanceof ModBannerBlockEntity bannerBlockEntity) {
            BannerPatternLayers patternLayers = bannerBlockEntity.getPatterns();
            level.setBlockAndUpdate(pos, newBlockState);
            BannerBlockEntity newModBannerBlockEntity = (BannerBlockEntity) level.getBlockEntity(pos);
            if (newModBannerBlockEntity == null) return false;
            newModBannerBlockEntity.patterns = patternLayers;
            return true;
        }
        return false;
    }

    //判断方块类型
    public static String blockType(BlockState blockState) {
        if (blockState.is(BlockTags.WOOL)){
            return "WOOL";
        }
        if (blockState.is(BlockTags.WOOL_CARPETS)){
            return "CARPET";
        }
        if (blockState.is(BlockTags.BEDS)){
            return "BED";
        }
        if (blockState.is(BlockTags.TERRACOTTA)){
            return "TERRACOTTA";
        }
        if (blockState.is(Tags.Blocks.CONCRETES)){
            return "CONCRETE";
        }
        if (blockState.is(ModBlockTags.CONCRETE_POWDERS.get())){
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
        if (blockState.is(ModBlockTags.BANNERS.get())){
            return "BANNER";
        }
        if (blockState.is(ModBlockTags.WALL_BANNERS.get())){
            return "WALL_BANNER";
        }
        if (blockState.is(BlockTags.BEDS)){
            return "BED";
        }
        

        return "PASS";
    }
}