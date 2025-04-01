package org.polaris2023.wild_wind.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.DetectedVersion;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import org.polaris2023.wild_wind.datagen.tag.*;
import org.polaris2023.wild_wind.util.interfaces.ILanguage;
import org.polaris2023.wild_wind.util.interfaces.IModel;
import net.minecraft.server.packs.PackType;

import java.util.Optional;
import java.util.ServiceLoader;
import java.util.concurrent.CompletableFuture;

import static org.polaris2023.wild_wind.WildWindMod.MOD_ID;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = MOD_ID)
public class ModDataGenerator {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();
        DatapackBuiltinEntriesProvider datapackProvider = new RegistryDataGenerator(output, event.getLookupProvider());
        CompletableFuture<HolderLookup.Provider> lookupProvider = datapackProvider.getRegistryProvider();
        gen.addProvider(true, datapackProvider);

        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();
        gen.addProvider(event.includeServer(), new ModEnchantTagsProvider(output, lookupProvider, helper));
        for (ILanguage<?> language : ServiceLoader.load(ILanguage.class)) {
            gen.addProvider(event.includeClient(), language.setModid(MOD_ID).setOutput(output));
        }

        gen.addProvider(event.includeClient(), new ModSoundDefinitionsProvider(output, helper));
        for (IModel<?> model : ServiceLoader.load(IModel.class)) {
            gen.addProvider(event.includeClient(), model.setModid(MOD_ID).setOutput(output));
        }
        gen.addProvider(event.includeClient(), new WildWindClientProvider(output, MOD_ID, helper));
        gen.addProvider(event.includeServer(), new ModRecipeProvider(output, provider));
        gen.addProvider(event.includeServer(), new ModEntityTypeTagsProvider(output, provider, helper));
        ModBlockTagsProvider blockTagsProvider = new ModBlockTagsProvider(output, provider, helper);
        gen.addProvider(event.includeServer(), blockTagsProvider);
        gen.addProvider(event.includeServer(), new ModItemTagsProvider(output, provider, blockTagsProvider.contentsGetter(), helper));
        gen.addProvider(event.includeServer(), new ModLootTableProvider(output, provider));

        gen.addProvider(event.includeServer(), new ModInstrumentTagsProvider(output, provider, helper));
        gen.addProvider(event.includeServer(), new ModDataMapProvider(output, provider));
        gen.addProvider(event.includeServer(), new ModDamageTypeTagsProvider(output, lookupProvider, helper));

        gen.addProvider(true, new PackMetadataGenerator(output).add(PackMetadataSection.TYPE, new PackMetadataSection(
                Component.literal("Resources for Wild Wind"),
                DetectedVersion.BUILT_IN.getPackVersion(PackType.CLIENT_RESOURCES),
                Optional.empty())));
    }
}
