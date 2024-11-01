package org.polaris2023.ext.config;

import com.squareup.javapoet.*;
import org.polaris2023.ConfigGeneratedRecord;
import org.polaris2023.annotation.AutoConfig;
import org.polaris2023.annotation.config.*;
import org.polaris2023.ext.ClassProcessor;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.*;

public class ConfigProcessor extends ClassProcessor {
    public ConfigProcessor(Element element, ProcessingEnvironment env) {
        super(element, env);
    }

    @Override
    public void processor() {
        AutoConfig autoConfig = getCheck().getAnnotation(AutoConfig.class);
        if (autoConfig != null) {
            List<ConfigGeneratedRecord> list = new ArrayList<>();

            TypeSpec.Builder builder =TypeSpec
                    .classBuilder( getCheck().getSimpleName()+"Impl");
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
            CodeBlock.Builder code = noteAndPushCode(getCheck(), false);


            for (Element enclosedElement : getCheck().getEnclosedElements()) {
                String name = enclosedElement.getSimpleName().toString();
                switch (enclosedElement.asType().getKind()) {
                    case INT -> intRange(enclosedElement, builder, modConfigSpec, name, code);
                    case LONG -> longRange(enclosedElement, builder, modConfigSpec, name, code);
                    case DOUBLE -> doubleRange(enclosedElement, builder, modConfigSpec, name, code);
                    case BOOLEAN -> booleanDefine(enclosedElement, builder, modConfigSpec, name, code);
                    case DECLARED -> {
                        DefineEnum defineEnum = enclosedElement.getAnnotation(DefineEnum.class);


                        if (defineEnum != null) {
                            enumDefine(enclosedElement, builder, modConfigSpec, name, code, defineEnum);
                        }
                    }

                }

                if (enclosedElement.getKind().isClass()) {
                    if (enclosedElement.getAnnotation(SubConfig.class) != null) {
                        subConfig((TypeElement) enclosedElement, getCheck(), 1);

                    }

                }
            }
            if (!code.isEmpty()) {
                code.addStatement("BUILDER.pop()");
            }
            builder.addStaticBlock(code.build());
            builder.addField(FieldSpec
                    .builder(modConfigSpec, "SPEC", Modifier.FINAL, Modifier.STATIC)
                            .initializer("BUILDER.build()")
                    .build());




            JavaFile jf = JavaFile.builder(getCheck()
                    .getQualifiedName()
                    .toString()
                    .replace("."+getCheck().getSimpleName(), ""), builder.build()).build();
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

    private static void enumDefine(Element enclosedElement, TypeSpec.Builder builder, ClassName modConfigSpec, String name, CodeBlock.Builder code, DefineEnum defineEnum) {
        builder.addField(FieldSpec.builder(modConfigSpec.nestedClass("EnumValue<" + enclosedElement.asType().toString() + ">"),
                name, Modifier.STATIC, Modifier.FINAL
        ).build());
        code.add(noteAndPushCode(enclosedElement, true).build());
        code.addStatement(MessageFormat.format(" {0} = BUILDER.defineEnum(\"{1}\", {2}.{3})",
                name,
                defineEnum.value().isEmpty() ? enclosedElement.getSimpleName(): defineEnum.value(),
                enclosedElement.asType().toString(),
                defineEnum.defaultValue()));
    }

    private static void booleanDefine(Element enclosedElement, TypeSpec.Builder builder, ClassName modConfigSpec, String name, CodeBlock.Builder code) {
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

        }
    }

    private static void intRange(Element enclosedElement, TypeSpec.Builder builder, ClassName modConfigSpec, String name, CodeBlock.Builder code) {
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
        }
    }

    private static void doubleRange(Element enclosedElement, TypeSpec.Builder builder, ClassName modConfigSpec, String name, CodeBlock.Builder code) {
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

        }
    }

    private static void longRange(Element enclosedElement, TypeSpec.Builder builder, ClassName modConfigSpec, String name, CodeBlock.Builder code) {
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

    public void subConfig(TypeElement targetElement, TypeElement check, int i) {
        String impl = check.getSimpleName() + "Impl";
        for (Element enclosedElement : targetElement.getEnclosedElements()) {
            System.out.println(enclosedElement.getSimpleName());
        }
//        MessageFormat.format("""
//                {0}static class {1}'
//                '{0}'{
//                '{0}'
//                '{0}'}
//                '
//                """.stripIndent(),
//                "\t".repeat(i + 1),
//                targetElement.getSimpleName())



    }
}
