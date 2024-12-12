package org.polaris2023.utils.types;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import org.polaris2023.utils.Const;

import javax.lang.model.element.Modifier;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public enum MethodTypes implements Supplier<MethodSpec.Builder> {
    LANGUAGE_RUN(
            "run",
            Const.TypeNames.COMPLETABLE_FUTURE,
            List.of(
                    ParameterTypes.CACHED_OUTPUT
            ),
            """
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
                    """
            ,
            Modifier.PUBLIC
    ),
    LANGUAGE_GET_NAME(
            "getName",
            Const.ClassNames.STRING,
            "return \"Language Setting by \" + modid;",
            Modifier.PUBLIC
    ),
    LANGUAGE_SET_MODID(
            "setModid",
            List.of(
                    ParameterTypes.MODID
            ),
            """
                    this.modid = modid;
                    return this;
                    """,
            Modifier.PUBLIC
    ),
    LANGUAGE_SET_OUTPUT(
            "setOutput",
            List.of(
                    ParameterTypes.PACK_OUTPUT
            ),
            """
                    this.output = output;
                    languageDir = output
                        .getOutputFolder(PackOutput.Target.RESOURCE_PACK)
                        .resolve(modid)
                        .resolve("lang");
                    return this;
                    """,
            Modifier.PUBLIC
    ),
    LANGUAGE_SET_TARGET_LANGUAGE(
            "setTargetLanguage",
            List.of(
                    ParameterTypes.TARGET_LANGUAGE
            ),
            """
                    this.targetLanguage = targetLanguage;
                    return this;
                    """,
            Modifier.PUBLIC
    ),
    LANGUAGE_ADD(
            "add",
            List.of(
                    ParameterTypes.R,
                    ParameterTypes.VALUE
            ),
            """
                    if (r instanceof String string) {
                        if (!languages.containsKey(targetLanguage)) languages.put(targetLanguage, new ConcurrentHashMap<>());
                        languages.get(targetLanguage).put(string, value);
                        return this;
                    }
                    return switch (r) {
                        case net.neoforged.neoforge.registries.DeferredHolder<?, ?> holder -> add(holder.get(), value);
                        case java.util.function.Supplier<?> supplier -> add(supplier.get(), value);
                        case net.minecraft.world.item.Item item -> add(item.getDescriptionId(), value);
                        case net.minecraft.world.level.block.Block block -> add(block.getDescriptionId(), value);
                        case net.minecraft.world.entity.EntityType<?> type -> add(type.getDescriptionId(), value);
                        case net.minecraft.network.chat.contents.TranslatableContents contents -> add(contents.getKey(), value);
                        case net.minecraft.world.effect.MobEffect effect -> add(effect.getDescriptionId(), value);
                        case net.minecraft.world.item.CreativeModeTab tab -> tab.getDisplayName().getContents() instanceof net.minecraft.network.chat.contents.TranslatableContents contents ?
                            add(contents, value) :
                            this;
                        case net.minecraft.world.item.ItemStack stack -> add(stack.getDescriptionId(), value);
                        default -> throw new IllegalStateException("Unexpected value: " + r);
                    };
                    """,
            Modifier.PUBLIC
    ),
    LANGUAGE_INIT(
            "init",
            () -> TypeName.VOID,
            "",
            Modifier.PUBLIC
    )
    ;
    private final MethodSpec.Builder builder;

    MethodTypes(String name, Supplier<TypeName> returns, List<ParameterTypes> params, String code, Modifier... modifiers) {
        this(name, returns, code, modifiers);
        builder.addParameters(params.stream().map(ParameterTypes::build).collect(Collectors.toSet()));

    }

    MethodTypes(String name, List<ParameterTypes> params, String code, Modifier... modifiers) {
        this(name, code, modifiers);
        builder.addParameters(params.stream().map(ParameterTypes::build).collect(Collectors.toSet()));

    }

    MethodTypes(String name, Supplier<TypeName> returns, String code, Modifier... modifiers) {
        this(name, code, modifiers);
        this.builder
                .returns(returns.get());
    }

    MethodTypes(String name, String code, Modifier... modifiers) {
        this(name, modifiers);
        if (!code.isEmpty())
            builder.addCode(code);
    }

    MethodTypes(String name, Modifier... modifiers) {
        this.builder = MethodSpec
                .methodBuilder(name)
                .addModifiers(modifiers);
    }

    @Override
    public MethodSpec.Builder get() {
        return builder;
    }

    public MethodSpec build() {
        return builder.build();
    }
}
