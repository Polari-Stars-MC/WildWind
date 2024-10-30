package org.polaris2023.wild_wind.datagen.lang;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.init.ModEntities;
import org.polaris2023.wild_wind.common.init.ModItems;

public class ModLangProviderZhTw extends LanguageProvider {

    public ModLangProviderZhTw(PackOutput output) {
        super(output, WildWindMod.MOD_ID, "zh_tw");
    }

    @Override
    protected void addTranslations() {
        add(ModItems.FIREFLY_SPAWN_EGG.get(), "螢火蟲刷怪蛋");
        add(ModItems.GLARE_SPAWN_EGG.get(), "怒目怪刷怪蛋");
        add(ModItems.GLOW_POWDER.get(), "螢光粉末");
        add(ModItems.GLOW_MUCUS.get(), "螢光黏液");
        add(ModItems.LIVING_TUBER.get(), "活根");
        add(ModBlocks.GLAREFLOWER.get(), "怒目花");
        add(ModBlocks.GLAREFLOWER_SEEDS.get(), "怒目花種子");
        add(ModBlocks.FIREFLY_JAR.get(), "螢火蟲瓶");
        add(ModEntities.FIREFLY.get(), "螢火蟲");
        add(ModEntities.GLARE.get(), "怒目怪");
    }
}
