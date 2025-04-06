package org.polaris2023.annotation.register.attachments;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/06 18:01:34}
 */
@Retention(RetentionPolicy.SOURCE)
public @interface AttachmentInteger {
    int defaultValue() default 0;
}
