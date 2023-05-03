package com.example.bookaroom.views.terminal.inputs;

import com.example.bookaroom.bookaroom.periodo.Periodo;
import com.example.bookaroom.views.abstractView.inputs.InputComponente;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DataInputConsole implements InputComponente<LocalDate> {
    TextInputConsole dataStrInput = new TextInputConsole("Insira a data <dd/MM/yyyy>: ", this::dateValidate);

    Boolean dateValidate(String date) {
        try {
            LocalDate.parse(date, Periodo.FORMATO_DATA);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public void render() {}

    @Override
    public LocalDate get() {
        return LocalDate.parse(dataStrInput.get(), Periodo.FORMATO_DATA);
    }
}
