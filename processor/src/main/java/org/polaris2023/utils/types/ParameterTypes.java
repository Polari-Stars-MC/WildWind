package org.polaris2023.utils.types;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import org.polaris2023.utils.Const;

import java.util.function.Supplier;

public enum ParameterTypes implements Supplier<ParameterSpec.Builder> {
    CACHED_OUTPUT(Const.ClassNames.CACHED_OUTPUT, "cachedOutput"),
    MODID(Const.ClassNames.STRING, "modid"),
    PACK_OUTPUT(Const.ClassNames.PACK_OUTPUT, "output"),
    TARGET_LANGUAGE(Const.ClassNames.STRING, "targetLanguage"),
    R(() -> TypeName.OBJECT, "r"),
    VALUE(Const.ClassNames.STRING, "value")
    ;

    private final ParameterSpec.Builder builder;

    ParameterTypes(Supplier<TypeName> supplier, String name) {
        this.builder = ParameterSpec.builder(supplier.get(), name);
    }

    @Override
    public ParameterSpec.Builder get() {
        return builder;
    }

    public ParameterSpec build() {
        return builder.build();
    }
}
