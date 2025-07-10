package org.polaris2023.wild_wind.common.event.game;

import java.util.List;
import java.util.Map;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.level.ExplosionEvent;
import net.neoforged.neoforge.event.level.block.CropGrowEvent;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.client.ModTranslateKey;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.init.ModComponents;

import static org.polaris2023.wild_wind.util.EventsHandler.componentAdd;

/**
 * @author : baka4n
 * {@code @Date : 2025/05/04 23:56:18}
 */
@EventBusSubscriber(modid = WildWindMod.MOD_ID)
public class BlockEvents {
    @SubscribeEvent
    public static void cropGrowPost(CropGrowEvent.Post event) {

        LevelAccessor level = event.getLevel();

        if (level instanceof ServerLevel serverLevel) {
            BlockPos pos = event.getPos();
            if (serverLevel.getBlockState(pos).is(Blocks.ATTACHED_MELON_STEM)) {
                Direction direction = serverLevel.getBlockState(pos).getValue(HorizontalDirectionalBlock.FACING);
                BlockPos blockPos = pos.relative(direction);
                if (serverLevel.getRandom().nextFloat() < 0.02F) {
                    serverLevel.setBlockAndUpdate(blockPos, ModBlocks.GLISTERING_MELON.get().defaultBlockState());
                    serverLevel.setBlockAndUpdate(pos, Blocks.ATTACHED_MELON_STEM.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, direction));
                }
            }
        }
    }

    @SubscribeEvent
    public static void tooltipAdd(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        List<Component> toolTip = event.getToolTip();
        if (stack.has(DataComponents.FOOD)) {
            FoodProperties foodProperties = stack.get(DataComponents.FOOD);
            assert foodProperties != null;
            toolTip.addLast(Component.empty().append(ModTranslateKey.NUTRITION.translatable()).append(String.valueOf(foodProperties.nutrition())));
            toolTip.addLast(Component.empty().append(ModTranslateKey.SATURATION.translatable()).append(String.valueOf(foodProperties.saturation())));
            List<FoodProperties.PossibleEffect> effects = foodProperties.effects();
            if (!effects.isEmpty()) {
                toolTip.addLast(ModTranslateKey.EFFECT.translatable());
                for (FoodProperties.PossibleEffect effect : effects) {
                    MobEffectInstance effected = effect.effect();
                    toolTip.addLast(Component
                            .empty()
                            .append(effect.probability() * 100 + "% ")
                            .append(Component.translatable(effected.getDescriptionId()))
                            .append((effected.getAmplifier() + 1) + " ")
                            .append(String.valueOf(effected.getDuration()))
                            .append("tick")
                    );
                }
            }

        }
        componentAdd(stack, toolTip, ModTranslateKey.MEAT_VALUE, ModComponents.MEAT_VALUE, 0F);
        componentAdd(stack, toolTip, ModTranslateKey.VEGETABLE_VALUE, ModComponents.VEGETABLE_VALUE, 0F);
        componentAdd(stack, toolTip, ModTranslateKey.FRUIT_VALUE, ModComponents.FRUIT_VALUE, 0F);
        componentAdd(stack, toolTip, ModTranslateKey.PROTEIN_VALUE, ModComponents.PROTEIN_VALUE, 0F);
        componentAdd(stack, toolTip, ModTranslateKey.FISH_VALUE, ModComponents.FISH_VALUE, 0F);
        componentAdd(stack, toolTip, ModTranslateKey.MONSTER_VALUE, ModComponents.MONSTER_VALUE, 0F);
        componentAdd(stack, toolTip, ModTranslateKey.SWEET_VALUE, ModComponents.SWEET_VALUE, 0F);

    }

    @SubscribeEvent
    public static void registerExplosion(ExplosionEvent.Detonate event) {
        final Map<Block, Block> replacementMap = Map.of(
            ModBlocks.BLUE_ICE_BRICKS.get(), ModBlocks.CRACKED_BLUE_ICE_BRICKS.get(),
            ModBlocks.SNOW_BRICKS.get(), ModBlocks.CRACKED_SNOW_BRICKS.get()
        );

        Level level = event.getLevel();
        if (level.isClientSide) return;

        event.getAffectedBlocks().removeIf(pos -> {
            BlockState oldState = level.getBlockState(pos);
            if (replacementMap.containsKey(oldState.getBlock())) {
                Block newBlock = replacementMap.get(oldState.getBlock());
                BlockState newState = newBlock.defaultBlockState();
                level.setBlock(pos, newState, Block.UPDATE_ALL);
                return true;
            }
            return false;
        });
    }
}
