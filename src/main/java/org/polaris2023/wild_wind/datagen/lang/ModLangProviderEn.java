package org.polaris2023.wild_wind.datagen.lang;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModEntities;
import org.polaris2023.wild_wind.common.init.ModItems;

public class ModLangProviderEn extends LanguageProvider {
	public ModLangProviderEn(PackOutput output) {
		super(output, WildWindMod.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		add(ModItems.FIREFLY_SPAWN_EGG.get(), "Firefly Spawn Egg");
		add(ModEntities.FIREFLY.get(), "Firefly");
	}
}
