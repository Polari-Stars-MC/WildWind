package org.polaris2023.annotation.modelgen.block;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author : baka4n
 * {@code @Date : 2025/03/29 22:01:03}
 */
@Retention(RetentionPolicy.SOURCE)
public @interface Carpet {
    String carpet() default "";
    String render_type() default "";//默认剪切模型
    boolean item() default true;
}
