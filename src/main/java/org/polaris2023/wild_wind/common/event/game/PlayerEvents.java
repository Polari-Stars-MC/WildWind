package org.polaris2023.wild_wind.common.event.game;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.client.ModTranslateKey;
import org.polaris2023.wild_wind.common.init.ModAttachmentTypes;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.init.ModEnchantments;
import org.polaris2023.wild_wind.common.init.tags.ModEntityTypeTags;
import org.polaris2023.wild_wind.common.init.tags.ModItemTags;
import org.polaris2023.wild_wind.common.network.packets.EggShootPacket;
import org.polaris2023.wild_wind.util.EnchantmentHelper;

import java.util.Optional;

import static org.polaris2023.wild_wind.common.dyed.handler.RightClickHandler.rightClick;
import static org.polaris2023.wild_wind.util.EventsHandler.*;

/**
 * @author : baka4n
 * {@code @Date : 2025/05/04 23:53:09}
 */
@EventBusSubscriber(modid = WildWindMod.MOD_ID)
public class PlayerEvents {

    @SubscribeEvent
    public static void rightClickEventBlock(PlayerInteractEvent.RightClickBlock event) {
        ItemStack stack = event.getItemStack();
        Player player = event.getEntity();
        Level level = event.getLevel();

        Direction face = event.getHitVec().getDirection();
        if (stack.is(Items.MILK_BUCKET) && !player.isCrouching()) {
            BlockPos pos = event.getPos();
            BlockState state = level.getBlockState(pos);
            if (state.canBeReplaced()) {
                Block.popResource(level, pos, ItemStack.EMPTY);
            } else {
                pos = pos.relative(face);
            }

            level.setBlockAndUpdate(pos, ModBlocks.MILK.get().defaultBlockState());
            stack.shrink(1);
            player.setItemInHand(event.getHand(), Items.BUCKET.getDefaultInstance());

        } else if (stack.is(Items.BUCKET) && !player.isCrouching()) {
            BlockPos pos = event.getPos();
            BlockState blockState = level.getBlockState(pos);
            FluidState fluidState = blockState.getFluidState();
            if (fluidState.is(Tags.Fluids.MILK)) {
                level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                stack.shrink(1);
                player.setItemInHand(event.getHand(), Items.MILK_BUCKET.getDefaultInstance());
            }
        }
    }

    @SubscribeEvent
    public static void playerBreakBlock(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        if (event.getLevel() instanceof  ServerLevel serverLevel) {

            ItemStack mainHandItem = player.getMainHandItem();

            if (EnchantmentHelper.hasEnchantment(serverLevel, mainHandItem, ModEnchantments.AUTO_SMELTING.get())) {
                BlockPos pos = event.getPos();


                if (
                        isTool(mainHandItem, ItemTags.PICKAXES, BlockTags.MINEABLE_WITH_PICKAXE, serverLevel, pos)
                                || isTool(mainHandItem, ItemTags.AXES, BlockTags.MINEABLE_WITH_AXE, serverLevel, pos)
                                || isTool(mainHandItem, ItemTags.SHOVELS, BlockTags.MINEABLE_WITH_SHOVEL, serverLevel, pos)
                                || isTool(mainHandItem, ItemTags.HOES, BlockTags.MINEABLE_WITH_HOE, serverLevel, pos)
                                || nullTool(serverLevel, pos, BlockTags.MINEABLE_WITH_AXE, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.MINEABLE_WITH_SHOVEL, BlockTags.MINEABLE_WITH_HOE)
                ) {
                    //获取掉落物
                    autoSmelting(serverLevel, pos, mainHandItem, player);
                    serverLevel.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                    event.setCanceled(true);

                }
            }
        }
    }

    @SubscribeEvent
    public static void attackEntity(AttackEntityEvent event) {
        Player player = event.getEntity();
        eggShoot(player.getMainHandItem(), player, player.level());
    }

