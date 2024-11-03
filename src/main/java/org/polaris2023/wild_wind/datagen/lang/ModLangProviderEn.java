package org.polaris2023.wild_wind.datagen.lang;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.init.ModEntities;
import org.polaris2023.wild_wind.common.init.ModItems;

public class ModLangProviderEn extends LanguageProvider {

    public ModLangProviderEn(PackOutput output) {
        super(output, WildWindMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(ModItems.FIREFLY_SPAWN_EGG.get(), "Firefly Spawn Egg");
        add(ModItems.GLARE_SPAWN_EGG.get(), "Glare Spawn Egg");
        add(ModItems.TROUT_SPAWN_EGG.get(), "Trout Spawn Egg");
        add(ModItems.GLOW_POWDER.get(), "Glow Powder");
        add(ModItems.LIVING_TUBER.get(), "Living Tuber");
        add(ModItems.GLOW_MUCUS.get(), "Glow mucus");
        add(ModItems.TROUT_BUCKET.get(), "Trout bucket");
        add(ModItems.RAW_TROUT.get(), "raw_trout");
        add(ModItems.COOKED_TROUT.get(), "cooked_trout");
        add(ModBlocks.GLAREFLOWER.get(), "Glareflower");
        add(ModBlocks.GLAREFLOWER_SEEDS.get(), "Glareflower Seeds");
        add(ModBlocks.FIREFLY_JAR.get(), "Firefly Jar");
        add(ModEntities.FIREFLY.get(), "Firefly");
        add(ModEntities.GLARE.get(), "Glare");
        add(ModEntities.TROUT.get(), "Trout");

    }
}
