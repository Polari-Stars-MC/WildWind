package org.polaris2023.annotation.modelgen.block;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Wall {
    boolean bricks() default false;
    boolean item() default true;
    String wall() default  "";
}
