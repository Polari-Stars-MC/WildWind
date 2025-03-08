package org.polaris2023.annotation.language;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface I18n {
    String en_us();
    String zh_cn();
    String zh_tw();
    String descriptionId() default "";
    Other[] other() default {};
    @interface Other {
        String value();
        String translate();
    }
}
