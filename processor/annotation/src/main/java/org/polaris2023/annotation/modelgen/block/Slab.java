package org.polaris2023.annotation.modelgen.block;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Slab {
    String type() default "";
    boolean item() default true;
    String all() default  "";
    String bottom() default "";
    String side() default "";
    String top() default "";
}
