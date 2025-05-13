package org.polaris2023.wild_wind.common.init;


import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraft.world.level.block.entity.BannerPatternLayers;

import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.annotation.enums.RegType;
import org.polaris2023.annotation.enums.TagType;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.annotation.modelgen.item.BasicItem;
import org.polaris2023.annotation.modelgen.item.ParentItem;
import org.polaris2023.annotation.handler.RegistryHandler;
import org.polaris2023.annotation.tag.CTag;
import org.polaris2023.wild_wind.common.block.item.PresentBlockItem;
import org.polaris2023.wild_wind.common.block.item.TrappedPresentBlockItem;
import org.polaris2023.wild_wind.common.item.*;
import org.polaris2023.wild_wind.common.item.food.CheeseItem;
import org.polaris2023.wild_wind.common.item.food.NetherMushroomStewItem;
import org.polaris2023.wild_wind.common.item.modified.ModBannerItem;

import static org.polaris2023.wild_wind.WildWindMod.MOD_ID;
import static org.polaris2023.wild_wind.util.interfaces.registry.ItemRegistry.*;

@RegistryHandler(RegType.Item)
public class ModItems {
    public static final DeferredRegister.Items REGISTER = DeferredRegister.createItems(MOD_ID);

    @BasicItem
    @I18n(en_us = "Living Tuber", zh_cn = "活根", zh_tw = "活根")
    @CTag(names = "vegetable_food",type = TagType.Item)
    public static final DeferredItem<LivingTuberItem> LIVING_TUBER =
            register("living_tuber", properties -> new LivingTuberItem(properties
                    .stacksTo(16)
                    .component(ModComponents.VEGETABLE_VALUE, 1F)
                    .component(ModComponents.MEAT_VALUE, 1F)
                    .component(ModComponents.MONSTER_VALUE, 1F)
                    .food(ModFoods.LIVING_TUBER.get())));

    @BasicItem
    @I18n(en_us = "Fur", zh_cn = "毛皮", zh_tw = "毛皮")
    public static final DeferredItem<Item> FUR = simpleItem("fur");

    @I18n(en_us = "Magic Wand Tool", zh_cn = "魔棒工具", zh_tw = "魔棒工具")
    @ParentItem(parent = "minecraft:item/stick")
    public static final DeferredItem<MagicWandToolItem> MAGIC_WAND_TOOL_ITEM =
            register("magic_wand_tool", p -> new MagicWandToolItem(p.stacksTo(1)));

    @BasicItem
    @I18n(en_us = "Glow Powder", zh_cn = "萤光粉末", zh_tw = "螢光粉末")
    public static final DeferredItem<GlowPowderItem> GLOW_POWDER =
            register("glow_powder", p -> new GlowPowderItem(p.stacksTo(64)));

    @BasicItem
    @I18n(en_us = "Ash Dust", zh_cn = "灰烬粉末", zh_tw = "灰烬粉末")
    @CTag(names = "wild_wind_invisible", type = TagType.Item)
    public static final DeferredItem<AshDustItem> ASH_DUST =
            register("ash_dust", p -> new AshDustItem(p.stacksTo(64)));

    @BasicItem(used = false)// don't run datagen by this
    @I18n(en_us = "Magic Flute", zh_cn = "魔笛", zh_tw = "魔笛")
    public static final DeferredItem<MagicFluteItem> MAGIC_FLUTE =
            register("magic_flute", MagicFluteItem::stackTo1);

    @BasicItem
    @I18n(en_us = "Cheese", zh_cn = "奶酪", zh_tw = "起司")
    public static final DeferredItem<CheeseItem> CHEESE =
            register("cheese", p -> new CheeseItem(p.stacksTo(16).food(ModFoods.CHEESE.get())));
    @BasicItem
    @I18n(en_us = "Russian Soup", zh_cn = "罗宋汤", zh_tw = "羅宋湯")
    public static final DeferredItem<Item> RUSSIAN_SOUP =
            simpleItem("russian_soup", p -> p.stacksTo(1));
    @BasicItem
    @I18n(en_us = "Vegetable Soup", zh_cn = "蔬菜浓汤", zh_tw = "蔬菜濃湯")
    public static final DeferredItem<Item> VEGETABLE_SOUP =
            simpleItem("vegetable_soup", p -> p.stacksTo(1));

