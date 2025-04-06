package org.polaris2023.processor.clazz.registry;

import com.sun.source.tree.ClassTree;
import com.sun.source.tree.VariableTree;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.List;
import org.polaris2023.annotation.register.RegistryBlockItem;
import org.polaris2023.annotation.register.RegistryHandler;
import org.polaris2023.processor.InitProcessor;
import org.polaris2023.processor.clazz.ClassProcessor;
import org.polaris2023.utils.JCUtils;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.util.Optional;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/02 18:34:47}
 */
public class ItemRegistryProcessor extends ClassProcessor {
    public static final RegistryHandler.Type type = RegistryHandler.Type.Item;
    public ItemRegistryProcessor(JavacProcessingEnvironment environment) {
        super(environment);
    }

    @Override
    public void fieldDef(VariableElement variableElement, TypeElement typeElement) {
        if (!InitProcessor.REGISTRY_MAP.containsKey(type) || !InitProcessor.REGISTRY_CLASS_MAP.containsKey(type)) {
            return;
        }
        Optional<? extends VariableTree> variableTree = InitProcessor.REGISTRY_MAP.get(type);
        ClassTree classTree = InitProcessor.REGISTRY_CLASS_MAP.get(type);
        if(variableTree.isEmpty()) return;
        JCTree.JCClassDecl classDecl = (JCTree.JCClassDecl) trees.getTree(typeElement);
        JCTree.JCVariableDecl decl = (JCTree.JCVariableDecl) trees.getTree(variableElement);
        JCTree.JCVariableDecl registry = (JCTree.JCVariableDecl) variableTree.get();
        JCTree.JCClassDecl treeMapDecl = (JCTree.JCClassDecl) classTree;

        RegistryBlockItem blockItem = variableElement.getAnnotation(RegistryBlockItem.class);
        if (blockItem != null) {
            JCTree.JCVariableDecl registerSimpleBlockItem = maker.VarDef(
                    maker.Modifiers(Flags.PUBLIC | Flags.FINAL | Flags.STATIC),
                    decl.getName(),
                    maker.TypeApply(JCUtils.Ident.DeferredItem.get(maker, names),
                            List.of(JCUtils.Ident.BlockItem.get(maker, names))),
                    maker.Apply(
                            List.nil(),
                            maker.Select(
                                    JCUtils.makeIdent(maker, names, treeMapDecl.name + "." + registry.name),
                                    names.fromString("registerSimpleBlockItem")
                            ),
                            List.of(maker.Select(maker.Ident(classDecl.name), decl.name))
                    )
            );
            treeMapDecl.defs = treeMapDecl.defs.append(registerSimpleBlockItem);

//            System.out.println(treeMapDecl);
        }
    }
}
