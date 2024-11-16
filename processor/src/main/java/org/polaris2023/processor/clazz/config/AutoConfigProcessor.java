package org.polaris2023.processor.clazz.config;

import com.squareup.javapoet.*;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.VariableTree;
import com.sun.source.util.TreeScanner;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.parser.JavacParser;
import com.sun.tools.javac.parser.ParserFactory;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.List;
import org.polaris2023.annotation.AutoConfig;
import org.polaris2023.annotation.config.*;
import org.polaris2023.processor.InitProcessor;
import org.polaris2023.processor.clazz.ClassProcessor;
import org.polaris2023.utils.JCUtils;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.text.MessageFormat;

public class AutoConfigProcessor extends ClassProcessor {

    public AutoConfigProcessor(JavacProcessingEnvironment environment) {
        super(environment);
    }

    @Override
    public void classDef(TypeElement typeElement) {
        AutoConfig autoConfig = typeElement.getAnnotation(AutoConfig.class);
        if (autoConfig != null) {

            TypeSpec.Builder builder =TypeSpec
                    .classBuilder( typeElement.getSimpleName()+"Impl")
                    .addModifiers(Modifier.PUBLIC);

            builder.addSuperinterface(ClassName.bestGuess("org.polaris2023.wild_wind.util.interfaces.IConfig"));
            MethodSpec.Builder event = MethodSpec
                    .methodBuilder("onLoad")
                    .addModifiers(Modifier.STATIC)
                    .returns(TypeName.VOID)
                    .addParameter(ParameterSpec.builder(
                                    ClassName.bestGuess("net.neoforged.fml.event.config.ModConfigEvent"),
                                    "event",
                                    Modifier.FINAL)
                            .build())
                    .addAnnotation(AnnotationSpec.builder(
                                    ClassName.bestGuess("net.neoforged.bus.api.SubscribeEvent")
                            )
                            .build());
            builder.addAnnotation(AnnotationSpec
                    .builder(ClassName.bestGuess("net.neoforged.fml.common.EventBusSubscriber"))
                    .addMember("modid", "\"" + autoConfig.modid() +"\"")
                    .addMember("bus", "EventBusSubscriber.Bus.MOD")
                    .build());
            ClassName modConfigSpec = ClassName.get("net.neoforged.neoforge.common", "ModConfigSpec");

            builder.addField(FieldSpec
                    .builder(
                            modConfigSpec.nestedClass("Builder"),
                            "BUILDER", Modifier.STATIC, Modifier.FINAL
                    )
                    .initializer("new ModConfigSpec.Builder()")
                    .build());
            CodeBlock.Builder code = noteAndPushCode(typeElement, false);


            for (Element enclosedElement : typeElement.getEnclosedElements()) {
                generatedCodeBy(enclosedElement,
                        builder,
                        modConfigSpec,
                        enclosedElement.getSimpleName().toString(),
                        code,
                        event,
                        typeElement);


            }
            if (!code.isEmpty()) {
                code.addStatement("BUILDER.pop()");
            }
            builder.addField(FieldSpec
                    .builder(modConfigSpec, "SPEC", Modifier.FINAL, Modifier.STATIC)
                    .build());
            code.addStatement("SPEC = BUILDER.build()");
            builder.addStaticBlock(code.build());

            builder.addMethod(event.build());
            builder
                    .addMethod(MethodSpec
                            .methodBuilder("register")
                            .addModifiers(Modifier.PUBLIC)
                            .returns(TypeName.VOID)
                            .addParameter(ParameterSpec
                                    .builder(
                                            ClassName.bestGuess("net.neoforged.fml.ModContainer"),
                                            "modContainer")
                                    .build())
                            .addStatement("modContainer.registerConfig(net.neoforged.fml.config.ModConfig.Type.%s, SPEC)"
                                    .formatted(autoConfig.value().name()))
                            .build());



            JavaFile jf = JavaFile.builder(typeElement
                    .getQualifiedName()
                    .toString()
                    .replace("."+typeElement.getSimpleName(), ""), builder.build()).build();
            InitProcessor.add("org.polaris2023.wild_wind.util.interfaces.IConfig", jf.packageName + "." + builder.build().name);
            try {
                jf.writeTo(getFiler());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

//            try {
//                JavaFileObject sourceFile = getFiler().createSourceFile(getCheck().getQualifiedName() + "Impl");
//                try (Writer writer = sourceFile.openWriter()) {
//
//                    writer.write(MessageFormat
//                            .format("""
//                                    package {0};
//
//                                    import net.neoforged.neoforge.common.ModConfigSpec;
//                                    import net.neoforged.fml.common.EventBusSubscriber;
//                                    import net.neoforged.fml.common.EventBusSubscriber.Bus;
//                                    import net.neoforged.fml.event.config.ModConfigEvent;
//                                    import net.neoforged.bus.api.SubscribeEvent;
//                                    import net.neoforged.fml.ModContainer;
//                                    import net.neoforged.fml.config.ModConfig;
//
//                                    @EventBusSubscriber(
//                                    \tmodid = "{1}",
//                                    \tbus = Bus.MOD
//                                    )
//                                    class {2} '{
//                                    \tstatic final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
//                                    '{6}'
//                                    '{9}'
//                                    \tstatic final ModConfigSpec SPEC;
//                                    \tstatic {
//                                    '{4}
//                                    {7}{5}'
//                                    \t\tSPEC = BUILDER.build();
//                                    \t}
//                                    \t@SubscribeEvent
//                                    \tstatic void onLoad(final ModConfigEvent event)
//                                    \t{
//                                    '{8}'
//                                    \t}
//
//                                    \tpublic static void register(ModContainer modContainer) {
//                                    \t\tmodContainer.registerConfig(ModConfig.Type.'{3}', SPEC);
//                                    \t}
//                                    }
//                                    '
//                                    """.stripIndent(),
//                                    getCheck().getQualifiedName().toString().replace("."+getCheck().getSimpleName(), ""),
//                                    autoConfig.modid(),
//                                    getCheck().getSimpleName()+"Impl",
//                                    autoConfig.value().name(),
//                                    note,
//                                    note.isEmpty() ? "": "\t\tBUILDER.pop();",
//                                    psf,
//                                    stc,
//                                    evt,
//                                    isb)
//                    );
//                }
//
//            } catch (IOException ignored) {
//
//            }


        }
    }

    private static void generatedCodeBy(Element element, TypeSpec.Builder builder, ClassName modConfigSpec, String name, CodeBlock.Builder code, MethodSpec.Builder event, TypeElement check) {
        switch (element.asType().getKind()) {
            case INT -> intRange(element, builder, modConfigSpec, name, code, event, check);
            case LONG -> longRange(element, builder, modConfigSpec, name, code, event, check);
            case DOUBLE -> doubleRange(element, builder, modConfigSpec, name, code, event, check);
            case BOOLEAN -> booleanDefine(element, builder, modConfigSpec, name, code, event, check);
            case DECLARED -> enumDefine(element, builder, modConfigSpec, name, code, event, check);

        }
        if (element.getKind().isClass()) {
            if (element.getAnnotation(SubConfig.class) != null) {
                TypeSpec.Builder builder1 = TypeSpec
                        .classBuilder(name);
                CodeBlock.Builder codeInner = noteAndPushCode(element, false);
                for (Element enclosedElement : element.getEnclosedElements()) {
                    generatedCodeBy(enclosedElement, builder1, modConfigSpec, enclosedElement.getSimpleName().toString(), codeInner, event, (TypeElement) element);
                }
                if (!codeInner.isEmpty()) {
                    codeInner.addStatement("BUILDER.pop()");
                }
                builder1
                        .addMethod(MethodSpec
                                .methodBuilder("bootstrap")
                                .addModifiers(Modifier.STATIC)
                                .build());
                code.addStatement("%s.bootstrap()".formatted(builder1.build().name));
                builder1.addStaticBlock(codeInner.build());
                builder.addType(builder1.build());
            }

        }
    }

    private static void enumDefine(Element enclosedElement, TypeSpec.Builder builder, ClassName modConfigSpec, String name, CodeBlock.Builder code, MethodSpec.Builder event, TypeElement check) {
        DefineEnum defineEnum = enclosedElement.getAnnotation(DefineEnum.class);
        if (defineEnum != null) {
            builder.addField(FieldSpec.builder(modConfigSpec.nestedClass("EnumValue<" + enclosedElement.asType().toString() + ">"),
                    name, Modifier.STATIC, Modifier.FINAL
            ).build());
            code.add(noteAndPushCode(enclosedElement, true).build());
            code.addStatement(MessageFormat.format(" {0} = BUILDER.defineEnum(\"{1}\", {2}.{3})",
                    name,
                    defineEnum.value().isEmpty() ? enclosedElement.getSimpleName(): defineEnum.value(),
                    enclosedElement.asType().toString(),
                    defineEnum.defaultValue()));
            event.addCode("%s.%s = %s.%s.get();".formatted(check.getQualifiedName(), name, builder.build().name,name));
        }
    }

    private static void booleanDefine(Element enclosedElement, TypeSpec.Builder builder, ClassName modConfigSpec, String name, CodeBlock.Builder code, MethodSpec.Builder event, TypeElement check) {
        Define define = enclosedElement.getAnnotation(Define.class);
        if (define != null) {
            builder.addField(modConfigSpec.nestedClass("BooleanValue"),
                    name, Modifier.STATIC, Modifier.FINAL);
            code.add(noteAndPushCode(enclosedElement, true).build());
            code.addStatement("%s = BUILDER.define(\"%s\", %b)".formatted(
                    name,
                    define.value().isEmpty() ? enclosedElement.getSimpleName(): define.value(),
                    define.defaultValue()
            ));
            event.addCode("%s.%s = %s.%s.get();".formatted(check.getQualifiedName(), name, builder.build().name,name));
        }
    }

    private static void intRange(Element enclosedElement, TypeSpec.Builder builder, ClassName modConfigSpec, String name, CodeBlock.Builder code, MethodSpec.Builder event, TypeElement check) {
        DefineIntRange defineIntRange = enclosedElement.getAnnotation(DefineIntRange.class);
        if (defineIntRange != null) {

            builder.addField(FieldSpec
                    .builder(
                            modConfigSpec.nestedClass("IntValue"),
                            name, Modifier.FINAL, Modifier.STATIC)
                    .build());

            code.add(noteAndPushCode(enclosedElement, true).build());
            code.addStatement("%s = BUILDER.defineInRange(\"%s\", %d, %d, %d)"
                    .formatted(
                            name,
                            defineIntRange.value().isEmpty() ?
                                    name :
                                    defineIntRange.value(),
                            defineIntRange.defaultValue(),
                            defineIntRange.min(),
                            defineIntRange.max()
                    ));
            event.addCode("%s.%s = %s.%s.get();".formatted(check.getQualifiedName(), name, builder.build().name,name));
        }
    }

    private static void doubleRange(Element enclosedElement, TypeSpec.Builder builder, ClassName modConfigSpec, String name, CodeBlock.Builder code, MethodSpec.Builder event, TypeElement check) {
        DefineDoubleRange defineDoubleRange = enclosedElement.getAnnotation(DefineDoubleRange.class);
        if (defineDoubleRange != null) {
            builder.addField(FieldSpec
                    .builder(modConfigSpec.nestedClass("DoubleValue"),
                            name, Modifier.FINAL, Modifier.STATIC)
                    .build());
            code.add(noteAndPushCode(enclosedElement, true).build());
            code.addStatement("%s = BUILDER.defineInRange(\"%s\", %s, %s, %s)"
                    .formatted(
                            name,
                            defineDoubleRange.value().isEmpty() ? enclosedElement.getSimpleName(): defineDoubleRange.value(),
                            defineDoubleRange.defaultValue(),
                            defineDoubleRange.min(),
                            defineDoubleRange.max()));
            event.addCode("%s.%s = %s.%s.get();".formatted(check.getQualifiedName(), name, builder.build().name,name));
        }
    }

    private static void longRange(Element enclosedElement, TypeSpec.Builder builder, ClassName modConfigSpec, String name, CodeBlock.Builder code, MethodSpec.Builder event, TypeElement check) {
        DefineLongRange defineLongRange = enclosedElement.getAnnotation(DefineLongRange.class);
        if (defineLongRange != null) {
            builder.addField(FieldSpec
                    .builder(modConfigSpec.nestedClass("LongValue"),
                            name, Modifier.FINAL, Modifier.STATIC)
                    .build());
            code.add(noteAndPushCode(enclosedElement, true).build());
            code.addStatement("%s= BUILDER.defineInRange(\"%s\", %dL, %dL, %dL)".formatted(
                    name,
                    defineLongRange.value().isEmpty() ?
                            name :
                            defineLongRange.value(),
                    defineLongRange.defaultValue(),
                    defineLongRange.min(),
                    defineLongRange.max()
            ));
            event.addCode("%s.%s = %s.%s.get();".formatted(check.getQualifiedName(), name, builder.build().name,name));
        }
    }

    private static CodeBlock.Builder noteAndPushCode(Element check, boolean isEntry) {
        CodeBlock.Builder builderStaticCode = CodeBlock.builder();
        Note note = check.getAnnotation(Note.class);
        if (note != null) {
            for (String value : note.value()) {
                builderStaticCode
                        .addStatement("BUILDER.comment(\"" + value + "\")");
            }
        }
        if (!isEntry) {
            Push push = check.getAnnotation(Push.class);
            if (push != null) {
                builderStaticCode
                        .addStatement("BUILDER.push(\"" + push.value() + "\")");
            }
        }
        return builderStaticCode;
    }
}