    @SubscribeEvent
    public static void rightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack itemStack = event.getItemStack();
        InteractionHand hand = event.getHand();
        if (itemStack.is(Items.EGG)) {
            FoodProperties foodproperties = itemStack.getFoodProperties(player);
            if (foodproperties != null) {
                if (player.canEat(foodproperties.canAlwaysEat())) {
                    player.startUsingItem(hand);
                    event.setCancellationResult(InteractionResultHolder.consume(itemStack).getResult());

                } else {
                    event.setCancellationResult(InteractionResultHolder.fail(itemStack).getResult());
                }
            } else {
                event.setCancellationResult(InteractionResultHolder.pass(itemStack).getResult());
            }
            event.setCanceled(true);
        }

    }

    @SubscribeEvent
    public static void clickEventEmpty(PlayerInteractEvent.LeftClickEmpty event) {

        Player player = event.getEntity();
        ItemStack mainHandItem = player.getMainHandItem();
        ItemStack offHandItem = player.getOffhandItem();
        if (mainHandItem.is(Items.EGG) || offHandItem.is(Items.EGG)) {
            PacketDistributor.sendToServer(new EggShootPacket());
        }
//        eggShoot(event.getItemStack(), event.getEntity(), event.getLevel());
    }

    @SubscribeEvent
    public static void clickEventBlock(PlayerInteractEvent.LeftClickBlock event) {
        eggShoot(event.getItemStack(), event.getEntity(), event.getLevel());
    }

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event){
        Player player = event.getEntity();
        Level level = player.level();
        ItemStack itemStack = player.getMainHandItem();
        BlockPos pos = event.getPos();
        BlockState blockState = level.getBlockState(pos);
        if (event.getHand() == InteractionHand.MAIN_HAND) {
            if (itemStack.getItem() instanceof DyeItem) {
                if (!level.isClientSide) {
                    rightClick(player, level, itemStack, pos, blockState,event);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        Player player = event.getEntity();
        Entity target = event.getTarget();
        Level level = event.getLevel();
        AttachmentType<Integer> type = ModAttachmentTypes.MILKING_INTERVALS.get();
        ItemStack itemInHand = player.getItemInHand(event.getHand());
        if (target instanceof Goat || target instanceof Cow) {
            if (itemInHand.is(Items.BUCKET) && !((AgeableMob) target).isBaby()) {
                if (target.getData(type) > 0) {
                    if (player instanceof ServerPlayer serverPlayer) {
                        ModTranslateKey key = target instanceof Goat ?
                                ModTranslateKey.GOAT_INTOLERANCE :
                                ModTranslateKey.COW_INTOLERANCE;
                        serverPlayer.sendSystemMessage(key.translatable());
                        serverPlayer.swing(event.getHand(), Boolean.TRUE);
                    }

                    event.setCanceled(true);
                } else {
                    target.setData(type, 6000);
                }
            }
        } else if (target instanceof MushroomCow mushroomCow) {
            if (itemInHand.is(Items.BOWL) && !mushroomCow.isBaby()) {
                if (target.getData(type) > 0) {
                    if (player instanceof ServerPlayer serverPlayer) {
                        serverPlayer.sendSystemMessage(ModTranslateKey.MOOSHROOM_COW_INTOLERANCE.translatable());
                        serverPlayer.swing(event.getHand(), Boolean.TRUE);
                    }

                    event.setCanceled(true);
                } else {
                    target.setData(type, 6000);
                }
            }
        } else if (target instanceof ItemFrame frame) {
            if (frame.getType().is(ModEntityTypeTags.WILD_WIND_INVISIBLE.get())
                    && event.getHand() == InteractionHand.MAIN_HAND
                    && itemInHand.is(ModItemTags.WILD_WIND_INVISIBLE.get())
                    && player.isShiftKeyDown()) {
                AttachmentType<Boolean> isInvisible = ModAttachmentTypes.IS_INVISIBLE.get();
                AttachmentType<Boolean> vanillaInvisible = ModAttachmentTypes.VANILLA_INVISIBLE_SAVE.get();
                if (!frame.hasData(isInvisible)) {
                    frame.setData(isInvisible, true);
                    frame.setData(vanillaInvisible, frame.isInvisible());
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEntityInteractSpecific(PlayerInteractEvent.EntityInteractSpecific event){
        Player player = event.getEntity();
        Level level = player.level();
        Entity shulker = event.getTarget();
        ItemStack dyeItem = player.getMainHandItem();
        if (event.getHand() == InteractionHand.MAIN_HAND){
            if(!level.isClientSide) {
                if (dyeItem.getItem() instanceof DyeItem) {
                    if (!((Shulker) shulker).getVariant().equals(Optional.ofNullable(DyeColor.getColor(dyeItem)))){
                        if(shulker instanceof Shulker){
                            ((Shulker) shulker).setVariant(Optional.ofNullable(DyeColor.getColor(dyeItem)));
                            if(!player.isCreative()){
                                dyeItem.shrink(1);
                            }
                            player.awardStat(Stats.ITEM_USED.get(dyeItem.getItem()),1);
                            player.playSound(SoundEvents.DYE_USE);
                        }
                    }
                }
            }
        }
    }

}
