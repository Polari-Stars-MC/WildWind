package org.polaris2023.annotation.language;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author : baka4n
 * {@code @Date : 2025/03/14 21:53:17}
 */
@Retention(RetentionPolicy.SOURCE)
public @interface I18nEnum {
    String en_us();
    String zh_cn();
    String zh_tw();
    I18n.Other[] other() default {};
    @Retention(RetentionPolicy.SOURCE)
    @interface InnerI18n {
        String en_us();
        String zh_cn();
        String zh_tw();
        I18n.Other[] other() default {};
        Type FIX() default Type.SUFFIX;

        enum Type {
            PREFIX, SUFFIX
        }
    }
}
