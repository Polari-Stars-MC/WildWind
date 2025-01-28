package org.polaris2023.wild_wind.common.dyed.handler;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.Clearable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BedBlockEntity;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.polaris2023.wild_wind.common.dyed.DyedBlockMap;

import java.util.*;

public class RightClickHandler {


    public static void rightClick(Player player, Level level, ItemStack itemStack, BlockPos pos, BlockState blockState, PlayerInteractEvent.RightClickBlock event) {
        DyedBlockMap dyedBlockMap = new DyedBlockMap();
        Map<DyeColor, Block> dyedBlock = dyedBlockMap.getDyedBlock(blockState);

        if(dyedBlock != null){
            DyeColor dyeColor = DyeColor.getColor(itemStack);
            Block dyedBlockInstance = dyedBlock.get(dyeColor);
            if (dyedBlockInstance != null & dyeColor != null) {
                BlockState newBlockState = dyedBlockInstance.withPropertiesOf(blockState);
                if (dyedBlock == dyedBlockMap.getDyedBlock(blockState)) {

                    BedBlockEntity bedEntity = (BedBlockEntity) level.getBlockEntity(pos);
                    if (bedEntity != null && DyeColor.getColor(itemStack) != bedEntity.getColor()) {
                        Direction direction = bedEntity.getBlockState().getValue(BedBlock.FACING);
                        BedPart value = bedEntity.getBlockState().getValue(BedBlock.PART);
                        BlockPos otherPos = pos.offset(
                                value.equals(BedPart.HEAD) ? direction.getOpposite().getNormal() : direction.getNormal()
                        );
                        BlockState otherBlockState = level.getBlockState(otherPos);
                        BlockState newOtherBlockState = dyedBlockInstance.withPropertiesOf(otherBlockState);
                        BedBlockEntity bedOtherEntity = (BedBlockEntity) level.getBlockEntity(otherPos);
                        handleDyedBed(
                                level,
                                player,
                                itemStack,
                                blockState,
                                pos,
                                newBlockState,
                                bedEntity,
                                otherBlockState,
                                otherPos,
                                newOtherBlockState,
                                bedOtherEntity
                                );


                    }
//                    if (bedEntity != null && DyeColor.getColor(itemStack) != bedEntity.getColor()) {
//                        System.out.println(bedEntity.getColor());
//                        handleDyedBed(player, itemStack, blockState, level, pos, newBlockState);
//                        System.out.println(bedEntity.getColor());
//                        event.setCanceled(true);
//                        player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()), 1);
//                        level.playSound(null, pos, SoundEvents.DYE_USE, SoundSource.NEUTRAL);
//                    }
                    return;

                } else if (dyedBlock == dyedBlockMap.getDyedBlock(blockState)) {
                    if (level.getBlockState(pos).getBlock() != dyedBlock.get(dyeColor)) {
                        if (player.isCrouching()){
                            handleDyedShulkerBox(level, pos, newBlockState);
                            if(!player.isCreative()){
                                itemStack.shrink(1);
                            }
                            event.setCanceled(true);
                            player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()),1);
                            level.playSound(null,pos, SoundEvents.DYE_USE, SoundSource.NEUTRAL);
                        }
                    }
                } else {
                    if (level.getBlockState(pos).getBlock() != dyedBlock.get(dyeColor)) {
                        level.setBlockAndUpdate(pos,newBlockState);
                        if(!player.isCreative()){
                            itemStack.shrink(1);
                        }
                    }
                    event.setCanceled(true);
                    player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()),1);
                    level.playSound(null,pos, SoundEvents.DYE_USE, SoundSource.NEUTRAL);
                }
            }
        }

    }

    private static void handleDyedBed(
            Level level,
            Player player,
            ItemStack itemStack,
            BlockState blockState,
            BlockPos pos,
            BlockState newState,
            BedBlockEntity bedEntity,
            BlockState otherBlockState,
            BlockPos otherPos,
            BlockState newOtherState,
            BedBlockEntity bedOtherEntity){
        Clearable.tryClear(bedEntity);
        Clearable.tryClear(bedOtherEntity);




//        newBedBlock.setPlacedBy(level, pos, newBlockStateProperties, player, Items.BLUE_BED.getDefaultInstance());
//        ItemStack newBedItem = blockState.getBlock().getCloneItemStack(level, pos, blockState);


    }

    private static void handleDyedShulkerBox(Level level, BlockPos pos, BlockState newBlockStateProperties) {
        ShulkerBoxBlockEntity shulkerBoxEntity = (ShulkerBoxBlockEntity) level.getBlockEntity(pos);
        List<ItemStack> items = new ArrayList<>();
        // 获取 HolderLookup.Provider
        HolderLookup.Provider provider = level.registryAccess();
        for (int i = 0; i < shulkerBoxEntity.getContainerSize(); i++) {
//            System.out.println(shulkerBoxEntity.getItem(i));
            items.add(shulkerBoxEntity.getItem(i));
//            System.out.println(items);
        }
        CompoundTag oldTag = shulkerBoxEntity.saveWithFullMetadata(provider);
//
//        System.out.println(oldTag.getString("CustomName"));
//        System.out.println(oldTag);
//
//        System.out.println("---------------");
        level.setBlockAndUpdate(pos, newBlockStateProperties);
        ShulkerBoxBlockEntity newShulkerBoxEntity = (ShulkerBoxBlockEntity) level.getBlockEntity(pos);

//        newShulkerBoxEntity.setCustomName();

        for (int i = 0; i < items.size(); i++) {
            newShulkerBoxEntity.setItem(i, items.get(i));
        }

    }


}
