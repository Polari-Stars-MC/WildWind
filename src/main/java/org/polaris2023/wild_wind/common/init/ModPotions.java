package org.polaris2023.wild_wind.common.init;

import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Supplier;

import static org.polaris2023.wild_wind.common.init.ModInitializer.POTIONS;

public class ModPotions {
    public static <T extends Potion> DeferredHolder<Potion,T> register(String name, Supplier<T> supplier){
        return POTIONS.register(name, supplier);
    }
}
