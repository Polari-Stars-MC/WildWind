package org.polaris_bear.wild_wind.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris_bear.wild_wind.WildWindMod;
import org.polaris_bear.wild_wind.common.init.ModItems;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, WildWindMod.MOD_ID, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void registerModels() {
        ModItems.ITEMS
                .getEntries()
                .stream()
                .filter(holder -> holder.get() instanceof DeferredSpawnEggItem)
                .map(holder -> (DeferredHolder<Item, DeferredSpawnEggItem>) holder).forEach(holder -> withExistingParent(holder.getId().getPath(), mcLoc("item/template_spawn_egg")));


//        withExistingParent(ModItems.FIREFLY_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }
}
