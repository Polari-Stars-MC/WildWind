package org.polaris2023.wild_wind.util.interfaces;

import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;

import java.util.concurrent.CompletableFuture;

public interface ILanguage<T extends ILanguage<T> & DataProvider> {
    T add(Object r, String value);
    T setModid(String modid);
    T setOutput(PackOutput output);
    CompletableFuture<?> run(CachedOutput cachedOutput);
    default void init() {}
}
