package org.polaris2023.ext;


import lombok.Getter;
import lombok.experimental.Delegate;
import org.polaris2023.ext.interfaces.IClassProcessor;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;


@Getter
public class ClassProcessor implements IClassProcessor {

    TypeElement check;
    @Delegate
    ProcessingEnvironment env;

    public ClassProcessor() {}

    public TypeElement check(Element e) {
        if (e.getKind().isClass())
            return (TypeElement) e;
        return null;
    }

    public void processor() {
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

    @Override
    public void run() {
        if (check != null) {
            processor();
        }
    }
}
