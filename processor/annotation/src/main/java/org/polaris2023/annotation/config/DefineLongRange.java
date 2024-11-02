package org.polaris2023.annotation.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface DefineLongRange {
    String value() default "";
    long defaultValue();
    long min();
    long max();
}
