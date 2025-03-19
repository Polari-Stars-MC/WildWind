package org.polaris2023.wild_wind.common.event;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
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
        if(stack.is(ItemTags.WOOL) && !stack.is(ModBlocks.WOOL.asItem())) {
            BlockState blockstate = event.getLevel().getBlockState(event.getPos());
            FluidState fluidstate = event.getLevel().getFluidState(event.getPos());
            int level;
            try {
                level = blockstate.getValue(LayeredCauldronBlock.LEVEL);
            } catch (Exception e) {
                event.setCanceled(true);
                return;
            }
            if (blockstate.getBlock() instanceof LayeredCauldronBlock && level > 0) {
                LayeredCauldronBlock.lowerFillLevel(blockstate, event.getLevel(), event.getPos());
                event.getItemStack().consume(1, event.getEntity());
                event.getEntity().addItem(new ItemStack(ModBlocks.WOOL));
                if (event.getEntity().getInventory().getFreeSlot() == -1) {
                    event.getEntity().drop(new ItemStack(ModBlocks.WOOL), false);
                } else {
                    event.getEntity().addItem(new ItemStack(ModBlocks.WOOL));
                }
                event.setCanceled(true);
            }
        }
    }
}
