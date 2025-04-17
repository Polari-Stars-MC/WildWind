package org.polaris2023.processor.clazz.handler;

import com.sun.source.tree.ClassTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.VariableTree;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import org.polaris2023.annotation.enums.RegType;
import org.polaris2023.annotation.handler.RegistryHandler;
import org.polaris2023.annotation.handler.TagHandler;
import org.polaris2023.processor.InitProcessor;
import org.polaris2023.processor.clazz.ClassProcessor;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/02 18:00:30}
 */
public class HandlerProcessor extends ClassProcessor {
    public HandlerProcessor(JavacProcessingEnvironment environment) {
        super(environment);
    }

    @Override
    public void classDef(TypeElement typeElement) {
        RegistryHandler handler = typeElement.getAnnotation(RegistryHandler.class);
        ClassTree tree = trees.getTree(typeElement);
        if (handler != null) {
            RegType value = handler.value();

            InitProcessor.REGISTRY_MAP.put(value,
                    tree.getMembers().stream().filter(t -> t.getKind().equals(Tree.Kind.VARIABLE))
                            .map(t -> (VariableTree) t).findFirst()
            );
            InitProcessor.REGISTRY_CLASS_MAP.put(value, tree);
        }


    }

    @Override
    public void methodDef(ExecutableElement executableElement, TypeElement typeElement) {
        TagHandler tagHandler = executableElement.getAnnotation(TagHandler.class);
        if (tagHandler != null) {
            InitProcessor.TAG_MAP.put(tagHandler.value(), trees.getTree(executableElement));
        }
    }
}
