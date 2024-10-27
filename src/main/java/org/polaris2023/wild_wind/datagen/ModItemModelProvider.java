package org.polaris2023.wild_wind.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
	public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, WildWindMod.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		spawnEggItem(ModItems.FIREFLY_SPAWN_EGG.get());
	}
}
