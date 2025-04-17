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
import org.polaris2023.wild_wind.util.data.ModBlockFamilies;

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
        IntrinsicTagAppender<Item> firefly_food = tag(ModItemTags.FIREFLY_FOOD);
        firefly_food.add(Items.GLOW_BERRIES, Items.GLOW_LICHEN);
        IntrinsicTagAppender<Item> meat_food = tag(ModItemTags.MEAT_FOOD);
        meat_food.add(
                Items.BEEF, Items.COOKED_BEEF,
                Items.PORKCHOP, Items.COOKED_PORKCHOP,
                Items.MUTTON, Items.COOKED_MUTTON,
                Items.RABBIT, Items.COOKED_RABBIT,
                Items.CHICKEN, Items.COOKED_CHICKEN
        );
        IntrinsicTagAppender<Item> vegetable_food = tag(ModItemTags.VEGETABLE_FOOD);
        add(vegetable_food,
                Items.CARROT, ModBaseFoods.BAKED_CARROT, Items.GOLDEN_CARROT,
                Items.BEETROOT, ModBaseFoods.BAKED_BEETROOT,
                Items.POTATO, Items.BAKED_POTATO,
                ModBaseFoods.PUMPKIN_SLICE, ModBaseFoods.BAKED_PUMPKIN_SLICE,
                Items.BROWN_MUSHROOM, Items.RED_MUSHROOM, ModBaseFoods.BAKED_MUSHROOM,
                Items.CRIMSON_FUNGUS, Items.WARPED_FUNGUS,
                ModItems.LIVING_TUBER, ModBaseFoods.BAKED_LIVING_TUBER
        );
        IntrinsicTagAppender<Item> fruit_food = tag(ModItemTags.FRUIT_FOOD);
        add(fruit_food,
                Items.APPLE, ModBaseFoods.BAKED_APPLE, Items.GOLDEN_APPLE, Items.ENCHANTED_GOLDEN_APPLE,
                Items.CHORUS_FRUIT, Items.POPPED_CHORUS_FRUIT,
                Items.MELON_SLICE, ModBaseFoods.BAKED_MELON_SLICE, Items.GLISTERING_MELON_SLICE,
                Items.SWEET_BERRIES, Items.GLOW_BERRIES,ModBaseFoods.BAKED_BERRIES,
                Items.SUGAR_CANE
        );
        IntrinsicTagAppender<Item> protein_food = tag(ModItemTags.PROTEIN_FOOD);
        add(protein_food,
                Items.EGG,
                Items.TURTLE_EGG,
                Items.SNIFFER_EGG,
                Items.DRAGON_EGG,
                ModBaseFoods.COOKED_EGG
        );
        IntrinsicTagAppender<Item> fish_food = tag(ModItemTags.FISH_FOOD);
        add(fish_food,
                Items.COD, Items.COOKED_COD,
                Items.SALMON, Items.COOKED_SALMON,
                ModBaseFoods.RAW_TROUT, ModBaseFoods.COOKED_TROUT,
                Items.KELP, Items.DRIED_KELP
        );
        IntrinsicTagAppender<Item> monster_food = tag(ModItemTags.MONSTER_FOOD);
        add(monster_food,
                Items.RABBIT_FOOT,
                Items.SPIDER_EYE, Items.FERMENTED_SPIDER_EYE,
                Items.TROPICAL_FISH, Items.PUFFERFISH,
                Items.ROTTEN_FLESH
        );
        add(tag(ItemTags.WOOL), ModBlocks.WOOL);
        add(tag(ItemTags.WOOL_CARPETS), ModBlocks.CARPET);
        this.copy(ModBlockTags.AZALEA_LOGS.get(), ModItemTags.AZALEA_LOGS.get());
        this.copy(ModBlockTags.PALM_LOGS.get(), ModItemTags.PALM_LOGS.get());
        this.copy(ModBlockTags.BAOBAB_LOGS.get(), ModItemTags.BAOBAB_LOGS.get());
        tag(ItemTags.LOGS_THAT_BURN).addTag(ModItemTags.AZALEA_LOGS.get()).addTag(ModItemTags.PALM_LOGS.get()).addTag(ModItemTags.BAOBAB_LOGS.get());
        tag(ItemTags.LEAVES).add(ModBlocks.PALM_LEAVES_ITEM.get(), ModBlocks.BAOBAB_LEAVES_ITEM.get());
        tag(ItemTags.SAPLINGS).add(ModBlocks.PALM_SAPLING_ITEM.get(), ModBlocks.BAOBAB_SAPLING_ITEM.get());
        tag(ModItemTags.POISON_SEEDS_COMPONENT_SEED)
                .addTag(Tags.Items.SEEDS_TORCHFLOWER)
                .add(Items.PITCHER_POD);
        tag(ModItemTags.SEEDS_COMPONENT_FOOD)
                .addTag(Tags.Items.SEEDS_PUMPKIN)
                .addTag(Tags.Items.SEEDS_MELON)
                .addTag(Tags.Items.SEEDS_BEETROOT)
                .addTag(Tags.Items.SEEDS_WHEAT);
        tag(ModItemTags.EGG_COMPONENT_FOOD)
                .addTag(Tags.Items.EGGS)
                .add(Items.TURTLE_EGG);
        tag(ModItemTags.VEGETABLE_COMPONENT_OP5F).add(
                Items.KELP, Items.DRIED_KELP,
                Items.BROWN_MUSHROOM, Items.RED_MUSHROOM,
                Items.CRIMSON_FUNGUS, Items.WARPED_FUNGUS);
        tag(ModItemTags.VEGETABLE_COMPONENT_1F).add(
                Items.CARROT, Items.GOLDEN_CARROT,
                Items.BEETROOT, Items.POTATO,
                Items.BAKED_POTATO, Items.POISONOUS_POTATO
        );
        tag(ModItemTags.MEAT_COMPONENT_OP5F).add(
                Items.MUTTON, Items.COOKED_MUTTON,
                Items.CHICKEN, Items.COOKED_CHICKEN,
                Items.RABBIT, Items.RABBIT_FOOT,
                Items.COOKED_RABBIT, Items.ROTTEN_FLESH,
                Items.SPIDER_EYE, Items.FERMENTED_SPIDER_EYE,
                Items.COD, Items.COOKED_COD,
                Items.SALMON, Items.COOKED_SALMON,
                Items.TROPICAL_FISH, Items.PUFFERFISH
        );
        tag(ModItemTags.MEAT_COMPONENT_1F).add(
                Items.BEEF, Items.COOKED_BEEF,
                Items.PORKCHOP, Items.COOKED_PORKCHOP
        );
        tag(ModItemTags.FISH_COMPONENT_1F).add(
                Items.COD, Items.COOKED_COD,
                Items.SALMON, Items.COOKED_SALMON,
                Items.TROPICAL_FISH, Items.PUFFERFISH
        );
        tag(ModItemTags.MONSTER_COMPONENT_1F).add(
                Items.POISONOUS_POTATO, Items.RABBIT_FOOT,
                Items.ROTTEN_FLESH, Items.SPIDER_EYE,
                Items.FERMENTED_SPIDER_EYE, Items.TROPICAL_FISH,
                Items.PUFFERFISH
        );
        tag(ModItemTags.FRUIT_COMPONENT_1F).add(
                Items.APPLE, Items.GOLDEN_APPLE,
                Items.ENCHANTED_GOLDEN_APPLE, Items.CHORUS_FRUIT,
                Items.POPPED_CHORUS_FRUIT, Items.SUGAR_CANE
        );
        tag(ModItemTags.FRUIT_COMPONENT_0P5F).add(
                Items.MELON_SLICE, Items.GLISTERING_MELON_SLICE,
                Items.SWEET_BERRIES, Items.GLOW_BERRIES
        );
        tag(ModItemTags.PROTEIN_COMPONENT_1F).add(Items.EGG, Items.TURTLE_EGG,
                Items.SNIFFER_EGG, Items.DRAGON_EGG);
        tag(ModItemTags.SWEET_COMPONENT_1F).add(Items.SUGAR, Items.HONEY_BOTTLE);
        tag(ModItemTags.WILD_WIND_INVISIBLE.get()).add(ModItems.ASH_DUST.get());
        ModBlockFamilies.AZALEA.generateItemTags(this::tag);
        ModBlockFamilies.PALM.generateItemTags(this::tag);
        ModBlockFamilies.BAOBAB.generateItemTags(this::tag);
        tag(ItemTags.WOLF_FOOD).add(ModBaseFoods.COOKED_TROUT.get(), ModBaseFoods.RAW_TROUT.get(), ModBaseFoods.COOKED_FROG_LEG.get(), ModBaseFoods.FROG_LEG.get(),
                ModBaseFoods.COOKED_PIRANHA.get(), ModBaseFoods.RAW_PIRANHA.get());
        tag(ItemTags.CAT_FOOD).add(ModBaseFoods.RAW_PIRANHA.get());
        tag(ItemTags.OCELOT_FOOD).add(ModBaseFoods.RAW_PIRANHA.get());
        tag(ItemTags.FISHES).add(ModBaseFoods.RAW_PIRANHA.get(), ModBaseFoods.COOKED_PIRANHA.get());
        tag(Tags.Items.CONCRETE_POWDERS).add(ModBlocks.CONCRETE_POWDER_ITEM.get());
        tag(ItemTags.BANNERS).add(ModBlocks.BANNER.get().asItem());
        tag(ItemTags.STAIRS).add(
                ModBlocks.ANDESITE_BRICK_STAIRS_ITEM.get(), ModBlocks.DIORITE_BRICK_STAIRS_ITEM.get(), ModBlocks.GRANITE_BRICK_STAIRS_ITEM.get(),
                ModBlocks.BLUE_ICE_BRICK_STAIRS_ITEM.get()
        );
        tag(ItemTags.SLABS).add(
                ModBlocks.ANDESITE_BRICK_SLAB_ITEM.get(), ModBlocks.DIORITE_BRICK_SLAB_ITEM.get(), ModBlocks.GRANITE_BRICK_SLAB_ITEM.get(),
                ModBlocks.BLUE_ICE_BRICK_SLAB_ITEM.get()
        );
        tag(ItemTags.WALLS).add(
                ModBlocks.STONE_WALL_ITEM.get(), ModBlocks.POLISHED_STONE_WALL_ITEM.get(),
                ModBlocks.ANDESITE_BRICK_WALL_ITEM.get(), ModBlocks.DIORITE_BRICK_WALL_ITEM.get(), ModBlocks.GRANITE_BRICK_WALL_ITEM.get(),
                ModBlocks.BLUE_ICE_BRICK_WALL_ITEM.get()
        );
    }

    public static void add(IntrinsicTagAppender<Item> appender, ItemLike... likes) {
        appender.add(Arrays.stream(likes).map(ItemLike::asItem).toArray(Item[]::new));
    }


    protected IntrinsicTagAppender<Item> tag(Supplier<TagKey<Item>> tag) {
        return super.tag(tag.get());
    }
}
