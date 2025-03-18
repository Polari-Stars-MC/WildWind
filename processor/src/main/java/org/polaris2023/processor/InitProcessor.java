package org.polaris2023.processor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.*;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.Tree;
import com.sun.source.util.Trees;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.parser.JavacParser;
import com.sun.tools.javac.parser.ParserFactory;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Context;
import org.polaris2023.processor.clazz.ClassProcessor;
import org.polaris2023.processor.clazz.config.AutoConfigProcessor;
import org.polaris2023.processor.clazz.datagen.I18nProcessor;
import org.polaris2023.processor.clazz.datagen.ModelProcessor;
import org.polaris2023.processor.pack.PackageProcessor;
import org.polaris2023.utils.Codes;
import org.polaris2023.utils.Unsafe;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

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

    public Trees trees;
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static Optional<? extends MethodTree> modelInit;

    public static void modelGen(Context context,String code) {

        modelInit.ifPresent(tree -> {
            var jcMethodDecl = (JCTree.JCMethodDecl) tree;

            ParserFactory parserFactory = ParserFactory.instance(context);
            JavacParser javacParser = parserFactory.newParser(code, false, false, false);

            JCTree.JCStatement jcStatement = javacParser.parseStatement();
            if (jcMethodDecl.body != null)
                jcMethodDecl.body.stats = jcMethodDecl.body.stats.prepend(jcStatement);
        });
    }

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
        trees = Trees.instance(environment);
        classProcessors.add(new ClassProcessor(environment));
        classProcessors.add(new PackageProcessor(environment));
        classProcessors.add(new AutoConfigProcessor(environment));
        classProcessors.add(new I18nProcessor(environment));
        classProcessors.add(new ModelProcessor(environment));
    }

    /**
     * {@inheritDoc}
     *
     * @param annotations
     * @param roundEnv
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        if (ONLY_ONCE.get()) {
            Symbol.ClassSymbol typeElement1 = environment.getElementUtils().getTypeElement("org.polaris2023.wild_wind.datagen.custom.WildWindClientProvider");
            ClassTree classTree = trees.getTree(typeElement1);
            modelInit =
                    ((Stream<? extends MethodTree>) classTree
                            .getMembers()
                            .stream()
                            .filter(tree -> tree.getKind().equals(Tree.Kind.METHOD)))
                            .filter(method -> method.getName().toString().equals("init")).findFirst();
            for (ClassProcessor classProcessor : classProcessors) {
                classProcessor.process(annotations, roundEnv);
            }

            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Init Processor by wild wind");
            StringBuilder language_init = new StringBuilder("this\n");

            I18nProcessor.LANGUAGES.forEach((lang, code) -> language_init
                    .append("\t\t\t.setTargetLanguage(\"%s\")".formatted(lang))
                    .append("\n")
                    .append(code));
            Codes.LanguageProvider.saveAndAddServiceCode(filer, "org.polaris2023.wild_wind.util.interfaces.ILanguage", language_init);
            Codes.ModelProvider.saveAndAddServiceCode(filer, "org.polaris2023.wild_wind.util.interfaces.IModel", ModelProcessor.MODEL);
            servicesSave();
            ONLY_ONCE.set(false);
        }
        return true;
    }

//    public void processMethod(MethodTree node) {
//        var tree = (JCTree.JCMethodDecl) node;
//        String codeSnippet = """
//				for (int i = 0; i < 3; i++) {
//							System.out.println("Inject i: " + i);
//						}
//				""";
//        ParserFactory parserFactory = ParserFactory.instance(context);
//        JavacParser javacParser = parserFactory.newParser(codeSnippet, false, false, false);
//
//        JCTree.JCStatement jcStatement = javacParser.parseStatement();
//        if (tree.body != null)
//            tree.body.stats = tree.body.stats.prepend(jcStatement);
//    }


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
}
