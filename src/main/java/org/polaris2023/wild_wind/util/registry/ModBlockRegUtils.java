package org.polaris2023.wild_wind.util.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.polaris2023.wild_wind.common.block.misc.AxeStrippableBlock;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.init.ModItems;

import java.util.function.Supplier;

public class ModBlockRegUtils {

    public static<T extends Block> DeferredBlock<T> register(String name, Supplier<T> block) {
        DeferredBlock<T> register = ModBlocks.BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(register.get(), new Item.Properties()));
        return register;
    }

    public static DeferredBlock<Block> normal(String name, BlockBehaviour.Properties properties) {
        return register(name, () -> new Block(properties));
    }

    public static<T extends RotatedPillarBlock> DeferredBlock<AxeStrippableBlock<?>> wood(String name, Supplier<T> block, MapColor mapColor) {
        return register(name, () -> new AxeStrippableBlock<>(block, BlockBehaviour.Properties
                .of().mapColor(mapColor).instrument(NoteBlockInstrument.BASS)
                .strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    }

    public static DeferredBlock<RotatedPillarBlock> wood(String name, MapColor mapColor) {
        return register(name, () -> new RotatedPillarBlock(BlockBehaviour.Properties
                .of().mapColor(mapColor).instrument(NoteBlockInstrument.BASS)
                .strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    }

    public static DeferredBlock<PressurePlateBlock> pressurePlate(String name, BlockBehaviour.Properties properties, BlockSetType blockSetType) {
        return register(name, () -> new PressurePlateBlock(blockSetType, properties));
    }

    public static DeferredBlock<FenceGateBlock> fenceGate(String name, BlockBehaviour.Properties properties, WoodType woodType) {
        return register(name, () -> new FenceGateBlock(woodType, properties));
    }

    public static DeferredBlock<TrapDoorBlock> trapdoor(String name, BlockBehaviour.Properties properties, BlockSetType blockSetType) {
        return register(name, () -> new TrapDoorBlock(blockSetType, properties));
    }

    public static DeferredBlock<ButtonBlock> button(String name, boolean sensitive, BlockSetType blockSetType) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY);
        return register(name, () -> new ButtonBlock(blockSetType, sensitive ? 30 : 20, properties));
    }

    public static <T extends Block> DeferredBlock<StairBlock> stair(String name, Supplier<T> block, BlockBehaviour.Properties properties) {
        return register(name, () -> new StairBlock(block.get().defaultBlockState(), properties));
    }

    public static DeferredBlock<FenceBlock> fence(String name, BlockBehaviour.Properties properties) {
        return register(name, () -> new FenceBlock(properties));
    }

    public static DeferredBlock<DoorBlock> door(String name, BlockBehaviour.Properties properties, BlockSetType blockSetType) {
        return register(name, () -> new DoorBlock(blockSetType, properties));
    }

    public static DeferredBlock<SlabBlock> slab(String name, BlockBehaviour.Properties properties) {
        return register(name, () -> new SlabBlock(properties));
    }

    public static DeferredBlock<WallBlock> wall(String name, BlockBehaviour.Properties properties) {
        return register(name, () -> new WallBlock(properties));
    }

}