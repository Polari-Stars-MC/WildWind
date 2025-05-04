package org.polaris2023.wild_wind.common.event.game;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModBlocks;

@EventBusSubscriber(modid = WildWindMod.MOD_ID)
public class CauldronEvent {
    @SubscribeEvent
    public static void run(PlayerInteractEvent.RightClickBlock event) {
        ItemStack stack = event.getItemStack();
        BlockState blockstate = event.getLevel().getBlockState(event.getPos());
        if(blockstate.is(Blocks.WATER_CAULDRON)) {
            washingDyedItem(stack, blockstate, event, ItemTags.WOOL, ModBlocks.WOOL.get().asItem());
            washingDyedItem(stack, blockstate, event, ItemTags.create(ResourceLocation.parse("c:glazed_terracottas")), ModBlocks.GLAZED_TERRACOTTA.get().asItem());
        }
    }

    static void washingDyedItem(ItemStack stack, BlockState blockstate, PlayerInteractEvent.RightClickBlock event, TagKey<Item> tag, Item item) {
        if(stack.is(tag) && !stack.is(item)) {
            int level = blockstate.getValue(LayeredCauldronBlock.LEVEL);
            if (blockstate.getBlock() instanceof LayeredCauldronBlock && level > 0) {
                LayeredCauldronBlock.lowerFillLevel(blockstate, event.getLevel(), event.getPos());
                event.getItemStack().consume(1, event.getEntity());
                if (event.getEntity().getInventory().getFreeSlot() == -1) {
                    event.getEntity().drop(new ItemStack(item), false);
                } else {
                    event.getEntity().addItem(new ItemStack(item));
                }
                event.setCanceled(true);
            }
        }
    }
}
