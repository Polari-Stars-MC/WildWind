package org.polaris2023.ext.interfaces;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;

public interface IProcessor<T extends Element> {
    IProcessor<T> setCheck(Element element);
    IProcessor<T> setEnv(ProcessingEnvironment env);
    void run();
}
