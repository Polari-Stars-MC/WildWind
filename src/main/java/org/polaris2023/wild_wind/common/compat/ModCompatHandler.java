package org.polaris2023.wild_wind.common.compat;

import com.google.common.collect.Sets;
import net.neoforged.fml.ModList;
import org.polaris2023.wild_wind.common.compat.terrablender.TerraBlenderUtils;

import java.util.List;
import java.util.Set;

public interface ModCompatHandler {
	Set<String> compatMods = Sets.newHashSet();

	String TERRABLENDER = "terrablender";
	List<String> compatModIds = List.of(TERRABLENDER);

	static void lazyCheckModLoaded() {
		compatModIds.forEach(modid -> {
			if(ModList.get().isLoaded(modid)) {
				compatMods.add(modid);
			}
		});
	}

	static void solveCompat() {
		lazyCheckModLoaded();
	}

	static void solveTerraBlender() {
		if(compatMods.contains(TERRABLENDER)) {
			TerraBlenderUtils.init();
		}
	}
}
