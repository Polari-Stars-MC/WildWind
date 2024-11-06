package org.polaris2023.ext;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;

public class PackageProcessor extends IProcessor<PackageElement> {

    @Override
    public PackageElement check(Element e) {
        if (e.getKind() == ElementKind.PACKAGE)
            return (PackageElement) e;
        return null;
    }

    @Override
    public void processor() {
        writePackageInfo(getCheck());
    }

    public void writePackageInfo(PackageElement packageElement) {
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
        } catch (IOException ignored) {}
    }
}
