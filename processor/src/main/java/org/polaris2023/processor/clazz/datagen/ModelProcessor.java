package org.polaris2023.processor.clazz.datagen;

import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import org.polaris2023.annotation.modelgen.BasicItem;
import org.polaris2023.annotation.modelgen.SpawnEggItem;
import org.polaris2023.processor.clazz.ClassProcessor;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

public class ModelProcessor extends ClassProcessor {

    public static final StringBuilder MODEL = new StringBuilder();

    public ModelProcessor(JavacProcessingEnvironment environment) {
        super(environment);
    }

    public static void check() {
        if (MODEL.isEmpty()) {
            MODEL.append("this");
        }
    }

    @Override
    public void fieldDef(VariableElement variableElement, TypeElement typeElement) {
        BasicItem basicItem = variableElement.getAnnotation(BasicItem.class);
        if (basicItem != null) {
            check();
            MODEL.append("\n\t\t")
                    .append(".basicItem(")
                    .append(typeElement.getQualifiedName())
                    .append(".")
                    .append(variableElement.getSimpleName())
                    .append(")");
        }
        SpawnEggItem spawnEggItem = variableElement.getAnnotation(SpawnEggItem.class);
        if (spawnEggItem != null) {
            check();
            MODEL.append("\n\t\t")
                    .append(".spawnEggItem(")
                    .append(typeElement.getQualifiedName())
                    .append(".")
                    .append(variableElement.getSimpleName())
                    .append(")");
        }
    }
}
