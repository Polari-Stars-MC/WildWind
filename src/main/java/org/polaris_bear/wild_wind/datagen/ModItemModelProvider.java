package org.polaris_bear.wild_wind.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris_bear.wild_wind.WildWindMod;
import org.polaris_bear.wild_wind.common.init.ModItems;

import java.util.Collection;
import java.util.function.Consumer;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, WildWindMod.MOD_ID, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void registerModels() {
//        ModItems.ITEMS
//                .getEntries()
//                .stream()
//                .filter(holder -> holder.get() instanceof DeferredSpawnEggItem)
//                .map(holder -> (DeferredHolder<Item, DeferredSpawnEggItem>) holder).forEach(holder -> withExistingParent(holder.getId().getPath(), mcLoc("item/template_spawn_egg")));
        isSame(ModItems.ITEMS.getEntries(), DeferredSpawnEggItem.class, holder -> {
            withExistingParent(holder.getId().getPath(), mcLoc("item/template_spawn_egg"));
        });

//        withExistingParent(ModItems.FIREFLY_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
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
