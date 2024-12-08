package org.polaris2023.wild_wind.datagen.custom;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.polaris2023.wild_wind.util.interfaces.IModel;

import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 减去错误的检查的模型json生成检索器， 将会出现 BasicItem BasicBlockItem BasicSpawnEggItem三个注解
 */
public class ItemModelProvider implements DataProvider, IModel<ItemModelProvider> {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().setLenient().create();
    private static final Map<String, String> SPAWN_EGG = Map.of("parent", "minecraft:item/template_spawn_egg");
    private PackOutput output;
    private String modid;

    private Path assetsDir;

    private static final ConcurrentHashMap<ResourceLocation, Object> ITEM_MODELS =
            new ConcurrentHashMap<>();// object is Bean or map， by gson
    private ItemModelProvider basicItem(Item item) {
        ResourceLocation key = BuiltInRegistries.ITEM.getKey(item);
        ITEM_MODELS.put(key, Map.of("parent", "minecraft:item/generated", "textures", Map.of(
                "layer0", key.getNamespace() + ":item/" + key.getPath()
        )));
        return this;
    }

    private ItemModelProvider basicBlockItem(Block block) {
        ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block);
        ITEM_MODELS.put(key, Map.of("parent", key.getNamespace() + ":block/" + key.getPath()));
        return this;
    }



    private ItemModelProvider spawnEggItem(Item item) {
        ITEM_MODELS.put(BuiltInRegistries.ITEM.getKey(item), SPAWN_EGG);
        return this;
    }

    @Override
    public ItemModelProvider setOutput(PackOutput output) {
        this.output = output;
        assetsDir = output
                .getOutputFolder(PackOutput.Target.RESOURCE_PACK);
        return this;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        init();
        CompletableFuture<?>[] futures = new CompletableFuture[ITEM_MODELS.size()];
        int i = 0;
        for (Map.Entry<ResourceLocation, Object> entry : ITEM_MODELS.entrySet()) {
            ResourceLocation key = entry.getKey();
            Object object = entry.getValue();
            Path itemModel = assetsDir.resolve(key.getNamespace()).resolve("models").resolve("item");
            JsonElement jsonTree = GSON.toJsonTree(object);
            futures[i] = DataProvider.saveStable(output, jsonTree, itemModel);
            i++;
        }
        return CompletableFuture.allOf(futures);
    }

    @Override
    public String getName() {
        return "Model Provider by" + modid;
    }

    @Override
    public ItemModelProvider setModid(String modid) {
        this.modid = modid;
        return this;
    }
}
