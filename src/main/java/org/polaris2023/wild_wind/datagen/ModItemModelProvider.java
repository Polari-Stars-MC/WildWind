package org.polaris2023.wild_wind.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModItems;

import java.util.Collection;
import java.util.function.Consumer;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, WildWindMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        isSame(ModItems.entry(), DeferredSpawnEggItem.class, holder -> {
            spawnEggItem(holder.get());
//            withExistingParent(holder.getId().getPath(), mcLoc("item/template_spawn_egg"));
        });

    }

    @SuppressWarnings("unchecked")
    public <T extends Item> void isSame(Collection<DeferredHolder<Item, ? extends Item>> items, Class<DeferredSpawnEggItem> tClass, Consumer<DeferredHolder<Item, T>> consumer) {
        items
                .stream()
                .filter(holder -> holder.get().getClass().equals(tClass))
                .map(holder -> (DeferredHolder<Item, T>) holder)
                .forEach(consumer);
    }
}
