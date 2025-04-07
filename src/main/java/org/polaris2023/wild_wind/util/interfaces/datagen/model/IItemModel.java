package org.polaris2023.wild_wind.util.interfaces.datagen.model;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import org.polaris2023.wild_wind.datagen.WildWindClientProvider;
import org.polaris2023.wild_wind.util.interfaces.datagen.DatagenClient;

/**
 * @author : baka4n
 * {@code @Date : 2025/03/29 21:14:27}
 */
public interface IItemModel extends DatagenClient {
    default ItemModelBuilder basicBlockLocatedItem(ResourceLocation block) {
        return self().itemModelProvider.getBuilder(block.toString()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", ResourceLocation.fromNamespaceAndPath(block.getNamespace(), "block/" + block.getPath()));
    }

    default ItemModelBuilder basicBlockLocatedItem(Item blockItem) {
        return basicBlockLocatedItem(BuiltInRegistries.ITEM.getKey(blockItem));
    }

    default <T extends ItemLike> ItemModelBuilder basicItem(T like) {
        return self().itemModelProvider.basicItem(like.asItem());
    }

    default <T extends ItemLike> ResourceLocation key(T item) {
        return BuiltInRegistries.ITEM.getKey(item.asItem());
    }

    default <T extends ItemLike> ResourceLocation blockTexture(T item) {
        ResourceLocation name = key(item);
        return ResourceLocation.fromNamespaceAndPath(name.getNamespace(), "item/" + name.getPath());
    }
}
