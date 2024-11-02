package org.polaris2023.plugin;

import com.sun.source.tree.ClassTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.util.*;

import java.util.Arrays;

public class InitPlugin implements Plugin {
    @Override
    public String getName() {
        return "PolarisInit";
    }

    @Override
    public void init(JavacTask task, String... args) {
        System.out.printf("Initializing plugin %s with args %s%n", getName(), Arrays.toString(args));
        task.addTaskListener(new TaskListener() {
            @Override
            public void finished(TaskEvent e) {
                if (e.getKind() == TaskEvent.Kind.ENTER) {
                    e.getCompilationUnit().accept(new TreeScanner<Void, Void>() {
                        @Override
                        public Void visitClass(ClassTree node, Void unused) {

                            System.out.println(node.getSimpleName());
                            return super.visitClass(node, unused);
                        }

                        @Override
                        public Void visitMethod(MethodTree node, Void unused) {

                            return super.visitMethod(node, unused);
                        }
                    }, null);
                }
            }
        });
    }
}
