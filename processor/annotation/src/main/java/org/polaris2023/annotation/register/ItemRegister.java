package org.polaris2023.annotation.register;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface ItemRegister {
    String value() default "ModItems";//generation name
    String[] simpleItem() default {};// simple items
    ItemEntry[] entry();
    @interface ItemEntry {
        String[] value();// register name
        String className() default "net.minecraft.world.item.Item";// class name
    }

    @interface SpawnEgg {
        String value();// register name
    }

    @interface Properties {

    }
}
