package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.annotation.modelgen.item.BasicBlockLocatedItem;
import org.polaris2023.annotation.modelgen.item.BasicItem;
import org.polaris2023.annotation.modelgen.item.ParentItem;
import org.polaris2023.annotation.modelgen.item.SpawnEggItem;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.block.item.PresentBlockItem;
import org.polaris2023.wild_wind.common.block.item.TrappedPresentBlockItem;
import org.polaris2023.wild_wind.common.item.*;
import org.polaris2023.wild_wind.common.item.food.CheeseItem;
import org.polaris2023.wild_wind.common.item.food.NetherMushroomStewItem;
import org.polaris2023.wild_wind.common.item.modified.ModBannerItem;

import static org.polaris2023.wild_wind.util.registry.ModItemRegUtils.*;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WildWindMod.MOD_ID);
    private static final Item.Properties STACK_1 = new Item.Properties().stacksTo(1);
    private static final Item.Properties STACK_16 = new Item.Properties().stacksTo(16);

    //Base Item
    @BasicItem
    @I18n(en_us = "Apple Cake", zh_cn = "苹果派", zh_tw = "蘋果派")
    public static final DeferredItem<Item> APPLE_CAKE = normal("apple_cake");
    @BasicItem
    @I18n(en_us = "Berry Cake", zh_cn = "浆果派", zh_tw = "漿果派")
    public static final DeferredItem<Item> BERRY_CAKE = normal("berry_cake");
    @BasicItem
    @I18n(en_us = "candy", zh_cn = "糖果", zh_tw = "糖果")
    public static final DeferredItem<Item> CANDY = normal("candy", () -> STACK_16);
    @BasicItem
    @I18n(en_us = "Cheese Pumpkin soup", zh_cn = "奶酪南瓜汤", zh_tw = "起司南瓜湯")
    public static final DeferredItem<Item> CHEESE_PUMPKIN_SOUP = normal("cheese_pumpkin_soup", () -> STACK_1);
    @BasicItem
    @I18n(en_us = "Spider Egg", zh_cn = "蜘蛛卵", zh_tw = "蜘蛛卵")
    public static final DeferredItem<Item> SPIDER_EGG = ITEMS.register("spider_egg", () -> new BlockItem(ModBlocks.SPIDER_EGG.get(), STACK_1));
    @BasicItem
    @I18n(en_us = "Spider Mucosa", zh_cn = "蛛丝壁膜", zh_tw = "蛛絲壁膜")
    public static final DeferredItem<Item> SPIDER_MUCOSA = ITEMS.register("spider_mucosa",
            () -> new BlockItem(ModBlocks.SPIDER_MUCOSA.get(), new Item.Properties()));
    @BasicItem
    @I18n(en_us = "salt", zh_cn = "盐", zh_tw = "鹽")
    public static final DeferredItem<Item> SALT = normal("salt");
    @I18n(en_us = "Magic Wand Tool", zh_cn = "魔棒工具", zh_tw = "魔棒工具")
    @ParentItem(parent = "minecraft:item/stick")
    public static final DeferredItem<Item> MAGIC_WAND_TOOL = ITEMS.register("magic_wand_tool", () -> new MagicWandToolItem(STACK_1));
    @BasicItem
    @I18n(en_us = "Glow Powder", zh_cn = "萤光粉末", zh_tw = "螢光粉末")
    public static final DeferredItem<Item> GLOW_POWDER = ITEMS.register("glow_powder", () -> new GlowPowderItem(new Item.Properties()));
    @BasicItem
    @I18n(en_us = "Ash Dust", zh_cn = "灰烬粉末", zh_tw = "灰烬粉末")
    public static final DeferredItem<Item> ASH_DUST = ITEMS.register("ash_dust", () -> new AshDustItem(new Item.Properties()));
    @BasicItem(used = false)// don't run datagen by this
    @I18n(en_us = "Magic Flute", zh_cn = "魔笛", zh_tw = "魔笛")
    public static final DeferredItem<Item> MAGIC_FLUTE = ITEMS.register("magic_flute",
            () -> new MagicFluteItem(STACK_1.durability(100).rarity(Rarity.UNCOMMON)));

    //Food Item
    @BasicItem
    @I18n(en_us = "Living Tuber", zh_cn = "活根", zh_tw = "活根")
    public static final DeferredItem<Item> LIVING_TUBER = ITEMS.register("living_tuber",
            () -> new LivingTuberItem(STACK_16
                    .component(ModComponents.VEGETABLE_VALUE, 1F)
                    .component(ModComponents.MEAT_VALUE, 1F)
                    .component(ModComponents.MONSTER_VALUE, 1F)
                    .food(ModFoods.LIVING_TUBER.get())));
    @BasicItem
    @I18n(en_us = "Baked Living Tuber", zh_cn = "烤活根", zh_tw = "烤活根")
    public static final DeferredItem<Item> BAKED_LIVING_TUBER = ITEMS.register("baked_living_tuber",
            () -> new LivingTuberItem(STACK_16
                    .component(ModComponents.VEGETABLE_VALUE, 1F)
                    .component(ModComponents.MEAT_VALUE, 1F)
                    .component(ModComponents.MONSTER_VALUE, 1F)
                    .food(ModFoods.BAKED_LIVING_TUBER.get())));
    @BasicItem
    @I18n(en_us = "cheese", zh_cn = "奶酪", zh_tw = "起司")
    public static final DeferredItem<Item> CHEESE = ITEMS.register("cheese", () -> new CheeseItem(STACK_16.food(ModFoods.CHEESE.get())));
    @BasicItem
    @I18n(en_us = "Russian Soup", zh_cn = "罗宋汤", zh_tw = "羅宋湯")
    public static final DeferredItem<Item> RUSSIAN_SOUP = ITEMS.register("russian_soup", () -> new Item(STACK_1));
    @BasicItem
    @I18n(en_us = "Vegetable Soup", zh_cn = "蔬菜浓汤", zh_tw = "蔬菜濃湯")
    public static final DeferredItem<Item> VEGETABLE_SOUP = ITEMS.register("vegetable_soup", () -> new Item(STACK_1));
    @BasicItem
    @I18n(en_us = "Nether Mushroom Stew", zh_cn = "下界蘑菇煲", zh_tw = "下界蘑菇煲")
    public static final DeferredItem<Item> NETHER_MUSHROOM_STEW = ITEMS.register("nether_mushroom_stew",
            () -> new NetherMushroomStewItem(STACK_1, ModFoods.NETHER_MUSHROOM_STEW));
    @BasicItem
    @I18n(en_us = "Raw Trout", zh_cn = "生鳟鱼", zh_tw = "生鱒魚")
    public static final DeferredItem<Item> RAW_TROUT = food("raw_trout", () -> new Item.Properties()
            .component(ModComponents.MEAT_VALUE, 0.5F).component(ModComponents.FISH_VALUE, 1F));
    @BasicItem
    @I18n(en_us = "Cooked Trout", zh_cn = "熟鳟鱼", zh_tw = "熟鱒魚")
    public static final DeferredItem<Item> COOKED_TROUT = food("cooked_trout", () -> new Item.Properties()
            .component(ModComponents.MEAT_VALUE, 0.5F).component(ModComponents.FISH_VALUE, 1F));
    @BasicItem
    @I18n(en_us = "Raw Piranha", zh_cn = "生食人鱼", zh_tw = "生食人魚")
    public static final DeferredItem<Item> RAW_PIRANHA = normal("raw_piranha", () -> new Item.Properties()
            .component(ModComponents.MEAT_VALUE, 0.5F).component(ModComponents.FISH_VALUE, 1F).food(ModFoods.RAW_TROUT.get()));
    @BasicItem
    @I18n(en_us = "Cooked Piranha", zh_cn = "熟食人鱼", zh_tw = "熟食人魚")
    public static final DeferredItem<Item> COOKED_PIRANHA = normal("cooked_piranha", () -> new Item.Properties()
            .component(ModComponents.MEAT_VALUE, 0.5F).component(ModComponents.FISH_VALUE, 1F).food(ModFoods.COOKED_TROUT.get()));
    @BasicItem
    @I18n(en_us = "Baked Beetroot", zh_cn = "烤甜菜根", zh_tw = "烤甜菜根")
    public static final DeferredItem<Item> BAKED_BEETROOT = food("baked_beetroot",
            () -> new Item.Properties().component(ModComponents.VEGETABLE_VALUE, 1F));
    @BasicItem
    @I18n(en_us = "Baked carrot", zh_tw = "烤胡蘿蔔", zh_cn = "烤胡萝卜")
    public static final DeferredItem<Item> BAKED_CARROT = food("baked_carrot",
            () -> new Item.Properties().component(ModComponents.VEGETABLE_VALUE, 1F));
    @BasicItem
    @I18n(en_us = "Cooked Egg", zh_cn = "煎蛋", zh_tw = "煎蛋")
    public static final DeferredItem<Item> COOKED_EGG = food("cooked_egg",
            () -> new Item.Properties().component(ModComponents.PROTEIN_VALUE, 1F));
    @BasicItem
    @I18n(en_us = "Dough", zh_cn = "面团", zh_tw = "麵團")
    public static final DeferredItem<Item> DOUGH = food("dough");
    @BasicItem
    @I18n(en_us = "Fish Chowder", zh_cn = "海鲜杂烩", zh_tw = "海鮮雜燴")
    public static final DeferredItem<Item> FISH_CHOWDER = food("fish_chowder", () -> STACK_1);
    @BasicItem
    @I18n(en_us = "Pumpkin Slice", zh_cn = "南瓜片", zh_tw = "南瓜片")
    public static final DeferredItem<Item> PUMPKIN_SLICE = food("pumpkin_slice",
            () -> new Item.Properties().component(ModComponents.VEGETABLE_VALUE, 0.5F));
    @BasicItem
    @I18n(en_us = "Baked Pumpkin Slice", zh_cn = "烤南瓜片", zh_tw = "烤南瓜片")
    public static final DeferredItem<Item> BAKED_PUMPKIN_SLICE = food("baked_pumpkin_slice",
            () -> new Item.Properties().component(ModComponents.VEGETABLE_VALUE, 0.5F));
    @BasicItem
    @I18n(en_us = "Baked Apple", zh_cn = "烤苹果", zh_tw = "烤蘋果")
    public static final DeferredItem<Item> BAKED_APPLE = food("baked_apple",
            () -> new Item.Properties().component(ModComponents.FRUIT_VALUE, 1F));
    @BasicItem
    @I18n(en_us = "Baked Melon Slice", zh_cn = "烤西瓜片", zh_tw = "烤西瓜片")
    public static final DeferredItem<Item> BAKED_MELON_SLICE = food("baked_melon_slice",
            () -> new Item.Properties().component(ModComponents.FRUIT_VALUE, 0.5F));
    @BasicItem
    @I18n(en_us = "Flour", zh_cn = "面粉", zh_tw = "麵粉")
    public static final DeferredItem<Item> FLOUR = food("flour", Item.Properties::new);
    @BasicItem
    @I18n(en_us = "Baked Mushroom", zh_cn = "烤蘑菇", zh_tw = "烤蘑菇")
    public static final DeferredItem<Item> BAKED_MUSHROOM = food("baked_mushroom",
            () -> new Item.Properties().component(ModComponents.VEGETABLE_VALUE, 0.5F));
    @BasicItem
    @I18n(en_us = "Baked Seeds", zh_cn = "烤种子", zh_tw = "烤種子")
    public static final DeferredItem<Item> BAKED_SEEDS = food("baked_seeds", Item.Properties::new);
    @BasicItem
    @I18n(en_us = "Baked Berries", zh_cn = "烤浆果", zh_tw = "烤莓醬")
    public static final DeferredItem<Item> BAKED_BERRIES = food("baked_berries",
            () -> new Item.Properties().component(ModComponents.FRUIT_VALUE, 0.5F));
    @BasicItem
    @I18n(en_us = "Raw Frog Leg", zh_cn = "生蛙腿", zh_tw = "生蛙腿")
    public static final DeferredItem<Item> RAW_FROG_LEG = food("raw_frog_leg", () -> new Item.Properties()
            .component(ModComponents.MEAT_VALUE, 0.5F).component(ModComponents.MONSTER_VALUE, 1F));
    @BasicItem
    @I18n(en_us = "Cooked Frog Leg", zh_cn = "烤蛙腿", zh_tw = "烤蛙腿")
    public static final DeferredItem<Item> COOKED_FROG_LEG = food("cooked_frog_leg", () -> new Item.Properties()
            .component(ModComponents.MEAT_VALUE, 0.5F).component(ModComponents.FISH_VALUE, 1F));
    @BasicItem
    @I18n(en_us = "Charred Cuisine", zh_cn = "焦糊料理", zh_tw = "焦糊料理")
    public static final DeferredItem<Item> CHARRED_CUISINE = food("charred_cuisine");
    @BasicItem
    @I18n(en_us = "Failed Cuisine", zh_cn = "失败料理", zh_tw = "失败料理")
    public static final DeferredItem<Item> FAILED_CUISINE = food("failed_cuisine");

    //Mob Bucket Item
    @BasicItem
    @I18n(en_us = "Trout Bucket", zh_cn = "鳟鱼桶", zh_tw = "鱒魚桶")
    public static final DeferredItem<Item> TROUT_BUCKET = mobBucket("trout", ModEntityTypes.TROUT);
    @BasicItem
    @I18n(en_us = "Piranha Bucket", zh_cn = "食人鱼桶", zh_tw = "食人魚桶")
    public static final DeferredItem<Item> PIRANHA_BUCKET = mobBucket("piranha", ModEntityTypes.TROUT);

    //Spawn Egg Item
    @SpawnEggItem
    @I18n(en_us = "Firefly Spawn Egg", zh_cn = "萤火虫刷怪蛋", zh_tw = "螢火蟲生怪蛋")
    public static final DeferredItem<Item> FIREFLY_SPAWN_EGG = spawnEgg("firefly", ModEntityTypes.FIREFLY, 0x0A233F, 0xAA8F74);
    @SpawnEggItem
    @I18n(en_us = "Glare Spawn Egg", zh_cn = "怒目怪刷怪蛋", zh_tw = "怒目靈生怪蛋")
    public static final DeferredItem<Item> GLARE_SPAWN_EGG = spawnEgg("glare", ModEntityTypes.GLARE, 0x49601B, 0x10160A);
    @SpawnEggItem
    @I18n(en_us = "Glare Spawn Egg", zh_cn = "鳟鱼刷怪蛋", zh_tw = "鱒魚生怪蛋")
    public static final DeferredItem<Item> TROUT_SPAWN_EGG = spawnEgg("trout", ModEntityTypes.TROUT, 0x8290a5, 0x6b9f93);
    @SpawnEggItem
    @I18n(en_us = "Piranha Spawn Egg", zh_cn = "食人鱼刷怪蛋", zh_tw = "食人魚生怪蛋")
    public static final DeferredItem<Item> PIRANHA_SPAWN_EGG = spawnEgg("piranha", ModEntityTypes.PIRANHA, 0x564943, 0x82200B);

    //Block Item
    @BasicItem
    public static final DeferredItem<BlockItem> GLOW_MUCUS = ITEMS.registerSimpleBlockItem("glow_mucus", ModBlocks.GLOW_MUCUS);
    @BasicItem
    public static final DeferredItem<BlockItem> GLAREFLOWER = ITEMS.registerSimpleBlockItem("glareflower", ModBlocks.GLAREFLOWER);
    @BasicItem
    public static final DeferredItem<Item> GLAREFLOWER_SEEDS = ITEMS.register("glareflower_seeds",
            () -> new BlockItem(ModBlocks.GLAREFLOWER_SEEDS.get(), new Item.Properties().food(
                    (new FoodProperties.Builder().nutrition(1).saturationModifier(0.3F)).build())));
    @BasicItem
    public static final DeferredItem<BlockItem> REEDS = ITEMS.registerSimpleBlockItem("reeds", ModBlocks.REEDS);
    @BasicItem
    public static final DeferredItem<BlockItem> CATTAILS = ITEMS.registerSimpleBlockItem("cattails", ModBlocks.CATTAILS);
    public static final DeferredItem<PresentBlockItem> PRESENT = ITEMS.register("present",
            () -> new PresentBlockItem(ModBlocks.PRESENT.get(), new Item.Properties()));
    public static final DeferredItem<TrappedPresentBlockItem> TRAPPED_PRESENT = ITEMS.register("trapped_present",
            () -> new TrappedPresentBlockItem(ModBlocks.TRAPPED_PRESENT.get(), new Item.Properties()));
    public static final DeferredItem<Item> BANNER = ITEMS.register("banner",
            () -> new ModBannerItem(ModBlocks.BANNER.get(), ModBlocks.WALL_BANNER.get(), STACK_16
                    .component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)));
    @BasicBlockLocatedItem
    public static final DeferredItem<BlockItem> TINY_CACTUS = ITEMS.registerSimpleBlockItem("tiny_cactus", ModBlocks.TINY_CACTUS);
    @BasicItem
    public static final DeferredItem<BlockItem> DUCKWEED = ITEMS.registerSimpleBlockItem("duckweed", ModBlocks.DUCKWEED);
    @ParentItem(parent = "wild_wind:block/ash_1")
    public static final DeferredItem<BlockItem> ASH = ITEMS.registerSimpleBlockItem("ash", ModBlocks.ASH);

    public static final DeferredItem<SignItem> AZALEA_SIGN = sign("azalea_sign", ModBlocks.AZALEA_SIGN, ModBlocks.AZALEA_WALL_SIGN);
    public static final DeferredItem<HangingSignItem> AZALEA_HANGING_SIGN = hangingSign("azalea_hanging_sign", ModBlocks.AZALEA_HANGING_SIGN, ModBlocks.AZALEA_WALL_HANGING_SIGN);

    public static final DeferredItem<SignItem> BAOBAB_SIGN = sign("baobab_sign", ModBlocks.BAOBAB_SIGN, ModBlocks.BAOBAB_WALL_SIGN);
    public static final DeferredItem<HangingSignItem> BAOBAB_HANGING_SIGN = hangingSign("baobab_hanging_sign", ModBlocks.BAOBAB_HANGING_SIGN, ModBlocks.BAOBAB_WALL_HANGING_SIGN);

    @BasicBlockLocatedItem
    public static final DeferredItem<BlockItem> BAOBAB_SAPLING = ITEMS.registerSimpleBlockItem("baobab_sapling", ModBlocks.BAOBAB_SAPLING);

    @BasicBlockLocatedItem
    public static final DeferredItem<BlockItem> PALM_SAPLING = ITEMS.registerSimpleBlockItem("palm_sapling", ModBlocks.PALM_SAPLING);

    public static final DeferredItem<SignItem> PALM_SIGN = sign("palm_sign", ModBlocks.PALM_SIGN, ModBlocks.PALM_WALL_SIGN);
    public static final DeferredItem<HangingSignItem> PALM_HANGING_SIGN = hangingSign("palm_hanging_sign", ModBlocks.PALM_HANGING_SIGN, ModBlocks.PALM_WALL_HANGING_SIGN);

    //Boat
    @BasicItem
    @I18n(en_us = "Azalea Boat", zh_cn = "杜鹃木船", zh_tw = "杜鵑木船")
    public static final DeferredItem<BoatItem> AZALEA_BOAT = boat("azalea_boat", false, ModEnumExtensions.WILD_WIND_AZALEA);
    @BasicItem
    @I18n(en_us = "Azalea Chest Boat", zh_cn = "杜鹃木运输船", zh_tw = "杜鵑木運輸船")
    public static final DeferredItem<BoatItem> AZALEA_CHEST_BOAT = boat("azalea_chest_boat", true, ModEnumExtensions.WILD_WIND_AZALEA);
    @BasicItem
    @I18n(en_us = "Palm Boat", zh_cn = "棕榈木船", zh_tw = "棕櫚木船")
    public static final DeferredItem<BoatItem> PALM_BOAT = boat("palm_boat", false, ModEnumExtensions.WILD_WIND_PALM);
    @BasicItem
    @I18n(en_us = "Palm Chest Boat", zh_cn = "棕榈木运输船", zh_tw = "棕櫚木運輸船")
    public static final DeferredItem<BoatItem> PALM_CHEST_BOAT = boat("palm_chest_boat", true, ModEnumExtensions.WILD_WIND_PALM);
    @BasicItem
    @I18n(en_us = "Baobab Boat", zh_cn = "猴面包木船", zh_tw = "猴麵包木船")
    public static final DeferredItem<BoatItem> BAOBAB_BOAT = boat("baobab_boat", false, ModEnumExtensions.WILD_WIND_BAOBAB);
    @BasicItem
    @I18n(en_us = "Baobab Chest Boat", zh_cn = "猴面包木运输船", zh_tw = "猴麵包木運輸船")
    public static final DeferredItem<BoatItem> BAOBAB_CHEST_BOAT = boat("baobab_chest_boat", true, ModEnumExtensions.WILD_WIND_BAOBAB);

}