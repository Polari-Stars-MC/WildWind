package org.polaris2023.ext.language;

import com.google.auto.service.AutoService;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.ext.ClassProcessor;
import org.polaris2023.ext.interfaces.IClassProcessor;

import javax.lang.model.element.Element;
import javax.lang.model.element.VariableElement;
import java.util.HashMap;
import java.util.Map;

@AutoService(IClassProcessor.class)
public class I18nProcessor extends ClassProcessor {
    public static final Map<String, StringBuilder> LANGUAGES =  new HashMap<>();

    public static void add(String key, String value) {
        if (!LANGUAGES.containsKey(key)) LANGUAGES.put(key, new StringBuilder());
        LANGUAGES.get(key).append(value).append("\n");
    }
    public I18nProcessor() {
        super();
    }
    @Override
    public void processor() {
        super.processor();
        for (Element element : getCheck().getEnclosedElements()) {

            if (element.getKind().isField()) {
                VariableElement variableElement = (VariableElement) element;
                I18n i18n = variableElement.getAnnotation(I18n.class);
                if (i18n != null) {
                    add("en_us", ".add(%s, \"%s\")".formatted(getCheck().getQualifiedName() + "." + variableElement.getSimpleName(), i18n.en_us()));
                    add("zh_cn", ".add(%s, \"%s\")".formatted(getCheck().getQualifiedName() + "." + variableElement.getSimpleName(), i18n.zh_cn()));
                    add("zh_tw", ".add(%s, \"%s\")".formatted(getCheck().getQualifiedName() + "." + variableElement.getSimpleName(), i18n.zh_tw()));
                    for (I18n.Other other : i18n.other()) {
                        add(other.value(), ".add(%s, \"%s\")".formatted(getCheck().getQualifiedName() + "." + variableElement.getSimpleName(), other.translate()));
                    }
                }
            }
        }
    }
}
