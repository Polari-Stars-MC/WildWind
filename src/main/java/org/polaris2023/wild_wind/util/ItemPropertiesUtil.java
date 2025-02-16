package org.polaris2023.wild_wind.util;

import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * @author : baka4n
 * {@code @Date : 2025/02/16 18:24:53}
 */
public enum ItemPropertiesUtil implements Consumer<Item.Properties> {
    STACK_TO_1(p -> p.stacksTo(1)),
    STACK_TO_SNOW(p -> p.stacksTo(16))
    ;
    private final Consumer<Item.Properties> props;
    ItemPropertiesUtil(Consumer<Item.Properties> props) {
        this.props = props;
    }

    @Override
    public void accept(Item.Properties properties) {
        props.accept(properties);
    }

    @NotNull
    @Override
    public Consumer<Item.Properties> andThen(@NotNull Consumer<? super Item.Properties> after) {
        return props.andThen(after);
    }
}
