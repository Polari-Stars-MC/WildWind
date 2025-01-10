package org.polaris2023.wild_wind.util.interfaces;

import net.minecraft.data.DataProvider;

public interface IModel<T extends IModel<T> & DataProvider> extends IData<T> {
    default void init() {}
}
