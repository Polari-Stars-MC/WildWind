package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.datagen.ModBlockFamilies;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class ModCreativeModeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, WildWindMod.MOD_ID);

    @I18n(en_us = "Wild wind: Building block", zh_cn = "原野之风：建筑方块", zh_tw = "原野之風：建築方塊")
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BUILDING_BLOCK = register("building_block",
            ModBlocks.POLISHED_STONE::toStack, () -> ((parameters, output) -> {
                ModBlockFamilies.AZALEA.addCreativeTab(output);
                ModBlockFamilies.PALM.addCreativeTab(output);
                ModBlockFamilies.BAOBAB.addCreativeTab(output);
                output.accept(ModBlocks.STONE_WALL.get());
                output.accept(ModBlocks.POLISHED_STONE.get());
                output.accept(ModBlocks.POLISHED_STONE_STAIRS.get());
                output.accept(ModBlocks.POLISHED_STONE_SLAB.get());
                output.accept(ModBlocks.POLISHED_STONE_WALL.get());
                output.accept(ModBlocks.SALT_BLOCK.get());
                output.accept(ModBlocks.WOOL.get());
                output.accept(ModBlocks.CARPET.get());
                output.accept(ModBlocks.CONCRETE.get());
                output.accept(ModBlocks.CONCRETE_POWDER.get());
                output.accept(ModBlocks.GLAZED_TERRACOTTA.get());
            }));

    @I18n(en_us = "Wild wind: Natural block", zh_cn = "原野之风：自然方块", zh_tw = "原野之風：自然方塊")
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> NATURAL_BLOCK = register("natural_block",
            ModBlocks.SALT_ORE::toStack, () -> ((parameters, output) -> {
                output.accept(ModBlocks.BRITTLE_ICE.get());
                output.accept(ModBlocks.ASH_BLOCK.get());
                output.accept(ModItems.ASH.get());
                output.accept(ModBlocks.SILT.get());
                output.accept(ModBlocks.QUICKSAND.get());
                output.accept(ModBlocks.RED_QUICKSAND.get());
                output.accept(ModBlocks.SALT_ORE.get());
                output.accept(ModBlocks.DEEPSLATE_SALT_ORE.get());
                output.accept(ModBlocks.AZALEA_LOG.get());
                output.accept(ModBlocks.PALM_LOG.get());
                output.accept(ModBlocks.PALM_CROWN.get());
                output.accept(ModBlocks.BAOBAB_LOG.get());
                output.accept(ModBlocks.PALM_LEAVES.get());
                output.accept(ModBlocks.BAOBAB_LEAVES.get());
                output.accept(ModItems.PALM_SAPLING.get());
                output.accept(ModItems.BAOBAB_SAPLING.get());
                output.accept(ModItems.REEDS.get());
                output.accept(ModItems.CATTAILS.get());
                output.accept(ModItems.GLAREFLOWER.get());
                output.accept(ModItems.GLAREFLOWER_SEEDS.get());
                output.accept(ModItems.DUCKWEED.get());
                output.accept(ModBlocks.GLISTERING_MELON.get());
                output.accept(ModBlocks.SCULK_JAW.get());
                output.accept(ModBlocks.SPIDER_COVER.get());
                output.accept(ModItems.SPIDER_MUCOSA.get());
                output.accept(ModItems.SPIDER_EGG.get());
                output.accept(ModItems.TINY_CACTUS.get());
            }), BUILDING_BLOCK.getId());

    @I18n(en_us = "Wild wind: Spawn Eggs", zh_cn = "原野之风：刷怪蛋", zh_tw = "原野之風：生怪蛋")
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SPAWN_EGGS = register("spawn_eggs",
            ModItems.FIREFLY_SPAWN_EGG::toStack, () -> ((parameters, output) -> getItemStream()
                    .filter(item -> item instanceof DeferredSpawnEggItem).forEach(output::accept)), NATURAL_BLOCK.getId());

    @I18n(en_us = "Wild wind: Tools and Utilities", zh_cn = "原野之风：工具与实用物品", zh_tw = "原野之風：工具與實用物品")
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TOOLS_AND_UTILITIES = register("tools_and_utilities",
            ModItems.MAGIC_FLUTE::toStack, () -> ((parameters, output) -> {
                output.accept(ModItems.AZALEA_BOAT.get());
                output.accept(ModItems.AZALEA_CHEST_BOAT.get());
                output.accept(ModItems.PALM_BOAT.get());
                output.accept(ModItems.PALM_CHEST_BOAT.get());
                output.accept(ModItems.BAOBAB_BOAT.get());
                output.accept(ModItems.BAOBAB_CHEST_BOAT.get());
                output.accept(ModItems.AZALEA_SIGN.get());
                output.accept(ModItems.AZALEA_HANGING_SIGN.get());
                output.accept(ModItems.PALM_SIGN.get());
                output.accept(ModItems.PALM_HANGING_SIGN.get());
                output.accept(ModItems.BAOBAB_SIGN.get());
                output.accept(ModItems.BAOBAB_HANGING_SIGN.get());
                output.accept(ModItems.PRESENT.get());
                output.accept(ModItems.TRAPPED_PRESENT.get());
                output.accept(ModBlocks.FIREFLY_JAR.get());
                output.accept(ModItems.GLOW_MUCUS.get());
                output.accept(ModItems.TROUT_BUCKET.get());
                output.accept(ModItems.PIRANHA_BUCKET.get());
                output.accept(ModItems.MAGIC_FLUTE.get());
            }), SPAWN_EGGS.getId());

    @I18n(en_us = "Wild wind: Food & drink", zh_cn = "原野之风：食物与饮品", zh_tw = "原野之風：食物與飲品")
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> FOOD_AND_DRINK = register("food_and_drink",
            ModItems.BAKED_CARROT::toStack, () -> ((parameters, output) -> getItemStream()
                    .filter(item -> item.components.has(DataComponents.FOOD)).forEach(output::accept)), TOOLS_AND_UTILITIES.getId());

    @I18n(en_us = "Wild wind: Ingredients", zh_cn = "原野之风：原材料", zh_tw = "原野之風：原材料")
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> INGREDIENTS = register("ingredients",
            ModItems.GLOW_POWDER::toStack, () -> ((parameters, output) -> {
                output.accept(ModItems.FLOUR.get());
                output.accept(ModItems.GLOW_POWDER.get());
                output.accept(ModItems.ASH_DUST.get());
                output.accept(ModItems.SALT.get());
                output.accept(ModItems.FANGS);
            }), FOOD_AND_DRINK.getId());

    @I18n(en_us = "Wild wind: Misc", zh_cn = "原野之风：杂项", zh_tw = "原野之風：雜項")
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MISC = register("misc",
            ModItems.GLOW_POWDER::toStack, () -> ((parameters, output) -> {
                for (DeferredHolder<Item, ? extends Item> item : ModItems.ITEMS.getEntries()) {
                    if (checkOr(item, BUILDING_BLOCK, NATURAL_BLOCK, TOOLS_AND_UTILITIES, FOOD_AND_DRINK, INGREDIENTS, SPAWN_EGGS)) {
                        output.accept(item.get());
                    }
                }

                ItemStack stack = new ItemStack(Items.SLIME_BALL);
                stack.set(ModComponents.SLIME_COLOR, 100);
                output.accept(stack);
            }));

    private static DeferredHolder<CreativeModeTab, CreativeModeTab> register(
            String name, Supplier<ItemStack> icon,
            Supplier<CreativeModeTab.DisplayItemsGenerator> parameters) {
        return CREATIVE_MODE_TABS.register(name, () -> CreativeModeTab.builder().icon(icon)
                .title(Component.translatable("tabs.%s.%s.title".formatted(WildWindMod.MOD_ID, name)))
                .displayItems(parameters.get()).build());
    }

    private static DeferredHolder<CreativeModeTab, CreativeModeTab> register(
            String name, Supplier<ItemStack> icon,
            Supplier<CreativeModeTab.DisplayItemsGenerator> parameters,
            ResourceLocation tab) {
        return CREATIVE_MODE_TABS.register(name, () -> CreativeModeTab.builder().icon(icon)
                .title(Component.translatable("tabs.%s.%s.title".formatted(WildWindMod.MOD_ID, name)))
                .displayItems(parameters.get()).withTabsBefore(tab).build());
    }

    @SafeVarargs
    public static <T extends Item> boolean checkOr(DeferredHolder<Item, T> item, Supplier<CreativeModeTab>... suppliers) {
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

    private static Stream<? extends Item> getItemStream() {
        return ModItems.ITEMS.getEntries().stream().map(DeferredHolder::get);
    }

}