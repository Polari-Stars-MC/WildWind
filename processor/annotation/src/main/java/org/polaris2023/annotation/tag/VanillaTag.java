package org.polaris2023.annotation.tag;

import org.polaris2023.annotation.enums.TagType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/14 18:33:05}
 */
@Retention(RetentionPolicy.SOURCE)
public @interface VanillaTag {
    String[] names();
    TagType type();
    boolean mixin() default false;
    boolean tag() default false;
}
