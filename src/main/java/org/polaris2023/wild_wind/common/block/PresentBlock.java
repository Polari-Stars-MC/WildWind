package org.polaris2023.wild_wind.common.block;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import org.polaris2023.wild_wind.client.ModTranslateKey;
import org.polaris2023.wild_wind.common.init.ModComponents;

import java.util.List;

/**
* @author : baka4n
* {@code @Date : 2025/03/14 22:54:29}
*/public class PresentBlock extends Block {
    public static final List<String> COLORS = List.of(
            "natural", "white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "light_gray", "cyan", "purple", "blue", "brown", "green", "red", "black"
    );
    public PresentBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(
                Component.empty()
                        .append(ModTranslateKey.PRESENT_COLOR_TOOLTIP_RIBBON.translatable())
                        .append(stack.getOrDefault(ModComponents.PRESENT_RIBBON.get(), "null"))
        );
        tooltipComponents.add(
                Component.empty()
                        .append(ModTranslateKey.PRESENT_COLOR_TOOLTIP_BOX.translatable())
                        .append(stack.getOrDefault(ModComponents.PRESENT_BOX.get(), "null"))
        );

    }
}
