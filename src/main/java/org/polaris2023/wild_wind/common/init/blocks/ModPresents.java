package org.polaris2023.wild_wind.common.init.blocks;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.annotation.language.I18nEnum;

import java.util.Locale;

import static org.polaris2023.wild_wind.common.init.ModInitializer.register;

/**
 * @author : baka4n
 * {@code @Date : 2025/03/14 21:42:35}
 */

public enum ModPresents {
    @I18nEnum(en_us = "", zh_cn = "", zh_tw = "")
    DEFAULT,
    @I18nEnum(en_us = "Red Ribbon Red ", zh_cn = "红丝带红", zh_tw = "紅絲帶紅")
    RED_RED,
    ;
    @I18nEnum.InnerI18n(en_us = "Present", zh_cn = "礼物盒", zh_tw = "禮物盒")
    public final DeferredBlock<Block> present;
    public final DeferredItem<BlockItem> present_item;
    @I18nEnum.InnerI18n(en_us = "Trapped Present", zh_cn = "陷阱礼物盒", zh_tw = "陷阱禮物盒")
    public final DeferredBlock<Block> trapped_present;
    public final DeferredItem<BlockItem> trapped_present_item;

    ModPresents() {
        String name = name().toLowerCase(Locale.ROOT) + "_present";
        if (name().equals("DEFAULT")) name = "present";
        present = register(name, BlockBehaviour.Properties.of().noLootTable());
        present_item = register(name, present);
        String trapped = "trapped_" + name;
        trapped_present = register(trapped, BlockBehaviour.Properties.of().noLootTable());
        trapped_present_item = register(trapped, trapped_present);

    }
}
