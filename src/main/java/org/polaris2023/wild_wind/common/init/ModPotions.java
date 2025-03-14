package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.annotation.language.PotionI18n;
import org.polaris2023.wild_wind.WildWindMod;

import java.util.function.Supplier;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(BuiltInRegistries.POTION, WildWindMod.MOD_ID);

    @PotionI18n(en_us ="Glowing", zh_cn ="发光", zh_tw ="发光")
    public static final DeferredHolder<Potion, Potion> GLOWING_POTION = POTIONS.register("glowing_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.GLOWING, 3600)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }

    public static <T extends Potion> DeferredHolder<Potion,T> register(String name, Supplier<T> supplier){
        return POTIONS.register(name, supplier);
    }
}
