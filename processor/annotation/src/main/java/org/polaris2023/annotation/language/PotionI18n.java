package org.polaris2023.annotation.language;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface PotionI18n {
	String PREFIX_SPLASH_EN_US = "Splash ";
	String PREFIX_SPLASH_ZH_CN = "喷溅型";
	String PREFIX_SPLASH_ZH_TW = "飛濺型";
	String PREFIX_LINGERING_EN_US = "Lingering ";
	String PREFIX_LINGERING_ZH_CN = "滞留型";
	String PREFIX_LINGERING_ZH_TW = "滯留型";
	String SUFFIX_EN_US = " Potion";
	String SUFFIX_ZH_CN = "药水";
	String SUFFIX_ZH_TW = "藥水";

	String en_us();
	String zh_cn();
	String zh_tw();
}
