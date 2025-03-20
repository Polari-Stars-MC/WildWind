package org.polaris2023.wild_wind.datagen;

import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;

import java.util.concurrent.CompletableFuture;

/**
 * @author : baka4n
 * {@code @Date : 2025/03/20 21:00:28}
 */
public class WildWindServerProvider implements DataProvider {
    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        return CompletableFuture.allOf();
    }

    @Override
    public String getName() {
        return "";
    }
}
