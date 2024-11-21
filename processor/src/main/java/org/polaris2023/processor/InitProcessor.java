package org.polaris2023.processor;

import com.google.auto.service.AutoService;
import com.google.common.reflect.TypeToken;
import com.squareup.javapoet.*;
import com.sun.source.tree.MethodTree;
import com.sun.source.util.Trees;
import com.sun.tools.javac.parser.JavacParser;
import com.sun.tools.javac.parser.ParserFactory;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Context;
import org.polaris2023.processor.clazz.ClassProcessor;
import org.polaris2023.processor.clazz.config.AutoConfigProcessor;
import org.polaris2023.processor.clazz.datagen.I18nProcessor;
import org.polaris2023.processor.jc.ModifierProcessor;
import org.polaris2023.processor.pack.PackageProcessor;
import org.polaris2023.utils.Costs;
import org.polaris2023.utils.Unsafe;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.polaris2023.utils.Costs.ClassNames.*;

@AutoService(Processor.class)
@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class InitProcessor extends AbstractProcessor {

    static {
        Unsafe.exportJdkModule();
    }

    public static final Map<String, StringBuilder> SERVICES = new HashMap<>();
    public static final AtomicBoolean ONLY_ONCE = new AtomicBoolean(true);
    public JavacProcessingEnvironment environment;
    public Context context;
    public TreeMaker maker;
    public Trees trees;
    public static void add(String serviceName, String name) {
        if (!SERVICES.containsKey(serviceName)) SERVICES.put(serviceName, new StringBuilder());
        SERVICES.get(serviceName).append(name).append("\n");
    }

    public static final List<ClassProcessor> classProcessors = new ArrayList<>();


    public Filer filer;
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
        environment = (JavacProcessingEnvironment) processingEnv;

        classProcessors.add(new ClassProcessor(environment));
        classProcessors.add(new PackageProcessor(environment));
        classProcessors.add(new AutoConfigProcessor(environment));
        classProcessors.add(new I18nProcessor(environment));
        classProcessors.add(new ModifierProcessor(environment));
    }

    public static final MethodSpec.Builder INIT = MethodSpec
            .methodBuilder("init")
            .addModifiers(Modifier.PUBLIC)
            .returns(TypeName.VOID);

    /**
     * {@inheritDoc}
     *
     * @param annotations
     * @param roundEnv
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        if (ONLY_ONCE.get()) {
            for (ClassProcessor classProcessor : classProcessors) {
                classProcessor.process(annotations, roundEnv);
            }
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Init Processor by wild wind");
            TypeSpec.Builder builder = generatedLanguageProvider();
            StringBuilder sb = new StringBuilder("this");
            I18nProcessor.LANGUAGES.forEach((lang, code) -> sb
                    .append(".setTargetLanguage(\"%s\")".formatted(lang))
                    .append("\n")
                    .append(code));
            INIT.addCode(sb + ";");
            saveAndAddServiceByLanguageGenerated(builder);
            servicesSave();
            ONLY_ONCE.set(false);
        }
        return true;
    }

    public void processMethod(MethodTree node) {
        var tree = (JCTree.JCMethodDecl) node;
        String codeSnippet = """
				for (int i = 0; i < 3; i++) {
							System.out.println("Inject i: " + i);
						}
				""";
        ParserFactory parserFactory = ParserFactory.instance(context);
        JavacParser javacParser = parserFactory.newParser(codeSnippet, false, false, false);

        JCTree.JCStatement jcStatement = javacParser.parseStatement();
        if (tree.body != null)
            tree.body.stats = tree.body.stats.prepend(jcStatement);
    }

    private void saveAndAddServiceByLanguageGenerated(TypeSpec.Builder builder) {
        builder.addMethod(INIT.build());
        JavaFile jf = JavaFile.builder("org.polaris2023.wild_wind.datagen.custom", builder.build()).build();
        try {
            jf.writeTo(filer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InitProcessor.add("org.polaris2023.wild_wind.util.interfaces.ILanguage",
                jf.packageName + "." + builder.build().name);
    }

    private void servicesSave() {
        for (Map.Entry<String, StringBuilder> entry : SERVICES.entrySet()) {
            String service_name = entry.getKey();
            StringBuilder text = entry.getValue();
            try {

                var sourceFile = filer.createResource(StandardLocation.CLASS_OUTPUT, "", "META-INF/services/" + service_name);
                try (Writer writer = sourceFile.openWriter()) {
                    writer.write(text.toString());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static TypeSpec.Builder generatedLanguageProvider() {
        TypeSpec.Builder builder =
                TypeSpec.
                        classBuilder("LanguageProvider" + System.currentTimeMillis())
                        .addFields(List.of(
                                FieldSpec
                                        .builder(ClassName.bestGuess("com.google.gson.Gson"),
                                                "GSON",
                                                Modifier.PRIVATE, Modifier.STATIC)
                                        .initializer("new com.google.gson.GsonBuilder().setLenient().setPrettyPrinting().create()")
                                        .build(),
                                FieldSpec.builder(STRING.get(),
                                                "modid",
                                                Modifier.PRIVATE)
                                        .build(),
                                FieldSpec.builder(STRING.get(),
                                                "targetLanguage",
                                                Modifier.PRIVATE)
                                        .initializer("targetLanguage = \"en_us\"")
                                        .build(),
                                FieldSpec.builder(PACK_OUTPUT.get(),
                                                "output",
                                                Modifier.PRIVATE)
                                        .build(),
                                FieldSpec.builder(ClassName.get(new TypeToken<ConcurrentHashMap<String, ConcurrentHashMap<String, String>>>() {}.getType()),
                                                "languages",
                                                Modifier.PRIVATE, Modifier.FINAL)
                                        .initializer("new ConcurrentHashMap<>()")
                                        .build(),
                                FieldSpec.builder(NIO_PATH.get(),
                                                "languageDir",
                                                Modifier.PRIVATE)
                                        .build()

                        ))
                        .addMethods(List.of(
                                MethodSpec
                                        .methodBuilder("setModid")
                                        .returns(TypeName.VOID)
                                        .addParameter(STRING.get(), "modid")
                                        .addModifiers(Modifier.PUBLIC)
                                        .addCode("this.modid = modid;")
                                        .build(),
                                MethodSpec
                                        .methodBuilder("setLanguageDir")
                                        .returns(TypeName.VOID)
                                        .addParameter(NIO_PATH.get(), "languageDir")
                                        .addModifiers(Modifier.PUBLIC)
                                        .addCode("this.languageDir = languageDir;")
                                        .build(),
                                MethodSpec
                                        .methodBuilder("setOutput")
                                        .returns(TypeName.VOID)
                                        .addParameter(PACK_OUTPUT.get(), "output")
                                        .addModifiers(Modifier.PUBLIC)
                                        .addCode("""
                                                    this.output = output;
                                                    languageDir = output
                                                                    .getOutputFolder(PackOutput.Target.RESOURCE_PACK)
                                                                    .resolve(modid)
                                                                    .resolve("lang");
                                                    """)
                                        .build(),
                                MethodSpec
                                        .methodBuilder("run")
                                        .returns(Costs.TypeNames.COMPLETABLE_FUTURE.get())
                                        .addModifiers(Modifier.PUBLIC)
                                        .addParameter(ParameterSpec
                                                .builder(ClassName.bestGuess("net.minecraft.data.CachedOutput"), "cachedOutput")
                                                .build())
                                        .addCode("""
                                                    init();
                                                    final CompletableFuture<?>[] futures = new CompletableFuture[languages.size()];
                                                            int i = 0;
                                                            for (var entry : languages.entrySet()) {
                                                                String lang = entry.getKey();
                                                                ConcurrentHashMap<String, String> kv = entry.getValue();
                                                                Path json = languageDir.resolve(lang + ".json");
                                                                com.google.gson.JsonElement jsonTree = GSON.toJsonTree(kv);
                                                                futures[i] = DataProvider.saveStable(cachedOutput, jsonTree, json);
                                                                i++;
                                                            }
                                                            return CompletableFuture.allOf(futures);
                                                    """)
                                        .build(),
                                MethodSpec.methodBuilder("getName")
                                        .returns(STRING.get())
                                        .addModifiers(Modifier.PUBLIC)
                                        .addCode("return \"Language Setting by \" + modid;")
                                        .build()
                        ))
                        .addModifiers(Modifier.PUBLIC)
                ;

        builder.addMethod(MethodSpec
                .methodBuilder("setTargetLanguage")
                .returns(ClassName.bestGuess(builder.build().name))
                .addParameter(STRING.get(), "targetLanguage")
                .addModifiers(Modifier.PUBLIC)
                .addCode("""
                        this.targetLanguage = targetLanguage;
                        return this;
                        """)
                .build());
        builder
                .addSuperinterfaces(List.of(
                        ClassName.bestGuess("net.minecraft.data.DataProvider")
                ))
                .addSuperinterface(ParameterizedTypeName.get(ClassName
                        .bestGuess("org.polaris2023.wild_wind.util.interfaces.ILanguage"), ClassName.bestGuess(builder.build().name)))

                .addMethod(MethodSpec.methodBuilder("add")
                        .returns(ClassName.bestGuess(builder.build().name))
                        .addParameter(TypeName.OBJECT, "r")
                        .addParameter(STRING.get(), "value")
                        .addModifiers(Modifier.PUBLIC)
                        .addCode("""
                                    if (r instanceof String string) {
                                        if (!languages.containsKey(targetLanguage)) languages.put(targetLanguage, new ConcurrentHashMap<>());
                                            languages.get(targetLanguage).put(string, value);
                                            return this;
                                        }
                                        return switch (r) {
                                            case net.neoforged.neoforge.registries.DeferredHolder<?, ?> holder -> add(holder.get(), value);
                                            case java.util.function.Supplier<?> supplier -> add(supplier.get(), value);
                                            case net.minecraft.world.item.Item item -> add(item.getDescriptionId(), value);
                                            case net.minecraft.world.level.block.Block block -> add(block.getDescriptionId(), value);
                                            case net.minecraft.world.entity.EntityType<?> type -> add(type.getDescriptionId(), value);
                                            case net.minecraft.network.chat.contents.TranslatableContents contents -> add(contents.getKey(), value);
                                            case net.minecraft.world.item.CreativeModeTab tab -> tab.getDisplayName().getContents() instanceof net.minecraft.network.chat.contents.TranslatableContents contents ?
                                                    add(contents, value) :
                                                    this;
                                            case net.minecraft.world.item.ItemStack stack -> add(stack.getDescriptionId(), value);
                                            default -> throw new IllegalStateException("Unexpected value: " + r);
                                        };
                                    """)
                        .build());
        return builder;
    }
}
