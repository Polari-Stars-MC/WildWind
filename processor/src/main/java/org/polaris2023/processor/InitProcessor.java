package org.polaris2023.processor;

import com.google.auto.service.AutoService;
import com.google.common.collect.Maps;
import org.polaris2023.ext.ClassProcessor;
import org.polaris2023.ext.config.ConfigProcessor;
import org.polaris2023.ext.IProcessor;
import org.polaris2023.ext.PackageProcessor;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import java.util.List;
import java.util.Set;

@AutoService(Processor.class)
@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class InitProcessor extends AbstractProcessor {
    public Filer filer;
    public static final List<Maps.EntryTransformer<Element, ProcessingEnvironment, IProcessor<?>>> PROCESSORS = List.of(
            PackageProcessor::new,
            ConfigProcessor::new,
            ClassProcessor::new
    );
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();

    }



    /**
     * {@inheritDoc}
     *
     * @param annotations
     * @param roundEnv
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        roundEnv
                .getRootElements()
                .forEach(element -> PROCESSORS
                        .forEach(processor -> processor
                                .transformEntry(element, processingEnv)
                                .run()));

        return true;
    }
}
