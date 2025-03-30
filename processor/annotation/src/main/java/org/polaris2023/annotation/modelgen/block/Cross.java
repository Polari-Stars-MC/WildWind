package org.polaris2023.annotation.modelgen.block;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author : baka4n
 * {@code @Date : 2025/03/29 21:20:55}
 */
@Retention(RetentionPolicy.SOURCE)
public @interface Cross {
    String cross() default "";
    String render_type() default "cutout";//默认剪切模型
    boolean item() default true;
}
