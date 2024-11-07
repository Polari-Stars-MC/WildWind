package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.FoodOnAStickItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.entity.Trout;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModItems {

    static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(WildWindMod.MOD_ID);

    @I18n(en_us = "Glow Powder", zh_cn = "萤光粉末", zh_tw = "螢光粉末")
    public static final DeferredItem<Item> GLOW_POWDER = register("glow_powder");
    @I18n(en_us = "Living Tuber", zh_cn = "活根", zh_tw = "活根")
    public static final DeferredItem<Item> LIVING_TUBER = register("living_tuber");

    @I18n(en_us = "Raw Trout", zh_cn = "生鳟鱼", zh_tw = "生鱒魚")
    public static final DeferredItem<Item> RAW_TROUT = register("raw_trout");
    @I18n(en_us = "Cooked Trout", zh_cn = "熟鳟鱼", zh_tw = "熟鱒魚")
    public static final DeferredItem<Item> COOKED_TROUT = register("cooked_trout");

    @I18n(en_us = "Trout Bucket", zh_cn = "鳟鱼桶", zh_tw = "鱒魚桶")
    public static final DeferredItem<MobBucketItem> TROUT_BUCKET = register("trout_bucket", () -> new MobBucketItem(ModEntities.TROUT.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, new Item.Properties().stacksTo(1).component(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY)));


    @I18n(en_us = "Firefly Spawn Egg", zh_cn = "萤火虫刷怪蛋", zh_tw = "螢火蟲生怪蛋")
    public static final DeferredItem<DeferredSpawnEggItem> FIREFLY_SPAWN_EGG =
            register("firefly_spawn_egg",
                    ModEntities.FIREFLY,
                    0x0A233F,
                            0xAA8F74);

    @I18n(en_us = "Glare Spawn Egg", zh_cn = "怒目怪刷怪蛋", zh_tw = "怒目靈生怪蛋")
    public static final DeferredItem<DeferredSpawnEggItem> GLARE_SPAWN_EGG =
            register("glare_spawn_egg",
                    ModEntities.GLARE,
                    0x49601B,
                    0x10160A);
    @I18n(en_us = "Glare Spawn Egg", zh_cn = "鳟鱼刷怪蛋", zh_tw = "鱒魚生怪蛋")
    public static final DeferredItem<DeferredSpawnEggItem> TROUT_SPAWN_EGG =
            register("trout_spawn_egg",
                    ModEntities.TROUT,
                    0x8290a5,
                    0x6b9f93
                    );

    private static DeferredItem<Item> register(String name) {
        return ITEMS.registerSimpleItem(name);
    }

    private static DeferredItem<DeferredSpawnEggItem> register(String name,
                                                               Supplier<? extends EntityType<? extends Mob>> type,
                                                               int backgroundColor,
                                                               int highlightColor,
                                                               Consumer<Item.Properties> consumer) {
        return ITEMS.registerItem(name, properties -> {
            consumer.accept(properties);
            return new DeferredSpawnEggItem(type, backgroundColor, highlightColor, properties);
        });
    }

    private static DeferredItem<DeferredSpawnEggItem> register(String name,
                                                               Supplier<? extends EntityType<? extends Mob>> type,
                                                               int backgroundColor,
                                                               int highlightColor) {
        return register(name, type, backgroundColor, highlightColor, properties -> {});
    }

    private static <T extends Item> DeferredItem<T> register(String name, Supplier<T> item) {
        return ITEMS.register(name, item);
    }

    public static Collection<DeferredHolder<Item, ? extends Item>> entry() {
        return ITEMS.getEntries();
    }
}
