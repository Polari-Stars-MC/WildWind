package org.polaris2023.utils;

import com.google.common.reflect.TypeToken;
import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class Const {

    public enum ClassNames implements Supplier<TypeName> {
        STRING("java.lang.String"),
        NIO_PATH("java.nio.file.Path"),
        PACK_OUTPUT("net.minecraft.data.PackOutput"),
        COMPLETABLE_FUTURE("java.util.concurrent.CompletableFuture"),
        GSON("com.google.gson.Gson"),
        CACHED_OUTPUT("net.minecraft.data.CachedOutput"),
        MC_DATA_PROVIDER("net.minecraft.data.DataProvider"),
        I_LANGUAGE("org.polaris2023.wild_wind.util.interfaces.ILanguage")
        ;
        ;
        private final ClassName className;

        ClassNames(String name) {
            this.className = ClassName.bestGuess(name);
        }

        @Override
        public ClassName get() {
            return className;
        }
    }
    public enum TypeNames implements Supplier<TypeName> {
        COMPLETABLE_FUTURE(new TypeToken<java.util.concurrent.CompletableFuture<?>>() {}),
        MAP_STRING_MAP_STRING_STRING(new TypeToken<ConcurrentHashMap<String, ConcurrentHashMap<String, String>>>() {});
        private final TypeName typeName;

        TypeNames(TypeToken<?> typeToken) {
            this.typeName = ClassName.get(typeToken.getType());
        }


        @Override
        public TypeName get() {
            return typeName;
        }
    }
}
