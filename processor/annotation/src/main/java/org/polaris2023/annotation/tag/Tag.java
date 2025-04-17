package org.polaris2023.annotation.tag;

import org.polaris2023.annotation.enums.TagType;
import org.polaris2023.annotation.register.ResourceLocation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author : baka4n
 * {@code @Date : 2025/03/29 20:40:20}
 */
@Retention(RetentionPolicy.SOURCE)
public @interface Tag {
    String[] names();
    TagType type();
    boolean mixin() default false;
    boolean tag() default false;
}
