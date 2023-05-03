package com.example.bookaroom.views.terminal.inputs;

import com.example.bookaroom.views.abstractView.inputs.SelectInputComponente;

import java.util.HashMap;

public class OptionSelector<T> implements SelectInputComponente<T> {
    public static class Option<V> {
        String nome;
        V value;

        public Option(String nome, V value) {
            this.nome = nome;
            this.value = value;
        }

        public String nome() { return nome; }
        public V value() { return value; }
    }

    private final String title;
    private final HashMap<Integer, Option<T>> options;
    private T selected;

    @SafeVarargs
    public OptionSelector(String title, Option<T> ...options) {
        this.options = new HashMap<>();
        this.title = title;

        int i = 0;
        for(Option<T> option : options) {
            this.options.put(++i, option);
        }
    }

    public OptionSelector(String title, Iterable<T> values) {
        this.options = new HashMap<>();
        this.title = title;
        values.forEach(this::addOption);
    }

    public void addOption(String nome, T value) {
        options.put(options.size()+1, new Option<>(nome, value));
    }

    public void addOption(T value) {
        addOption(value.toString(), value);
    }

    public void render() {
        System.out.println(title);
        options.forEach((key, option) -> System.out.println("\t" + key + ". " + option.nome()));
    }

    public void waitInput() {
        String message = "Escolha uma opção: ";
        TextInputConsole optionInput = new TextInputConsole(message);
        Option<T> OptionSelected = options.get(optionInput.getInt());

        while(OptionSelected == null) {
            System.out.println("Opçao Invalida\n");
            OptionSelected = options.get(optionInput.getInt());
        }
        selected = OptionSelected.value();
    }

    public T get() {
        render();
        waitInput();
        return selected;
    }
}
