package org.polaris2023.processor.pack;

import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import org.polaris2023.processor.clazz.ClassProcessor;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.*;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

public class PackageProcessor extends ClassProcessor {


    public PackageProcessor(JavacProcessingEnvironment environment) {
        super(environment);
    }

    @Override
    public void packageDef(PackageElement packageElement, TypeElement typeElement) {

        try {
            JavaFileObject fileObject = getFiler().createSourceFile(packageElement.getQualifiedName().toString() + ".package-info");
            try(Writer writer = fileObject.openWriter()) {
                writer.write("@ParametersAreNonnullByDefault\n");
                writer.write("@FieldsAreNonnullByDefault\n");
                writer.write("@MethodsReturnNonnullByDefault\n");
                writer.write("package " + packageElement.getQualifiedName().toString() + ";\n");
                writer.write("import net.minecraft.FieldsAreNonnullByDefault;\n");
                writer.write("import net.minecraft.MethodsReturnNonnullByDefault;\n");
                writer.write("import javax.annotation.ParametersAreNonnullByDefault;\n");
            }
        } catch (IOException ignored) {

        }
    }
}
