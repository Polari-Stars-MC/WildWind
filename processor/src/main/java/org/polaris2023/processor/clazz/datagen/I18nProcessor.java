package org.polaris2023.processor.clazz.datagen;

import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.annotation.language.PotionI18n;
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
                PotionI18n potionI18n = variableElement.getAnnotation(PotionI18n.class);
                if (i18n != null) {
                    String name;
                    if(i18n.descriptionId().isEmpty()) {
                        name = typeElement.getQualifiedName() + "." + variableElement.getSimpleName();
                    } else {
                        name = "\"" + i18n.descriptionId() + "\"";
                    }
                    add("en_us", ".add(%s, \"%s\")".formatted(name, i18n.en_us()));
                    add("zh_cn", ".add(%s, \"%s\")".formatted(name, i18n.zh_cn()));
                    add("zh_tw", ".add(%s, \"%s\")".formatted(name, i18n.zh_tw()));
                    for (I18n.Other other : i18n.other()) {
                        add(other.value(), ".add(%s, \"%s\")".formatted(name, other.translate()));
                    }
                } else if(potionI18n != null) {
                    String name;
                    name = typeElement.getQualifiedName() + "." + variableElement.getSimpleName();
                    add("en_us", ".addPotion(%s, \"%s\", \"%s\", \"%s\", \"%s\")".formatted(name, potionI18n.en_us(), PotionI18n.PREFIX_SPLASH_EN_US, PotionI18n.PREFIX_LINGERING_EN_US, PotionI18n.SUFFIX_EN_US));
                    add("zh_cn", ".addPotion(%s, \"%s\", \"%s\", \"%s\", \"%s\")".formatted(name, potionI18n.zh_cn(), PotionI18n.PREFIX_SPLASH_ZH_CN, PotionI18n.PREFIX_LINGERING_ZH_CN, PotionI18n.SUFFIX_ZH_CN));
                    add("zh_tw", ".addPotion(%s, \"%s\", \"%s\", \"%s\", \"%s\")".formatted(name, potionI18n.zh_tw(), PotionI18n.PREFIX_SPLASH_ZH_TW, PotionI18n.PREFIX_LINGERING_ZH_TW, PotionI18n.SUFFIX_ZH_TW));
                }
            }
        }
    }
}
