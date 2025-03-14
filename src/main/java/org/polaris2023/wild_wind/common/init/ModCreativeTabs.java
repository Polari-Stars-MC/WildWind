package org.polaris2023.wild_wind.common.init;

import biomesoplenty.init.ModCreativeTab;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.wild_wind.common.init.blocks.ModPresents;
import org.polaris2023.wild_wind.common.init.items.ModBaseItems;
import org.polaris2023.wild_wind.common.init.items.entity.ModBoats;
import org.polaris2023.wild_wind.common.init.items.entity.ModMobBuckets;
import org.polaris2023.wild_wind.common.init.items.entity.ModSpawnEggs;
import org.polaris2023.wild_wind.common.init.items.foods.ModBaseFoods;
import org.polaris2023.wild_wind.datagen.ModBlockFamilies;

import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

import static org.polaris2023.wild_wind.WildWindMod.MOD_ID;
import static org.polaris2023.wild_wind.common.init.ModInitializer.TABS;


@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = MOD_ID)
public enum ModCreativeTabs implements Supplier<CreativeModeTab> {
    @I18n(en_us = "Wild wind: Building block", zh_cn = "原野之风：建筑方块", zh_tw = "原野之風：建築方塊")
    BUILDING_BLOCK(ModBlocks.POLISHED_STONE::toStack, () -> (__, output) -> {
        output.accept(ModBlocks.AZALEA_LOG);
        output.accept(ModBlocks.AZALEA_WOOD);
        output.accept(ModBlocks.STRIPPED_AZALEA_LOG);
        output.accept(ModBlocks.STRIPPED_AZALEA_WOOD);
        ModBlockFamilies.AZALEA_PLANKS.addCreativeTab(output);

        output.accept(ModBlocks.PALM_LOG);
        output.accept(ModBlocks.PALM_WOOD);
        output.accept(ModBlocks.STRIPPED_PALM_LOG);
        output.accept(ModBlocks.STRIPPED_PALM_WOOD);
        output.accept(ModBlocks.PALM_CROWN);
        ModBlockFamilies.PALM_PLANKS.addCreativeTab(output);

        output.accept(ModBlocks.BAOBAB_LOG);
        output.accept(ModBlocks.BAOBAB_WOOD);
        output.accept(ModBlocks.STRIPPED_BAOBAB_LOG);
        output.accept(ModBlocks.STRIPPED_BAOBAB_WOOD);
        ModBlockFamilies.BAOBAB_PLANKS.addCreativeTab(output);

        output.accept(ModBlocks.STONE_WALL);
        output.accept(ModBlocks.POLISHED_STONE);
        output.accept(ModBlocks.POLISHED_STONE_STAIRS);
        output.accept(ModBlocks.POLISHED_STONE_SLAB);
        output.accept(ModBlocks.POLISHED_STONE_WALL);

        output.accept(ModBlocks.SALT_BLOCK);
        output.accept(ModBlocks.WOOL);
        output.accept(ModBlocks.CARPET);
        output.accept(ModBlocks.CONCRETE);
        output.accept(ModBlocks.CONCRETE_POWDER);
        output.accept(ModBlocks.GLAZED_TERRACOTTA);
    }),
    @I18n(en_us = "Wild wind: Natural block", zh_cn = "原野之风：自然方块", zh_tw = "原野之風：自然方塊")
    NATURAL_BLOCKS(ModBlocks.SALT_ORE_ITEM::toStack, () -> (__, output) -> {
        output.accept(ModBlocks.BRITTLE_ICE_ITEM);
        output.accept(ModBlocks.ASH_BLOCK_ITEM);
        output.accept(ModBlocks.ASH);
        output.accept(ModBlocks.SILT);
        output.accept(ModBlocks.QUICKSAND);
        output.accept(ModBlocks.RED_QUICKSAND);
        output.accept(ModBlocks.SALT_ORE);
        output.accept(ModBlocks.DEEPSLATE_SALT_ORE);
        output.accept(ModBlocks.AZALEA_LOG);
        output.accept(ModBlocks.PALM_LOG);
        output.accept(ModBlocks.PALM_CROWN);
        output.accept(ModBlocks.BAOBAB_LOG);
        output.accept(ModBlocks.PALM_LEAVES);
        output.accept(ModBlocks.BAOBAB_LEAVES);
        output.accept(ModBlocks.PALM_SAPLING);
        output.accept(ModBlocks.BAOBAB_SAPLING);
        output.accept(ModBlocks.REEDS);
        output.accept(ModBlocks.CATTAILS);
        output.accept(ModBlocks.GLAREFLOWER);
        output.accept(ModBlocks.GLAREFLOWER_SEEDS);
        output.accept(ModBlocks.DUCKWEED);
        output.accept(ModBlocks.GLISTERING_MELON);
        output.accept(ModBlocks.SCULK_JAW);
        output.accept(ModBlocks.SPIDER_COVER);
        output.accept(ModBaseItems.SPIDER_MUCOSA);
        output.accept(ModBaseItems.SPIDER_EGG);
        output.accept(ModBlocks.TINY_CACTUS);
    }, BUILDING_BLOCK),
    @I18n(en_us = "Wild wind: Spawn Eggs", zh_cn = "原野之风：刷怪蛋", zh_tw = "原野之風：生怪蛋")
    SPAWN_EGGS(ModSpawnEggs.FIREFLY_SPAWN_EGG.entry::toStack, () -> (__, output) -> {
        output.accept(ModSpawnEggs.FIREFLY_SPAWN_EGG);
        output.accept(ModSpawnEggs.TROUT_SPAWN_EGG);
        output.accept(ModSpawnEggs.PIRANHA_SPAWN_EGG);
    }, NATURAL_BLOCKS),
    @I18n(en_us = "Wild wind: Tools and Utilities", zh_cn = "原野之风：工具与实用物品", zh_tw = "原野之風：工具與實用物品")
    TOOLS_AND_UTILITIES(ModItems.MAGIC_FLUTE::toStack, () -> (__, output) -> {
        output.accept(ModBoats.AZALEA_BOAT);
        output.accept(ModBoats.AZALEA_CHEST_BOAT);
        output.accept(ModBoats.PALM_BOAT);
        output.accept(ModBoats.PALM_CHEST_BOAT);
        output.accept(ModBoats.BAOBAB_BOAT);
        output.accept(ModBoats.BAOBAB_CHEST_BOAT);
        output.accept(ModBlocks.AZALEA_SIGN);
        output.accept(ModBlocks.AZALEA_HANGING_SIGN);
        output.accept(ModBlocks.PALM_SIGN);
        output.accept(ModBlocks.PALM_HANGING_SIGN);
        output.accept(ModBlocks.BAOBAB_SIGN);
        output.accept(ModBlocks.BAOBAB_HANGING_SIGN);
        output.accept(ModPresents.DEFAULT.present);
        output.accept(ModPresents.DEFAULT.trapped_present);
        output.accept(ModBlocks.FIREFLY_JAR);
        output.accept(ModBlocks.GLOW_MUCUS);
        output.accept(ModMobBuckets.TROUT_BUCKET);
        output.accept(ModMobBuckets.PIRANHA_BUCKET);
        output.accept(ModItems.MAGIC_FLUTE);

    }, SPAWN_EGGS),
    @I18n(en_us = "Wild wind: Food & drink", zh_cn = "原野之风：食物与饮品", zh_tw = "原野之風：食物與飲品")
    FOOD_AND_DRINK(ModBaseFoods.BAKED_CARROT.entry::toStack,
            () -> (__, output) -> {
                output.accept(ModBaseFoods.BAKED_APPLE);
                output.accept(ModBaseFoods.BAKED_MELON_SLICE);
                output.accept(ModBaseFoods.PUMPKIN_SLICE);
                output.accept(ModBaseFoods.BAKED_PUMPKIN_SLICE);
                output.accept(ModBaseFoods.BAKED_MUSHROOM);
                output.accept(ModBaseFoods.BAKED_SEEDS);
                output.accept(ModBaseFoods.BAKED_BERRIES);
                output.accept(ModBaseFoods.BAKED_CARROT);
                output.accept(ModBaseFoods.BAKED_BEETROOT);
                output.accept(ModItems.LIVING_TUBER);
                output.accept(ModBaseFoods.BAKED_LIVING_TUBER);
                output.accept(ModBaseFoods.RAW_FROG_LEG);
                output.accept(ModBaseFoods.COOKED_FROG_LEG);
                output.accept(ModBaseFoods.RAW_TROUT);
                output.accept(ModBaseFoods.COOKED_TROUT);
                output.accept(ModBaseFoods.RAW_PIRANHA);
                output.accept(ModBaseFoods.COOKED_PIRANHA);
                output.accept(ModBaseFoods.DOUGH);
                output.accept(ModBaseFoods.COOKED_EGG);
                output.accept(ModItems.CHEESE);
            }, TOOLS_AND_UTILITIES),
    @I18n(en_us = "Wild wind: Ingredients", zh_cn = "原野之风：原材料", zh_tw = "原野之風：原材料")
    INGREDIENTS(ModItems.GLOW_POWDER::toStack, () -> (__, output) -> {
        output.accept(ModBaseFoods.FLOUR);
        output.accept(ModItems.GLOW_POWDER);
        output.accept(ModBaseItems.ASH_DUST);
        output.accept(ModBaseItems.SALT);
    }, FOOD_AND_DRINK),
    @I18n(en_us = "Wild wind: Misc", zh_cn = "原野之风：杂项", zh_tw = "原野之風：雜項")
    WILD_WIND(ModBlocks.COOKING_POT_ITEM::toStack,
            () -> (__, output) -> {
                for (DeferredHolder<Item, ? extends Item> item : ModInitializer.items()) {
                    if (checkOr(item,
                            BUILDING_BLOCK,
                            NATURAL_BLOCKS,
                            TOOLS_AND_UTILITIES,
                            FOOD_AND_DRINK,
                            INGREDIENTS,
                            SPAWN_EGGS
                    )) {
                        output.accept(item.get());
                    }
                }
                //肉食
                ItemStack stack = new ItemStack(Items.SLIME_BALL);
                stack.set(ModComponents.SLIME_COLOR, 100);
                output.accept(stack);
            }),

