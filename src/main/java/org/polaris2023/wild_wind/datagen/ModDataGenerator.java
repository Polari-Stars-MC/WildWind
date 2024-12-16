package org.polaris2023.wild_wind.datagen;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import org.polaris2023.wild_wind.datagen.model.ModBlockModelProvider;
import org.polaris2023.wild_wind.datagen.tag.ModBlockTagsProvider;
import org.polaris2023.wild_wind.datagen.tag.ModEntityTypeTagsProvider;
import org.polaris2023.wild_wind.datagen.tag.ModInstrumentTagsProvider;
import org.polaris2023.wild_wind.datagen.tag.ModItemTagsProvider;
import org.polaris2023.wild_wind.util.interfaces.ILanguage;
import org.polaris2023.wild_wind.util.interfaces.IModel;

import java.util.ServiceLoader;

import static org.polaris2023.wild_wind.WildWindMod.MOD_ID;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = MOD_ID)
public class ModDataGenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var gen = event.getGenerator();
        var pack = gen.getPackOutput();
        var provider = event.getLookupProvider();
        var helper = event.getExistingFileHelper();
        for (ILanguage<?> language : ServiceLoader.load(ILanguage.class)) {
            gen.addProvider(event.includeClient(), language.setModid(MOD_ID).setOutput(pack));
        }

        gen.addProvider(event.includeClient(), new ModSoundDefinitionsProvider(pack, helper));
        gen.addProvider(event.includeClient(), new ModBlockModelProvider(pack, helper));
        for (IModel<?> model : ServiceLoader.load(IModel.class)) {
            gen.addProvider(event.includeClient(), model.setModid(MOD_ID).setOutput(pack));
        }
        gen.addProvider(event.includeClient(), new ModBlockStateProvider(pack, helper));
        gen.addProvider(event.includeServer(), new ModRecipeProvider(pack, provider));
        gen.addProvider(event.includeServer(), new ModEntityTypeTagsProvider(pack, provider, helper));
        ModBlockTagsProvider blockTagsProvider = new ModBlockTagsProvider(pack, provider, helper);
        gen.addProvider(event.includeServer(), blockTagsProvider);
        gen.addProvider(event.includeServer(), new ModItemTagsProvider(pack, provider, blockTagsProvider.contentsGetter(), helper));
        gen.addProvider(event.includeServer(), new ModLootTableProvider(pack, provider));
        gen.addProvider(event.includeServer(), new ModEnchantmentProvider(pack, provider));
        gen.addProvider(event.includeServer(), new ModInstrumentTagsProvider(pack, provider, helper));
    }
}
