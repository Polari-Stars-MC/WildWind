package org.polaris2023.ext.config;

import org.polaris2023.ConfigGeneratedRecord;
import org.polaris2023.annotation.AutoConfig;
import org.polaris2023.annotation.config.*;
import org.polaris2023.ext.ClassProcessor;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigProcessor extends ClassProcessor {
    public ConfigProcessor(Element element, ProcessingEnvironment env) {
        super(element, env);
    }

    @Override
    public void processor() {
        AutoConfig autoConfig = getCheck().getAnnotation(AutoConfig.class);
        if (autoConfig != null) {
            List<ConfigGeneratedRecord> list = new ArrayList<>();

            StringBuilder note = new StringBuilder();

            Note noteAnno = getCheck().getAnnotation(Note.class);
            if (noteAnno != null) {
                note.append("\t\tBUILDER");

                for (String s : noteAnno.value()) {
                    note
                            .append(".comment(\"")
                            .append(s)
                            .append("\")");
                }
            }
            Push push = getCheck().getAnnotation(Push.class);
            if (push != null) {
                note
                        .append(".push(\"")
                        .append(push.value())
                        .append("\")");
            }
            if (!note.isEmpty())
                note.append(";");
            StringBuilder isb = new StringBuilder();
            for (Element enclosedElement : getCheck().getEnclosedElements()) {
//                System.out.format("%s ->%s", enclosedElement.getSimpleName(), enclosedElement.asType().getKind());
                switch (enclosedElement.asType().getKind()) {
                    case INT -> {

                        DefineIntRange defineIntRange = enclosedElement.getAnnotation(DefineIntRange.class);
                        if (defineIntRange != null) {
                            list.add(new ConfigGeneratedRecord(
                                    "ModConfigSpec.IntValue",
                                    enclosedElement,
                                    "defineInRange(\"%s\", %s, %s, %s)".formatted(
                                            defineIntRange.value().isEmpty() ? enclosedElement.getSimpleName(): defineIntRange.value(),
                                            defineIntRange.defaultValue(),
                                            defineIntRange.min(),
                                            defineIntRange.max()
                                    ), getCheck()));

                        }
                    }
                    case LONG -> {
                        DefineLongRange defineLongRange = enclosedElement.getAnnotation(DefineLongRange.class);
                        if (defineLongRange != null) {
                            list.add(new ConfigGeneratedRecord(
                                    "ModConfigSpec.LongValue",
                                    enclosedElement,
                                    "defineInRange(\"%s\", %s, %s, %s)".formatted(
                                            defineLongRange.value().isEmpty() ? enclosedElement.getSimpleName(): defineLongRange.value(),
                                            defineLongRange.defaultValue(),
                                            defineLongRange.min(),
                                            defineLongRange.max()
                                    ),
                                    getCheck()));
                        }
                    }
                    case DOUBLE -> {
                        DefineDoubleRange defineDoubleRange = enclosedElement.getAnnotation(DefineDoubleRange.class);
                        if (defineDoubleRange != null) {
                            list.add(new ConfigGeneratedRecord(
                                    "ModConfigSpec.DoubleValue",
                                    enclosedElement,
                                    "defineInRange(\"%s\", %s, %s, %s)"
                                            .formatted(
                                                    defineDoubleRange.value().isEmpty() ? enclosedElement.getSimpleName(): defineDoubleRange.value(),
                                                    defineDoubleRange.defaultValue(),
                                                    defineDoubleRange.min(),
                                                    defineDoubleRange.max())
                                    ,getCheck()));

                        }
                    }
                    case BOOLEAN -> {

                        Define define = enclosedElement.getAnnotation(Define.class);
                        if (define != null) {
                            list.add(new ConfigGeneratedRecord(
                                    "ModConfigSpec.BooleanValue",
                                    enclosedElement,
                                    "define(\"%s\", %s)".formatted(
                                            define.value().isEmpty() ? enclosedElement.getSimpleName(): define.value(),
                                            define.defaultValue()
                                    ),getCheck()));

                        }
                    }
                    case DECLARED -> {
                        DefineEnum defineEnum = enclosedElement.getAnnotation(DefineEnum.class);


                        if (defineEnum != null) {
                            list.add(new ConfigGeneratedRecord(
                                    "ModConfigSpec.EnumValue<%s>".formatted(enclosedElement.asType().toString()),
                                    enclosedElement,
                                    MessageFormat.format("BUILDER.defineEnum(\"{1}\", {0}.{2})",
                                            enclosedElement.getSimpleName(),
                                            defineEnum.value().isEmpty() ? enclosedElement.getSimpleName(): defineEnum.value(),
                                            defineEnum.defaultValue()),
                                    getCheck()
                            ));
                        }
                    }

                }
                if (enclosedElement.getKind().isClass()) {
                    if (enclosedElement.getAnnotation(SubConfig.class) != null) {
                        subConfig((TypeElement) enclosedElement, getCheck(), 1);

                    }

                }
            }

            StringBuilder psf = new StringBuilder();
            StringBuilder stc = new StringBuilder();
            StringBuilder evt = new StringBuilder();
            for (ConfigGeneratedRecord cgr : list) {
                psf.append("\tstatic final %s %s;".formatted(cgr.className(), cgr.fieldName().getSimpleName()));
                StringBuilder _note = new StringBuilder();
                Note _n = cgr.fieldName().getAnnotation(Note.class);
                if (_n != null) {
                    for (String s : _n.value()) {
                        _note.append(".comment(\"%s\")".formatted(s));
                    }
                    _note.append(";");
                }
                stc.append("\t\t%s = BUILDER%s.%s;\n".formatted( cgr.fieldName().getSimpleName(), _note, cgr.code()));
                evt.append(MessageFormat.format("\t\t{0}.{1} = {1}.get();\n", cgr.check().getSimpleName(), cgr.fieldName().getSimpleName()));
            }
            try {
                JavaFileObject sourceFile = getFiler().createSourceFile(getCheck().getQualifiedName() + "Impl");
                try (Writer writer = sourceFile.openWriter()) {

                    writer.write(MessageFormat
                            .format("""
                                    package {0};
                                    
                                    import net.neoforged.neoforge.common.ModConfigSpec;
                                    import net.neoforged.fml.common.EventBusSubscriber;
                                    import net.neoforged.fml.common.EventBusSubscriber.Bus;
                                    import net.neoforged.fml.event.config.ModConfigEvent;
                                    import net.neoforged.bus.api.SubscribeEvent;
                                    import net.neoforged.fml.ModContainer;
                                    import net.neoforged.fml.config.ModConfig;
                                    
                                    @EventBusSubscriber(
                                    \tmodid = "{1}",
                                    \tbus = Bus.MOD
                                    )
                                    class {2} '{
                                    \tstatic final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
                                    '{6}'
                                    '{9}'
                                    \tstatic final ModConfigSpec SPEC;
                                    \tstatic {
                                    '{4}
                                    {7}{5}'
                                    \t\tSPEC = BUILDER.build();
                                    \t}
                                    \t@SubscribeEvent
                                    \tstatic void onLoad(final ModConfigEvent event)
                                    \t{
                                    '{8}'
                                    \t}
                                
                                    \tpublic static void register(ModContainer modContainer) {
                                    \t\tmodContainer.registerConfig(ModConfig.Type.'{3}', SPEC);
                                    \t}
                                    }
                                    '
                                    """.stripIndent(),
                                    getCheck().getQualifiedName().toString().replace("."+getCheck().getSimpleName(), ""),
                                    autoConfig.modid(),
                                    getCheck().getSimpleName()+"Impl",
                                    autoConfig.value().name(),
                                    note,
                                    note.isEmpty() ? "": "\t\tBUILDER.pop();",
                                    psf,
                                    stc,
                                    evt,
                                    isb)
                    );
                }

            } catch (IOException ignored) {

            }


        }
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
