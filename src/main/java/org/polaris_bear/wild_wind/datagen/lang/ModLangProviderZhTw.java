package org.polaris_bear.wild_wind.datagen.lang;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.polaris_bear.wild_wind.WildWindMod;
import org.polaris_bear.wild_wind.common.init.ModEntities;
import org.polaris_bear.wild_wind.common.init.ModItems;

public class ModLangProviderZhTw extends LanguageProvider {

    public ModLangProviderZhTw(PackOutput output) {
        super(output, WildWindMod.MOD_ID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        add(ModItems.FIREFLY_SPAWN_EGG.get(), "螢火蟲刷怪蛋");
        add(ModEntities.FIREFLY.get(), "螢火蟲");
    }
}
