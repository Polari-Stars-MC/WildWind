package org.polaris2023.wild_wind.common.init;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.wild_wind.common.effect.ExiledEffect;

import java.util.function.Supplier;

import static org.polaris2023.wild_wind.common.init.ModInitializer.EFFECTS;

public class ModEffects {

    @I18n(en_us = "exiled", zh_cn = "放逐", zh_tw = "放逐")
    public static final DeferredHolder<MobEffect, ExiledEffect> EXILED =
            register("exiled", () -> new ExiledEffect(MobEffectCategory.NEUTRAL, 0x00BDBE));

    public static <T extends MobEffect> DeferredHolder<MobEffect, T> register(String name, Supplier<T> effect){
        return EFFECTS.register(name, effect);
    }
}
