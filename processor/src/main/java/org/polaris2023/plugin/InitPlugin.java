package org.polaris2023.plugin;

import com.google.auto.service.AutoService;
import com.sun.source.util.*;
import com.sun.tools.javac.api.*;
import com.sun.tools.javac.util.*;
import com.sun.tools.javac.tree.*;
import org.polaris2023.utils.Unsafe;

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
    }
}
