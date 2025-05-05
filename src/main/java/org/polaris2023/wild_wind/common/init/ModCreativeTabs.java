package org.polaris2023.wild_wind.common.init;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.wild_wind.common.init.items.ModBaseItems;
import org.polaris2023.wild_wind.common.init.items.entity.ModBoats;
import org.polaris2023.wild_wind.common.init.items.entity.ModMobBuckets;
import org.polaris2023.wild_wind.common.init.items.entity.ModSpawnEggs;
import org.polaris2023.wild_wind.common.init.items.foods.ModBaseFoods;
import org.polaris2023.wild_wind.util.data.ModBlockFamilies;
import org.polaris2023.wild_wind.util.interfaces.registry.ItemRegistry;

import java.util.Locale;
import java.util.function.Supplier;

import static org.polaris2023.wild_wind.WildWindMod.MOD_ID;
import static org.polaris2023.wild_wind.common.init.ModInitializer.TABS;


@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = MOD_ID)
public enum ModCreativeTabs implements Supplier<CreativeModeTab> {
    @I18n(en_us = "Wild wind: Building block", zh_cn = "原野之风：建筑方块", zh_tw = "原野之風：建築方塊")
    BUILDING_BLOCK(ModBlocks.POLISHED_STONE::toStack, () -> (__, output) -> {
        ModBlockFamilies.AZALEA.addCreativeTab(output);
        ModBlockFamilies.PALM.addCreativeTab(output);
        ModBlockFamilies.BAOBAB.addCreativeTab(output);

        output.accept(ModBlocks.STONE_WALL);
        output.accept(ModBlocks.POLISHED_STONE);
        output.accept(ModBlocks.POLISHED_STONE_STAIRS);
        output.accept(ModBlocks.POLISHED_STONE_SLAB);
        output.accept(ModBlocks.POLISHED_STONE_WALL);
        output.accept(ModBlocks.POLISHED_GRANITE_WALL);
        output.accept(ModBlocks.GRANITE_BRICKS);
        output.accept(ModBlocks.CRACKED_GRANITE_BRICKS);
        output.accept(ModBlocks.GRANITE_BRICK_STAIRS);
        output.accept(ModBlocks.GRANITE_BRICK_SLAB);
        output.accept(ModBlocks.GRANITE_BRICK_WALL);
        output.accept(ModBlocks.CHISELED_GRANITE_BRICKS);
        output.accept(ModBlocks.DIORITE_BRICKS);
        output.accept(ModBlocks.CRACKED_DIORITE_BRICKS);
        output.accept(ModBlocks.DIORITE_BRICK_STAIRS);
        output.accept(ModBlocks.DIORITE_BRICK_SLAB);
        output.accept(ModBlocks.DIORITE_BRICK_WALL);
        output.accept(ModBlocks.CHISELED_DIORITE_BRICKS);
        output.accept(ModBlocks.ANDESITE_BRICKS);
        output.accept(ModBlocks.CRACKED_ANDESITE_BRICKS);
        output.accept(ModBlocks.ANDESITE_BRICK_STAIRS);
        output.accept(ModBlocks.ANDESITE_BRICK_SLAB);
        output.accept(ModBlocks.ANDESITE_BRICK_WALL);
        output.accept(ModBlocks.CHISELED_ANDESITE_BRICKS);
        output.accept(ModBlocks.BLUE_ICE_BRICKS);
        output.accept(ModBlocks.CRACKED_BLUE_ICE_BRICKS);
        output.accept(ModBlocks.BLUE_ICE_BRICK_STAIRS);
        output.accept(ModBlocks.BLUE_ICE_BRICK_SLAB);
        output.accept(ModBlocks.BLUE_ICE_BRICK_WALL);
        output.accept(ModBlocks.CHISELED_BLUE_ICE_BRICKS);

        output.accept(ModBlocks.SALT_BLOCK);
        output.accept(ModBlocks.WOOL);
        output.accept(ModBlocks.CARPET);
        output.accept(ModBlocks.CONCRETE);
        output.accept(ModBlocks.CONCRETE_POWDER);
        output.accept(ModBlocks.GLAZED_TERRACOTTA);
    }),
    @I18n(en_us = "Wild wind: Natural block", zh_cn = "原野之风：自然方块", zh_tw = "原野之風：自然方塊")
    NATURAL_BLOCKS(ModBlocks.SALT_ORE::toStack, () -> (__, output) -> {
        output.accept(ModBlocks.BRITTLE_ICE);
        output.accept(ModBlocks.PYROCLAST);
        output.accept(ModBlocks.SILT);
        output.accept(ModBlocks.QUICKSAND);
        output.accept(ModBlocks.RED_QUICKSAND);
        output.accept(ModBlocks.ASH_BLOCK);
        output.accept(ModBlocks.ASH);
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
        output.accept(ModBlocks.COBWEB_COVER);
        output.accept(ModBaseItems.COBWEB_MUCOSA);
        output.accept(ModBaseItems.SPIDER_EGG);
        output.accept(ModBlocks.TINY_CACTUS);
        output.accept(ModBlocks.FLUFFY_DANDELION);
        output.accept(ModBlocks.ROSE);
    }, BUILDING_BLOCK),
    @I18n(en_us = "Wild wind: Spawn Eggs", zh_cn = "原野之风：刷怪蛋", zh_tw = "原野之風：生怪蛋")
    SPAWN_EGGS(ModSpawnEggs.FIREFLY_SPAWN_EGG.entry::toStack, () -> (__, output) -> {
        output.accept(ModSpawnEggs.FIREFLY_SPAWN_EGG);
        output.accept(ModSpawnEggs.TROUT_SPAWN_EGG);
        output.accept(ModSpawnEggs.PIRANHA_SPAWN_EGG);
        output.accept(ModSpawnEggs.GLARE_SPAWN_EGG);
    }, NATURAL_BLOCKS),
    @I18n(en_us = "Wild wind: Tools and Utilities", zh_cn = "原野之风：工具与实用物品", zh_tw = "原野之風：工具與實用物品")
    TOOLS_AND_UTILITIES(ModItems.MAGIC_FLUTE::toStack, () -> (__, output) -> {
        output.accept(ModBlocks.BED);
        output.accept(ModBlocks.BANNER);
        output.accept(ModBlocks.FIREFLY_JAR);
        output.accept(ModBlocks.GLOW_MUCUS);
//        output.accept(ModBlocks.NEST);
//        output.accept(ModBlocks.FEEDING_TROUGH);
//        output.accept(ModBlocks.KILN);
        output.accept(ModBlocks.COOKING_POT);
        output.accept(ModBlocks.PRESENT);
        output.accept(ModBlocks.TRAPPED_PRESENT);
        output.accept(ModBlocks.AZALEA_SIGN);
        output.accept(ModBlocks.AZALEA_HANGING_SIGN);
        output.accept(ModBlocks.PALM_SIGN);
        output.accept(ModBlocks.PALM_HANGING_SIGN);
        output.accept(ModBlocks.BAOBAB_SIGN);
        output.accept(ModBlocks.BAOBAB_HANGING_SIGN);
        output.accept(ModMobBuckets.TROUT_BUCKET);
        output.accept(ModMobBuckets.PIRANHA_BUCKET);
//        output.accept(ModItems.ANCIENT_CODEX);
        output.accept(ModBoats.AZALEA_BOAT);
        output.accept(ModBoats.AZALEA_CHEST_BOAT);
        output.accept(ModBoats.PALM_BOAT);
        output.accept(ModBoats.PALM_CHEST_BOAT);
        output.accept(ModBoats.BAOBAB_BOAT);
        output.accept(ModBoats.BAOBAB_CHEST_BOAT);
        output.accept(ModItems.MAGIC_FLUTE);
//        output.accept(ModItems.CHAINMAIL_HORSE_ARMOR);
//        output.accept(ModItems.NETHERITE_HORSE_ARMOR);
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
                output.accept(ModBaseFoods.VENISON);
                output.accept(ModBaseFoods.COOKED_VENISON);
                output.accept(ModBaseFoods.BAT_WING);
                output.accept(ModBaseFoods.COOKED_BAT_WING);
                output.accept(ModBaseFoods.FROG_LEG);
                output.accept(ModBaseFoods.COOKED_FROG_LEG);
                output.accept(ModBaseFoods.CALAMARI);
                output.accept(ModBaseFoods.GLOWING_CALAMARI);
                output.accept(ModBaseFoods.COOKED_CALAMARI);
                output.accept(ModBaseFoods.RAW_TROUT);
                output.accept(ModBaseFoods.COOKED_TROUT);
                output.accept(ModBaseFoods.RAW_PIRANHA);
                output.accept(ModBaseFoods.COOKED_PIRANHA);
                output.accept(ModBaseFoods.DOUGH);
                output.accept(ModBaseFoods.BERRY_PIE);
                output.accept(ModBaseFoods.APPLE_PIE);
                output.accept(ModBaseFoods.GOLDEN_APPLE_PIE);
                output.accept(ModBaseFoods.ENCHANTED_GOLDEN_APPLE_PIE);
                output.accept(ModBaseFoods.COOKED_EGG);
                output.accept(ModItems.CHEESE);
                output.accept(ModBaseFoods.FAILED_CUISINE);
                output.accept(ModBaseFoods.CHARRED_CUISINE);
                output.accept(ModItems.MILK_BOTTLE);
                output.accept(ModItems.SPLASH_MILK_BOTTLE);
                output.accept(ModItems.LINGERING_MILK_BOTTLE);
                output.accept(ModItems.SPLASH_HONEY_BOTTLE);
                output.accept(ModItems.LINGERING_HONEY_BOTTLE);
                output.accept(ModItems.HONEY_BUCKET);
            }, TOOLS_AND_UTILITIES),
    @I18n(en_us = "Wild wind: Ingredients", zh_cn = "原野之风：原材料", zh_tw = "原野之風：原材料")
    INGREDIENTS(ModItems.GLOW_POWDER::toStack, () -> (__, output) -> {
        output.accept(ModBaseFoods.FLOUR);
//        output.accept(ModItems.CHARRED_BONE);
//        output.accept(ModItems.FISH_BONE);
//        output.accept(ModItems.FANGS);
        output.accept(ModItems.ASH_DUST);
        output.accept(ModItems.FUR);
        output.accept(ModItems.GLOW_POWDER);
        output.accept(ModBaseItems.SALT);
//        output.accept(ModItems.TORN_PAGES);
//        output.accept(ModItems.AUTO_SMELTING);
//        output.accept(ModItems.CURSE_OF_RUSTING_1);
//        output.accept(ModItems.CURSE_OF_RUSTING_2);
//        output.accept(ModItems.CURSE_OF_RUSTING_3);
        output.accept(ModBaseItems.CANDY);
    }, FOOD_AND_DRINK),
    @I18n(en_us = "Wild wind: Misc", zh_cn = "原野之风：杂项", zh_tw = "原野之風：雜項")
    WILD_WIND(ModBlocks.COOKING_POT::toStack,
            () -> (__, output) -> {
                for (DeferredHolder<Item, ? extends Item> item : ItemRegistry.entry()) {
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
