package org.polaris2023.wild_wind.datagen.lang;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.init.ModEntities;
import org.polaris2023.wild_wind.common.init.ModItems;

public class ModLangProviderZh extends LanguageProvider {

    public ModLangProviderZh(PackOutput output) {
        super(output, WildWindMod.MOD_ID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        add(ModItems.FIREFLY_SPAWN_EGG.get(), "萤火虫刷怪蛋");
        add(ModItems.GLARE_SPAWN_EGG.get(), "怒目怪刷怪蛋");
        add(ModItems.GLOW_POWDER.get(), "萤光粉末");
        add(ModItems.GLOW_MUCUS.get(), "萤光黏液");
        add(ModItems.LIVING_TUBER.get(), "活根");
        add(ModBlocks.GLAREFLOWER.get(), "怒目花");
        add(ModBlocks.GLAREFLOWER_SEEDS.get(), "怒目花种子");
        add(ModBlocks.FIREFLY_JAR.get(), "萤火虫瓶");
        add(ModEntities.FIREFLY.get(), "萤火虫");
        add(ModEntities.GLARE.get(), "怒目怪");
    }
}
