package org.polaris2023.wild_wind.common.block.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import org.polaris2023.wild_wind.common.block.PresentBlock;
import org.polaris2023.wild_wind.common.init.ModComponents;

/**
 * @author : baka4n
 * {@code @Date : 2025/03/14 19:45:10}
 */
public class PresentBlockItem extends BlockItem {
    public PresentBlockItem(PresentBlock block, Properties properties) {
        super(block, properties);

    }

    public int getColor(int tintIndex, ItemStack stack) {
        int box = stack.getOrDefault(ModComponents.PRESENT_BOX_COLOR, 12030298);
        int ribbon = stack.getOrDefault(ModComponents.PRESENT_RIBBON_COLOR, 12030298);
        return tintIndex == 0 ? box : ribbon;
    }

    @Override
    public Component getName(ItemStack stack) {
        int box = stack.getOrDefault(ModComponents.PRESENT_BOX_COLOR, 12030298);
        int ribbon = stack.getOrDefault(ModComponents.PRESENT_RIBBON_COLOR, 12030298);
        return Component.translatable("%s.%s.%s".formatted(getDescriptionId(),box, ribbon));

    }
}
