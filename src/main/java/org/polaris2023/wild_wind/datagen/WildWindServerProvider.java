package org.polaris2023.wild_wind.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

/**
 * @author : baka4n
 * {@code @Date : 2025/03/20 21:00:28}
 */
public class WildWindServerProvider implements DataProvider {

    public WildWindServerProvider(PackOutput output,
                                  String modid,
                                  CompletableFuture<HolderLookup.Provider> provider,
                                  ExistingFileHelper helper) {

    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        return CompletableFuture.allOf();
    }

    @Override
    public String getName() {
        return "";
    }
}
