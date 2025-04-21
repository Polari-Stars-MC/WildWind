package org.polaris2023.wild_wind.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.AddTableLootModifier;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import org.polaris2023.wild_wind.datagen.loot.ModEntityExtraLootSubProvider;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import static org.polaris2023.wild_wind.WildWindMod.MOD_ID;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/01 20:48:00}
 */
public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, MOD_ID);
    }

    @Override
    protected void start() {
        this.add("animals_drop_fur", new AddTableLootModifier(new LootItemCondition[] {
                anyOf(
                        EntityType.CAMEL.getDefaultLootTable(),
                        EntityType.FOX.getDefaultLootTable(),
                        EntityType.POLAR_BEAR.getDefaultLootTable(),
                        EntityType.SNIFFER.getDefaultLootTable()
                )
        }, ModEntityExtraLootSubProvider.DROP_FUR));
        this.add("bats_drop_wing", new AddTableLootModifier(new LootItemCondition[] {
                anyOf(
                        EntityType.BAT.getDefaultLootTable()
                )
        }, ModEntityExtraLootSubProvider.DROP_BAT_WING));
        this.add("squids_drop_calamari", new AddTableLootModifier(new LootItemCondition[] {
                anyOf(
                        EntityType.SQUID.getDefaultLootTable()
                )
        }, ModEntityExtraLootSubProvider.DROP_CALAMARI));
        this.add("glow_squids_drop_glowing_calamari", new AddTableLootModifier(new LootItemCondition[] {
                anyOf(
                        EntityType.GLOW_SQUID.getDefaultLootTable()
                )
        }, ModEntityExtraLootSubProvider.DROP_GLOWING_CALAMARI));
        this.add("wither_skeleton_drop_charred_bone", new AddTableLootModifier(new LootItemCondition[] {
                anyOf(
                        EntityType.WITHER_SKELETON.getDefaultLootTable()
                )
        }, ModEntityExtraLootSubProvider.DROP_CHARRED_BONE));
    }

    @SafeVarargs
	private static LootItemCondition anyOf(ResourceKey<LootTable>... lootTables) {
        return AnyOfCondition.anyOf(
                Arrays.stream(lootTables)
                        .map(ResourceKey::location)
                        .map(LootTableIdCondition::builder)
                        .toArray(LootTableIdCondition.Builder[]::new)
        ).build();
    }
}
