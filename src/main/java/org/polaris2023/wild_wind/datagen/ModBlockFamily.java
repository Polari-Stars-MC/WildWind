package org.polaris2023.wild_wind.datagen;

import net.minecraft.data.recipes.*;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.util.Helpers;

import java.util.function.Consumer;
import java.util.function.Function;

import static org.polaris2023.wild_wind.datagen.ModRecipeProvider.*;

public record ModBlockFamily(Block baseBlock, Block wood, Block log, Block strippedWood, Block strippedLog,
							 BoatItem boat, BoatItem chestBoat, ButtonBlock button, FenceBlock fence, FenceGateBlock fenceGate, PressurePlateBlock pressurePlate,
							 StandingSignBlock standingSign, WallSignBlock wallSign, CeilingHangingSignBlock ceilingHangingSign, WallHangingSignBlock wallHangingSign,
							 SlabBlock slab, StairBlock stair, DoorBlock door, TrapDoorBlock trapdoor, String recipeGroupPrefix, String recipeUnlockedBy) {

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
		output.accept(wood);
		output.accept(log);
		output.accept(strippedWood);
		output.accept(strippedLog);
		output.accept(stair);
		output.accept(slab);
		output.accept(fence);
		output.accept(fenceGate);
		output.accept(door);
		output.accept(trapdoor);
		output.accept(pressurePlate);
		output.accept(button);
	}

	public void generateBlockLoot(Consumer<Block> dropSelf, Consumer<SlabBlock> dropSlab) {
		dropSelf.accept(this.baseBlock);
		dropSelf.accept(this.wood);
		dropSelf.accept(this.log);
		dropSelf.accept(this.strippedWood);
		dropSelf.accept(this.strippedLog);
		dropSelf.accept(this.button);
		dropSelf.accept(this.fence);
		dropSelf.accept(this.fenceGate);
		dropSelf.accept(this.pressurePlate);
		dropSelf.accept(this.standingSign);
		dropSelf.accept(this.wallSign);
		dropSelf.accept(this.ceilingHangingSign);
		dropSelf.accept(this.wallHangingSign);
		dropSlab.accept(this.slab);
		dropSelf.accept(this.stair);
		dropSelf.accept(this.trapdoor);
	}

	public void generateBlockTags(Function<TagKey<Block>, IntrinsicHolderTagsProvider.IntrinsicTagAppender<Block>> tag) {
		tag.apply(BlockTags.PLANKS).add(this.baseBlock);
		tag.apply(BlockTags.LOGS_THAT_BURN).add(this.log);
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
		tag.apply(ItemTags.PLANKS).add(this.baseBlock.asItem());
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

	public void generateFlammable() {
		registerFlammable(this.baseBlock, 5, 20);
		registerFlammable(this.wood, 5, 5);
		registerFlammable(this.log, 5, 5);
		registerFlammable(this.strippedWood, 5, 5);
		registerFlammable(this.strippedLog, 5, 5);
		registerFlammable(this.slab, 5, 20);
		registerFlammable(this.stair, 5, 20);
		registerFlammable(this.fence, 5, 20);
		registerFlammable(this.fenceGate, 5, 20);
	}

	public void generateRecipes(RecipeOutput recipeOutput) {
		final ShapedRecipeBuilder[] shapedList = {
			shaped(RecipeCategory.BUILDING_BLOCKS, this.slab, 6,
				builder -> {
					unlockedBy(builder, this.baseBlock);
					builder
							.pattern(("SSS"))
							.group("wooden_slab")
							.define('S',this.baseBlock);
			}),
			shaped(RecipeCategory.REDSTONE, this.pressurePlate, 1,
				builder -> {
					unlockedBy(builder,this.baseBlock);
					builder
							.pattern(("SS"))
							.group("wooden_pressure_plate")
							.define('S',this.baseBlock);
			}),
			shaped(RecipeCategory.BUILDING_BLOCKS, this.strippedWood, 3,
			builder -> {
				unlockedBy(builder, this.strippedLog);
				builder
						.pattern(("SS"))
						.pattern(("SS"))
						.group("bark")
						.define('S', this.strippedLog);
			}),
			shaped(RecipeCategory.BUILDING_BLOCKS, this.wood, 3,
			builder -> {
				unlockedBy(builder, this.log);
				builder
						.pattern(("SS"))
						.pattern(("SS"))
						.group("bark")
						.define('S', this.log);
			}),
			shaped(RecipeCategory.REDSTONE, this.fenceGate, 1,
			builder -> {
				unlockedBy(builder,this.baseBlock);
				unlockedBy(builder, Items.STICK);
				builder
						.pattern(("PSP"))
						.pattern(("PSP"))
						.group("wooden_fence_gate")
						.define('S',this.baseBlock)
						.define('P', Items.STICK);
			}),
			shaped(RecipeCategory.MISC, this.fence, 3,
			builder -> {
				unlockedBy(builder,this.baseBlock);
				unlockedBy(builder, Items.STICK);
				builder
						.pattern(("SPS"))
						.pattern(("SPS"))
						.group("wooden_fence")
						.define('S',this.baseBlock)
						.define('P', Items.STICK);
			}),
			shaped(RecipeCategory.REDSTONE, this.door, 3,
			builder -> {
				unlockedBy(builder,this.baseBlock);
				builder
						.pattern(("SS"))
						.pattern(("SS"))
						.pattern(("SS"))
						.group("wooden_door")
						.define('S',this.baseBlock);
			}),
			shaped(RecipeCategory.REDSTONE, this.trapdoor, 2,
			builder -> {
				unlockedBy(builder,this.baseBlock);
				builder
						.pattern(("SSS"))
						.pattern(("SSS"))
						.group("wooden_trapdoor")
						.define('S',this.baseBlock);
			}),
			shaped(RecipeCategory.MISC, this.standingSign, 3,
			builder -> {
				unlockedBy(builder,this.baseBlock);
				unlockedBy(builder, Items.STICK);
				builder
						.pattern(("SSS"))
						.pattern(("SSS"))
						.pattern((" P "))
						.group("wooden_sign")
						.define('S',this.baseBlock)
						.define('P', Items.STICK);
			}),
			shaped(RecipeCategory.BUILDING_BLOCKS, this.stair, 4,
			builder -> {
				unlockedBy(builder,this.baseBlock);
				builder
						.pattern(("S  "))
						.pattern(("SS "))
						.pattern(("SSS"))
						.group("wooden_stairs")
						.define('S',this.baseBlock);
			}),
			shaped(RecipeCategory.MISC, this.ceilingHangingSign, 6,
			builder -> {
				unlockedBy(builder, ModBlocks.STRIPPED_AZALEA_LOG.get());
				unlockedBy(builder, Blocks.CHAIN);
				builder
						.pattern(("I I"))
						.pattern(("SSS"))
						.pattern(("SSS"))
						.group("hanging_sign")
						.define('S', ModBlocks.STRIPPED_AZALEA_LOG.get())
						.define('I', Blocks.CHAIN);
			}),
			shaped(RecipeCategory.MISC, this.boat, 1,
			builder -> {
				unlockedBy(builder,this.baseBlock);
				builder
						.pattern(("S S"))
						.pattern(("SSS"))
						.group("boat")
						.define('S',this.baseBlock);
			})

		};
		final ShapelessRecipeBuilder[] shapelessList = {
			shapeless(RecipeCategory.MISC, this.baseBlock, 4, builder -> {
				unlockedBy(builder, this.wood, this.log, this.strippedWood, this.strippedLog);
				builder.requires(Ingredient.of(this.wood, this.log, this.strippedWood, this.strippedLog));
			}),
			shapeless(RecipeCategory.MISC, this.button, 1, builder -> {
				unlockedBy(builder, this.baseBlock);
				builder.requires(Ingredient.of(this.baseBlock))
						.group("wooden_button");
			}),
			shapeless(RecipeCategory.MISC, this.chestBoat, 1, builder -> {
				unlockedBy(builder, this.boat);
				unlockedBy(builder, Blocks.CHEST);
				builder
						.requires(Blocks.CHEST)
						.requires(this.boat)
						.group("chest_boat");
			})
		};
		for (ShapedRecipeBuilder recipe : shapedList) {
			recipe.save(recipeOutput);
		}
		for (ShapelessRecipeBuilder recipe : shapelessList) {
			recipe.save(recipeOutput);
		}
	}

	public static void registerFlammable(Block block, int encouragement, int flammability) {
		FireBlock fireblock = (FireBlock) Blocks.FIRE;
		fireblock.setFlammable(block, encouragement, flammability);
	}
}
