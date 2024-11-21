package org.polaris2023.annotation.jc;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Final {
    boolean field() default false;
    boolean method() default false;
}
