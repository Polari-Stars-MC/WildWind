package org.polaris2023.plugin.visitor;

import com.sun.source.tree.AnnotationTree;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.Tree;
import com.sun.source.util.TreeScanner;
import com.sun.tools.javac.util.Context;

public class ConfigVisitor extends TreeScanner<String, Context> {
    @Override
    public String visitAnnotation(AnnotationTree node, Context context) {
        System.out.println(node.getAnnotationType().toString());

        return super.visitAnnotation(node, context);
    }

    @Override
    public String visitClass(ClassTree node, Context context) {
        System.out.println(node.getSimpleName());
        return super.visitClass(node, context);
    }
}
