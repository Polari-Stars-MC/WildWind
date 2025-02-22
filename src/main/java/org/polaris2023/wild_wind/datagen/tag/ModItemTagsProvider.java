package org.polaris2023.wild_wind.datagen.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModItems;
import org.polaris2023.wild_wind.common.init.items.foods.ModBaseFoods;
import org.polaris2023.wild_wind.common.init.tags.ModBlockTags;
import org.polaris2023.wild_wind.common.init.tags.ModItemTags;
import org.polaris2023.wild_wind.datagen.ModBlockFamilies;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ModItemTagsProvider extends ItemTagsProvider {


    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, WildWindMod.MOD_ID, existingFileHelper);
    }

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
        this.copy(ModBlockTags.AZALEA_LOGS.get(), ModItemTags.AZALEA_LOGS.get());
        ModBlockFamilies.AZALEA_PLANKS.generateItemTags(this::tag);
    }

    public static void add(IntrinsicTagAppender<Item> appender, ItemLike... likes) {
        appender.add(Arrays.stream(likes).map(ItemLike::asItem).toArray(Item[]::new));
    }


    protected IntrinsicTagAppender<Item> tag(Supplier<TagKey<Item>> tag) {
        return super.tag(tag.get());
    }
}
