package org.polaris2023.annotation.tag;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author : baka4n
 * {@code @Date : 2025/03/29 20:38:27}
 */
@Retention(RetentionPolicy.SOURCE)
public @interface CTag {
    String[] name();
}
