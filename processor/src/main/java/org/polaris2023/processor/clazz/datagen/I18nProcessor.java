package org.polaris2023.processor.clazz.datagen;

import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.annotation.language.I18nEnum;
import org.polaris2023.processor.clazz.ClassProcessor;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.util.HashMap;
import java.util.Map;

public class I18nProcessor extends ClassProcessor {

    public static final Map<String, StringBuilder> LANGUAGES =  new HashMap<>();

    public static void add(String key, String value) {
        if (!LANGUAGES.containsKey(key)) LANGUAGES.put(key, new StringBuilder());
        LANGUAGES.get(key).append("\t\t\t").append(value).append("\n");
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
                I18nEnum i18nEnum = variableElement.getAnnotation(I18nEnum.class);

                if (i18nEnum != null) {
                    for (Element enclosedElement : typeElement.getEnclosedElements()) {
                        if (enclosedElement.getKind().isField()) {
                            VariableElement fieldElement = (VariableElement) enclosedElement;
                            I18nEnum.InnerI18n innerI18n = fieldElement.getAnnotation(I18nEnum.InnerI18n.class);
                            if (innerI18n != null) {

                                String name = typeElement.getQualifiedName() + "." + variableElement.getSimpleName() + "." + fieldElement.getSimpleName();
                                String en_us = innerI18n.FIX().equals(I18nEnum.InnerI18n.Type.SUFFIX) ? i18nEnum.en_us() + innerI18n.en_us() : innerI18n.en_us() + i18nEnum.en_us();
                                String zh_cn = innerI18n.FIX().equals(I18nEnum.InnerI18n.Type.SUFFIX) ? i18nEnum.zh_cn() + innerI18n.zh_cn() : innerI18n.zh_cn() + i18nEnum.zh_cn();
                                String zh_tw = innerI18n.FIX().equals(I18nEnum.InnerI18n.Type.SUFFIX) ? i18nEnum.zh_tw() + innerI18n.zh_tw() : innerI18n.zh_tw() + i18nEnum.zh_tw();
                                translate(name, en_us, zh_cn, zh_tw, innerI18n.FIX(), i18nEnum.other(),innerI18n.other());
                            }
                        }
                    }
                }
                if (i18n != null) {
                    String name;
                    String descriptionId = i18n.descriptionId();
                    if(descriptionId.isEmpty()) {
                        name = typeElement.getQualifiedName() + "." + variableElement.getSimpleName();
                    } else {
                        name = "\"" + descriptionId + "\"";
                    }
                    String en_us = i18n.en_us();
                    String zh_cn = i18n.zh_cn();
                    String zh_tw = i18n.zh_tw();
                    translate(name, en_us, zh_cn, zh_tw, null,null, i18n.other());
                }
            }
        }
    }

    private static void translate(String name, String en_us, String zh_cn, String zh_tw,I18nEnum.InnerI18n.Type type, I18n.Other[] fixName, I18n.Other[] i18n) {
        add("en_us", ".add(%s, \"%s\")".formatted(name, en_us));
        add("zh_cn", ".add(%s, \"%s\")".formatted(name, zh_cn));
        add("zh_tw", ".add(%s, \"%s\")".formatted(name, zh_tw));
        if (type == null) {
            for (I18n.Other other : i18n) {
                add(other.value(), ".add(%s, \"%s\")".formatted(name, other.translate()));
            }
        } else {
            Map<String, String> kv = new HashMap<>();
            for (I18n.Other other : fixName) {
                String value = other.value();
                for (I18n.Other other1 : i18n) {
                    if (other1.value().equals(value)) {
                        kv.put(value, type.equals(I18nEnum.InnerI18n.Type.SUFFIX) ? other.translate() + other1.translate() : other1.translate() + other.translate());
                        break;
                    }
                }
            }
            kv.forEach((k, v) -> {
                add(k, ".add(%s, \"%s\")".formatted(name, v));
            });
        }
    }
}
