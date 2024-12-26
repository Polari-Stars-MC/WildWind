package org.polaris2023.annotation.modelgen;

import org.polaris2023.annotation.KV;
import org.polaris2023.annotation.modelgen.other.Element;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface MoreBlockAndItem {
    String credit() default "datagen in wild wind author is Baka4n";
    KV[] textures() default {};
    Element[] elements() default {};
}
