package org.polaris2023.utils;


import lombok.Getter;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;

@SuppressWarnings("deprecation")
public class Unsafe {
    @Getter
    private static final sun.misc.Unsafe unsafe;
    @Getter
    private static final MethodHandles.Lookup lookup;

    static {
        MethodHandles.lookup();
        try {
            Field theUnsafe = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (sun.misc.Unsafe) theUnsafe.get(null);
            Field field = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
            Object base = unsafe.staticFieldBase(field);
            long offset = unsafe.staticFieldOffset(field);
            lookup = (MethodHandles.Lookup) unsafe.getObject(base, offset);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static void exportJdkModule() {
        try {
            var module = ModuleLayer.boot().findModule("jdk.compiler").orElseThrow();
            var method = Module.class.getDeclaredMethod("implAddExportsToAllUnnamed", String.class);
            var handle = getLookup().unreflect(method).bindTo(module);
            for (String aPackage : module.getPackages()) {
                handle.invoke(aPackage);
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

}
