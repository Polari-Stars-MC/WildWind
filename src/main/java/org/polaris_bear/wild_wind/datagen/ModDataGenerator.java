package org.polaris_bear.wild_wind.datagen;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.polaris_bear.wild_wind.WildWindMod;
import org.polaris_bear.wild_wind.datagen.lang.ModLangProviderEn;
import org.polaris_bear.wild_wind.datagen.lang.ModLangProviderZh;
import org.polaris_bear.wild_wind.datagen.tag.ModEntityTypeTagsProvider;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = WildWindMod.MOD_ID)
public class ModDataGenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var gen = event.getGenerator();
        var pack = gen.getPackOutput();
        var provider = event.getLookupProvider();
        var helper = event.getExistingFileHelper();
        gen.addProvider(event.includeClient(), new ModLangProviderEn(pack));
        gen.addProvider(event.includeClient(), new ModLangProviderZh(pack));
        gen.addProvider(event.includeClient(), new ModItemModelProvider(pack, helper));
        gen.addProvider(event.includeServer(), new ModEntityTypeTagsProvider(pack, provider, helper));
        gen.addProvider(event.includeServer(), new ModBlockTagsProvider(pack, provider, helper));
        gen.addProvider(event.includeServer(), new ModLootTableProvider(pack, provider));
    }
}
