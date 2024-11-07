package org.polaris2023.plugin;

import com.google.auto.service.AutoService;
import com.sun.source.tree.AnnotationTree;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.util.*;
import com.sun.source.util.TreeScanner;
import com.sun.tools.javac.api.*;
import com.sun.tools.javac.util.*;
import com.sun.tools.javac.tree.*;
import com.sun.tools.javac.parser.*;
import org.polaris2023.plugin.visitor.ConfigVisitor;
import org.polaris2023.utils.Unsafe;

import javax.tools.JavaFileObject;
import java.util.Arrays;


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
            @Override
            public void finished(TaskEvent e) {
                if (e.getKind() == TaskEvent.Kind.PARSE) {
                    if (e.getSourceFile().getName().contains("package-info.java")) {
                        return;//排除package-info.java
                    }
                    System.out.println(e.getSourceFile().getName());
                }
            }
        });
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
