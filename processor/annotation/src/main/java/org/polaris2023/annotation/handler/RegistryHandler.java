package org.polaris2023.annotation.handler;

import org.polaris2023.annotation.enums.RegType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/02 17:02:57}
 * 获取注册表所在位置
 */
@Retention(RetentionPolicy.SOURCE)
public @interface RegistryHandler {
    RegType value() default RegType.NONE;

}
