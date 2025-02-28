package org.polaris2023.annotation.modelgen.block;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author : baka4n
 * {@code @Date : 2025/02/28 19:22:17}
 */
@Retention(RetentionPolicy.SOURCE)
public @interface PressurePlate {
    String texture() default "";
    boolean item() default true;
}
