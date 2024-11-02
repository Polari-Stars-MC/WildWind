package org.polaris2023.plugin.visitor;

import com.sun.source.tree.ClassTree;
import com.sun.source.util.TreeScanner;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Names;

public class AutoConfigScanner extends TreeScanner<Void, Context> {
    public AutoConfigScanner() {

    }
    @Override
    public Void visitClass(ClassTree node, Context context) {
        var maker = TreeMaker.instance(context);
        var name = Names.instance(context);
        var claz = (JCTree.JCClassDecl) node;
        if (node
                .getModifiers()
                .getAnnotations()
                .stream()
                .anyMatch(e -> e.getAnnotationType().toString().equals("AutoConfig")))
            return super.visitClass(node, context);
        return null;
    }
}
