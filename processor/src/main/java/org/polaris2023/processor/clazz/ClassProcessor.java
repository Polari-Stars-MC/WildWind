package org.polaris2023.processor.clazz;

import com.sun.source.util.Trees;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Names;
import lombok.experimental.Delegate;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.*;
import java.util.Set;

public class ClassProcessor {
    @Delegate
    public final JavacProcessingEnvironment environment;
    @Delegate
    public final Context context;
    @Delegate
    public final TreeMaker maker;
    @Delegate
    public final Trees trees;

    @Delegate
    public final Names names;

    public ClassProcessor(JavacProcessingEnvironment environment) {
        this.environment = environment;
        this.context = environment.getContext();
        this.maker = TreeMaker.instance(context);
        this.trees = Trees.instance(environment);
        this.names = Names.instance(context);
    }

    public void classDef(TypeElement typeElement) {}

    public void packageDef(PackageElement packageElement, TypeElement typeElement) {}

    public void fieldDef(VariableElement variableElement, TypeElement typeElement) {}
    public void methodDef(ExecutableElement executableElement, TypeElement typeElement) {}

    public void process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element rootElement : roundEnv.getRootElements()) {
            if (rootElement.getKind().isClass()) {
                Element enclosingElement = rootElement.getEnclosingElement();
                if (enclosingElement.getKind() == ElementKind.PACKAGE) {
                    packageDef((PackageElement) enclosingElement, (TypeElement) rootElement);
                }
                for (Element enclosedElement : rootElement.getEnclosedElements()) {
                    if (enclosedElement.getKind().isField()) {
                        fieldDef((VariableElement) enclosedElement, (TypeElement) rootElement);
                    } else if (enclosedElement.getKind().isExecutable()) {
                        methodDef((ExecutableElement) enclosedElement, (TypeElement) rootElement);
                    }
                }
                classDef((TypeElement) rootElement);
            }
        }
    }
}
