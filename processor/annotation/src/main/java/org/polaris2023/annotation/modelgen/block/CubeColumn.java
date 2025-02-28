package org.polaris2023.annotation.modelgen.block;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author : baka4n
 * {@code @Date : 2025/02/28 18:45:46}
 */
@Retention(RetentionPolicy.SOURCE)
public @interface CubeColumn {
    boolean horizontal() default false;
    boolean suffix() default false;
    boolean item() default true;
    String end() default "";
    String side() default "";
}
