package org.polaris2023.annotation.language;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author : baka4n
 * {@code @Date : 2025/03/15 21:51:30}
 */
@Retention(RetentionPolicy.SOURCE)
public @interface I18nEnum {
    String en_us();
    String zh_cn();
    String zh_tw();
    Other18n[] other() default {};
    Type type() default Type.PREFIX;
    enum Type {
        PREFIX,SUFFIX
    }

}
