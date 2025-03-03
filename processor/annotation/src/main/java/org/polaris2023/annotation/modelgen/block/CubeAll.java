package org.polaris2023.annotation.modelgen.block;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface CubeAll {
    boolean item() default true;
    String render_type() default "";
    String texture() default "";
}
