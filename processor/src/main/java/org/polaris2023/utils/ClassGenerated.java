package org.polaris2023.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : baka4n
 * {@code @Date : 2025/02/28 17:03:54}
 * 新的可能的生成机制，之前的太臃肿
 */
public record ClassGenerated(List<String> imports) {


    public ClassGenerated() {
        this(new ArrayList<>());
    }

    public ClassGenerated imports(String... imports) {
        this.imports.addAll(Arrays.stream(imports).toList());
        return this;
    }

    public String generated(String packageName, String className) {
        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(packageName).append(";");
        for (String anImport : imports) {
            sb.append("import ").append(anImport).append(";");
        }
        return sb.toString();
    }
}
