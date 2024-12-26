package org.polaris2023.wild_wind.util.interfaces;

public interface ISelf<T> {
    @SuppressWarnings("unchecked")
    default T wild_wind$self() {
        return (T) this;
    }
}
