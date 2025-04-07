package org.polaris2023.annotation.modelgen.other;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Display {
    Type name();
    Vec3f rotation() default @Vec3f(x = 0.0F, y = 0.0F, z = 0.0F);
    Vec3f translation() default @Vec3f(x = 0.0F, y = 0.0F, z = 0.0F);
    Vec3f rightRotation() default @Vec3f(x = 0.0F, y = 0.0F, z = 0.0F);
    Vec3f scale() default @Vec3f(x = 1.0F, y = 1.0F, z = 1.0F);
    enum Type {
        NONE,
        THIRD_PERSON_LEFT_HAND,
        THIRD_PERSON_RIGHT_HAND,
        FIRST_PERSON_LEFT_HAND,
        FIRST_PERSON_RIGHT_HAND,
        HEAD,
        GUI,
        GROUND,
        FIXED
    }
}
