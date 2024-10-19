package org.polaris_bear.wild_wind.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.polaris_bear.wild_wind.datagen.loot.ModEntityLootSubProvider;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class ModLootTableProvider extends LootTableProvider {
    public ModLootTableProvider(PackOutput output,
                                CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Set.of(), List.of(
                gen(ModEntityLootSubProvider::new, LootContextParamSets.ENTITY)
        ), registries);
    }


    public static SubProviderEntry gen(
            Function<HolderLookup.Provider, LootTableSubProvider> function,
            LootContextParamSet s) {
        return new SubProviderEntry(function, s);
    }
}
