package org.polaris2023.annotation.modelgen.other;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface UV {
    double horizontallyMove();
    double verticallyMove();
    double horizontallyScale();
    double verticallyScale();
}
