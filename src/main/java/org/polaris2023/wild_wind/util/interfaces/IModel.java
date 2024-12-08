package org.polaris2023.wild_wind.util.interfaces;

import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;

public interface IModel<T extends IModel<T> & DataProvider> {
    T setModid(String modid);
    T setOutput(PackOutput output);
    default void init() {}
}
