package org.polaris2023.ext;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.experimental.Delegate;

import javax.annotation.processing.*;
import javax.lang.model.element.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class IProcessor<T extends Element> {


    final T check;
    public final List<Maps.EntryTransformer<Element, ProcessingEnvironment, ClassProcessor>> PROCESSORS = new ArrayList<>();
    public final List<Maps.EntryTransformer<Element, ProcessingEnvironment, FieldProcessor>> FIELD_PROCESSORS = new ArrayList<>();

    @Delegate
    final ProcessingEnvironment env;
    public IProcessor(Element element, ProcessingEnvironment env) {
       check = check(element);
       this.env = env;
    }

    public void add(Maps.EntryTransformer<Element, ProcessingEnvironment, ClassProcessor> processor) {
        PROCESSORS.add(processor);
    }

    public void addField(Maps.EntryTransformer<Element, ProcessingEnvironment, FieldProcessor> processor) {
        FIELD_PROCESSORS.add(processor);
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