    @BasicItem
    @I18n(en_us = "Nether Mushroom Stew", zh_cn = "下界蘑菇煲", zh_tw = "下界蘑菇煲")
    public static final DeferredItem<NetherMushroomStewItem> NETHER_MUSHROOM_STEW =
            register("nether_mushroom_stew", properties -> 
                    new NetherMushroomStewItem(properties.stacksTo(1), ModFoods.NETHER_MUSHROOM_STEW));

    public static final DeferredItem<PresentBlockItem> PRESENT_ITEM = register("present", p -> new PresentBlockItem(ModBlocks.PRESENT.get(), p));

    public static final DeferredItem<TrappedPresentBlockItem> TRAPPED_PRESENT_ITEM = register("trapped_present", p -> new TrappedPresentBlockItem(ModBlocks.TRAPPED_PRESENT.get(), p));

    public static final DeferredItem<ModBannerItem> BANNER_ITEM = register("banner", p -> new ModBannerItem(ModBlocks.BANNER.get(), ModBlocks.WALL_BANNER.get(), p.stacksTo(16).component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)));
    @I18n(en_us = "Ancient Codex", zh_cn = "远古典籍", zh_tw = "遠古典籍", descriptionId = "item.wild_wind.patchouli.ancient_codex")
    public static final Object ANCIENT_CODEX = new Object();

    @I18n(en_us = "Fangs", zh_cn = "尖牙", zh_tw = "")
    @BasicItem
    public static final DeferredItem<Item> FANGS = simpleItem("fangs");

    @I18n(en_us = "Netherite Apple", zh_cn = "下界合金苹果", zh_tw = "")
    @BasicItem
    public static final DeferredItem<Item> NETHERITE_APPLE = simpleItem("netherite_apple");

    @I18n(en_us = "Netherite Apple Pie", zh_cn = "下界合金苹果派", zh_tw = "")
    @BasicItem
    public static final DeferredItem<Item> NETHERITE_APPLE_PIE = simpleItem("netherite_apple_pie");

    @I18n(en_us = "Torn Pages", zh_cn = "残破书页", zh_tw = "")
    @BasicItem
    public static final DeferredItem<Item> TORN_PAGES = simpleItem("torn_pages");

    @BasicItem
    @I18n(en_us = "Milk Bottle", zh_cn = "奶瓶", zh_tw = "奶瓶")
    public static final DeferredItem<Item> MILK_BOTTLE =
            simpleItem("milk_bottle", p -> p.stacksTo(1));

    @BasicItem
    @I18n(en_us = "Splash Milk Bottle", zh_cn = "喷溅型奶瓶", zh_tw = "噴濺型奶瓶")
    public static final DeferredItem<Item> SPLASH_MILK_BOTTLE =
            simpleItem("splash_milk_bottle", p -> p.stacksTo(1));

    @BasicItem
    @I18n(en_us = "Lingering Milk Bottle", zh_cn = "滞留型奶瓶", zh_tw = "滯留型奶瓶")
    public static final DeferredItem<Item> LINGERING_MILK_BOTTLE =
            simpleItem("lingering_milk_bottle", p -> p.stacksTo(1));

    @BasicItem
    @I18n(en_us = "Splash Honey Bottle", zh_cn = "喷溅型蜂蜜瓶", zh_tw = "噴濺型蜂蜜瓶")
    public static final DeferredItem<Item> SPLASH_HONEY_BOTTLE =
            simpleItem("splash_honey_bottle", p -> p.stacksTo(1));

    @BasicItem
    @I18n(en_us = "Lingering Honey Bottle", zh_cn = "滞留型蜂蜜瓶", zh_tw = "滞留型蜂蜜瓶")
    public static final DeferredItem<Item> LINGERING_HONEY_BOTTLE =
            simpleItem("lingering_honey_bottle", p -> p.stacksTo(1));

    @BasicItem
    @I18n(en_us = "Honey Bucket", zh_cn = "蜂蜜桶", zh_tw = "蜂蜜桶")
    public static final DeferredItem<Item> HONEY_BUCKET =
            simpleItem("honey_bucket", p -> p.stacksTo(1));

    @BasicItem
    @I18n(en_us = "Water Lily", zh_cn = "莲花", zh_tw = "蓮花")
    public static final DeferredItem<Item> WATER_LILY =
            register("water_lily",
                    p -> new PlaceOnWaterBlockItem(ModBlocks.WATER_LILY.get(), new Item.Properties()));
}
