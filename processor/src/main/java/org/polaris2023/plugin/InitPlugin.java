package org.polaris2023.plugin;

import com.google.auto.service.AutoService;
import com.sun.source.tree.*;
import com.sun.source.util.*;
import com.sun.source.util.TreeScanner;
import com.sun.tools.javac.api.*;
import com.sun.tools.javac.util.*;
import com.sun.tools.javac.tree.*;
import com.sun.tools.javac.parser.*;
import lombok.Getter;
import lombok.Setter;
import org.polaris2023.plugin.visitor.ConfigVisitor;
import org.polaris2023.utils.Unsafe;

import javax.lang.model.element.Modifier;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@AutoService(Plugin.class)
public class InitPlugin implements Plugin {
    private Context context;
    private Log log = null;
    private Trees trees;
    private TreeMaker treeMaker;
    static {
        Unsafe.exportJdkModule();
    }

    @Override
    public String getName() {
        return "PolarisInit";
    }

    @Override
    public void init(JavacTask task, String... args) {

        context = ((BasicJavacTask) task).getContext();
        log = Log.instance(context);
        trees = Trees.instance(task);
        treeMaker = TreeMaker.instance(context);
        log.printRawLines("Initializing plugin %s args %s".formatted(getName(), Arrays.toString(args)));
        task.addTaskListener(new TaskListener() {
            /**
             * Invoked when an event has been completed.
             *
             * @param e the event
             * @implSpec The default implementation of this method does nothing.
             */
            @Override
            public void finished(TaskEvent e) {

            }
        });
//        task.addTaskListener(new TaskListener() {
//            @Override
//            public void finished(TaskEvent e) {
//                if (e.getKind() == TaskEvent.Kind.PARSE) {
//                    if (e.getSourceFile().getName().contains("package-info.java")) {
//                        return;//排除package-info.java
//                    }
//
//                    e.getCompilationUnit().accept(new ConfigVisitor(), context);
//                }
//            }
//        });
    }

    public void processMethod(MethodTree node) {
        var tree = (JCTree.JCMethodDecl) node;
        String codeSnippet = """
				for (int i = 0; i < 3; i++) {
							System.out.println("Inject i: " + i);
						}
				""";
        ParserFactory parserFactory = ParserFactory.instance(context);
        JavacParser javacParser = parserFactory.newParser(codeSnippet, false, false, false);

        JCTree.JCStatement jcStatement = javacParser.parseStatement();
        if (tree.body != null)
            tree.body.stats = tree.body.stats.prepend(jcStatement);
    }
}
