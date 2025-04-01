package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wild_wind.WildWindMod;

public class ModVillagers {

    static DeferredRegister<PoiType> POIS = DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, WildWindMod.MOD_ID);
    static DeferredRegister<VillagerType> VILLAGERS = DeferredRegister.create(BuiltInRegistries.VILLAGER_TYPE, WildWindMod.MOD_ID);
    static DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(BuiltInRegistries.VILLAGER_PROFESSION, WildWindMod.MOD_ID);

}