package org.polaris2023.annotation.modelgen.block;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author : baka4n
 * {@code @Date : 2025/03/20 21:58:49}
 */
@Retention(RetentionPolicy.SOURCE)
public @interface CubeOrientable {
    String side() default "";
    String front() default "";
    String top() default "";
    boolean item() default true;
}
