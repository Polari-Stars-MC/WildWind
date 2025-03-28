package org.polaris2023.annotation.modelgen.block;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author : baka4n
 * {@code @Date : 2025/03/28 17:38:00}
 */
@Retention(RetentionPolicy.SOURCE)
public @interface CubeAllFor {
    CubeAll cube() default @CubeAll;
    Type[] value() default {};
    @Retention(RetentionPolicy.SOURCE)
    @interface Type {
        String key();
        CubeAll cube();
    }
}
