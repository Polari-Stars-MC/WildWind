package org.polaris2023.processor.clazz.registry;

import com.sun.source.tree.ClassTree;
import com.sun.source.tree.VariableTree;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import org.polaris2023.annotation.register.RegistryHandler;
import org.polaris2023.processor.InitProcessor;
import org.polaris2023.processor.clazz.ClassProcessor;

import java.util.Map;
import java.util.Optional;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/06 18:04:44}
 */
public abstract class RegistryProcessor extends ClassProcessor {

    public abstract RegistryHandler.Type defaultType();
    public JCTree.JCClassDecl getRegisterTree() {//获取注册表所在类
        error(!InitProcessor.REGISTRY_CLASS_MAP.containsKey(defaultType()));
        return (JCTree.JCClassDecl) InitProcessor.REGISTRY_CLASS_MAP.get(defaultType());
    }

    public JCTree.JCVariableDecl getRegistryField() {
        error(!InitProcessor.REGISTRY_MAP.containsKey(defaultType()));
        Optional<? extends VariableTree> variableTree = InitProcessor.REGISTRY_MAP.get(defaultType());
        if (variableTree.isEmpty()) {
            throw new RuntimeException("Don't find register class for Field");
        }
        return (JCTree.JCVariableDecl) variableTree.get();
    }



    private void error(boolean b) {
        if (b) {
            throw new RuntimeException("Don't find register class for " + defaultType().name());
        }
    }

    public RegistryProcessor(JavacProcessingEnvironment environment) {
        super(environment);

    }
}
