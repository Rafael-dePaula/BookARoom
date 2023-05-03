package com.example.bookaroom.views.terminal.inputs;

import com.example.bookaroom.views.abstractView.inputs.InputComponente;

import java.util.Scanner;
import java.util.function.Function;

public class TextInputConsole implements InputComponente<String> {
    String label;
    Function<String, Boolean> validateFunction;

    static Function<String, Boolean> validateExpressionFunc(String expression) {
        return (string) -> string.matches(expression);
    }

    public TextInputConsole(String label) {
        this.label = label;
        validateFunction = (str) -> true;
    }

    public TextInputConsole(String label, String validateExpression) {
        this(label, validateExpressionFunc(validateExpression));
    }

    public TextInputConsole(String label, Function<String, Boolean> validateFunction) {
        this(label);
        this.validateFunction = validateFunction;
    }

    private boolean validate(String value) {
        return validateFunction.apply(value);
    }

    public void render() {
        System.out.print(label);
    }

    public String get() {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            render();
            String userInput = scanner.nextLine();
            if(validate(userInput)) {
                return userInput;
            }
            System.out.println("Input invalido");
        }
    }

    public Integer getInt() {
        while(true) {
            try {
                return Integer.parseInt(get());
            } catch (NumberFormatException e) {
                System.out.println("Input invalido");
            }
        }
    }
}
