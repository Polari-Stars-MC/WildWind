package org.polaris2023.plugin;

import com.sun.source.util.*;

import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.api.BasicJavacTask;

import java.util.Arrays;

public class InitPlugin implements Plugin {
    @Override
    public String getName() {
        return "PolarisInit";
    }

    @Override
    public void init(JavacTask task, String... args) {
        System.out.printf("Initializing plugin %s with args %s%n", getName(), Arrays.toString(args));
    }
}
