package org.polaris2023.wild_wind.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import org.polaris2023.wild_wind.common.init.ModVanillaCompat;

import java.util.concurrent.CompletableFuture;

public class ModCompostMapProvider extends DataMapProvider {
    Builder<Compostable, Item> compostables = this.builder(NeoForgeDataMaps.COMPOSTABLES);

    public ModCompostMapProvider(PackOutput packOutput, CompletableFuture<Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        ModVanillaCompat.compostables.forEach(this::addCompostable);
    }

    protected void addCompostable(ItemLike item, float chance) {
        compostables.add(item.asItem().builtInRegistryHolder(), new Compostable(chance), false);
    }
}
