package org.polaris2023.wild_wind;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class WildWindCommonConfig {
	private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
	static final ModConfigSpec SPEC;

	private WildWindCommonConfig() {
	}

	public static final class BiomeConfig {
		private static final ModConfigSpec.IntValue OVERWORLD_BIOMES_WEIGHT;
		private static final ModConfigSpec.IntValue NETHER_BIOMES_WEIGHT;

		public static void bootstrap() {
		}

		public static int overworldBiomeWeight() {
			return OVERWORLD_BIOMES_WEIGHT.get();
		}
		public static int netherBiomeWeight() {
			return NETHER_BIOMES_WEIGHT.get();
		}

		static {
			BUILDER.push("biomes-generation")
					.comment("Note: These fields are only enabled if you install terrablender.")
					.comment("You can determine:")
					.comment("  1. Whether each biome can generate in the world or not.")
					.comment("  2. The weight of each region.");
			OVERWORLD_BIOMES_WEIGHT = BUILDER.comment("Weight of the overworld region of wild wind.").defineInRange("OVERWORLD_BIOMES_WEIGHT", 80, 0, 65536);
			NETHER_BIOMES_WEIGHT = BUILDER.comment("Weight of the nether region of wild wind.").defineInRange("NETHER_BIOMES_WEIGHT", 80, 0, 65536);
			BUILDER.pop();
		}
	}

	static {
		BUILDER.push("wild_wind-common");
		BiomeConfig.bootstrap();
		BUILDER.pop();

		SPEC = BUILDER.build();
	}
}
