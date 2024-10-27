package org.polaris2023.ext;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.VariableElement;

public class FieldProcessor extends IProcessor<VariableElement> {
    public FieldProcessor(Element element, ProcessingEnvironment env) {
        super(element, env);
    }

    @Override
    public VariableElement check(Element e) {
        if (e.getKind() == ElementKind.FIELD)
            return (VariableElement) e;
        return null;
    }

    @Override
    public void processor() {

    }
}
