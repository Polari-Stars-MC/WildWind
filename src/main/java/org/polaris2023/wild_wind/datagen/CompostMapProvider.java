package org.polaris2023.wild_wind.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import org.polaris2023.wild_wind.common.init.ModFoods;
import org.polaris2023.wild_wind.common.init.items.foods.ModBaseFoods;

import java.util.concurrent.CompletableFuture;

public class CompostMapProvider extends DataMapProvider {
    public CompostMapProvider(PackOutput packOutput, CompletableFuture<Provider> lookupProvider)
    {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather() {
        this.builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(ModBaseFoods.BAKED_BEETROOT.entry, new Compostable(0.85f), false)
                .add(ModBaseFoods.BAKED_SEEDS.entry, new Compostable(0.5f), false)
                .add(ModBaseFoods.BAKED_BERRIES.entry, new Compostable(0.65f), false)
                .add(ModBaseFoods.BAKED_CARROT.entry, new Compostable(0.85f), false)
                .add(ModBaseFoods.BAKED_MUSHROOM.entry, new Compostable(0.85f), false)
                .add(ModBaseFoods.BAKED_PUMPKIN_SLICE.entry, new Compostable(0.65f), false)
                .add(ModBaseFoods.BAKED_MELON_SLICE.entry, new Compostable(0.65f), false)
                .add(ModBaseFoods.PUMPKIN_SLICE.entry, new Compostable(0.5f), false)
                .add(ModBaseFoods.BAKED_APPLE.entry, new Compostable(0.85f), false)
                .add(ModBaseFoods.DOUGH.entry, new Compostable(0.85f), false)
                .add(ModBaseFoods.FLOUR.entry, new Compostable(0.65f), false)
                .add(ModBaseFoods.FAILED_CUISINE.entry, new Compostable(0.85f), false);

    }
}
