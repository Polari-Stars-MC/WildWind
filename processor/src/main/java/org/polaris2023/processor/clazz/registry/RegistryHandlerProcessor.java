package org.polaris2023.processor.clazz.registry;

import com.sun.source.tree.ClassTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.VariableTree;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import org.polaris2023.annotation.register.RegistryHandler;
import org.polaris2023.processor.InitProcessor;
import org.polaris2023.processor.clazz.ClassProcessor;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/02 18:00:30}
 */
public class RegistryHandlerProcessor extends ClassProcessor {
    public RegistryHandlerProcessor(JavacProcessingEnvironment environment) {
        super(environment);
    }

    @Override
    public void classDef(TypeElement typeElement) {
        RegistryHandler handler = typeElement.getAnnotation(RegistryHandler.class);
        if (handler != null) {
            RegistryHandler.Type value = handler.value();
            ClassTree tree = trees.getTree(typeElement);
            InitProcessor.REGISTRY_MAP.put(value,
                    tree.getMembers().stream().filter(t -> t.getKind().equals(Tree.Kind.VARIABLE))
                            .map(t -> (VariableTree) t).findFirst()
            );
            InitProcessor.REGISTRY_CLASS_MAP.put(value, tree);
        }
    }
}
