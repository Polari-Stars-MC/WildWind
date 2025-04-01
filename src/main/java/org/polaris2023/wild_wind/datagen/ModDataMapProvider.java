package org.polaris2023.wild_wind.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.init.ModItems;

import java.util.concurrent.CompletableFuture;

/** @noinspection deprecation*/
public class ModDataMapProvider extends DataMapProvider {

    public ModDataMapProvider(PackOutput packOutput, CompletableFuture<Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        // COMPOSTABLES
        this.addCompostable(0.85F, ModItems.BAKED_BEETROOT.get());
        this.addCompostable(0.85F, ModItems.BAKED_CARROT.get());
        this.addCompostable(0.85F, ModItems.BAKED_MUSHROOM.get());
        this.addCompostable(0.85F, ModItems.BAKED_APPLE.get());
        this.addCompostable(0.85F, ModItems.DOUGH.get());
        this.addCompostable(0.85F, ModItems.FAILED_CUISINE.get());
        this.addCompostable(0.65F, ModItems.BAKED_PUMPKIN_SLICE.get());
        this.addCompostable(0.65F, ModItems.BAKED_MELON_SLICE.get());
        this.addCompostable(0.65F, ModItems.BAKED_BERRIES.get());
        this.addCompostable(0.65F, ModItems.FLOUR.get());
        this.addCompostable(0.5F, ModItems.BAKED_SEEDS.get());
        this.addCompostable(0.5F, ModItems.BAKED_SEEDS.get());
        this.addCompostable(0.3F, ModBlocks.BAOBAB_LEAVES.get());
        this.addCompostable(0.3F, ModBlocks.PALM_LEAVES.get());
        this.addCompostable(0.3F, ModBlocks.BAOBAB_SAPLING.get());
        this.addCompostable(0.3F, ModBlocks.PALM_SAPLING.get());
        // FURNACE_FUELS
        this.addFurnaceFuel(ModBlocks.PALM_CROWN.get(), 300);
    }

    protected void addCompostable(float chance, ItemLike item) {
        Builder<Compostable, Item> builder = this.builder(NeoForgeDataMaps.COMPOSTABLES);
        builder.add(item.asItem().builtInRegistryHolder(), new Compostable(chance), false);
    }

    protected void addFurnaceFuel(ItemLike item, int burnTime) {
        Builder<FurnaceFuel, Item> builder = this.builder(NeoForgeDataMaps.FURNACE_FUELS);
        builder.add(item.asItem().builtInRegistryHolder(), new FurnaceFuel(burnTime), false);
    }

}
