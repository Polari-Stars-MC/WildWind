package org.polaris2023.annotation.modelgen.block;

import org.polaris2023.annotation.register.ResourceLocation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author : baka4n
 * {@code @Date : 2025/03/20 21:58:49}
 */
@Retention(RetentionPolicy.SOURCE)
public @interface CubeBottomTop {
    ResourceLocation side();
    ResourceLocation bottom();
    ResourceLocation top();
}
