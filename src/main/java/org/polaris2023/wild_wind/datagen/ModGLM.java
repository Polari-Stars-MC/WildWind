package org.polaris2023.wild_wind.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

import java.util.concurrent.CompletableFuture;

import static org.polaris2023.wild_wind.WildWindMod.MOD_ID;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/01 20:48:00}
 */
public class ModGLM extends GlobalLootModifierProvider {
    public ModGLM(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, MOD_ID);
    }

    @Override
    protected void start() {

    }
}
