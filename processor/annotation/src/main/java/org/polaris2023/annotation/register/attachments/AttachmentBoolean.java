package org.polaris2023.annotation.register.attachments;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/06 19:45:25}
 */
@Retention(RetentionPolicy.SOURCE)
public @interface AttachmentBoolean {
    boolean defaultValue() default false;
}
