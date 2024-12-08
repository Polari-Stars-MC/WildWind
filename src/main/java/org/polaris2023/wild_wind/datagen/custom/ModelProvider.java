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
public class ModelProvider implements DataProvider, IModel<ModelProvider> {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().setLenient().create();
    private static final Map<String, String> SPAWN_EGG = Map.of("parent", "minecraft:item/template_spawn_egg");
    private PackOutput output;
    private String modid;

    private Path assetsDir;

    private static final ConcurrentHashMap<ResourceLocation, Object> ITEM_MODELS =
            new ConcurrentHashMap<>();// object is Bean or map， by gson
    private static final ConcurrentHashMap<ResourceLocation, Object> BLOCK_MODELS =
            new ConcurrentHashMap<>();
    private ModelProvider basicItem(Item item) {
        ResourceLocation key = BuiltInRegistries.ITEM.getKey(item);
        ITEM_MODELS.put(key, Map.of("parent", "minecraft:item/generated", "textures", Map.of(
                "layer0", key.withPrefix("item/").toString()
        )));
        return this;
    }

    private ModelProvider basicBlockItem(Block block) {
        ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block);
        ITEM_MODELS.put(key, Map.of("parent", key.withPrefix("block/").toString()));
        return this;
    }



    private ModelProvider spawnEggItem(Item item) {
        ITEM_MODELS.put(BuiltInRegistries.ITEM.getKey(item), SPAWN_EGG);
        return this;
    }

    private ModelProvider cubeAll(Block block) {
        ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block);
        BLOCK_MODELS.put(key, Map.of(
                "parent", "minecraft:block/cube_all",
                "textures", Map.of(
                        "all", key.withPrefix("block/").toString()
                )
        ));
        return this;
    }

    private ModelProvider slab(Block block) {

        ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block);
        ResourceLocation blockKey = key.withPrefix("block/");
        BLOCK_MODELS.put(key, Map.of(
                "parent", "minecraft:block/slab",
                "textures", Map.of(
                      "bottom", blockKey.toString(),
                      "side", blockKey.toString(),
                      "top", blockKey.toString()
                )
        ));
        BLOCK_MODELS.put(key.withSuffix("_top"), Map.of(
                "parent", "minecraft:block/slab_top",
                "textures", Map.of(
                      "bottom", blockKey.toString(),
                      "side", blockKey.toString(),
                      "top", blockKey.toString()
                )
        ));

        return this;
    }

    @Override
    public ModelProvider setOutput(PackOutput output) {
        this.output = output;
        assetsDir = output
                .getOutputFolder(PackOutput.Target.RESOURCE_PACK);
        return this;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        init();
        CompletableFuture<?>[] futures = new CompletableFuture[ITEM_MODELS.size() + BLOCK_MODELS.size()];
        int i = 0;
        for (Map.Entry<ResourceLocation, Object> entry : ITEM_MODELS.entrySet()) {
            ResourceLocation key = entry.getKey();
            Object object = entry.getValue();
            Path itemModel = assetsDir.resolve(key.getNamespace()).resolve("models").resolve("item");
            JsonElement jsonTree = GSON.toJsonTree(object);
            futures[i] = DataProvider.saveStable(output, jsonTree, itemModel);
            i++;
        }
        for (Map.Entry<ResourceLocation, Object> entry : BLOCK_MODELS.entrySet()) {
            ResourceLocation key = entry.getKey();
            Object object = entry.getValue();
            Path blockModel = assetsDir.resolve(key.getNamespace()).resolve("models").resolve("block");
            JsonElement jsonTree = GSON.toJsonTree(object);
            futures[i] = DataProvider.saveStable(output, jsonTree, blockModel);
            i++;
        }
        return CompletableFuture.allOf(futures);
    }

    @Override
    public String getName() {
        return "Model Provider by" + modid;
    }

    @Override
    public ModelProvider setModid(String modid) {
        this.modid = modid;
        return this;
    }
}
