package org.polaris2023.processor.clazz.registry;

import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import org.polaris2023.annotation.enums.RegType;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/02 18:34:04}
 */
public class BlockRegistryProcessor extends RegistryProcessor {
    @Override
    public RegType defaultType() {
        return RegType.Block;
    }

    public BlockRegistryProcessor(JavacProcessingEnvironment environment) {
        super(environment);
    }
}
