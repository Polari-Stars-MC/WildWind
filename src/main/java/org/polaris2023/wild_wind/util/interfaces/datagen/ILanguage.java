package org.polaris2023.wild_wind.util.interfaces.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.wild_wind.datagen.WildWindClientProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public interface ILanguage extends DatagenClient {
    Map<String, LanguageProvider> LANGUAGES = new HashMap<>();

    default  <T extends Potion> WildWindClientProvider addPotion(String lang, Supplier<T> r, String value, String splashPrefix, String lingeringPrefix, String suffix) {
        ResourceLocation key = BuiltInRegistries.POTION.getKey(r.get());
        if (key != null) {
            String name = key.getPath();
            lang(lang, Items.POTION.getDescriptionId() + ".effect." + name, value + suffix);
            lang(lang, Items.SPLASH_POTION.getDescriptionId() + ".effect." + name, splashPrefix + value + suffix);
            lang(lang, Items.LINGERING_POTION.getDescriptionId() + ".effect." + name, lingeringPrefix + value + suffix);
        }

        return self();
    }

    default LanguageProvider lang(String lang, Object key, String translate) {
        LanguageProvider languageProvider;
        if (!LANGUAGES.containsKey(lang)) {
            languageProvider = new LanguageProvider(self().output, self().modid, lang) {
                @Override
                protected void addTranslations() {
                }
            };
            LANGUAGES.put(lang, languageProvider);
        } else {
            languageProvider = LANGUAGES.get(lang);
        }
        switch (key) {
            case String str -> languageProvider.add(str, translate);
            case DeferredHolder<?, ?> holder -> lang(lang, holder.get(), translate);
            case Supplier<?> supplier -> lang(lang, supplier.get(), translate);
            case ResourceKey<?> eKey -> {
                eKey.cast(Registries.ENCHANTMENT).ifPresent(rk -> {
                    lang(lang, "enchantment.%s".formatted(eKey.location().toString().replace(":", ".")), translate);
                });
            }
            case SoundEvent sound -> languageProvider.add("sound." + sound.getLocation().toString().replace(":", "."), translate);
            case Block block -> languageProvider.add(block, translate);
            case Item item -> languageProvider.add(item, translate);
            case EntityType<?> type -> languageProvider.add(type.getDescriptionId(), translate);
            case TranslatableContents contents -> languageProvider.add(contents.getKey(), translate);
            case MobEffect effect -> languageProvider.add(effect.getDescriptionId(), translate);
            case CreativeModeTab tab -> lang(lang, tab.getDisplayName().getContents() instanceof TranslatableContents contents ? contents : Optional.empty(), translate);
            case ItemStack stack -> languageProvider.add(stack.getDescriptionId(), translate);
            default -> {}//没有其中任何一个语言则略过
        }
        return languageProvider;
    }
}
