package com.example.bookaroom.views.abstractView;

import com.example.bookaroom.views.abstractView.inputs.InputComponente;

public abstract class TextInput implements InputComponente<String> {
    protected String label;
    protected String validateExpression;
    public TextInput(String label) {
        this.label = label;
    }
}
