package org.polaris_bear.ext;

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
    public final List<IProcessor<?>> PROCESSORS = new ArrayList<>();

    @Delegate
    final ProcessingEnvironment env;
    public IProcessor(Element element, ProcessingEnvironment env) {
       check = check(element);
       this.env = env;
    }

    public void add(Maps.EntryTransformer<Element, ProcessingEnvironment, IProcessor<?>> processor, Element e) {
        PROCESSORS.add(processor.transformEntry(e, env));
    }

    public void run() {
        if (check != null) {
            processor();
        }
        for (IProcessor<?> processor : PROCESSORS) {
            processor.run();
        }
    }

    public abstract T check(Element e);
    public abstract void processor();

}
