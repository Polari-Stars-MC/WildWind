package org.polaris2023.processor.clazz.registry;

import com.sun.source.tree.MethodTree;
import com.sun.source.tree.VariableTree;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import org.polaris2023.annotation.enums.RegType;
import org.polaris2023.annotation.register.RegistryBlockItem;
import org.polaris2023.processor.InitProcessor;
import org.polaris2023.utils.JCUtils;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/02 18:34:47}
 */
public class ItemRegistryProcessor extends RegistryProcessor {

    @Override
    public RegType defaultType() {
        return RegType.Item;
    }

    public ItemRegistryProcessor(JavacProcessingEnvironment environment) {
        super(environment);
    }

    @Override
    public void fieldDef(VariableElement variableElement, TypeElement typeElement) {
        JCTree.JCVariableDecl variableTree = getRegistryField();
        JCTree.JCClassDecl classTree = getRegisterTree();
        JCTree.JCClassDecl classDecl = (JCTree.JCClassDecl) trees.getTree(typeElement);
        JCTree.JCVariableDecl decl = (JCTree.JCVariableDecl) trees.getTree(variableElement);

        RegistryBlockItem blockItem = variableElement.getAnnotation(RegistryBlockItem.class);

        if (blockItem != null) {
            JCTree.JCMethodDecl registryBlockItem = (JCTree.JCMethodDecl) InitProcessor.REGISTRY_BLOCK_ITEM;
            JCTree.JCVariableDecl registryExpr = registryBlockItem.params.getFirst();
            JCTree.JCFieldAccess block = maker.Select(
                    maker.QualIdent(classDecl.sym),
                    decl.sym
            );
            JCTree.JCMethodInvocation getId = maker.Apply(
                    List.of(JCUtils.Ident.Block.get(maker, names)),
                    maker.Select(
                            block,
                            names.fromString("getId")
                    ),
                    List.nil()
            );
            JCTree.JCMethodInvocation get = maker.Apply(
                    List.of(JCUtils.Ident.Block.get(maker, names)),
                    maker.Select(
                            block,
                            names.fromString("get")
                    ),
                    List.nil()
            );
            JCTree.JCNewClass propertiesNew;
            propertiesNew = maker.NewClass(
                    null,
                    List.nil(),
                    JCUtils.Ident.ItemProperties.get(maker, names),
                    List.nil(),
                    null
            );
            JCTree.JCNewClass blockItemNew = maker.NewClass(
                    null,
                    List.nil(),
                    JCUtils.Ident.BlockItem.get(maker, names),
                    List.of(get, propertiesNew),
                    null
            );


            JCTree.JCMethodInvocation accept = maker.Apply(
                    List.of(
                            JCUtils.Ident.ResourceLocation.get(maker, names),
                            JCUtils.Ident.Item.get(maker, names)
                    ),
                    maker.Select(
                            maker.QualIdent(registryExpr.sym),
                            names.fromString("accept")
                    ),
                    List.of(getId, blockItemNew)
            );
            JCTree.JCStatement exec = maker.Exec(accept);
            System.out.println(exec);
            JCTree.JCBlock body = registryBlockItem.body;
            List<JCTree.JCStatement> old = body.stats;
            registryBlockItem.body.stats = old != null ?
                    old.append(exec) : List.of(exec);


//            JCTree.JCVariableDecl registerSimpleBlockItem = maker.VarDef(
//                    maker.Modifiers(Flags.PUBLIC | Flags.FINAL | Flags.STATIC),
//                    decl.getName(),
//                    maker.TypeApply(JCUtils.Ident.DeferredItem.get(maker, names),
//                            List.of(JCUtils.Ident.BlockItem.get(maker, names))),
//                    maker.Apply(
//                            List.nil(),
//                            maker.Select(
//                                    JCUtils.makeIdent(maker, names, classTree.name + "." + variableTree.name),
//                                    names.fromString("registerSimpleBlockItem")
//                            ),
//                            List.of(maker.Select(maker.Ident(classDecl.name), decl.name))
//                    )
//            );
//            classTree.defs = classTree.defs.append(registerSimpleBlockItem);

//            System.out.println(treeMapDecl);
        }
    }
}
