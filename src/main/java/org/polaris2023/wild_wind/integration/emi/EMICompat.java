package org.polaris2023.wild_wind.integration.emi;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiWorldInteractionRecipe;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluids;
import org.polaris2023.wild_wind.common.dyed.DyedBlockMap;
import org.polaris2023.wild_wind.common.init.ModBlocks;

import java.util.Objects;

@EmiEntrypoint
public class EMICompat implements EmiPlugin {
    @Override
    public void register(EmiRegistry registry) {
        registerWorldInteractionWashingRecipe(registry, "WOOL", ModBlocks.WOOL.get());
        registerWorldInteractionWashingRecipe(registry, "GLAZED_TERRACOTTA", ModBlocks.GLAZED_TERRACOTTA.get());
    }

    private void registerWorldInteractionWashingRecipe(EmiRegistry registry, String type, Block result) {
        EmiStack cauldron = EmiStack.of(Items.CAULDRON);
        EmiStack waterThird = EmiStack.of(Fluids.WATER, 1000 / 3);
        for(Block block : DyedBlockMap.getDyedBlock(type).values()) {
            registry.addRecipe(
                    EmiWorldInteractionRecipe.builder()
                            .leftInput(EmiStack.of(block))
                            .rightInput(cauldron, true)
                            .rightInput(waterThird, false)
                            .output(EmiStack.of(result))
                            .supportsRecipeTree(false)
                            .build()
            );
        }
    }
}
