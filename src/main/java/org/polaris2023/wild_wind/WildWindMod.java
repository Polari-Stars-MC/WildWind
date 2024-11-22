package org.polaris2023.wild_wind;

import io.github.tt432.eyelib.event.InitComponentEvent;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.TypedDataComponent;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.polaris2023.wild_wind.common.WildWindEventHandler;
import org.polaris2023.wild_wind.common.init.ModComponents;
import org.polaris2023.wild_wind.common.init.ModFoods;
import org.polaris2023.wild_wind.util.interfaces.IConfig;

import java.util.ServiceLoader;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Mod(WildWindMod.MOD_ID)
public class WildWindMod {

    public static final String MOD_ID = "wild_wind";
    public static final String MOD_NAME = "Wild Wind";
    public static final String MOD_VERSION = ModList.get().getModFileById(MOD_ID).versionString();
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public WildWindMod(IEventBus modEventBus, ModContainer modContainer) {
        WildWindEventHandler.modConstruction(modEventBus);
        NeoForge.EVENT_BUS.addListener((InitComponentEvent event) -> {
            food(Items.EGG, ModFoods.EGG);
            food(Items.TURTLE_EGG, ModFoods.EGG);
            food(Items.BROWN_MUSHROOM, ModFoods.BROWN_MUSHROOM);
            food(Items.RED_MUSHROOM, ModFoods.RED_MUSHROOM);
            food(Items.CRIMSON_FUNGUS, ModFoods.CRIMSON_FUNGUS);
            food(Items.WARPED_FUNGUS, ModFoods.WARPED_FUNGUS);
            food(Items.BEETROOT_SEEDS, ModFoods.SEEDS);
            food(Items.MELON_SEEDS, ModFoods.SEEDS);
            food(Items.PUMPKIN_SEEDS, ModFoods.SEEDS);
            food(Items.WHEAT_SEEDS, ModFoods.SEEDS);
            food(Items.TORCHFLOWER_SEEDS, ModFoods.POISON_SEEDS);
            food(Items.PITCHER_POD, ModFoods.POISON_SEEDS);
            food(Items.SUGAR_CANE, ModFoods.SUGAR_CANE);
            food(Items.SUGAR, ModFoods.SUGAR);
            food(Items.FERMENTED_SPIDER_EYE, ModFoods.FERMENTED_SPIDER_EYE);
            food(Items.GLISTERING_MELON_SLICE, ModFoods.GLISTERING_MELON_SLICE);
            food(Items.MILK_BUCKET, ModFoods.MILK);
            component(Items.SLIME_BALL, ModComponents.SLIME_COLOR, 0);

        });

        for (var iConfig : ServiceLoader.load(IConfig.class))
            iConfig.register(modContainer);
    }

    private static void food(Item item, Supplier<FoodProperties> food) {
        component(item, builder -> {
            builder.set(DataComponents.FOOD, food.get());
        });
    }

    private static <T> void component(Item item, Supplier<DataComponentType<T>> type, T t) {
        component(item, builder -> {
            builder.set(type, t);
        });
    }

    private static <T> void component(Item item, Consumer<DataComponentMap.Builder> consumer) {
        DataComponentMap.Builder builder = DataComponentMap.builder();
        builder.addAll(item.components);
        consumer.accept(builder);
        item.components = builder.build();
    }
}
