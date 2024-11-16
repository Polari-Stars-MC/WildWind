package org.polaris2023.wild_wind.common.compat.terrablender;

import org.polaris2023.wild_wind.common.compat.terrablender.region.ModNetherBiomeRegion;
import org.polaris2023.wild_wind.common.compat.terrablender.region.ModOverworldBiomeRegion;
import org.polaris2023.wild_wind.config.WildWindCommonConfig;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

import static org.polaris2023.wild_wind.WildWindMod.MOD_ID;

public final class TerraBlenderUtils {
    private TerraBlenderUtils() {
    }

    public static void init() {
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModSurfaceRules.overworld());

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.NETHER, MOD_ID, ModSurfaceRules.nether());
        Regions.register(new ModOverworldBiomeRegion(WildWindCommonConfig.BiomeConfig.OVERWORLD_BIOMES_WEIGHT));
        Regions.register(new ModNetherBiomeRegion(WildWindCommonConfig.BiomeConfig.NETHER_BIOMES_WEIGHT));
    }
}
