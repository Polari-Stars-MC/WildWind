package org.polaris2023.wild_wind.datagen.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.polaris2023.wild_wind.common.init.ModEnchantments;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.tags.EnchantmentTags.NON_TREASURE;
import static org.polaris2023.wild_wind.WildWindMod.MOD_ID;

/**
 * @author : baka4n
 * {@code @Date : 2025/03/22 19:05:36}
 */
public class ModEnchantTagsProvider extends EnchantmentTagsProvider {
    public ModEnchantTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(NON_TREASURE).add(ModEnchantments.AUTO_SMELTING.get());
    }
}
