package org.polaris2023.processor.jc;

import com.sun.source.tree.MethodTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.VariableTree;
import com.sun.tools.javac.code.*;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import org.polaris2023.annotation.jc.Final;
import org.polaris2023.processor.clazz.ClassProcessor;

import javax.lang.model.element.*;

public class ModifierProcessor extends ClassProcessor {
    public ModifierProcessor(JavacProcessingEnvironment environment) {
        super(environment);
    }

    @Override
    public void fieldDef(VariableElement variableElement, TypeElement typeElement) {
        Final aFinal = variableElement.getAnnotation(Final.class);
        if (aFinal != null) {
            VariableTree tree = (VariableTree) trees.getTree(variableElement);
            tree.getModifiers().getFlags().add(Modifier.FINAL);
        }
    }

    @Override
    public void methodDef(ExecutableElement executableElement, TypeElement typeElement) {
        Final aFinal = executableElement.getAnnotation(Final.class);
        if (aFinal != null) {
            MethodTree tree = trees.getTree(executableElement);
            tree.getModifiers().getFlags().add(Modifier.FINAL);
        }
    }

    @Override
    public void packageDef(PackageElement packageElement, TypeElement typeElement) {
        Final aFinal = packageElement.getAnnotation(Final.class);
        if (aFinal != null) {
            if (typeElement.getKind() == ElementKind.CLASS) {
                addonFinal(typeElement);
            }

        }
    }

    private void addonFinal(TypeElement typeElement) {
        JCTree.JCClassDecl tree = (JCTree.JCClassDecl) trees.getTree(typeElement);
        tree.mods.flags = tree.mods.flags | Flags.FINAL;
        for (JCTree def : tree.defs) {
            if (def.getKind() == Tree.Kind.VARIABLE) {
                JCTree.JCVariableDecl variableTree = (JCTree.JCVariableDecl) def;
                variableTree.mods.flags = variableTree.mods.flags | Flags.FINAL;
            } else if (def.getKind() == Tree.Kind.METHOD) {
                JCTree.JCMethodDecl methodDecl = (JCTree.JCMethodDecl) def;
                methodDecl.mods.flags = methodDecl.mods.flags | Flags.FINAL;

            }
        }
    }

    @Override
    public void classDef(TypeElement typeElement) {
        Final aFinal = typeElement.getAnnotation(Final.class);
        if (aFinal != null) {
            // TODO: 2023/5/10
            addonFinal(typeElement);
        }

    }
}
