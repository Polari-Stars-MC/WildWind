package org.polaris2023.wild_wind.datagen.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.polaris2023.annotation.enums.TagType;
import org.polaris2023.annotation.handler.TagHandler;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.init.ModItems;
import org.polaris2023.wild_wind.common.init.items.foods.ModBaseFoods;
import org.polaris2023.wild_wind.common.init.tags.ModBlockTags;
import org.polaris2023.wild_wind.common.init.tags.ModItemTags;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ModItemTagsProvider extends ItemTagsProvider {


    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, WildWindMod.MOD_ID, existingFileHelper);
    }

    @TagHandler(TagType.Item)
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.copy(ModBlockTags.AZALEA_LOGS.get(), ModItemTags.AZALEA_LOGS.get());
        this.copy(ModBlockTags.PALM_LOGS.get(), ModItemTags.PALM_LOGS.get());
        this.copy(ModBlockTags.BAOBAB_LOGS.get(), ModItemTags.BAOBAB_LOGS.get());
//        tag(ModItemTags.VEGETABLE_COMPONENT_OP5F).add(
//                Items.KELP, Items.DRIED_KELP,
//                Items.BROWN_MUSHROOM, Items.RED_MUSHROOM,
//                Items.CRIMSON_FUNGUS, Items.WARPED_FUNGUS);
//        tag(ModItemTags.VEGETABLE_COMPONENT_1F).add(
//                Items.CARROT, Items.GOLDEN_CARROT,
//                Items.BEETROOT, Items.POTATO,
//                Items.BAKED_POTATO, Items.POISONOUS_POTATO
//        );
//        tag(ModItemTags.MEAT_COMPONENT_OP5F).add(
//                Items.MUTTON, Items.COOKED_MUTTON,
//                Items.CHICKEN, Items.COOKED_CHICKEN,
//                Items.RABBIT, Items.RABBIT_FOOT,
//                Items.COOKED_RABBIT, Items.ROTTEN_FLESH,
//                Items.SPIDER_EYE, Items.FERMENTED_SPIDER_EYE,
//                Items.COD, Items.COOKED_COD,
//                Items.SALMON, Items.COOKED_SALMON,
//                Items.TROPICAL_FISH, Items.PUFFERFISH
//        );
//        tag(ModItemTags.MEAT_COMPONENT_1F).add(
//                Items.BEEF, Items.COOKED_BEEF,
//                Items.PORKCHOP, Items.COOKED_PORKCHOP
//        );
//        tag(ModItemTags.FISH_COMPONENT_1F).add(
//                Items.COD, Items.COOKED_COD,
//                Items.SALMON, Items.COOKED_SALMON,
//                Items.TROPICAL_FISH, Items.PUFFERFISH
//        );
//        tag(ModItemTags.MONSTER_COMPONENT_1F).add(
//                Items.POISONOUS_POTATO, Items.RABBIT_FOOT,
//                Items.ROTTEN_FLESH, Items.SPIDER_EYE,
//                Items.FERMENTED_SPIDER_EYE, Items.TROPICAL_FISH,
//                Items.PUFFERFISH
//        );
//        tag(ModItemTags.FRUIT_COMPONENT_1F).add(
//                Items.APPLE, Items.GOLDEN_APPLE,
//                Items.ENCHANTED_GOLDEN_APPLE, Items.CHORUS_FRUIT,
//                Items.POPPED_CHORUS_FRUIT, Items.SUGAR_CANE
//        );
//        tag(ModItemTags.FRUIT_COMPONENT_0P5F).add(
//                Items.MELON_SLICE, Items.GLISTERING_MELON_SLICE,
//                Items.SWEET_BERRIES, Items.GLOW_BERRIES
//        );
//        tag(ModItemTags.PROTEIN_COMPONENT_1F).add(Items.EGG, Items.TURTLE_EGG,
//                Items.SNIFFER_EGG, Items.DRAGON_EGG);
//        tag(ModItemTags.SWEET_COMPONENT_1F).add(Items.SUGAR, Items.HONEY_BOTTLE);
        tag(ItemTags.STAIRS).add(
                ModBlocks.ANDESITE_BRICK_STAIRS_ITEM.get(), ModBlocks.DIORITE_BRICK_STAIRS_ITEM.get(), ModBlocks.GRANITE_BRICK_STAIRS_ITEM.get(),
                ModBlocks.BLUE_ICE_BRICK_STAIRS.get().asItem()
        );
        tag(ItemTags.SLABS).add(
                ModBlocks.ANDESITE_BRICK_SLAB_ITEM.get(), ModBlocks.DIORITE_BRICK_SLAB_ITEM.get(), ModBlocks.GRANITE_BRICK_SLAB_ITEM.get(),
                ModBlocks.BLUE_ICE_BRICK_SLAB.get().asItem()
        );
        tag(ItemTags.WALLS).add(
                ModBlocks.STONE_WALL.asItem(), ModBlocks.POLISHED_STONE_WALL.get().asItem(),
                ModBlocks.ANDESITE_BRICK_WALL_ITEM.get(), ModBlocks.DIORITE_BRICK_WALL_ITEM.get(), ModBlocks.GRANITE_BRICK_WALL_ITEM.get(),
                ModBlocks.BLUE_ICE_BRICK_WALL.get().asItem()
        );
    }

    public static void add(IntrinsicTagAppender<Item> appender, ItemLike... likes) {
        appender.add(Arrays.stream(likes).map(ItemLike::asItem).toArray(Item[]::new));
    }


    protected IntrinsicTagAppender<Item> tag(Supplier<TagKey<Item>> tag) {
        return super.tag(tag.get());
    }
}
