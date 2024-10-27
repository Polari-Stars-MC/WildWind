package org.polaris2023.ext.config;

import org.polaris2023.annotation.AutoConfig;
import org.polaris2023.annotation.config.*;
import org.polaris2023.ext.ClassProcessor;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;

public class ConfigProcessor extends ClassProcessor {
    public ConfigProcessor(Element element, ProcessingEnvironment env) {
        super(element, env);
    }

    @Override
    public void processor() {
        AutoConfig annotation = getCheck().getAnnotation(AutoConfig.class);
        if (annotation != null) {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for (Element enclosedElement : getCheck().getEnclosedElements()) {
                switch (enclosedElement.asType().getKind()) {
                    case INT -> {
                        DefineIntRange defineIntRange = enclosedElement.getAnnotation(DefineIntRange.class);
                        if (defineIntRange != null) {
                            sb.append("\tprivate static final ModConfigSpec.IntValue %s = BUILDER.defineInRange(\"%s\", %d, %d, %d);\n"
                                    .formatted(
                                            enclosedElement.getSimpleName(),
                                            defineIntRange.value().isEmpty() ? enclosedElement.getSimpleName(): defineIntRange.value(),
                                            defineIntRange.defaultValue(),
                                            defineIntRange.min(),
                                            defineIntRange.max()));
                            sb1.append("\t\t%s.%s = %s.get();\n".formatted(
                                    getCheck().getSimpleName(),
                                    enclosedElement.getSimpleName(),
                                    enclosedElement.getSimpleName()
                            ));
                        }
                    }
                    case LONG -> {
                        DefineLongRange defineIntRange = enclosedElement.getAnnotation(DefineLongRange.class);
                        if (defineIntRange != null) {
                            sb.append("\tprivate static final ModConfigSpec.LongValue %s = BUILDER.defineInRange(\"%s\", %dL, %dL, %dL);\n"
                                    .formatted(
                                            enclosedElement.getSimpleName(),
                                            defineIntRange.value().isEmpty() ? enclosedElement.getSimpleName(): defineIntRange.value(),
                                            defineIntRange.defaultValue(),
                                            defineIntRange.min(),
                                            defineIntRange.max()));
                            sb1.append("\t\t%s.%s = %s.get();\n".formatted(
                                    getCheck().getSimpleName(),
                                    enclosedElement.getSimpleName(),
                                    enclosedElement.getSimpleName()
                            ));
                        }
                    }
                    case DOUBLE -> {
                        DefineDoubleRange defineIntRange = enclosedElement.getAnnotation(DefineDoubleRange.class);
                        if (defineIntRange != null) {
                            sb.append("\tprivate static final ModConfigSpec.DoubleValue %s = BUILDER.defineInRange(\"%s\", %f, %f, %f);\n"
                                    .formatted(
                                            enclosedElement.getSimpleName(),
                                            defineIntRange.value().isEmpty() ? enclosedElement.getSimpleName(): defineIntRange.value(),
                                            defineIntRange.defaultValue(),
                                            defineIntRange.min(),
                                            defineIntRange.max()));
                            sb1.append("\t\t%s.%s = %s.get();\n".formatted(
                                    getCheck().getSimpleName(),
                                    enclosedElement.getSimpleName(),
                                    enclosedElement.getSimpleName()
                            ));
                        }
                    }
                    case BOOLEAN -> {
                        Define define = enclosedElement.getAnnotation(Define.class);
                        if (define != null) {
                            sb.append("\tprivate static final ModConfigSpec.BooleanValue %s = BUILDER.define(\"%s\", %b);\n"
                                    .formatted(
                                            enclosedElement.getSimpleName(),
                                            define.value().isEmpty() ? enclosedElement.getSimpleName(): define.value(),
                                            define.defaultValue()));
                            sb1.append("\t\t%s.%s = %s.get();\n".formatted(
                                    getCheck().getSimpleName(),
                                    enclosedElement.getSimpleName(),
                                    enclosedElement.getSimpleName()
                            ));
                        }
                    }
                    case DECLARED -> {
                        DefineEnum defineEnum = enclosedElement.getAnnotation(DefineEnum.class);


                        if (defineEnum != null) {

                            sb.append("\tprivate static final ModConfigSpec.EnumValue<%s> %s = BUILDER.defineEnum(\"%s\", %s.%s);\n".formatted(
                                   enclosedElement.asType().toString(),
                                   enclosedElement.getSimpleName(),
                                   defineEnum.value().isEmpty() ? enclosedElement.getSimpleName(): defineEnum.value(),
                                   enclosedElement.asType().toString(),
                                   defineEnum.defaultValue()));
                        }
                    }
                }

            }
            try {
                JavaFileObject sourceFile = getFiler().createSourceFile(getCheck().getQualifiedName() + "Impl");
                try (Writer writer = sourceFile.openWriter()) {

                    writer.write("""
                                package %s;
                                
                                import net.neoforged.neoforge.common.ModConfigSpec;
                                import net.neoforged.fml.common.EventBusSubscriber;
                                import net.neoforged.fml.common.EventBusSubscriber.Bus;
                                import net.neoforged.fml.event.config.ModConfigEvent;
                                import net.neoforged.bus.api.SubscribeEvent;
                                import net.neoforged.fml.ModContainer;
                                import net.neoforged.fml.config.ModConfig;
                                
                                @EventBusSubscriber(
                                    modid = "%s",
                                    bus = Bus.MOD
                                )
                                public class %s {
                                    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
                                
                                %s
                                
                                    static final ModConfigSpec SPEC = BUILDER.build();
                                
                                    @SubscribeEvent
                                    static void onLoad(final ModConfigEvent event)
                                    {
                                %s
                                    }
                                
                                    public static void register(ModContainer modContainer) {
                                        modContainer.registerConfig(ModConfig.Type.%s, SPEC);
                                    }
                                }
                                """.stripIndent()
                            .formatted(
                                    getCheck().getQualifiedName().toString().replace("."+getCheck().getSimpleName(), ""),
                                    annotation.modid(),
                                    getCheck().getSimpleName()+"Impl",
                                    sb.toString(),
                                    sb1.toString(),
                                    annotation.value().name()
                            )
                    );
                }
            } catch (IOException ignored) {

            }

        }
    }
}
