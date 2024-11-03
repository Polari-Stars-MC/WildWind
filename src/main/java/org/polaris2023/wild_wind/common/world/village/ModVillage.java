package org.polaris2023.wild_wind.common.world.village;

import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import static org.polaris2023.wild_wind.WildWindMod.MOD_ID;
import static org.polaris2023.wild_wind.util.RegistryUtil.getRegistryName;

public class ModVillage {
	public static class Registers {
		private static final DeferredRegister<PoiType> POINTS_OF_INTEREST = DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, MOD_ID);
		private static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(Registries.VILLAGER_PROFESSION, MOD_ID);
		private static final DeferredRegister<VillagerType> VILLAGER_TYPE = DeferredRegister.create(Registries.VILLAGER_TYPE, MOD_ID);

		private static Collection<BlockState> assembleStates(Block block) {
			return block.getStateDefinition().getPossibleStates();
		}

		private static PoiType createPointOfInterest(Collection<BlockState> block) {
			return new PoiType(ImmutableSet.copyOf(block), 1, 1);
		}

		private static VillagerProfession createProf(ResourceLocation name, Supplier<ResourceKey<PoiType>> poi, SoundEvent sound) {
			ResourceKey<PoiType> poiName = poi.get();
			return new VillagerProfession(
					name.toString(),
					(p) -> p.is(poiName),
					(p) -> p.is(poiName),
					ImmutableSet.of(),
					ImmutableSet.of(),
					sound
			);
		}

		public static void init(IEventBus bus) {
			POINTS_OF_INTEREST.register(bus);
			PROFESSIONS.register(bus);
			VILLAGER_TYPE.register(bus);
		}
	}

	@EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.GAME)
	public static class Events {
		private static final ResourceLocation VANILLA_FISHERMAN = ResourceLocation.withDefaultNamespace("fisherman");

		@SubscribeEvent
		public static void registerTrades(VillagerTradesEvent event) {
			Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
			ResourceLocation currentVillagerProfession = getRegistryName(event.getType());
			//TODO: add villager trades
			if(VANILLA_FISHERMAN.equals(currentVillagerProfession)) {

			}
		}
	}
}
