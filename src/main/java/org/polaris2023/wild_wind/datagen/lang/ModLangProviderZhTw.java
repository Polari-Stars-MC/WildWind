package org.polaris2023.wild_wind.datagen.lang;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModEntities;
import org.polaris2023.wild_wind.common.init.ModItems;

public class ModLangProviderZhTw extends LanguageProvider {

    public ModLangProviderZhTw(PackOutput output) {
        super(output, WildWindMod.MOD_ID, "zh_tw");
    }

    @Override
    protected void addTranslations() {
        add(ModItems.FIREFLY_SPAWN_EGG.get(), "螢火蟲刷怪蛋");
        add(ModEntities.FIREFLY.get(), "螢火蟲");
    }
}
