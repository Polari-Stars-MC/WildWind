package org.polaris2023.annotation.modelgen.block;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author : baka4n
 * {@code @Date : 2025/02/28 17:16:32}
 */
@Retention(RetentionPolicy.SOURCE)
public @interface Fence {
    boolean item() default true;
    String texture() default "";
}
