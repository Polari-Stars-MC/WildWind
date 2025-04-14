package org.polaris2023.wild_wind.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.polaris2023.annotation.enums.TagType;
import org.polaris2023.annotation.handler.TagHandler;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author : baka4n
 * {@code @Date : 2025/03/20 21:00:28}
 */
public class WildWindServerProvider implements DataProvider {
    public final ConcurrentMap<Class<?>, IntrinsicHolderTagsProvider<?>> tagsProviders = new ConcurrentHashMap<>();
    private final String modid;
    public WildWindServerProvider(PackOutput output,
                                  String modid,
                                  CompletableFuture<HolderLookup.Provider> provider,
                                  ExistingFileHelper helper) {
        this.modid = modid;
        BlockTagsProvider value = new BlockTagsProvider(output, provider, modid, helper) {
            @Override
            protected void addTags(HolderLookup.Provider provider) {
                block(this);
            }
        };
        tagsProviders.put(Block.class, value);
        tagsProviders.put(Item.class, new ItemTagsProvider(output, provider, value.contentsGetter(), modid, helper) {
            @Override
            protected void addTags(HolderLookup.Provider provider) {
                item(this);
            }
        });

    }

    @TagHandler(TagType.Block)
    public void block(BlockTagsProvider provider) {

    }//只有这俩个是手动生成其他都说按需要生成
    @TagHandler(TagType.Item)
    public void item(ItemTagsProvider provider) {

    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        CompletableFuture<?>[] futures = new CompletableFuture[tagsProviders.size()];
        int i = 0;
        for (Map.Entry<Class<?>, IntrinsicHolderTagsProvider<?>> entry : tagsProviders.entrySet()) {
            IntrinsicHolderTagsProvider<?> provider = entry.getValue();
            futures[i] = provider.run(cachedOutput);
            i++;
        }
        return CompletableFuture.allOf(futures);
    }

    @Override
    public String getName() {
        return "Server Provider with" + modid;
    }
}
