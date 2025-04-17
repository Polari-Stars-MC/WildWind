package org.polaris2023.processor.clazz.registry;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.List;
import org.polaris2023.annotation.enums.RegType;
import org.polaris2023.annotation.register.attachments.AttachmentBoolean;
import org.polaris2023.annotation.register.attachments.AttachmentInteger;
import org.polaris2023.utils.JCUtils;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.util.Locale;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/06 18:02:28}
 */
public class AttachmentRegistryProcessor extends RegistryProcessor {
    @Override
    public RegType defaultType() {
        return RegType.AttachmentType;
    }

    public AttachmentRegistryProcessor(JavacProcessingEnvironment environment) {
        super(environment);
    }

    @Override
    public void fieldDef(VariableElement variableElement, TypeElement typeElement) {
        // ModAttachmentTypes.REGISTER
        //                   .->
        JCTree.JCVariableDecl registryField = getRegistryField();
        // ModAttachmentTypes.REGISTER
        //                 <-.
        JCTree.JCClassDecl registerTree = getRegisterTree();
        // ModAttachmentTypes.MILKING_INTERVALS
        //                   .->
        JCTree.JCVariableDecl decl = (JCTree.JCVariableDecl) trees.getTree(variableElement);
        AttachmentInteger i = variableElement.getAnnotation(AttachmentInteger.class);
        AttachmentBoolean ab = variableElement.getAnnotation(AttachmentBoolean.class);
        Object defaultValue = 0;
        boolean b = false;
        JCTree.JCFieldAccess codec = null;
        if (i != null) {
            b = true;
            defaultValue = i.defaultValue();
            codec = maker.Select(
                    JCUtils.Ident.Codec.get(maker, names),
                    names.fromString("INT")
            );
        } else if (ab != null) {
            b = true;
            defaultValue = ab.defaultValue();
            codec = maker.Select(
                    JCUtils.Ident.Codec.get(maker, names),
                    names.fromString("BOOL")
            );
        }
        if (b) {
            decl.mods = maker.Modifiers(decl.mods.flags | Flags.FINAL, decl.mods.annotations);
            JCTree.JCLambda innerDefaultLambda = maker.Lambda(List.nil(), maker.Literal(defaultValue));
            JCTree.JCMethodInvocation builderCall = maker.Apply(
                    List.nil(),
                    maker.Select(
                            JCUtils.Ident.AttachmentType.get(maker, names),
                            names.fromString("builder")
                    ),
                    List.of(innerDefaultLambda)
            );

            JCTree.JCMethodInvocation serializeCall = maker.Apply(
                    List.nil(),
                    maker.Select(
                            builderCall,
                            names.fromString("serialize")
                    ),
                    List.of(codec)
            );
            JCTree.JCMethodInvocation buildCall = maker.Apply(
                    List.nil(),
                    maker.Select(
                            serializeCall,
                            names.fromString("build")
                    ),
                    List.nil()
            );
            JCTree.JCLambda outerLambda = maker.Lambda(List.nil(), buildCall);
            decl.init = maker.Apply(
                    List.nil(),
                    maker.Select(JCUtils.makeIdent(maker, names, registerTree.name + "." + registryField.name),
                            names.fromString("register")),
                    List.of(
                            maker.Literal(decl.name.toString().toLowerCase(Locale.ROOT)),
                            outerLambda
                    )
            );
        }
    }
}
