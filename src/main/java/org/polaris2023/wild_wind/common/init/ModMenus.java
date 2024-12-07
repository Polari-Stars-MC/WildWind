package org.polaris2023.wild_wind.common.init;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.wild_wind.client.menus.CookingPotMenu;

import static org.polaris2023.wild_wind.common.init.ModInitializer.MENU_TYPES;

public class ModMenus {
    public static final DeferredHolder<MenuType<?>, MenuType<CookingPotMenu>> COOKING_POT =
            registerMenuType("cooking_pot", CookingPotMenu::new);
    private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(
            String name,
            IContainerFactory<T> factory
    ) {
        return MENU_TYPES.register(name, () -> IMenuTypeExtension.create(factory));
    }
}
