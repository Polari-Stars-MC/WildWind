package org.polaris2023.ext;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.experimental.Delegate;
import org.polaris2023.ext.interfaces.IClassProcessor;

import javax.annotation.processing.*;
import javax.lang.model.element.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class IProcessor<T extends Element> implements IClassProcessor {



    T check;
    public final List<Maps.EntryTransformer<Element, ProcessingEnvironment, ClassProcessor>> PROCESSORS = new ArrayList<>();
    public final List<Maps.EntryTransformer<Element, ProcessingEnvironment, FieldProcessor>> FIELD_PROCESSORS = new ArrayList<>();

    @Delegate
    ProcessingEnvironment env;

    public void add(Maps.EntryTransformer<Element, ProcessingEnvironment, ClassProcessor> processor) {
        PROCESSORS.add(processor);
    }

    public void addField(Maps.EntryTransformer<Element, ProcessingEnvironment, FieldProcessor> processor) {
        FIELD_PROCESSORS.add(processor);
    }

    @Override
    public IClassProcessor setCheck(Element element) {
        check = check(element);
        return this;
    }

    @Override
    public IClassProcessor setEnv(ProcessingEnvironment env) {
        this.env = env;
        return this;
    }

    public void run() {
        if (check != null) {
            processor();
            for (var processor : PROCESSORS) {
                processor.transformEntry(check, env).run();
            }
            for (Element enclosedElement : check.getEnclosedElements()) {
                for (Maps.EntryTransformer<Element, ProcessingEnvironment, FieldProcessor> fieldProcessor : FIELD_PROCESSORS) {
                    fieldProcessor.transformEntry(enclosedElement, env).run();
                }
            }
        }



    }

    public abstract T check(Element e);
    public abstract void processor();

}
