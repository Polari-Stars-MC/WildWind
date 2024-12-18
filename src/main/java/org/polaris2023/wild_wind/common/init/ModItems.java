package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.annotation.modelgen.BasicItem;
import org.polaris2023.annotation.modelgen.SpawnEggItem;
import org.polaris2023.wild_wind.common.item.LivingTuberItem;
import org.polaris2023.wild_wind.common.item.food.NetherMushroomStewItem;
import org.polaris2023.wild_wind.common.item.MagicFluteItem;

import java.util.List;

import static org.polaris2023.wild_wind.common.init.ModInitializer.*;


public class ModItems {
    @BasicItem
    @I18n(en_us = "Glow Powder", zh_cn = "萤光粉末", zh_tw = "螢光粉末")
    public static final DeferredItem<Item> GLOW_POWDER = simpleItem("glow_powder");
    @BasicItem
    @I18n(en_us = "Living Tuber", zh_cn = "活根", zh_tw = "活根")
    public static final DeferredItem<LivingTuberItem> LIVING_TUBER =
            register("living_tuber", properties -> new LivingTuberItem(properties
                    .stacksTo(16)
                    .component(ModComponents.VEGETABLE_VALUE, 1F)
                    .component(ModComponents.MEAT_VALUE, 1F)
                    .component(ModComponents.MONSTER_VALUE, 1F)
                    .food(ModFoods.LIVING_TUBER.get())));
    @BasicItem
    @I18n(en_us = "Baked Living Tuber", zh_cn = "烤活根", zh_tw = "烤活根")
    public static final DeferredItem<Item> BAKED_LIVING_TUBER =
            simpleItem(
                    "baked_living_tuber", p -> p
                            .stacksTo(16)
                            .component(ModComponents.VEGETABLE_VALUE, 1F)
                            .component(ModComponents.MEAT_VALUE, 1F)
                            .component(ModComponents.MONSTER_VALUE, 1F),
                    ModFoods.COOKED_LIVING_TUBER);
    @BasicItem
    @I18n(en_us = "Raw Trout", zh_cn = "生鳟鱼", zh_tw = "生鱒魚")
    public static final DeferredItem<Item> RAW_TROUT =
            simpleItem("raw_trout", p -> p
                            .component(ModComponents.MEAT_VALUE, 0.5F)
                            .component(ModComponents.FISH_VALUE, 1F),
                    ModFoods.RAW_TROUT);
    @BasicItem
    @I18n(en_us = "Cooked Trout", zh_cn = "烤鳟鱼", zh_tw = "烤鱒魚")
    public static final DeferredItem<Item> COOKED_TROUT =
            simpleItem("cooked_trout", p -> p
                            .component(ModComponents.MEAT_VALUE, 0.5F)
                            .component(ModComponents.FISH_VALUE, 1F),
                    ModFoods.COOKED_TROUT);

    @BasicItem
    @I18n(en_us = "Magic Flute", zh_cn = "魔笛", zh_tw = "魔笛")
    public static final DeferredItem<MagicFluteItem> MAGIC_FLUTE =
            register("magic_flute", MagicFluteItem::stackTo1);

    @BasicItem
    @I18n(en_us = "Apple Cake", zh_cn = "苹果派", zh_tw = "蘋果派")
    public static final DeferredItem<Item> APPLE_CAKE =
            simpleItem("apple_cake");

    @BasicItem
    @I18n(en_us = "Berry Cake", zh_cn = "浆果派", zh_tw = "漿果派")
    public static final DeferredItem<Item> BERRY_CAKE =
            simpleItem("berry_cake");

    @BasicItem
    @I18n(en_us = "Candy", zh_cn = "糖果", zh_tw = "糖果")
    public static final DeferredItem<Item> CANDY =
            simpleItem("candy", p -> p.stacksTo(16));

