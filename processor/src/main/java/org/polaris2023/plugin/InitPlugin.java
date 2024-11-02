package org.polaris2023.plugin;

import com.sun.source.tree.ClassTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.util.*;

import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.api.BasicJavacTask;
import org.polaris2023.plugin.listeners.InitListener;

import java.util.Arrays;

public class InitPlugin implements Plugin {
    @Override
    public String getName() {
        return "PolarisInit";
    }

    @Override
    public void init(JavacTask task, String... args) {
        System.out.printf("Initializing plugin %s with args %s%n", getName(), Arrays.toString(args));
        Context ctx = ((BasicJavacTask) task).getContext();
        task.addTaskListener(new InitListener(ctx));
    }
}
