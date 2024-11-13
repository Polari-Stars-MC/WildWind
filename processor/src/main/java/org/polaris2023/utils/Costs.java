package org.polaris2023.utils;

import com.google.common.reflect.TypeToken;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

import java.util.function.Supplier;

public class Costs {
    public enum ClassNames implements Supplier<ClassName> {
        STRING("java.lang.String"),
        NIO_PATH("java.nio.file.Path"),
        PACK_OUTPUT("net.minecraft.data.PackOutput"),
        COMPLETABLE_FUTURE("java.util.concurrent.CompletableFuture"),
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
        COMPLETABLE_FUTURE(new TypeToken<java.util.concurrent.CompletableFuture<?>>() {}),;
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
