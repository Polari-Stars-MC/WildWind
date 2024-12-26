package org.polaris2023.annotation.modelgen.other;

public @interface Addition {
    Display[] display() default {};
    Override[] overrides() default {};
}
