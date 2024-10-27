package org.polaris2023.processor;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

@AutoService(Processor.class)
@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class InitProcessor extends AbstractProcessor {
    public Filer filer;
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();

    }

    /**
     * {@inheritDoc}
     *
     * @param annotations
     * @param roundEnv
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element rootElement : roundEnv.getRootElements()) {

            packageCheck(rootElement.getEnclosingElement());
        }
        return true;
    }

    public void packageCheck(Element element) {
        if (element.getKind() == ElementKind.PACKAGE) {
            PackageElement packageElement = (PackageElement) element;
            System.out.println(packageElement);
            writePackageInfo(packageElement);

        }
    }

    public void writePackageInfo(PackageElement packageElement) {
        try {
            JavaFileObject fileObject = filer.createSourceFile(packageElement.getQualifiedName().toString() + ".package-info");

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
