package org.polaris2023.annotation.tag;

import org.polaris2023.annotation.enums.TagType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/16 17:59:05}
 */
@Retention(RetentionPolicy.SOURCE)
public @interface WildWindTag {
    String[] names();
    TagType type();
}
