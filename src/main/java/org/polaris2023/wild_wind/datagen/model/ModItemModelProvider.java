package org.polaris2023.wild_wind.datagen.model;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModInitializer;
import org.polaris2023.wild_wind.common.item.BasicBlockItem;
import org.polaris2023.wild_wind.common.item.BasicItem;
import org.polaris2023.wild_wind.common.item.BasicMobBucketItem;
import org.polaris2023.wild_wind.common.item.MagicFluteItem;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, WildWindMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (DeferredHolder<Item, ? extends Item> item : ModInitializer.items()) {
            switch (item.get()) {
                case DeferredSpawnEggItem eggItem -> {
                    spawnEggItem(eggItem);
                }
                case BasicItem basicItem -> {
                    basicItem(basicItem);
                }
                case BasicBlockItem blockItem -> {
                    basicItem(blockItem);
                }
                case BasicMobBucketItem bucketItem -> {
                    basicItem(bucketItem);
                }
                case MagicFluteItem magicFluteItem -> basicItem(magicFluteItem);
                default -> {

                }
            }
        }


    }
}
