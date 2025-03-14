package org.polaris2023.utils;

import org.polaris2023.processor.InitProcessor;

import javax.annotation.processing.Filer;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;

public enum Codes {
    ModelProvider(
            """
            package %%package%%;
            
            import com.google.gson.Gson;
            import com.google.gson.GsonBuilder;
            import com.google.gson.JsonElement;
            
            import net.minecraft.core.registries.BuiltInRegistries;
            import net.minecraft.data.CachedOutput;
            import net.minecraft.data.DataProvider;
            import net.minecraft.data.PackOutput;
            import net.minecraft.resources.ResourceLocation;
            import net.minecraft.world.item.BlockItem;
            import net.minecraft.world.item.Item;
            import net.minecraft.world.item.SpawnEggItem;
            import net.minecraft.world.level.block.Block;
            import net.minecraft.world.level.block.SlabBlock;
            import net.minecraft.world.level.block.StairBlock;
            
            import net.neoforged.neoforge.registries.DeferredHolder;
            
            import org.polaris2023.wild_wind.common.init.ModInitializer;
            import org.polaris2023.wild_wind.util.interfaces.IModel;
            
            import java.nio.file.Path;
            import java.util.List;
            import java.util.HashMap;
            import java.util.Map;
            import java.util.concurrent.CompletableFuture;
            import java.util.concurrent.ConcurrentHashMap;
            import java.util.function.Supplier;
            
            public final class %%classname%% implements DataProvider, IModel<%%classname%%> {
                private static final Gson GSON = new GsonBuilder().setPrettyPrinting().setLenient().create();
                private static final Map<String, String> SPAWN_EGG = Map.of("parent", "minecraft:item/template_spawn_egg");
                private PackOutput output;
                private String modid;
                private Path assetsDir;
                private final ConcurrentHashMap<ResourceLocation, Object> MODELS =
                    new ConcurrentHashMap<>();// object is Bean or map， by gson
                private final ConcurrentHashMap<ResourceLocation, Object> BLOCKSTATES =
                    new ConcurrentHashMap<>();
            
                private <T extends Item> %%classname%% basicItem(Supplier<T> item) {
                    ResourceLocation key = BuiltInRegistries.ITEM.getKey(item.get()).withPrefix("item/");
                    MODELS.put(key, Map.of("parent", "minecraft:item/generated", "textures", Map.of(
                                        "layer0", key.toString()
                    )));
                    return this;
                }
            
                private <T extends Item> %%classname%% basicItem(Supplier<T> item, String prefix) {
                    MODELS.put(BuiltInRegistries.ITEM.getKey(item.get()).withPrefix("item/" + prefix + "_"), Map.of("parent", "minecraft:item/generated", "textures", Map.of(
                                        "layer0", BuiltInRegistries.ITEM.getKey(item.get()).withPrefix("item/").toString()
                    )));
                    return this;
                }
            
                private <T extends Item> %%classname%% basicItem(Supplier<T> item, Map<String, Object> display) {
                    ResourceLocation key = BuiltInRegistries.ITEM.getKey(item.get()).withPrefix("item/");
                    MODELS.put(key, Map.of("parent", "minecraft:item/generated", "textures", Map.of(
                        "layer0", key.toString()
                    ), "display", display));
                    return this;
                }
            
                private <T extends Item> %%classname%% basicItem(Supplier<T> item, Map<String, Object> display, String prefix) {
                    MODELS.put(BuiltInRegistries.ITEM.getKey(item.get()).withPrefix("item/" + prefix + "_"), Map.of("parent", "minecraft:item/generated", "textures", Map.of(
                        "layer0", BuiltInRegistries.ITEM.getKey(item.get()).withPrefix("item/").toString()
                    ), "display", display));
                    return this;
                }
            
                private <T extends Item> %%classname%% basicItem(Supplier<T> item, Map<String, Object> display, List<Object> overrides) {
                    ResourceLocation key = BuiltInRegistries.ITEM.getKey(item.get()).withPrefix("item/");
                    MODELS.put(key, Map.of("parent", "minecraft:item/generated", "textures", Map.of(
                        "layer0", key.toString()
                    ), "display", display, "overrides", overrides));
                    return this;
                }
            
                private <T extends Item> %%classname%% basicItem(Supplier<T> item, Map<String, Object> display, List<Object> overrides, String prefix) {
                    MODELS.put(BuiltInRegistries.ITEM.getKey(item.get()).withPrefix("item/" + prefix + "_"), Map.of("parent", "minecraft:item/generated", "textures", Map.of(
                        "layer0", BuiltInRegistries.ITEM.getKey(item.get()).withPrefix("item/").toString()
                    ), "display", display, "overrides", overrides));
                    return this;
                }
            
                private <T extends Item> %%classname%% basicBlockLocatedItem(Supplier<T> item) {
                    ResourceLocation key = BuiltInRegistries.ITEM.getKey(item.get()).withPrefix("item/");
                    MODELS.put(key, Map.of("parent", "minecraft:item/generated", "textures", Map.of(
                                        "layer0", BuiltInRegistries.ITEM.getKey(item.get()).withPrefix("block/").toString()
                    )));
                    return this;
                }
            
                private <T extends BlockItem> %%classname%% basicParentBlockItem(Supplier<T> blockItem, ResourceLocation inventory) {
                    ResourceLocation key = BuiltInRegistries.ITEM.getKey(blockItem.get());
                    ResourceLocation itemKey = key.withPrefix("item/");
                    MODELS.put(itemKey, Map.of("parent", inventory.toString()));
                    return this;
                }
            
                private <T extends BlockItem> %%classname%% basicBlockItem(Supplier<T> blockItem) {
                    ResourceLocation key = BuiltInRegistries.ITEM.getKey(blockItem.get());
                    ResourceLocation blockKey = key.withPrefix("block/");
                    ResourceLocation itemKey = key.withPrefix("item/");
                    MODELS.put(itemKey, Map.of("parent", blockKey.toString()));
                    return this;
                }
                private <T extends BlockItem> %%classname%% basicBlockItemWithSuffix(Supplier<T> blockItem, String suffix) {
                    ResourceLocation key = BuiltInRegistries.ITEM.getKey(blockItem.get());
                    ResourceLocation blockKey = key.withPrefix("block/");
                    ResourceLocation itemKey = key.withPrefix("item/");
                    MODELS.put(itemKey, Map.of("parent", blockKey.toString() + suffix));
                    return this;
                }
                private <T extends Block> %%classname%% allDoorBlock(Supplier<T> block) {
                    ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block.get());
                    ResourceLocation blockKey = key.withPrefix("block/");
                    ResourceLocation doorBottom = replace(blockKey, "door_bottom");
                    ResourceLocation doorTop = replace(blockKey, "door_top");
                    Map<String, ResourceLocation> locations = new HashMap<>();
                    for (String s : List.of(
                    "door_bottom_left", "door_bottom_left_open", "door_bottom_right", "door_bottom_right_open",
                    "door_top_left", "door_top_left_open", "door_top_right", "door_top_right_open"
                    )) {
                        ResourceLocation replace = replace(blockKey, s);
                        locations.put(s, replace);
                        MODELS.put(replace, Map.of(
                            "parent", "minecraft:block/" + s,
                            "textures", Map.of(
                                "bottom", doorBottom.toString(),
                                "top", doorTop.toString()
                            )
                        ));
                    }
                    Map<String, Object> tMap = new HashMap<>();
                    tMap.put("facing=east,half=lower,hinge=left,open=false", model(locations.get("door_bottom_left"), null, null, null, false));
                    tMap.put("facing=east,half=lower,hinge=left,open=true", model(locations.get("door_bottom_left_open"), null, 90, null, false));
                    tMap.put("facing=east,half=lower,hinge=right,open=false", model(locations.get("door_bottom_right"), null, null, null, false));
                    tMap.put("facing=east,half=lower,hinge=right,open=true", model(locations.get("door_bottom_right_open"), null, 270, null, false));
                    tMap.put("facing=east,half=upper,hinge=left,open=false", model(locations.get("door_top_left"), null, null, null, false));
                    tMap.put("facing=east,half=upper,hinge=left,open=true", model(locations.get("door_top_left_open"), null, 90, null, false));
                    tMap.put("facing=east,half=upper,hinge=right,open=false", model(locations.get("door_top_right"), null, null, null, false));
                    tMap.put("facing=east,half=upper,hinge=right,open=true", model(locations.get("door_top_right_open"), null, 270, null, false));
                    tMap.put("facing=north,half=lower,hinge=left,open=false", model(locations.get("door_bottom_left"), null, 270, null, false));
                    tMap.put("facing=north,half=lower,hinge=left,open=true", model(locations.get("door_bottom_left_open"), null, null, null, false));
                    tMap.put("facing=north,half=lower,hinge=right,open=false", model(locations.get("door_bottom_right"), null, 270, null, false));
                    tMap.put("facing=north,half=lower,hinge=right,open=true", model(locations.get("door_bottom_right_open"), null, 180, null, false));
                    tMap.put("facing=north,half=upper,hinge=left,open=false", model(locations.get("door_top_left"), null, 270, null, false));
                    tMap.put("facing=north,half=upper,hinge=left,open=true", model(locations.get("door_top_left_open"), null, null, null, false));
                    tMap.put("facing=north,half=upper,hinge=right,open=false", model(locations.get("door_top_right"), null, 270, null, false));
                    tMap.put("facing=north,half=upper,hinge=right,open=true", model(locations.get("door_top_right_open"), null, 180, null, false));
                    tMap.put("facing=south,half=lower,hinge=left,open=false", model(locations.get("door_bottom_left"), null, 90, null, false));
                    tMap.put("facing=south,half=lower,hinge=left,open=true", model(locations.get("door_bottom_left_open"), null, 180, null, false));
                    tMap.put("facing=south,half=lower,hinge=right,open=false", model(locations.get("door_bottom_right"), null, 90, null, false));
                    tMap.put("facing=south,half=lower,hinge=right,open=true", model(locations.get("door_bottom_right_open"), null, null, null, false));
                    tMap.put("facing=south,half=upper,hinge=left,open=false", model(locations.get("door_top_left"), null, 90, null, false));
                    tMap.put("facing=south,half=upper,hinge=left,open=true", model(locations.get("door_top_left_open"), null, 180, null, false));
                    tMap.put("facing=south,half=upper,hinge=right,open=false", model(locations.get("door_top_right"), null, 90, null, false));
                    tMap.put("facing=south,half=upper,hinge=right,open=true", model(locations.get("door_top_right_open"), null, null, null, false));
                    tMap.put("facing=west,half=lower,hinge=left,open=false", model(locations.get("door_bottom_left"), null, 180, null, false));
                    tMap.put("facing=west,half=lower,hinge=left,open=true", model(locations.get("door_bottom_left_open"), null, 270, null, false));
                    tMap.put("facing=west,half=lower,hinge=right,open=false", model(locations.get("door_bottom_right"), null, 180, null, false));
                    tMap.put("facing=west,half=lower,hinge=right,open=true", model(locations.get("door_bottom_right_open"), null, 90, null, false));
                    tMap.put("facing=west,half=upper,hinge=left,open=false", model(locations.get("door_top_left"), null, 180, null, false));
                    tMap.put("facing=west,half=upper,hinge=left,open=true", model(locations.get("door_top_left_open"), null, 270, null, false));
                    tMap.put("facing=west,half=upper,hinge=right,open=false", model(locations.get("door_top_right"), null, 180, null, false));
                    tMap.put("facing=west,half=upper,hinge=right,open=true", model(locations.get("door_top_right_open"), null, 90, null, false));
                    BLOCKSTATES.put(key, Map.of("variants", tMap));
                    basicItem(() -> block.get().asItem());
                    ResourceLocation trapdoor = replace(key, "trapdoor");
                    ResourceLocation blockTrapdoor = replace(blockKey, "trapdoor");
                    ResourceLocation itemTrapdoor = trapdoor.withPrefix("item/");
                    ResourceLocation trapdoorBottom = replace(blockKey, "trapdoor_bottom");
                    ResourceLocation trapdoorTop = replace(blockKey, "trapdoor_top");
                    ResourceLocation trapdoorOpen = replace(blockKey, "trapdoor_open");
                    MODELS.put(trapdoorBottom, Map.of("parent", "minecraft:block/template_orientable_trapdoor_bottom","textures", Map.of("texture", blockTrapdoor.toString())));
                    MODELS.put(trapdoorTop, Map.of("parent", "minecraft:block/template_orientable_trapdoor_top","textures", Map.of("texture", blockTrapdoor.toString())));
                    MODELS.put(trapdoorOpen, Map.of("parent", "minecraft:block/template_orientable_trapdoor_open","textures", Map.of("texture", blockTrapdoor.toString())));
                    MODELS.put(itemTrapdoor, Map.of("parent", trapdoorBottom.toString()));
                    Map<String, Object> ttMap = new HashMap<>();
                    ttMap.put("facing=east,half=bottom,open=false", model(trapdoorBottom, null, 90, null, false));
                    ttMap.put("facing=east,half=bottom,open=true", model(trapdoorOpen, null, 90, null, false));
                    ttMap.put("facing=east,half=top,open=false", model(trapdoorTop, null, 90, null, false));
                    ttMap.put("facing=east,half=top,open=true", model(trapdoorOpen, 180, 270, null, false));
                    ttMap.put("facing=north,half=bottom,open=false", model(trapdoorBottom, null, null, null, false));
                    ttMap.put("facing=north,half=bottom,open=true", model(trapdoorOpen, null, null, null, false));
                    ttMap.put("facing=north,half=top,open=false", model(trapdoorTop, null, null, null, false));
                    ttMap.put("facing=north,half=top,open=true", model(trapdoorOpen, 180, 180, null, false));
                    ttMap.put("facing=south,half=bottom,open=false", model(trapdoorBottom, null, 180, null, false));
                    ttMap.put("facing=south,half=bottom,open=true", model(trapdoorOpen, null, 180, null, false));
                    ttMap.put("facing=south,half=top,open=false", model(trapdoorTop, null, 180, null, false));
                    ttMap.put("facing=south,half=top,open=true", model(trapdoorOpen, 180, 0, null, false));
                    ttMap.put("facing=west,half=bottom,open=false", model(trapdoorBottom, null, 270, null, false));
                    ttMap.put("facing=west,half=bottom,open=true", model(trapdoorOpen, null, 270, null, false));
                    ttMap.put("facing=west,half=top,open=false", model(trapdoorTop, null, 270, null, false));
                    ttMap.put("facing=west,half=top,open=true", model(trapdoorOpen, 180, 90, null, false));
                    BLOCKSTATES.put(trapdoor, Map.of("variants", ttMap));
                    return this;
                }
                private <T extends Block> %%classname%% allSignBlock(Supplier<T> block) {
                    ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block.get());//modid:xxx_sign
                    ResourceLocation blockKey = key.withPrefix("block/");//modid:block/xxx_sign
                    ResourceLocation hangingKey = replace(key, "hanging_sign");//modid:block/xxx_hanging_sign
                    ResourceLocation hangingBlockKey = replace(blockKey, "hanging_sign");//modid:block/xxx_hanging_sign
                    ResourceLocation wallHangingKey = replace(key, "wall_hanging_sign");
                    ResourceLocation wallKey = replace(key, "wall_sign");
                    ResourceLocation planks = planks(blockKey);
                    ResourceLocation strippedLog = replace(key, "log").withPrefix("block/stripped_");
                    MODELS.put(blockKey, Map.of("textures", Map.of("particle", planks.toString())));
                    MODELS.put(hangingBlockKey, Map.of("textures", Map.of("particle", strippedLog.toString())));
                    basicItem(() -> block.get().asItem());
                    basicItem(() -> BuiltInRegistries.BLOCK.get(replace(key, "hanging_sign")).asItem());
                    BLOCKSTATES.put(key, Map.of("variants",  Map.of("", model(blockKey, null, null, null, false))));
                    BLOCKSTATES.put(hangingKey, Map.of("variants", Map.of("", model(hangingBlockKey, null, null, null, false))));
                    BLOCKSTATES.put(wallKey, Map.of("variants", Map.of("", model(blockKey, null, null, null, false))));
                    BLOCKSTATES.put(wallHangingKey, Map.of("variants", Map.of("", model(hangingBlockKey, null, null, null, false))));
                    return this;
                }
            
                private <T extends Block> %%classname%% cubeColumn(Supplier<T> block, String end, String side, boolean isItem, boolean isHorizontal, boolean isSuffix) {
                    ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block.get());
                    ResourceLocation blockKey = key.withPrefix("block/");
                    MODELS.put((isHorizontal && isSuffix) ? blockKey.withSuffix("_horizontal") : blockKey, Map.of(
                        "parent", "minecraft:block/cube_column" + (isHorizontal ? "_horizontal" : ""),
                        "textures", Map.of(
                            "end", end,
                            "side", side
                        )
                    ));
                    BLOCKSTATES.put(key, Map.of(
                        "variants", Map.of("", model(blockKey, null, null, null, false))
                    ));
                    if(isItem)
                        basicBlockItem((Supplier<? extends BlockItem>) () -> (BlockItem) block.get().asItem());
                    return this;
                }
            
                private <T extends Block> %%classname%% cubeAll(Supplier<T> block, String texture, String renderType, boolean isItem) {
                    ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block.get());
                    ResourceLocation blockKey = key.withPrefix("block/");
                    Map<String, Object> tMap = new HashMap<>();
                    tMap.put("parent", "minecraft:block/cube_all");
                    tMap.put("textures", Map.of(
                            "all", texture.isEmpty() ? blockKey.toString() : texture
                        ));
                    if(!renderType.isEmpty()) tMap.put("render_type", renderType);
                    MODELS.put(blockKey, tMap);
                    BLOCKSTATES.put(key, Map.of(
                        "variants", Map.of("", model(blockKey, null, null, null, false))
                    ));
                    if(isItem)
                        basicBlockItem((Supplier<? extends BlockItem>) () -> (BlockItem) block.get().asItem());
                    return this;
                }
                private Map<String, Object> model(ResourceLocation key, Number x, Number y, Number z, boolean uvLock) {
                    Map<String, Object> tMap = new HashMap<>();
                    tMap.put("model", key.toString());
                    if (x != null) tMap.put("x", x);
                    if (y != null) tMap.put("y", y);
                    if (z != null) tMap.put("z", z);
                    if (uvLock) tMap.put("uvlock", uvLock);
                    return tMap;
                }
            
                private <T extends Block> %%classname%% allWoodBlock(Supplier<T> block) {
                    ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block.get());//modid:xxx_planks
                    if (!key.getPath().contains("planks")) {
                        key = planks(key);
                        ResourceLocation finalKey = key;
                        cubeAll(() -> BuiltInRegistries.BLOCK.get(finalKey), "", "", true);
                    } else {
                        cubeAll(block, "", "", true);
                    }
                    ResourceLocation finalKey1 = key;
                    buttonBlock(() -> BuiltInRegistries.BLOCK.get(replace(finalKey1, "button")), "");
                    fenceBlock(() -> BuiltInRegistries.BLOCK.get(replace(finalKey1, "fence")), "", true);
                    fenceGateBlock(() -> BuiltInRegistries.BLOCK.get(replace(finalKey1, "fence_gate")), "", true);
                    slabBlock(() -> BuiltInRegistries.BLOCK.get(replace(finalKey1, "slab")), "", "", "", true);
                    logBlock(() -> BuiltInRegistries.BLOCK.get(replace(finalKey1, "log")), true);
                    logBlock(() -> BuiltInRegistries.BLOCK.get(replace(finalKey1, "log").withPrefix("stripped_")), true);
                    woodBlock(() -> BuiltInRegistries.BLOCK.get(replace(finalKey1, "wood")), true);
                    woodBlock(() -> BuiltInRegistries.BLOCK.get(replace(finalKey1, "wood").withPrefix("stripped_")), true);
                    stairsBlock(() -> BuiltInRegistries.BLOCK.get(replace(finalKey1, "stairs")), "", "", "", true);
                    pressurePlateBlock(() -> BuiltInRegistries.BLOCK.get(replace(finalKey1, "pressure_plate")), "", true);
                    allSignBlock(() -> BuiltInRegistries.BLOCK.get(replace(finalKey1, "sign")));
                    allDoorBlock(() -> BuiltInRegistries.BLOCK.get(replace(finalKey1, "door")));
                    return this;
                }
            
                private <T extends Block> %%classname%% stairsBlock(Supplier<T> block, String bottom, String side, String top, boolean isItem) {
                    ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block.get());
                    ResourceLocation blockKey = key.withPrefix("block/");
                    ResourceLocation inner =  blockKey.withSuffix("_inner");
                    ResourceLocation outer = blockKey.withSuffix("_outer");
                    ResourceLocation planks = planks(blockKey);
                    MODELS.put(blockKey, Map.of(
                        "parent", "minecraft:block/stairs",
                        "textures", Map.of(
                            "bottom", bottom.isEmpty() ?  planks.toString() : bottom,
                            "side", side.isEmpty() ? planks.toString() : side,
                            "top", top.isEmpty() ? planks.toString() : top
                    )));
                    MODELS.put(inner, Map.of(
                        "parent", "minecraft:block/inner_stairs",
                        "textures", Map.of(
                            "bottom", bottom.isEmpty() ? planks.toString() : bottom,
                            "side", side.isEmpty() ? planks.toString() : side,
                            "top", top.isEmpty() ? planks.toString() : top
                    )));
                    MODELS.put(outer, Map.of(
                        "parent", "minecraft:block/outer_stairs",
                        "textures", Map.of(
                            "bottom", bottom.isEmpty() ? planks.toString() : bottom,
                            "side", side.isEmpty() ? planks.toString() : side,
                            "top", top.isEmpty() ? planks.toString() : top
                    )));
                    Map tMap = new HashMap<>();
                    tMap.putAll(Map.of(
                        "facing=east,half=bottom,shape=inner_left", model(inner, null, 270, null, true),
                        "facing=east,half=bottom,shape=inner_right", model(inner, null, null, null, false),
                        "facing=east,half=bottom,shape=outer_left", model(outer, null, 270, null, true),
                        "facing=east,half=bottom,shape=outer_right", model(outer, null, null, null, false),
                        "facing=east,half=bottom,shape=straight", model(blockKey, null, null, null, false)
                    ));
                    tMap.putAll(Map.of(
                        "facing=east,half=top,shape=inner_left", model(inner, 180, null, null, true),
                        "facing=east,half=top,shape=inner_right", model(inner, 180, 90, null, true),
                        "facing=east,half=top,shape=outer_left", model(outer, 180, null, null, true),
                        "facing=east,half=top,shape=outer_right", model(outer, 180, 90, null,  true),
                        "facing=east,half=top,shape=straight", model(blockKey, 180, null, null, true)
                    ));
                    tMap.putAll(Map.of(
                        "facing=north,half=bottom,shape=inner_left", model(inner, null, 180, null, true),
                        "facing=north,half=bottom,shape=inner_right", model(inner, null, 270, null, true),
                        "facing=north,half=bottom,shape=outer_left", model(outer, null, 180, null, true),
                        "facing=north,half=bottom,shape=outer_right", model(outer, null, 270, null, true),
                        "facing=north,half=bottom,shape=straight", model(blockKey, null, 270, null, true)
                    ));
                    tMap.putAll(Map.of(
                        "facing=north,half=top,shape=inner_left", model(inner, 180, 270, null, true),
                        "facing=north,half=top,shape=inner_right", model(inner, 180, null, null, true),
                        "facing=north,half=top,shape=outer_left", model(outer, 180, null, null, true),
                        "facing=north,half=top,shape=outer_right", model(outer, 180, null, null,  true),
                        "facing=north,half=top,shape=straight", model(blockKey, 180, 270, null, true)
                    ));
                    tMap.putAll(Map.of(
                        "facing=south,half=bottom,shape=inner_left", model(inner, null, null, null, false),
                        "facing=south,half=bottom,shape=inner_right", model(inner, null, 90, null, true),
                        "facing=south,half=bottom,shape=outer_left", model(outer, null, null, null, false),
                        "facing=south,half=bottom,shape=outer_right", model(outer, null, 90, null, true),
                        "facing=south,half=bottom,shape=straight", model(blockKey, null, 90, null, true)
                    ));
                    tMap.putAll(Map.of(
                        "facing=south,half=top,shape=inner_left", model(inner, 180, 90, null, true),
                        "facing=south,half=top,shape=inner_right", model(inner, 180, 180, null, true),
                        "facing=south,half=top,shape=outer_left", model(outer, 180, 90, null, true),
                        "facing=south,half=top,shape=outer_right", model(outer, 180, 180, null,  true),
                        "facing=south,half=top,shape=straight", model(blockKey, 180, 90, null, true)
                    ));
                    tMap.putAll(Map.of(
                        "facing=west,half=bottom,shape=inner_left", model(inner, null, 90, null, true),
                        "facing=west,half=bottom,shape=inner_right", model(inner, null, 180, null, true),
                        "facing=west,half=bottom,shape=outer_left", model(outer, null, 90, null, true),
                        "facing=west,half=bottom,shape=outer_right", model(outer, null, 180, null, true),
                        "facing=west,half=bottom,shape=straight", model(blockKey, null, 180, null, true)
                    ));
                    tMap.putAll(Map.of(
                        "facing=west,half=top,shape=inner_left", model(inner, 180, 180, null, true),
                        "facing=west,half=top,shape=inner_right", model(inner, 180, 270, null, true),
                        "facing=west,half=top,shape=outer_left", model(outer, 180, 180, null, true),
                        "facing=west,half=top,shape=outer_right", model(outer, 180, 270, null,  true),
                        "facing=west,half=top,shape=straight", model(blockKey, 180, 180, null, true)
                    ));
                    BLOCKSTATES.put(key, Map.of(
                        "variants", tMap
                    ));
                    if(isItem)
                        basicBlockItem((Supplier<? extends BlockItem>) () -> (BlockItem) block.get().asItem());
                    return this;
                }
                private <T extends Block> %%classname%% fenceGateBlock(Supplier<T> block, String textures, boolean isItem) {
                    ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block.get());
                    ResourceLocation blockKey = key.withPrefix("block/");
                    ResourceLocation itemKey = key.withPrefix("item/");
                    ResourceLocation open = blockKey.withSuffix("_open");
                    ResourceLocation planks = planks(blockKey, "fence_gate");
                    ResourceLocation wall = blockKey.withSuffix("_wall");
                    ResourceLocation wall_open = blockKey.withSuffix("_wall_open");
                    MODELS.put(blockKey, Map.of(
                        "parent", "minecraft:block/template_fence_gate",
                        "textures", Map.of("texture", planks.toString())
                    ));
                    MODELS.put(open, Map.of(
                        "parent", "minecraft:block/template_fence_gate_open",
                        "textures", Map.of("texture", planks.toString())
                    ));
                    MODELS.put(wall, Map.of(
                        "parent", "minecraft:block/template_fence_gate_wall",
                        "textures", Map.of("texture", planks.toString())
                    ));
                    MODELS.put(wall_open, Map.of(
                        "parent", "minecraft:block/template_fence_gate_wall_open",
                        "textures", Map.of("texture", planks.toString())
                    ));
                    Map tMap = new HashMap();
                    tMap.put("facing=east,in_wall=false,open=false", model(blockKey, null, 270, null, true));
                    tMap.put("facing=east,in_wall=false,open=true", model(open, null, 270, null, true));
                    tMap.put("facing=east,in_wall=true,open=false", model(wall, null, 270, null, true));
                    tMap.put("facing=east,in_wall=true,open=true", model(wall_open, null, 270, null, true));
                    tMap.put("facing=north,in_wall=false,open=false", model(blockKey, null, 180, null, true));
                    tMap.put("facing=north,in_wall=false,open=true", model(open, null, 180, null, true));
                    tMap.put("facing=north,in_wall=true,open=false", model(wall, null, 180, null, true));
                    tMap.put("facing=north,in_wall=true,open=true", model(wall_open, null, 180, null, true));
                    tMap.put("facing=south,in_wall=false,open=false", model(blockKey, null, null, null, true));
                    tMap.put("facing=south,in_wall=false,open=true", model(open, null, null, null, true));
                    tMap.put("facing=south,in_wall=true,open=false", model(wall, null, null, null, true));
                    tMap.put("facing=south,in_wall=true,open=true", model(wall_open, null, null, null, true));
                    tMap.put("facing=west,in_wall=false,open=false", model(blockKey, null, 90, null, true));
                    tMap.put("facing=west,in_wall=false,open=true", model(open, null, 90, null, true));
                    tMap.put("facing=west,in_wall=true,open=false", model(wall, null, 90, null, true));
                    tMap.put("facing=west,in_wall=true,open=true", model(wall_open, null, 90, null, true));
                    BLOCKSTATES.put(key, Map.of("variants", tMap));
                    if(isItem)
                        basicBlockItem((Supplier<? extends BlockItem>) () -> (BlockItem) block.get().asItem());
                    return this;
                }
                private <T extends Block> %%classname%% fenceBlock(Supplier<T> block, String textures, boolean isItem) {
                    ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block.get());
                    ResourceLocation blockKey = key.withPrefix("block/");
                    ResourceLocation itemKey = key.withPrefix("item/");
                    ResourceLocation post = blockKey.withSuffix("_post");
                    ResourceLocation side = blockKey.withSuffix("_side");
                    ResourceLocation inventory = blockKey.withSuffix("_inventory");
                    ResourceLocation planks = planks(blockKey);
                    MODELS.put(post, Map.of(
                        "parent", "minecraft:block/fence_post",
                        "textures", Map.of("texture", planks.toString())
                    ));
                    MODELS.put(side, Map.of(
                        "parent", "minecraft:block/fence_side",
                        "textures", Map.of("texture", planks.toString())
                    ));
                    BLOCKSTATES.put(key, Map.of(
                        "multipart", List.of(
                            Map.of("apply", model(post, null, null, null, false)),
                            Map.of("apply", model(side, null, null, null, true), "when", Map.of("north", "true")),
                            Map.of("apply", model(side, null, 90, null, true), "when", Map.of("east", "true")),
                            Map.of("apply", model(side, null, 180, null, true), "when", Map.of("south", "true")),
                            Map.of("apply", model(side, null, 270, null, true), "when", Map.of("west", "true"))
                        )
                    ));
                    if(isItem) {
                        MODELS.put(inventory, Map.of(
                            "parent", "minecraft:block/fence_inventory",
                            "textures", Map.of("texture", planks.toString())
                        ));
                        MODELS.put(itemKey, Map.of("parent", inventory.toString()));
                    }
                    return this;
                }
                private ResourceLocation planks(ResourceLocation blockKey) {
                    String path = blockKey.getPath();
                    path = path.substring(0, path.lastIndexOf("_") + 1) + "planks";
                    return blockKey.withPath(path);
                }
                private ResourceLocation planks(ResourceLocation blockKey, String key) {
                    String path = blockKey.getPath();
                    path = path.replace(key, "planks");
                    return blockKey.withPath(path);
                }
                private ResourceLocation replace(ResourceLocation blockKey, String tPath) {
                    String path = blockKey.getPath();
                    path = path.substring(0, path.lastIndexOf("_") + 1) + tPath;
                    return blockKey.withPath(path);
                }
            
                private <T extends Block> %%classname%% slabBlock(Supplier<T> block, String bottom, String side, String top, boolean isItem) {
                    ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block.get());
                    ResourceLocation blockKey = key.withPrefix("block/");
                    ResourceLocation planks = planks(blockKey);
                    ResourceLocation topRl = blockKey.withSuffix("_top");
                    MODELS.put(blockKey, Map.of(
                        "parent", "minecraft:block/slab",
                        "textures", Map.of(
                            "bottom", bottom.isEmpty() ? planks.toString() : bottom,
                            "side", side.isEmpty() ? planks.toString() : side,
                            "top", top.isEmpty() ? planks.toString() : top
                    )));
                    MODELS.put(topRl, Map.of(
                        "parent", "minecraft:block/slab_top",
                        "textures", Map.of(
                            "bottom", bottom.isEmpty() ? planks.toString() : bottom,
                            "side", side.isEmpty() ? planks.toString() : side,
                            "top", top.isEmpty() ? planks.toString() : top
                        )));
                        BLOCKSTATES.put(key, Map.of(
                            "variants", Map.of(
                                "type=bottom", model(blockKey, null, null, null, false),
                                "type=double", model(planks, null, null, null, false),
                                "type=top", model(topRl, null, null, null, false)
                            )));
                      if(isItem)
                        basicBlockItem((Supplier<? extends BlockItem>) () -> (BlockItem) block.get().asItem());
                    return this;
                }
            
                private <T extends Block> %%classname%% logBlock(Supplier<T> block, boolean isItem) {
                    ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block.get());
                    ResourceLocation blockKey = key.withPrefix("block/");
                    ResourceLocation horizontal = blockKey.withSuffix("_horizontal");
                    ResourceLocation top = blockKey.withSuffix("_top");
                    cubeColumn(block, top.toString(), blockKey.toString(), isItem, false, true);
                    cubeColumn(block, top.toString(), blockKey.toString(), isItem, true, true);
                    BLOCKSTATES.put(key, Map.of(
                        "variants", Map.of(
                            "axis=x", model(horizontal, 90, 90, null, false),
                            "axis=y", model(blockKey, null, null, null, false),
                            "axis=z", model(horizontal, 90, null, null, false)
                        )
                    ));
                    if(isItem)
                        basicBlockItem((Supplier<? extends BlockItem>) () -> (BlockItem) block.get().asItem());
                    return this;
                }
                private <T extends Block> %%classname%% woodBlock(Supplier<T> block, boolean isItem) {
                    ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block.get());
                    ResourceLocation blockKey = key.withPrefix("block/");
                    ResourceLocation log = replace(blockKey, "log");
                    cubeColumn(block, log.toString(), log.toString(), isItem, false, true);
                    BLOCKSTATES.put(key, Map.of(
                        "variants", Map.of(
                            "axis=x", model(blockKey, 90, 90, null, false),
                            "axis=y", model(blockKey, null, null, null, false),
                            "axis=z", model(blockKey, 90, null, null, false)
                        )
                    ));
                    if(isItem)
                        basicBlockItem((Supplier<? extends BlockItem>) () -> (BlockItem) block.get().asItem());
                    return this;
                }
            
                private <T extends Block> %%classname%% buttonBlock(Supplier<T> block, String texture) {
                    ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block.get());
                    ResourceLocation blockKey = key.withPrefix("block/");
                    ResourceLocation inventory = blockKey.withSuffix("_inventory");
                    ResourceLocation pressed = blockKey.withSuffix("_pressed");
                    ResourceLocation planks = planks(blockKey);
                    MODELS.put(blockKey, Map.of(
                        "parent", "minecraft:block/button",
                        "textures", Map.of("texture", texture.isEmpty() ? planks.toString(): texture)));
                    MODELS.put(inventory, Map.of(
                        "parent", "minecraft:block/button_inventory",
                        "textures", Map.of("texture", texture.isEmpty() ? planks.toString(): texture)));
                    MODELS.put(pressed, Map.of(
                        "parent", "minecraft:block/button_pressed",
                        "textures", Map.of("texture", texture.isEmpty() ? planks.toString(): texture)));
                    Map tMap = new HashMap();
                    tMap.put("face=ceiling,facing=east,powered=false", model(blockKey, 180, 270, null, false));
                    tMap.put("face=ceiling,facing=east,powered=true", model(pressed, 180, 270, null, false));
                    tMap.put("face=ceiling,facing=north,powered=false", model(blockKey, 180, 180, null, false));
                    tMap.put("face=ceiling,facing=north,powered=true", model(pressed, 180, 180, null, false));
                    tMap.put("face=ceiling,facing=south,powered=false", model(blockKey, 180, null, null, false));
                    tMap.put("face=ceiling,facing=south,powered=true", model(pressed, 180, null, null, false));
                    tMap.put("face=ceiling,facing=west,powered=false", model(blockKey, 180, 90, null, false));
                    tMap.put("face=ceiling,facing=west,powered=true", model(pressed, 180, 90, null, false));
                    tMap.put("face=floor,facing=east,powered=false", model(blockKey, null, 90, null, false));
                    tMap.put("face=floor,facing=east,powered=true", model(pressed, null, 90, null, false));
                    tMap.put("face=floor,facing=north,powered=false", model(blockKey, null, null, null, false));
                    tMap.put("face=floor,facing=north,powered=true", model(pressed, null, null, null, false));
                    tMap.put("face=floor,facing=south,powered=false", model(blockKey, null, 180, null, false));
                    tMap.put("face=floor,facing=south,powered=true", model(pressed, null, 180, null, false));
                    tMap.put("face=floor,facing=west,powered=false", model(blockKey, null, 270, null, false));
                    tMap.put("face=floor,facing=west,powered=true", model(pressed, null, 270, null, false));
                    tMap.put("face=wall,facing=east,powered=false", model(blockKey, 90, 90, null, false));
                    tMap.put("face=wall,facing=east,powered=true", model(pressed, 90, 90, null, false));
                    tMap.put("face=wall,facing=north,powered=false", model(blockKey, 90, null, null, false));
                    tMap.put("face=wall,facing=north,powered=true", model(pressed, 90, null, null, false));
                    tMap.put("face=wall,facing=south,powered=false", model(blockKey, 90, 180, null, false));
                    tMap.put("face=wall,facing=south,powered=true", model(pressed, 90, 180, null, false));
                    tMap.put("face=wall,facing=west,powered=false", model(blockKey, 90, 270, null, false));
                    tMap.put("face=wall,facing=west,powered=true", model(pressed, 90, 270, null, false));
                    BLOCKSTATES.put(key, Map.of(
                        "variants", tMap
                    ));
                    basicParentBlockItem((Supplier<? extends BlockItem>) () -> (BlockItem) block.get().asItem(), inventory);
                    return this;
                }
            
                private <T extends Block> %%classname%% pressurePlateBlock(Supplier<T> block, String texture, boolean isItem) {
                    ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block.get());
                    ResourceLocation blockKey = key.withPrefix("block/");
                    ResourceLocation down = blockKey.withSuffix("_down");
                    ResourceLocation planks = planks(blockKey, "pressure_plate");
                    MODELS.put(blockKey, Map.of(
                        "parent", "minecraft:block/pressure_plate_up",
                        "textures", Map.of("texture", planks.toString())
                    ));
                    MODELS.put(down, Map.of(
                        "parent", "minecraft:block/pressure_plate_down",
                        "textures", Map.of("texture", planks.toString())
                    ));
                    BLOCKSTATES.put(key, Map.of(
                        "variants", Map.of(
                            "powered=false", model(blockKey, null, null, null, false),
                            "powered=true", model(down, null, null, null, false)
                        )
                    ));
                    if(isItem)
                        basicBlockItem((Supplier<? extends BlockItem>) () -> (BlockItem) block.get().asItem());
                    return this;
                }
            
                private <T extends Item> %%classname%% parentItem(
                    Supplier<T> supplier,
                    String parent,
                    String... textures
                ) {
                    ResourceLocation key = BuiltInRegistries.ITEM.getKey(supplier.get()).withPrefix("item/");
                    Map<String, String> texturesMap = new HashMap<>();
                    for (int i = 0; i < textures.length - 1; i+=2) {
                        texturesMap.put(textures[i], textures[i + 1]);
                    }
                    Map map = new HashMap<>();
                    map.put("parent", parent);
                    if (!texturesMap.isEmpty()) {
                        map.put("textures", texturesMap);
                    }
                    MODELS.put(key, map);
                    return this;
                }
            
                private <T extends SpawnEggItem> %%classname%% spawnEggItem(Supplier<T> item) {
                    MODELS.put(BuiltInRegistries.ITEM.getKey(item.get()).withPrefix("item/"), SPAWN_EGG);
                    return this;
                }
            
                @Override
                public void init() {
                    %%init%%;
                }
            
                @Override
                public %%classname%% setOutput(PackOutput output) {
                    this.output = output;
                    assetsDir = output.getOutputFolder(PackOutput.Target.RESOURCE_PACK);
                    return this;
                }
            
                @Override
                public CompletableFuture<?> run(CachedOutput output) {
                    init();
                    CompletableFuture<?>[] futures = new CompletableFuture[MODELS.size() + BLOCKSTATES.size()];
                    int i = 0;
                    for (Map.Entry<ResourceLocation, Object> entry : MODELS.entrySet()) {
                        ResourceLocation key = entry.getKey();
                        Object object = entry.getValue();
                        Path itemModel = assetsDir.resolve(key.getNamespace()).resolve("models").resolve(key.getPath() + ".json");
                        JsonElement jsonTree = GSON.toJsonTree(object);
                        futures[i] = DataProvider.saveStable(output, jsonTree, itemModel);
                        i++;
                    }
                    for(Map.Entry<ResourceLocation, Object> entry : BLOCKSTATES.entrySet()) {
                        ResourceLocation key = entry.getKey();
                        Object object = entry.getValue();
                        Path states = assetsDir.resolve(key.getNamespace()).resolve("blockstates").resolve(key.getPath() + ".json");
                         JsonElement jsonTree = GSON.toJsonTree(object);
                         futures[i] = DataProvider.saveStable(output, jsonTree, states);
                         i++;
                    }
                    return CompletableFuture.allOf(futures);
                }
            
                @Override
                public String getName() {
                    return "Model Provider by " + modid;
                }
            
                @Override
                public %%classname%% setModid(String modid) {
                    this.modid = modid;
                    return this;
                }
            }
            """.stripIndent(),
            "org.polaris2023.wild_wind.datagen.custom",
            "ModelProviderWildWind"
    ),
    LanguageProvider("""
            package %%package%%;
            
            import com.google.gson.Gson;
            import java.lang.Object;
            import java.lang.String;
            import java.nio.file.Path;
            import java.util.concurrent.CompletableFuture;
            import java.util.concurrent.ConcurrentHashMap;
            import net.minecraft.data.CachedOutput;
            import net.minecraft.data.DataProvider;
            import net.minecraft.data.PackOutput;
            import org.polaris2023.wild_wind.util.interfaces.ILanguage;
            import net.neoforged.neoforge.registries.DeferredHolder;
            import java.util.function.Supplier;
            import net.minecraft.world.item.Item;
            import net.minecraft.world.item.Items;
            import net.minecraft.world.level.block.Block;
            import net.minecraft.world.entity.EntityType;
            import net.minecraft.network.chat.contents.TranslatableContents;
            import net.minecraft.world.effect.MobEffect;
            import net.minecraft.world.item.alchemy.Potion;
            import net.minecraft.world.item.CreativeModeTab;
            import net.minecraft.world.item.ItemStack;
            import net.minecraft.sounds.SoundEvent;
            
            public final class %%classname%% implements ILanguage<%%classname%%>, DataProvider {
                private static final Gson GSON = new com.google.gson.GsonBuilder().setLenient().setPrettyPrinting().create();
            
                private PackOutput output;
            
                private final ConcurrentHashMap<String, ConcurrentHashMap<String, String>> languages = new ConcurrentHashMap<>();
            
                private String targetLanguage = targetLanguage = "en_us";
            
                private Path languageDir;
            
                private String modid;
            
                public %%classname%% setOutput(PackOutput output) {
                    this.output = output;
                    languageDir = output
                        .getOutputFolder(PackOutput.Target.RESOURCE_PACK)
                        .resolve(modid)
                        .resolve("lang");
                    return this;
                }
            
                public CompletableFuture<?> run(CachedOutput cachedOutput) {
                    init();
                    final CompletableFuture<?>[] futures = new CompletableFuture[languages.size()];
                    int i = 0;
                    for (var entry : languages.entrySet()) {
                        String lang = entry.getKey();
                        ConcurrentHashMap<String, String> kv = entry.getValue();
                        Path json = languageDir.resolve(lang + ".json");
                        com.google.gson.JsonElement jsonTree = GSON.toJsonTree(kv);
                        futures[i] = DataProvider.saveStable(cachedOutput, jsonTree, json);
                        i++;
                    }
                    return CompletableFuture.allOf(futures);
                }
            
                public %%classname%% setTargetLanguage(String targetLanguage) {
                    this.targetLanguage = targetLanguage;
                    return this;
                }
            
                public String getName() {
                    return "Language Setting by " + modid;
                }
            
                public %%classname%% add(Object r, String value) {
                    if (r instanceof String string) {
                        if (!languages.containsKey(targetLanguage)) languages.put(targetLanguage, new ConcurrentHashMap<>());
                        languages.get(targetLanguage).put(string, value);
                        return this;
                    }
                    return switch (r) {
                        case DeferredHolder<?, ?> holder -> add(holder.get(), value);
                        case Supplier<?> supplier -> add(supplier.get(), value);
                        case Item item -> add(item.getDescriptionId(), value);
                        case Block block -> add(block.getDescriptionId(), value);
                        case SoundEvent sound -> add("sound." + sound.getLocation().toString().replace(":", "."), value);
                        case EntityType<?> type -> add(type.getDescriptionId(), value);
                        case TranslatableContents contents -> add(contents.getKey(), value);
                        case MobEffect effect -> add(effect.getDescriptionId(), value);
                        case CreativeModeTab tab -> tab.getDisplayName().getContents() instanceof TranslatableContents contents ?
                            add(contents, value) :
                            this;
                        case ItemStack stack -> add(stack.getDescriptionId(), value);
                        default -> throw new IllegalStateException("Unexpected value: " + r);
                    };
                }
            
                public %%classname%% addPotion(DeferredHolder<Potion, Potion> r, String value, String splashPrefix, String lingeringPrefix, String suffix) {
                    String name = r.getKey().location().getPath();
                    add(Items.POTION.getDescriptionId() + ".effect." + name, value + suffix);
                    add(Items.SPLASH_POTION.getDescriptionId() + ".effect." + name, splashPrefix + value + suffix);
                    add(Items.LINGERING_POTION.getDescriptionId() + ".effect." + name, lingeringPrefix + value + suffix);
                    return this;
                }
            
                public %%classname%% setModid(String modid) {
                    this.modid = modid;
                    return this;
                }
            
                public void init() {
                    %%init%%;
                }
            }
            """.stripIndent(),
            "org.polaris2023.wild_wind.datagen.custom",
            "LanguageProviderWildWind"
    );
    private final String code;
    private final String packageName;
    private final String classname;

    Codes(String code, String packageName, String classname) {
        this.code = code;
        this.packageName = packageName;
        this.classname = classname;
    }

    public String code() {
        return code;
    }

    public void saveAndAddServiceCode(Filer filer, String services_className, Object init) {
        try {
            String qName = "%s.%s".formatted(packageName, classname);
            JavaFileObject sourceFile = filer.createSourceFile(qName);
            try(Writer writer = sourceFile.openWriter()) {
                writer.write(code
                        .replace("%%classname%%", classname)
                        .replace("%%package%%", packageName)
                        .replace("%%init%%", init.toString()));
            }

            InitProcessor.add(services_className, qName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
