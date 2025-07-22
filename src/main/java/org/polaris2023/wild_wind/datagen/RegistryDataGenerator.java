package org.polaris2023.wild_wind.datagen;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModDamageType;
import org.polaris2023.wild_wind.common.init.ModEnchantments;
import org.polaris2023.wild_wind.datagen.worldgen.ModBiomeModifierRegistry;
import org.polaris2023.wild_wind.datagen.worldgen.ModConfiguredFeatureRegistry;
import org.polaris2023.wild_wind.datagen.worldgen.ModPlacedFeatureRegistry;
import org.polaris2023.wild_wind.datagen.worldgen.ModStructureModifierRegistry;

public class RegistryDataGenerator extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
        .add(Registries.ENCHANTMENT, ModEnchantments::bootstrap)
        .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatureRegistry::bootstrap)
        .add(Registries.PLACED_FEATURE, ModPlacedFeatureRegistry::bootstrap)
        .add(Registries.DAMAGE_TYPE, ModDamageType::bootstrap)
        .add(Registries.STRUCTURE, ModStructureModifierRegistry::bootstrapStructure)
        .add(Registries.STRUCTURE_SET, ModStructureModifierRegistry::bootstrapStructureSet)
        .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifierRegistry::bootstrap);

    public RegistryDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, BUILDER, Set.of("minecraft", WildWindMod.MOD_ID));
    }
}
