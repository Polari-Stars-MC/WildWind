package org.polaris2023.wild_wind.datagen;

import net.minecraft.data.DataProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.datagen.lang.ModLangProviderEn;
import org.polaris2023.wild_wind.datagen.lang.ModLangProviderZh;
import org.polaris2023.wild_wind.datagen.lang.ModLangProviderZhTw;
import org.polaris2023.wild_wind.datagen.model.ModBlockModelProvider;
import org.polaris2023.wild_wind.datagen.model.ModItemModelProvider;
import org.polaris2023.wild_wind.datagen.tag.ModBlockTagsProvider;
import org.polaris2023.wild_wind.datagen.tag.ModEntityTypeTagsProvider;
import org.polaris2023.wild_wind.datagen.tag.ModItemTagsProvider;
import org.polaris2023.wild_wind.util.interfaces.ILanguage;

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
            if (language instanceof DataProvider) {
                language.setModid(MOD_ID);
                language.setOutput(pack);
                gen.addProvider(event.includeClient(), (DataProvider) language);
            }
        }
        //gen.addProvider(event.includeClient(), new ModLangProviderEn(pack));
        //gen.addProvider(event.includeClient(), new ModLangProviderZh(pack));
        //gen.addProvider(event.includeClient(), new ModLangProviderZhTw(pack));

//        gen.addProvider(event.includeClient(), new CheckGenTextures(pack, MOD_ID, provider, helper));
        gen.addProvider(event.includeClient(), new ModBlockModelProvider(pack, helper));
        gen.addProvider(event.includeClient(), new ModItemModelProvider(pack, helper));
        gen.addProvider(event.includeServer(), new ModEntityTypeTagsProvider(pack, provider, helper));
        ModBlockTagsProvider blockTagsProvider = new ModBlockTagsProvider(pack, provider, helper);
        gen.addProvider(event.includeServer(), blockTagsProvider);
        gen.addProvider(event.includeServer(), new ModItemTagsProvider(pack, provider, blockTagsProvider.contentsGetter(), helper));
        gen.addProvider(event.includeServer(), new ModLootTableProvider(pack, provider));
    }
}
