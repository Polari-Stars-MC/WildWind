package org.polaris2023.utils.types;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeName;
import org.polaris2023.utils.Const;

import javax.lang.model.element.Modifier;
import java.util.function.Supplier;

public enum FieldTypes implements Supplier<FieldSpec.Builder> {
    GSON(Const.ClassNames.GSON, "new com.google.gson.GsonBuilder().setLenient().setPrettyPrinting().create()"),
    modid(Const.ClassNames.STRING, Modifier.PRIVATE),
    targetLanguage(Const.ClassNames.STRING, "targetLanguage = \"en_us\"", Modifier.PRIVATE),
    output(Const.ClassNames.PACK_OUTPUT, Modifier.PRIVATE),
    languages(Const.TypeNames.MAP_STRING_MAP_STRING_STRING, "new ConcurrentHashMap<>()", Modifier.PRIVATE, Modifier.FINAL),
    languageDir(Const.ClassNames.NIO_PATH, Modifier.PRIVATE)
    ;

    private final FieldSpec.Builder builder;

    FieldTypes(Supplier<TypeName> name, String code, Modifier... modifiers) {
        this(name, modifiers);
        builder.initializer(code);
    }

    FieldTypes(Supplier<TypeName> name, Modifier... modifiers) {
        builder = FieldSpec.builder(
                        name.get(),
                        name()
                )
                .addModifiers(modifiers);
    }

    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public FieldSpec.Builder get() {
        return builder;
    }


    public FieldSpec build() {
        return builder.build();
    }
}
