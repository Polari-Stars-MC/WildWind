package org.polaris2023.utils.types;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import org.polaris2023.utils.Const;

import javax.lang.model.element.Modifier;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public enum Types implements Supplier<TypeSpec.Builder> {
    LanguageProviderWildWind(
            List.of(
                FieldTypes.GSON,
                FieldTypes.modid,
                FieldTypes.targetLanguage,
                FieldTypes.output,
                FieldTypes.languages,
                FieldTypes.languageDir
            ),
            List.of(
                    MethodTypes.LANGUAGE_RUN,
                    MethodTypes.LANGUAGE_GET_NAME,
                    MethodTypes.LANGUAGE_SET_MODID,
                    MethodTypes.LANGUAGE_SET_OUTPUT,
                    MethodTypes.LANGUAGE_SET_TARGET_LANGUAGE,
                    MethodTypes.LANGUAGE_ADD
            ),
            List.of(
                    Const.ClassNames.MC_DATA_PROVIDER
            ),
            types -> {
                ClassName self = ClassName.bestGuess(types.name());
                MethodTypes.LANGUAGE_SET_MODID.get().returns(self);
                MethodTypes.LANGUAGE_SET_OUTPUT.get().returns(self);
                MethodTypes.LANGUAGE_SET_TARGET_LANGUAGE.get().returns(self);
                MethodTypes.LANGUAGE_ADD.get().returns(self);
                types.builder.addSuperinterface(ParameterizedTypeName.get(Const.ClassNames.I_LANGUAGE.get(), self));
            },
            Modifier.PUBLIC
    );

    private final TypeSpec.Builder builder;

    Types() {
        this.builder = TypeSpec.classBuilder(name());
    }

    Types(List<FieldTypes> fields, List<MethodTypes> methods, List<Supplier<TypeName>> supers, Consumer<Types> consumer, Modifier... modifiers) {
        this();
        consumer.accept(this);
        builder
                .addFields(fields.stream().map(FieldTypes::build).collect(Collectors.toSet()))
                .addMethods(methods.stream().map(MethodTypes::build).collect(Collectors.toSet()))
                .addSuperinterfaces(supers.stream().map(Supplier::get).collect(Collectors.toSet()))
                .addModifiers(modifiers);
    }

    Types(List<FieldTypes> fields, List<MethodTypes> methods, List<Supplier<TypeName>> supers, Modifier... modifiers) {
        this(fields, methods, supers, __ -> {}, modifiers);
    }

    @Override
    public TypeSpec.Builder get() {
        return builder;
    }

    public TypeSpec build() {
        return builder.build();
    }
}
