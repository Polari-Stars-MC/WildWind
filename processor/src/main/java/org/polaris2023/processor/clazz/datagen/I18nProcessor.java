package org.polaris2023.processor.clazz.datagen;

import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.annotation.language.I18nEnum;
import org.polaris2023.annotation.language.I18nEnumInner;
import org.polaris2023.annotation.language.Other18n;
import org.polaris2023.processor.clazz.ClassProcessor;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
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
                I18nEnum i18nE = variableElement.getAnnotation(I18nEnum.class);
                if (i18nE != null) {
                    for (Element element1 : typeElement.getEnclosedElements()) {
                        I18nEnumInner i18nEI = element1.getAnnotation(I18nEnumInner.class);
                        if (i18nEI != null && !element1.getModifiers().contains(Modifier.STATIC)) {
                            String name = typeElement.getQualifiedName() + "." + variableElement.getSimpleName() + "." + element1.getSimpleName();
                            add("en_us", ".add(%s, \"%s\")".formatted(name, i18nE.type().equals(I18nEnum.Type.PREFIX) ? i18nE.en_us() + i18nEI.en_us() : i18nEI.en_us() + i18nE.en_us()));
                            add("zh_cn", ".add(%s, \"%s\")".formatted(name, i18nE.type().equals(I18nEnum.Type.PREFIX) ? i18nE.zh_cn() + i18nEI.zh_cn() : i18nEI.zh_cn() + i18nE.zh_cn()));
                            add("zh_tw", ".add(%s, \"%s\")".formatted(name, i18nE.type().equals(I18nEnum.Type.PREFIX) ? i18nE.zh_tw() + i18nEI.zh_tw() : i18nEI.zh_tw() + i18nE.zh_tw()));
                            Other18n[] other = i18nE.other();
                            Other18n[] other1 = i18nEI.other();
                            for (Other18n other18n : other) {
                                for (Other18n n : other1) {
                                    if (other18n.value().equals(n.value())) {
                                        add(other18n.value(), ".add(%s, \"%s\")".formatted(name, i18nE.type().equals(I18nEnum.Type.PREFIX) ? other18n.translate() + n.translate() : n.translate() + other18n.translate()));
                                    }
                                }
                            }
                        }
                    }
                }
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
                    for (Other18n other18n : i18n.other()) {
                        add(other18n.value(), ".add(%s, \"%s\")".formatted(name, other18n.translate()));
                    }
                }
            }
        }
    }
}
