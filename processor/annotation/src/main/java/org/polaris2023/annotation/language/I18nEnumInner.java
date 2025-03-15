package org.polaris2023.annotation.language;

/**
 * @author : baka4n
 * {@code @Date : 2025/03/15 21:54:47}
 */
public @interface I18nEnumInner {
    String en_us();

    String zh_cn();

    String zh_tw();

    Other18n[] other() default {};
}