    ;

    @SafeVarargs
    private static <T extends Item> boolean checkOr(DeferredHolder<Item, T> item, Supplier<CreativeModeTab>... suppliers) {
        for (Supplier<CreativeModeTab> supplier : suppliers) {
            if (!check(item, supplier)) {
                return false;
            }
        }
        return true;
    }

    private static <T extends Item> boolean check(DeferredHolder<Item, T> item, Supplier<CreativeModeTab> supplier) {
        return supplier.get().getDisplayItems().stream().filter(stack -> stack.is(item)).findFirst().isEmpty();
    }

    private final DeferredHolder<CreativeModeTab, CreativeModeTab> tabs;
    ModCreativeTabs(Supplier<ItemStack> icon,
                    Supplier<CreativeModeTab.DisplayItemsGenerator> parameters,
                    ModCreativeTabs tab) {
        tabs = TABS.register(name().toLowerCase(Locale.ROOT), () -> CreativeModeTab
                .builder()
                .icon(icon)
                .title(Component.translatable("tabs.%s.%s.title"
                        .formatted(
                                MOD_ID,
                                name().toLowerCase(Locale.ROOT))))
                .displayItems(parameters.get())
                .withTabsBefore(tab.tabs.getKey())
                .build());
    }

    ModCreativeTabs(Supplier<ItemStack> icon,
                    Supplier<CreativeModeTab.DisplayItemsGenerator> parameters) {
        tabs = TABS.register(name().toLowerCase(Locale.ROOT), () -> CreativeModeTab
                .builder()
                .icon(icon)
                .title(Component.translatable("tabs.%s.%s.title"
                        .formatted(
                                MOD_ID,
                                name().toLowerCase(Locale.ROOT))))
                .displayItems(parameters.get())
                .build());
    }

    @SubscribeEvent
    public static void buildGroup(BuildCreativeModeTabContentsEvent event) {

    }

    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public CreativeModeTab get() {
        return tabs.get();
    }
}
