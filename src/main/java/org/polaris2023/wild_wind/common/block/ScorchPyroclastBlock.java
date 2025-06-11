package org.polaris2023.wild_wind.common.block;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.loot.LootParams;
import org.jetbrains.annotations.Nullable;
import org.polaris2023.wild_wind.common.init.ModBlocks;

import java.util.List;

public class ScorchPyroclastBlock extends PyroclastBlock {
    public ScorchPyroclastBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, BlockEntity te, ItemStack stack) {
        super.playerDestroy(level, player, pos, state, te, stack);
        if (!level.isClientSide && stack.getEnchantmentLevel(level.registryAccess().lookup(Registries.ENCHANTMENT).get().getOrThrow(Enchantments.SILK_TOUCH)) < 1) {
            level.setBlock(pos, Blocks.LAVA.defaultBlockState(), Block.UPDATE_ALL);
        }
    }

    @Override
    protected void crash(ServerLevel level, BlockPos blockPos, @Nullable Entity entity) {
        super.crash(level, blockPos, entity);
        level.setBlock(blockPos, Blocks.LAVA.defaultBlockState(), Block.UPDATE_ALL);
    }

    @Override
    protected void tryAbsorbLava(Level level, BlockPos pos) {
        System.out.print("flag");
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        handle(level, pos);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, level, pos, block, fromPos, isMoving);
        handle(level, pos);
    }

    public static final Direction[] DIRECTIONS = Direction.values();

    private void handle(Level level, BlockPos pos) {
        boolean flag = false;
        FluidState fluidState;
        BlockPos relativePos;
        for (Direction direction : DIRECTIONS) {
            relativePos = pos.relative(direction);
            fluidState = level.getFluidState(relativePos);
            if (fluidState.is(Fluids.WATER)) {
                flag = true;
                level.setBlock(relativePos, Blocks.OBSIDIAN.defaultBlockState(), Block.UPDATE_ALL);
            } else if (fluidState.is(Fluids.FLOWING_WATER)) {
                flag = true;
                level.setBlock(relativePos, Blocks.COBBLESTONE.defaultBlockState(), Block.UPDATE_ALL);
            }
        }
        if (flag) {
            level.setBlock(pos, ModBlocks.PYROCLAST.get().defaultBlockState(), Block.UPDATE_ALL);
        }
    }
}
