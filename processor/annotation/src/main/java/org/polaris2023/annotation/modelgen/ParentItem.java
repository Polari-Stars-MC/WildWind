package org.polaris2023.annotation.modelgen;

import org.polaris2023.annotation.KV;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface ParentItem {
    String parent();
    KV[] textures() default {};
}
