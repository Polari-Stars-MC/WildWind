package org.polaris2023.plugin;

import com.google.auto.service.AutoService;
import com.sun.source.util.*;
import java.util.Arrays;


@AutoService(Plugin.class)
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
