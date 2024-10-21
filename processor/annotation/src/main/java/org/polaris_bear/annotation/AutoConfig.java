package org.polaris_bear.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface AutoConfig {
    enum Type {
        COMMON,
        CLIENT,
        SERVER,
        STARTUP
    }
    Type value() default Type.COMMON;
    String modid();
}
