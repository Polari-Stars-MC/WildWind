package org.polaris2023.processor.clazz;

import com.sun.source.util.Trees;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Names;
import lombok.experimental.Delegate;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
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

    public void packageDef(PackageElement packageElement) {}

    public void process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element rootElement : roundEnv.getRootElements()) {
            Element enclosingElement = rootElement.getEnclosingElement();
            if (enclosingElement.getKind() == ElementKind.PACKAGE) {
                packageDef((PackageElement) enclosingElement);
            }
            if (rootElement.getKind().isClass()) {
                classDef((TypeElement) rootElement);
            }
        }
    }
}