    @BasicItem
    @I18n(en_us = "Cheese", zh_tw = "起司", zh_cn = "奶酪")
    public static final DeferredItem<Item> CHEESE =
            item("cheese", p -> new Item(p.stacksTo(16).food(ModFoods.CHEESE.get())) {
                @Override
                public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
                    super.finishUsingItem(itemStack, level, entity);
                    List<MobEffectInstance> activeEffects = List.copyOf(entity.getActiveEffects());
                    activeEffects.stream().filter(mobEffectInstance -> !mobEffectInstance.getEffect().value().isBeneficial())
                            .forEach(mobEffectInstance -> entity.removeEffect(mobEffectInstance.getEffect()));
                    return itemStack;
                }
            });

    @BasicItem
    @I18n(en_us = "Cheese Pumpkin soup", zh_cn = "奶酪南瓜汤", zh_tw = "起司南瓜湯")
    public static final DeferredItem<Item> CHEESE_PUMPKIN_SOUP =
            simpleItem("cheese_pumpkin_soup", p -> p.stacksTo(1));

    @BasicItem
    @I18n(en_us = "Baked Beetroot", zh_cn = "烤甜菜根", zh_tw = "烤甜菜根")
    public static final DeferredItem<Item> BAKED_BEETROOT =
            simpleItem("baked_beetroot", p -> p
                            .component(ModComponents.VEGETABLE_VALUE, 1F),
                    ModFoods.BAKED_BEETROOT);

    @BasicItem
    @I18n(en_us = "Baked carrot", zh_tw = "烤胡蘿蔔", zh_cn = "烤胡萝卜")
    public static final DeferredItem<Item> BAKED_CARROT =
            simpleItem("baked_carrot", p -> p
                            .component(ModComponents.VEGETABLE_VALUE, 1F),
                    ModFoods.BAKED_CARROT);

    @BasicItem
    @I18n(en_us = "Cooked Egg", zh_cn = "煎蛋", zh_tw = "煎蛋")
    public static final DeferredItem<Item> COOKED_EGG =
            simpleItem("cooked_egg", p -> p
                            .component(ModComponents.PROTEIN_VALUE, 1F),
                    ModFoods.COOKED_EGG);

    @BasicItem
    @I18n(en_us = "Dough", zh_cn = "面团", zh_tw = "麵團")
    public static final DeferredItem<Item> DOUGH = simpleItem("dough", ModFoods.DOUGH);

    @BasicItem
    @I18n(en_us = "Fish Chowder", zh_cn = "海鲜杂烩", zh_tw = "海鮮雜燴")
    public static final DeferredItem<Item> FISH_CHOWDER =
            simpleItem("fish_chowder", p -> p
                            .stacksTo(1),
                    ModFoods.FISH_CHOWDER);

    @BasicItem
    @I18n(en_us = "Trout Bucket", zh_cn = "鳟鱼桶", zh_tw = "鱒魚桶")
    public static final DeferredItem<MobBucketItem> TROUT_BUCKET =
            register("trout_bucket", () -> new MobBucketItem(
                    ModEntities.TROUT.get(),
                    Fluids.WATER,
                    SoundEvents.BUCKET_EMPTY_FISH,
                    new Item.Properties()
                            .stacksTo(1)
                            .component(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY)));

    @BasicItem
    @I18n(en_us = "Russian Soup", zh_cn = "罗宋汤", zh_tw = "羅宋湯")
    public static final DeferredItem<Item> RUSSIAN_SOUP =
            simpleItem("russian_soup", p -> p.stacksTo(1));

    @BasicItem
    @I18n(en_us = "Pumpkin Slice", zh_cn = "南瓜片", zh_tw = "南瓜片")
    public static final DeferredItem<Item> PUMPKIN_SLICE =
            simpleItem("pumpkin_slice", p -> p
                            .component(ModComponents.VEGETABLE_VALUE, 0.5F),
                    ModFoods.PUMPKIN_SLICE);

    @BasicItem
    @I18n(en_us = "Baked Pumpkin Slice", zh_cn = "南瓜片", zh_tw = "南瓜片")
    public static final DeferredItem<Item> BAKED_PUMPKIN_SLICE =
            simpleItem("baked_pumpkin_slice", p -> p
                            .component(ModComponents.VEGETABLE_VALUE, 0.5F),
                    ModFoods.BAKED_PUMPKIN_SLICE);

    @BasicItem
    @I18n(en_us = "Baked Apple", zh_cn = "烤苹果", zh_tw = "烤蘋果")
    public static final DeferredItem<Item> BAKED_APPLE =
            simpleItem("baked_apple", p -> p
                            .component(ModComponents.FRUIT_VALUE, 1F),
                    ModFoods.BAKED_APPLE);
    @BasicItem
    @I18n(en_us = "Baked Melon Slice", zh_cn = "烤西瓜片", zh_tw = "烤西瓜片")
    public static final DeferredItem<Item> BAKED_MELON_SLICE =
            simpleItem("baked_melon_slice", p -> p
                            .component(ModComponents.FRUIT_VALUE, 0.5F),
                    ModFoods.BAKED_MELON_SLICE);

    @BasicItem
    @I18n(en_us = "Vegetable Soup", zh_cn = "蔬菜浓汤", zh_tw = "蔬菜濃湯")
    public static final DeferredItem<Item> VEGETABLE_SOUP =
            simpleItem("vegetable_soup", p -> p.stacksTo(1));
    @BasicItem
    @I18n(en_us = "Flour", zh_cn = "面粉", zh_tw = "麵粉")
    public static final DeferredItem<Item> FLOUR = simpleItem("flour", ModFoods.FLOUR);

    @BasicItem
    @I18n(en_us = "Spider Egg", zh_cn = "蜘蛛卵", zh_tw = "蜘蛛卵")
    public static final DeferredItem<Item> SPIDER_EGG =
            simpleItem("spider_egg", p -> p.stacksTo(1));

    @BasicItem
    @I18n(en_us = "Spider Mucosa", zh_cn = "蛛丝壁膜", zh_tw = "蛛絲壁膜")
    public static final DeferredItem<Item> SPIDER_MUCOSA = simpleItem("spider_mucosa");

    @BasicItem
    @I18n(en_us = "Nether Mushroom Stew", zh_cn = "下界蘑菇煲", zh_tw = "下界蘑菇煲")
    public static final DeferredItem<NetherMushroomStewItem> NETHER_MUSHROOM_STEW =
            register("nether_mushroom_stew", properties -> 
                    new NetherMushroomStewItem(properties.stacksTo(1), ModFoods.NETHER_MUSHROOM_STEW));

    @BasicItem
    @I18n(en_us = "Baked Mushroom", zh_cn = "烤蘑菇", zh_tw = "烤蘑菇")
    public static final DeferredItem<Item> BAKED_MUSHROOM =
            simpleItem("baked_mushroom", p -> p
                            .component(ModComponents.VEGETABLE_VALUE, 0.5F),
                    ModFoods.BAKED_MUSHROOM);

    @BasicItem
    @I18n(en_us = "Baked Seeds", zh_cn = "烤种子", zh_tw = "烤種子")
    public static final DeferredItem<Item> BAKED_SEEDS =
            simpleItem("baked_seeds", ModFoods.BAKED_SEEDS);

    @BasicItem
    @I18n(en_us = "Baked Berries", zh_cn = "烤浆果", zh_tw = "烤莓醬")
    public static final DeferredItem<Item> BAKED_BERRIES =
            simpleItem("baked_berries", p -> p
                    .component(ModComponents.FRUIT_VALUE, 0.5F),
            ModFoods.BAKED_BERRIES);


    @SpawnEggItem
    @I18n(en_us = "Firefly Spawn Egg", zh_cn = "萤火虫刷怪蛋", zh_tw = "螢火蟲生怪蛋")
    public static final DeferredItem<DeferredSpawnEggItem> FIREFLY_SPAWN_EGG =
            register("firefly_spawn_egg",
                    ModEntities.FIREFLY,
                    0x0A233F,
                            0xAA8F74);


    @SpawnEggItem
    @I18n(en_us = "Glare Spawn Egg", zh_cn = "怒目怪刷怪蛋", zh_tw = "怒目靈生怪蛋")
    public static final DeferredItem<DeferredSpawnEggItem> GLARE_SPAWN_EGG =
            register("glare_spawn_egg",
                    ModEntities.GLARE,
                    0x49601B,
                    0x10160A);
    @SpawnEggItem
    @I18n(en_us = "Glare Spawn Egg", zh_cn = "鳟鱼刷怪蛋", zh_tw = "鱒魚生怪蛋")
    public static final DeferredItem<DeferredSpawnEggItem> TROUT_SPAWN_EGG =
            register("trout_spawn_egg",
                    ModEntities.TROUT,
                    0x8290a5,
                    0x6b9f93
                    );
    @BasicItem
    @I18n(en_us = "Raw Frog Leg", zh_cn = "生蛙腿", zh_tw = "生蛙腿")
    public static final DeferredItem<Item> RAW_FROG_LEG =
            simpleItem("raw_frog_leg", p -> p
                            .component(ModComponents.MEAT_VALUE, 0.5F)
                            .component(ModComponents.MONSTER_VALUE, 1F),
                    ModFoods.RAW_FROG_LEG);

    @BasicItem
    @I18n(en_us = "Cooked Frog Leg", zh_cn = "烤蛙腿", zh_tw = "烤蛙腿")
    public static final DeferredItem<Item> COOKED_FROG_LEG =
            simpleItem("cooked_frog_leg", p -> p
                            .component(ModComponents.MEAT_VALUE, 0.5F)
                            .component(ModComponents.MONSTER_VALUE, 1F),
                    ModFoods.COOKED_FROG_LEG);

    @BasicItem
    @I18n(en_us = "salt", zh_cn = "盐", zh_tw = "鹽")
    public static final DeferredItem<Item> SALT =
            simpleItem("salt", p -> p.stacksTo(16), ModFoods.SALT);


}
