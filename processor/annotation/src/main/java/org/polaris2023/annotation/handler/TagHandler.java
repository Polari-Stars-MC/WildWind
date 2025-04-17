package org.polaris2023.annotation.handler;

import org.polaris2023.annotation.enums.TagType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/14 14:27:23}
 */
@Retention(RetentionPolicy.SOURCE)
public @interface TagHandler {
    TagType value() default TagType.None;
}
