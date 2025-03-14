package org.polaris2023.wild_wind.datagen;

import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import org.polaris2023.wild_wind.util.Helpers;

import java.util.function.Consumer;
import java.util.function.Function;

public record ModBlockFamily(Block baseBlock, ButtonBlock button, FenceBlock fence, FenceGateBlock fenceGate,
							 PressurePlateBlock pressurePlate, StandingSignBlock standingSign, WallSignBlock wallSign,
							 CeilingHangingSignBlock ceilingHangingSign, WallHangingSignBlock wallHangingSign,
							 SlabBlock slab, StairBlock stair, DoorBlock door, TrapDoorBlock trapdoor,
							 String recipeGroupPrefix, String recipeUnlockedBy) {
	@Deprecated(since = "using annotation @AllWood", forRemoval = true)
	public void registerStatesAndModels(BlockStateProvider provider, String name) {
		ResourceLocation planks = Helpers.location("block/" + name + "_planks");
		ResourceLocation log = Helpers.location("block/" + name + "_log");
		provider.buttonBlock(this.button, planks);//replace
		provider.fenceBlock(this.fence, planks);//replace
		provider.fenceGateBlock(this.fenceGate, planks);//replace
		provider.pressurePlateBlock(this.pressurePlate, planks);//replace
		provider.signBlock(this.standingSign, this.wallSign, planks);//replace
		provider.hangingSignBlock(this.ceilingHangingSign, this.wallHangingSign, log);//replace
		provider.slabBlock(this.slab, planks, planks);//replace
		provider.stairsBlock(this.stair, planks);//replace
		provider.doorBlock(this.door, Helpers.location("block/" + name + "_door_bottom"), Helpers.location("block/" + name + "_door_top"));//replace
		provider.trapdoorBlock(this.trapdoor, Helpers.location("block/" + name + "_trapdoor"), true);//replace
	}

	public void addCreativeTab(CreativeModeTab.Output output) {
		output.accept(baseBlock);
		output.accept(stair);
		output.accept(slab);
		output.accept(fence);
		output.accept(fenceGate);
		output.accept(door);
		output.accept(trapdoor);
		output.accept(pressurePlate);
		output.accept(button);
	}

	public void generateBlockLoot(Consumer<Block> dropSelf) {
		dropSelf.accept(this.baseBlock);
		dropSelf.accept(this.button);
		dropSelf.accept(this.fence);
		dropSelf.accept(this.fenceGate);
		dropSelf.accept(this.pressurePlate);
		dropSelf.accept(this.standingSign);
		dropSelf.accept(this.wallSign);
		dropSelf.accept(this.ceilingHangingSign);
		dropSelf.accept(this.wallHangingSign);
		dropSelf.accept(this.slab);
		dropSelf.accept(this.stair);
		dropSelf.accept(this.trapdoor);
	}

	public void generateBlockTags(Function<TagKey<Block>, IntrinsicHolderTagsProvider.IntrinsicTagAppender<Block>> tag) {
		tag.apply(BlockTags.WOODEN_BUTTONS).add(this.button);
		tag.apply(BlockTags.WOODEN_FENCES).add(this.fence);
		tag.apply(BlockTags.FENCE_GATES).add(this.fenceGate);
		tag.apply(BlockTags.WOODEN_PRESSURE_PLATES).add(this.pressurePlate);
		tag.apply(BlockTags.STANDING_SIGNS).add(this.standingSign);
		tag.apply(BlockTags.WALL_SIGNS).add(this.wallSign);
		tag.apply(BlockTags.CEILING_HANGING_SIGNS).add(this.ceilingHangingSign);
		tag.apply(BlockTags.WALL_HANGING_SIGNS).add(this.wallHangingSign);
		tag.apply(BlockTags.WOODEN_SLABS).add(this.slab);
		tag.apply(BlockTags.WOODEN_STAIRS).add(this.stair);
		tag.apply(BlockTags.WOODEN_DOORS).add(this.door);
		tag.apply(BlockTags.WOODEN_TRAPDOORS).add(this.trapdoor);
	}
	public void generateItemTags(Function<TagKey<Item>, IntrinsicHolderTagsProvider.IntrinsicTagAppender<Item>> tag) {
		tag.apply(ItemTags.WOODEN_BUTTONS).add(this.button.asItem());
		tag.apply(ItemTags.WOODEN_FENCES).add(this.fence.asItem());
		tag.apply(ItemTags.FENCE_GATES).add(this.fenceGate.asItem());
		tag.apply(ItemTags.WOODEN_PRESSURE_PLATES).add(this.pressurePlate.asItem());
		tag.apply(ItemTags.SIGNS).add(this.standingSign.asItem(), this.wallSign.asItem());
		tag.apply(ItemTags.HANGING_SIGNS).add(this.ceilingHangingSign.asItem(), this.wallHangingSign.asItem());
		tag.apply(ItemTags.WOODEN_SLABS).add(this.slab.asItem());
		tag.apply(ItemTags.WOODEN_STAIRS).add(this.stair.asItem());
		tag.apply(ItemTags.WOODEN_DOORS).add(this.door.asItem());
		tag.apply(ItemTags.WOODEN_TRAPDOORS).add(this.trapdoor.asItem());
	}
}
