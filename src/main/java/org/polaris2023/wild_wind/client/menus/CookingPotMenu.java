package org.polaris2023.wild_wind.client.menus;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.polaris2023.wild_wind.client.container.CookingPotContainer;
import org.polaris2023.wild_wind.common.block.entity.CookingPotBlockEntity;
import org.polaris2023.wild_wind.common.init.ModMenuTypes;

public class CookingPotMenu extends AbstractContainerMenu implements ContainerListener {
    private final Level level;
    private final ContainerData data;
    private final CraftingContainer container;

    public CookingPotMenu(int containerId, Inventory playInv, FriendlyByteBuf buf) {
        this(containerId, playInv, playInv.player.level().getBlockEntity(buf.readBlockPos()), new SimpleContainerData(6));
    }

    public CookingPotMenu(int containerId, Inventory playInv, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.COOKING_POT.get(), containerId);
        checkContainerSize(playInv, 1);
        this.level = playInv.player.level();
        this.data = data;
        container = new CookingPotContainer(this, 2, 2);
        CookingPotBlockEntity cookingPot = (CookingPotBlockEntity) entity;


        addDataSlots(data);
    }

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player inventory and the other inventory(s).
     *
     * @param player
     * @param index
     */
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return null;
    }

    /**
     * Determines whether supplied player can use this container
     *
     * @param player
     */
    @Override
    public boolean stillValid(Player player) {
        return false;
    }

    /**
     * Sends the contents of an inventory slot to the client-side Container. This doesn't have to match the actual contents of that slot.
     *
     * @param containerToSend
     * @param dataSlotIndex
     * @param stack
     */
    @Override
    public void slotChanged(AbstractContainerMenu containerToSend, int dataSlotIndex, ItemStack stack) {

    }

    @Override
    public void dataChanged(AbstractContainerMenu containerMenu, int dataSlotIndex, int value) {

    }
}
