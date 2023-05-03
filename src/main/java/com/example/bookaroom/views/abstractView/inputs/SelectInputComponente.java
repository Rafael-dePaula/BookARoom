package com.example.bookaroom.views.abstractView.inputs;

public interface SelectInputComponente<T> extends InputComponente<T> {
    void addOption(T value);
    void addOption(String text, T value);

    default void setOptions(Iterable<T> options) {
        options.forEach(this::addOption);
    }
}
