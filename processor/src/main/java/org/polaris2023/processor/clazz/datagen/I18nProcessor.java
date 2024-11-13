package org.polaris2023.processor.clazz.datagen;

import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.processor.clazz.ClassProcessor;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.util.HashMap;
import java.util.Map;

public class I18nProcessor extends ClassProcessor {

    public static final Map<String, StringBuilder> LANGUAGES =  new HashMap<>();

    public static void add(String key, String value) {
        if (!LANGUAGES.containsKey(key)) LANGUAGES.put(key, new StringBuilder());
        LANGUAGES.get(key).append(value).append("\n");
    }

    public I18nProcessor(JavacProcessingEnvironment environment) {
        super(environment);
    }

    @Override
    public void classDef(TypeElement typeElement) {
        for (Element element : typeElement.getEnclosedElements()) {

            if (element.getKind().isField()) {
                VariableElement variableElement = (VariableElement) element;
                I18n i18n = variableElement.getAnnotation(I18n.class);
                if (i18n != null) {
                    add("en_us", ".add(%s, \"%s\")".formatted(typeElement.getQualifiedName() + "." + variableElement.getSimpleName(), i18n.en_us()));
                    add("zh_cn", ".add(%s, \"%s\")".formatted(typeElement.getQualifiedName() + "." + variableElement.getSimpleName(), i18n.zh_cn()));
                    add("zh_tw", ".add(%s, \"%s\")".formatted(typeElement.getQualifiedName() + "." + variableElement.getSimpleName(), i18n.zh_tw()));
                    for (I18n.Other other : i18n.other()) {
                        add(other.value(), ".add(%s, \"%s\")".formatted(typeElement.getQualifiedName() + "." + variableElement.getSimpleName(), other.translate()));
                    }
                }
            }
        }
    }
}
