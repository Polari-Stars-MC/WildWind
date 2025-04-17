package org.polaris2023.annotation.handler;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/16 21:44:26}
 */
@Retention(RetentionPolicy.SOURCE)//初始化mixin类所在位置
public @interface MixinDefine {
    String value();//classname
}
