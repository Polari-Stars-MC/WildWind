package org.polaris2023.wild_wind.common.init.items.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.annotation.modelgen.item.SpawnEggItem;
import org.polaris2023.wild_wind.common.init.ModEntities;

import java.util.Locale;
import java.util.function.Supplier;

import static org.polaris2023.wild_wind.util.interfaces.registry.ItemRegistry.register;

/**
 * @author : baka4n
 * {@code @Date : 2025/02/12 19:56:04}
 */
public enum ModSpawnEggs implements Supplier<DeferredSpawnEggItem>, ItemLike {
    @SpawnEggItem
    @I18n(en_us = "Firefly Spawn Egg", zh_cn = "萤火虫刷怪蛋", zh_tw = "螢火蟲生怪蛋")
    FIREFLY_SPAWN_EGG(ModEntities.FIREFLY, 0x0A233F, 0xAA8F74),
    @SpawnEggItem
    @I18n(en_us = "Glare Spawn Egg", zh_cn = "怒目怪刷怪蛋", zh_tw = "怒目靈生怪蛋")
    GLARE_SPAWN_EGG(ModEntities.GLARE, 0x49601B, 0x10160A),
    @SpawnEggItem
    @I18n(en_us = "Glare Spawn Egg", zh_cn = "鳟鱼刷怪蛋", zh_tw = "鱒魚生怪蛋")
    TROUT_SPAWN_EGG(ModEntities.TROUT, 0x8290a5, 0x6b9f93),
    @SpawnEggItem
    @I18n(en_us = "Piranha Spawn Egg", zh_cn = "食人鱼刷怪蛋", zh_tw = "食人魚生怪蛋")
    PIRANHA_SPAWN_EGG(ModEntities.PIRANHA, 0x564943, 0x82200B),
    ;

    public final DeferredItem<DeferredSpawnEggItem> entry;
    <E extends Mob> ModSpawnEggs(DeferredHolder<EntityType<?>, EntityType<E>> type, int backgroundColor, int highlightColor) {
        entry = register(name().toLowerCase(Locale.ROOT), type, backgroundColor, highlightColor);
    }



    @Override
    public DeferredSpawnEggItem get() {
        return entry.get();
    }

    @Override
    public Item asItem() {
        return entry.asItem();
    }
}
