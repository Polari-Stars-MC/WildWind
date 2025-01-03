package org.polaris2023.wild_wind.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.polaris2023.wild_wind.datagen.loot.ModEntityLootSubProvider;

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

    @Override
    protected void validate(WritableRegistry<LootTable> writableregistry, ValidationContext validationcontext, ProblemReporter.Collector problemreporter$collector) {
    }
}
