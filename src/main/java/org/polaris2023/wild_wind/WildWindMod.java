package org.polaris2023.wild_wind;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.polaris2023.wild_wind.common.init.*;
import org.polaris2023.wild_wind.util.interfaces.IConfig;

import java.util.ServiceLoader;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Mod(WildWindMod.MOD_ID)
public class WildWindMod {

    public static final String MOD_ID = "wild_wind";
    public static final String MOD_VERSION = ModList.get().getModFileById(MOD_ID).versionString();

    public WildWindMod(IEventBus modEventBus, ModContainer modContainer) {
        ModInitializer.init(modEventBus);
        ModPotions.register(modEventBus);
        ModVanillaCompat.register(NeoForge.EVENT_BUS);
        ModAttachmentTypes.ATTACHMENT_TYPES.register(modEventBus);
        modEventBus.addListener((FMLCommonSetupEvent event) -> event.enqueueWork(() -> {
            food(Items.EGG, ModFoods.EGG);
            food(Items.TURTLE_EGG, ModFoods.EGG);
            food(Items.SNIFFER_EGG, ModFoods.SNIFFER_EGG);
            food(Items.DRAGON_EGG, ModFoods.DRAGON_EGG);
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
            food(Items.KELP, ModFoods.KELP);
            food(Items.BEEF, ModFoods.RAW_BEEF);
            food(Items.MUTTON, ModFoods.RAW_MUTTON);
            food(Items.PORKCHOP, ModFoods.RAW_PORKCHOP);
            food(Items.RABBIT, ModFoods.RAW_RABBIT);
            food(Items.RABBIT_FOOT, ModFoods.RABBIT_FOOT);
            food(Items.COD, ModFoods.RAW_COD);
            food(Items.SALMON, ModFoods.RAW_SALMON);
            food(Items.TROPICAL_FISH, ModFoods.RAW_TROPICAL_FISH);
            food(Items.POPPED_CHORUS_FRUIT, ModFoods.POPPED_CHORUS_FRUIT);
            component(ModComponents.VEGETABLE_VALUE, 0.5F,
                    Items.KELP, Items.DRIED_KELP,
                    Items.BROWN_MUSHROOM, Items.RED_MUSHROOM,
                    Items.CRIMSON_FUNGUS, Items.WARPED_FUNGUS
            );
            component(ModComponents.VEGETABLE_VALUE, 1F,
                    Items.CARROT, Items.GOLDEN_CARROT,
                    Items.BEETROOT, Items.POTATO,
                    Items.BAKED_POTATO, Items.POISONOUS_POTATO
            );

            component(ModComponents.MEAT_VALUE, 1F,
                    Items.BEEF, Items.COOKED_BEEF,
                    Items.PORKCHOP, Items.COOKED_PORKCHOP
            );
            component(ModComponents.MEAT_VALUE, 0.5F,
                    Items.MUTTON, Items.COOKED_MUTTON,
                    Items.CHICKEN, Items.COOKED_CHICKEN,
                    Items.RABBIT, Items.RABBIT_FOOT,
                    Items.COOKED_RABBIT, Items.ROTTEN_FLESH,
                    Items.SPIDER_EYE, Items.FERMENTED_SPIDER_EYE,
                    Items.COD, Items.COOKED_COD,
                    Items.SALMON, Items.COOKED_SALMON,
                    Items.TROPICAL_FISH, Items.PUFFERFISH
            );
            component(ModComponents.FISH_VALUE, 1F,
                    Items.COD, Items.COOKED_COD,
                    Items.SALMON, Items.COOKED_SALMON,
                    Items.TROPICAL_FISH, Items.PUFFERFISH
            );
            component(ModComponents.MONSTER_VALUE, 1F,
                    Items.POISONOUS_POTATO, Items.RABBIT_FOOT,
                    Items.ROTTEN_FLESH, Items.SPIDER_EYE,
                    Items.FERMENTED_SPIDER_EYE, Items.TROPICAL_FISH,
                    Items.PUFFERFISH
            );
            component(ModComponents.FRUIT_VALUE, 1F,
                    Items.APPLE, Items.GOLDEN_APPLE,
                    Items.ENCHANTED_GOLDEN_APPLE, Items.CHORUS_FRUIT,
                    Items.POPPED_CHORUS_FRUIT, Items.SUGAR_CANE
            );
            component(ModComponents.FRUIT_VALUE, 0.5F,
                    Items.MELON_SLICE, Items.GLISTERING_MELON_SLICE,
                    Items.SWEET_BERRIES, Items.GLOW_BERRIES
            );
            component(ModComponents.PROTEIN_VALUE, 1F,
                    Items.EGG, Items.TURTLE_EGG,
                    Items.SNIFFER_EGG, Items.DRAGON_EGG
            );
            component(ModComponents.SWEET_VALUE, 1F,
                    Items.SUGAR, Items.HONEY_BOTTLE
            );

            component(Items.SLIME_BALL, ModComponents.SLIME_COLOR, 0);

            appendBlocksToBlockEntities();
        }));

        for (IConfig iConfig : ServiceLoader.load(IConfig.class)) {
            iConfig.register(modContainer);
        }
    }

    private static void food(Item item, Supplier<FoodProperties> food) {
        component(item, builder -> builder.set(DataComponents.FOOD, food.get()));
    }

    private static <T> void component(Supplier<DataComponentType<T>> type, T t, Item... items) {
        for (Item item : items) {
            component(item, type, t);
        }
    }

    private static <T> void component(Item item, Supplier<DataComponentType<T>> type, T t) {
        component(item, builder -> builder.set(type, t));
    }

    private static void component(Item item, Consumer<DataComponentMap.Builder> consumer) {
        DataComponentMap.Builder builder = DataComponentMap.builder();
        builder.addAll(item.components);
        consumer.accept(builder);
        item.components = builder.build();
    }

    private static void appendBlocksToBlockEntities() {
        Set<Block> signValidBlocks = BlockEntityType.SIGN.validBlocks;
        if(!(signValidBlocks instanceof ObjectOpenHashSet<Block>)) {
            signValidBlocks = new ObjectOpenHashSet<>(signValidBlocks);
        }

        signValidBlocks.add(ModBlocks.AZALEA_SIGN.get());
        signValidBlocks.add(ModBlocks.AZALEA_WALL_SIGN.get());
        signValidBlocks.add(ModBlocks.PALM_SIGN.get());
        signValidBlocks.add(ModBlocks.PALM_WALL_SIGN.get());
        signValidBlocks.add(ModBlocks.BAOBAB_SIGN.get());
        signValidBlocks.add(ModBlocks.BAOBAB_WALL_SIGN.get());

        Set<Block> hangingSignValidBlocks = BlockEntityType.HANGING_SIGN.validBlocks;
        if (!(hangingSignValidBlocks instanceof ObjectOpenHashSet<Block>)) {
            hangingSignValidBlocks = new ObjectOpenHashSet<>(hangingSignValidBlocks);
        }

        hangingSignValidBlocks.add(ModBlocks.AZALEA_HANGING_SIGN.get());
        hangingSignValidBlocks.add(ModBlocks.AZALEA_WALL_HANGING_SIGN.get());
        hangingSignValidBlocks.add(ModBlocks.PALM_HANGING_SIGN.get());
        hangingSignValidBlocks.add(ModBlocks.PALM_WALL_HANGING_SIGN.get());
        hangingSignValidBlocks.add(ModBlocks.BAOBAB_HANGING_SIGN.get());
        hangingSignValidBlocks.add(ModBlocks.BAOBAB_WALL_HANGING_SIGN.get());
    }

}