package org.polaris2023.annotation.modelgen.block;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Stairs {
    boolean item() default true;
    String bottom() default "";
    String side() default "";
    String top() default "";
    String all() default "";
}
