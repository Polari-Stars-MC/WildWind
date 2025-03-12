package org.polaris2023.wild_wind.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.polaris2023.wild_wind.common.init.ModEnchantments;
import org.polaris2023.wild_wind.datagen.tag.ModBlockTagsProvider;
import org.polaris2023.wild_wind.datagen.tag.ModEntityTypeTagsProvider;
import org.polaris2023.wild_wind.datagen.tag.ModInstrumentTagsProvider;
import org.polaris2023.wild_wind.datagen.tag.ModItemTagsProvider;
import org.polaris2023.wild_wind.datagen.worldgen.ModBiomeModifierRegistry;
import org.polaris2023.wild_wind.datagen.worldgen.ModConfiguredFeatureRegistry;
import org.polaris2023.wild_wind.datagen.worldgen.ModPlacedFeatureRegistry;
import org.polaris2023.wild_wind.util.interfaces.IData;
import org.polaris2023.wild_wind.util.interfaces.ILanguage;
import org.polaris2023.wild_wind.util.interfaces.IModel;

import java.util.ServiceLoader;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static org.polaris2023.wild_wind.WildWindMod.MOD_ID;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = MOD_ID)
public class ModDataGenerator {
    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.ENCHANTMENT, ModEnchantments::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatureRegistry::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatureRegistry::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifierRegistry::bootstrap);

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput pack = gen.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();
        for (ILanguage<?> language : ServiceLoader.load(ILanguage.class)) {
            gen.addProvider(event.includeClient(), language.setModid(MOD_ID).setOutput(pack));
        }

        gen.addProvider(event.includeClient(), new ModSoundDefinitionsProvider(pack, helper));
        for (IModel<?> model : ServiceLoader.load(IModel.class)) {
            gen.addProvider(event.includeClient(), model.setModid(MOD_ID).setOutput(pack));
        }
        gen.addProvider(event.includeClient(), new ModBlockStateProvider(pack, helper));
        gen.addProvider(event.includeClient(), new ModItemModelProvider(pack, helper));
        gen.addProvider(event.includeServer(), new ModRecipeProvider(pack, provider));
        gen.addProvider(event.includeServer(), new ModEntityTypeTagsProvider(pack, provider, helper));
        ModBlockTagsProvider blockTagsProvider = new ModBlockTagsProvider(pack, provider, helper);
        gen.addProvider(event.includeServer(), blockTagsProvider);
        gen.addProvider(event.includeServer(), new ModItemTagsProvider(pack, provider, blockTagsProvider.contentsGetter(), helper));
        gen.addProvider(event.includeServer(), new ModLootTableProvider(pack, provider));
        gen.addProvider(event.includeServer(), new ModInstrumentTagsProvider(pack, provider, helper));

        // datapack registry
        gen.<DatapackBuiltinEntriesProvider>addProvider(
                event.includeServer(),
                output -> new DatapackBuiltinEntriesProvider(output, event.getLookupProvider(), BUILDER, Set.of(MOD_ID))
        );
    }
}
