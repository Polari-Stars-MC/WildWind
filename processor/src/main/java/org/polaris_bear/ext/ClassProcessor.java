package org.polaris_bear.ext;

import org.polaris_bear.annotation.AutoConfig;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;

public class ClassProcessor extends IProcessor<TypeElement> {
    public ClassProcessor(Element element, ProcessingEnvironment env) {
        super(element, env);
    }

    @Override
    public TypeElement check(Element e) {
        if (e.getKind() == ElementKind.CLASS)
            return (TypeElement) e;
        return null;
    }

    @Override
    public void processor() {

    }
}
