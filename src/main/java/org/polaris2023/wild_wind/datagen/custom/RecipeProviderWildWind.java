package org.polaris2023.wild_wind.datagen.custom;

import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import org.polaris2023.wild_wind.util.interfaces.IRecipe;

import java.util.concurrent.CompletableFuture;

public class RecipeProviderWildWind implements DataProvider, IRecipe<RecipeProviderWildWind> {
    private String modid;
    private PackOutput output;

    @Override
    public void init() {
        IRecipe.super.init();
    }

    @Override
    public RecipeProviderWildWind setModid(String modid) {
        this.modid = modid;
        return this;
    }

    @Override
    public RecipeProviderWildWind setOutput(PackOutput output) {
        this.output = output;
        return this;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        return CompletableFuture.allOf();
    }

    @Override
    public String getName() {
        return "Recipe Provider" + modid;
    }
}
