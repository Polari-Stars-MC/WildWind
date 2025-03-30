package org.polaris2023.wild_wind.common.recipe;

import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModItems;
import org.polaris2023.wild_wind.common.init.ModPotions;

@EventBusSubscriber(modid = WildWindMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class BrewingRecipe {
    @SubscribeEvent
    public static void registerBrewingRecipes(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addMix(
            Potions.AWKWARD,
            ModItems.ASH_DUST.get(),
            Potions.INVISIBILITY
        );

        builder.addMix(
            Potions.AWKWARD,
            ModItems.GLOW_POWDER.get(),
            ModPotions.GLOWING_POTION
        );
    }
}
