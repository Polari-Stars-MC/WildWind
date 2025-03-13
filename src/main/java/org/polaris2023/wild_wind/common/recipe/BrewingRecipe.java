package org.polaris2023.wild_wind.common.recipe;

import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import org.polaris2023.wild_wind.common.init.items.ModBaseItems;

@EventBusSubscriber(modid = "wild_wind")
public class BrewingRecipe {
    @SubscribeEvent
    public static void registerBrewingRecipes(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addMix(
            Potions.AWKWARD,
            ModBaseItems.ASH_DUST.get(),
            Potions.INVISIBILITY
        );
    }
}
