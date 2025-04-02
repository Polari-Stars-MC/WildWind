package org.polaris2023.annotation.modelgen.block;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author : baka4n
 * {@code @Date : 2025/03/20 21:25:41}
 * {@code {@link CubeAll}}
 */
@Retention(RetentionPolicy.SOURCE)
public @interface BasicBlock {
    String render_type() default "";
    boolean item() default true;
}
