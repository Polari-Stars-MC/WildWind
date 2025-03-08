package org.polaris2023.annotation.modelgen.block;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author : baka4n
 * {@code @Date : 2025/02/24 14:02:16}
 */
@Retention(RetentionPolicy.SOURCE)
public @interface Log {
    boolean item() default true;
}
