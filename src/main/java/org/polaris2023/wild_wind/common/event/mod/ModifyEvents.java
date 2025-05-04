package org.polaris2023.wild_wind.common.event.mod;

import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.init.ModComponents;
import org.polaris2023.wild_wind.common.init.ModFoods;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static org.polaris2023.wild_wind.util.EventsModHandler.component;
import static org.polaris2023.wild_wind.util.EventsModHandler.food;

/**
 * @author : baka4n
 * {@code @Date : 2025/05/05 00:06:09}
 */
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = WildWindMod.MOD_ID)
public class ModifyEvents {

    @SubscribeEvent
    public static void addBlockEntityType(BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityType.SIGN,
                ModBlocks.AZALEA_SIGN.get(),
                ModBlocks.AZALEA_WALL_SIGN.get(),
                ModBlocks.PALM_SIGN.get(),
                ModBlocks.PALM_WALL_SIGN.get(),
                ModBlocks.BAOBAB_SIGN.get(),
                ModBlocks.BAOBAB_WALL_SIGN.get()
        );
        event.modify(BlockEntityType.HANGING_SIGN,
                ModBlocks.AZALEA_HANGING_SIGN.get(),
                ModBlocks.AZALEA_WALL_HANGING_SIGN.get(),
                ModBlocks.PALM_HANGING_SIGN.get(),
                ModBlocks.PALM_WALL_HANGING_SIGN.get(),
                ModBlocks.BAOBAB_HANGING_SIGN.get(),
                ModBlocks.BAOBAB_WALL_HANGING_SIGN.get()
        );

    }

    @SubscribeEvent
    public static void modifyDefaultComponent(ModifyDefaultComponentsEvent event) {
        event.modify(Items.EGG, food(ModFoods.EGG));
        event.modify(Items.TURTLE_EGG, food(ModFoods.EGG));
        event.modify(Items.SNIFFER_EGG, food(ModFoods.SNIFFER_EGG));
        event.modify(Items.DRAGON_EGG, food(ModFoods.DRAGON_EGG));
        event.modify(Items.BROWN_MUSHROOM, food(ModFoods.BROWN_MUSHROOM));
        event.modify(Items.RED_MUSHROOM, food(ModFoods.RED_MUSHROOM));
        event.modify(Items.CRIMSON_FUNGUS, food(ModFoods.CRIMSON_FUNGUS));
        event.modify(Items.WARPED_FUNGUS, food(ModFoods.WARPED_FUNGUS));
        event.modify(Items.BEETROOT_SEEDS, food(ModFoods.SEEDS));
        event.modify(Items.MELON_SEEDS, food(ModFoods.SEEDS));
        event.modify(Items.PUMPKIN_SEEDS, food(ModFoods.SEEDS));
        event.modify(Items.WHEAT_SEEDS, food(ModFoods.SEEDS));
        event.modify(Items.TORCHFLOWER_SEEDS, food(ModFoods.POISON_SEEDS));
        event.modify(Items.PITCHER_POD, food(ModFoods.POISON_SEEDS));
        event.modify(Items.SUGAR_CANE, food(ModFoods.SUGAR_CANE));
        event.modify(Items.SUGAR, food(ModFoods.SUGAR));
        event.modify(Items.FERMENTED_SPIDER_EYE, food(ModFoods.FERMENTED_SPIDER_EYE));
        event.modify(Items.GLISTERING_MELON_SLICE, food(ModFoods.GLISTERING_MELON_SLICE));
        event.modify(Items.MILK_BUCKET, food(ModFoods.MILK));
        event.modify(Items.KELP, food(ModFoods.KELP));
        event.modify(Items.BEEF, food(ModFoods.RAW_BEEF));
        event.modify(Items.MUTTON, food(ModFoods.RAW_MUTTON));
        event.modify(Items.PORKCHOP, food(ModFoods.RAW_PORKCHOP));
        event.modify(Items.RABBIT, food(ModFoods.RAW_RABBIT));
        event.modify(Items.RABBIT_FOOT, food(ModFoods.RABBIT_FOOT));
        event.modify(Items.COD, food(ModFoods.RAW_COD));
        event.modify(Items.SALMON, food(ModFoods.RAW_SALMON));
        event.modify(Items.TROPICAL_FISH, food(ModFoods.RAW_TROPICAL_FISH));
        event.modify(Items.POPPED_CHORUS_FRUIT, food(ModFoods.POPPED_CHORUS_FRUIT));
        BiConsumer<ItemLike, Consumer<DataComponentPatch.Builder>> modify = event::modify;
        component(modify, ModComponents.VEGETABLE_VALUE, 0.5F,
                Items.KELP, Items.DRIED_KELP,
                Items.BROWN_MUSHROOM, Items.RED_MUSHROOM,
                Items.CRIMSON_FUNGUS, Items.WARPED_FUNGUS);
        component(modify, ModComponents.VEGETABLE_VALUE, 1F,
                Items.CARROT, Items.GOLDEN_CARROT,
                Items.BEETROOT, Items.POTATO,
                Items.BAKED_POTATO, Items.POISONOUS_POTATO
        );

        component(modify, ModComponents.MEAT_VALUE, 1F,
                Items.BEEF, Items.COOKED_BEEF,
                Items.PORKCHOP, Items.COOKED_PORKCHOP
        );
        component(modify, ModComponents.MEAT_VALUE, 0.5F,
                Items.MUTTON, Items.COOKED_MUTTON,
                Items.CHICKEN, Items.COOKED_CHICKEN,
                Items.RABBIT, Items.RABBIT_FOOT,
                Items.COOKED_RABBIT, Items.ROTTEN_FLESH,
                Items.SPIDER_EYE, Items.FERMENTED_SPIDER_EYE,
                Items.COD, Items.COOKED_COD,
                Items.SALMON, Items.COOKED_SALMON,
                Items.TROPICAL_FISH, Items.PUFFERFISH
        );
        component(modify, ModComponents.FISH_VALUE, 1F,
                Items.COD, Items.COOKED_COD,
                Items.SALMON, Items.COOKED_SALMON,
                Items.TROPICAL_FISH, Items.PUFFERFISH
        );
        component(modify, ModComponents.MONSTER_VALUE, 1F,
                Items.POISONOUS_POTATO, Items.RABBIT_FOOT,
                Items.ROTTEN_FLESH, Items.SPIDER_EYE,
                Items.FERMENTED_SPIDER_EYE, Items.TROPICAL_FISH,
                Items.PUFFERFISH
        );
        component(modify, ModComponents.FRUIT_VALUE, 1F,
                Items.APPLE, Items.GOLDEN_APPLE,
                Items.ENCHANTED_GOLDEN_APPLE, Items.CHORUS_FRUIT,
                Items.POPPED_CHORUS_FRUIT, Items.SUGAR_CANE
        );
        component(modify, ModComponents.FRUIT_VALUE, 0.5F,
                Items.MELON_SLICE, Items.GLISTERING_MELON_SLICE,
                Items.SWEET_BERRIES, Items.GLOW_BERRIES
        );
        component(modify, ModComponents.PROTEIN_VALUE, 1F,
                Items.EGG, Items.TURTLE_EGG,
                Items.SNIFFER_EGG, Items.DRAGON_EGG
        );
        component(modify, ModComponents.SWEET_VALUE, 1F,
                Items.SUGAR, Items.HONEY_BOTTLE
        );

    }
}
