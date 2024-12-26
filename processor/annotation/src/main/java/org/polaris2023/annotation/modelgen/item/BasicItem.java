package org.polaris2023.annotation.modelgen.item;

import org.polaris2023.annotation.modelgen.other.Addition;
import org.polaris2023.annotation.modelgen.other.KeyAddition;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface BasicItem {
    Addition value() default @Addition;
    KeyAddition[] more() default {};
}
